package com.isl.gbm.user.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Date;

/**
 * The Class AdminLog.
 */
@Entity
@Table(name = "GBM_USER_MGMT_ADMINISTER_LOG")
public class AdminLog {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "adminLogSequence", sequenceName = "SEQ_USER_MGMT_ADMINISTER_LOG", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adminLogSequence")
    private Integer id;

    /**
     * The user id.
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * The log type.
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * The created time.
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * The ip address.
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * The success.
     */
    @Column(name = "success")
    private Integer success;

    /**
     * The username.
     */
    @Column(name = "username")
    private String username;

    /**
     * The user agent.
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * The request body.
     */
    @Column(name = "request_payload")
    private String requestPayload;

    /**
     * The response body.
     */
    @Column(name = "response_payload")
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
    public Integer getSuccess() {
        return success;
    }

    /**
     * Sets the success.
     *
     * @param success the new success
     */
    public void setSuccess(Integer success) {
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
