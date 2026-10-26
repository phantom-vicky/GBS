package com.isl.gbm.common.util;

public enum IGProcNameEnum {

    /*API INTEGRATION */
    PKG_GBM_ICEGATE_API_INT_SP_INSERT ("{call pkg_gbm_igate_stg_flow.sp_put_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_API_INT_SP_VALIDATION ("{call PKG_GBM_IGATE_INET.SP_VALIDATION_API(?,?,?)}"),
    PKG_GBM_ICEGATE_API_INT_SP_INQ ("{call PKG_GBM_IGATE_INET.SP_INQUIRY_API(?,?,?,?,?)}"),

/*DOUBLE VERIFICATION */
    PKG_GBM_ICEGATE_API_DOUBLE_VERIFY ("{call SP_IGATE_DOUBLE_VER(?,?,?,?,?,?,?)}"),


/* eod push */
   PKG_GBM_ICEGATE_EOD_PUSH_API_REQ ("{call PKG_GBM_IGATE_EOD_API.SP_REQUEST(?,?,?,?)}"),
    SP_GBM_IGATE_14PAY_STUB  ("{call SP_GBM_IGATE_14PAY_STUB (?,?,?)}"),
/*eod push response*/
PKG_GBM_ICEGATE_EOD_PUSH_API_RES("{call PKG_GBM_IGATE_EOD_API.SP_RESPONSE(?,?,?,?,?,?,?)}"),

/*eod pull api */
PKG_GBM_ICEGATE_EOD_PULL_MANUAL_API_REQ("{call SP_GBM_IGATE_EOD_MANUAL_INT(?,?,?,?)}"),
    SP_GBM_IGATE_15PAY_STUB ("{call SP_GBM_IGATE_15PAY_STUB(?,?,?,?)}"),

PKG_GBM_ICEGATE_EOD_PULL_API_REQ("{call PKG_GBM_IGATE_EOD_INT_API.SP_REQ(?,?,?)}"),
   /*eod pull response*/
   PKG_GBM_ICEGATE_EOD_PULL_API_RES("{call PKG_GBM_IGATE_EOD_INT_API.SP_RES(?,?,?)}"),


   /*Icegate Scheme Parameters starts here*/
    PKG_GBM_ICEGATE_SCHM_PARAM_ADD_VAL("{call PKG_GBM_IGATE_SCHM_PARAM.SP_ADD_VAL(?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_ADD("{call PKG_GBM_IGATE_SCHM_PARAM.SP_ADD(?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_MOD_SEL("{call PKG_GBM_IGATE_SCHM_PARAM.SP_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_MOD("{call PKG_GBM_IGATE_SCHM_PARAM.SP_MOD(?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_VER_SEL("{call PKG_GBM_IGATE_SCHM_PARAM.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_VER("{call PKG_GBM_IGATE_SCHM_PARAM.SP_VER(?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_DEL("{call PKG_GBM_IGATE_SCHM_PARAM.SP_DEL(?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SCHM_PARAM_INQ("{call PKG_GBM_IGATE_SCHM_PARAM.SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Icegate Scheme Parameters ends here*/

    /*Icegate Remittance Calender Maintenance starts here*/
    REMITTANCE_CALENDER_MAINTENANCE_ADD("{call PKG_GBM_IGATE_REMIT_CAL.sp_add(?,?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_MODIFY("{call PKG_GBM_IGATE_REMIT_CAL.sp_mod(?,?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_VERIFY("{call PKG_GBM_IGATE_REMIT_CAL.sp_ver(?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_DELETE("{call PKG_GBM_IGATE_REMIT_CAL.sp_del(?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_MODIFY_SELECT("{call PKG_GBM_IGATE_REMIT_CAL.sp_mod_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_INQUIRY("{call PKG_GBM_IGATE_REMIT_CAL.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    REMITTANCE_CALENDER_MAINTENANCE_VERIFY_SELECT("{call PKG_GBM_IGATE_REMIT_CAL.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Icegate Remittance Calender Maintenance ends here*/

    /*Level Master - Starts */
    PKG_GBM_IGATE_LEVEL_MASTER_SP_ADD("{ call PKG_GBM_IGATE_LEVEL_MASTER.sp_add(?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_SP_INQ("{ call PKG_GBM_IGATE_LEVEL_MASTER.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_SP_VER_SEL("{ call PKG_GBM_IGATE_LEVEL_MASTER.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_SP_VER("{ call PKG_GBM_IGATE_LEVEL_MASTER.sp_ver(?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_SP_DEL("{ call PKG_GBM_IGATE_LEVEL_MASTER.sp_del(?,?,?,?) }"),
    /*Level Master - Ends */


    /*Level Master Mapping- Starts */
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_TOR("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_ins_tor(?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_ADD("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_ins(?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_INQ("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_MOD_SEL("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_mod_sel(?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_VER_SEL("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_VER("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_ver(?,?,?,?,?,?,?) }"),
    PKG_GBM_IGATE_LEVEL_MASTER_MAP_MOD("{ call  PKG_GBM_IGATE_LEVEL_MASTER_MAP.sp_mod(?,?,?,?,?,?,?,?) }"),
    /*Level Master Mapping- Ends */

    SP_GBM_ICEGATE_FETCH_METADATA("{ call SP_GBM_IGATE_FETCH_METADATA(?,?,?,?) }"),

    GENERIC_MODIFY("{ call SP_GBM_PP_GENERIC_MODIFY(?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_SEL_VERIFY("{ call SP_GBM_PP_GENERIC_SEL_VERIFY(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_VERIFY("{ call SP_GBM_PP_GENERIC_VERIFY(?,?,?,?,?,?,?,?,?) }"),

    GENERIC_DELETE("{ call SP_GBM_PP_GENERIC_DELETE(?,?,?,?,?,?,?,?) }"),

    GENERIC_SEL_INQUIRY("{ call SP_GBM_PP_GENERIC_INQUIRY(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_LIST_UNVER("{call SP_GBM_PP_GENERIC_UNVER(?,?,?,?,?,?,?,?)}"),
    sp_gbm_fetch_bankname("{call sp_gbm_fetch_bankname(?,?,?)}"),
    /*CbdtCommonService Metadata - Ends */

    /*CbdtCommonService Metadata - Starts */
    SP_GBM_PPF_FETCH_METADATA("{ call SP_GBM_PPF_FETCH_METADATA(?,?,?,?) }"),
    GENERIC_SELECT("{ call SP_GBM_PP_GENERIC_SELECT(?,?,?,?,?,?)}"),
    GENERIC_INSERT("{ call SP_GBM_PP_GENERIC_INSERT(?,?,?,?,?,?,?,?) }"),

    SP_GBM_IGATE_META_DATA("{call SP_GBM_IGATE_META_DATA(?,?,?,?,?,?)}"),











    //will remove later

    /*CbdtCommonService Metadata - Starts */
    SP_GBM_SCSS_FETCH_METADATA("{ call SP_GBM_SCSS_FETCH_METADATA(?,?,?,?) }"),

    /*CbdtCommonService Metadata - Ends */

    /*Interest Rate Code Maintanance - Starts */
    SP_GBM_SCSS_INT_RATE_INS("{ call SP_GBM_SCSS_INT_RATE_INS(?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_INQ("{ call SP_GBM_SCSS_INT_RATE_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_MOD("{ call SP_GBM_SCSS_INT_RATE_MOD(?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_MOD_SEL("{ call SP_GBM_SCSS_INT_RATE_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_VER_SEL("{ call SP_GBM_SCSS_INT_RATE_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_VER("{ call SP_GBM_SCSS_INT_RATE_VER(?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_INT_RATE_DEL("{ call SP_GBM_SCSS_INT_RATE_DEL(?,?,?,?,?,?) }"),
    /*Interest Rate Code Maintanance - Ends */

    /*Cbdt CRN Inquiry*/
    sp_gbm_icegate_rep_crn_inq("{ call sp_gbm_icegate_rep_crn_inq (?,?,?,?,?,?,?,?,?) }"),
    /*Cbdt CRN Inquiry*/
    /*Audit Log - Starts */
    SP_GBM_PP_REP_AUDIT_DC_SOL("{call SP_GBM_PP_REP_AUDIT_DC_SOL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_PP_REP_AUDIT_LOG("{call SP_GBM_PP_REP_AUDIT_LOG(?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Audit Log - Ends */

    /*Scheme Parameter - Starts*/
    SP_GBM_SCSS_SCH_PARA_INS("{ call SP_GBM_SCSS_SCH_PARA_INS(?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_MOD_SEL("{ call SP_GBM_SCSS_SCH_PARA_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_INS_VAL("{ call SP_GBM_SCSS_SCH_PARA_INS_VAL(?,?) }"),
    SP_GBM_SCSS_SCH_PARA_MOD("{ call SP_GBM_SCSS_SCH_PARA_MOD(?,?,?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_DEL("{ call SP_GBM_SCSS_SCH_PARA_DEL(?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_VER_SEL("{ call SP_GBM_SCSS_SCH_PARA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_VER("{ call SP_GBM_SCSS_SCH_PARA_VER(?,?,?,?,?) }"),
    SP_GBM_SCSS_SCH_PARA_INQ("{ call SP_GBM_SCSS_SCH_PARA_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    /*Scheme Parameter - Ends*/


    pkg_icegate_h2h_corp_master_add("{ call pkg_h2h_corp_master.sp_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_inq("{ call pkg_h2h_corp_master.sp_inq(?,?,?,?,?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_modSel("{ call pkg_h2h_corp_master.sp_mod_sel(?,?,?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_mod("{ call pkg_h2h_corp_master.sp_mod(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_ver_sel("{ call pkg_h2h_corp_master.sp_ver_sel(?,?,?,?,?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_delete("{ call pkg_h2h_corp_master.sp_del(?,?,?,?,?)}"),
    pkg_icegate_h2h_corp_master_verify("{ call pkg_h2h_corp_master.sp_ver(?,?,?,?,?)}"),
    sp_h2h_file_monitor_mod("{call sp_h2h_rep_file_monitor_mod(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    /*TOC Slab Sharing Maintainance - Starts */
    SP_GBM_SCSS_TOC_SHAR_INS_TOR("{ call SP_GBM_SCSS_TOC_SHAR_INS_TOR(?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_INS("{ call SP_GBM_SCSS_TOC_SHAR_INS(?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_INQ("{ call SP_GBM_SCSS_TOC_SHAR_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_VER_SEL("{ call SP_GBM_SCSS_TOC_SHAR_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_DEL("{ call SP_GBM_SCSS_TOC_SHAR_DEL(?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_VER("{ call SP_GBM_SCSS_TOC_SHAR_VER(?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_MOD_SEL("{ call SP_GBM_SCSS_TOC_SHAR_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_SCSS_TOC_SHAR_MOD("{ call SP_GBM_SCSS_TOC_SHAR_MOD(?,?,?,?,?,?,?,?)}"),
    /*TOC Slab Sharing Maintainance- Ends */


    /*ICEGATE -SYSTEM */
    PKG_GBM_ICEGATE_H2H_SYS_PARAM("{call PKG_GBM_ICEGATE_H2H_SYS_PARAM.sp_add(?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_H2H_SYS_PARAM_INQUIRY("{call PKG_GBM_ICEGATE_H2H_SYS_PARAM.sp_inq(?,?,?,?,?,?)}"),

    /*ICEGATE -Level Master Mapping- Starts */
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_INS_TOR("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_INS_TOR(?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_INS("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_INS(?,?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_INQ("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_MOD_SEL("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_VER_SEL("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_VER("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_VER(?,?,?,?,?,?,?) }"),
    PKG_GBM_ICEGATE_LEVEL_MASTER_MAP_SP_MOD("{ call PKG_GBM_ICEGATE_LEVEL_MASTER_MAP.SP_MOD(?,?,?,?,?,?,?,?) }"),
    /*ICEGATE - Level Master Mapping- Ends */

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_ADD_TOR("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP.SP_ADD_TOR(?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_ADD("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP.SP_ADD(?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_VER_SEL("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_VER("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP.SP_VER(?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_MOD_SEL("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP. SP_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_MOD("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP. SP_MOD(?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_INQ("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP. SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP_DEL("{call PKG_GBM_ICEGATE_BRAN_TRAN_TYP_MAP. SP_DEL(?,?,?,?,?,?,?)}"),


    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_PREV_PEND_CHALLANS("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_VAL_PREV_PEND_CHALLANS(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_ADD_VAL("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_ADD_VAL(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_ADD("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_ADD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_VER_SEL("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_VER_SEL_CLR("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_CRN_VAL("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_CRN_VAL(?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_CRN_CLR("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_CRN_VAL(?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_DEL("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_DEL(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_DEL_CLR("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_DEL(?,?,?,?,?)}"),


    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_INQ("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_INQ(?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_INQ_CLR("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_VER("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_VER("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_SP_VER("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_VER_RES("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_VER_RES(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_SP_VER_RES("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER_RES(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_VER_RES_CLR("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER_RES(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF_REMIT_ACK_RES("{call PKG_GBM_ICEGATE_TRAN_OTC_CSH_TNSF.SP_REMIT_ACK_RES(?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_REMIT_ACK_RES("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_REMIT_ACK_RES(?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF_MOD_SEL("{call PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF. SP_MOD_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF_MOD("{call PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF. SP_MOD(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF_VER_SEL("{call PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF_VER("{call PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF. SP_VER(?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF_INQ("{call PKG_GBM_ICEGATE_RECT_ERR_CSH_TNSF. SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_CRN_CRN_VALIDATION("{call PKG_GBM_ICEGATE_CRN. SP_CRN_VALIDATION(?,?,?,?)}"),

    PKG_GBM_ICEGATE_CRN_CRN_INSERTION("{call PKG_GBM_ICEGATE_CRN. SP_CRN_INSERTION(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_SP_VER_SEL("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?)}"),

    GBM_ICEGATE_ITR_SOL_TR_MNT_SP_REMIT_ACK_RES("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_REMIT_ACK_RES(?,?,?,?,?,?,?)}"),

    GBM_ICEGATE_ITR_SOL_TR_MNT_SP_CRN_VAL("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_CRN_VAL(?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_sp_inq("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ITR_SOL_TR_MNT_SP_DEL("{call PKG_GBM_ICEGATE_ITR_SOL_TR_MNT.SP_DEL(?,?,?,?,?)}"),

    pkg_gbm_icegate_lug_rbi_sp_get_payment_date("call pkg_gbm_IGATE_lug_rbi.sp_get_payment_date(?,?,?,?,?,?,?)"),

    sp_gbm_igate_rbi_lugg_sum("{call sp_gbm_igate_rbi_lugg_sum(?,?,?,?,?,?)}"),
    sp_gbm_igate_lugg_sum("{call sp_gbm_igate_lugg_sum(?,?,?,?,?)}"),

    PKG_GBM_IGATE_LEVEL_MASTER_ADD("{call PKG_GBM_IGATE_LEVEL_MASTER.sp_add(?,?,?,?,?)}"),
    PKG_GBM_IGATE_LEVEL_MASTER_VER_SEL("{call PKG_GBM_IGATE_LEVEL_MASTER.sp_ver_sel(?,?,?,?,?)}"),
    PKG_GBM_IGATE_LEVEL_MASTER_VER("{call PKG_GBM_IGATE_LEVEL_MASTER.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_IGATE_LEVEL_MASTER_INQ("{call PKG_GBM_IGATE_LEVEL_MASTER.sp_inq(?,?,?,?,?)}"),
    PKG_GBM_IGATE_LEVEL_MASTER_DEL("{call PKG_GBM_IGATE_LEVEL_MASTER.sp_del(?,?,?,?,?)}"),
    /*Consolidated Funds Transfer to LC consolidation  A/c starts here*/

    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_ADD("{call PKG_GBM_IGATE_CON_TO_LC.sp_add(?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_ADD_VAL("{call PKG_GBM_IGATE_CON_TO_LC.sp_add_val(?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_VERIFY("{call PKG_GBM_IGATE_CON_TO_LC.sp_ver(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_VERIFY_RES("{call PKG_GBM_IGATE_CON_TO_LC.sp_ver_res(?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_VERIFY_SELECT("{call PKG_GBM_IGATE_CON_TO_LC.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_DELETE("{call PKG_GBM_IGATE_CON_TO_LC.sp_del(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_FUND_TRAN_TO_LC_INQUIRY("{call PKG_GBM_IGATE_CON_TO_LC.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    /*Consolidated Funds Transfer to LC consolidation  A/c ends here*/



 /*Funds Transfer to MajorHeads  A/c starts here*/

    pkg_gbm_igate_ft_maj_head_sp_add_val("{call pkg_gbm_igate_ft_maj_head.sp_add_val (?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_add("{call pkg_gbm_igate_ft_maj_head.sp_add (?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_inq ("{call pkg_gbm_igate_ft_maj_head.sp_inq  (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_ver_sel("{call pkg_gbm_igate_ft_maj_head.sp_ver_sel (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_ver("{call pkg_gbm_igate_ft_maj_head.sp_ver (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_ver_res("{call pkg_gbm_igate_ft_maj_head.sp_ver_res (?,?,?,?,?,?,?,?,?)}"),
    pkg_gbm_igate_ft_maj_head_sp_del("{call pkg_gbm_igate_ft_maj_head.sp_del (?,?,?,?,?,?,?,?)}"),


    /*Funds Transfer to MajorHeads  A/c End here*/









    /*Clearing Instrument starts here*/
    CLEARING_INSTRUMENT_ADD_TORCH("{ call pkg_gbm_icegate_clr_instr_reglr.sp_add_tor(?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_ADD_VALIDATION("{ call pkg_gbm_icegate_clr_instr_reglr.sp_add_val(?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_ADD("{ call pkg_gbm_icegate_clr_instr_reglr.sp_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_ADD("{ call pkg_gbm_icegate_cen_instr_reglr.sp_add(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_VERIFY_SELECT("{ call pkg_gbm_icegate_clr_instr_reglr.sp_ver_sel(?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_VERIFY_SELECT("{ call pkg_gbm_icegate_cen_instr_reglr.sp_ver_sel(?,?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_VERIFY("{ call pkg_gbm_icegate_clr_instr_reglr.sp_ver(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_VERIFY("{ call  pkg_gbm_icegate_cen_instr_reglr.sp_ver(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_VERIFY_RES("{ call pkg_gbm_icegate_clr_instr_reglr.sp_ver_res(?,?,?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_VERIFY_RES("{ call pkg_gbm_icegate_cen_instr_reglr.sp_ver_res(?,?,?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_DELETE("{ call pkg_gbm_icegate_clr_instr_reglr.sp_del(?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_DELETE("{ call pkg_gbm_icegate_cen_instr_reglr.sp_del(?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_INQUIRY("{ call pkg_gbm_icegate_clr_instr_reglr.sp_inq(?,?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_INQUIRY("{ call pkg_gbm_icegate_cen_instr_reglr.sp_inq(?,?,?,?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_UPDATE_REMIT_BOUNCE("{ call pkg_gbm_icegate_clr_instr_reglr.sp_remit_bounce_res(?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_UPDATE_REMIT_BOUNCE("{ call pkg_gbm_icegate_cen_instr_reglr.sp_remit_bounce_res(?,?,?,?,?,?)}"),
    CLEARING_INSTRUMENT_BOUNCE_REASON_LIST("{call pkg_gbm_icegate_clr_instr_reglr. sp_get_bounce_reasons(?,?,?)}"),
    /*Clearing Instrument end here*/


    //getBounceReason for Centralized Instrument Regularization//
    pkg_gbm_gst_cen_instr_reglr("{call pkg_gbm_gst_cen_instr_reglr.sp_get_bounce_reasons(?,?,?)}"),




    SP_GBM_ICEGATE_FIN_TRAN_INQ("{ call SP_GBM_ICEGATE_FIN_TRAN_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_FIN_TRAN_INQ_LC("{ call SP_GBM_ICEGATE_FIN_TRAN_INQ_LC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    sp_gbm_icegate_rep_fti("{ call sp_gbm_icegate_rep_fti(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    ICEGATE_TOC_CLAIM_ANNEXURE_SCROLL("{call PKG_GBM_ICEGATE_TOC_CLAIM_REPORT.SP_REPORT1(?,?,?,?,?,?,?,?)}"),

    sp_gbm_icegate_rep_unver_rec("{call sp_gbm_icegate_rep_unver_rec(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_CRN_BULK_UPD_CRN_BULK_UPD_SEL("{call PKG_GBM_ICEGATE_CRN_BULK_UPD.SP_CRN_BULK_UPD_SEL(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_CRN_BULK_UPD_CRN_BULK_UPD("{call PKG_GBM_ICEGATE_CRN_BULK_UPD. SP_CRN_BULK_UPD(?,?,?)}"),


    /*ICEGATE CRN API Inquiry starts here*/
    GBM_ICEGATE_API_CRN_INQUIRY("{ call pkg_gbm_icegate_crn_inquiry.sp_inq(?,?,?,?,?,?)}"),
    /*ICEGATE CRN API Inquiry ends here*/

    /*Reports starts here*/
    ICEGATE_SUMMARY_REPORT("{call SP_GBM_IGATE_REP_INET_SUMRY(?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_BRANCH_TRANSACTION_REPORT("{call SP_GBM_ICEGATE_REP_BRANCH_TRAN(?,?,?,?,?)}"),
    ICEGATE_OTC_DETAILS_REPORT("{call sp_gbm_icegate_rep_otc_det(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_cre_deb_tran("{call sp_gbm_icegate_rep_cre_deb_tran(?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_int_tran_det("{call SP_GBM_IGATE_REP_INET_TRAN_DET  (?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_TIN_PFMS_STAT("{call SP_GBM_ICEGATE_REP_TIN_PFMS_STAT(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_POST_RECON_STAT_TIN("{call SP_GBM_ICEGATE_REP_PO_REC_TIN(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_POST_RECON_STAT_PFMS("{call SP_GBM_ICEGATE_REP_PO_REC_PFMS(?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_out_cle_rej("{call sp_gbm_icegate_rep_out_cle_rej(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_INT_CD_TR_INQ("{call SP_GBM_ICEGATE_REP_INT_CD_TR_INQ(?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_SUM_LUG_RBI("{call SP_GBM_IGATE_REP_SUM_LUG_RBI(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_SUM_LUG_TIN("{call SP_GBM_ICEGATE_REP_SUM_LUG_TIN(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_SUM_LUG_PFMS("{call SP_GBM_ICEGATE_REP_SUM_LUG_PFMS(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_ack_file_dwn("{call sp_gbm_igate_rep_ack_file_dwn(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_e_deb_not_dow("{call sp_gbm_icegate_rep_e_deb_not_dow(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_dn_rbi_nt_fil("{call sp_gbm_icegate_rep_dn_rbi_nt_fil(?,?,?,?,?,?)}"),
   SP_GBM_IGATE_INET_TRAN_DET_REP("{call SP_GBM_IGATE_INET_TRAN_DET_REP(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_igate_inet_maj_head_rep("{call sp_gbm_igate_inet_maj_head_rep(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_igate_rep_ack_num_inq("{call sp_gbm_igate_rep_ack_num_inq(?,?,?,?,?)}"),
 sp_gbm_igate_eod_int_pull_api("{call sp_gbm_igate_eod_int_pull_api(?,?,?,?,?,?)}"),

    sp_gbm_icegate_rep_dn_rbi_st_fil("{call sp_gbm_icegate_rep_dn_rbi_st_fil(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_edms_fil_pfms("{call sp_gbm_icegate_rep_edms_fil_pfms(?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_EDMS_FIL_PFMS_SP_DMS_FILE_REQ("{call PKG_GBM_ICEGATE_EDMS_FIL_PFMS.SP_DMS_FILE_REQ(?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_EDMS_FIL_PFMS_SP_RES("{call PKG_GBM_ICEGATE_EDMS_FIL_PFMS.SP_RES(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_dn_m_int_fil("{call sp_gbm_icegate_rep_dn_m_int_fil(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_dn_m_nt_fil("{call sp_gbm_icegate_rep_dn_m_nt_fil(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_dn_re_ack_fl("{call sp_gbm_icegate_rep_dn_re_ack_fl(?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_thr_way_rec("{call sp_gbm_icegate_rep_thr_way_rec(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_MOE("{call SP_GBM_ICEGATE_REP_MOE(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_ST_RBI_LG_FL("{call SP_GBM_ICEGATE_REP_ST_RBI_LG_FL(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_ICEGATE_RBI_LG_FL("{call pkg_gbm_IGATE_rbi_download.sp_get_lug_files(?,?,?,?,?,?)}"),
    sp_non_avail_cbs("{call pkg_igbm_reports.sp_non_avail_cbs(?,?,?,?,?,?)}"),
    sp_all_details("{call pkg_igbm_reports.sp_all_details(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_ICEGATE_RBI_UPD_OF_DOWN("{call pkg_gbm_IGATE_rbi_download.sp_downlaoded_files_upd(?,?,?,?,?)}"),
    PKG_IGBM_REPORTS_SP_SUMMARY("{call PKG_IGBM_REPORTS.SP_SUMMARY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_ack_slip("{call sp_gbm_icegate_rep_ack_slip(?,?,?,?,?,?,?,?)}"),
    sp_gbm_igate_rep_e_deb_not_dow("{call sp_gbm_igate_rep_ekub_deb_not(?,?,?,?,?,?)}"),

    sp_gbm_igate_rep_dn_rbi_nt_fil("{call sp_gbm_igate_rep_dn_rbi_nt_fil(?,?,?,?,?,?)}"),
    /*Reports Ends here*/
    sp_gbm_icegate_rep_pre_eod_chk("{call sp_gbm_icegate_rep_pre_eod_chk(?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_val("{call sp_gbm_icegate_rep_val(?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_brwise_con_sum("{call sp_gbm_icegate_rep_brwise_con_sum(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_icegate_rep_dashboard("{call sp_gbm_icegate_rep_dashboard(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_RECON_RES_PFMS_SP_REQ("{call PKG_GBM_ICEGATE_RECON_RES_PFMS.SP_REQ(?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_RECON_RES_PFMS_SP_RES("{call PKG_GBM_ICEGATE_RECON_RES_PFMS.SP_RES(?,?,?,?,?,?,?,?,?)}"),







    PKG_GBM_CEN_BOD_ICEGATE_DATE("{call PKG_GBM_CEN_BOD_ICEGATE.SP_GET_BOD_DATE(?,?,?,?,?)}"),
    PKG_GBM_CEN_BOD_ICEGATE_LEV_BRANCH("{call PKG_GBM_CEN_BOD_ICEGATE.SP_GET_LEV_BRANCH(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CEN_BOD_ICEGATE_FINAL("{call PKG_GBM_CEN_BOD_ICEGATE.SP_GBM_ICEGATE_CEN_BOD(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CEN_EOD_ICEGATE_DATE("{call PKG_GBM_CEN_EOD_ICEGATE.SP_GET_EOD_DATE(?,?,?,?,?)}"),
    PKG_GBM_CEN_EOD_ICEGATE_LEV_BRANCH("{call PKG_GBM_CEN_EOD_ICEGATE.SP_GET_LEV_BRANCH(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CEN_EOD_ICEGATE_FINAL("{call PKG_GBM_CEN_EOD_ICEGATE.SP_GBM_ICEGATE_CEN_EOD(?,?,?,?,?,?,?,?)}"),

    SP_GBM_ICEGATE_CLR_ACK_SLIP_REP("{call SP_GBM_ICEGATE_CLR_ACK_SLIP_REP(?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_TOC_Annexure_2 ("{call sp_gbm_igate_toc_Annexure_2 (?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_TOC_ANNEXUR_2A("{call sp_gbm_igate_Annexure_2A(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_TOC_ANNEXUR_2B("{call sp_gbm_igate_Annexure_2B(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Reports Ends here*/
    sp_gbm_icegate_rep_ack_slip_api("{call sp_gbm_icegate_rep_ack_slip_api(?,?,?,?,?)}"),
    sp_get_lug_files("{call pkg_gbm_IGATE_rbi_download.sp_get_lug_files(?,?,?,?,?,?,?)}"),
    SP_GBM_ICEGATE_REP_MAJ_CON("{call SP_GBM_ICEGATE_REP_MAJ_CON(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Reports Ends here*/

    sp_gbm_gst_rep_st_dt_wise_coll("{call sp_gbm_gst_rep_st_dt_wise_coll(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    //gst _clearing_instrument_regularization_report
    SP_GBM_GST_REP_CLR_INS_REG("{call sp_gbm_gst_rep_clr_ins_reg(?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_BOD_GBM_ICEGATE_BOD("{call PKG_GBM_BOD_ICEGATE.SP_GBM_ICEGATE_BOD(?,?,?,?,?,?,?,?)}"),

    PKG_GBM_EOD_GBM_ICEGATE_EOD("{call PKG_GBM_EOD_ICEGATE.SP_GBM_ICEGATE_EOD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    /*ICEGATE TOC Claim Compuatation starts here*/
    ICEGATE_TOC_CLAIM_COMPUTATION_PROCESS_ADD("{call PKG_GBM_IGATE_TOC_COMPUTATION.sp_add (?,?,?,?,?,?)}"),
    ICEGATE_TOC_CLAIM_COMPUTATION_PROCESS_VERIFY("{call PKG_GBM_IGATE_TOC_COMPUTATION.sp_ver(?,?,?,?,?,?)}"),
    ICEGATE_TOC_CLAIM_COMPUTATION_PROCESS_DELETE("{call PKG_GBM_IGATE_TOC_COMPUTATION.sp_del(?,?,?,?,?,?)}"),
    ICEGATE_TOC_CLAIM_COMPUTATION_PROCESS_VERIFY_DEL_SELECT("{call PKG_GBM_IGATE_TOC_COMPUTATION.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_TOC_CLAIM_COMPUTATION_PROCESS_INQUIRY("{call PKG_GBM_IGATE_TOC_COMPUTATION.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*ICEGATE TOC Claim Compuatation ends here*/

    /* Luggage File Generation starts here*/
    ICEGATE_RBI_LUGGAGE_FILE_NEXT_SERIAL_NO("{call pkg_gbm_IGATE_lug_rbi.sp_file_next_num(?,?)}"),
    ICEGATE_RBI_LUGGAGE_FILE_REQ("{call pkg_gbm_IGATE_lug_rbi.sp_req(?,?,?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_RBI_LUGGAGE_FILE_RES("{call pkg_gbm_IGATE_lug_rbi.sp_res(?,?,?,?,?,?,?,?,?)}"),

    ICEGATE_TIN_LUGGAGE_FILE_NEXT_SERIAL_NO("{call PKG_GBM_ICEGATE_LUG_TIN.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_TIN_LUGGAGE_FILE_REQ("{call PKG_GBM_ICEGATE_LUG_TIN. sp_req(?,?,?,?,?,?,?,?)}"),
    ICEGATE_TIN_LUGGAGE_FILE_NOT_INCL("{call PKG_GBM_ICEGATE_LUG_TIN.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)}"),
    ICEGATE_TIN_LUGGAGE_FILE_RES("{call PKG_GBM_ICEGATE_LUG_TIN. sp_res(?,?,?,?,?,?,?,?,?)}"),

    ICEGATE_PFMS_LUGGAGE_FILE_NEXT_SERIAL_NO("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_PFMS_LUGGAGE_FILE_NEXT_SERIAL_NO_JSON("{call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_PFMS_LUGGAGE_FILE_REQ("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_REQ(?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_PFMS_LUGGAGE_FILE_NOT_INCL("{call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)}"),
    ICEGATE_PFMS_LUGGAGE_FILE_RES("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_RES(?,?,?,?,?,?,?,?,?,?)}"),

    /* Luggage File Generation ends here*/

    /* Luggage File Re-Generation starts here*/

    ICEGATE_RBI_REGEN_LUGGAGE_FILE_NEXT_SERIAL_NO("{call PKG_GBM_ICEGATE_LUG_RBI.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_RBI_REGEN_LUGGAGE_FILE_REQ("{call PKG_GBM_ICEGATE_LUG_REGEN_RBI. sp_req(?,?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_RBI_REGEN_LUGGAGE_FILE_RES("{call PKG_GBM_ICEGATE_LUG_REGEN_RBI. sp_res(?,?,?,?,?,?,?,?,?)}"),

    ICEGATE_TIN_REGEN_LUGGAGE_FILE_NEXT_SERIAL_NO("{call PKG_GBM_ICEGATE_LUG_TIN.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_TIN_REGEN_LUGGAGE_FILE_REQ("{call PKG_GBM_ICEGATE_LUG_REGEN_TIN. sp_req(?,?,?,?,?,?,?,?)}"),
    ICEGATE_TIN_REGEN_LUGGAGE_FILE_RES("{call PKG_GBM_ICEGATE_LUG_REGEN_TIN. sp_res(?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_TIN_REGEN_LUGGAGE_FILE_NOT_INCL("{call PKG_GBM_ICEGATE_LUG_TIN.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)}"),

    ICEGATE_PFMS_REGEN_LUGGAGE_FILE_NEXT_SERIAL_NO("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_FILE_NEXT_NUM(?,?)}"),
    ICEGATE_PFMS_REGEN_LUGGAGE_FILE_REQ("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_REQ(?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_PFMS_REGEN_LUGGAGE_FILE_NOT_INCL("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)}"),
    ICEGATE_PFMS_REGEN_LUGGAGE_FILE_RES("{call PKG_GBM_ICEGATE_LUG_PFMS.SP_RES(?,?,?,?,?,?,?,?,?,?)}"),

    /* Luggage File Re-Generation ends here*/


    PKG_GBM_ICEGATE_INET_TRAN_STAT_MOD("{call PKG_GBM_ICEGATE_INET_TRAN_STAT.SP_MOD(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_INET_TRAN_STAT_INQ("{call PKG_GBM_ICEGATE_INET_TRAN_STAT. SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_INET_TRAN_STAT_DEL("{call PKG_GBM_ICEGATE_INET_TRAN_STAT. SP_DEL(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_INET_TRAN_STAT_VER_SEL("{call PKG_GBM_ICEGATE_INET_TRAN_STAT. SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_INET_TRAN_STAT_VER("{call PKG_GBM_ICEGATE_INET_TRAN_STAT. SP_VER(?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_EFPB_PRE_EOD_CHK("{call PKG_GBM_ICEGATE_EFPB_PRE_EOD_CHK. sp_val_req(?,?,?,?,?,?)}"),


    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_ADD_VAL("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS.SP_ADD_VAL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_ADD("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS.SP_ADD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_INQ("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS. SP_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_DEL("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS. SP_DEL(?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_VER_SEL("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS. SP_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_VER_VAL("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS. SP_VER_VAL(?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_VER("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS. SP_VER(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CDBT_CC_DD_TRAN_DTLS_VER_RES("{call PKG_GBM_CDBT_CC_DD_TRAN_DTLS.SP_VER_RES(?,?,?,?,?,?,?,?,?)}"),


    PKG_GBM_ICEGATE_GET_LUGG_FILE_DET_RES("{call PKG_GBM_ICEGATE_GET_LUGG_FILE_DET. SP_RES(?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_SGNL_READINESS_LUGGFILES("{call PKG_GBM_ICEGATE_SGNL_READINESS. SP_LUGGFILES(?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_POST_RECO_RES_RECON_STAT_UPD("{call PKG_GBM_ICEGATE_POST_RECO_RES. SP_RECON_STAT_UPD(?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_DMS_FILE_PFMS_RES("{call PKG_GBM_ICEGATE_DMS_FILE_PFMS.SP_RES(?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ON_DEMAND_API_ICEGATE_INQ("{call PKG_GBM_ICEGATE_ON_DEMAND_API. SP_ICEGATE_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_SCROLL_ERR_INQ("{call PKG_GBM_ICEGATE_SCROLL_ERR. SP_INQ(?,?,?)}"),

    IGBM_CLIENT_INFO("{call PKG_IGBM_CRN_FLOW.SP_CLIENT_INFO(?,?,?,?,?,?)}"),

    ICEGATE_PFMS_LUGG_FILE_REQ("{call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_REQ(?,?,?,?,?,?,?,?)"),

    ICEGATE_PFMS_LUGG_FILE_NEXT_SERIAL_NO("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_FILE_NEXT_NUM(?,?)"),

    ICEGATE_PFMS_LUGG_FILE_RESP("call ICEGATE_PFMS_LUGG_FILE_REQ.SP_RES(?,?,?,?,?,?,?,?,? )" ),

    ICEGATE_PFMS_LUGG_FILE_NOT_INCL("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)"),

    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_LUGG_FILE_DET_SUMRY(?,?,?,?,?,?,?)"),
    ICEGATE_PFMS_SAVE_LUGG_FILE("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_SAVE_LUG_FILE(?,?,?,?,?,?)"),

    PKG_GBM_ICEGATE_SHARE_LUGG_PFMS_RES("{call PKG_GBM_ICEGATE_SHARE_LUGG_PFMS.SP_UPD_LUG_FILE_STAT(?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SHARE_LUGG_PFMS_REQ("{call PKG_GBM_ICEGATE_SHARE_LUGG_PFMS.SP_REQ_SUMRY_FILE_DET(?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SHARE_LUGG_PFMS_SP_GET_DETAILS("{call PKG_GBM_ICEGATE_SHARE_LUGG_PFMS.SP_GET_DETAILS(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_SHARE_LUGG_PFMS_DATA("{call PKG_GBM_ICEGATE_SHARE_LUGG_PFMS.SP_GET_LUG_FILE_DATA(?,?,?,?,?,?,?)}"),

    PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS_VAL("{call PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS.SP_VAL(?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS_REC("{call PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS.SP_CORRECTED_REC(?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS_RES("{call PKG_GBM_ICEGATE_ERRONEOUS_DETPFMS.SP_PFMS_RES(?,?,?,?,?,?,?,?,?)}"),


    /*Intimate War Deployment*/
    SP_GBM_WAR_DEP_ALERT("{call SP_GBM_WAR_DEP_ALERT(?,?,?)}"),

    /*Level Master - Starts */
    GBM_ICEGATE_HIR_LEVEL_INS("{ call GBM_ICEGATE_HIR_LEVEL_INS(?,?,?,?,?,?) }"),
    GBM_ICEGATE_HIR_LEVEL_SEL("{ call GBM_ICEGATE_HIR_LEVEL_SEL(?,?,?,?,?,?,?,?,?,?,?) }"),
    GBM_ICEGATE_HIR_LEVEL_SEL_VER("{ call GBM_ICEGATE_HIR_LEVEL_SEL_VER(?,?,?,?,?,?,?,?,?,?,?) }"),
    GBM_ICEGATE_HIR_LEVEL_VER("{ call GBM_ICEGATE_HIR_LEVEL_VER(?,?,?,?,?) }"),
    GBM_ICEGATE_HIR_LEVEL_DEL("{ call GBM_ICEGATE_HIR_LEVEL_DEL(?,?,?,?,?) }"),
    /*Level Master - Ends */
    /*TOC SLAB Maintenance-Starts*/

    SP_GBM_ICEGATE_TOC_SLAB_INS_TOR("{ call SP_GBM_SCSS_TOC_SLAB_MOD_SEL(?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_ADD("{ call sp_gbm_scss_toc_slab_ins(?,?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_INQ("{ call SP_GBM_SCSS_TOC_SLAB_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_VER_SEL("{ call SP_GBM_SCSS_TOC_SLAB_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_MOD_SEL("{ call SP_GBM_SCSS_TOC_SLAB_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_VER("{ call SP_GBM_SCSS_TOC_SLAB_VER(?,?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_MOD("{ call SP_GBM_SCSS_TOC_SLAB_MOD(?,?,?,?,?,?) }"),
    SP_GBM_ICEGATE_TOC_SLAB_DEL("{ call SP_GBM_SCSS_TOC_SLAB_DEL(?,?,?,?,?,?,?) }"),
    /*TOC SLAB Maintenance-Ends*/


    /*ICEGATE Bod / Eod Status Inquiry*/
    gbm_bod_eod_status_inq_icegate("{ call gbm_bod_eod_status_inq_icegate(?,?,?,?,?,?,?,?,?,?) }"),

    /*GST Bod / Eod Status Inquiry*/
    gbm_bod_eod_status_inq_gst("{ call gbm_bod_eod_status_inq_gst(?,?,?,?,?,?,?,?,?,?) }"),

    PKG_IGBM_REJ_CHALLANS_SP_GET_REF_NUM("{ call PKG_IGBM_REJ_CHALLANS.SP_GET_REF_NUM(?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_GET_REJ_REASON("{ call PKG_IGBM_REJ_CHALLANS.SP_GET_REJ_REASONS(?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_ADD_DET("{ call PKG_IGBM_REJ_CHALLANS.SP_ADD_TOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_ADD("{ call PKG_IGBM_REJ_CHALLANS.SP_ADD(?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_VER_SEL("{ call PKG_IGBM_REJ_CHALLANS.SP_VER_SEL(?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_VER("{ call PKG_IGBM_REJ_CHALLANS.SP_VER(?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_DEL("{ call PKG_IGBM_REJ_CHALLANS.SP_DEL(?,?,?,?,?)}"),
    PKG_IGBM_REJ_CHALLANS_SP_INQ("{ call PKG_IGBM_REJ_CHALLANS.SP_INQ(?,?,?,?,?)}"),

    PKG_IGBM_PROCESS_CHALLANS_SP_ADD_DET("{ call PKG_IGBM_PROCESS_CHALLANS.SP_GET_ADD_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_ADD("{ call PKG_IGBM_PROCESS_CHALLANS.SP_ADD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_VER_SEL("{ call PKG_IGBM_PROCESS_CHALLANS.SP_VER_SEL(?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_VER("{ call PKG_IGBM_PROCESS_CHALLANS.SP_VER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_DEL("{ call PKG_IGBM_PROCESS_CHALLANS.SP_DEL(?,?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_INQ("{ call PKG_IGBM_PROCESS_CHALLANS.SP_INQ(?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_VER_RES("{ call PKG_IGBM_PROCESS_CHALLANS.SP_VER_RES(?,?,?,?,?,?,?,?,? )}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_GET_CBS_DETAILS("{ call PKG_IGBM_PROCESS_CHALLANS.SP_GET_CBS_DETAILS(?,?,?,?,?)}"),
    PKG_IGBM_PROCESS_CHALLANS_SP_UPD_CBS_DETAILS("{ call PKG_IGBM_PROCESS_CHALLANS.SP_UPD_CBS_DETAILS(?,?,?,?,?)}"),

    SP_GET_PRO_REC_TIN_PFMS("{call PKG_IGBM_UPD_TIN_PFMS.SP_GET_PRO_REC_TIN_PFMS(?,?,?,?,?,?,?)}"),
    SP_UPD_TIN_PFMS_STAT("{call PKG_IGBM_UPD_TIN_PFMS.SP_UPD_TIN_PFMS_STAT(?,?,?,?,?,?)}"),

    ICEGATE_CRN_BULK_UPD_INET_sp_crn_bulk_upd_sel("{call PKG_GBM_ICEGATE_CRN_BULK_UPD_INET.sp_crn_bulk_upd_sel(?,?,?,?,?,?,?,?,?)}"),
    ICEGATE_CRN_BULK_UPD_INET_sp_crn_bulk_upd("{call  PKG_GBM_ICEGATE_CRN_BULK_UPD_INET.sp_crn_bulk_upd(?,?,?)}"),
    SP_UPD_TIN_PORTAL("{call PKG_IGBM_UPD_TIN_PFMS.SP_UPD_TIN_PORTAL(?,?,?,?,?,?)}"),

    SP_UPD_PFMS_PORTAL("{call PKG_IGBM_UPD_TIN_PFMS.SP_UPD_PFMS_PORTAL(?,?,?,?,?,?)}"),


    PKG_IGBM_UPD_STAGING_GET_STG("{call PKG_IGBM_UPD_STAGING.SP_GET_REC_TO_UPD_STG(?,?,?,?,?,?,?)}"),
    PKG_IGBM_UPD_STAGING_UPD_PROCESSED_REC("{call PKG_IGBM_STG.SP_UPD_PROCESSED_REC(?,?,?,?,?,?)}"),
    PKG_IGBM_UPD_STAGING_UPD_STAT("{call PKG_IGBM_UPD_STAGING.SP_UPD_STAGING_STAT(?,?,?,?,?,?)}"),
    PKG_IGBM_REPORTS_SP_CRN_SUCCESS_FAIL("{call PKG_IGBM_REPORTS.SP_CRN_SUCCESS_FAIL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_IGBM_REPORTS_SP_FINAL_PAYMENT_FAIL("{call PKG_IGBM_REPORTS.SP_FINAL_PAYMENT_FAIL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    PKG_GBM_CBS_RESTRIC_SCHM_INS_TOR("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_ins_tor(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_ADD("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_ins(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_INQ("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_inq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_VER("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_ver_sel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_VER_TOR("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_ver(?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_MOD_TOR("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_mod_sel(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_MOD("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_mod(?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_DEL_SEL("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_del_sel(?,?,?,?,?,?,?,?)}"),
    PKG_GBM_CBS_RESTRIC_SCHM_DEL("{call PKG_GBM_CBS_RESTRIC_SCHM.sp_del(?,?,?,?,?)}"),





    //    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_LUGG_FILE_DET_SUMRY(?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_REQ("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_REQ(?,?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_FILE_NEXT_NUM("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_FILE_NEXT_NUM(?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_REC_NOT_INCL_LIST("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_REC_NOT_INCL_LIST(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_PFMS_RES("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.sp_pfms_res(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_RES("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.sp_res(?,?,?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_RES_FINAL("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.sp_pfms_res(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_LUGG_FILE_DET_SUMRY("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_LUGG_FILE_DET_SUMRY(?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS_SP_SAVE_LUG_FILE("call PKG_GBM_ICEGATE_SIGNAL_INTI_PFMS.SP_SAVE_LUG_FILE(?,?,?,?,?,?)"),


    PKG_GBM_ICEGATE_RE_GEN_PFMS_FILE_NEXT_NUM("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_file_next_num(?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_SP_REQ("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_req(?,?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_SP_REC_NOT_INCL_LIST("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_rec_not_incl_list(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_PFMS_REV_UPD("call pkg_gbm_icegate_signal_inti_pfms.sp_cancel(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_SP_RES("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_res(?,?,?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_SP_LUGG_FILE_DET_SUMRY("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_lugg_file_det_sumry(?,?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_sp_save_lug_file("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_save_lug_file(?,?,?,?,?,?)"),
    PKG_GBM_ICEGATE_RE_GEN_PFMS_sp_pfms_res("call PKG_GBM_ICEGATE_LUG_REGEN_PFMS.sp_pfms_res(?,?,?,?,?,?)"),
//    pkg_gbm_icegate_lug_rbi_sp_get_payment_date("call pkg_gbm_IGATE_lug_rbi.sp_get_payment_date(?,?,?,?,?)"),
    pkg_gbm_icegate_cen_instr_reglr_sp_add_tor("{call pkg_gbm_icegate_cen_instr_reglr.sp_add_tor(?,?,?,?,?,?,?,?,?)}"),
    CLEARING_CENTRAL_INSTRUMENT_ADD_VALIDATION("{ call pkg_gbm_icegate_cen_instr_reglr.sp_add_val(?,?,?,?,?,?)}"),
    pkg_gbm_icegate_cen_instr_reglr_sp_get_bounce_reasons("{call pkg_gbm_icegate_cen_instr_reglr.sp_get_bounce_reasons(?,?,?)}"),
    pkg_gbm_gst_cen_instr_reglr_sp_get_bounce_reasons("{call pkg_gbm_icegate_cen_instr_reglr.sp_get_bounce_reasons(?,?,?)}"),

    sp_h2h_rep_file_process_status("{call sp_h2h_rep_file_process_status(?,?,?,?,?,?,?)}"),




    sp_h2h_rep_onboard_cus("{call sp_h2h_rep_onboard_cus(?,?,?,?,?,?,?)}"),


    sp_gbm_icegate_rep_otc_det("call sp_gbm_icegate_rep_otc_det(?,?,?,?,?,?,?,?,?)"),
    sp_gbm_icegate_rep_clr_stat_unk("call sp_gbm_icegate_rep_clr_stat_unk(?,?,?,?,?,?,?,?,?)"),
    SP_GBM_ICEGATE_REP_CLR_INS_REG("call SP_GBM_ICEGATE_REP_CLR_INS_REG(?,?,?,?,?,?,?,?,?,?)"),

    /*Reconciliation Report Start*/
    RECONCILIATION_GBM_CBS_DETAIL("{call PKG_GBM_IGATE_CBS_RECON_REP.sp_gbm_cbs_tran_reconcile(?,?,?,?)}"),
    RECONCILIATION_GBM_CBS_DETAIL1("{call  PKG_GBM_IGATE_CBS_RECON_REP.sp_gbm_cbs_gen_rep(?,?,?,?,?)}"),
    RECONCILIATION_GBM_CBS_DETAILREPORT("{call PKG_GBM_IGATE_CBS_RECON_REP.sp_gbm_cbs_tran_recon_status(?,?,?,?,?)}"),
    ICEGATE_RECONCILIATION_INSERT_DETAIL("{call PKG_GBM_IGATE_CBS_RECON_REP.sp_cbs_recon_file_ins_rep(?,?,?)}"),

    /*Reconciliation Report End*/

   SP_GBM_IGATE_RECONCILE_REPORT( "call SP_GBM_IGATE_RECONCILE_REPORT(?,?,?,?,?,?,?,?,?)"),
    sp_h2h_rep_corp_fileprocess("call sp_h2h_rep_corp_fileprocess(?,?,?,?,?,?,?,?)"),
            ;

    IGProcNameEnum(String procName) {
        this.procName = procName;
    }

    private final String procName;

    public String getProcName() {
        System.out.println("sql procedure" + procName);
        return procName;
    }
}
