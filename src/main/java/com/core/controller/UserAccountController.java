package com.core.controller;

import com.core.form.UserLoginForm;
import com.core.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/login")
    public void userAccountLogin(@Valid @RequestBody UserLoginForm form) {
        userAccountService.userLogin(form);
    }
}
