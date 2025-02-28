package com.mc.core.infra.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 기본 엔티티 클래스를 정의하는 어노테이션
@MappedSuperclass
// 이 클래스의 생명주기를 관리하는 AuditingEntityListener를 설정
@EntityListeners(AuditingEntityListener.class)
public class CommonTimeEntity {

    // 생성 날짜를 자동으로 저장하는 필드
    @CreatedDate
    protected LocalDateTime createdAt = LocalDateTime.now();

    // 마지막 수정 날짜를 자동으로 저장하는 필드
    @LastModifiedDate
    protected LocalDateTime modifiedAt = LocalDateTime.now();
}
