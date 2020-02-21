package com.graham.interfaces.response;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.graham.exception.GrahamError;

import lombok.Setter;

/**
 * エラーレスポンスのマッピング用クラス(Json)
 */
@Setter
public class ErrorResponseForm {

	@JsonProperty("error")
	private GrahamError error;
	@JsonProperty("timestamp")
    private ZonedDateTime exceptionOccurrenceTime;
	
}
