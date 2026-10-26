package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.BusinessException;
import com.isl.gbm.user.management.annotations.UserLog;
import com.isl.gbm.user.management.constants.*;
import com.isl.gbm.user.management.dao.ActiveUserDao;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.dto.UserCredentials;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.security.shiro.config.AccountExpiredException;
import com.isl.gbm.user.management.security.shiro.config.FinacleNotIntegratedException;
import com.isl.gbm.user.management.service.UserAdministrationService;
import com.isl.gbm.user.management.util.APIResponseBuilder;
import com.isl.gbm.user.management.util.UserAccountUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/gbmultra/user")
public class UserLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserAdministrationService userAdministrationService;
    @Autowired
    private ActiveUserDao activeUserDao;

    /**
     * Login.
     *
     * @param userCredentials the user credentials
     * @return the response entity
     */
    @PostMapping("/login")
    @UserLog(logType = UserMgmtOperations.LOGIN)
    public ResponseEntity<ResponseDataDTO> login(@RequestBody UserCredentials userCredentials,
                                                 HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        Subject currentUser = SecurityUtils.getSubject();
        User user=new User();
        try {
            getActiveUsers(currentUser,  userCredentials);
            if(userCredentials.getIsForcingLogoutPreviousUser() == null || userCredentials.getIsForcingLogoutPreviousUser().equals("")){

                UsernamePasswordToken token = new UsernamePasswordToken(userCredentials.getUsername(),
                        UserAccountUtils.decodeAesPassword(userCredentials.getPassword()), false);
                LOGGER.info("User logging in: " + userCredentials.getUsername());
                user=activeUserDao.validateUserStatus(userCredentials.getUsername());
                if(null!=user&&null!=user.getStatus()&&user.getStatus().equals("T01")) {
                    return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ACCOUNT_TERMINATED, null,
                            HttpStatus.UNAUTHORIZED);                }
                if(null!=user&&null!=user.getStatus()&&user.getStatus().equals("D02")) {
                    throw new DisabledAccountException("Failed to logout the user");
                }if(null!=user&&null!=user.getStatus()&&user.getStatus().equals("RD")) {
                    return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ROLE_ACCOUNT_DISABLED, null,
                            HttpStatus.UNAUTHORIZED);
                }
                else {
                    currentUser.login(token);
                    LOGGER.info("User logged in: " + userCredentials.getUsername());
                }
            }
        } catch (FinacleNotIntegratedException e) {
            LOGGER.info("FinacleNotIntegratedException: " + e.getMessage());
            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.FINACLE_NOT_YET_INTEGRATED, null,
                    HttpStatus.UNAUTHORIZED);
        } catch (BusinessException e) {
            LOGGER.info("BusinessException: " + e.getMessage());
            System.out.println("Active Users  BusinessException: " + e.getMessage());
//            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ALREADY_LOGGED_IN, null,
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), null,
                    HttpStatus.UNAUTHORIZED);
        } catch (ExcessiveAttemptsException | LockedAccountException e) {
            LOGGER.info("ExcessiveAttemptsException (or) LockedAccountException: " + e.getMessage());
            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ACCOUNT_LOCKED, null,
                    HttpStatus.UNAUTHORIZED);
        } catch (DisabledAccountException e) {
            LOGGER.info("DisabledAccountException: " + e.getMessage());
            if(null!=user&&null!=user.getStatus()&&user.getStatus().equals("D02")){
            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ACCOUNT_DORMANT, null,
                    HttpStatus.UNAUTHORIZED);
            }else{
             return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ACCOUNT_DISABLED, null,
                        HttpStatus.UNAUTHORIZED);
            }

        }
        catch (AccountExpiredException e) {
            LOGGER.info("AccountExpiredException: " + e.getMessage());
            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.ACCOUNT_EXPIRED, null,
                    HttpStatus.UNAUTHORIZED);
        } catch (IncorrectCredentialsException | UnknownAccountException e) {
            LOGGER.info("IncorrectCredentialsException (or) UnknownAccountException: " + e.getMessage());
            return APIResponseBuilder.buildErrorResponse(UserMgmtExceptionMessages.INVALID_CREDENTIALS, null,
                    HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            LOGGER.info("AccountExpiredException: " + e.getMessage());
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), null, HttpStatus.FORBIDDEN);
        }
        LOGGER.info("User Logged in Successfully");
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_USER_LOGIN),
                HttpStatus.OK);
    }

    /**
     * Logout.
     *
     * @return the response entity
     */
    @GetMapping("/logout")
    public ResponseEntity<ResponseDataDTO> logout(@RequestParam("logOutReason") String logOutReason) {     //logOut Reason For Log Aspect
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
            LOGGER.info("User Logged out");
            return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_USER_LOGOUT),
                    HttpStatus.OK);
        }
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.USER_ALREADY_LOGOUT),
                HttpStatus.OK);
    }


    //    @PreDestroy
    public void logOutActiveUsers() {
        final List<ActiveUserDto> activeUsers = new ArrayList<>();
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();
        this.userAdministrationService.updateLogOutInfo(activeSessions.stream().map(session -> new ActiveUserDto((Integer) session.getAttribute(UserConstants.USER_ID), (String) session.getAttribute(UserConstants.SESSION_ID), (String) session.getAttribute(UserConstants.USERNAME))).collect(Collectors.toList()), UserLogOutReasons.MIDDLEWARE_SHUTDOWN.getReasonName(), UserLogOutReasons.MIDDLEWARE_SHUTDOWN.getReasonDesc());
    }

    public void getActiveUsers(Subject currentUser, UserCredentials userCredentials) throws BusinessException {
        if(userCredentials.getIsForcingLogoutPreviousUser() != null
                && userCredentials.getIsForcingLogoutPreviousUser().equalsIgnoreCase("Y")){
            LOGGER.info("forceLogin: " + userCredentials.getUsername());
            this.forceLogin(currentUser, userCredentials);
        }else{
            ResponseDataDTO responseDataDTO = userAdministrationService.getUserLogin(userCredentials.getUsername());
            if (responseDataDTO.getMessage().equalsIgnoreCase(com.isl.gbm.common.util.CommonConstants.SUCCESS_CONST)) {
                String flag = (String) responseDataDTO.getResult();
                LOGGER.info("Checking Login Flag: " + userCredentials.getUsername());
                if (flag.equalsIgnoreCase("Y")) {
                    LOGGER.info("FORCING_LOGOUT initiated: " + userCredentials.getUsername());
                    throw new BusinessException("FORCING_LOGOUT");
                }
            } else {
                LOGGER.info(ErrorMessages.DATABASE_ERROR);
                throw new BusinessException(ErrorMessages.DATABASE_ERROR);
            }
        }
    }
    public void forceLogin(Subject currentUser, UserCredentials userCredentials) throws BusinessException {
        List<ActiveUserDto> activeUsers = userAdministrationService.getActiveUsers();
      if(null!=activeUsers && activeUsers.size()>0){
        for(ActiveUserDto user : activeUsers){
            if(user.getUsername() != null){
                if(user.getUsername().equals(userCredentials.getUsername())){
                    if(!currentUser.getSession().getId().equals(user.getSessionId())){
                        System.out.println("Killing session: " + user.getUsername() + " with session id " + user.getSessionId());
                        userAdministrationService.killSingleUserForceLogout(user);
                    }
                }
            }
        }}
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
        userCredentials.setIsForcingLogoutPreviousUser("N");
        ResponseDataDTO responseDataDTO = userAdministrationService.forceLogoutSingleUser(userCredentials.getUsername());
        if(responseDataDTO.getMessage().equalsIgnoreCase(com.isl.gbm.common.util.CommonConstants.SUCCESS_CONST)){
            LOGGER.info("Successfully Logged out previous user");
            throw new BusinessException("Successfully Logged out previous user");
        }else{
            LOGGER.info(ErrorMessages.DATABASE_ERROR);
            throw new BusinessException("Failed to logout the user");
        }
    }


}
