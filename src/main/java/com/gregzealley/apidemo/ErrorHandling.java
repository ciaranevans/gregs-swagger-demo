package com.gregzealley.apidemo;

import com.gregzealley.apidemo.models.DefaultErrorResponse;
import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public DefaultErrorResponse handleError(final Throwable throwable) {
        return new DefaultErrorResponse("Something went wrong!");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DefaultErrorResponse handleError(final IndexOutOfBoundsException ex) {
        return new DefaultErrorResponse("Thing not found");
    }

}
