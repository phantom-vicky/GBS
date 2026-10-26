package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateMasterMaintenanceDTO {
    private String stateTaxCode;
    private String stateTaxDescription;
    private String stateOrUnion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String masterName;
    private String id;

    public String getStateTaxDescription() {
        return stateTaxDescription;
    }

    public void setStateTaxDescription(String stateTaxDescription) {
        this.stateTaxDescription = stateTaxDescription;
    }

    public List<HashMap<String, String>> getTaxCode() {
        return taxCode;
    }


    @Override
    public String toString() {
        return "StateMasterMaintenanceDTO{" +
                "stateTaxCode='" + stateTaxCode + '\'' +
                ", stateTaxDescription='" + stateTaxDescription + '\'' +
                ", stateOrUnion='" + stateOrUnion + '\'' +
                ", masterName='" + masterName + '\'' +
                ", taxCode=" + taxCode +
                ", code='" + code + '\'' +
                ", codeDescription='" + codeDescription + '\'' +
                '}';
    }

    public void setTaxCode(List<HashMap<String, String>> taxCode) {
        this.taxCode = taxCode;
    }

    private List<HashMap<String, String>> taxCode;

    public String getStateTaxCode() {
        return stateTaxCode;
    }

    public void setStateTaxCode(String stateTaxCode) {
        this.stateTaxCode = stateTaxCode;
    }


    public String getStateOrUnion() {
        return stateOrUnion;
    }

    public void setStateOrUnion(String stateOrUnion) {
        this.stateOrUnion = stateOrUnion;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    private String code;
    private String codeDescription;

    public static class StateTaxMasterMaintenanceResponseDTO {

        private String id;
        private String stateTaxCode;
        private String stateTaxDescription;
        private String stateUtCode;
        private String StateUtCodeDescription;
        private String masterCodeOld;
        private String masterCodeDescOld;
        private String masterCodeNew;
        private String masterCodeDescNew;
        private String createdBy;
        private String createdDate;
        private String modifiedBy;
        private String modifiedDate;
        private String verifiedBy;
        private String verifiedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStateTaxCode() {
            return stateTaxCode;
        }

        public void setStateTaxCode(String stateTaxCode) {
            this.stateTaxCode = stateTaxCode;
        }

        public String getStateTaxDescription() {
            return stateTaxDescription;
        }

        public void setStateTaxDescription(String stateTaxDescription) {
            this.stateTaxDescription = stateTaxDescription;
        }

        public String getStateUtCode() {
            return stateUtCode;
        }

        public void setStateUtCode(String stateUtCode) {
            this.stateUtCode = stateUtCode;
        }

        public String getStateUtCodeDescription() {
            return StateUtCodeDescription;
        }

        public void setStateUtCodeDescription(String stateUtCodeDescription) {
            StateUtCodeDescription = stateUtCodeDescription;
        }

        public String getMasterCodeOld() {
            return masterCodeOld;
        }

        public void setMasterCodeOld(String masterCodeOld) {
            this.masterCodeOld = masterCodeOld;
        }

        public String getMasterCodeDescOld() {
            return masterCodeDescOld;
        }

        public void setMasterCodeDescOld(String masterCodeDescOld) {
            this.masterCodeDescOld = masterCodeDescOld;
        }

        public String getMasterCodeNew() {
            return masterCodeNew;
        }

        public void setMasterCodeNew(String masterCodeNew) {
            this.masterCodeNew = masterCodeNew;
        }

        public String getMasterCodeDescNew() {
            return masterCodeDescNew;
        }

        public void setMasterCodeDescNew(String masterCodeDescNew) {
            this.masterCodeDescNew = masterCodeDescNew;
        }

        private String verifiedStatus;
        private String prevOperationType;


        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public String getVerifiedBy() {
            return verifiedBy;
        }

        public void setVerifiedBy(String verifiedBy) {
            this.verifiedBy = verifiedBy;
        }

        public String getVerifiedDate() {
            return verifiedDate;
        }

        public void setVerifiedDate(String verifiedDate) {
            this.verifiedDate = verifiedDate;
        }

        public String getVerifiedStatus() {
            return verifiedStatus;
        }

        public void setVerifiedStatus(String verifiedStatus) {
            this.verifiedStatus = verifiedStatus;
        }

        public String getPrevOperationType() {
            return prevOperationType;
        }

        public void setPrevOperationType(String prevOperationType) {
            this.prevOperationType = prevOperationType;
        }
    }

}
