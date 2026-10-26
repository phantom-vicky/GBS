package com.isl.gbm.user.management.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The Class UserConstants.
 */
public class UserConstants {

    /**
     * Instantiates a new user constants.
     */
    private UserConstants() {

    }

    /**
     * The Constant USERNAME.
     */
    public static final String USERNAME = "username";

    public static final String PASSWORD_EXPIRED_STATUS = "isPwdExpired";

    /**
     * The Constant USER_ID.
     */
    public static final String USER_ID = "userId";

    /**
     * The Constant USER_AGENT.
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * The Constant STATUS.
     */
    public static final String STATUS = "status";
    public static final String PASSWORD = "password";

    /**
     * The Constant REDIRECT_TO.
     */
    public static final String REDIRECT_TO = "redirectTo";

    /**
     * The Constant SOL_ID.
     */
    public static final String SOL_ID = "solId";

    /**
     * The Constant HOME.
     */
    public static final String LOC_HOME = "home";

    /**
     * The Constant RESET_PASSWORD.
     */
    public static final String LOC_RESET_PASSWORD = "reset_password";    //NOSONAR

    /**
     * The Constant PASSWORD_HISTORY_LIMIT.
     */
    public static final int PASSWORD_HISTORY_LIMIT = 5;

    /**
     * The Constant PARENT_PERMISSIONS_COUNT.
     */
    public static final int PARENT_PERMISSIONS_COUNT = 2;

    /**
     * The Constant PARENT_PERMISISONS.
     */
    public static final List<String> PARENT_PERMISISONS = Collections.unmodifiableList(Arrays.asList(PermissionConstants.USER_MGMT_PERMISSION_ADD, PermissionConstants.USER_MGMT_PERMISSION_ENQUIRY));

    /**
     * The Constant LAST_LOGIN_TIME.
     */
    public static final String LAST_LOGIN_TIME = "lastLoginTime";

    /**
     * The Constant USER_DOMAIN.
     */
    public static final String USER_DOMAIN = "userDomain";

    /**
     * The Constant SESSION_ID.
     */
    public static final String SESSION_ID = "sessionId";

    /**
     * The Constant IS_AUTHENTICATED.
     */
    public static final String IS_AUTHENTICATED = "isAuthenticated";

    public static final String solDate = "solDate";

    public static final String solDesc = "solDesc";

    public static final String PERMISSIONS = "permissions";

    public static final String MENU_LINKS = "menuLinks";

    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_SECRET = "clientSecret";

    public static final String BANK_NAME = "bankName";
    public static final String AGREEMENT_STATUS = "agrStatus";


}
