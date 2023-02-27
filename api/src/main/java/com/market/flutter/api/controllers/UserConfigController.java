package com.market.flutter.api.controllers;

import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.UpdateBinanceConfigRequest;
import com.market.flutter.api.models.dto.UpdateNotificationTopicRequest;
import com.market.flutter.api.services.UserConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserConfigController extends BaseController {

    private final UserConfigService userConfigService;

    @PutMapping("config/binance")
    public ApiResponse<Void> updateBinanceConfig(@RequestBody UpdateBinanceConfigRequest configRequest) {
        userConfigService.updateUserBinanceConfig(getLoggedInUserName(), configRequest.binanceApiKey(), configRequest.binanceSecretKey());
        return success();
    }

    @PutMapping("config/notification")
    public ApiResponse<Void> updateNotificationTopic(@RequestBody UpdateNotificationTopicRequest configRequest) {
        userConfigService.updateUserNotificationTopic(getLoggedInUserName(), configRequest.topic());
        return success();
    }

}
