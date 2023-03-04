package com.market.flutter.api.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.UserData;

@RestController
public class UserController extends BaseController {

    @GetMapping("user/me")
    public ApiResponse<UserData> getAllAssets() {
        // TODO implement
        return success(new UserData("John", "Doe", getLoggedInUserName(), null));
    }

}
