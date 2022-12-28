package com.core.security;
import com.core.entity.system.UserAccount;
import com.core.entity.system.UserAccountStatusEnum;
import com.core.repository.UserAccountRepository;
import com.core.security.util.InvalidTokenException;
import com.core.security.util.TokenExpiredException;
import com.core.security.util.TokenGenerator;
import com.core.util.LanguageUtil;
import com.core.security.util.TokenGenerator.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class AccessTokenUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    public static final String PURPOSE_ACCESS_TOKEN = "ACCESS_TOKEN";

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        if (!PURPOSE_ACCESS_TOKEN.equals(token.getCredentials())) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        final Status status;
        try {
            status = tokenGenerator.verify(PURPOSE_ACCESS_TOKEN, token.getPrincipal().toString());
        } catch (InvalidTokenException e) {
            throw new UsernameNotFoundException("Invalid access token", e);
        } catch (TokenExpiredException e) {
            throw new UsernameNotFoundException("Access token expired", e);
        }
        int userId = Integer.parseInt(status.data.trim());
        UserAccount userAccount = userAccountRepository.findById(userId).orElse(null);
        if (UserAccountStatusEnum.ACTIVE.value != userAccount.getStatus()) {
            throw new UsernameNotFoundException(languageUtil.getTranslatedText("access.token.expired", null, "en"));
        }
        return new AccessTokenUserDetails(userId);
    }
}