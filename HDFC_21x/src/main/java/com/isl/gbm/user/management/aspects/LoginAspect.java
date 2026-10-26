package com.isl.gbm.user.management.aspects;

import com.isl.gbm.Utils.CommonConstants;
import com.isl.gbm.cbs.integration.service.OtherBankDetailsService;
import com.isl.gbm.cbs.integration.service.SolDateIntegrationService;
import com.isl.gbm.cbs.integration.service.SolDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.exception.GenericBusinessException;
import com.isl.gbm.rbibond.exceptions.AccountTerminatedException;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.model.UserDomain;
import com.isl.gbm.user.management.model.UserStatus;
import com.isl.gbm.user.management.service.PermissionAdministrationService;
import com.isl.gbm.user.management.service.UserAdministrationService;
import com.isl.gbm.user.management.service.UserService;
import com.isl.gbm.user.management.validator.UserAdministrationValidator;
import com.isl.gbm.user.management.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * The Class LoginAspect.
 */
@Aspect
@Configuration
public class LoginAspect {

    private static final Logger Log = LoggerFactory.getLogger(LoginAspect.class);

    @Value("${shiro.autheciation.realm.type}")
    private String relamType;
    /**
     * The user administration service.
     */
    @Autowired
    private UserAdministrationService userAdministrationService;

    /**
     * The user administration validator.
     */
    @Autowired
    private UserAdministrationValidator userAdministrationValidator;

    /**
     * The user validator.
     */
    @Autowired
    private UserValidator userValidator;

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * The user permission service.
     */
    @Autowired
    private PermissionAdministrationService permissionAdministrationService;


    @Autowired
    OtherBankDetailsService otherBankDetailsService;

    @Autowired
    SolDateIntegrationService solDateIntegrationService;

    @Autowired
    SolDetailsIntegrationService solDetailsIntegrationService;

    @Qualifier("customCsrfTokenRepository")
    @Autowired
    private CsrfTokenRepository csrfTokenRepository;


    @Value("${gbm.bankName}")
    private String bankName;


    @Pointcut("execution(* com.isl.gbm.user.management.controller.UserLoginController.login(..))")
    private void atExecution() {
        // Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
        // @AfterReturning,@Around specifications
        // The pointcut is a catch-all pointcut with the scope of execution
    }

    /**
     * Before login method advice.
     *
     * @param joinPoint the join point
     */
    @Before(" atExecution()")
    public void beforeLoginMethodAdvice(JoinPoint joinPoint) {
        Session session = SecurityUtils.getSubject().getSession();
        this.setUserSession(session, joinPoint);
        UserCredentials userCredentials = (UserCredentials) joinPoint.getArgs()[0];
        Integer userId = (Integer) session.getAttribute(UserConstants.USER_ID);
        Log.debug("Before login aspect userId={}", userId);

        if (userId != null && UserDomain.STAND_ALONE.equals(userCredentials.getUserDomain())) {
            String userStatus = (String) session.getAttribute(UserConstants.STATUS);

            UserDetailsValidationDTO userDetailsValidationDTO = this.userValidator.getUserValidationDetailsForLogin();
            // CHECK WHETHER USER ACTIVATION PERIOD/DORMANT TIME IS EXPIRED
            if (UserStatus.INACTIVE.getStatus().equals(userStatus) || (UserStatus.DISABLED.getStatus().equals(userStatus))) {
                throw  new DisabledAccountException("Account is Disabled");
            }
            if(UserStatus.DELETED.getStatus().equals(userStatus)){
                throw new GenericBusinessException("Account is Dormant");
            }
            if(UserStatus.TERMINATED.getStatus().equals(userStatus)){
                throw new AccountTerminatedException("Account is Terminated");
            }
            if(UserStatus.DEACTIVE.getStatus().equals(userStatus)){
                throw new GenericBusinessException("Account is Deactivated");
            }

            // FOR CONTRACT USER, CHECK WHETHER USER IS EXPIRED
            if (BooleanUtils.isTrue(userDetailsValidationDTO.getUserExpied())) {


                this.userService.setAccountExpired();
            }

            // FOR DISABLED USER, CHECK WHETHER BLOCK TIME IS OVER
            if (UserStatus.DISABLED.getStatus().equals(userStatus) && BooleanUtils.isTrue(userDetailsValidationDTO.getUserBlockTimeOver())) {

                this.userService.enableAccount();
            }
        } else if (userId != null && UserDomain.FINACLE.equals(userCredentials.getUserDomain())) {
            session.setAttribute(UserConstants.SOL_ID, userCredentials.getSolId());

        }
//        } else {
//            Session session = SecurityUtils.getSubject().getSession();
//            if(session != null){
//                this.setUserSession(session, joinPoint);
//                UserCredentials userCredentials = (UserCredentials) joinPoint.getArgs()[0];
//                Integer userId = (Integer) session.getAttribute(UserConstants.USER_ID);
//                session.setAttribute(UserConstants.SOL_ID, userCredentials.getSolId());
//            }
//        }
    }

