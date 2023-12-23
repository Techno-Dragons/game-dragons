package com.example.techit7.article.exception;


import com.example.techit7.global.response.GlobalResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> methodValidException(MethodArgumentNotValidException e) {

        GlobalResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<GlobalResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalResponse> incorrectUserAccess(EntityNotFoundException e) {
        GlobalResponse errorResponse = GlobalResponse.of("404", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GlobalResponse> userPermissionError(IllegalArgumentException e) {
        GlobalResponse errorResponse = GlobalResponse.of("403", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    private GlobalResponse makeErrorResponse(BindingResult bindingResult) {
        String code = "";
        String message = "";

        if (bindingResult.hasErrors()) {
            message = bindingResult.getFieldError().getDefaultMessage();
            code = "Empty";
        }

        return GlobalResponse.of(code, message);
    }

}
