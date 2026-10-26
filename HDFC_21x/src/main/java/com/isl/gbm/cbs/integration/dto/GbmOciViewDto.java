/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * The Class GbmOciViewDto.
 *
 * @author Rahul.Kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GbmOciViewDto {

    /** The branch code. */
    private String branchCode;

    /** The instrument serial number. */
    private String instrumentSerialNumber;

    /** The clearing zone code. */
    private String clearingZoneCode;

    /** The clearing zone date. */
    private Date clearingZoneDate;

    /** The sol id. */
    private String solId;

    /** The instrument id. */
    private String instrumentId;

    /** The instrument id. */
    private Date instrumentDate;

    /** The instrument amount. */
    private Double instrumentAmount;

    /** The tran code. */
    private String tranCode;

    /** The sort code. */
    private String sortCode;

    /** The bank code. */
    private String bankCode;

    /** The delete flag. */
    private Character deleteFlag;

    /** The set num. */
    private String setNum;

    /** The status flag. */
    private Character statusFlag;

    /** The tran id 2. */
    private String tranId2;

    private String rejCode1;

    public String getRejCode1() {
        return rejCode1;
    }

    public void setRejCode1(String rejCode1) {
        this.rejCode1 = rejCode1;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * Gets the branch code.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * Gets the clearing zone code.
     *
     * @return the clearing zone code
     */
    public String getClearingZoneCode() {
        return this.clearingZoneCode;
    }

    /**
     * Gets the clearing zone date.
     *
     * @return the clearing zone date
     */
    public Date getClearingZoneDate() {
        return this.clearingZoneDate;
    }

    /**
     * Gets the delete flag.
     *
     * @return the delete flag
     */
    public Character getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Gets the instrument amount.
     *
     * @return the instrument amount
     */
    public Double getInstrumentAmount() {
        return this.instrumentAmount;
    }

    /**
     * Gets the instrument date.
     *
     * @return the instrument date
     */
    public Date getInstrumentDate() {
        return this.instrumentDate;
    }

    /**
     * Gets the instrument id.
     *
     * @return the instrument id
     */
    public String getInstrumentId() {
        return this.instrumentId;
    }

    /**
     * Gets the instrument serial number.
     *
     * @return the instrument serial number
     */
    public String getInstrumentSerialNumber() {
        return this.instrumentSerialNumber;
    }

    /**
     * Gets the sets the num.
     *
     * @return the sets the num
     */
    public String getSetNum() {
        return this.setNum;
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
     * Gets the sort code.
     *
     * @return the sort code
     */
    public String getSortCode() {
        return this.sortCode;
    }

    /**
     * Gets the status flag.
     *
     * @return the status flag
     */
    public Character getStatusFlag() {
        return this.statusFlag;
    }

    /**
     * Gets the tran code.
     *
     * @return the tran code
     */
    public String getTranCode() {
        return this.tranCode;
    }

    /**
     * Gets the tran id 2.
     *
     * @return the tran id 2
     */
    public String getTranId2() {
        return this.tranId2;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode
     *            the new bank code
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode
     *            the new branch code
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * Sets the clearing zone code.
     *
     * @param clearingZoneCode
     *            the new clearing zone code
     */
    public void setClearingZoneCode(final String clearingZoneCode) {
        this.clearingZoneCode = clearingZoneCode;
    }

    /**
     * Sets the clearing zone date.
     *
     * @param clearingZoneDate
     *            the new clearing zone date
     */
    public void setClearingZoneDate(final Date clearingZoneDate) {
        this.clearingZoneDate = clearingZoneDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag
     *            the new delete flag
     */
    public void setDeleteFlag(final Character deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the instrument amount.
     *
     * @param instrumentAmount
     *            the new instrument amount
     */
    public void setInstrumentAmount(final Double instrumentAmount) {
        this.instrumentAmount = instrumentAmount;
    }

    /**
     * Sets the instrument date.
     *
     * @param instrumentDate
     *            the new instrument date
     */
    public void setInstrumentDate(final Date instrumentDate) {
        this.instrumentDate = instrumentDate;
    }

    /**
     * Sets the instrument id.
     *
     * @param instrumentId
     *            the new instrument id
     */
    public void setInstrumentId(final String instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * Sets the instrument serial number.
     *
     * @param instrumentSerialNumber
     *            the new instrument serial number
     */
    public void setInstrumentSerialNumber(final String instrumentSerialNumber) {
        this.instrumentSerialNumber = instrumentSerialNumber;
    }

    /**
     * Sets the sets the num.
     *
     * @param setNum
     *            the new sets the num
     */
    public void setSetNum(final String setNum) {
        this.setNum = setNum;
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
     * Sets the sort code.
     *
     * @param sortCode
     *            the new sort code
     */
    public void setSortCode(final String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Sets the status flag.
     *
     * @param statusFlag
     *            the new status flag
     */
    public void setStatusFlag(final Character statusFlag) {
        this.statusFlag = statusFlag;
    }

    /**
     * Sets the tran code.
     *
     * @param tranCode
     *            the new tran code
     */
    public void setTranCode(final String tranCode) {
        this.tranCode = tranCode;
    }

    /**
     * Sets the tran id 2.
     *
     * @param tranId2
     *            the new tran id 2
     */
    public void setTranId2(final String tranId2) {
        this.tranId2 = tranId2;
    }

}
