package com.graham.interfaces.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class JwtResponseForm {

	/** トークン */
	@JsonProperty("token")
	private String token;
	
	/** ログインID */
	@JsonProperty("login_id")
	private String loginId;
	
	/** 権限 */
	@JsonProperty("role")
	private List<String> role;
	
	public JwtResponseForm(String token, String loginId, List<String> roles) {
		this.token = token;
		this.loginId = loginId;
		this.role = roles;
	}
}
