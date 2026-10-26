package com.isl.gbm.common.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "gbm_sub_code_master")
public class GbmSubCodeMaster {


    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The sub code.
     */
    @Id
    @Column(name = "SUB_CODE")
    private String subCode;

    /**
     * The code.
     */
    @Column(name = "CODE")
    private String code;
    /**
     * The sub code description.
     */
    @Column(name = "SUB_CODE_DESC")
    private String subCodeDescription;
    /**
     * The created by.
     */
    @Column(name = "CREATED_BY")
    private String createdBy;
    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    /**
     * The last modified by.
     */
    @Column(name = "LAST_MOD_BY")
    private String lastModifiedBy;
    /**
     * The last modified date.
     */
    @Column(name = "LAST_MOD_DATE")
    private Date lastModifiedDate;
    /**
     * The is deleted.
     */
    @Column(name = "IS_DELETED")
    private Character deleted;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Character getDeleted() {
        return this.deleted;
    }

    /**
     * Gets the last modified by.
     *
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the lastModifiedDate
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the sub code.
     *
     * @return the subCode
     */
    public String getSubCode() {
        return this.subCode;
    }

    /**
     * Gets the sub code description.
     *
     * @return the subCodeDescription
     */
    public String getSubCodeDescription() {
        return this.subCodeDescription;
    }

    /**
     * Sets the code.
     *
     * @param code the code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(final Character deleted) {
        this.deleted = deleted;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the lastModifiedDate to set
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the sub code.
     *
     * @param subCode the subCode to set
     */
    public void setSubCode(final String subCode) {
        this.subCode = subCode;
    }

    /**
     * Sets the sub code description.
     *
     * @param subCodeDescription the subCodeDescription to set
     */
    public void setSubCodeDescription(final String subCodeDescription) {
        this.subCodeDescription = subCodeDescription;
    }

}
