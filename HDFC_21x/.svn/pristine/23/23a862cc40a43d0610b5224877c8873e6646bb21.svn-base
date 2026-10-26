package com.isl.gbm.common.util;

public enum ApyProcEnum {

    SP_GBM_PP_FETCH_METADATA("{ call SP_GBM_PP_FETCH_METADATA(?,?,?,?) }"),

    //proc to replace recursive meta data calls to single proc call
    SP_GBM_APY_META_DATA("{call SP_GBM_APY_META_DATA(?,?,?,?,?,?)}"),

    //proc to replace recursive generic data calls to single proc call
    SP_GBM_GEN_SEL_COM("{call SP_GBM_GEN_SEL_COM(?,?,?,?)}"),

    //proc to merge common generic and meta data calls to once call

    /*
      Generic Block
    */
    GENERIC_SELECT("{ call SP_GBM_PP_GENERIC_SELECT(?,?,?,?,?,?)}"),

    GENERIC_INSERT("{ call SP_GBM_PP_GENERIC_INSERT(?,?,?,?,?,?,?,?) }"),
    /**
     * {@code call SP_GBM_PP_GENERIC_MODIFY(tableName IN,columnValuesList
     * IN,primaryKeyValue IN,lastModifiedBy IN,moduleName IN,remark IN,featureName
     * IN,modifierId IN,outputMessage OUT,outputMessageDesc OUT)}
     *
     * @category Database Procedure Call
     */
    GENERIC_MODIFY("{ call SP_GBM_PP_GENERIC_MODIFY(?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_SEL_VERIFY("{ call SP_GBM_PP_GENERIC_SEL_VERIFY(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_VERIFY("{ call SP_GBM_PP_GENERIC_VERIFY(?,?,?,?,?,?,?,?,?) }"),

    GENERIC_DELETE("{ call SP_GBM_PP_GENERIC_DELETE(?,?,?,?,?,?,?,?) }"),

    GENERIC_SEL_INQUIRY("{ call SP_GBM_PP_GENERIC_INQUIRY(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }"),

    GENERIC_LIST_UNVER("{call SP_GBM_PP_GENERIC_UNVER(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCH_PARA_INS("{ call SP_GBM_APY_SCH_PARA_INS(?,?,?,?,?) }"),

    SP_GBM_APY_SCH_PARA_MOD_SEL("{call SP_GBM_APY_SCH_PARA_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCH_PARA_SEL_VER("{call SP_GBM_APY_SCH_PARA_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCH_PARA_MOD("{call SP_GBM_APY_SCH_PARA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_SCH_PARA_INS_VAL("{call SP_GBM_APY_SCH_PARA_INS_VAL(?,?)}"),

    SP_GBM_APY_SCH_PARA_VER("{call SP_GBM_APY_SCH_PARA_VER(?,?,?,?,?,?)}"),
    SP_GBM_APY_SCH_PARA_DEL("{call SP_GBM_APY_SCH_PARA_DEL(?,?,?,?,?,?)}"),

    SP_GBM_APY_SCH_PARA_INQ("{call SP_GBM_APY_SCH_PARA_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    //procs for hierarchy maintenance

    SP_GBM_APY_HIE_LEV_MST_INS("{call SP_GBM_APY_HIE_LEV_MST_INS(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_MOD("{call SP_GBM_APY_HIE_LEV_MST_MOD(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_VER("{call SP_GBM_APY_HIE_LEV_MST_VER(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIERARCHY_VER("{call SP_GBM_APY_HIERARCHY_VER(?,?,?,?,?,?,?)"),
    SP_GBM_APY_HIERARCHY_DEL("{call SP_GBM_APY_HIERARCHY_DEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIERARCHY_MOD_SEL("{call SP_GBM_APY_HIERARCHY_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIERARCHY_VER_SEL("{call SP_GBM_APY_HIERARCHY_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIERARCHY_INS_TOR("{call SP_GBM_APY_HIERARCHY_INS_TOR(?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_INS_TOR("{call SP_GBM_APY_HIE_LEV_MST_INS_TOR(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_MOD_SEL("{call SP_GBM_APY_HIE_LEV_MST_MOD_SEL(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_VER_SEL("{call SP_GBM_APY_HIE_LEV_MST_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_HIE_LEV_MST_INQ("{call SP_GBM_APY_HIE_LEV_MST_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    //procs for level master
    GBM_APY_HIR_LEVEL_INS("{call GBM_APY_HIR_LEVEL_INS(?,?,?,?,?,?)}"),
    GBM_APY_HIR_LEVEL_VER("{call GBM_APY_HIR_LEVEL_VER(?,?,?,?,?)}"),
    GBM_APY_HIR_LEVEL_DEL("{call GBM_APY_HIR_LEVEL_DEL(?,?,?,?,?)}"),
    GBM_APY_HIR_LEVEL_SEL("{call GBM_APY_HIR_LEVEL_SEL(?,?,?,?,?,?,?,?,?,?,?)}"),
    GBM_APY_HIR_LEVEL_SEL_VER("{call GBM_APY_HIR_LEVEL_SEL_VER(?,?,?,?,?,?,?,?,?,?,?)}"),

    //procs for contribution
    SP_GBM_APY_CONTRIBUTE_INS_SEL("{call SP_GBM_APY_CONTRIBUTE_INS_SEL(?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTE_MOD_SEL("{call SP_GBM_APY_CONTRIBUTE_MOD_SEL(?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTION_INS("{call SP_GBM_APY_CONTRIBUTE_INS(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTION_MOD("{call SP_GBM_APY_CONTRIBUTE_MOD(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTE_VER("{call SP_GBM_APY_CONTRIBUTE_VER(?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTE_DEL("{call SP_GBM_APY_CONTRIBUTE_DEL(?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTE_VER_SEL("{call SP_GBM_APY_CONTRIBUTE_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CONTRIBUTE_INQ("{call SP_GBM_APY_CONTRIBUTE_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"),

    //pran upload for cra pran upload

    SP_GBM_APY_PRAN_UPLOAD("{call SP_GBM_APY_PRAN_UPLOAD(?,?,?,?,?,?,?,?,?)}"),

    //procs for acknowledgement number

    GBM_APY_DC_ACK_NUM_MAS_INS("{call GBM_APY_DC_ACK_NUM_MAS_INS(?,?,?,?,?,?,?,?)}"),

    GBM_APY_DC_ACK_NUM_MAS_VER("{call GBM_APY_DC_ACK_NUM_MAS_VER(?,?,?,?,?,?,?)}"),

    GBM_APY_DC_ACK_NUM_MAS_DEL("{call GBM_APY_DC_ACK_NUM_MAS_DEL(?,?,?,?,?,?,?)}"),

    GBM_APY_DC_ACK_NUM_MAS_INQ("{call GBM_APY_DC_ACK_NUM_MAS_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    GBM_APY_DC_ACK_NUM_MAS_VER_SEL("{call GBM_APY_DC_ACK_NUM_MAS_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    //bod eod enums
    SP_GBM_APY_BOD_EOD_INITIATE("{call SP_GBM_APY_BOD_INITIATE(?,?,?,?,?,?)}"),

    SP_GBM_APY_BOD_EOD_COMPLETE("{call SP_GBM_APY_BOD_COMPLETE(?,?,?,?,?,?)}"),

    //agent master procs
    SP_GBM_APY_AGENT_MST_INS("{call SP_GBM_APY_AGENT_MST_INS(?,?,?,?,?,?)}"),

    SP_GBM_APY_AGENT_MST_MOD_SEL("{call SP_GBM_APY_AGENT_MST_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    SP_GBM_APY_AGENT_MST_VER_SEL("{call SP_GBM_APY_AGENT_MST_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_AGENT_MST_INQ("{call SP_GBM_APY_AGENT_MST_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_AGENT_MST_MOD("{call SP_GBM_APY_AGENT_MST_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_AGENT_MST_VER("{call SP_GBM_APY_AGENT_MST_VER(?,?,?,?,?)}"),
    SP_GBM_APY_AGENT_MST_DEL("{call SP_GBM_APY_AGENT_MST_DEL(?,?,?,?,?)}"),

    //subscriber registration procs

    SP_GBM_APY_SUB_REG_INS("{call SP_GBM_APY_SUB_REG_INS(?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_MOD_SEL("{call SP_GBM_APY_SUB_REG_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_MOD("{call SP_GBM_APY_SUB_REG_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_DEL("{call SP_GBM_APY_SUB_REG_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_INS_TOR("{call SP_GBM_APY_SUB_REG_INS_TOR(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_VER_SEL("{call SP_GBM_APY_SUB_REG_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_VER("{call SP_GBM_APY_SUB_REG_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_INQ("{call SP_GBM_APY_SUB_REG_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_REG_MOD_VAL("{call SP_GBM_APY_SUB_REG_MOD_VAL(?,?,?)}"),

    SP_GBM_APY_SUB_REG_BANK_TOR("{call SP_GBM_APY_SUB_REG_BANK_TOR(?,?,?,?,?,?,?)}"),


    //procs for subscriber modification
    SP_GBM_APY_SUB_MOD_MOD_SEL("{call SP_GBM_APY_SUB_MOD_MOD_SEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_SUB_MOD_MOD("{call SP_GBM_APY_SUB_MOD_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_SUB_MOD_DEL("{call SP_GBM_APY_SUB_MOD_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_SUB_MOD_VER("{call SP_GBM_APY_SUB_MOD_VER(?,?,?,?,?)}"),
    SP_GBM_APY_SUB_MOD_VER_SEL("{call SP_GBM_APY_SUB_MOD_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_SUB_MOD_INQ("{call SP_GBM_APY_SUB_MOD_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    /*procs for FACTA file generation - starts*/
    SP_GBM_APY_GEN_FATCA_REQ("{call SP_GBM_APY_GEN_FATCA_REQ(?,?,?,?,?)}"),
    SP_GBM_APY_GEN_FATCA_RES("{call SP_GBM_APY_GEN_FATCA_RES(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_GEN_FATCA_DWN("{call SP_GBM_APY_GEN_FATCA_DWN(?,?,?,?)}"),
    /*procs for FACTA file generation - Ends*/

    /*procs for  - Generation of Subscriber Contribution File (ODC) for DOB Change Second Level file generation - starts*/
    SP_GBM_APY_GEN_DOB_SCF_REQ("{call SP_GBM_APY_GEN_DOB_SCF_REQ(?,?,?,?,?,?)}"),
    SP_GBM_APY_GEN_DOB_SCF_RES("{call SP_GBM_APY_GEN_DOB_SCF_RES(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_GEN_DOB_SCF_DWN("{call SP_GBM_APY_GEN_DOB_SCF_DWN(?,?,?,?)}"),


    /*procs for  - Generation of Subscriber Contribution File (ODC) for DOB Change Second Level file generation - Ends*/

    //procs for srf file generation
    SP_GBM_APY_GEN_SRF_REQ("{call SP_GBM_APY_GEN_SRF_REQ(?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SRF_RES("{call SP_GBM_APY_GEN_SRF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SRF_DWN("{call SP_GBM_APY_GEN_SRF_DWN(?,?,?,?)}"),

    SP_GBM_APY_CRA_STATUS_INS("{call SP_GBM_APY_CRA_STATUS_INS(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_CRA_STATUS_VER("{call SP_GBM_APY_CRA_STATUS_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_CRA_STATUS_SEL_VER("{call SP_GBM_APY_CRA_STATUS_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_CRA_STATUS_DEL("{call SP_GBM_APY_CRA_STATUS_DEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CRA_STATUS_INQ("{call SP_GBM_APY_CRA_STATUS_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    //rectification of rejected srf file

    SP_GBM_APY_FR_RECTIFY_MOD("{call SP_GBM_APY_FR_RECTIFY_MOD(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_FR_RECTIFY_VER("{call SP_GBM_APY_FR_RECTIFY_VER(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_FR_RECTIFY_DEL("{call SP_GBM_APY_FR_RECTIFY_DEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_FR_RECTIFY_INQ("{call SP_GBM_APY_FR_RECTIFY_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    //procs for regeneration of srf file

    SP_GBM_APY_REGEN_SRF_REQ("{call SP_GBM_APY_REGEN_SRF_REQ(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_SRF_RES("{call SP_GBM_APY_REGEN_SRF_RES(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_SRF_DWN("{call SP_GBM_APY_REGEN_SRF_DWN(?,?,?,?)}"),

    SP_GBM_APY_GEN_SMD_REQ("{call SP_GBM_APY_GEN_SMD_REQ(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SMD_RES("{call SP_GBM_APY_GEN_SMD_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SMD_DWN("{call SP_GBM_APY_GEN_SMD_DWN(?,?,?,?)}"),

    // APY BOD & EOD
    APY_BOD("{call PKG_GBM_BOD_APY.SP_GBM_APY_BOD(?,?,?,?,?,?,?,?)}"),

    APY_EOD("{call PKG_GBM_EOD_APY.SP_GBM_APY_EOD(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    SP_GBM_APY_REP_STA_INS_REG("{call SP_GBM_APY_REP_STA_INS_REG(?,?,?,?,?,?)}"),

    SP_GBM_APY_REP_P_STA_INS_REG("{call SP_GBM_APY_REP_P_STA_INS_REG(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_ACC_OPN("{call SP_GBM_APY_REP_ACC_OPN(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REJ_REG_POP_SP_REP("{call SP_GBM_APY_REJ_REG_POP_SP_REP(?,?,?,?,?,?)}"),
    SP_GBM_APY_SUB_DET_POP_SP_REP("{call SP_GBM_APY_SUB_DET_POP_SP_REP(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_SUB_DET_MOD("{call SP_GBM_APY_REP_SUB_DET_MOD(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_SUB_MOD_SP("{call SP_GBM_APY_REP_SUB_MOD_SP(?,?,?,?,?,?)}"),
    SP_GBM_APY_FIN_TRAN_INQ("{call SP_GBM_APY_FIN_TRAN_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_UNV_REC("{call SP_GBM_APY_REP_UNV_REC(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_CRA_RES_STATUS("{call SP_GBM_APY_REP_CRA_RES_STATUS(?,?,?,?,?)}"),
    sp_gbm_apy_rep_dob_cra_popsp("{call sp_gbm_apy_rep_dob_cra_popsp(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_CRA_RES_STA_POP("{call SP_GBM_APY_REP_CRA_RES_STA_POP(?,?,?,?,?)}"),
    sp_gbm_apy_rep_dob_popsp("{call sp_gbm_apy_rep_dob_popsp(?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_fqy_pop("{call sp_gbm_apy_rep_fqy_pop(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_fqy_pop_sp("{call sp_gbm_apy_rep_fqy_pop_sp(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_ack_vol_popsp("{call sp_gbm_apy_rep_ack_vol_popsp(?,?,?,?,?)}"),
    sp_gbm_apy_rep_ack_vol_pop("{call sp_gbm_apy_rep_ack_vol_pop(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_TRAN_IN_OUT("{call SP_GBM_APY_REP_TRAN_IN_OUT(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_dob_pop("{call sp_gbm_apy_rep_dob_pop(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_dob_cra_pop("{call sp_gbm_apy_rep_dob_cra_pop(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_SUM_COL_POP("{call SP_GBM_APY_REP_SUM_COL_POP(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_SUM_COL_POP_SP("{call SP_GBM_APY_REP_SUM_COL_POP_SP(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_SUB_MOD_POP("{call SP_GBM_APY_REP_SUB_MOD_POP(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_ACK_SLP("{call SP_GBM_APY_REP_ACK_SLP(?,?,?,?,?,?,?,?)}"),
    P_MSG_DESC_OUT("{call P_MSG_DESC_OUT(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_REJ_REG("{call SP_GBM_APY_REP_REJ_REG(?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_ACC_CAN_AGENT("{call SP_GBM_APY_REP_ACC_CAN_AGENT(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_POPSP_CAN_AGENT("{call SP_GBM_APY_REP_POPSP_CAN_AGENT(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_PAN_UP_ALLSTA("{call SP_GBM_APY_REP_PAN_UP_ALLSTA(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_REP_APY_FATCA_POP("{call SP_GBM_APY_REP_APY_FATCA_POP(?,?,?,?)}"),
    SP_GBM_APY_REP_APY_FAT_POPSP("{call SP_GBM_APY_REP_APY_FAT_POPSP(?,?,?,?)}"),
    SP_GBM_APY_REP_ACC_OPN_DAT("{call SP_GBM_APY_REP_ACC_OPN_DAT(?,?,?,?,?,?)}"),
    //procs for upgradation and downgradation

    SP_GBM_APY_UPG_DOWNG_INS_TOR("{call SP_GBM_APY_UPG_DOWNG_INS_TOR(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_INS("{call SP_GBM_APY_UPG_DOWNG_INS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_VER_SEL("{call SP_GBM_APY_UPG_DOWNG_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_INQ("{call SP_GBM_APY_UPG_DOWNG_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_VER("{call SP_GBM_APY_UPG_DOWNG_VER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_VER_RES("{call SP_GBM_APY_UPG_DOWNG_VER_RES(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SPM_RES("{call SP_GBM_APY_GEN_SPM_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SPM_REQ("{call SP_GBM_APY_GEN_SPM_REQ(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_DOWNG_DEL("{call SP_GBM_APY_UPG_DOWNG_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SPM_DWN("{call SP_GBM_APY_GEN_SPM_DWN(?,?,?,?)}"),
    //procs for deletion of rejected prans

    SP_GBM_APY_DEL_PRAN_INS_VAL("{call SP_GBM_APY_DEL_PRAN_INS_VAL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DEL_PRAN_INS("{call SP_GBM_APY_DEL_PRAN_INS(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DEL_PRAN_VER("{call SP_GBM_APY_DEL_PRAN_VER(?,?,?,?,?)}"),

    SP_GBM_APY_DEL_PRAN_DEL("{call SP_GBM_APY_DEL_PRAN_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_DEL_PRAN_VER_SEL("{call SP_GBM_APY_DEL_PRAN_VER_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DEL_PRAN_INQ("{call SP_GBM_APY_DEL_PRAN_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_INS_TOR("{call SP_GBM_APY_UPDOWNG_CRA_INS_TOR(?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_INS("{call SP_GBM_APY_UPDOWNG_CRA_INS(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_MOD_SEL("{call SP_GBM_APY_UPDOWNG_CRA_MOD_SEL(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_MOD("{call SP_GBM_APY_UPDOWNG_CRA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_INQ("{call SP_GBM_APY_UPDOWNG_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_DEL("{call SP_GBM_APY_UPDOWNG_CRA_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_VER_SEL("{call SP_GBM_APY_UPDOWNG_CRA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_VER("{call SP_GBM_APY_UPDOWNG_CRA_VER(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPDOWNG_CRA_VER_RES("{call SP_GBM_APY_UPDOWNG_CRA_VER_RES(?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SCF_REQ("{call SP_GBM_APY_GEN_SCF_REQ(?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SCF_RES("{call SP_GBM_APY_GEN_SCF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SCF_DWN("{call SP_GBM_APY_GEN_SCF_DWN(?,?,?,?)}"),

    SP_GBM_APY_SCF_CRA_INS("{call SP_GBM_APY_SCF_CRA_INS(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_CRA_SEL_VER("{call SP_GBM_APY_SCF_CRA_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_CRA_VER("{call SP_GBM_APY_SCF_CRA_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_CRA_DEL("{call SP_GBM_APY_SCF_CRA_DEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_CRA_INQ("{call SP_GBM_APY_SCF_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_INS_TOR("{call SP_GBM_APY_UPG_ISF_CRA_INS_TOR(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_INS("{call SP_GBM_APY_UPG_ISF_CRA_INS(?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_MOD_SEL("{call SP_GBM_APY_UPG_ISF_CRA_MOD_SEL(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_MOD("{call SP_GBM_APY_UPG_ISF_CRA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_DEL("{call SP_GBM_APY_UPG_ISF_CRA_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_INQ("{call SP_GBM_APY_UPG_ISF_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_VER_SEL("{call SP_GBM_APY_UPG_ISF_CRA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_VER("{call SP_GBM_APY_UPG_ISF_CRA_VER(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_UPG_ISF_CRA_VER_RES("{call SP_GBM_APY_UPG_ISF_CRA_VER_RES(?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_FR_RECT_MOD("{call SP_GBM_APY_SCF_FR_RECT_MOD(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_FR_RECT_VER("{call SP_GBM_APY_SCF_FR_RECT_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_FR_RECT_DEL("{call SP_GBM_APY_SCF_FR_RECT_DEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_FR_RECT_INQ("{call SP_GBM_APY_SCF_FR_RECT_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_FR_SCF_REQ("{call SP_GBM_APY_REGEN_FR_SCF_REQ(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_FR_SCF_RES("{call SP_GBM_APY_REGEN_FR_SCF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_INS_TOR("{call SP_GBM_APY_SCF_EXS_SHT_INS_TOR(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_INS("{call SP_GBM_APY_SCF_EXS_SHT_INS(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_DEL("{call SP_GBM_APY_SCF_EXS_SHT_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_VER_SEL("{call SP_GBM_APY_SCF_EXS_SHT_VER_SEL(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_INQ("{call SP_GBM_APY_SCF_EXS_SHT_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_VER("{call SP_GBM_APY_SCF_EXS_SHT_VER(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SCF_EXS_SHT_VER_RES("{call SP_GBM_APY_SCF_EXS_SHT_VER_RES(?,?,?,?,?,?,?,?)}"),


    /*APY FATCA starts here*/
    SP_GBM_APY_FATCA_DEC_INS_SEL("{call SP_GBM_APY_FATCA_DEC_INS_SEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_INS("{call SP_GBM_APY_FATCA_DEC_INS(?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_MOD("{call SP_GBM_APY_FATCA_DEC_MOD(?,?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_VER("{call SP_GBM_APY_FATCA_DEC_VER(?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_DEL("{call SP_GBM_APY_FATCA_DEC_DEL(?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_MOD_SEL("{call SP_GBM_APY_FATCA_DEC_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_VER_SEL("{call SP_GBM_APY_FATCA_DEC_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_FATCA_DEC_INQ("{call SP_GBM_APY_FATCA_DEC_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*APY FATCA ends here*/

    /*Transfer In APY FROM OTHER POP*/
    SP_GBM_APY_TRANSFER_IN_INS("{call SP_GBM_APY_TRANSFER_IN_INS(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_MOD_SEL("{call SP_GBM_APY_TRANSFER_IN_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_MOD("{call SP_GBM_APY_TRANSFER_IN_MOD(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_VER_SEL("{call SP_GBM_APY_TRANSFER_IN_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_VER("{call SP_GBM_APY_TRANSFER_IN_VER(?,?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_DEL("{call SP_GBM_APY_TRANSFER_IN_DEL(?,?,?,?,?)}"),
    SP_GBM_APY_TRANSFER_IN_INQ("{call SP_GBM_APY_TRANSFER_IN_INQ (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    /*Transfer In APY FROM OTHER POP*/

    /*APY Tranfer Out start here*/
    SP_GBM_APY_XFER_OUT_INS_SEL("{call SP_GBM_APY_XFER_OUT_INS_SEL(?,?,?,?,?)}"),
    SP_GBM_APY_XFER_OUT_INS("{call SP_GBM_APY_XFER_OUT_INS(?,?,?,?)}"),
    SP_GBM_APY_XFER_OUT_INQ("{call SP_GBM_APY_XFER_OUT_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_XFER_OUT_VEL_SEL("{call SP_GBM_APY_XFER_OUT_VEL_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_XFER_OUT_VEL("{call SP_GBM_APY_XFER_OUT_VEL(?,?,?,?,?)}"),
    SP_GBM_APY_XFER_OUT_DEL("{call SP_GBM_APY_XFER_OUT_DEL(?,?,?,?,?)}"),
    /*APY Tranfer Out ends here*/

    sp_gbm_apy_gen_dob_spm_req("{call sp_gbm_apy_gen_dob_spm_req(?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_dob_spm_res("{call sp_gbm_apy_gen_dob_spm_res(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_dob_spm_dwn("{call sp_gbm_apy_gen_dob_spm_dwn(?,?,?,?)}"),

    SP_GBM_APY_GEN_TRAN_IN_REQ("{call SP_GBM_APY_GEN_TRAN_IN_REQ(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_GEN_TRAN_IN_RES("{call SP_GBM_APY_GEN_TRAN_IN_RES(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_DOW_TRAN_IN("{call SP_GBM_APY_DOW_TRAN_IN(?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_PR_SCF_REQ("{call SP_GBM_APY_REGEN_PR_SCF_REQ(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_REGEN_PR_SCF_RES("{call SP_GBM_APY_REGEN_PR_SCF_RES(?,?,?,?,?,?,?)}"),

    //procs for subscriber modification dob changes

    SP_GBM_APY_DOB_CNG_INS_TOR("{call SP_GBM_APY_DOB_CNG_INS_TOR(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CNG_INS("{call SP_GBM_APY_DOB_CNG_INS(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CNG_DEL("{call SP_GBM_APY_DOB_CNG_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CNG_VER("{call SP_GBM_APY_DOB_CNG_VER(?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CNG_VER_SEL("{call SP_GBM_APY_DOB_CNG_VER_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CNG_INQ("{call SP_GBM_APY_DOB_CNG_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    sp_gbm_apy_gen_srf_mod_req("{call sp_gbm_apy_gen_srf_mod_req(?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_srf_mod_res("{call sp_gbm_apy_gen_srf_mod_res(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_srf_mod_dwn("{call sp_gbm_apy_gen_srf_mod_dwn(?,?,?,?)}"),

    /*Change in Frequency starts here*/
    SP_GBM_APY_CHAN_IN_FRE_INS_TOR("{call SP_GBM_APY_CHAN_IN_FRE_INS_TOR(?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_IN_FRE_INS("{call SP_GBM_APY_CHAN_IN_FRE_INS(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_IN_FRE_INQ("{call SP_GBM_APY_CHAN_IN_FRE_INQ(?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_IN_FRE_VER_SEL("{call SP_GBM_APY_CHAN_IN_FRE_VER_SEL(?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_IN_FRE_DEL("{call SP_GBM_APY_CHAN_IN_FRE_DEL(?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_IN_FRE_VER("{call SP_GBM_APY_CHAN_IN_FRE_VER(?,?,?,?,?)}"),
    /*Change in Frequency ends here*/

    SP_GBM_APY_DOB_CRA_INS_TOR("{call SP_GBM_APY_DOB_CRA_INS_TOR(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_INS("{call SP_GBM_APY_DOB_CRA_INS(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_MOD_SEL("{call SP_GBM_APY_DOB_CRA_MOD_SEL(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_DOB_CRA_MOD("{call SP_GBM_APY_DOB_CRA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_INQ("{call SP_GBM_APY_DOB_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_DEL("{call SP_GBM_APY_DOB_CRA_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_VER_SEL("{call SP_GBM_APY_DOB_CRA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_VER("{call SP_GBM_APY_DOB_CRA_VER(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_CRA_VER_RES("{call SP_GBM_APY_DOB_CRA_VER_RES(?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_cocon_pop_sp("{call sp_gbm_apy_rep_cocon_pop_sp(?,?,?,?,?,?,?,?,?,?)}"),

    sp_gbm_apy_rep_si_exc_pop("{call sp_gbm_apy_rep_si_exc_pop(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_si_exc_pop_sp("{call sp_gbm_apy_rep_si_exc_pop_sp(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_cocon_pop("{call sp_gbm_apy_rep_cocon_pop(?,?,?,?,?,?,?,?,?,?)}"),

    //procs for subscriber dob changes odc corrections

    SP_GBM_APY_DOB_ISF_CRA_INS_TOR("{call SP_GBM_APY_DOB_ISF_CRA_INS_TOR(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_INS("{call SP_GBM_APY_DOB_ISF_CRA_INS(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_INQ("{call SP_GBM_APY_DOB_ISF_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_MOD_SEL("{call SP_GBM_APY_DOB_ISF_CRA_MOD_SEL(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_MOD("{call SP_GBM_APY_DOB_ISF_CRA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_DEL("{call SP_GBM_APY_DOB_ISF_CRA_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_VER_SEL("{call SP_GBM_APY_DOB_ISF_CRA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_VER("{call SP_GBM_APY_DOB_ISF_CRA_VER(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_ISF_CRA_VER_RES("{call SP_GBM_APY_DOB_ISF_CRA_VER_RES(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_CRA_INS("{call SP_GBM_APY_DOB_SCF_CRA_INS(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_CRA_SEL_VER("{call SP_GBM_APY_DOB_SCF_CRA_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_CRA_DEL("{call SP_GBM_APY_DOB_SCF_CRA_DEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_CRA_VER("{call SP_GBM_APY_DOB_SCF_CRA_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_CRA_INQ("{call SP_GBM_APY_DOB_SCF_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    //rectification for fully rejected scf file

    SP_GBM_APY_DOB_SCF_FR_RECT_MOD("{call SP_GBM_APY_DOB_SCF_FR_RECT_MOD(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_FR_RECT_INQ("{call SP_GBM_APY_DOB_SCF_FR_RECT_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_FR_RECT_DEL("{call SP_GBM_APY_DOB_SCF_FR_RECT_DEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_SCF_FR_RECT_VER("{call SP_GBM_APY_DOB_SCF_FR_RECT_VER(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_RGEN_FR_SCF_REQ("{call SP_GBM_APY_DOB_RGEN_FR_SCF_REQ(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_RGEN_FR_SCF_RES("{call SP_GBM_APY_DOB_RGEN_FR_SCF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_RGEN_SCF_DWN("{call SP_GBM_APY_DOB_RGEN_SCF_DWN(?,?,?,?)}"),

    //procs for short excess fall for dob

    SP_GBM_APY_DOB_EXS_SHT_INS_TOR("{call SP_GBM_APY_DOB_EXS_SHT_INS_TOR(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_INS("{call SP_GBM_APY_DOB_EXS_SHT_INS(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_INQ("{call SP_GBM_APY_DOB_EXS_SHT_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_DEL("{call SP_GBM_APY_DOB_EXS_SHT_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_VER_SEL("{call SP_GBM_APY_DOB_EXS_SHT_VER_SEL(?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_VER("{call SP_GBM_APY_DOB_EXS_SHT_VER(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_EXS_SHT_VER_RES("{call SP_GBM_APY_DOB_EXS_SHT_VER_RES(?,?,?,?,?,?,?,?)}"),


    //procs for Co Contribution file generation, regeneration and download
    SP_GBM_APY_GEN_COCON_SUMM("{call SP_GBM_APY_GEN_COCON_SUMM(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_cocon_req("{call sp_gbm_apy_gen_cocon_req(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_cocon_res("{call sp_gbm_apy_gen_cocon_res(?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_cocon_dwn("{call sp_gbm_apy_gen_cocon_dwn(?,?,?,?,?)}"),
    sp_gbm_apy_regen_cocon_req("{call sp_gbm_apy_regen_cocon_req(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_regen_cocon_res("{call sp_gbm_apy_regen_cocon_res(?,?,?,?,?,?,?,?,?)}"),


    SP_GBM_APY_COCON_REJ_INS_TOR("{call SP_GBM_APY_COCON_REJ_INS_TOR(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_INS("{call SP_GBM_APY_COCON_REJ_INS(?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_INQ("{call SP_GBM_APY_COCON_REJ_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_MOD_SEL("{call SP_GBM_APY_COCON_REJ_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_VER_SEL("{call SP_GBM_APY_COCON_REJ_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_VER("{call SP_GBM_APY_COCON_REJ_VER(?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_DEL("{call SP_GBM_APY_COCON_REJ_DEL(?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REJ_MOD("{call SP_GBM_APY_COCON_REJ_MOD(?,?,?,?,?,?,?)}"),


    SP_GBM_APY_COCON_CREF_INS_TOR("{call SP_GBM_APY_COCON_CREF_INS_TOR(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_INS("{call SP_GBM_APY_COCON_CREF_INS(?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_INQ("{call SP_GBM_APY_COCON_CREF_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_VER_SEL("{call SP_GBM_APY_COCON_CREF_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_MOD_SEL("{call SP_GBM_APY_COCON_CREF_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_VER("{call SP_GBM_APY_COCON_CREF_VER(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_DEL("{call SP_GBM_APY_COCON_CREF_DEL(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_CREF_MOD("{call SP_GBM_APY_COCON_CREF_MOD(?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_cocon_fail_rejrea("{call sp_gbm_apy_cocon_fail_rejrea(?,?,?)}"),


    /*CRA Status Frequency Change*/
    SP_GBM_APY_CHAN_FQ_INS_TOR("{call SP_GBM_APY_CHAN_FQ_INS_TOR(?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_FQ_MOD_SEL("{call SP_GBM_APY_CHAN_FQ_MOD_SEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_FQ_INS("{call SP_GBM_APY_CHAN_FQ_INS(?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_FQ_MOD("{call SP_GBM_APY_CHAN_FQ_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_CHAN_FQ_DEL("{call SP_GBM_APY_CHAN_FQ_DEL(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_RGEN_PR_SCF_REQ("{call SP_GBM_APY_DOB_RGEN_PR_SCF_REQ(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_FQ_VER_SEL("{call SP_GBM_APY_CHAN_FQ_VER_SEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_CHAN_FQ_VER("{call SP_GBM_APY_CHAN_FQ_VER(?,?,?,?,?,?)}"),

    SP_GBM_APY_DOB_RGEN_PR_SCF_RES("{call SP_GBM_APY_DOB_RGEN_PR_SCF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_INS_TOR("{call SP_GBM_APY_VOL_EXT_INS_TOR(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_INS("{call SP_GBM_APY_VOL_EXT_INS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_MOD_SEL("{call SP_GBM_APY_VOL_EXT_MOD_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_MOD("{call SP_GBM_APY_VOL_EXT_MOD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_VER_SEL("{call SP_GBM_APY_VOL_EXT_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_VER("{call SP_GBM_APY_VOL_EXT_VER(?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_DEL("{call SP_GBM_APY_VOL_EXT_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_INQ("{call SP_GBM_APY_VOL_EXT_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_ZERO_TOR("{call SP_GBM_APY_VOL_EXT_ZERO_TOR(?,?,?,?,?,?,?)}"),

    /*Upload for Co Contribution recieved from CRA Starts here*/
    sp_gbm_apy_cocon_file_resd_ins("{call sp_gbm_apy_cocon_file_resd_ins(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_cocon_file_resd_inq("{call sp_gbm_apy_cocon_file_resd_inq(?,?,?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_cocon_file_val("{call sp_gbm_apy_cocon_file_val(?,?,?)}"),
    /*Upload for Co Contribution recieved from CRA ends here*/

    SP_GBM_APY_REP_ACK_VOL_EXT("{call SP_GBM_APY_REP_ACK_VOL_EXT(?,?,?,?,?,?)}"),


    SP_GBM_APY_VOL_EXT_CRA_INS_TOR("{call SP_GBM_APY_VOL_EXT_CRA_INS_TOR(?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_INS("{call SP_GBM_APY_VOL_EXT_CRA_INS(?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_MOD_SEL("{call SP_GBM_APY_VOL_EXT_CRA_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_INQ("{call SP_GBM_APY_VOL_EXT_CRA_INQ(?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_DEL("{call SP_GBM_APY_VOL_EXT_CRA_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_MOD("{call SP_GBM_APY_VOL_EXT_CRA_MOD(?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_VER_SEL("{call SP_GBM_APY_VOL_EXT_CRA_VER_SEL(?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_CHAN_FQ_INQ("{call SP_GBM_APY_CHAN_FQ_INQ(?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_CRA_VER("{call SP_GBM_APY_VOL_EXT_CRA_VER(?,?,?,?,?)}"),

    //voluntary exit rejected pran rectification

    SP_GBM_APY_VOL_EXT_REC_INS_TOR("{call SP_GBM_APY_VOL_EXT_REC_INS_TOR(?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_REC_INS("{call SP_GBM_APY_VOL_EXT_REC_INS(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_REC_INQ("{call SP_GBM_APY_VOL_EXT_REC_INQ(?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_REC_VER_SEL("{call SP_GBM_APY_VOL_EXT_REC_VER_SEL(?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_REC_DEL("{call SP_GBM_APY_VOL_EXT_REC_DEL(?,?,?,?,?,?)}"),

    SP_GBM_APY_VOL_EXT_REC_VER("{call SP_GBM_APY_VOL_EXT_REC_VER(?,?,?,?,?,?)}"),


    /* SI Execution */
    SI_EXECUTION_PRE_CALC("{call PKG_GBM_APY_SI.SP_GBM_APY_PROCESS_UPDATE(?,?,?,?,?,?,?)}"),
    SI_EXECUTION_VALIDATION("{call PKG_GBM_APY_SI.SP_GBM_APY_PAYMET_VAL(?,?,?,?,?,?,?,?,?,?,?)}"),
    SI_EXECUTION_TRAN_REQUEST("{call PKG_GBM_APY_SI.SP_GBM_APY_PAYMENT_TRAN_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SI_EXECUTION_TRAN_RESPONSE("{call PKG_GBM_APY_SI.SP_GBM_APY_PAYMENT_TRAN_RES(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SI_EXECUTION_PROGRESS_STATUS("{call PKG_GBM_APY_SI.SP_GBM_APY_PAYMENT_STATUS(?,?,?,?,?,?,?)}"),
    SI_EXECUTION_VALIDATION_BATCH("{call PKG_GBM_APY_SI.SP_GBM_APY_VAL_BATCH(?,?,?,?,?,?,?,?)}"),
    SI_EXECUTION_MIDDLEWARE_STAT("{call PKG_GBM_APY_SI.SP_GBM_APY_MIDDLEWARE_STAT(?,?,?,?,?,?,?)}"),
    SI_EXECUTION_TRAN_BATCH("{call PKG_GBM_APY_SI.SP_GBM_APY_TRAN_BATCH(?,?,?,?,?,?,?,?)}"),


    /*Voluntary Exit generation*/
    sp_gbm_apy_gen_vol_ext_req("{call sp_gbm_apy_gen_vol_ext_req(?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_vol_ext_res("{call sp_gbm_apy_gen_vol_ext_res(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_regen_vol_ext_req("{call sp_gbm_apy_regen_vol_ext_req(?,?,?,?,?,?,?,?)}"),
    sp_gbm_apy_regen_vol_ext_res("{call sp_gbm_apy_regen_vol_ext_res(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_gen_vol_ext_dwn("{call sp_gbm_apy_gen_vol_ext_dwn(?,?,?,?)}"),

    /*Reports*/
    sp_gbm_apy_rep_ex_du_to_dt("{call sp_gbm_apy_rep_ex_du_to_dt(?,?,?,?,?)}"),
    sp_gbm_apy_rep_ex_du_to_dt_sp("{call sp_gbm_apy_rep_ex_du_to_dt_sp(?,?,?,?,?)}"),
    sp_gbm_apy_rep_date_wise_summ("{call sp_gbm_apy_rep_date_wise_summ(?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_mon_wise_summ("{call sp_gbm_apy_rep_mon_wise_summ(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_pen_act_pop_sp("{call sp_gbm_apy_rep_pen_act_pop_sp(?,?,?,?,?,?,?)}"),
    sp_gbm_apy_rep_pen_act_pop("{call sp_gbm_apy_rep_pen_act_pop(?,?,?,?,?,?,?)}"),


    //procs for si scf file generation

    SP_GBM_APY_GEN_SI_SCF_REQ("{call SP_GBM_APY_GEN_SI_SCF_REQ(?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SI_SCF_RES("{call SP_GBM_APY_GEN_SI_SCF_RES(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_GEN_SI_SCF_DWN("{call SP_GBM_APY_GEN_SI_SCF_DWN(?,?,?,?)}"),

    /*Initiate Transfer of Co-Contribution starts here*/
    sp_gbm_apy_paymet_tran_req("{call sp_gbm_apy_paymet_tran_req(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_PAYMET_TRAN_RES("{call SP_GBM_APY_PAYMET_TRAN_RES(?,?,?,?,?,?,?,?,?,?,?,?)}"),
    /*Initiate Transfer of Co-Contribution ends here*/

    /*Date of refund starts here*/
    SP_GBM_APY_COCON_REFUN_INS_TOR("{call SP_GBM_APY_COCON_REFUN_INS_TOR(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_INS("{call SP_GBM_APY_COCON_REFUN_INS(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_MOD("{call SP_GBM_APY_COCON_REFUN_MOD(?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_VER("{call SP_GBM_APY_COCON_REFUN_VER(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_DEL("{call SP_GBM_APY_COCON_REFUN_DEL(?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_MOD_SEL("{call SP_GBM_APY_COCON_REFUN_MOD_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_INQ("{call SP_GBM_APY_COCON_REFUN_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),
    SP_GBM_APY_COCON_REFUN_VER_SEL("{call SP_GBM_APY_COCON_REFUN_VER_SEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),


    /*Date of refund ends here*/

    SP_GBM_APY_SI_ENA_DIS_MOD_SEL("{call SP_GBM_APY_SI_ENA_DIS_MOD_SEL(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SI_ENA_DIS_MOD("{call SP_GBM_APY_SI_ENA_DIS_MOD(?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SI_ENA_DIS_SEL_VER("{call SP_GBM_APY_SI_ENA_DIS_SEL_VER(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    SP_GBM_APY_SI_ENA_DIS_VER("{call SP_GBM_APY_SI_ENA_DIS_VER(?,?,?,?,?)}"),

    SP_GBM_APY_SI_ENA_DIS_DEL("{call SP_GBM_APY_SI_ENA_DIS_DEL(?,?,?,?,?)}"),

    SP_GBM_APY_SI_ENA_DIS_INQ("{call SP_GBM_APY_SI_ENA_DIS_INQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),

    /*Bod Eod Status Inquiry starts*/
    gbm_bod_eod_status_inq_apy("{ call gbm_bod_eod_status_inq_apy(?,?,?,?,?,?,?,?,?,?) }");
    /*Bod Eod Status Inquiry ends*/

    ApyProcEnum(String procName) {
        this.procName = procName;
    }

    private final String procName;

    public String getProcName() {

        System.out.println("sql procedure" + procName);
        return procName;
    }
}
