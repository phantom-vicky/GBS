package com.isl.gbm.rbibond.dto.reports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="GBM_BANK_BRANCHES")
public class BondCalculatedDetailsDTO {
    @Id
    @Column(name = "BRANCH_ID")
    private String BRANCHID;

    @Column(name = "SOL_ID")
    private String SOLID;

    public String getSOLID() {
        return SOLID;
    }

    public void setSOLID(String SOLID) {
        this.SOLID = SOLID;
    }

    public String getBRANCHID() {
        return BRANCHID;
    }

    public void setBRANCHID(String BRANCHID) {
        this.BRANCHID = BRANCHID;
    }
}
