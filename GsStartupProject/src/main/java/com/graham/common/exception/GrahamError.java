package com.graham.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@Setter
public class GrahamError {

	@JsonProperty("status")
	private final int status;
	@JsonProperty("code")
	private final String code;
	@JsonProperty("message")
	private final String message;
	
	public GrahamError(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
