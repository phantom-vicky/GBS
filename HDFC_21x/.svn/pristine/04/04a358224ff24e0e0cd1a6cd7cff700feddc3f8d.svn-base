/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class TransactionCodesDto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCodesDto {

    /**
     * The id.
     */
    private Integer id;



    @Valid
    List<ColumnValueDTO> columnValues;

    private Map<String, Object> listofTransactionCodesOldNewValue;


    /**
     * The clg tran code.
     */
    @Size(max = 5, message = "Clearing transaction code should not exceed 5 characters")
    private String clgTranCode;

    /**
     * The clg tran code desc.
     */
    @Size(max = 40, message = "Transaction code description should not exceed 40 characters")
    private String clgTranCodeDesc;

    /**
     * The instrument type.
     */
    @Size(max = 5, message = "Instrument type should not exceed 5 characters")
    private String instrumentType;

    /**
     * The delete flag.
     */
    private boolean deleteFlag;

    /**
     * The scheme code.
     */
    private String schemeCode;

    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The modified by.
     */
    private String modifiedBy;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The is verified.
     */
    private boolean isVerified;

    /**
     * The verified by.
     */
    private String verifiedBy;

    /**
     * The verified date.
     */
    private Date verifiedDate;

    /**
     * The file name.
     */
    @Size(max = 100, message = "File name should not exceed 100 characters")
    private String fileName;

    /**
     * The add text 1.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String addText1;

    /**
     * The add text 2.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String addText2;

    /**
     * The add text 3.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String addText3;



    private String creatDate;

    private String modDate;

    private String verDate;

    private String previousOperationType;

    private String verifiedFlag;



    /**
     * Gets the adds the text 1.
     *
     * @return the adds the text 1
     */
    public String getAddText1() {
        return this.addText1;
    }

    /**
     * Gets the adds the text 2.
     *
     * @return the adds the text 2
     */
    public String getAddText2() {
        return this.addText2;
    }

    /**
     * Gets the adds the text 3.
     *
     * @return the adds the text 3
     */
    public String getAddText3() {
        return this.addText3;
    }

    /**
     * Gets the clg tran code.
     *
     * @return the clg tran code
     */
    public String getClgTranCode() {
        return this.clgTranCode;
    }

    /**
     * Gets the clg tran code desc.
     *
     * @return the clg tran code desc
     */
    public String getClgTranCodeDesc() {
        return this.clgTranCodeDesc;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */

    public Integer getId() {
        return this.id;
    }

    /**
     * Gets the instrument type.
     *
     * @return the instrument type
     */
    public String getInstrumentType() {
        return this.instrumentType;
    }

    /**
     * Gets the checks if is verified.
     *
     * @return the checks if is verified
     */
    public boolean getIsVerified() {
        return this.isVerified;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public String getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * Gets the verified by.
     *
     * @return the verified by
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * Gets the verified date.
     *
     * @return the verified date
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * Checks if is delete flag.
     *
     * @return true, if is delete flag
     */
    public boolean isDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Sets the adds the text 1.
     *
     * @param addText1 the new adds the text 1
     */
    public void setAddText1(final String addText1) {
        this.addText1 = addText1;
    }

    /**
     * Sets the adds the text 2.
     *
     * @param addText2 the new adds the text 2
     */
    public void setAddText2(final String addText2) {
        this.addText2 = addText2;
    }

    /**
     * Sets the adds the text 3.
     *
     * @param addText3 the new adds the text 3
     */
    public void setAddText3(final String addText3) {
        this.addText3 = addText3;
    }

    /**
     * Sets the clg tran code.
     *
     * @param clgTranCode the new clg tran code
     */
    public void setClgTranCode(final String clgTranCode) {
        this.clgTranCode = clgTranCode;
    }

    /**
     * Sets the clg tran code desc.
     *
     * @param clgTranCodeDesc the new clg tran code desc
     */
    public void setClgTranCodeDesc(final String clgTranCodeDesc) {
        this.clgTranCodeDesc = clgTranCodeDesc;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag the new delete flag
     */
    public void setDeleteFlag(final boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Sets the instrument type.
     *
     * @param instrumentType the new instrument type
     */
    public void setInstrumentType(final String instrumentType) {
        this.instrumentType = instrumentType;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the new modified by
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Sets the scheme code.
     *
     * @param schemeCode the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * Sets the verified.
     *
     * @param isVerified the new verified
     */
    public void setVerified(final boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Sets the verified by.
     *
     * @param verifiedBy the new verified by
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate the new verified date
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
    }

    public Map<String, Object> getListofTransactionCodesOldNewValue() {
        return listofTransactionCodesOldNewValue;
    }

    public void setListofTransactionCodesOldNewValue(Map<String, Object> listofTransactionCodesOldNewValue) {
        this.listofTransactionCodesOldNewValue = listofTransactionCodesOldNewValue;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getVerDate() {
        return verDate;
    }

    public void setVerDate(String verDate) {
        this.verDate = verDate;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getVerifiedFlag() {
        return verifiedFlag;
    }

    public void setVerifiedFlag(String verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

    @Override
    public String toString() {
        return "TransactionCodesDto [id=" + this.id + ", clgTranCode=" + this.clgTranCode + ", clgTranCodeDesc="
                + this.clgTranCodeDesc + ", instrumentType=" + this.instrumentType + ", deleteFlag=" + this.deleteFlag
                + ", schemeCode=" + this.schemeCode + ", createdBy=" + this.createdBy + ", createdDate="
                + this.createdDate + ", modifiedBy=" + this.modifiedBy + ", modifiedDate=" + this.modifiedDate
                + ", isVerified=" + this.isVerified + ", verifiedBy=" + this.verifiedBy + ", verifiedDate="
                + this.verifiedDate + ", fileName=" + this.fileName + ", addText1=" + this.addText1 + ", addText2="
                + this.addText2 + ", addText3=" + this.addText3 + "]";
    }

}
