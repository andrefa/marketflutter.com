package com.market.flutter.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;

@Configuration
@RequiredArgsConstructor
public class OkHttpConfig {

    @Bean
    public OkHttpClient ntfyClient() {
        return new OkHttpClient.Builder().build();
    }

}