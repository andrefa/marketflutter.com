package com.market.flutter.api.controllers;

import com.market.flutter.api.models.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    protected ApiResponse<ErrorResponse> formatErrorResponse(Exception ex, WebRequest request) {
        log.error("Error while processing request [" + request + "].", ex);
        return new ApiResponse<>(false, ex.getLocalizedMessage(), new ErrorResponse(ex.getStackTrace()));
    }

}

record ErrorResponse(StackTraceElement[] stackTrace) {
}
