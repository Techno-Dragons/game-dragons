package com.example.techit7.article.exception.image_exception;

import com.example.techit7.article.exception.BaseException;
import com.example.techit7.article.exception.BaseExceptionType;

public class ImageException extends BaseException {

    private final ImageExceptionType exceptionType;

    ImageException(String message, ImageExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public BaseExceptionType exceptionType() {
        return exceptionType;
    }
}
