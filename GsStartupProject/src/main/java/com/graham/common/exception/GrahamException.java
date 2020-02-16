package com.graham.common.exception;

import lombok.Getter;

@Getter
public class GrahamException extends RuntimeException {
	
	private GrahamError grahamError;
	
	public GrahamException(GrahamError grahamError) {
		this.grahamError = grahamError;
	}

}
