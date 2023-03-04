package com.market.flutter.api.controllers;

import com.market.flutter.api.models.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController extends BaseController {

    @GetMapping("/healthcheck")
    public ApiResponse<Void> healthcheck() {
        return success();
    }

}
