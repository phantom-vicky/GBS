package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DdTransactionApiSuccessDto {
    private String successOrFailure;
    private String chrgdOff;
    private String TranId;
    private String TranDate;
    private String errorsource;
    private String errorCode;
    private String errormessageCode;
    private String errorType;

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    private String branchCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorsource() {
        return errorsource;
    }

    public void setErrorsource(String errorsource) {
        this.errorsource = errorsource;
    }

    public String getErrormessageCode() {
        return errormessageCode;
    }

    public void setErrormessageCode(String errormessageCode) {
        this.errormessageCode = errormessageCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getSuccessOrFailure() {
        return successOrFailure;
    }

    public void setSuccessOrFailure(String successOrFailure) {
        this.successOrFailure = successOrFailure;
    }

    public String getChrgdOff() {
        return chrgdOff;
    }

    public void setChrgdOff(String chrgdOff) {
        this.chrgdOff = chrgdOff;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTranId() {
        return TranId;
    }

    public void setTranId(String tranId) {
        TranId = tranId;
    }
}
