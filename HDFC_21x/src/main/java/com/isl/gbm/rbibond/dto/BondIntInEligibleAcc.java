package com.isl.gbm.rbibond.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gbm_rbi_bond_int_ineli_accts")
public class BondIntInEligibleAcc {
    @Id
    @Column(name = "BRANCH_ID")
    private String BRANCHID;

    @Column(name = "BOND_SUBSCRIPTION_ID")
    private String BONDSUBSCRIPTIONID;

    @Column(name = "INTEREST_AMT")
    private String INTERESTAMT;

    @Column(name = "INELIGIBLE_REASONS")
    private String INELIGIBLEREASONS;

    @Column(name = "BLA_NO")
    private String BLANO;

    @Column(name = "CREATED_DATE")
    private String CREATEDDATE;

    public String getBRANCHID() {
        return BRANCHID;
    }

    public void setBRANCHID(String BRANCHID) {
        this.BRANCHID = BRANCHID;
    }

    public String getBONDSUBSCRIPTIONID() {
        return BONDSUBSCRIPTIONID;
    }

    public void setBONDSUBSCRIPTIONID(String BONDSUBSCRIPTIONID) {
        this.BONDSUBSCRIPTIONID = BONDSUBSCRIPTIONID;
    }

    public String getINTERESTAMT() {
        return INTERESTAMT;
    }

    public void setINTERESTAMT(String INTERESTAMT) {
        this.INTERESTAMT = INTERESTAMT;
    }

    public String getINELIGIBLEREASONS() {
        return INELIGIBLEREASONS;
    }

    public void setINELIGIBLEREASONS(String INELIGIBLEREASONS) {
        this.INELIGIBLEREASONS = INELIGIBLEREASONS;
    }

    public String getBLANO() {
        return BLANO;
    }

    public void setBLANO(String BLANO) {
        this.BLANO = BLANO;
    }
}
