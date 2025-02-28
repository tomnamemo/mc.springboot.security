package com.mc.core.infra.module.account.domain;

import com.mc.core.infra.entity.BaseEntity;
import com.mc.core.infra.module.account.dto.AccountDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Account extends BaseEntity {

    @Id
    private String email;
    private String password;
    @Setter
    private Role role;

    public Account(String email,String password) {
        super();
        this.email = email;
        this.password = password;
    }

}
