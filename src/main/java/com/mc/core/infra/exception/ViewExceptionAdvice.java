package com.mc.core.infra.exception;

import com.mc.core.infra.exception.exceptions.ViewException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 뷰 관련 예외 처리 클래스
@ControllerAdvice
@Order(1) // 이 어드바이스의 우선순위 설정
public class ViewExceptionAdvice {

    // 로거 인스턴스 생성
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    // @ExceptionHandler: ViewException 처리
    @ExceptionHandler(ViewException.class)
    public String viewExceptionHandler(ViewException e, Model model) {
        // 모델에 예외 메시지를 추가
        model.addAttribute("message", e.code().message());
        // 오류 페이지로 리다이렉트
        return "error/redirect";
    }
}
