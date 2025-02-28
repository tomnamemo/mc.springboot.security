package com.mc.core.infra.module.account.dto;

import com.mc.core.infra.module.account.domain.Account;
import com.mc.core.infra.module.account.domain.Role;

public record AccountDto(
        String email,
        String password,
        Role role
) {

    public static AccountDto from(Account account) {
        return new AccountDto(account.getEmail(), account.getPassword(), account.getRole());
    }


}
