/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author Vasanthi
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TranDetailsIntegrationDto {

    private String clgTranCode;

    private String clgTranCodeDesc;

    private Character applicabilityInd;

    private Character consolidationFlag;

    private String defaultUploadAcctPrefix;

    private Character deleteFlag;

    private String drBacid;

    private String instrumentType;

    private Date lchgTime;

    private String lchgUserId;

    private Date rcreTime;

    private String rcreUserId;

    private Number tsCount;

    private Character mediaLoadFlag;

    private String searchSchmType;

    private Character sysArrivedAcctFlag;

    /**
     * @return the applicabilityInd
     */
    public Character getApplicabilityInd() {
        return applicabilityInd;
    }

    /**
     * @return the clgTranCode
     */
    public String getClgTranCode() {
        return clgTranCode;
    }

    /**
     * @return the clgTranCodeDesc
     */
    public String getClgTranCodeDesc() {
        return clgTranCodeDesc;
    }

    /**
     * @return the consolidationFlag
     */
    public Character getConsolidationFlag() {
        return consolidationFlag;
    }

    /**
     * @return the defaultUploadAcctPrefix
     */
    public String getDefaultUploadAcctPrefix() {
        return defaultUploadAcctPrefix;
    }

    /**
     * @return the deleteFlag
     */
    public Character getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @return the drBacid
     */
    public String getDrBacid() {
        return drBacid;
    }

    /**
     * @return the instrumentType
     */
    public String getInstrumentType() {
        return instrumentType;
    }

    /**
     * @return the lchgTime
     */
    public Date getLchgTime() {
        return lchgTime;
    }

    /**
     * @return the lchgUserId
     */
    public String getLchgUserId() {
        return lchgUserId;
    }

    /**
     * @return the mediaLoadFlag
     */
    public Character getMediaLoadFlag() {
        return mediaLoadFlag;
    }

    /**
     * @return the rcreTime
     */
    public Date getRcreTime() {
        return rcreTime;
    }

    /**
     * @return the rcreUserId
     */
    public String getRcreUserId() {
        return rcreUserId;
    }

    /**
     * @return the searchSchmType
     */
    public String getSearchSchmType() {
        return searchSchmType;
    }

    /**
     * @return the sysArrivedAcctFlag
     */
    public Character getSysArrivedAcctFlag() {
        return sysArrivedAcctFlag;
    }

    /**
     * @return the tsCount
     */
    public Number getTsCount() {
        return tsCount;
    }

    /**
     * @param applicabilityInd
     *            the applicabilityInd to set
     */
    public void setApplicabilityInd(Character applicabilityInd) {
        this.applicabilityInd = applicabilityInd;
    }

    /**
     * @param clgTranCode
     *            the clgTranCode to set
     */
    public void setClgTranCode(String clgTranCode) {
        this.clgTranCode = clgTranCode;
    }

    /**
     * @param clgTranCodeDesc
     *            the clgTranCodeDesc to set
     */
    public void setClgTranCodeDesc(String clgTranCodeDesc) {
        this.clgTranCodeDesc = clgTranCodeDesc;
    }

    /**
     * @param consolidationFlag
     *            the consolidationFlag to set
     */
    public void setConsolidationFlag(Character consolidationFlag) {
        this.consolidationFlag = consolidationFlag;
    }

    /**
     * @param defaultUploadAcctPrefix
     *            the defaultUploadAcctPrefix to set
     */
    public void setDefaultUploadAcctPrefix(String defaultUploadAcctPrefix) {
        this.defaultUploadAcctPrefix = defaultUploadAcctPrefix;
    }

    /**
     * @param deleteFlag
     *            the deleteFlag to set
     */
    public void setDeleteFlag(Character deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @param drBacid
     *            the drBacid to set
     */
    public void setDrBacid(String drBacid) {
        this.drBacid = drBacid;
    }

    /**
     * @param instrumentType
     *            the instrumentType to set
     */
    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    /**
     * @param lchgTime
     *            the lchgTime to set
     */
    public void setLchgTime(Date lchgTime) {
        this.lchgTime = lchgTime;
    }

    /**
     * @param lchgUserId
     *            the lchgUserId to set
     */
    public void setLchgUserId(String lchgUserId) {
        this.lchgUserId = lchgUserId;
    }

    /**
     * @param mediaLoadFlag
     *            the mediaLoadFlag to set
     */
    public void setMediaLoadFlag(Character mediaLoadFlag) {
        this.mediaLoadFlag = mediaLoadFlag;
    }

    /**
     * @param rcreTime
     *            the rcreTime to set
     */
    public void setRcreTime(Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    /**
     * @param rcreUserId
     *            the rcreUserId to set
     */
    public void setRcreUserId(String rcreUserId) {
        this.rcreUserId = rcreUserId;
    }

    /**
     * @param searchSchmType
     *            the searchSchmType to set
     */
    public void setSearchSchmType(String searchSchmType) {
        this.searchSchmType = searchSchmType;
    }

    /**
     * @param sysArrivedAcctFlag
     *            the sysArrivedAcctFlag to set
     */
    public void setSysArrivedAcctFlag(Character sysArrivedAcctFlag) {
        this.sysArrivedAcctFlag = sysArrivedAcctFlag;
    }

    /**
     * @param tsCount
     *            the tsCount to set
     */
    public void setTsCount(Number tsCount) {
        this.tsCount = tsCount;
    }

    @Override
    public String toString() {
        return "TranDetailsIntegrationDto [clgTranCode=" + clgTranCode + ", clgTranCodeDesc=" + clgTranCodeDesc + "]";
    }
}
