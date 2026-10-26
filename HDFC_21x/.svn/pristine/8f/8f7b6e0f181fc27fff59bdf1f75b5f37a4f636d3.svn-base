/*
 *
 */
package com.isl.gbm.user.management.service;



import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The Interface UserAdministrationService.
 */

/**
 * @author Anoop.Kumar
 */
public interface UserAdministrationService {
    /**
     * Creates the user.
     *
     * @param user the user
     */
    ResponseDataDTO createUser(UserDto user);

    /**
     * Creates the user role mapping.
     *
     * @param userId  the user id
     * @param roleIds the role ids
     */
    void createUserRoleMapping(Integer userId, Set<Integer> roleIds);

    /**
     * Delete user.
     *
     * @param userId the user id
     */
    void deleteUser(Integer userId);

    /**
     * Disable user.
     *
     * @param userId       the user id
     * @param disabledTill the disabled till
     */
    void disableUser(Integer userId, Date disabledTill);

    /**
     * Gets the active users.
     *
     * @return the active users
     */
    List<ActiveUserDto> getActiveUsers();

    List<ActiveUserDto> getActiveUsersForSack(UserAdminSackDTO userAdminSackDTO);

    /**
     * Gets the locked users.
     *
     * @return the locked users
     */
    List<UserDto> getLockedUsers();

    /**
     * Gets the user.
     *
     * @param userId the user id
     * @return the user
     */
    UserDto getUser(Integer userId);

    /**
     * Gets the user by username.
     *
     * @param username the username
     * @return the user by username
     */
    UserDto getUserByUsername(String username);

    /**
     * Gets the user param.
     *
     * @return the user param
     */
    UserParamDto getUserParam();

    /**
     * Gets the user roles.
     *
     * @param userId the user id
     * @return the user roles
     */
    List<RoleDto> getUserRoles(Integer userId);

    /**
     * Gets the users.
     *
     * @return the users
     */
    List<UserDto> getUsers();

    /**
     * Checks if is user exists.
     *
     * @param userId the user id
     * @return the boolean
     */
    Boolean isUserExists(Integer userId);

    /**
     * Checks if is username exists.
     *
     * @param username the username
     * @return true, if is username exists
     */
    Boolean isUsernameExists(String username);

    void sackUsers(List<ActiveUserDto> dtoList);

    void killSingleUserForceLogout(ActiveUserDto dtoList);

    /**
     * Unlock user.
     *
     * @param userId the user id
     */
    void unlockUser(Integer userId);

    /**
     * Update password.
     *
     * @param userId   the user id
     * @param password the password
     */
    void updatePassword(Integer userId, String password);

    /**
     * Update user.
     *
     * @param user the user
     */
    void updateUser(UserDto user);

    /**
     * Save user param.
     *
     * @param userParamDto the user param dto
     */
    void updateUserParams(UserParamDto userParamDto);


    void activeUser(Integer userId);

    ResponseDataDTO modifyUserDetails(String userId);

    ResponseDataDTO verifyUserDetails(String userId);

    ResponseDataDTO modifyUser(UserDetailsDto user);

    ResponseDataDTO deleteUser(UserDetailsDto user);

    ResponseDataDTO inquiryUserDetails(String userId);

    ResponseDataDTO verifyUser(UserDetailsDto user);

    void modifyUser(UserDto user);

    void updateLogOutInfo(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc);
    ResponseDataDTO getUserLogin(String username);

    ResponseDataDTO forceLogoutSingleUser(String username);
}
