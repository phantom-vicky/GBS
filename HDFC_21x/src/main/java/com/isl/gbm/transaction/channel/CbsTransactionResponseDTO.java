package com.isl.gbm.transaction.channel;

import java.util.Date;

public class CbsTransactionResponseDTO {
    /**
     * The request uuid.
     */
    private String requestUuid;

    /**
     * The cbs transaction id.
     */
    private String cbsTransactionId;

    /**
     * The cbs transaction date.
     */
    private Date cbsTransactionDate;

    /**
     * The error code.
     */
    private String errorCode;

    /**
     * The error description.
     */
    private String errorDescription;

    /**
     * The error source.
     */
    private String errorSource;

    /**
     * The error type.
     */
    private String errorType;
    /**
     * The set number.
     */
    private String setNumber;

    /**
     * The tran status.
     */
    private String tranStatus;

    /**
     * The message.
     */
    private String message;

    /**
     * The part tran serial number.
     */
    private String partTranSerialNumber;

    /**
     * The finacle response status.
     */
    private String finacleResponseStatus;

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public String getCbsTransactionId() {
        return cbsTransactionId;
    }

    public void setCbsTransactionId(String cbsTransactionId) {
        this.cbsTransactionId = cbsTransactionId;
    }

    public Date getCbsTransactionDate() {
        return cbsTransactionDate;
    }

    public void setCbsTransactionDate(Date cbsTransactionDate) {
        this.cbsTransactionDate = cbsTransactionDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPartTranSerialNumber() {
        return partTranSerialNumber;
    }

    public void setPartTranSerialNumber(String partTranSerialNumber) {
        this.partTranSerialNumber = partTranSerialNumber;
    }

    public String getFinacleResponseStatus() {
        return finacleResponseStatus;
    }

    public void setFinacleResponseStatus(String finacleResponseStatus) {
        this.finacleResponseStatus = finacleResponseStatus;
    }

    @Override
    public String toString() {
        return "CbsTransactionResponseDTO{" +
                "requestUuid='" + requestUuid + '\'' +
                ", cbsTransactionId='" + cbsTransactionId + '\'' +
                ", cbsTransactionDate=" + cbsTransactionDate +
                ", errorCode='" + errorCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorSource='" + errorSource + '\'' +
                ", errorType='" + errorType + '\'' +
                ", setNumber='" + setNumber + '\'' +
                ", tranStatus=" + tranStatus +
                ", message='" + message + '\'' +
                ", partTranSerialNumber='" + partTranSerialNumber + '\'' +
                ", finacleResponseStatus='" + finacleResponseStatus + '\'' +
                '}';
    }


}
