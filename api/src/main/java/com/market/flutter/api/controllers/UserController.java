package com.market.flutter.api.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {
    
    @GetMapping("not-the-user/me")
    public Object getAllAssets() {
        return Map.of("user", Map.of("name", "John Doe", "email", "email@domain.com"));
    }

    

}
