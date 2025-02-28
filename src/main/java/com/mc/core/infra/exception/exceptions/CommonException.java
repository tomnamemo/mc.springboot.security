package com.mc.core.infra.exception.exceptions;

import com.mc.core.infra.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// catched: try-catch를 강제하는 예외
// uncaught: try-catch를 강제하지 않음
public class CommonException extends RuntimeException {
    // 로거 인스턴스 생성
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    // 응답 코드 필드
    private ResponseCode code;

    // 응답 코드만 받는 생성자
    public CommonException(ResponseCode code) {
        this.code = code;
    }

    // 응답 코드와 예외를 받는 생성자
    public CommonException(ResponseCode code, Exception e) {
        this.code = code;
        log.error(e.getMessage(), e); // 예외 메시지와 스택 트레이스를 로깅
    }

    // 응답 코드를 반환하는 메서드
    public ResponseCode code() {
        return code;
    }
}
