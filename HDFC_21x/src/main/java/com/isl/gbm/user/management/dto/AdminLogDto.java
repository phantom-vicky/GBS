package com.isl.gbm.user.management.dto;

import java.util.Date;

/**
 * The Class AdminLogDto.
 */
public class AdminLogDto {

    /**
     * The id.
     */
    private Integer id;

    /**
     * The user id.
     */
    private Integer userId;

    /**
     * The log type.
     */
    private String logType;

    /**
     * The created time.
     */
    private Date createdTime;

    /**
     * The browser type.
     */
    private String browserType;

    /**
     * The ip address.
     */
    private String ipAddress;

    /**
     * The success.
     */
    private Boolean success;

    /**
     * The username.
     */
    private String username;

    /**
     * The user agent.
     */
    private String userAgent;

    /**
     * The request payload.
     */
    private String requestPayload;

    /**
     * The response payload.
     */
    private String responsePayload;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the log type.
     *
     * @return the log type
     */
    public String getLogType() {
        return logType;
    }

    /**
     * Sets the log type.
     *
     * @param logType the new log type
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * Gets the created time.
     *
     * @return the created time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the created time.
     *
     * @param createdTime the new created time
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Gets the browser type.
     *
     * @return the browser type
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * Sets the browser type.
     *
     * @param browserType the new browser type
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    /**
     * Gets the ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the ip address.
     *
     * @param ipAddress the new ip address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets the success.
     *
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets the success.
     *
     * @param success the new success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
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
     * Gets the user agent.
     *
     * @return the user agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the user agent.
     *
     * @param userAgent the new user agent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Gets the request payload.
     *
     * @return the request payload
     */
    public String getRequestPayload() {
        return requestPayload;
    }

    /**
     * Sets the request payload.
     *
     * @param requestPayload the new request payload
     */
    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }

    /**
     * Gets the response payload.
     *
     * @return the response payload
     */
    public String getResponsePayload() {
        return responsePayload;
    }

    /**
     * Sets the response payload.
     *
     * @param responsePayload the new response payload
     */
    public void setResponsePayload(String responsePayload) {
        this.responsePayload = responsePayload;
    }


}
