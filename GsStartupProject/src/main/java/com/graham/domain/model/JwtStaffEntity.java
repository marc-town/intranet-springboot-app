package com.graham.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "m_staff", 
	uniqueConstraints = { 
		@UniqueConstraint(columnNames = "login_id"),
		@UniqueConstraint(columnNames = "mail_address") 
	})
public class JwtStaffEntity {

	/** 社員ID */
	@Id
	@Column(name = "staff_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer staffId;
	
	/** メールアドレス */
	@Column(name = "mail_address")
	private String email;

	/** ログインID */
	@Column(name = "login_id")
	private String username;
	
	/** パスワード */
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "c_staff_role",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
	
	public JwtStaffEntity() {
	}
	
	public JwtStaffEntity(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
