/*
 *
 */
package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.constants.UserLogOutReasons;
import com.isl.gbm.user.management.controller.UserLoginController;
import com.isl.gbm.user.management.dao.RoleDao;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.dao.UserLogDao;
import com.isl.gbm.user.management.dao.UserParamDao;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.Role;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.model.UserDomain;
import com.isl.gbm.user.management.model.UserParam;
import com.isl.gbm.user.management.model.UserStatus;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


/**
 * The Class UserAdministrationManager.
 */
@Component
public class UserAdministrationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdministrationManager.class);


    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuditLogger auditLogger;

    /**
     * The role dao.
     */
    @Autowired
    private RoleDao roleDao;


    @Autowired
    private UserLogDao userLogDao;

    /**
     * The user param dao.
     */
    @Autowired
    private UserParamDao userParamDao;

    /**
     * Creates the user.
     *
     * @param userDto the user dto
     */
    public ResponseDataDTO createUser(final UserDto userDto) {
        final User user = new User();
        this.mapUserDtoToModel(userDto, user);
        user.setUsername(userDto.getUsername());
        user.setCreatedByUser(UserAccountUtils.getCurrentUsername());
        user.setCreatedDate(new Date());
        if (UserDomain.FINACLE.getDomain().equals(user.getUserDomain())) {
            user.setStatus(UserStatus.ACTIVE.getId());
        } else {
            user.setPassword(UserAccountUtils.encryptPassword(userDto.getPassword()));
            user.setStatus(UserStatus.INACTIVE.getId());
        }
        user.setDeleted(0);
        return userDao.saveUser(user);
    }


    public ResponseDataDTO modifyUser(UserDetailsDto user){
        return userDao.modifyUser(user);
    }

    public ResponseDataDTO inquiryUserDetails(String userId){
        return userDao.inquiryUserDetails(userId);
    }


    public ResponseDataDTO verifyUser(UserDetailsDto user){
        return userDao.verifyUser(user);
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     */
    public void deleteUser(final Integer userId) {
        final User user = this.userDao.getUser(userId);
        user.setDeleted(1);
        user.setStatus(UserStatus.DELETED.getId());
        this.userDao.save(user);
    }

    /**
     * Disable user.
     *
     * @param userId the user id
     */
    public void disableUser(final Integer userId, final Date disabledTill) {
        final User user = this.userDao.getUser(userId);
        user.setStatus(UserStatus.DISABLED.getId());
        if (disabledTill != null) {
            user.setUserDisabledTill(disabledTill);
        }
        this.userDao.save(user);

    }

    /**
     * Gets the active users.
     *
     * @return the active users
     */
    public List<ActiveUserDto> getActiveUsers() {
        final List<ActiveUserDto> activeUsers = new ArrayList<>();
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();

        for (final Session session : activeSessions) {

            final ActiveUserDto activeUserDto = new ActiveUserDto();
            activeUserDto.setHostAddress(session.getHost());
            activeUserDto.setLastAccessTime(session.getLastAccessTime());
            activeUserDto.setLoggedInSince(session.getStartTimestamp());
            activeUserDto.setMaxIdleTime(session.getTimeout());

            activeUserDto.setUsername((String) session.getAttribute(UserConstants.USERNAME));
            activeUserDto.setSessionId(session.getId().toString());
            activeUsers.add(activeUserDto);
        }
        return activeUsers;
    }

    @SuppressWarnings({"null", "unused"})
    public List<ActiveUserDto> getActiveUsersForSack(UserAdminSackDTO userAdminSackDTO) {
        final List<ActiveUserDto> activeUsers = new ArrayList<>();
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();

        Session session1 = null;
        ResponseDataDTO responseDataDTO = userDao.getCurrentLoggedInBranchActiveUsers(userAdminSackDTO);
        Map<String,ActiveUserDto> loggedInUsersInDb = null;
        if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST) && userAdminSackDTO.getBranchId() != null){
            loggedInUsersInDb = (Map<String,ActiveUserDto>)responseDataDTO.getResult();
            loggedInUsersInDb.remove(UserAccountUtils.getCurrentUsername());
        } else if(userAdminSackDTO.getBranchId() == null){
            loggedInUsersInDb = (Map<String,ActiveUserDto>)responseDataDTO.getResult();
            activeUsers.addAll(loggedInUsersInDb.values());
            return activeUsers;
        }
        else {
            throw new GenericBusinessException(responseDataDTO.getMessage());
        }


        for (final Session session : activeSessions) {
            final ActiveUserDto activeUserDto = new ActiveUserDto();
            activeUserDto.setHostAddress(session.getHost());
            activeUserDto.setLastAccessTime(session.getLastAccessTime());
            activeUserDto.setLoggedInSince(session.getStartTimestamp());
            activeUserDto.setMaxIdleTime(session.getTimeout());

            activeUserDto.setUsername((String) session.getAttribute(UserConstants.USERNAME));
            String SOL_ID = (String) session.getAttribute(UserConstants.SOL_ID);
            String currentSolId = UserAccountUtils.getCurrentSolId();

            activeUserDto.setSessionId(session.getId().toString());
            if (!StringUtils.equalsIgnoreCase(session.getId().toString(),
                    SecurityUtils.getSubject().getSession().getId().toString()) && StringUtils.equals(SOL_ID, currentSolId))
       /*     if (!StringUtils.equalsIgnoreCase(session.getId().toString(),
                    SecurityUtils.getSubject().getSession().getId().toString()))*/{
                activeUsers.add(activeUserDto);
                loggedInUsersInDb.remove(activeUserDto.getUsername());

            }
        }
        if(loggedInUsersInDb!=null)
            activeUsers.addAll(loggedInUsersInDb.values());
        return activeUsers;
    }

    public ResponseDataDTO modifyUserDetails(String userId){
        return userDao.modifyUserDetails(userId);
    }

    public ResponseDataDTO deleteUser(UserDetailsDto user){
        return userDao.deleteUser(user);
    }

    public ResponseDataDTO verifyUserDetails(String userId){
        return userDao.verifyUserDetails(userId);
    }

    /**
     * Gets the locked users.
     *
     * @return the locked users
     */
    public List<UserDto> getLockedUsers() {
        final List<UserDto> users = new ArrayList<>();
        this.userDao.getLockedUsers().stream().forEach(user -> users.add(this.mapUserModelToDto(user)));
        return users;
    }

    /**
     * Gets the user.
     *
     * @param userId the user id
     * @return the user
     */
    public UserDto getUser(final Integer userId) {
        return this.mapUserModelToDto(this.userDao.getUser(userId));
    }

    /**
     * Gets the user by username.
     *
     * @param username the username
     * @return the user by username
     */
    public UserDto getUserByUsername(final String username) {
        final User user = this.userDao.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        final UserDto userDto = this.mapUserModelToDto(user);
        userDto.setPassword(user.getPassword());
        return userDto;
    }

    /**
     * Gets the user param.
     *
     * @return the user param
     */
    public UserParamDto getUserParam() {
        return this.mapUserParamModelToDto(this.userParamDao.getUserParam());
    }

    /**
     * Gets the user roles.
     *
     * @param userId the user id
     * @return the user roles
     */
    public List<RoleDto> getUserRoles(final Integer userId) {
        final List<RoleDto> roleDtos = new ArrayList<>();
        final User user = this.userDao.getUser(userId);
        final Set<Role> roles = user.getRoles();
        for (final Role role : roles) {
            roleDtos.add(this.mapRoleModelToDto(role));
        }
        return roleDtos;
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
    public List<UserDto> getUsers() {
        final List<UserDto> users = new ArrayList<>();
        this.userDao.getUsers().stream().forEach(user -> users.add(this.mapUserModelToDto(user)));
        return users;
    }

    /**
     * Checks if is user exists.
     *
     * @param userId the user id
     * @return the boolean
     */
    public Boolean isUserExists(final Integer userId) {
        return this.userDao.getUser(userId) != null;
    }

    /**
     * Checks if is username esists.
     *
     * @param username the username
     * @return true, if is username esists
     */
    public Boolean isUsernameExists(final String username) {
        return this.userDao.getUserByUsername(username) != null;
    }

    /**
     * Map role model to dto.
     *
     * @param role the role
     * @return the role dto
     */
    private RoleDto mapRoleModelToDto(final Role role) {
        final RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setCreatedByUsername(role.getCreatedBy());
        roleDto.setCreatedDate(role.getCreatedDate());
        roleDto.setDescription(role.getDescription());
        roleDto.setName(role.getName());
        return roleDto;
    }

    /**
     * Map user dto to model.
     *
     * @param userDto the user dto
     * @return the user
     */
    private void mapUserDtoToModel(final UserDto userDto, final User user) {
        user.setId(userDto.getId());
        user.setApplicationName(userDto.getApplicationName());
        user.setDepartmentCode(userDto.getDepartmentCode());
        user.setEmployeeCode(userDto.getEmployeeCode());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLocationCode(userDto.getLocationCode());
        user.setSolId(userDto.getSolId());
        user.setSupervisorCode(userDto.getSupervisorCode());
        user.setUserType(userDto.getUserType());
        user.setUserExpiryDate(userDto.getUserExpiryDate());
        user.setUserDomain(UserDomain.findByDomain(userDto.getUserDomain()).getId());
    }

    /**
     * Map user model to dto.
     *
     * @param user the user
     * @return the user
     */
    private UserDto mapUserModelToDto(final User user) {
        final UserDto userAdminDto = new UserDto();
        userAdminDto.setId(user.getId());
        userAdminDto.setApplicationName(user.getApplicationName());
        userAdminDto.setDepartmentCode(user.getDepartmentCode());
        userAdminDto.setEmployeeCode(user.getEmployeeCode());
        userAdminDto.setFirstName(user.getFirstName());
        userAdminDto.setLastName(user.getLastName());
        userAdminDto.setLocationCode(user.getLocationCode());
        userAdminDto.setSolId(user.getSolId());
        userAdminDto.setSupervisorCode(user.getSupervisorCode());
        userAdminDto.setUserType(user.getUserType());
        userAdminDto.setUserExpiryDate(user.getUserExpiryDate());
        userAdminDto.setUsername(user.getUsername());
        userAdminDto.setActivationTime(user.getActivationTime());
        userAdminDto.setCreatedByUser(user.getCreatedByUser());
        userAdminDto.setCreatedDate(user.getCreatedDate());
        userAdminDto.setDeleted(user.getDeleted());
        userAdminDto.setModifiedByUser(user.getModifiedByUser());
        userAdminDto.setModifiedDate(user.getModifiedDate());
        userAdminDto.setPasswordExpiryDate(user.getPasswordExpiryDate());
        userAdminDto.setPasswordModifiedDate(user.getPasswordModifiedDate());
        userAdminDto.setStatus(UserStatus.findById(user.getStatus()).getStatus());
        userAdminDto.setUserDisabledTill(user.getUserDisabledTill());
        userAdminDto.setUserExpiryDate(user.getUserExpiryDate());
        userAdminDto.setVerifiedByUser(user.getVerifiedByUser());
        userAdminDto.setVerifiedDate(user.getVerifiedDate());
        userAdminDto.setLastLoginAttempt(user.getLastLoginAttempt());
        userAdminDto.setInvalidLoginCount(user.getInvalidLoginCount());
        userAdminDto.setUserDomain(UserDomain.findById(user.getUserDomain()).getDomain());
        return userAdminDto;
    }

    /**
     * Map user param model to dto.
     *
     * @param userParam the user param
     * @return the user param dto
     */
    private UserParamDto mapUserParamModelToDto(final UserParam userParam) {
        final UserParamDto userParamDto = new UserParamDto();
        userParamDto.setActivationPeriod(userParam.getActivationPeriod());
        userParamDto.setAllowOldPassword(userParam.getAllowOldPassword() == 1 ? true : false);
        userParamDto.setDormancyPeriod(userParam.getDormancyPeriod());
        userParamDto.setId(userParam.getId());
        userParamDto.setOldPasswordExclusionCount(userParam.getOldPasswordExclusionCount());
        userParamDto.setPasswordAge(userParam.getPasswordAge());
        return userParamDto;
    }

    public void sackUsers(final List<ActiveUserDto> dtoList) {
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();

        LOGGER.info("active sessions: " + activeSessions.toString());
        LOGGER.info("dtoList: " + dtoList.toString());
        for(ActiveUserDto activeUser : dtoList){
            if(activeUser.getUsername() != null){
                for(Session session : activeSessions){
                    try {
                        String sessionUsername = session.getAttribute(UserConstants.USERNAME).toString();
                        LOGGER.info("loop session name: " +sessionUsername);
                        if(sessionUsername != null){
                            if(StringUtils.equalsIgnoreCase(sessionUsername, activeUser.getUsername())
                                    && (ObjectUtils.notEqual(session, SecurityUtils.getSubject().getSession()))){
                                LOGGER.info("killing Username: " + activeUser.getUsername() + " with session ID: " + session.getId());
                                sessionManager.getSessionDAO().delete(session);
                                session.stop();
                                LOGGER.info("killing user initiated for: " + activeUser.getUsername());
                                ResponseDataDTO userResponse = userDao.forceLogoutSingleUser(activeUser.getUsername());
                                if(userResponse.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
                                    LOGGER.info("Successfully killed: " + activeUser.getUsername());
                                }else{
                                    LOGGER.info("Failed to kill: " + activeUser.getUsername());
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            }
        }
        this.updateLogOutInfo(dtoList, UserLogOutReasons.SACKED_BY_OTHER.getReasonName(),UserLogOutReasons.SACKED_BY_OTHER.getReasonDesc());
    }




//    public void sackUsers(final List<ActiveUserDto> dtoList) {
//        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
//        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
//        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();
//        ArrayList activeSessionList = new ArrayList<>();
//        ArrayList sackedSessionList = new ArrayList<>();
//
//        for (final Session session : activeSessions) {
//            System.out.println("Active session USER NAME " + session.getAttribute(UserConstants.USERNAME)!=null ? session.getAttribute(UserConstants.USERNAME).toString() :  session.getAttribute(UserConstants.USERNAME));
//            LOGGER.info("Active session  ID " + session.getId());
//            activeSessionList.add(session.getAttribute(UserConstants.USERNAME));
//            activeSessionList.add(session.getId());
//            for (final ActiveUserDto user : dtoList) {
//                if (StringUtils.equalsIgnoreCase(session.getAttribute(UserConstants.USERNAME).toString(), user.getUsername())
//                        && (ObjectUtils.notEqual(session, SecurityUtils.getSubject().getSession()))) {
//                    //session.stop();
//                    //user.setUserId((Integer) session.getAttribute(UserConstants.USER_ID));
//                    LOGGER.info("SACKED SESSION USER NAME " + session.getAttribute(UserConstants.USERNAME));
//                    LOGGER.info("SACKED SESSION  ID " + session.getId());
//                    sackedSessionList.add(session.getAttribute(UserConstants.USERNAME));
//                    sackedSessionList.add(session.getId());
//                    sessionManager.getSessionDAO().delete(session);
//
//                    session.stop();
//                }
//            }
//        }
//
//        LOGGER.info("ACTIVE SESSION  List " + activeSessionList.size());
//        LOGGER.info("SACKED SESSION  List " + sackedSessionList.size());
//        this.updateLogOutInfo(dtoList, UserLogOutReasons.SACKED_BY_OTHER.getReasonName(),UserLogOutReasons.SACKED_BY_OTHER.getReasonDesc());
//    }

    public void killSingleUserForceLogout(final ActiveUserDto activeUserDto){
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();
        for (final Session session : activeSessions) {
            if (StringUtils.equalsIgnoreCase(session.getId().toString(), activeUserDto.getSessionId())
                    && (ObjectUtils.notEqual(session, SecurityUtils.getSubject().getSession()))) {

                activeUserDto.setUserId((Integer) session.getAttribute(UserConstants.USER_ID));
                sessionManager.getSessionDAO().delete(session);
                session.stop();
            }
        }
        List<ActiveUserDto> dtoList = new ArrayList<>();
        dtoList.add(activeUserDto);
        this.updateLogOutInfo(dtoList, UserLogOutReasons.SACKED_BY_OTHER.getReasonName(),UserLogOutReasons.SACKED_BY_OTHER.getReasonDesc());
    }

    /**
     * Save user role mapping.
     *
     * @param userId  the user id
     * @param roleIds the role ids
     */
    public void saveUserRoleMapping(final Integer userId, final Set<Integer> roleIds) {
        final User user = this.userDao.getUser(userId);
        final Set<Role> roleSet = new HashSet<>();
        for (final Integer roleId : roleIds) {
            final Role role = this.roleDao.getRole(roleId);
            if (role != null) {
                roleSet.add(role);
            }
        }
        user.setRoles(roleSet);
        user.setModifiedDate(new Date());
        user.setModifiedByUser(UserAccountUtils.getCurrentUsername());
        this.userDao.save(user);
    }

    /**
     * Sets the role dao.
     *
     * @param roleDao the new role dao
     */
    public void setRoleDao(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Sets the user param dao.
     *
     * @param userParamDao the new user param dao
     */
    public void setUserParamDao(final UserParamDao userParamDao) {
        this.userParamDao = userParamDao;
    }

    /**
     * Unlock user.
     *
     * @param userId the user id
     */
    public void unlockUser(final Integer userId) {
        final User user = this.userDao.getUser(userId);
        if (user.getStatus().equals(UserStatus.LOCKED.getId())) {
            user.setStatus(UserStatus.ACTIVE.getId());
            user.setInvalidLoginCount(0);
        }
        this.userDao.save(user);
    }

    public void activeUser(Integer userId) {
        final User user = this.userDao.getUser(userId);
        if (user.getStatus().equals(UserStatus.DISABLED.getId())) {
            user.setStatus(UserStatus.ACTIVE.getId());
            user.setUserDisabledTill(null);
            user.setLastSuccessLogin(new Date());
            user.setPasswordExpiryDate(new Date());
            user.setInvalidLoginCount(0);
        }
        this.userDao.save(user);
    }

    /**
     * Update password.
     *
     * @param userId   the user id
     * @param password the password
     */
    public void updatePassword(final Integer userId, final String password) {
        final User user = this.userDao.getUser(userId);
        user.setPassword(UserAccountUtils.encryptPassword(password));
        user.setPasswordModifiedDate(new Date());
        user.setPasswordExpiryDate(new Date());
        this.userDao.save(user);
    }

    /**
     * Update user.
     *
     * @param userDto the user dto
     */
    public void updateUser(final UserDto userDto) {
        final User user = this.userDao.getUser(userDto.getId());
        this.mapUserDtoToModel(userDto, user);
        user.setModifiedByUser(UserAccountUtils.getCurrentUsername());
        user.setModifiedDate(new Date());
        this.userDao.save(user);
    }

    /**
     * Update user param.
     *
     * @param userParamDto the user param dto
     */
    public void updateUserParams(final UserParamDto userParamDto) {
        final UserParam userParam = this.userParamDao.getUserParam();
        userParam.setActivationPeriod(userParamDto.getActivationPeriod());
        userParam.setAllowOldPassword(userParamDto.getAllowOldPassword() ? 1 : 0);
        userParam.setDormancyPeriod(userParamDto.getDormancyPeriod());
        userParam.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
        userParam.setLastModifiedDate(new Date());
        userParam.setOldPasswordExclusionCount(userParamDto.getOldPasswordExclusionCount());
        userParam.setPasswordAge(userParamDto.getPasswordAge());
        this.userParamDao.save(userParam);
    }

    /**
     * Map user param model to dto. Update user status.
     *
     * @param userId     the user id
     * @param userStatus the user status
     */
    public void updateUserStatus(final Integer userId, final UserStatus userStatus) {
        final User user = this.userDao.getUser(userId);
        user.setStatus(userStatus.getId());
        this.userDao.save(user);
    }


    public List<ActiveUserDto> getActiveOtherUsersInCurrentSolId() {
        final List<ActiveUserDto> activeUsers = new ArrayList<>();
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();


        for (final Session session : activeSessions) {
            if (UserAccountUtils.getCurrentSolId().equalsIgnoreCase((String) session.getAttribute(UserConstants.SOL_ID))) {
                if (!UserAccountUtils.getCurrentUserID().equals((Integer) session.getAttribute(UserConstants.USER_ID))) {
                    final ActiveUserDto activeUserDto = new ActiveUserDto();
                    activeUserDto.setHostAddress(session.getHost());
                    activeUserDto.setLastAccessTime(session.getLastAccessTime());
                    activeUserDto.setLoggedInSince(session.getStartTimestamp());
                    activeUserDto.setMaxIdleTime(session.getTimeout());

                    activeUserDto.setUserId((Integer) session.getAttribute(UserConstants.USER_ID));
                    activeUserDto.setUsername((String) session.getAttribute(UserConstants.USERNAME));
                    activeUserDto.setSessionId(session.getId().toString());
                    activeUsers.add(activeUserDto);
                }
            }
        }


        return activeUsers;
    }


    public void modifyUser(UserDto userDto) {
        final User user = this.userDao.getUser(userDto.getId());
        this.mapModifyUserDtoToModel(userDto, user);
        user.setModifiedByUser(UserAccountUtils.getCurrentUsername());
        user.setModifiedDate(new Date());
        this.userDao.update(user);
    }

    private void mapModifyUserDtoToModel(final UserDto userDto, final User user) {
        user.setId(userDto.getId());
        user.setApplicationName(userDto.getApplicationName());
        user.setDepartmentCode(userDto.getDepartmentCode());
        user.setLocationCode(userDto.getLocationCode());
        user.setSolId(userDto.getSolId());
        user.setSupervisorCode(userDto.getSupervisorCode());
        user.setUserType(userDto.getUserType());
        user.setUserExpiryDate(userDto.getUserExpiryDate());
    }

//    public void updateLogOutInfo(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc) {
//        this.userDao.updateLogoutStatusForUsersById(activeUserDtoList.stream().map(ActiveUserDto::getUserId).collect(Collectors.toList()));
//        System.out.println("Shiro Update mgmt Table ");
//        this.userLogDao.updateUserLogAfterLogOut(activeUserDtoList,logOutReason,logOutReasonDesc);
//        System.out.println("Shiro Update log Table ");
//    }

    public void updateLogOutInfo(List<ActiveUserDto> activeUserDtoList, String logOutReason, String logOutReasonDesc) {
        if(activeUserDtoList !=null) {
            this.userDao.updateLogoutStatusForUsersById(activeUserDtoList.stream().map(ActiveUserDto::getUserId).collect(Collectors.toList()));
            System.out.println("Shiro Update mgmt Table ");
        }

        if(!UserLogOutReasons.SESSION_EXPIRED.getReasonName().equalsIgnoreCase(logOutReason)) {
            this.userLogDao.updateUserLogAfterLogOut(activeUserDtoList, logOutReason, logOutReasonDesc);
            System.out.println("Shiro Update log Table ");
        }

    }

    public ResponseDataDTO getUserLogin(String username) {
        return userDao.getUserLogin(username);
    }

    public ResponseDataDTO forceLogoutSingleUser(String username) {
        return userDao.forceLogoutSingleUser(username);
    }
}
