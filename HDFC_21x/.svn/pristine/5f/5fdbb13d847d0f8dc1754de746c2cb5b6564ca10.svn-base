package com.isl.gbm.rbibond.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FtsFetchCiDataRequest {
    private ObjRequest objRequest;

    public ObjRequest getObjRequest() {
        return objRequest;
    }

    public void setObjRequest(ObjRequest objRequest) {
        this.objRequest = objRequest;
    }

    public static class ObjRequest {
        private String Acknowledgement_No;
        private String System_Type;

        public ObjRequest(String acknowledgement_No, String system_Type) {
            Acknowledgement_No = acknowledgement_No;
            System_Type = system_Type;
        }

        public String getAcknowledgement_No() {
            return Acknowledgement_No;
        }

        public void setAcknowledgement_No(String acknowledgement_No) {
            Acknowledgement_No = acknowledgement_No;
        }

        public String getSystem_Type() {
            return System_Type;
        }

        public void setSystem_Type(String system_Type) {
            System_Type = system_Type;
        }
    }
}
