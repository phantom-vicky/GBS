package com.isl.gbm.rbibond.dto.reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

    @Entity
    @Table(name = "gbm_rbi_bond_int_ineli_accts")
    public class BondCollectingDTO {



        @Id
        @Column(name = "SL_NO")
        private String SLNO;

        public String getBONDSUBSCRIPTIONID() {
            return BONDSUBSCRIPTIONID;
        }

        public void setBONDSUBSCRIPTIONID(String BONDSUBSCRIPTIONID) {
            this.BONDSUBSCRIPTIONID = BONDSUBSCRIPTIONID;
        }

        @Column(name = "BRANCH_ID")
        private String BRANCHID;

        @Column(name = "CUSTOMER_NAME")
        private String CUSTOMERNAME;

        @Column(name = "BOND_SUBSCRIPTION_ID")
        private String BONDSUBSCRIPTIONID;

        @Column(name = "SUBSCRIPTION_AMOUNT")
        private String BONDSUBCRIPTIONAMOUNT;

        @Column(name = "SUBCRIPTION_DATE")
        private String SUBCRIPTIONDATE;

        @Column(name = "p_interest_paid_in")
        private String PINTERESTPAIDIN;

        @Column(name = "p_finacial_year_in")
        private String PFINACIALYEARIN;

        @Column(name = "SINGLE_BOND_LEDGER_ACC_NUM")
        private String SINGLEBONDLEDGERACCOUNTNUMBER;

        @Column(name = "FIRST_JOINT_HLDR_BLA_NO")
        private String FIRSTJOINTHOLDERBLANO;

        @Column(name = "P_BRANCH_ID_IN")
        private String PBRANCHIDIN;
        @Column(name = "INTEREST_AMT")
        private String INTERESTAMT;

        @Column(name = "INELIGIBLE_REASONS")
        private String INELIGIBLEREASONS;

        @Column(name = "CREATED_DATE")
        private String CREATEDDATE;


        @Column(name = "BLA_NO")
        private String BLANO;



        public String getCREATEDDATE() {
            return CREATEDDATE;
        }

        public void setCREATEDDATE(String CREATEDDATE) {
            this.CREATEDDATE = CREATEDDATE;
        }

        public String getINELIGIBLEREASONS() {
            return INELIGIBLEREASONS;
        }

        public void setINELIGIBLEREASONS(String INELIGIBLEREASONS) {
            this.INELIGIBLEREASONS = INELIGIBLEREASONS;
        }

        public String getINTERESTAMT() {
            return INTERESTAMT;
        }

        public void setINTERESTAMT(String INTERESTAMT) {
            this.INTERESTAMT = INTERESTAMT;
        }



        public String getBLANO() {
            return BLANO;
        }

        public void setBLANO(String BLANO) {
            this.BLANO = BLANO;
        }

        public String getSINGLEBONDLEDGERACCOUNTNUMBER() {
            return SINGLEBONDLEDGERACCOUNTNUMBER;
        }

        public void setSINGLEBONDLEDGERACCOUNTNUMBER(String SINGLEBONDLEDGERACCOUNTNUMBER) {
            this.SINGLEBONDLEDGERACCOUNTNUMBER = SINGLEBONDLEDGERACCOUNTNUMBER;
        }

        public String getFIRSTJOINTHOLDERBLANO() {
            return FIRSTJOINTHOLDERBLANO;
        }

        public void setFIRSTJOINTHOLDERBLANO(String FIRSTJOINTHOLDERBLANO) {
            this.FIRSTJOINTHOLDERBLANO = FIRSTJOINTHOLDERBLANO;
        }

        public String getSLNO() {
            return SLNO;
        }

        public void setSLNO(String SLNO) {
            this.SLNO = SLNO;
        }

        public String getBRANCHID() {
            return BRANCHID;
        }

        public void setBRANCHID(String BRANCHID) {
            this.BRANCHID = BRANCHID;
        }

        public String getCUSTOMERNAME() {
            return CUSTOMERNAME;
        }

        public void setCUSTOMERNAME(String CUSTOMERNAME) {
            this.CUSTOMERNAME = CUSTOMERNAME;
        }
        public String getBONDSUBCRIPTIONAMOUNT() {
            return BONDSUBCRIPTIONAMOUNT;
        }

        public void setBONDSUBCRIPTIONAMOUNT(String BONDSUBCRIPTIONAMOUNT) {
            this.BONDSUBCRIPTIONAMOUNT = BONDSUBCRIPTIONAMOUNT;
        }

        public String getSUBCRIPTIONDATE() {
            return SUBCRIPTIONDATE;
        }

        public void setSUBCRIPTIONDATE(String SUBCRIPTIONDATE) {
            this.SUBCRIPTIONDATE = SUBCRIPTIONDATE;
        }

        public String getINTERESTPAYMENTMODE() {
            return INTERESTPAYMENTMODE;
        }

        public void setINTERESTPAYMENTMODE(String INTERESTPAYMENTMODE) {
            this.INTERESTPAYMENTMODE = INTERESTPAYMENTMODE;
        }

        public String getGROSSINTERESTAMOUNT() {
            return GROSSINTERESTAMOUNT;
        }

        public void setGROSSINTERESTAMOUNT(String GROSSINTERESTAMOUNT) {
            this.GROSSINTERESTAMOUNT = GROSSINTERESTAMOUNT;
        }

        public String getREASON() {
            return REASON;
        }

        public void setREASON(String REASON) {
            this.REASON = REASON;
        }

        @Column(name = "MODE_OF_INTEREST_PAYMENT")
        private String INTERESTPAYMENTMODE;

        @Column(name = "GROSS_INTEREST_AMOUNT")
        private String GROSSINTERESTAMOUNT;

        @Column(name = "REASON")
        private String REASON;

        public String getPINTERESTPAIDIN() {
            return PINTERESTPAIDIN;
        }

        public void setPINTERESTPAIDIN(String PINTERESTPAIDIN) {
            this.PINTERESTPAIDIN = PINTERESTPAIDIN;
        }

        public String getPFINACIALYEARIN() {
            return PFINACIALYEARIN;
        }

        public void setPFINACIALYEARIN(String PFINACIALYEARIN) {
            this.PFINACIALYEARIN = PFINACIALYEARIN;
        }

        public String getPBRANCHIDIN() {
            return PBRANCHIDIN;
        }

        public void setPBRANCHIDIN(String PBRANCHIDIN) {
            this.PBRANCHIDIN = PBRANCHIDIN;
        }
    }



