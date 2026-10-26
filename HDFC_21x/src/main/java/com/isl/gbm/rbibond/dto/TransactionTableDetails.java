package com.isl.gbm.rbibond.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gbm_transaction_table")
public class TransactionTableDetails {

    @Id
    @Column(name = "FINACLE_TRAN_ID")
    private String FINACLETRANID;

    @Column(name = "GBM_TRAN_ID")
    private String GBMTRANID;

    @Column(name = "VERIFIED_STATUS")
    private String VERIFIEDSTATUS;

    @Column(name = "IS_DELETED")
    private String ISDELETED;

    @Column(name = "TRAN_LEG")
    private String TRANLEG;

    @Column(name = "SCH_SUB_CODE")
    private String SCHSUBCODE;

    public String getFINACLETRANID() {
        return FINACLETRANID;
    }

    public void setFINACLETRANID(String FINACLETRANID) {
        this.FINACLETRANID = FINACLETRANID;
    }

    public String getGBMTRANID() {
        return GBMTRANID;
    }

    public void setGBMTRANID(String GBMTRANID) {
        this.GBMTRANID = GBMTRANID;
    }

    public String getVERIFIEDSTATUS() {
        return VERIFIEDSTATUS;
    }

    public void setVERIFIEDSTATUS(String VERIFIEDSTATUS) {
        this.VERIFIEDSTATUS = VERIFIEDSTATUS;
    }

    public String getISDELETED() {
        return ISDELETED;
    }

    public void setISDELETED(String ISDELETED) {
        this.ISDELETED = ISDELETED;
    }

    public String getTRANLEG() {
        return TRANLEG;
    }

    public void setTRANLEG(String TRANLEG) {
        this.TRANLEG = TRANLEG;
    }

    public String getSCHSUBCODE() {
        return SCHSUBCODE;
    }

    public void setSCHSUBCODE(String SCHSUBCODE) {
        this.SCHSUBCODE = SCHSUBCODE;
    }
}
