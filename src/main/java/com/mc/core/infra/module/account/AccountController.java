package com.mc.core.infra.module.account;


import com.mc.core.infra.module.account.domain.Role;
import com.mc.core.infra.module.account.request.SignUpForm;
import com.mc.core.infra.module.account.validator.SignUpFormValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {


    private final AccountService accountService;
    private final SignUpFormValidator signUpFormValidator;

    @InitBinder(value = "signUpForm")
    protected void signUpFormBiner(WebDataBinder binder) {
        binder.setValidator(signUpFormValidator);
    }

    @GetMapping("signup")
    public void signup(SignUpForm signUpForm) {}


    @GetMapping("login")
    public void login() {}


    @PostMapping("/signup")
    public String add(
            @Valid
            SignUpForm form,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "account/signup";
        }
        accountService.add(form, Role.USER);
        return "/index";
    }

    @GetMapping("dashboard")
    public void dashboard(Authentication authentication) {
        log.info("{}", authentication);
    }

}
