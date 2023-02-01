package com.core.service;
import com.core.entity.System.UserAccount;
import com.core.exception.BadRequestException;
import com.core.form.UserLoginForm;
import com.core.view.LoginView;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface UserAccountService {

    void createUserAccount(UserAccount userAccount);

    void updateUserAccount(UserAccount userAccount);

    LoginView userLogin(UserLoginForm userLoginForm);

    UserAccount getById(Integer userId);

    LoginView login(String token,String data) throws BadRequestException, IOException, GeneralSecurityException;

}
