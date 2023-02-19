package com.market.flutter.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.AvailableAsset;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AssetController extends BaseController {

    @GetMapping("assets/available")
    public ApiResponse<List<AvailableAsset>> getAllAssets() {
        return success(List.of());
    }

}
