/*
 *
 */
package com.isl.gbm.user.management.security.shiro.config;


import com.isl.gbm.user.management.dto.UserDto;
import com.isl.gbm.user.management.model.UserDomain;
import com.isl.gbm.user.management.service.UserService;
import com.isl.gbm.user.management.util.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * The Class BcryptPasswordMatcher.
 */
@Component
public class BcryptPasswordMatcher implements CredentialsMatcher {

    /**
     * The user service.
     */
    @Autowired
    @Lazy
    private UserService userService;


    /**
     * Do credentials match.
     *
     * @param token the token
     * @param info  the info
     * @return true, if successful
     */
    /* (non-Javadoc)
     * @see org.apache.shiro.authc.credential.CredentialsMatcher#doCredentialsMatch(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationInfo)
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String password = new String(userToken.getPassword());
        UserDto user = this.userService.getUser();
        if (user.getUserDomain().equals(UserDomain.FINACLE.getDomain())) {
            return true;
        }
        if (user.getLastLoginAttempt() == null || !DateUtils.isToday(user.getLastLoginAttempt())) {
            user.setInvalidLoginCount(0);
        }

        Integer retryCount = user.getInvalidLoginCount();
        boolean matches = BCrypt.checkpw(password, info.getCredentials().toString());

        retryCount = matches ? 0 : retryCount + 1;

        this.userService.updateLoginRetryCount(retryCount);

        if (retryCount > 3) {
            this.userService.lockUser();
            throw new ExcessiveAttemptsException();
        }

        return matches;
    }

}