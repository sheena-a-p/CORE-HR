package com.core.serviceImpl;
import com.core.entity.system.UserAccount;
import com.core.entity.system.UserAccountStatusEnum;
import com.core.exception.BadRequestException;
import com.core.form.UserLoginForm;
import com.core.repository.UserAccountRepository;
import com.core.security.config.SecurityConfig;
import com.core.service.CrudService;
import com.core.service.UserAccountService;
import com.core.exception.InvalidTokenException;
import com.core.util.LanguageUtil;
import com.core.exception.TokenExpiredException;
import com.core.util.SecurityUtil;
import com.core.util.TokenGenerator;
import com.core.view.LoginView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.core.util.TokenGenerator.Token;
import java.util.Date;

@Service
public class UserAccountServiceImpl extends CrudService implements UserAccountService {

    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    private static final String PURPOSE_EMAIL_VERIFICATION_TOKEN = "EMAIL_VERIFICATION_TOKEN";
    private static final String PURPOSE_EMAIL_UPDATE_VERIFICATION_TOKEN = "EMAIL_UPDATE_VERIFICATION_TOKEN";
    private static final String PURPOSE_FORGOT_PASSWORD_TOKEN = "FORGOT_PASSWORD_TOKEN";
    public static final String PURPOSE_ACCESS_TOKEN = "ACCESS_TOKEN";

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

    @Override
    public void createUserAccount(UserAccount userAccount) {
        preCreate(userAccount);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) {
        preUpdate(userAccount);
        userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount getById(Integer userId) {
        System.out.println("get By id  : "+userId);
        return userAccountRepository.findById(userId).orElseThrow();
    }

    public UserAccount preCreate(UserAccount userAccount) {
        userAccount.setStatus(100);
        userAccount.setDateCreated(new Date());
        userAccount.setDateModified(new Date());
        userAccount.setStaffCreated(SecurityUtil.getCurrentStaffId());
        userAccount.setStaffModified(SecurityUtil.getCurrentStaffId());
        userAccount.setCompanyId(SecurityUtil.getCurrentCompanyId());
        return  userAccount;
    }

    public UserAccount preUpdate(UserAccount userAccount) {
        userAccount.setDateModified(new Date());
        userAccount.setStaffModified(SecurityUtil.getCurrentStaffId());
        return userAccount;
    }

    @Override
    public LoginView userLogin(UserLoginForm userLoginForm) {
        UserAccount user = userAccountRepository.findByEmail(userLoginForm.getEmail()).orElseThrow();
        LOGGER.info("login:- logging in user with UserId : " + user.getUserId());

        if (!passwordEncoder.matches(userLoginForm.getPassword(), user.getPassword())) {
            throw new RuntimeException(languageUtil.getTranslatedText("invalid.credantials", null, "en"));
        }
        if (UserAccountStatusEnum.ACTIVE.getValue() != user.getStatus()) {
            try {
                tokenGenerator.verify(PURPOSE_EMAIL_VERIFICATION_TOKEN, user.getVerificationToken());
            } catch (InvalidTokenException e) {
                throw new BadRequestException(languageUtil.getTranslatedText("access.token.expired", null, "en"));
            } catch (TokenExpiredException e) {
                throw new BadRequestException("Token is expired");
            }
            throw new BadRequestException(languageUtil.getTranslatedText("email.not.verified", null, "en"));
        }
        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(),
                securityConfig.getRefreshTokenExpiry());

        return  new LoginView(user.getEmail(),accessToken,refreshToken);
    }

}
