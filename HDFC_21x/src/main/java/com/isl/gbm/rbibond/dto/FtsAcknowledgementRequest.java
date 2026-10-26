package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FtsAcknowledgementRequest {

    @JsonProperty("objRequest")
    private FtsObjRequestAck objRequest;

    public FtsObjRequestAck getObjRequest() {
        return objRequest;
    }

    public void setObjRequest(FtsObjRequestAck objRequest) {
        this.objRequest = objRequest;
    }
}
