package com.market.flutter.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.services.binance.BinanceClientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AssetController extends BaseController {

    private BinanceClientService binanceClientService;

    @GetMapping("assets/list")
    public List<Object> getAllAssets() {
        binanceClientService.test();
        return List.of();
    }

    // @GetMapping("/assets/{id}")
    // public Asset getAsset(@PathVariable String id) {
    //     return assetService.getAsset(id);
    // }

    // @PostMapping("/assets")
    // public void addAsset(@RequestBody Asset asset) {
    //     assetService.addAsset(asset);
    // }

    // @PutMapping("/assets/{id}")
    // public void updateAsset(@RequestBody Asset asset, @PathVariable String id) {
    //     assetService.updateAsset(id, asset);
    // }

    // @DeleteMapping("/assets/{id}")
    // public void deleteAsset(@PathVariable String id) {
    //     assetService.deleteAsset(id);
    // }

}
