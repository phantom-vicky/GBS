/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

// TODO: Auto-generated Javadoc

/**
 * The Class GbmDtdViewDto.
 *
 * @author Rahul.Kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GbmDtdViewDto {

    /** The acid. */
    private String acid;

    /** The ref number. */
    private String refNumber;

    /** The sol id. */
    private String solId;

    /** The entry user id. */
    private String entryUserId;

    /** The tran id. */
    private String tranId;

    /** The tran date. */
    private Date tranDate;

    /** The vfd date. */
    private Date vfdDate;

    /** The part tran type. */
    private Character partTranType;

    /** The part tran serial number. */
    private String partTranSerialNumber;

    /** The tran amount. */
    private Double tranAmount;

    /** The posted flag. */
    private Character postedFlag;

    /** The tran type. */
    private Character tranType;

    /** The del flag. */
    private Character delFlag;

    /** The foracid id. */
    private String foracid;

    /**
     * Gets the acid.
     *
     * @return the acid
     */
    public String getAcid() {
        return this.acid;
    }

    /**
     * @return the delFlag
     */
    public Character getDelFlag() {
        return this.delFlag;
    }

    /**
     * Gets the entry user id.
     *
     * @return the entry user id
     */
    public String getEntryUserId() {
        return this.entryUserId;
    }

    /**
     * @return the foracid
     */
    public String getForacid() {
        return this.foracid;
    }

    /**
     * Gets the part tran serial number.
     *
     * @return the part tran serial number
     */
    public String getPartTranSerialNumber() {
        return this.partTranSerialNumber;
    }

    /**
     * Gets the part tran type.
     *
     * @return the part tran type
     */
    public Character getPartTranType() {
        return this.partTranType;
    }

    /**
     * Gets the posted flag.
     *
     * @return the posted flag
     */
    public Character getPostedFlag() {
        return this.postedFlag;
    }

    /**
     * Gets the ref number.
     *
     * @return the ref number
     */
    public String getRefNumber() {
        return this.refNumber;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return this.solId;
    }

    /**
     * Gets the tran amount.
     *
     * @return the tran amount
     */
    public Double getTranAmount() {
        return this.tranAmount;
    }

    /**
     * Gets the tran date.
     *
     * @return the tran date
     */
    public Date getTranDate() {
        return this.tranDate;
    }

    /**
     * Gets the tran id.
     *
     * @return the tran id
     */
    public String getTranId() {
        return this.tranId;
    }

    /**
     * @return the tranType
     */
    public Character getTranType() {
        return this.tranType;
    }

    /**
     * Gets the vfd date.
     *
     * @return the vfd date
     */
    public Date getVfdDate() {
        return this.vfdDate;
    }

    /**
     * Sets the acid.
     *
     * @param acid
     *            the new acid
     */
    public void setAcid(final String acid) {
        this.acid = acid;
    }

    /**
     * @param delFlag
     *            the delFlag to set
     */
    public void setDelFlag(final Character delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * Sets the entry user id.
     *
     * @param entryUserId
     *            the new entry user id
     */
    public void setEntryUserId(final String entryUserId) {
        this.entryUserId = entryUserId;
    }

    /**
     * @param foracid
     *            the foracid to set
     */
    public void setForacid(final String foracid) {
        this.foracid = foracid;
    }

    /**
     * Sets the part tran serial number.
     *
     * @param partTranSerialNumber
     *            the new part tran serial number
     */
    public void setPartTranSerialNumber(final String partTranSerialNumber) {
        this.partTranSerialNumber = partTranSerialNumber;
    }

    /**
     * Sets the part tran type.
     *
     * @param partTranType
     *            the new part tran type
     */
    public void setPartTranType(final Character partTranType) {
        this.partTranType = partTranType;
    }

    /**
     * Sets the posted flag.
     *
     * @param postedFlag
     *            the new posted flag
     */
    public void setPostedFlag(final Character postedFlag) {
        this.postedFlag = postedFlag;
    }

    /**
     * Sets the ref number.
     *
     * @param refNumber
     *            the new ref number
     */
    public void setRefNumber(final String refNumber) {
        this.refNumber = refNumber;
    }

    /**
     * Sets the sol id.
     *
     * @param solId
     *            the new sol id
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the tran amount.
     *
     * @param tranAmount
     *            the new tran amount
     */
    public void setTranAmount(final Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    /**
     * Sets the tran date.
     *
     * @param tranDate
     *            the new tran date
     */
    public void setTranDate(final Date tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * Sets the tran id.
     *
     * @param tranId
     *            the new tran id
     */
    public void setTranId(final String tranId) {
        this.tranId = tranId;
    }

    /**
     * @param tranType
     *            the tranType to set
     */
    public void setTranType(final Character tranType) {
        this.tranType = tranType;
    }

    /**
     * Sets the vfd date.
     *
     * @param vfdDate
     *            the new vfd date
     */
    public void setVfdDate(final Date vfdDate) {
        this.vfdDate = vfdDate;
    }
}
