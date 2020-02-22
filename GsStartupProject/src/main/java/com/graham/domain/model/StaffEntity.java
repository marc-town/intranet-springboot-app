package com.graham.domain.model;

import javax.persistence.*;

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
	private String loginId;
	
	/** パスワード */
	@Column(name = "password")
	private String password;
}