    /**
     * Sets the user session.
     *
     * @param session   the session
     * @param joinPoint the new user session
     */
    private void setUserSession(Session session, JoinPoint joinPoint) {
        UserCredentials userCredentials = (UserCredentials) joinPoint.getArgs()[0];
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[1];
        session.setAttribute(UserConstants.USERNAME, userCredentials.getUsername());
        session.setAttribute(UserConstants.USER_AGENT, request.getHeader(UserConstants.USER_AGENT));
        // if (this.userAdministrationValidator.isUsernameExists(userCredentials.getUsername())) {
        UserDto userDto = this.userAdministrationService.getUserByUsername(userCredentials.getUsername());
        ClientDetailsDTO clientDetailsDTO = this.userService.getClientInfo();
        if(userDto!=null){
            session.setAttribute(UserConstants.USER_ID, userDto.getId());
            session.setAttribute(UserConstants.STATUS, userDto.getStatus());
            session.setAttribute(UserConstants.SOL_ID, userDto.getSolId());
            session.setAttribute(UserConstants.USER_DOMAIN, userDto.getUserDomain());
            if(userDto.getPasswordExpiryDate()!=null)
                session.setAttribute(UserConstants.PASSWORD_EXPIRED_STATUS,new Date().after(userDto.getPasswordExpiryDate()));


            //new Date().after(userDto.getPasswordExpiryDate());

        }
        if (clientDetailsDTO != null) {
            session.setAttribute(UserConstants.CLIENT_ID, clientDetailsDTO.getClientId());
            session.setAttribute(UserConstants.CLIENT_SECRET, clientDetailsDTO.getClientSecret());
        }
    }

    /**
     * After returning login method advice.
     *
     * @param joinPoint the join point
     * @param result    the result
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @AfterReturning(pointcut = "atExecution()", returning = "result")
    public void afterReturningLoginMethodAdvice(JoinPoint joinPoint, Object result) {
        try {
            ResponseEntity<ResponseDataDTO> responseEntity = (ResponseEntity) result;
            if (responseEntity.getStatusCode() != HttpStatus.OK)
            {return;}

            Session session = SecurityUtils.getSubject().getSession(true);
            this.setUserSession(session, joinPoint);

                String userStatus = (String) session.getAttribute(UserConstants.STATUS);
                Object userDomain = session.getAttribute(UserConstants.USER_DOMAIN);
                Map<String, Object> redirect = new HashMap<>();

            if (UserDomain.FINACLE.getDomain().equals(userDomain)
                    || UserStatus.ACTIVE.getStatus().equals(userStatus)) {
                redirect.put(UserConstants.REDIRECT_TO, UserConstants.LOC_HOME);
                Object username = session.getAttribute(UserConstants.USERNAME);
                Object solId = session.getAttribute(UserConstants.SOL_ID);
                redirect.put(UserConstants.USERNAME, username != null ? String.valueOf(username) : null);
                redirect.put(UserConstants.SOL_ID, solId != null ? String.valueOf(solId) : null);
                redirect.put(UserConstants.CLIENT_ID, (String) session.getAttribute(UserConstants.CLIENT_ID));
                redirect.put(UserConstants.CLIENT_SECRET, (String) session.getAttribute(UserConstants.CLIENT_SECRET));
                ResponseDataDTO postLoginResponse = userService.postLoginUserDetails();
                if (postLoginResponse != null
                        && CommonConstants.SUCCESS_CONST.equals(postLoginResponse.getMessage())
                        && postLoginResponse.getResult() instanceof PostLoginUserDetailsDTO) {
                    PostLoginUserDetailsDTO postLoginUserDetailsDTO =
                            (PostLoginUserDetailsDTO) postLoginResponse.getResult();
                    redirect.put(UserConstants.LAST_LOGIN_TIME, postLoginUserDetailsDTO.getLastLoginSuccessTime());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                    Date date = sdf.parse(postLoginUserDetailsDTO.getSolDate());
                    redirect.put(UserConstants.solDate, String.valueOf(date.getTime()));
                    redirect.put(UserConstants.solDesc, postLoginUserDetailsDTO.getSolDesc());
                    redirect.put(UserConstants.PERMISSIONS, postLoginUserDetailsDTO.getPermissionList());
                    redirect.put(UserConstants.MENU_LINKS, postLoginUserDetailsDTO.getMenuLinksList());
                    redirect.put(UserConstants.BANK_NAME, bankName);
                }
            }

            session.removeAttribute(UserConstants.PASSWORD_EXPIRED_STATUS);

            ResponseDataDTO responseDataDTO = responseEntity.getBody();
            if (responseDataDTO != null) {
                responseDataDTO.setResult(redirect);
            }

            issueCsrfToken((HttpServletRequest) joinPoint.getArgs()[1],
                    (HttpServletResponse) joinPoint.getArgs()[2]);
        } catch (Exception e) {
            Log.error("Failed to complete post-login session setup", e);
        }
    }

    private void issueCsrfToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            CsrfToken csrfToken = csrfTokenRepository.generateToken(request);
            csrfTokenRepository.saveToken(csrfToken, request, response);
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        } catch (Exception e) {
            Log.error("Failed to issue CSRF token after login", e);
        }
    }

    /**
     * After throwing loggable class advice.
     *
     * @param joinPoint the join point
     * @param error     the error
     */
    @AfterThrowing(pointcut = "atExecution()", throwing = "error")
    public void afterThrowingLoggableClassAdvice(JoinPoint joinPoint, Throwable error) {
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null && session.getAttribute(UserConstants.USER_ID) != null) {
            this.userService.updateUserLastLoginAttempt();
        }
    }

}
