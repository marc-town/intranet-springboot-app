package com.graham.interfaces.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

/**
 * 社員登録情報
 */
@Data
public class JwtRequestForm {

	/** ログインID */
    @Size(min = 8, max = 20)
    private String loginId;
 
	/** メールアドレス */
    @Size(max = 100)
    @Email
    private String email;
    
    /** 権限 */
    private Set<String> role;
    
    /** パスワード */
    @Size(min = 8, max = 40)
    private String password;
    
    /**
	 * パスワード暗号化
	 * @param encoder
	 */
	public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
}