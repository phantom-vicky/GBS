package com.isl.gbm.user.management.validator;


import com.isl.gbm.user.management.service.UserManagementUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserManagementUtilValidator.
 */
@Component
public class UserManagementCommonValidator {

    /**
     * The user management util service.
     */
    @Autowired
    private UserManagementUtilService userManagementUtilService;

    /**
     * Sets the user management util service.
     *
     * @param userManagementUtilService
     *            the new user management util service
     */


    /**
     * Checks if is valid feature id.
     *
     * @param featureId the feature id
     * @return true, if is valid feature id
     */
    public Boolean isValidFeatureId(int featureId) {
        return userManagementUtilService.isValidFeatureId(featureId);
    }
}
