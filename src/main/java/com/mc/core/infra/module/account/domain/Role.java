package com.mc.core.infra.module.account.domain;

public enum Role {
    //ROLE prefix는 규칙
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    ;

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
