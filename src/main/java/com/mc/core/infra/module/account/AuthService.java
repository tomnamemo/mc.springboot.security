package com.mc.core.infra.module.account;

import com.mc.core.infra.module.account.domain.Account;
import com.mc.core.infra.module.account.domain.Principal;
import com.mc.core.infra.module.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new Principal(AccountDto.from(account));
    }
}
