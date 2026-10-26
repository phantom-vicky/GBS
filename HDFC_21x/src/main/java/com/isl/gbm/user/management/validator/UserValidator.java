package com.isl.gbm.user.management.validator;


import com.isl.gbm.user.management.dto.UserDetailsValidationDTO;
import com.isl.gbm.user.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserValidator.
 */
@Component
public class UserValidator {

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * Sets the user service.
     *
     * @param userService the new user service
     */


    /**
     * Checks if is password recently used.
     *
     * @param password                  the password
     * @param oldPasswordExclusionCount
     * @return the boolean
     */
    public Boolean isPasswordRecentlyUsed(String password, Integer oldPasswordExclusionCount) {
        return this.userService.isPasswordRecentlyUsed(password, oldPasswordExclusionCount);
    }

    /**
     * Allow old passwords.
     *
     * @return the boolean
     */
    public Boolean allowOldPasswords() {
        return this.userService.allowOldPasswords();
    }

    /**
     * Checks if is activation period expired.
     *
     * @return the boolean
     */
    public Boolean isActivationPeriodExpired() {
        return this.userService.isActivationPeriodExpired();
    }

    /**
     * Checks if is dormant.
     *
     * @return the boolean
     */
    public Boolean isDormant() {
        return this.userService.isDormant();
    }

    /**
     * Checks if is password expired.
     *
     * @return the boolean
     */
    public Boolean isPasswordExpired() {
        return this.userService.isPasswordExpired();
    }

    /**
     * Checks if is user expied.
     *
     * @return the boolean
     */
    public Boolean isUserExpired() {
        return this.userService.isUserExpired();
    }

    /**
     * Checks if is user block time over.
     *
     * @return the boolean
     */
    public Boolean isUserBlockTimeOver() {
        return this.userService.isUserBlockTimeOver();
    }

    public UserDetailsValidationDTO getUserValidationDetailsForLogin(){
        return this.userService.getUserValidationDetailsForLogin();
    }
}
