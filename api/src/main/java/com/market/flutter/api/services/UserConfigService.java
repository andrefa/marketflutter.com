package com.market.flutter.api.services;

import com.market.flutter.api.models.domain.User;
import com.market.flutter.api.models.domain.UserConfig;
import com.market.flutter.api.repositories.UserConfigRepository;
import com.market.flutter.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConfigService {

    private final UserRepository userRepository;
    private final UserConfigRepository userConfigRepository;

    public void updateUserBinanceConfig(String email, String binanceApiKey, String binanceSecretKey) {
        UserConfig userConfig = fetchUserConfig(email);
        userConfig.setBinanceApiKey(binanceApiKey);
        userConfig.setBinanceSecretKey(binanceSecretKey);

        userConfigRepository.save(userConfig);
    }

    public void updateUserNotificationTopic(String email, String topic) {
        UserConfig userConfig = fetchUserConfig(email);
        userConfig.setNtfyTopicName(topic);

        userConfigRepository.save(userConfig);
    }

    private UserConfig fetchUserConfig(String email) {
        User user = userRepository.findByEmail(email);
        return user.getUserConfig();
    }

}
