package com.graham.interfaces.request;

import lombok.Data;

@Data
public class StaffRequestForm {

	/** 社員ID */
	private Integer staffId;
	
	/** メールアドレス */
	private String mailAddress;

	/** ログインID */
	private String loginId;
	
	/** パスワード */
	private String password;
}