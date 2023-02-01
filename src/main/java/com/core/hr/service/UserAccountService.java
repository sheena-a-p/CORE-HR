package com.core.hr.service;
import com.core.hr.entity.System.UserAccount;
import com.core.hr.exception.BadRequestException;
import com.core.hr.form.UserLoginForm;
import com.core.hr.view.LoginView;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface UserAccountService {

    void createUserAccount(UserAccount userAccount);

    void updateUserAccount(UserAccount userAccount);

    LoginView userLogin(UserLoginForm userLoginForm);

    UserAccount getById(Integer userId);

    LoginView login(String token,String data) throws BadRequestException, IOException, GeneralSecurityException;

}
