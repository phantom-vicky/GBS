package com.isl.gbm.cbs.integration.common;

public enum ProcNameEnum {

    /*Branch IFSC Code ------------------------Starts */
    PKG_GBM_CBS_BR_IFSC_SP_ADD("{call PKG_GBM_CBS_BR_IFSC.sp_add(?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_MOD_SEL("{call PKG_GBM_CBS_BR_IFSC.sp_mod_sel(?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_MOD("{call PKG_GBM_CBS_BR_IFSC.sp_mod(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_INQ("{call PKG_GBM_CBS_BR_IFSC.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_VER_SEL("{call PKG_GBM_CBS_BR_IFSC.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_VER("{call PKG_GBM_CBS_BR_IFSC.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_IFSC_SP_DEL("{call PKG_GBM_CBS_BR_IFSC.sp_del(?,?,?,?,?)}"),
    /*Branch IFSC Code -----------------------Ends */
    /*Other Bank Branches -----------------------Starts */
    PKG_GBM_CBS_BR_OTH_BNK_SP_ADD("{call pkg_gbm_cbs_br_oth_bnk.sp_add (?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_MOD_SEL("{call PKG_GBM_CBS_BR_OTH_BNK.sp_mod_sel(?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_INQ("{call pkg_gbm_cbs_br_oth_bnk.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_VER_SEL("{call pkg_gbm_cbs_br_oth_bnk.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_VER("{call pkg_gbm_cbs_br_oth_bnk.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_DEL("{call pkg_gbm_cbs_br_oth_bnk.sp_del(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BR_OTH_BNK_SP_MOD("{call pkg_gbm_cbs_br_oth_bnk.sp_mod (?,?,?,?,?,?)}"),
    /*Other Bank Branches -----------------------Ends */
    /*List Of Transaction Codes -----------------------Starts */
    PKG_GBM_CBS_LST_TRAN_CODE_SP_ADD("{call pkg_gbm_cbs_lst_tran_code.sp_add (?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_MOD_SEL("{call pkg_gbm_cbs_lst_tran_code.sp_mod_sel(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_INQ("{call pkg_gbm_cbs_lst_tran_code.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_VER_SEL("{call pkg_gbm_cbs_lst_tran_code.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_VER("{call pkg_gbm_cbs_lst_tran_code.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_DEL("{call pkg_gbm_cbs_lst_tran_code.sp_del(?,?,?,?,?)}"),
    PKG_GBM_CBS_LST_TRAN_CODE_SP_MOD("{call pkg_gbm_cbs_lst_tran_code.sp_mod (?,?,?,?,?,?)}"),
    /*List Of Transaction Codes -----------------------Ends */
    /*Other bank Details -----------------------Starts */
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_ADD("{call pkg_gbm_cbs_oth_bank_dtls.sp_add (?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_MOD_SEL("{call pkg_gbm_cbs_oth_bank_dtls.sp_mod_sel(?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_INQ("{call pkg_gbm_cbs_oth_bank_dtls.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_VER_SEL("{call pkg_gbm_cbs_oth_bank_dtls.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_VER("{call pkg_gbm_cbs_oth_bank_dtls.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_DEL("{call pkg_gbm_cbs_oth_bank_dtls.sp_del(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_OTH_BANK_DTLS_SP_MOD("{call pkg_gbm_cbs_oth_bank_dtls.sp_mod (?,?,?,?,?,?)}"),
    /*Other bank Details -----------------------Ends */
    /*User Work Class Information Details -----------------------Starts */
    PKG_GBM_CBS_USER_DTLS_SP_ADD("{call pkg_gbm_cbs_user_dtls.sp_add (?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_MOD_SEL("{call pkg_gbm_cbs_user_dtls.sp_mod_sel(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_INQ("{call pkg_gbm_cbs_user_dtls.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_VER_SEL("{call pkg_gbm_cbs_user_dtls.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_VER("{call pkg_gbm_cbs_user_dtls.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_DEL("{call pkg_gbm_cbs_user_dtls.sp_del(?,?,?,?,?)}"),
    PKG_GBM_CBS_USER_DTLS_SP_MOD("{call pkg_gbm_cbs_user_dtls.sp_mod (?,?,?,?,?,?)}"),
    /*User Work Class Information -----------------------Ends */

    pkg_gbm_cbs_ofyc_acct_pl_hldr_add("{call pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_add(?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_mod_sel("{call pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_mod_sel(?,?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_mod("{call  pkg_gbm_cbs_ofyc_acct_pl_hldr.SP_MOD(?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_inq("{call  pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_ver_sel("{call  pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_ver("{call  pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_ver(?,?,?,?,?)}"),
    pkg_gbm_cbs_ofyc_acct_pl_hldr_del("{call  pkg_gbm_cbs_ofyc_acct_pl_hldr.sp_del(?,?,?,?,?)}"),


    pkg_gbm_cbs_schm_codes_add("{call pkg_gbm_cbs_schm_codes.sp_add(?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_inq("{call pkg_gbm_cbs_schm_codes.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_ver_sel("{call pkg_gbm_cbs_schm_codes.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_ver("{call pkg_gbm_cbs_schm_codes.sp_ver(?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_del("{call pkg_gbm_cbs_schm_codes.sp_del(?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_mod_sel("{call pkg_gbm_cbs_schm_codes.sp_mod_sel(?,?,?,?,?,?)}"),
    pkg_gbm_cbs_schm_codes_mod("{call pkg_gbm_cbs_schm_codes.sp_mod(?,?,?,?,?,?,?,?)}"),



    PKG_GBM_CBS_INSTRUMENT_DET_ADD("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_add(?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_DET_INQ("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_DET_VER_SEL("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_DET_MOD_SEL("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_mod_sel(?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_DET_VER("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_MOD("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_mod(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_INSTRUMENT_DET_DEL("{call PKG_GBM_CBS_INSTRUMENT_DET.sp_del(?,?,?,?,?)}"),


    sp_gbm_cbs_pop_sol_date("{call sp_gbm_cbs_pop_sol_date(?,?,?,?,?,?,?,?)}"),
    sp_gbm_cbs_pop_sol_date_inq("{call sp_gbm_cbs_pop_sol_date_inq(?,?,?,?)}"),


    PKG_GBM_CBS_BRNCHS_BANKS_ADD("{call PKG_GBM_CBS_BRNCHS_BANKS.sp_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_INQ("{call PKG_GBM_CBS_BRNCHS_BANKS.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_VER_SEL("{call  PKG_GBM_CBS_BRNCHS_BANKS.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_VER("{call  PKG_GBM_CBS_BRNCHS_BANKS.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_DEL("{call  PKG_GBM_CBS_BRNCHS_BANKS.sp_del(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_MOD_SEL("{call PKG_GBM_CBS_BRNCHS_BANKS.sp_mod_sel(?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_BRNCHS_BANKS_MOD("{call PKG_GBM_CBS_BRNCHS_BANKS.SP_MOD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
/* State Master */
    PKG_STATE_MASTER_ADD("{call pkg_gbm_rbi_bond_state_master.sp_add(?,?,?,?,?)}"),

    PKG_STATE_MASTER_INQUIRY("{call pkg_gbm_rbi_bond_state_master.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_STATE_MASTER_VERIFY_SELECT("{call pkg_gbm_rbi_bond_state_master.sp_ver_Sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    PKG_STATE_MASTER_VERIFY("{call pkg_gbm_rbi_bond_state_master.sp_ver(?,?,?,?)}"),

    PKG_STATE_MASTER_DELETE("{call pkg_gbm_rbi_bond_state_master.sp_del(?,?,?,?,?)}"),


    /*CBS BULK UPLOAD*/
    PKG_CBS_BLUK_UPLOAD("{call PKG_CBS_BLUK_UPLOAD.sp_upload(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_CBS_BLUK_UPLOAD_VER_SEL("{call PKG_CBS_BLUK_UPLOAD.sp_ver_sel(?,?,?,?,?,?,?)}"),
    PKG_CBS_BLUK_UPLOAD_VER("{call PKG_CBS_BLUK_UPLOAD.sp_ver(?,?,?,?,?,?)}"),
    PKG_CBS_BLUK_UPLOAD_DEL("{call PKG_CBS_BLUK_UPLOAD.sp_del(?,?,?,?,?,?,?)}")
    ;


    private final String procName;

    ProcNameEnum(String procName) {
        this.procName = procName;
    }


    public String getProcName() {
        System.out.println("sql procedure" + procName);
        return procName;
    }
}
