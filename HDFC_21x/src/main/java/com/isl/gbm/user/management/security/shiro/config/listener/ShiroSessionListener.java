package com.isl.gbm.user.management.security.shiro.config.listener;

import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.constants.UserLogOutReasons;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.service.UserAdministrationService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Component(value = "customShiroListener")
public class ShiroSessionListener implements SessionListener {
    @Autowired
    @Lazy
    private UserAdministrationService userAdministrationService;
    private static final Logger log = LoggerFactory.getLogger(ShiroSessionListener.class);


    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
        this.userAdministrationService.updateLogOutInfo(Arrays.asList(new ActiveUserDto((Integer) session.getAttribute(UserConstants.USER_ID), (String) session.getAttribute(UserConstants.SESSION_ID), (String) session.getAttribute(UserConstants.USERNAME))), UserLogOutReasons.SESSION_EXPIRED.getReasonName(),UserLogOutReasons.SESSION_EXPIRED.getReasonDesc());
        log.info("session stopped");

    }

    @Override
    public void onExpiration(Session session) {

        sessionCount.decrementAndGet();
        this.userAdministrationService.updateLogOutInfo(Arrays.asList(new ActiveUserDto((Integer) session.getAttribute(UserConstants.USER_ID), (String) session.getAttribute(UserConstants.SESSION_ID), (String) session.getAttribute(UserConstants.USERNAME))), UserLogOutReasons.SESSION_EXPIRED.getReasonName(),UserLogOutReasons.SESSION_EXPIRED.getReasonDesc());
        log.info("session expired");
    }
}