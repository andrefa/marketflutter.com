package com.market.flutter.api.services.binance;

import java.util.LinkedHashMap;
import java.util.function.BiFunction;

import org.springframework.stereotype.Service;

import com.binance.connector.client.SpotClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BinanceClientService {

    private final SpotClient unauthenticatedBinanceClient;

    private final BiFunction<String, String, SpotClient> authenticatedBinanceClientFactory;

    public void test() {
        String result = unauthenticatedBinanceClient.createMarket().exchangeInfo(new LinkedHashMap<>());
        System.out.println(result);

        LinkedHashMap<String,Object> parameters = new LinkedHashMap<String,Object>();

        parameters.put("symbol","BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.01);
        parameters.put("price", 9500);

        SpotClient client = authenticatedBinanceClientFactory.apply("oxob8Fwc8Zg83hKw4VremmX4ZC5czDwifjoDicVGtw96OQuuRYRO6hWP57lDn6yP", "hXIu6KRBZOKKHKglsHFjhshlsudEbOLF4P8q9FCO1aTOxqGRni0YV5aXnrvOK8gU");
        String result2 = client.createTrade().testNewOrder(parameters);

        System.out.println(result2);
    }
    
}
