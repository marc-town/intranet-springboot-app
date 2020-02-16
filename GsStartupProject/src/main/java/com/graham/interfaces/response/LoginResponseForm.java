package com.graham.interfaces.response;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.graham.domain.model.StaffEntity;

import lombok.Getter;

@Getter
public class LoginResponseForm extends User {

	private StaffEntity staff;
	
	public LoginResponseForm(StaffEntity staff) {
		super(staff.getLoginId(), staff.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.staff = staff;
	}
}
