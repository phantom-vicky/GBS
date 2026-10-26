/*
 * Copyright (c) 2016 Accel Frontline Ltd.
 * All rights reserved.
 */
package com.isl.gbm.user.management.aspects;

import com.isl.gbm.user.management.annotations.UserLog;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.constants.UserLogOutReasons;
import com.isl.gbm.user.management.dto.UserLogDto;
import com.isl.gbm.user.management.service.LogService;
import com.isl.gbm.user.management.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * The Class LoggerAspect.
 */
@Aspect
@Configuration
public class UserLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLogAspect.class);

    /**
     * The log service.
     */
    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

/**
 * Sets the log service.
 *
 * @param logService
 *            the new log service
 */

    /**
     * Defines a pointcut. The pointcut is a catch-all pointcut with the scope of
     * execution
     */
    @Pointcut("execution(* *(..))")
    private void atExecution() {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut is a catch-all pointcut with the scope of execution
    }

    @Pointcut("execution(* com.isl.gbm.user.management.controller.UserLoginController.logout(..))")
    private void atLogout() {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut is a catch-all pointcut with the scope of execution
    }

    /**
     * Defines a pointcut. This will look for the @Loggable in a method
     *
     * @param loggable the loggable
     */
    @Pointcut("@annotation(loggable)")
    private void annotationPointCutDefinition(UserLog loggable) {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut will look for the @Loggable before every method
    }

    /**
     * Defines a pointcut. This will look for the @Loggable in a class
     *
     * @param loggable the loggable
     */
    //@Pointcut("@target(loggable)")
    private void targetsLoggableAnnotation(UserLog loggable) {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut will look for the @Loggable before every class
    }

    /**
     * After loggable method advice.
     *
     * @param joinPoint the join point
     * @param loggable  the loggable
     */
    @After("annotationPointCutDefinition(loggable) && atExecution()")
    public void afterLoggableMethodAdvice(JoinPoint joinPoint, final UserLog loggable) {
        // This advice will be applied after the method execution
    }

    /**
     * After returning loggable method advice.
     *
     * @param joinPoint the join point
     * @param result    the result
     * @param loggable  the loggable
     */
    @AfterReturning(pointcut = "annotationPointCutDefinition(loggable) && atExecution()", returning = "result")
    public void afterReturningLoggableMethodAdvice(JoinPoint joinPoint, Object result, final UserLog loggable) {
        if (verifyAnnotationPresent(joinPoint)) {
            performAfterReturningAdvice(loggable.logType(), result);
        }
    }

    /**
     * Perform after returning advice.
     *
     * @param operation the operation
     * @param result    the result
     * @paramjoinPoint the join point
     */
    @SuppressWarnings("rawtypes")
    private void performAfterReturningAdvice(String operation, Object result) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            UserLogDto userLogDto = getUserLogDto(session, operation);
            if (userLogDto == null) {
                return;
            }
            if ((null != result) && (result instanceof ResponseEntity)) {
                final ResponseEntity entity = (ResponseEntity) result;
                userLogDto.setSuccess(entity.getStatusCode().value() == 200 ? true : false);
                Object body = entity.getBody();
                userLogDto.setResponsePayload(body != null ? body.toString() : null);
            }
            this.logService.saveUserLog(userLogDto);
        }
    }

    /**
     * After throwing loggable method advice.
     *
     * @param joinPoint the join point
     * @param error     the error
     * @param loggable  the loggable
     */
    @AfterThrowing(pointcut = "annotationPointCutDefinition(loggable) && atExecution()", throwing = "error")
    public void afterThrowingLoggableMethodAdvice(JoinPoint joinPoint, Throwable error, final UserLog loggable) {
        if (verifyAnnotationPresent(joinPoint)) {
            performAfterThrowingAdvice(loggable.logType(), error);
        }
    }

    /**
     * Before logout method advice.
     *
     * @param joinPoint the join point
     */
    @Around("atLogout()")
    public ResponseEntity beforeLogoutMethodAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            String logOutReason = String.valueOf(joinPoint.getArgs()[0]);
            if (UserLogOutReasons.NORMAL_LOGOUT.getReasonName().equals(logOutReason)) {
                this.userService.logOutUser();
                this.logService.saveLogoutTime((String) currentUser.getSession().getId(), UserLogOutReasons.NORMAL_LOGOUT.getReasonName(), UserLogOutReasons.NORMAL_LOGOUT.getReasonDesc());
            } else if (UserLogOutReasons.BROWSER_WINDOW_CLOSED.getReasonName().equals(logOutReason)) {
                this.userService.logOutUser();
                this.logService.saveLogoutTime((String) currentUser.getSession().getId(), UserLogOutReasons.BROWSER_WINDOW_CLOSED.getReasonName(), UserLogOutReasons.BROWSER_WINDOW_CLOSED.getReasonDesc());
            } else if (UserLogOutReasons.SESSION_EXPIRED.getReasonName().equals(logOutReason)) {
                this.userService.logOutUser();
                this.logService.saveLogoutTime((String) currentUser.getSession().getId(), UserLogOutReasons.SESSION_EXPIRED.getReasonName(), UserLogOutReasons.SESSION_EXPIRED.getReasonDesc());
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return (ResponseEntity) joinPoint.proceed();
    }

    /**
     * Perform after throwing advice.
     *
     * @param operation the operation
     * @param error     the error
     */
    private void performAfterThrowingAdvice(String operation, Throwable error) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            UserLogDto userLogDto = getUserLogDto(session, operation);
            if (userLogDto == null) {
                return;
            }
            userLogDto.setSuccess(false);
            userLogDto.setResponsePayload(error.getMessage());
            this.logService.saveUserLog(userLogDto);
        }
    }

    /**
     * Gets the user log dto.
     *
     * @param session   the session
     * @param operation the operation
     * @return the user log dto
     */
    private UserLogDto getUserLogDto(Session session, String operation) {
        Object username = session.getAttribute(UserConstants.USERNAME);
        Object userAgent = session.getAttribute(UserConstants.USER_AGENT);
        if (username == null || userAgent == null) {
            LOGGER.debug("Skipping user log for operation {} because session username or user-agent is missing", operation);
            return null;
        }
        Subject currentUser = SecurityUtils.getSubject();
        UserLogDto userLogDto = new UserLogDto();
        userLogDto.setUsername(username.toString());
        userLogDto.setUserAgent(userAgent.toString());
        userLogDto.setLogType(operation);
        userLogDto.setIpAddress(session.getHost());
        userLogDto.setCreatedTime(new Date());
        userLogDto.setUserId((Integer) session.getAttribute(UserConstants.USER_ID));
        userLogDto.setSolId((String) session.getAttribute(UserConstants.SOL_ID));
        userLogDto.setSessionId(currentUser.getSession() != null ? (String) currentUser.getSession().getId() : null);
        return userLogDto;
    }

    /**
     * Verify whether Loggable annotation is present or not.
     *
     * @param joiningPoint the joining point
     * @return true, if successful
     */
    private boolean verifyAnnotationPresent(final JoinPoint joiningPoint) {
        final Method method = MethodSignature.class.cast(joiningPoint.getSignature()).getMethod();
        //return method.isAnnotationPresent(UserLog.class);
        return true;
    }
}