package com.market.flutter.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.dto.ApiResponse;

@RestController
public class HealthcheckController extends BaseController {

    @GetMapping("/healthcheck")
    public ApiResponse<Void> healthcheck() {
        return success();
    }

}
