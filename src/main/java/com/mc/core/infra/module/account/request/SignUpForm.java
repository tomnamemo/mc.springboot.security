package com.mc.core.infra.module.account.request;

import com.mc.core.infra.module.account.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.password.PasswordEncoder;
public record SignUpForm(
        @Email
        String email,
       // @Pattern(regexp = "^(?!.*[ㄱ-힣])(?=.*\\W)(?=.*\\d)(?=.*[a-zA-Z])(?=.{8,})"
        //        , message = "비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.")
        String password
) {
    public Account toEntity(PasswordEncoder passwordEncoder) {
        return new Account(email, passwordEncoder.encode(password));
    }
}