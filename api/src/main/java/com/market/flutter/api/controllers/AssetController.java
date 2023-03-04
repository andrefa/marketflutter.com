package com.market.flutter.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.UserAsset;
import com.market.flutter.api.services.AssetService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AssetController extends BaseController {

    private final AssetService assetService;

    @GetMapping("assets/available")
    public ApiResponse<List<UserAsset>> listUserAssets() {
        return success(assetService.listAssetsPerUser(getLoggedInUserName()));
    }

    @PutMapping("assets/reset-base-price")
    public ApiResponse<Void> resetUserAssetsBasePrice() {
        assetService.resetUserAssetsBasePrice(getLoggedInUserName());
        return success();
    }

}
