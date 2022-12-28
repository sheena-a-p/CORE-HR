package com.core.service;
import com.core.entity.system.UserAccount;
import com.core.form.UserLoginForm;

public interface UserAccountService {

    public  void  userLogin(UserLoginForm userLoginForm);

    public UserAccount getById(Integer userId);
}
