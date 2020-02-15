package com.graham.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Table(name = "m_staff")
@Data
@Entity
public class StaffEntity {
	
	/** 社員ID */
	@Id
	@Column(name = "staff_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffId;
	
	/** メールアドレス */
	@Column(name = "mail_address")
	private String mailAddress;

	/** ログインID */
	@Column(name = "login_id")
	@NotNull(message="Login IDは必須です。")
	@Size(min = 8, max = 20)
	private String loginId;
	
	/** パスワード */
	@Column(name = "password")
	@NotNull(message="パスワードは必須です。")
	@Size(min = 8, max = 50)
	private String password;
}
