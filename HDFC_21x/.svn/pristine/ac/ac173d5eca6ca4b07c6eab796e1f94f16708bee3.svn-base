/*
 *
 */
package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.manager.UserAdministrationManager;
import com.isl.gbm.user.management.service.UserAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The Class UserAdministrationServiceImpl.
 */
@Service
@Transactional
public class UserAdministrationServiceImpl implements UserAdministrationService {

    /**
     * The user administration manager.
     */
    @Autowired
    private UserAdministrationManager userAdministrationManager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#createUser(
     * com.afl.gbm.user.management.dto.UserDto)
     */
    @Override
    public ResponseDataDTO createUser(final UserDto user) {
       return userAdministrationManager.createUser(user);
    }

    @Override
    public ResponseDataDTO modifyUserDetails(String userId){
        return userAdministrationManager.modifyUserDetails(userId);
    }

    @Override
    public ResponseDataDTO verifyUserDetails(String userId){
        return userAdministrationManager.verifyUserDetails(userId);
    }

    @Override
    public ResponseDataDTO modifyUser(UserDetailsDto user){
        return userAdministrationManager.modifyUser(user);
    }

    @Override
    public ResponseDataDTO deleteUser(UserDetailsDto user){
        return userAdministrationManager.deleteUser(user);
    }
    @Override
    public ResponseDataDTO inquiryUserDetails(String userId){
        return userAdministrationManager.inquiryUserDetails(userId);
    }

    @Override
    public ResponseDataDTO verifyUser(UserDetailsDto user){
        return userAdministrationManager.verifyUser(user);
    }

    /**
     * @param user
     */
    @Override
    public void modifyUser(final UserDto user) {
        this.userAdministrationManager.modifyUser(user);
    }

    @Override
    public void updateLogOutInfo(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc) {
        this.userAdministrationManager.updateLogOutInfo(activeUserDtoList, logOutReason, logOutReasonDesc);
    }

    @Override
    public ResponseDataDTO getUserLogin(String username) {
        return userAdministrationManager.getUserLogin(username);

    }

    @Override
    public ResponseDataDTO forceLogoutSingleUser(String username) {
        return userAdministrationManager.forceLogoutSingleUser(username);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * createUserRoleMapping(java.lang.Integer, java.util.Set)
     */
    @Override
    public void createUserRoleMapping(final Integer userId, final Set<Integer> roleIds) {
        this.userAdministrationManager.saveUserRoleMapping(userId, roleIds);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#deleteUser(
     * java.lang.Integer)
     */
    @Override
    public void deleteUser(final Integer userId) {
        this.userAdministrationManager.deleteUser(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#disableUser
     * (java.lang.Integer)
     */
    @Override
    public void disableUser(final Integer userId, final Date disabledTill) {
        this.userAdministrationManager.disableUser(userId, disabledTill);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * getActiveUsers()
     */
    @Override
    public List<ActiveUserDto> getActiveUsers() {
        return this.userAdministrationManager.getActiveUsers();
    }

    @Override
    public List<ActiveUserDto> getActiveUsersForSack(UserAdminSackDTO userAdminSackDTO) {
        return this.userAdministrationManager.getActiveUsersForSack(userAdminSackDTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * getLockedUsers(java.lang.Integer)
     */
    @Override
    public List<UserDto> getLockedUsers() {
        return this.userAdministrationManager.getLockedUsers();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#getUser(
     * java.lang.Integer)
     */
    @Override
    public UserDto getUser(final Integer userId) {
        return this.userAdministrationManager.getUser(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * getUserByUsername(java.lang.String)
     */
    @Override
    public UserDto getUserByUsername(final String username) {
        return this.userAdministrationManager.getUserByUsername(username);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * getUserParam()
     */
    @Override
    public UserParamDto getUserParam() {
        return this.userAdministrationManager.getUserParam();
    }

    @Override
    public List<RoleDto> getUserRoles(final Integer userId) {
        return this.userAdministrationManager.getUserRoles(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#getUsers()
     */
    @Override
    public List<UserDto> getUsers() {
        return this.userAdministrationManager.getUsers();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * isUserExists(java.lang.Integer)
     */
    @Override
    public Boolean isUserExists(final Integer userId) {
        return this.userAdministrationManager.isUserExists(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * isUsernameExists(java.lang.String)
     */
    @Override
    public Boolean isUsernameExists(final String username) {
        return this.userAdministrationManager.isUsernameExists(username);
    }

    @Override
    public void sackUsers(final List<ActiveUserDto> dtoList) {
        this.userAdministrationManager.sackUsers(dtoList);
    }

    @Override
    public void killSingleUserForceLogout(ActiveUserDto dtoList) {
        this.userAdministrationManager.killSingleUserForceLogout(dtoList);
    }

    /**
     * Sets the user administration manager.
     *
     * @param userAdministrationManager the new user administration manager
     */
    public void setUserAdministrationManager(final UserAdministrationManager userAdministrationManager) {
        this.userAdministrationManager = userAdministrationManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#unlockUser(
     * java.lang.Integer)
     */
    @Override
    public void unlockUser(final Integer userId) {
        this.userAdministrationManager.unlockUser(userId);
    }


    /**
     * @param userId
     */
    @Override
    public void activeUser(final Integer userId) {
        this.userAdministrationManager.activeUser(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * updatePassword(java.lang.Integer, java.lang.String)
     */
    @Override
    public void updatePassword(final Integer userId, final String password) {
        this.userAdministrationManager.updatePassword(userId, password);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.user.management.service.UserAdministrationService#updateUser(
     * com.afl.gbm.user.management.dto.UserDto)
     */
    @Override
    public void updateUser(final UserDto user) {
        this.userAdministrationManager.updateUser(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.user.management.service.UserAdministrationService#
     * saveUserParam(com.afl.gbm.user.management.dto.UserParamDto)
     */
    @Override
    public void updateUserParams(final UserParamDto userParamDto) {
        this.userAdministrationManager.updateUserParams(userParamDto);
    }

}
