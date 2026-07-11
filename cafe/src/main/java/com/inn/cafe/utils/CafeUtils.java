package com.inn.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class CafeUtils {

    private CafeUtils() {
    }

    public static ResponseEntity<Map<String, String>> getResponseEntity(String message,
                                                                        HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}