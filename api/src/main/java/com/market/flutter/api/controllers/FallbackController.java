package com.market.flutter.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.dto.ApiResponse;

@RestController
public class FallbackController {

    @RequestMapping("**")
    public ResponseEntity<ApiResponse<Void>> fallback() {
        return new ResponseEntity<>(ApiResponse.<Void>builder()
                .success(false)
                .error("Not Found")
                .build(), HttpStatus.NOT_FOUND);
    }

}
