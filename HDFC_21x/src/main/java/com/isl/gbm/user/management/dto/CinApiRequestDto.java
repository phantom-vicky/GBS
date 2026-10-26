package com.isl.gbm.user.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CinApiRequestDto {

    private String clientId;

    private String clientSecret;

    private String checkSumValue;

    @JsonProperty("MODULE")
    private String module;

    @JsonProperty("UC_ID")
    private String ucId;

    @JsonProperty("INPUT")
    private String input;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCheckSumValue() {
        return checkSumValue;
    }

    public void setCheckSumValue(String checkSumValue) {
        this.checkSumValue = checkSumValue;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUcId() {
        return ucId;
    }

    public void setUcId(String ucId) {
        this.ucId = ucId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

}
