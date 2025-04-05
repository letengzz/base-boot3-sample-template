package com.hjc.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public record ResponseEntity<T>(int code, String message, T data) {
    //请求成功
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(20000, "请求成功", data);
    }

    //请求成功 无data
    public static <T> ResponseEntity<T> success() {
        return ResponseEntity.success(null);
    }

    //请求失败
    public static <T> ResponseEntity<T> failure(int code, String message) {
        return new ResponseEntity<>(code, message, null);
    }

    public static <T> ResponseEntity<T> failure(ErrorCode errorCode, String message) {
        return new ResponseEntity<>(errorCode.getCode(), message, null);
    }
    //转化为JSON字符串
    public String asJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
