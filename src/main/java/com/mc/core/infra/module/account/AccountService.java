package com.mc.core.infra.module.account;


import com.mc.core.infra.module.account.domain.Account;
import com.mc.core.infra.module.account.domain.Role;
import com.mc.core.infra.module.account.request.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void add(SignUpForm form, Role role){
        String password = passwordEncoder.encode(form.password());
        Account account = form.toEntity(passwordEncoder);
        account.setRole(role);
        accountRepository.save(account);
    }
}
