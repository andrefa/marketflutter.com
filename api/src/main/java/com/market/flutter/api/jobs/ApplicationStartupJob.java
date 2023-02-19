package com.market.flutter.api.jobs;

import java.math.BigDecimal;

import org.knowm.xchange.binance.dto.marketdata.BinanceTicker24h;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.market.flutter.api.repositories.AssetConfigRepository;
import com.market.flutter.api.repositories.AssetRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApplicationStartupJob {

    private final AssetRepository assetRepository;
    private final AssetConfigRepository assetConfigRepository;

    private final BinanceMarketDataService marketDataService;

    @PostConstruct
    public void run() {
        updateAssetsBasePrice();
    }

    private void updateAssetsBasePrice() {
        log.info("Starting update of assets base price...");
        assetRepository.findAll().forEach(asset -> {
            try {
                String baseSymbol = asset.getCoin().getCryptoCode();
                String counterSymbol = asset.getAssetConfig().getExchangeCoin().getCryptoCode();
                log.info("Processing base price for asset {} with pair {}-{}", asset.getId(), baseSymbol, counterSymbol);

                CurrencyPair currencyPair = new CurrencyPair(baseSymbol, counterSymbol);
                BinanceTicker24h ticker = marketDataService.ticker24hAllProducts(currencyPair);
                BigDecimal lastPrice = ticker.getLastPrice();

                asset.getAssetConfig().setBasePrice(lastPrice);
                asset.getAssetConfig().setLastSeenPrice(lastPrice);

                assetConfigRepository.save(asset.getAssetConfig());
                log.info("Updated base price for asset {} with pair {}-{} to {}", asset.getId(), baseSymbol, counterSymbol, lastPrice);
            } catch (Exception e) {
                log.warn("Error updating base price for asset {}. Error: {}", asset.getId(), e);
            }
        });
        log.info("Finished update of assets base price.");
    }

}
