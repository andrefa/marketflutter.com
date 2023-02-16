package com.market.flutter.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.repositories.UserRepository;
import com.market.flutter.api.services.communications.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LetsMakeItWorkController extends BaseController {

    private UserRepository userRepository;
    private NotificationService notificationService;

    @GetMapping("test/notify")
    public List<Object> getAllAssets() {
        userRepository.findAll().forEach(user -> {
            notificationService.notifyUser(user, "test");
        });

        return List.of();
    }

}
