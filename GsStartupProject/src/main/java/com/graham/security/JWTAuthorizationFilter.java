package com.graham.security;

import io.jsonwebtoken.Jwts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.graham.services.JwtUserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 認可フィルター
 * ログイン後、SpringSecurityで守られたパスにアクセスされた時に動作する
 * JWTの検証、有効期限を延長（問題なければJWTの再発行）
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);
	
	private AuthenticationManager authenticationManager;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    	
		LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");
		
        String header = req.getHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME);
        
        LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");

        if (header == null || !header.startsWith(JwtSecurityConstants.TOKEN_PREFIX)) {
        	System.out.println("!!!! header : " + header);
        	LOGGER.info("JWTAuthorizationFilter doFilterInternal header == null || !header.startsWith(JwtSecurityConstants.TOKEN_PREFIX)");
            chain.doFilter(req, res);
            return;
        }
        LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");

        // AuthorizationヘッダのBearer Prefixである場合
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        Authentication authentication = authenticationManager.authenticate(getAuthentication(req));
        LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");
        
        // jwtの期限を更新
        // これにより、ログイン後アクセスするたびにjwtの期限が更新される
        String token = jwtTokenProvider.generateJwtToken(authentication);
        res.addHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME,
        		JwtSecurityConstants.TOKEN_PREFIX + token);
        LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
        LOGGER.info("BEGIN JWTAuthorizationFilter doFilterInternal");
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    	LOGGER.info(messageSource.getMessage("border_line", null, Locale.JAPANESE));
		LOGGER.info("BEGIN JWTAuthorizationFilter getAuthentication");
		LOGGER.info(messageSource.getMessage("border_line", null, Locale.JAPANESE));
		
        String token = request.getHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(JwtSecurityConstants.SECRET_KEY.getBytes())
                    .parseClaimsJws(token.replace(JwtSecurityConstants.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
