package com.isl.gbm.admin.model;


import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "gbm_user_profile")
@DynamicUpdate
@SelectBeforeUpdate
public class SackUserMaster implements Serializable {


    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SACK_ID_GEN")
    @SequenceGenerator(name = "SEQ_SACK_ID_GEN", sequenceName = "SEQ_SACK_ID_GEN", allocationSize = 1)
    private Integer id;

    /**
     * The sol id.
     */
    @Column(name = "SOL_ID")
    private String solId;

    /**
     * The user id.
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * The is deleted.
     */
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    /**
     * The in time.
     */
    @Column(name = "IN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inTime;

    /**
     * The out time.
     */
    @Column(name = "OUT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
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
