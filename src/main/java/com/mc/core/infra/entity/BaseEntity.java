package com.mc.core.infra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 기본 엔티티 클래스를 정의하는 어노테이션
@MappedSuperclass
// 이 클래스의 생명주기를 관리하는 AuditingEntityListener를 설정
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends CommonTimeEntity {

    // 활성화 상태
    protected Boolean activated = true;

    // 비활성화 메서드
    public void unActivate() {
        activated = false; // 비활성화
    }
}
