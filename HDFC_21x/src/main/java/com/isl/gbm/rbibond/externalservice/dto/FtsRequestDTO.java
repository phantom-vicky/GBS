package com.isl.gbm.rbibond.externalservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FtsRequestDTO {

    @JsonProperty("sessionContext")
    private SessionContextDTO sessionContext;

    @JsonProperty("FTSCustomerInstructionImageDetailsServiceRequestDTO")
    private FTSCustomerInstructionImageDetailsServiceRequestDTO FTSCustomerInstructionImageDetailsServiceRequestDTO;


    public SessionContextDTO getSessionContext() {
        return sessionContext;
    }

    public FTSCustomerInstructionImageDetailsServiceRequestDTO getFtsRequestDTO() {
        return FTSCustomerInstructionImageDetailsServiceRequestDTO;
    }

    public void setSessionContext(SessionContextDTO sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void setFtsRequestDTO(FTSCustomerInstructionImageDetailsServiceRequestDTO ftsRequestDTO) {
        this.FTSCustomerInstructionImageDetailsServiceRequestDTO = ftsRequestDTO;
    }

    public static class SessionContextDTO {

        @JsonProperty("channel")
        private String channel;

        @JsonProperty("userId")
        private String userId;

        @JsonProperty("externalReferenceNo")
        private String externalReferenceNo;

        @JsonProperty("bankCode")
        private String bankCode;

        @JsonProperty("transactionBranch")
        private String transactionBranch;


        public String getChannel() {
            return channel;
        }

        public String getUserId() {
            return userId;
        }

        public String getExternalReferenceNo() {
            return externalReferenceNo;
        }

        public String getBankCode() {
            return bankCode;
        }

        public String getTransactionBranch() {
            return transactionBranch;
        }

       public void setChannel(String channel) {
           this.channel = channel;
       }

       public void setUserId(String userId) {
           this.userId = userId;
       }

       public void setExternalReferenceNo(String externalReferenceNo) {
           this.externalReferenceNo = externalReferenceNo;
       }

       public void setBankCode(String bankCode) {
           this.bankCode = bankCode;
       }

       public void setTransactionBranch(String transactionBranch) {
           this.transactionBranch = transactionBranch;
       }

       @Override
       public String toString() {
           return "SessionContextDTO{" +
                   "channel='" + channel + '\'' +
                   ", userId='" + userId + '\'' +
                   ", externalReferenceNo='" + externalReferenceNo + '\'' +
                   ", bankCode='" + bankCode + '\'' +
                   ", transactionBranch='" + transactionBranch + '\'' +
                   '}';
       }
   }

    public static class FTSCustomerInstructionImageDetailsServiceRequestDTO {

        @JsonProperty("requestString")
        private RequestStringDTO requestString;

        @JsonProperty("headerParams")
        private List<HeaderParamDTO> headerParams;


        public RequestStringDTO getRequestString() {
            return requestString;
        }

        public List<HeaderParamDTO> getHeaderParams() {
            return headerParams;
        }

        public void setRequestString(RequestStringDTO requestString) {
            this.requestString = requestString;
        }

        public void setHeaderParams(List<HeaderParamDTO> headerParams) {
            this.headerParams = headerParams;
        }

        @Override
        public String toString() {
            return "FTSCustomerInstructionImageDetailsServiceRequestDTO{" +
                    "requestString=" + requestString +
                    ", headerParams=" + headerParams +
                    '}';
        }
    }

   public static class RequestStringDTO {

        @JsonProperty("objRequest")
        private ObjRequestDTO objRequest;

        public ObjRequestDTO getObjRequest() {
            return objRequest;
        }

       public void setObjRequest(ObjRequestDTO objRequest) {
           this.objRequest = objRequest;
       }

       @Override
       public String toString() {
           return "RequestStringDTO{" +
                   "objRequest=" + objRequest +
                   '}';
       }
   }

    public static class ObjRequestDTO {

        @JsonProperty("Cust_ID")
        private String Cust_ID;

        @JsonProperty("Account_Number")
        private String Account_Number;

        @JsonProperty("Barcode")
        private String Barcode;

        @JsonProperty("Form_Status")
        private String Form_Status;

        @JsonProperty("Status")
        private String Status;

        @JsonProperty("IsData")
        private String IsData;

        @JsonProperty("System_Type")
        private String System_Type;

        public String getCust_ID() {
            return Cust_ID;
        }

        public void setCust_ID(String cust_ID) {
            Cust_ID = cust_ID;
        }

        public String getAccount_Number() {
            return Account_Number;
        }

        public void setAccount_Number(String account_Number) {
            Account_Number = account_Number;
        }

        public String getBarcode() {
            return Barcode;
        }

        public void setBarcode(String barcode) {
            Barcode = barcode;
        }

        public String getForm_Status() {
            return Form_Status;
        }

        public void setForm_Status(String form_Status) {
            Form_Status = form_Status;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getIsData() {
            return IsData;
        }

        public void setIsData(String isData) {
            this.IsData = isData;
        }

        public String getSystem_Type() {
            return System_Type;
        }

        public void setSystem_Type(String system_Type) {
            System_Type = system_Type;
        }
    }

    public static class HeaderParamDTO {

        @JsonProperty("key")
        private String key;

        @JsonProperty("value")
        private String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "HeaderParamDTO{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FtsRequestDTO{" +
                "sessionContext=" + sessionContext +
                ", ftsRequestDTO=" + FTSCustomerInstructionImageDetailsServiceRequestDTO +
                '}';
    }
}

    

