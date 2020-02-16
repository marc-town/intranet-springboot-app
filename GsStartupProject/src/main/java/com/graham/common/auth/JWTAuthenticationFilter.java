package com.graham.common.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.graham.interfaces.request.LoginRequestForm;
import com.graham.interfaces.response.LoginResponseForm;
import com.graham.services.AttendanceService;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceService.class);
	
	private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        // ログイン用のpath
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(SecurityConstants.SIGNIN_URL, "POST"));

        // ログイン用のID/PWのパラメータ名
        setUsernameParameter(SecurityConstants.SIGNIN_ID);
        setPasswordParameter(SecurityConstants.PASSWORD);

    }

    // 認証の処理
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	LoginRequestForm userForm = new ObjectMapper().readValue(req.getInputStream(), LoginRequestForm.class);

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


    // 認証に成功した場合の処理
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // loginIdからtokenを設定してヘッダにセットする
        String token = TokenCreater.createToken(((LoginResponseForm)auth.getPrincipal()).getUsername());
        res.addHeader(SecurityConstants.AUTHORIZATION_HEADER_NAME, SecurityConstants.TOKEN_PREFIX + token);
    }
}
