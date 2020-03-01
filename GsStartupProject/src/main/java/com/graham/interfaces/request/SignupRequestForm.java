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
public class SignupRequestForm {
	
	/** 名前 */
	private String Name;
	
	/** なまえ */
	private String NameKana;

	/** ログインID */
    @Size(min = 8, max = 20)
    @NotBlank(message = "ログインIDは必須です")
    private String loginId;
 
	/** メールアドレス */
    @Size(max = 100)
    @Email
    private String email;
    
    /** 電話番号 */
    private String telephoneNumber;
    
    /** パスワード */
    @Size(min = 8, max = 40)
    @NotBlank(message = "パスワードは必須です")
    private String password;
    
    /** 権限 */
    private Set<String> role;
    
    
    /**
	 * パスワード暗号化
	 * @param encoder
	 */
	public void encrypt(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
}
