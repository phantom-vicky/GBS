package com.isl.gbm.rbibond.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GBM_RBI_BOND_LEDGER_ACC_MASTER")
public class BondLedgerAccNo {

    @Id
    @Column(name = "BOND_LEDGER_ACCOUNT_NUMBER")
    private String BONDLEDGERACCOUNTNUMBER;

    @Column(name = "BRANCH_ID")
    private String BRANCHID;

    @Column(name = "PAN_NO")
    private String PANNO;

    @Column(name = "BOND_HLD_FIRST_NAME")
    private String BONDHLDFIRSTNAME;

    @Column(name = "BOND_HLD_MID_NAME")
    private String BONDHLDMIDNAME;

    @Column(name = "BOND_HLD_LAST_NAME")
    private String BONDHLDLASTNAME;

    @Column(name = "verified_status")
    private String verifiedstatus;

    @Column(name = "SCH_CODE")
    private String SCHCODE;

    @Column(name = "is_deleted")
    private String isdeleted;

    @Column(name = "IS_ACTIVE")
    private String ISACTIVE;


    public String getBONDLEDGERACCOUNTNUMBER() {
        return BONDLEDGERACCOUNTNUMBER;
    }

    public void setBONDLEDGERACCOUNTNUMBER(String BONDLEDGERACCOUNTNUMBER) {
        this.BONDLEDGERACCOUNTNUMBER = BONDLEDGERACCOUNTNUMBER;
    }

    public String getBRANCHID() {
        return BRANCHID;
    }

    public void setBRANCHID(String BRANCHID) {
        this.BRANCHID = BRANCHID;
    }

    public String getPANNO() {
        return PANNO;
    }

    public void setPANNO(String PANNO) {
        this.PANNO = PANNO;
    }

    public String getBONDHLDFIRSTNAME() {
        return BONDHLDFIRSTNAME;
    }

    public void setBONDHLDFIRSTNAME(String BONDHLDFIRSTNAME) {
        this.BONDHLDFIRSTNAME = BONDHLDFIRSTNAME;
    }

    public String getBONDHLDMIDNAME() {
        return BONDHLDMIDNAME;
    }

    public void setBONDHLDMIDNAME(String BONDHLDMIDNAME) {
        this.BONDHLDMIDNAME = BONDHLDMIDNAME;
    }

    public String getBONDHLDLASTNAME() {
        return BONDHLDLASTNAME;
    }

    public void setBONDHLDLASTNAME(String BONDHLDLASTNAME) {
        this.BONDHLDLASTNAME = BONDHLDLASTNAME;
    }

    public String getVerifiedstatus() {
        return verifiedstatus;
    }

    public void setVerifiedstatus(String verifiedstatus) {
        this.verifiedstatus = verifiedstatus;
    }

    public String getSCHCODE() {
        return SCHCODE;
    }

    public void setSCHCODE(String SCHCODE) {
        this.SCHCODE = SCHCODE;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getISACTIVE() {
        return ISACTIVE;
    }

    public void setISACTIVE(String ISACTIVE) {
        this.ISACTIVE = ISACTIVE;
    }
}
