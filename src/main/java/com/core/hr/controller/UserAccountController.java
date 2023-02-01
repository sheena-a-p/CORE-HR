package com.core.hr.controller;
import com.core.hr.exception.BadRequestException;
import com.core.hr.form.UserLoginForm;
import com.core.hr.service.UserAccountService;
import com.core.hr.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* Controller to manage User Account
 * Author Sheena AP
 */
@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginView userAccountLogin(@Valid @RequestBody UserLoginForm form) {
        return userAccountService.userLogin(form);
    }

    @PostMapping("/login/gmail")
    public LoginView gmailLogin(@RequestParam(value = "token") String token, @Valid @RequestBody String data) throws IOException, GeneralSecurityException {
        if (data.isEmpty()) {
            throw new BadRequestException("String is empty");
        }
        return userAccountService.login(token, data);
    }
}
