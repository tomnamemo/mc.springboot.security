package com.mc.core.infra.module.account.validator;

import com.mc.core.infra.module.account.AccountRepository;
import com.mc.core.infra.module.account.request.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) target;
        if(accountRepository.existsById(signUpForm.email())){
            errors.rejectValue("email", "EMAIL", "이미 존재하는 이메일입니다.");
        }
    }
}
