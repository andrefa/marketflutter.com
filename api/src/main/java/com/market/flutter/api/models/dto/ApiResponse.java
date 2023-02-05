package com.market.flutter.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private boolean success;
    private String error;
    private T data;

}
