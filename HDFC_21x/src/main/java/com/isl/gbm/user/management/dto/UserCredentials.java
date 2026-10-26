package com.isl.gbm.user.management.dto;


import com.isl.gbm.user.management.model.UserDomain;
import com.isl.gbm.user.management.security.shiro.config.ValidPassword;

/**
 * The Class UserCredentials.
 */
public class UserCredentials {

    /**
     * The username.
     */
    private String username;

    private String isForcingLogoutPreviousUser;

    /**
     * The password.
     */
    @ValidPassword
    private String password;

    /**
     * The user domain.
     */
    private UserDomain userDomain;

    /**
     * The session id.
     */
    private String sessionId;

    /**
     * The is authenticated.
     */
    private Boolean isAuthenticated;

    /**
     * The sol id.
     */
    private String solId;


    public String getIsForcingLogoutPreviousUser() {
        return isForcingLogoutPreviousUser;
    }

    public void setIsForcingLogoutPreviousUser(String isForcingLogoutPreviousUser) {
        this.isForcingLogoutPreviousUser = isForcingLogoutPreviousUser;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return solId;
    }

    /**
     * Sets the sol id.
     *
     * @param solId the new sol id
     */
    public void setSolId(String solId) {
        this.solId = solId;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user domain.
     *
     * @return the user domain
     */
    public UserDomain getUserDomain() {
        return userDomain;
    }

    /**
     * Sets the user domain.
     *
     * @param userDomain the new user domain
     */
    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    /**
     * Gets the session id.
     *
     * @return the session id
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the session id.
     *
     * @param sessionId the new session id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Gets the checks if is authenticated.
     *
     * @return the checks if is authenticated
     */
    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Sets the checks if is authenticated.
     *
     * @param isAuthenticated the new checks if is authenticated
     */
    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserCredentials [username=" + username + ", password=" + password + ", userDomain=" + userDomain
                + ", sessionId=" + sessionId + ", isAuthenticated=" + isAuthenticated + "]";
    }
}
