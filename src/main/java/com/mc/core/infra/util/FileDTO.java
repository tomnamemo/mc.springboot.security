package com.mc.core.infra.util;

import lombok.Getter;
import lombok.Setter;

// 파일 정보를 담는 DTO 클래스
@Getter
@Setter
public class FileDTO {
    private String originFileName;  // 원본 파일 이름
    private String renameFileName;   // 변경된 파일 이름
    private String savePath;         // 저장 경로
}
