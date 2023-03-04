package com.market.flutter.api.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.listeners.BinancePricesListener;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobsController extends BaseController {

    private final BinancePricesListener binancePricesListener;

    @PutMapping("jobs/binance-prices-listener/start")
    public void startBinancePricesListener() {
        // restart the listener
        binancePricesListener.stop();
        binancePricesListener.start();
    }

    @PutMapping("jobs/binance-prices-listener/stop")
    public void stopBinancePricesListener() {
        binancePricesListener.stop();
    }

}
