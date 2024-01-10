package com.example.techit7.article.exception.image_exception;

import com.example.techit7.article.errormessage.ErrorMessage;
import com.example.techit7.article.exception.BaseExceptionType;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public enum ImageExceptionType implements BaseExceptionType {

	NOT_FOUND_IMAGE_ENTITY(HttpStatus.BAD_REQUEST, ErrorMessage.ENTITY_NOT_FOUND);

	ImageExceptionType(HttpStatus httpStatus, String errorMessage) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	private HttpStatus httpStatus;

	private String errorMessage;

}
