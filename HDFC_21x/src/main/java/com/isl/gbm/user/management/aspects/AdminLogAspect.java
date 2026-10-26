/*
 * Copyright (c) 2016 Accel Frontline Ltd.
 * All rights reserved.
 */
package com.isl.gbm.user.management.aspects;

import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.dto.AdminLogDto;
import com.isl.gbm.user.management.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Date;


/**
 * The Class LoggerAspect.
 */
@Aspect
@Configuration
public class AdminLogAspect {

    /**
     * The log service.
     */
    @Autowired
    private LogService logService;

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

    /**
     * Defines a pointcut. This will look for the @AdminLog in a method
     *
     * @param loggable the loggable
     */
    @Pointcut("@annotation(loggable)")
    private void annotationPointCutDefinition(AdminLog loggable) {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut will look for the @Loggable before every method
    }

    /**
     * Defines a pointcut. This will look for the @AdminLog in a class
     *
     * @param loggable the loggable
     */
    @Pointcut("@target(loggable)")
    private void targetsLoggableAnnotation(AdminLog loggable) {
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
    public void afterLoggableMethodAdvice(JoinPoint joinPoint, final AdminLog loggable) {
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
    public void afterReturningLoggableMethodAdvice(JoinPoint joinPoint, Object result, final AdminLog loggable) {
        if (verifyAnnotationPresent(joinPoint)) {
            performAfterReturningAdvice(joinPoint, loggable.logType(), result);
        }
    }

    /**
     * Perform after returning advice.
     *
     * @param operation the operation
     * @param joinPoint the join point
     * @param result    the result
     */
    @SuppressWarnings("rawtypes")
    private void performAfterReturningAdvice(JoinPoint joinPoint, String operation, Object result) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            AdminLogDto adminLogDto = getAdminLogDto(session, joinPoint, operation);

            if ((null != result) && (result instanceof ResponseEntity)) {
                final ResponseEntity entity = (ResponseEntity) result;
                adminLogDto.setSuccess(entity.getStatusCode().value() == 200 ? true : false);
                adminLogDto.setResponsePayload(entity.getBody().toString());
            }
            this.logService.saveAdminLog(adminLogDto);
        }
    }

    /**
     * Gets the admin log dto.
     *
     * @param session   the session
     * @param joinPoint the join point
     * @param operation the operation
     * @return the admin log dto
     */
    private AdminLogDto getAdminLogDto(Session session, JoinPoint joinPoint, String operation) {
        AdminLogDto adminLogDto = new AdminLogDto();
        adminLogDto.setUsername(session.getAttribute(UserConstants.USERNAME).toString());
        adminLogDto.setUserAgent(session.getAttribute(UserConstants.USER_AGENT).toString());
        adminLogDto.setLogType(operation);
        adminLogDto.setIpAddress(session.getHost());
        adminLogDto.setCreatedTime(new Date());
        adminLogDto.setUserId((Integer) session.getAttribute(UserConstants.USER_ID));
        String requestPayload = "";
        for (Object arg : joinPoint.getArgs()) {
            requestPayload = requestPayload + (arg != null ? arg.toString() + " ," : "");
        }
        adminLogDto.setRequestPayload(requestPayload);
        return adminLogDto;
    }

    /**
     * After throwing loggable method advice.
     *
     * @param joinPoint the join point
     * @param error     the error
     * @param loggable  the loggable
     */
    @AfterThrowing(pointcut = "annotationPointCutDefinition(loggable) && atExecution()", throwing = "error")
    public void afterThrowingLoggableMethodAdvice(JoinPoint joinPoint, Throwable error, final AdminLog loggable) {
        if (verifyAnnotationPresent(joinPoint)) {
            performAfterThrowingAdvice(joinPoint, loggable.logType(), error);
        }
    }

    /**
     * Perform after throwing advice.
     *
     * @param joinPoint the join point
     * @param error     the error
     * @paramlogType the log type
     */
    private void performAfterThrowingAdvice(JoinPoint joinPoint, String operation, Throwable error) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            AdminLogDto adminLogDto = getAdminLogDto(session, joinPoint, operation);
            adminLogDto.setSuccess(false);
            adminLogDto.setResponsePayload(error.getMessage());
            this.logService.saveAdminLog(adminLogDto);
        }
    }

    /**
     * Verify whether Loggable annotation is present or not.
     *
     * @param joiningPoint the joining point
     * @return true, if successful
     */
    private boolean verifyAnnotationPresent(final JoinPoint joiningPoint) {
        final Method method = MethodSignature.class.cast(joiningPoint.getSignature()).getMethod();
        return method.isAnnotationPresent(AdminLog.class);
    }
}