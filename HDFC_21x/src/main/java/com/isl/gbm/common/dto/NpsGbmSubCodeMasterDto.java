package com.isl.gbm.common.dto;

public class NpsGbmSubCodeMasterDto {
    private String subCode;

    private String code;

    private String subCodeDescription;

    public String getCode() {
        return this.code;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public String getSubCodeDescription() {
        return this.subCodeDescription;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setSubCode(final String subCode) {
        this.subCode = subCode;
    }

    public void setSubCodeDescription(final String subCodeDescription) {
        this.subCodeDescription = subCodeDescription;
    }

}
