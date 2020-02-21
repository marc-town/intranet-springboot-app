package com.graham.security;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
/**
 * トークン生成クラス
 */
@Component
public class JwtTokenProvider {
	
	@Autowired
    private MessageSource messageSource;
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${gsol.app.jwtSecret}")
	private static String jwtSecret;

	@Value("${gsol.app.jwtExpirationMs}")
	private static int jwtExpirationMs;
	
	public String generateJwtToken(Authentication authentication){
		
		LOGGER.info("BEGIN JwtTokenProvider generateJwtToken");
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) 
                // 有効期限30分（ミリ秒で指定）
                .setExpiration(new Date(System.currentTimeMillis() + JwtSecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, JwtSecurityConstants.SECRET_KEY)
                .compact();
    }
	/**
	 * loginIdからトークンを生成する
	 * 
	 * @param loginId
	 * @return token 生成したトークン
	 */
	public static String createJwtToken(String loginId) {
		LOGGER.info("BEGIN JwtTokenProvider createJwtToken");
		return Jwts.builder()
                .setSubject(loginId) 
                // 有効期限30分（ミリ秒で指定）
                .setExpiration(new Date(System.currentTimeMillis() + JwtSecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, String.join("", Collections.nCopies(23, JwtSecurityConstants.SECRET_KEY)))
                .compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			LOGGER.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOGGER.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
