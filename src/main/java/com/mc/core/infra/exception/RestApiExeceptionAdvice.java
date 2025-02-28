package com.mc.core.infra.exception;

import com.mc.core.infra.exception.exceptions.RestApiException;
import com.mc.core.infra.response.ApiResponse;
import com.mc.core.infra.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

// REST API 전역 예외 처리 클래스
@RestControllerAdvice
public class RestApiExeceptionAdvice {

    // 로거 인스턴스 생성
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // @ExceptionHandler: MethodArgumentNotValidException 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> validExceptionHandler(
            MethodArgumentNotValidException ex
    ){
        Map<String,String> errors = new LinkedHashMap<>();
        // 모든 오류를 맵에 추가
        ex.getAllErrors().forEach(e -> {
            errors.put(e.getCode(), e.getDefaultMessage());
        });

        // 잘못된 요청에 대한 응답 반환
        return  ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ResponseCode.BAD_REQUEST, errors));
    }

    // @ExceptionHandler: HttpRequestMethodNotSupportedException 처리
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> MethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException ex
    ){
        // 지원되지 않는 메서드에 대한 응답 반환
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ApiResponse.error(ResponseCode.BAD_REQUEST,
                        ex.getBody().getTitle()+ "\n" + ex.getBody().getDetail()));
    }

    // @ExceptionHandler: RestApiException 처리
    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ApiResponse<Void>> restApiExceptionHandler(
            RestApiException ex
    ){
        // RestApiException에 대한 응답 반환
        return ResponseEntity
                .status(ex.code().status())
                .body(ApiResponse.error((ex.code())));
    }

    // @ExceptionHandler: RuntimeException 처리
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> globalHandler(RuntimeException ex){
        log.error(ex.getMessage()); // 예외 메시지 로깅
        // 내부 서버 오류에 대한 응답 반환
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error(ResponseCode.UNKNOWN, ex.getMessage()));
    }

}
