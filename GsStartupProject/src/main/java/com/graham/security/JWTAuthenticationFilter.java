package com.graham.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.graham.interfaces.request.JwtRequestForm;
import com.graham.services.JwtUserDetailsServiceImpl;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);
	
	private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.setbCryptPasswordEncoder(bCryptPasswordEncoder);
        
        // ログイン用のpath
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(JwtSecurityConstants.SIGNIN_URL, "POST"));

        // ログイン用のID/PWのパラメータ名
        setUsernameParameter(JwtSecurityConstants.SIGNIN_ID);
        setPasswordParameter(JwtSecurityConstants.PASSWORD);

    }

    // 認証の処理
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
		LOGGER.info("BEGIN JWTAuthorizationFilter attemptAuthentication");

        try {
        	JwtRequestForm userForm = new ObjectMapper().readValue(req.getInputStream(), JwtRequestForm.class);

            // 認証情報をセット。
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userForm.getLoginId(),
                            userForm.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
        	LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


    // 認証に成功した場合の処理
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // loginIdからtokenを設定してヘッダにセットする
        String token = JwtTokenProvider.createJwtToken(((UserPrincipal)auth.getPrincipal()).getUsername());
        // TODO
        System.out.println(token);
        res.addHeader(JwtSecurityConstants.AUTHORIZATION_HEADER_NAME, JwtSecurityConstants.TOKEN_PREFIX + token);
    }
}
