package com.mc.core.infra.response;

import com.fasterxml.jackson.annotation.JsonInclude;

// null 값을 포함하지 않도록 설정
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
        String code,        // 응답 코드
        String message,     // 응답 메시지
        T Data              // 응답 데이터
) {
    // 성공적인 응답 생성 메서드
    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(ResponseCode.OK.code(), ResponseCode.OK.message(), data);
    }

    // 내용 없음 응답 생성 메서드
    public static <T> ApiResponse<T> noContent(){
        return new ApiResponse<>(ResponseCode.OK.code(), ResponseCode.OK.message(), null);
    }

    // 오류 응답 생성 메서드 (데이터 없음)
    public static <T> ApiResponse<T> error(ResponseCode code){
        return new ApiResponse<>(code.code(), code.message(), null);
    }

    // 오류 응답 생성 메서드 (데이터 포함)
    public static <T> ApiResponse<T> error(ResponseCode code, T data){
        return new ApiResponse<>(code.code(), code.message(), data);
    }
}
