package com.market.flutter.api.services.binance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.Function;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.service.trade.TradeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.market.flutter.api.models.domain.Coin;
import com.market.flutter.api.models.domain.User;
import com.market.flutter.api.models.domain.UserConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceClientService {

    @Qualifier("binanceTradeFactory")
    private final Function<UserConfig, TradeService> binanceTradeFactory;

    public void executeTrade(User user, Coin coinIn, Coin coinOut, BigDecimal amount) {
        try {
            CurrencyPair currencyPair = new CurrencyPair(String.format("%s%s", coinIn.getCryptoCode(), coinOut.getCryptoCode()));
            MarketOrder marketOrder = new MarketOrder(Order.OrderType.BID, amount, currencyPair);

            String orderId = binanceTradeFactory.apply(user.getUserConfig())
                    .placeMarketOrder(marketOrder);
            log.info("Order placed successfully with ID {}", orderId);
        } catch (IOException e) {
            log.error("Failed to place order", e);
        }
    }

}
