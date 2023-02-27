package com.market.flutter.api.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.market.flutter.api.models.dto.ApiResponse;

@RequestMapping("/api/v1")
public abstract class BaseController {

    protected ApiResponse<Void> success() {
        return new ApiResponse<>(true, null, null);
    }

    protected <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data);
    }

    protected ApiResponse<Void> failure(String error) {
        return new ApiResponse<>(false, error, null);
    }

    protected String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
