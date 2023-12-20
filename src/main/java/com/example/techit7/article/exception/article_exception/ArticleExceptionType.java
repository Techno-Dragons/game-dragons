package com.example.techit7.article.exception.article_exception;

import com.example.techit7.article.errormessage.ErrorMessage;
import com.example.techit7.article.exception.BaseExceptionType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ArticleExceptionType implements BaseExceptionType {
    NOT_FOUND_ARTICLE_ENTITY(
            HttpStatus.BAD_REQUEST,
            ErrorMessage.ENTITY_NOT_FOUND
    );

    private final HttpStatus httpStatus;
    private final String errorMessage;

    ArticleExceptionType(final HttpStatus httpStatus, final String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
