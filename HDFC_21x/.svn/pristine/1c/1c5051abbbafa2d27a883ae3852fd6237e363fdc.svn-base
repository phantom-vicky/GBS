package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.AgreementDTO;
import com.isl.gbm.user.management.dto.ClientDetailsDTO;
import com.isl.gbm.user.management.dto.UserDetailsValidationDTO;
import com.isl.gbm.user.management.dto.UserDto;
import com.isl.gbm.user.management.manager.UserManager;
import com.isl.gbm.user.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;


/**
 * The Class UserServiceImpl.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    /**
     * The user manager.
     */
    @Autowired
    private UserManager userManager;

    /**
     * Sets the user manager.
     *
     * @paramuserManager the new user manager
     */


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#updateLastLoginDetails(java.lang.Integer)
     */
    @Override
    public void updateLoginRetryCount(Integer retryCount) {
        this.userManager.updateLoginRetryCount(retryCount);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#getUser()
     */
    @Override
    public UserDto getUser() {
        return this.userManager.getUser();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#updatePassword(java.lang.String)
     */
    @Override
    public void updatePassword(String password,String id) {
        userManager.
                updatePassword(password,id);
    }


    @Override
    public UserDetailsValidationDTO getUserValidationDetailsForLogin(){
        return userManager.getUserValidationDetailsForLogin();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isPasswordRecentlyUsed(java.lang.String)
     */
    @Override
    public Boolean isPasswordRecentlyUsed(String password, Integer oldPasswordExclusionCount) {
        return this.userManager.isPasswordRecentlyUsed(password, oldPasswordExclusionCount);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#allowOldPasswords()
     */
    @Override
    public Boolean allowOldPasswords() {
        return this.userManager.allowOldPasswords();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#updateUserLastLoginAttempt()
     */
    @Override
    public void updateUserLastLoginAttempt() {
        this.userManager.updateUserLastLoginAttempt();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isActivationPeriodExpired()
     */
    @Override
    public Boolean isActivationPeriodExpired() {
        return this.userManager.isActivationPeriodExpired();
    }



    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isDormant()
     */
    @Override
    public Boolean isDormant() {
        return this.userManager.isDormant();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isPasswordExpired()
     */
    @Override
    public Boolean isPasswordExpired() {
        return this.userManager.isPasswordExpired();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isUserExpied()
     */
    @Override
    public Boolean isUserExpired() {
        return this.userManager.isUserExpied();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#isUserBlockTimeOver()
     */
    @Override
    public Boolean isUserBlockTimeOver() {
        return this.userManager.isUserBlockTimeOver();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#getPermissions()
     */
    @Override
    public Set<String> getPermissions() {
        return userManager.getPermissions();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#getLastSuccessLoginInfo()
     */
    @Override
    public Date getLastSuccessLoginInfo() {
        return this.userManager.getLastSuccessLoginInfo();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#lockUser()
     */
    @Override
    public void lockUser() {
        this.userManager.lockUser();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#setAccountExpired()
     */
    @Override
    public void setAccountExpired() {
        this.userManager.setAccountExpired();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#enableAccount()
     */
    @Override
    public void enableAccount() {
        this.userManager.enableAccount();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#disableAccount()
     */
    @Override
    public void disableAccount() {
        this.userManager.disableAccount();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserService#updateUserLastSuccessLogin()
     */
    @Override
    public void updateUserLastSuccessLogin() {
        this.userManager.updateUserLastSuccessLogin();
    }

    @Override
    public void logOutUser() {
        this.userManager.logOutUser();
    }

    /**
     * @see com.isl.gbm.user.management.service.UserService#getUserOldPasswordExclusionCount()
     */
    @Override
    public Integer getUserOldPasswordExclusionCount() {
        return this.userManager.getUserOldPasswordExclusionCount();
    }

    @Override
    public ClientDetailsDTO getClientInfo() {
        return userManager.getClientInfo();
    }

    @Override
    public ResponseDataDTO postLoginUserDetails(){
        return userManager.postLoginUserDetails();
    }

    @Override
    public ResponseDataDTO getAgreementStatus(){
        return userManager.getAgreementStatus();
    }

    @Override
    public ResponseDataDTO saveAgreement(AgreementDTO agreementDTO){
        return userManager.saveAgreement(agreementDTO);
    }

    @Override
    public void inActiveUser() {
        this.userManager.inActiveUser();
    }


}
