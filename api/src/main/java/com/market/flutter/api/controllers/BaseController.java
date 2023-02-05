package com.market.flutter.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.market.flutter.api.models.dto.ApiResponse;

@RequestMapping("/api/v1")
public abstract class BaseController {

    protected <T> ApiResponse<T> success(T data) {
        return ApiResponse
                .<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    protected <Void> ApiResponse<Void> failure(String error) {
        return ApiResponse
                .<Void>builder()
                .success(true)
                .error(error)
                .build();
    }

}
