package com.isl.gbm.rbibond.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GBM_RBI_BOND_SUBSCRIPTION_FLOW")
public class SubscriptionFlow {

    @Id
    @Column(name = "BOND_SUBSCRIPTION_ID")
    private String BONDSUBSCRIPTIONID;

    @Column(name = "SINGLE_BOND_LEDGER_ACC_NUM")
    private String SINGLEBONDLEDGERACCNUM;

    @Column(name = "FIRST_JOINT_HLDR_BLA_NO")
    private String FIRSTJOINTHLDRBLANO;

    @Column(name = "verified_status")
    private String verifiedstatus;

    @Column(name = "SCH_CODE")
    private String SCHCODE;

    @Column(name = "BRANCH_ID")
    private String branchId;

    @Column(name = "is_deleted")
    private String isdeleted;

    @Column(name = "IS_ACTIVE")
    private String ISACTIVE;

    @Column(name = "MODE_OF_HOLDING")
    private String MODEOFHOLDING;

    @Column(name = "BOND_HOLDER_NAME")
    private String BONDHOLDERNAME;

    @Column(name = "FIRST_JOINT_HLDR_NAME")
    private String FIRSTJOINTHOLDERNAME;

    @Column(name = "MODE_OF_INTEREST_PAYMENT")
    private String MODEOFINTERESTPAYMENT;

    @Column(name = "OPERATIVE_ACC_NUMBER")
    private String OPERATIVEACCNUMBER;

    @Column(name = "DD_PO_ACC_NUMBER")
    private String DDPOACCNUMBER;

    @Column(name = "CREDIT_ACCOUNT_NUMBER")
    private String CREDITACCOUNTNUMBER;

    @Column(name = "ACCOUNT_OPEN_DATE")
    private String ACCOUNTOPENDATE;

    @Column(name = "CREATED_DATE")
    private String CREATEDDATE;

    @Column(name = "SUBCRIPTION_DATE")
    private String SUBCRIPTIONDATE;



    @Column(name = "SUBSCRIPTION_AMOUNT")
    private String SUBSCRIPTIONAMOUNT;

    @Column(name = "MATURITY_DATE")
    private String MATURITYDATE;

    @Column(name = "ROW_NUM")
    private String ROWNUM;

    @Column(name = "IS_ACCT_CLOSURE")
    private String ISACCTCLOSURE;

    @Column(name = "AMOUNT_OF_REPAYMENT")
    private String AMOUNTOFREPAYMENT;


    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getSUBCRIPTIONDATE() {
        return SUBCRIPTIONDATE;
    }

    public void setSUBCRIPTIONDATE(String SUBCRIPTIONDATE) {
        this.SUBCRIPTIONDATE = SUBCRIPTIONDATE;
    }

    public String getMATURITYDATE() {
        return MATURITYDATE;
    }

    public void setMATURITYDATE(String MATURITYDATE) {
        this.MATURITYDATE = MATURITYDATE;
    }

    public String getROWNUM() {
        return ROWNUM;
    }

    public void setROWNUM(String ROWNUM) {
        this.ROWNUM = ROWNUM;
    }

    public String getISACCTCLOSURE() {
        return ISACCTCLOSURE;
    }

    public void setISACCTCLOSURE(String ISACCTCLOSURE) {
        this.ISACCTCLOSURE = ISACCTCLOSURE;
    }

    public String getAMOUNTOFREPAYMENT() {
        return AMOUNTOFREPAYMENT;
    }

    public void setAMOUNTOFREPAYMENT(String AMOUNTOFREPAYMENT) {
        this.AMOUNTOFREPAYMENT = AMOUNTOFREPAYMENT;
    }

    public String getSUBSCRIPTIONAMOUNT() {
        return SUBSCRIPTIONAMOUNT;
    }

    public void setSUBSCRIPTIONAMOUNT(String SUBSCRIPTIONAMOUNT) {
        this.SUBSCRIPTIONAMOUNT = SUBSCRIPTIONAMOUNT;
    }

    public String getCREATEDDATE() {
        return CREATEDDATE;
    }

    public void setCREATEDDATE(String CREATEDDATE) {
        this.CREATEDDATE = CREATEDDATE;
    }


    public String getACCOUNTOPENDATE() {
        return ACCOUNTOPENDATE;
    }

    public void setACCOUNTOPENDATE(String ACCOUNTOPENDATE) {
        this.ACCOUNTOPENDATE = ACCOUNTOPENDATE;
    }

    public String getSINGLEBONDLEDGERACCNUM() {
        return SINGLEBONDLEDGERACCNUM;
    }

    public void setSINGLEBONDLEDGERACCNUM(String SINGLEBONDLEDGERACCNUM) {
        this.SINGLEBONDLEDGERACCNUM = SINGLEBONDLEDGERACCNUM;
    }

    public String getFIRSTJOINTHLDRBLANO() {
        return FIRSTJOINTHLDRBLANO;
    }

    public void setFIRSTJOINTHLDRBLANO(String FIRSTJOINTHLDRBLANO) {
        this.FIRSTJOINTHLDRBLANO = FIRSTJOINTHLDRBLANO;
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

    public String getBONDSUBSCRIPTIONID() {
        return BONDSUBSCRIPTIONID;
    }

    public void setBONDSUBSCRIPTIONID(String BONDSUBSCRIPTIONID) {
        this.BONDSUBSCRIPTIONID = BONDSUBSCRIPTIONID;
    }

    public String getMODEOFHOLDING() {
        return MODEOFHOLDING;
    }

    public void setMODEOFHOLDING(String MODEOFHOLDING) {
        this.MODEOFHOLDING = MODEOFHOLDING;
    }

    public String getBONDHOLDERNAME() {
        return BONDHOLDERNAME;
    }

    public void setBONDHOLDERNAME(String BONDHOLDERNAME) {
        this.BONDHOLDERNAME = BONDHOLDERNAME;
    }

    public String getFIRSTJOINTHOLDERNAME() {
        return FIRSTJOINTHOLDERNAME;
    }

    public void setFIRSTJOINTHOLDERNAME(String FIRSTJOINTHOLDERNAME) {
        this.FIRSTJOINTHOLDERNAME = FIRSTJOINTHOLDERNAME;
    }

    public String getMODEOFINTERESTPAYMENT() {
        return MODEOFINTERESTPAYMENT;
    }

    public void setMODEOFINTERESTPAYMENT(String MODEOFINTERESTPAYMENT) {
        this.MODEOFINTERESTPAYMENT = MODEOFINTERESTPAYMENT;
    }

    public String getOPERATIVEACCNUMBER() {
        return OPERATIVEACCNUMBER;
    }

    public void setOPERATIVEACCNUMBER(String OPERATIVEACCNUMBER) {
        this.OPERATIVEACCNUMBER = OPERATIVEACCNUMBER;
    }

    public String getDDPOACCNUMBER() {
        return DDPOACCNUMBER;
    }

    public void setDDPOACCNUMBER(String DDPOACCNUMBER) {
        this.DDPOACCNUMBER = DDPOACCNUMBER;
    }

    public String getCREDITACCOUNTNUMBER() {
        return CREDITACCOUNTNUMBER;
    }

    public void setCREDITACCOUNTNUMBER(String CREDITACCOUNTNUMBER) {
        this.CREDITACCOUNTNUMBER = CREDITACCOUNTNUMBER;
    }
}

