package com.isl.gbm.rbibond.dto;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "gbm_rbi_bond_rej_resn_mst")
public class GbmRbiBondRejResnMst {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "MODULE_CODE")
    private String moduleCode;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CODE_DESC")
    private String codeDesc;

    @Column(name = "SUB_CODE")
    private String subCode;

    @Column(name = "SUB_CODE_DESC")
    private String subCodeDesc;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "LAST_MOD_BY")
    private String lastModBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MOD_DATE")
    private Date lastModDate;

    @Column(name = "VERIFIED_STATUS")
    private String verifiedStatus;

    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "VERIFIED_DATE")
    private Date verifiedDate;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubCodeDesc() {
        return subCodeDesc;
    }

    public void setSubCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModBy() {
        return lastModBy;
    }

    public void setLastModBy(String lastModBy) {
        this.lastModBy = lastModBy;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }
}
