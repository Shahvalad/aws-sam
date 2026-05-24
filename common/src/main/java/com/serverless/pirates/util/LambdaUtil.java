package com.serverless.pirates.util;

import com.google.gson.Gson;
import com.serverless.pirates.model.Pirate;
import software.amazon.awssdk.services.dynamodb.endpoints.internal.Value;

import java.util.Map;

public final class LambdaUtil {

    private static final Gson gson = new Gson();

    public static String buildFailedResponse(String message) {
        return gson.toJson(Map.of(
                "status", "error",
                "message", message
        ));
    }

    public static String buildSuccessfulResponse(Object data, String message) {
        return gson.toJson(Map.of(
                "status", "success",
                "data", data,
                "message", message
        ));
    }

    public static Pirate parseRequestBodyToPirate(String body) {
        return gson.fromJson(body, Pirate.class);
    }

    public static String validateField(String fieldName, Object field) {
        if (field instanceof String str && str.isBlank()) {
            return String.format("%s should not be empty.", fieldName);
        }
        if (field instanceof Integer integer && integer <= 0) {
            return String.format("%s must be greater than zero", fieldName);
        }
        return null;
    }

}
