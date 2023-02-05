package com.market.flutter.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.api.binance.ExchangeInfo;
import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.AvailableAsset;
import com.market.flutter.api.services.binance.BinanceClientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AssetController extends BaseController {

    private BinanceClientService binanceClientService;

    @GetMapping("assets/available")
    public ApiResponse<List<AvailableAsset>> getAllAssets() {
        ExchangeInfo info = binanceClientService.fetchExchangeInfo();

        List<AvailableAsset> availableAssets = info.symbols().stream().map(s -> AvailableAsset.builder()
                .symbol(s.symbol())
                .status(s.status())
                .baseAsset(s.baseAsset())
                .quoteAsset(s.quoteAsset())
                .build())
                .toList();

        return success(availableAssets);
    }

}
