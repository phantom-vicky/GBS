package com.isl.gbm.rbibond.dao;

public interface GbmRbiBondRejResnMstDao {

    /**
     * {@code true} when the trimmed remark matches {@code SUB_CODE_DESC} on a non-deleted row in
     * {@code gbm_rbi_bond_rej_resn_mst} (case-insensitive, per Hibernate lookup).
     */
    boolean isRejectionRemarkInMaster(String rejectionRemarks);
}
