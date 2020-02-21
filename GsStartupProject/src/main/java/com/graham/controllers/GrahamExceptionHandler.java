package com.graham.controllers;

import java.time.ZonedDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.graham.exception.GrahamException;
import com.graham.interfaces.response.ErrorResponseForm;

/**
 * throwされたExceptionをハンドルするクラス
 */
@RestControllerAdvice
public class GrahamExceptionHandler extends ResponseEntityExceptionHandler {

	// throw された GrahamException を捕捉
    @ExceptionHandler(GrahamException.class)
    public ResponseEntity<Object> handleGrahamException(GrahamException exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        return super.handleExceptionInternal(exception,
                createErrorResponseForm(exception),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    // レスポンスのボディ部を作成
    private ErrorResponseForm createErrorResponseForm(GrahamException exception) {

        ErrorResponseForm ErrorResponseForm = new ErrorResponseForm();

        ErrorResponseForm.setError(exception.getGrahamError());
        ErrorResponseForm.setExceptionOccurrenceTime(ZonedDateTime.now());

        return ErrorResponseForm;
    }
}
