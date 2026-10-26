package com.isl.gbm.user.management.validator;


import com.isl.gbm.user.management.service.UserAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserAdministrationValidator.
 *
 * @author ws1a271
 */
@Component
public class UserAdministrationValidator {

    /**
     * The user administration service.
     */
    @Autowired
    private UserAdministrationService userAdministrationService;

    /**
     * Sets the user administration service.
     *
     * @param userAdministrationService
     *            the new user administration service
     */

    /**
     * Checks if is username exists.
     *
     * @param username the username
     * @return the boolean
     */
    public Boolean isUsernameExists(String username) {
        return this.userAdministrationService.isUsernameExists(username);
    }

    /**
     * Checks if is user exists.
     *
     * @param userId the user id
     * @return the boolean
     */
    public Boolean isUserExists(Integer userId) {
        return this.userAdministrationService.isUserExists(userId);
    }

}
