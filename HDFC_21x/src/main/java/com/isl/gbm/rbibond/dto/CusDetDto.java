package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CusDetDto {

    private String id;

    private String cusAlphaCode;

    private String cusName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusAlphaCode() {
        return cusAlphaCode;
    }

    public void setCusAlphaCode(String cusAlphaCode) {
        this.cusAlphaCode = cusAlphaCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
}
