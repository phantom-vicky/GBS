package com.isl.gbm.admin.dto;

import java.util.Date;

public class SackUserDto {

    /**
     * The id.
     */
    private Integer id;

    /**
     * The sol id.
     */
    private String solId;

    /**
     * The user id.
     */
    private String userId;

    /**
     * The is deleted.
     */
    private Boolean isDeleted;

    /**
     * The in time.
     */
    private Date inTime;

    /**
     * The out time.
     */
    private Date outTime;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the in time.
     *
     * @return the in time
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * Gets the out time.
     *
     * @return the out time
     */
    public Date getOutTime() {
        return outTime;
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
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Checks if is deleted.
     *
     * @return true, if is deleted
     */
    public Boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted.
     *
     * @param isDeleted the new deleted
     */
    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
     * Sets the in time.
     *
     * @param inTime the new in time
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * Sets the out time.
     *
     * @param outTime the new out time
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
