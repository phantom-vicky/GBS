package com.isl.gbm.rbibond.dto.scrolls;

public class BrokerageClaimAndHandlingCommissionDTO {

    private String schemeName;
    private String schemeCode;
    private String fromDate;
    private String toDate;
    private String flag;
    private String pidId;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPidId() {
        return pidId;
    }

    public void setPidId(String pidId) {
        this.pidId = pidId;
    }
}
