package com.market.flutter.api.services.communications;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.flutter.api.models.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    @Value("${notification.url}")
    private final String notificationUrl;

    @Qualifier("ntfyClient")
    private final OkHttpClient okHttp;

    private final ObjectMapper objectMapper;

    public void notifyUser(User user, String message) {
        if (shouldntNotify(user, message)) {
            return;
        }

        try {
            String url = String.format("%s%s", notificationUrl, user.getUserConfig().getNtfyTopicName());
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(message, MediaType.parse("text/plain")))
                    .build();

            log.debug("Posting '{}' message to topic '{}'", message, user.getUserConfig().getNtfyTopicName());

            try (Response response = okHttp.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    log.debug("Failed to notify '{}' message to topic '{}'. Status code {}.",
                            message,
                            user.getUserConfig().getNtfyTopicName(),
                            response.code());
                }

                NtfyResponse ntfyResponse = objectMapper.readValue(response.body().byteStream(), NtfyResponse.class);

                log.debug("Response received '{}' for url '{}'", ntfyResponse, url);
            }
        } catch (IOException e) {
            log.warn("Excpetion to notify '{}' message to topic '{}. Error '{}'.",
                    message,
                    user.getUserConfig().getNtfyTopicName(),
                    e.getMessage());
        }
    }

    private boolean shouldntNotify(User user, String message) {
        return user == null
                || isBlank(user.getUserConfig().getNtfyTopicName())
                || isBlank(message);
    }

}


record NtfyResponse(String id, long time, String event, String topic, String message) {
}
