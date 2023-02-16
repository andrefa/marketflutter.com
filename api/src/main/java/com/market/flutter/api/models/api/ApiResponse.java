package com.market.flutter.api.models.api;

public record ApiResponse<T>(boolean success,
String error,
T data) {}
