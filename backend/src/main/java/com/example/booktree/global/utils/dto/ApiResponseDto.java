package com.example.booktree.global.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponseDto<T> {
    private T data;
    private String message;
}
