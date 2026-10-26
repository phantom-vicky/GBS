package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.AgreementDTO;
import com.isl.gbm.user.management.dto.ClientDetailsDTO;
import com.isl.gbm.user.management.dto.UserDetailsValidationDTO;
import com.isl.gbm.user.management.dto.UserDto;
import java.util.Date;
import java.util.Set;

public interface UserService {

    /**
     * Update user last login details.
     *
     * @param retryCount the retry count
     */
    void updateLoginRetryCount(Integer retryCount);

    /**
     * Gets the user.
     *
     * @return the user
     */
    UserDto getUser();

    /**
     * Update password.
     *
     * @param password the password
     */
    void updatePassword(String password,String Id);

    UserDetailsValidationDTO getUserValidationDetailsForLogin();

    /**
     * Checks if is password recently used.
     *
     * @param password the password
     * @param oldPasswordExclusionCount
     * @return the boolean
     */
    Boolean isPasswordRecentlyUsed(String password, Integer oldPasswordExclusionCount);

    /**
     * Allow old passwords.
     *
     * @return the boolean
     */
    Boolean allowOldPasswords();

    /**
     * Update user last login attempt.
     */
    void updateUserLastLoginAttempt();

    /**
     * Checks if is activation period expired.
     *
     * @return the boolean
     */
    Boolean isActivationPeriodExpired();

    /**
     * Checks if is dormant.
     *
     * @return the boolean
     */
    Boolean isDormant();

    /**
     * Checks if is password expired.
     *
     * @return the boolean
     */
    Boolean isPasswordExpired();

    /**
     * Checks if is user expied.
     *
     * @return the boolean
     */
    Boolean isUserExpired();

    /**
     * Checks if is user block time over.
     *
     * @return the boolean
     */
    Boolean isUserBlockTimeOver();

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    Set<String> getPermissions();

    /**
     * Gets the last success login info.
     *
     * @return the last success login info
     */
    Date getLastSuccessLoginInfo();

    /**
     * Lock user.
     */
    void lockUser();

    /**
     * Sets the account expired.
     */
    void setAccountExpired();

    /**
     * Enable account.
     */
    void enableAccount();

    /**
     * Disable account.
     */
    void disableAccount();

    /**
     * Update user last success login.
     */
    void updateUserLastSuccessLogin();

    /**
     * Log out user by updating Logged_Out Flag
     */
    void logOutUser();

    /**
     * Returns old password exclusion count from User Parameter Table
     *
     * @return oldPasswordExclusionCount
     */
    Integer getUserOldPasswordExclusionCount();

    ClientDetailsDTO getClientInfo();


    ResponseDataDTO postLoginUserDetails();
    ResponseDataDTO getAgreementStatus();

    ResponseDataDTO saveAgreement(AgreementDTO agreementDTO);

    void inActiveUser();
}
