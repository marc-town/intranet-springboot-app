package com.graham.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

/**
 * 例外発生時に出力す情報
 */
@Setter
public class GrahamError {

	/** HTTPステータス */
	@JsonProperty("status")
	private final int status;
	
	/** 独自エラーコード */
	@JsonProperty("code")
	private final String code;
	
	/** メッセージ */
	@JsonProperty("message")
	private final String message;
	
	public GrahamError(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
