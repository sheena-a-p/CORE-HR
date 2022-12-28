package com.core.serviceImpl;
import com.core.form.UserLoginForm;
import com.core.service.CrudService;
import com.core.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends CrudService implements UserAccountService {

    @Autowired
    //private UserAccountRepository userAccountRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Override
    public void userLogin(UserLoginForm userLoginForm) {
        //UserAccount user = userAccountRepository.findByEmail(userLoginForm.getEmail()).orElseThrow();
        //LOGGER.info("login:- logging in user with UserId : " + user.getUserId());
    }
}
