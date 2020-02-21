package com.graham.exception;

import lombok.Getter;

/**
 * Graham例外
 */
@Getter
public class GrahamException extends RuntimeException {
	
	private GrahamError grahamError;
	
	public GrahamException(GrahamError grahamError) {
		this.grahamError = grahamError;
	}

}
