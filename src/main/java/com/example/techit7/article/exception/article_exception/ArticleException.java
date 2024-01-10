package com.example.techit7.article.exception.article_exception;

import com.example.techit7.article.exception.BaseException;
import com.example.techit7.article.exception.BaseExceptionType;

public class ArticleException extends BaseException {

	private final ArticleExceptionType exceptionType;

	public ArticleException(final ArticleExceptionType exceptionType) {
		super(exceptionType.getErrorMessage());
		this.exceptionType = exceptionType;
	}

	@Override
	public BaseExceptionType exceptionType() {
		return exceptionType;
	}
}
