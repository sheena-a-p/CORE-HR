package com.core.controller;
import com.core.entity.system.UserAccount;
import com.core.form.UserLoginForm;
import com.core.service.UserAccountService;
import com.core.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public void saveUserAccount(@Valid @RequestBody UserAccount userAccount){
        try {
            if (userAccount.getUserId() == null){
                userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
                userAccountService.createUserAccount(userAccount);
            }else {
                userAccountService.updateUserAccount(userAccount);
            }
        }catch (Exception e){
            throw  new RuntimeException("Saving user details failed !",e);
        }
    }

    @PostMapping("/login")
    public LoginView userAccountLogin(@Valid @RequestBody UserLoginForm form) {
        return userAccountService.userLogin(form);
    }
}
