package com.example.booktree.global.exception;

import lombok.Getter;

@Getter
public enum JwtExceptionCode {
    UNKNOWN_ERROR("UNKNOWN_ERROR", "알 수 없는 오류"),
    NOT_FOUND_TOKEN("NOT_FOUND_TOKEN", "Headers에 토큰 형식의 값 찾을 수 없음"),
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 토큰"),
    EXPIRED_TOKEN("EXPIRED_TOKEN", "기간이 만료된 토큰"),
    UNSUPPORTED_TOKEN("UNSUPPORTED_TOKEN", "지원하지 않는 토큰");


    private final String code;  //값이 변하지 않도록 final
    private final String message;

    JwtExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String toString() {   //예외 메시지를 출력할 때 더 유용한 정보 제공.
        return String.format("[%s] %s", code, message);
    }

}
