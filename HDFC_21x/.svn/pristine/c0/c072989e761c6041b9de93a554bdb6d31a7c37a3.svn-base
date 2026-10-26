package com.isl.gbm.user.management.util;

import com.isl.gbm.common.util.AESUtils;
import com.isl.gbm.user.management.constants.UserConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.nio.charset.Charset;
import java.util.Base64;


/**
 * The Class UserAccountUtils.
 */
public class UserAccountUtils {

    /**
     * Instantiates a new user account utils.
     */
    private UserAccountUtils() {
    }

    /**
     * Encrypt password.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(decodeAesPassword(password), BCrypt.gensalt(12));
    }

    /**
     * Decode password.
     *
     * @param password the password
     * @return the string
     */
    public static String decodePassword(String password) throws IncorrectCredentialsException {
        try {
            return new String(Base64.getDecoder().decode(password), Charset.forName("UTF-8"));
        } catch (IllegalArgumentException e) {
            throw new IncorrectCredentialsException();
        }

    }


    public static String decodeAesPassword(String password) throws IncorrectCredentialsException {
        try {

            return AESUtils.decrypt(password) ;


            //return new String(Base64.getDecoder().decode(password), Charset.forName("UTF-8"));
        } catch (IllegalArgumentException e) {
            throw new IncorrectCredentialsException();
        }

    }


    public static String encodeAesPassword(String password) throws IncorrectCredentialsException {
        try {

            return AESUtils.encrypt(password) ;


            //return new String(Base64.getDecoder().decode(password), Charset.forName("UTF-8"));
        } catch (IllegalArgumentException e) {
            throw new IncorrectCredentialsException();
        }

    }

    /**
     * Gets the current user ID.
     *
     * @return the current user ID
     */
    public static Integer getCurrentUserID() {
        return (Integer) SecurityUtils.getSubject().getSession().getAttribute(UserConstants.USER_ID);
    }

    /**
     * Gets the current username.
     *
     * @return the current username
     */
    public static String getCurrentUsername() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute(UserConstants.USERNAME);
    }

    /**
     * Gets the current sol id.
     *
     * @return the current sol id
     */
    public static String getCurrentSolId() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute(UserConstants.SOL_ID);
    }

    public static String getClientId() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute(UserConstants.CLIENT_ID);
    }

    public static String getClientSecret() {
        return (String) SecurityUtils.getSubject().getSession().getAttribute(UserConstants.CLIENT_SECRET);
    }


    public static String getSolDesc() {
        return (String) SecurityUtils.getSubject()
                .getSession()
                .getAttribute("SOL_DESC");
    }


}
