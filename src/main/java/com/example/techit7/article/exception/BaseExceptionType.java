package com.example.techit7.article.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
	HttpStatus getHttpStatus();

	String getErrorMessage();
}
