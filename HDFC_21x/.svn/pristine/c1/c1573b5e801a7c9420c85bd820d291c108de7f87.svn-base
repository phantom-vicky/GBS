package com.isl.gbm.user.isac.util;

public enum ProcNamesEnum {


    PKG_GBM_USER_MGMT_ISAC_USER_CREATION("{ call PKG_GBM_USER_MGMT_ISAC.SP_ISAC_USER_CREATION(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_USER_MGMT_ISAC_USER_MODIFY("{ call PKG_GBM_USER_MGMT_ISAC.sp_mod(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_USER_MGMT_ISAC_USER_MODIFY_SEL("{ call PKG_GBM_USER_MGMT_ISAC.sp_mod_sel(?,?,?,?)}"),
    PKG_GBM_USER_MGMT_ISAC_UNLOCK_USER("{ call PKG_GBM_USER_MGMT_ISAC.SP_UNLOCK_USER(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_USER_MGMT_ISAC_SACK_USER("{ call PKG_GBM_USER_MGMT_ISAC.SP_SACK_USER(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_USER_MGMT_ISAC_USER_ENABLE("{ call PKG_GBM_USER_MGMT_ISAC.SP_ENABLE_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_USER_MGMT_ISAC_USER_DISABLE("{ call PKG_GBM_USER_MGMT_ISAC.SP_DISABLE_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_USER_MGMT_ISAC_USER_REVOKE("{ call PKG_GBM_USER_MGMT_ISAC.SP_REVOKE_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_USER_MGMT_ISAC_USER_TERMINATION("{ call PKG_GBM_USER_MGMT_ISAC.SP_TERMINATION_USER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_PPF_REP_to_det_loan_fin("{ call SP_GBM_PPF_REP_to_det_loan_fin(?,?,?,?,?,?) }"),
    ;
    private final String procName;

    ProcNamesEnum(String procName) {
        this.procName = procName;
    }

    public String getProcName() {
        System.out.println("sql procedure" + procName);
        return procName;
    }
}
