package com.core.service;
import com.core.entity.system.UserAccount;
import com.core.form.UserLoginForm;
import com.core.view.LoginView;

public interface UserAccountService {

    void createUserAccount(UserAccount userAccount);

    void updateUserAccount(UserAccount userAccount);

    LoginView userLogin(UserLoginForm userLoginForm);

    UserAccount getById(Integer userId);
}
