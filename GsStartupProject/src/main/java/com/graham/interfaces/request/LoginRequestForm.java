package com.graham.interfaces.request;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class LoginRequestForm {

	/** ログインID */
	private String loginId;
	
	/** パスワード */
	private String password;
	
	/**
	 * パスワード暗号化
	 * @param encoder
	 */
	public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
}
