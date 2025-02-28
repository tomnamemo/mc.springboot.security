package com.mc.core.infra.module.account.domain;

import com.mc.core.infra.module.account.dto.AccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Principal extends User {

    @Getter
    private final AccountDto accountDto;

    public Principal(AccountDto accountDto) {
        super(accountDto.email(),accountDto.password(), List.of(new SimpleGrantedAuthority(accountDto.role().role())));
        this.accountDto = accountDto;
    }

}
