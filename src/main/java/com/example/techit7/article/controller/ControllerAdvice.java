package com.example.techit7.article.controller;

import com.example.techit7.article.errormessage.ErrorMessage;
import com.example.techit7.article.exception.article_exception.ArticleException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(ArticleException.class)
	public ResponseEntity<ExceptionResponse> notFoundArticleEntity(ArticleException articleException) {
		return new ResponseEntity<>(new ExceptionResponse(ErrorMessage.ENTITY_NOT_FOUND), HttpStatus.BAD_REQUEST);
	}

	static class ExceptionResponse {

		private final String message;

		private ExceptionResponse(final String message) {
			this.message = message;
		}

	}
}
