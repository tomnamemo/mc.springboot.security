package com.mc.core.infra.response;

import org.springframework.http.HttpStatus;

// 응답 코드 열거형
public enum ResponseCode {

    OK("0000", HttpStatus.OK, "정상적으로 완료되었습니다."),
    UNAUTHORIZED("2000", HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    BAD_REQUEST("4000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    SERVER_ERROR("5000", HttpStatus.INTERNAL_SERVER_ERROR, "서버 장애가 발생했습니다."),
    UNKNOWN("9999", HttpStatus.I_AM_A_TEAPOT, "문제가 발생했습니다."),
    FILE_ERROR("8000", HttpStatus.INTERNAL_SERVER_ERROR, "파일 등록 중 문제가 발생했습니다.");

    private final String code;     // 응답 코드
    private final HttpStatus status; // HTTP 상태
    private final String message;   // 응답 메시지

    // 생성자
    private ResponseCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    // 응답 코드 반환
    public String code() {
        return code;
    }

    // HTTP 상태 반환
    public HttpStatus status() {
        return status;
    }

    // 응답 메시지 반환
    public String message() {
        return message;
    }
}
