package com.market.flutter.api.models.dto;

public record ApiResponse<T> (boolean success,
        String error,
        T data) {
}
