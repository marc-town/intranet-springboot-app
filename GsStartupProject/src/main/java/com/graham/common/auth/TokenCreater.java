package com.graham.common.auth;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenCreater {

	/**
	 * loginIdからトークンを生成する
	 * 
	 * @param loginId
	 * @return token 生成したトークン
	 */
	public static String createToken(String loginId){
        return Jwts.builder()
                .setSubject(loginId) 
                // 有効期限30分（ミリ秒で指定）
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.CRYPT_KEY.getBytes())
                .compact();
    }
}
