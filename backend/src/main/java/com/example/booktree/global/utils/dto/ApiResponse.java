package com.example.booktree.global.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private int statusCode;

    private String message;

    private T data;

    // data 없이 statusCode와 message만 사용하는 경우를 위한 메서드
    public static ApiResponse<Void> of(int statusCode, String message) {
        return new ApiResponse<>(LocalDateTime.now(), statusCode, message, null);
    }

    // data가 있는 경우를 위한 메서드
    public static <T> ApiResponse<T> of(int statusCode, String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), statusCode, message, data);
    }

}
