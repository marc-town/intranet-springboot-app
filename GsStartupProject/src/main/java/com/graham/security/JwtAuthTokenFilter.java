package com.graham.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.graham.services.JwtUserDetailsServiceImpl;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	/**
	 * リクエストヘッダからトークンを取得し検証する
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			LOGGER.info("BEGIN JwtAuthTokenFilter doFilterInternal");
			
			String jwt = parseJwt(request);
			if (jwt != null && jwtTokenProvider.validateJwtToken(jwt)) {
				String username = jwtTokenProvider.getUserNameFromJwtToken(jwt);

				UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			LOGGER.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * リクエストヘッダからトークン部分を抜き出す
	 * 
	 * @param request リクエスト情報
	 * @return token ヘッダから抽出したトークン
	 */
	private String parseJwt(HttpServletRequest request) {
		LOGGER.info("BEGIN JwtAuthTokenFilter parseJwt");
		
		String headerAuth = request.getHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME);
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(JwtSecurityConstants.TOKEN_PREFIX)) {
			return headerAuth.replace(JwtSecurityConstants.TOKEN_PREFIX, "");
		}

		return null;
	}
}
