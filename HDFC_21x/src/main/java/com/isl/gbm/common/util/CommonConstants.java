/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * The Class CommonConstants.
 *
 * @author Vasanthi
 */
@Component(value = "CommonConstants")
public final class CommonConstants {
    public static final String BANK_CODE ="KKBK";

    public static final String NO_STATUS = "N";
    public static final String INVALID_MOBILE_NO = "Invalid Mobile Number: ";
    public static final String INVALID_PAN_NO = "Invalid PAN Number: ";
    public static final String INVALID_IFSC_CODE = "Invalid IFSC Code: ";
    public static final String INVALID_EMAIL = "Invalid Email: ";
    public static final String INVALID_ACC_NO = "Invalid Account Number: ";
    public static final String INVALID_AMOUNT = "Invalid Amount: ";
    public static final String INVALID_PERCENTAGE = "Invalid Percentage: ";
    public static final String NAME_VAL = "Invalid NAME: ";
    public static final String INVALID_MAKER_REASON = "Special Character Not Allowed: ";
    public static final String MAKER_REASON_PATTERN = "^[A-Za-z0-9]+(?: [A-Za-z0-9]+)*$";

    public static final String MOB_PATTERN = "^\\d+$";
    public static final String ACC_PATTERN = "^\\d+$";
    public static final String IFSC_PATTERN = "^[A-Z]{4}0[A-Z0-9]{6}$";
    public static final String NAME_PATTERN = "^[A-Za-z]+(?: [A-Za-z]+)*$";
    public static final String PAN_PATTERN = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    public static final String EMAIL_PATTERN = "^[^\\s@]+@[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)*\\.[a-zA-Z]{2,6}$";
    public static final String HEADER_VALIDATION_FAILURE="Header validation failed";
    public static final String DB_STATUS="DB Status: ";
    public static final String DB_FAILURE="DB Failure: ";
    public static final String INVALID_DATE_FORMAT="Invalid Date Format: ";
    public static final String PAN_VALID_FAILURE="Pan Validation Failure";
    public static final String EXE_SQL_QUERY="Executed the sql query: ";
    public static final String INVALID_DRAWER_NAME="Invalid Drawer Name: ";
    public static final String INVALID_APPLICANT_NAME="Invalid Applicant Name: ";


    public static final String SSA_SCHEME ="0600";

    public static final String RECORD_FLG = "Y";

    /**
     * The Constant TRAN_LEG_DEBIT.
     */
    public static final Character TRAN_LEG_DEBIT = 'D';

    /**
     * The Constant TRAN_LEG_CREDIT.
     */
    public static final Character TRAN_LEG_CREDIT = 'C';

    public static final String ICEGATE_API_IGRN_INSERT_USE_CASE_ID = "UC_IGT_002";

    /**
     * The Constant USER_ID.
     */
    public static final String USER_ID = "MANOJ6";


    public static final String YES_EOD_STATUS = "C";
    /**
     * The Constant TRAN_OPERATION_ADD.
     */
    public static final char TRAN_OPERATION_ADD = 'A';

    /**
     * The Constant NPS_SCHEME_CODE.
     */
    public static final String NPS_MODULE_CODE = "NPS";

    public static final String APY_MODULE_CODE = "002";

    public static final String SSA_MODULE_ID = "0600";

    public static final String SSA_MODULE_CODE = "008";

    public static final String KVP_MODULE_ID = "0502";

    public static final String KVP_MODULE_CODE = "006";

    public static final String PENSION_MODULE_ID = "003";

    public static final String ICEGATE_MODULE_ID = "0011";

    public static final String EFREIGHT_MODULE_ID = "010";


    public static final String RBI_BOND_MODULE_ID = "012";

    /**
     * The Constant NPS_SCHEME_CODE.
     */
    public static final String APY_SCHEME_CODE = "1210";

    /**
     * The Constant NO.
     */
    public static final Character NO = 'N';

    /**
     * The Constant YES.
     */
    public static final Character YES = 'Y';


    public static final String SOL_ID = "101";

    /**
     * The Constant DATA_SAVED_MESSAGE.
     */
    public static final String DATA_SAVED_MESSAGE = "Data saved successfully";

    /**
     * The Constant DATA_UPDATED_MESSAGE.
     */
    public static final String DATA_UPDATED_MESSAGE = "Data updated successfully";

    /**
     * The Constant DATA_DELETED_MESSAGE.
     */
    public static final String DATA_DELETED_MESSAGE = "Data deleted successfully";

    /**
     * The Constant DATA_VERIFIED_MESSAGE.
     */
    public static final String DATA_VERIFIED_MESSAGE = "Data verified successfully";


    /**
     * The Constant YES_STATUS.
     */
    public static final String YES_STATUS = "Y";

    public static final Character DELETE = 'D';


    /**
     * The Constant VERIFIED.
     */
    public static final String VERIFIED = "Verified";

    /* The Constant COMPLETED.
     */
    public static final Character COMPLETED = 'C';

    public static final Number TRUE = 1;

    public static final Number FALSE = 0;

    public static final String PREFIX = "UPD";
    public static final String SUCCESS = "S";
    public static final String CAUTION = "C";
    public static final String FAILURE = "F";
    public static final String NORESPONSE = "X";
    public static final String PARTIAL_REJECT = "PR";

    private CommonConstants() {
        // private constructor
    }

    public static final String SUCCESS_CONST = "SUCCESS";

    public static final String FAILURE_CONST = "FAILED";
    /*
     * SP list for Database operations
     */
    public static final String WARNING_MESSAGE = "Previous Day Records Exists In The Dashboard";
    public static final String WARNING_MESSAGE1 = "LOCKED";

    public static final String SP_SELECT = "SINGLE_TABLE_PLAIN_SELECT";
    public static final String SP_INSERT = "SINGLE_TABLE_PLAIN_INSERT";
    public static final String SP_MODIFY = "SINGLE_TABLE_PLAIN_MODIFY";
    public static final String SP_VERIFY = "SINGLE_TABLE_PLAIN_VERIFY";
    public static final String SP_SELECT_VERIFY = "SINGLE_TABLE_SEL_VERIFY";
    public static final String SP_DELETE = "SINGLE_TABLE_PLAIN_DELETE";
    public static final String SP_INQUIRY = "SINGLE_TABLE_SEL_INQUIRY";
    public static final String SP_UNVERIFY = "SINGLE_TABLE_PLAIN_SELECT_UNVERIFIED";

    public static final List<String> VALID_SP_LIST = Arrays.asList(SP_INSERT, SP_MODIFY, SP_VERIFY, SP_DELETE, SP_SELECT, SP_SELECT_VERIFY, SP_INQUIRY, SP_UNVERIFY);
    public static final List<String> PRIMARY_KEY_REQUIRED_SP_LIST = Arrays.asList(SP_MODIFY, SP_VERIFY, SP_SELECT_VERIFY, SP_DELETE, SP_INQUIRY);
    public static final List<String> COLUMN_LIST_REQUIRED_SP_LIST = Arrays.asList(SP_SELECT, SP_SELECT_VERIFY, SP_INQUIRY);
    public static final List<String> COLUMN_VALUES_REQUIRED_SP_LIST = Arrays.asList(SP_INSERT, SP_MODIFY);


    /*
     * Database Objects & Collections Name
     */
    public static final String DATABASE_OBJECT_COLUMN_DETAILS = "TY_GBM_PP_COL_DETAILS_OBJ";
    public static final String DATABASE_COLLECTION_COLUMN_DETAILS = "TY_GBM_PP_COL_DETAILS";


    public static final String DATABASE_OBJECT_COLUMN_LIST = "GBM_COLUMNS_OBJ";
    public static final String DATABASE_COLLECTION_COLUMN_LIST = "GBM_COLUMNS_TYP";

    public static final String DATABASE_OBJECT_COLUMN_CONDITIONS = "TY_GBM_PP_GEN_SELECT_OBJ";
    public static final String DATABASE_COLLECTION_COLUMN_CONDITIONS = "TY_GBM_PP_GEN_SELECT_TYP";
    public static final String DATABASE_OBJECT_COL_DET_ARRAY = "TYP_GBM_PP_COL_DET_ARRAY_OBJ";

    public static final String DATABASE_COLLECTION_COL_DET_ARRAY = "TYP_GBM_PP_COL_DET_ARRAY";
    public static final String DATABASE_COLLECTION_DROPDOWN = "TY_GBM_RBIBOND_DRP_DWN_MST";


    public static final String DATABASE_COLLECTION_COL_BLA_ARRAY = "TY_GBM_RBI_BOND_BLA_DTLS";
    public static final String DATABASE_COLLECTION_COPARS_ARRAY = "TYP_GBM_RBI_BOND_COPAR";
    public static final String DATABASE_COLLECTION_NOMINATION_ARRAY = "TYP_GBM_RBI_BOND_NOM_DTLS";

    public static final String DATABASE_OBJECT_TRAN_ACC_VAL = "TYP_GBM_APY_TRAN_AC_VAL_OBJ";
    public static final String DATABASE_COLLECTION_TRAN_ACC_VAL = "TYP_GBM_APY_TRAN_AC_VAL";

    public static final String DATABASE_OBJECT_TRAN_ACC_VAL_SCSS = "TYP_GBM_SCSS_TRAN_AC_VAL_OBJ";
    public static final String DATABASE_COLLECTION_TRAN_ACC_VAL_SCSS = "TYP_GBM_SCSS_TRAN_AC_VAL";

    public static final String DATABASE_OBJECT_NPS_TRAN_ACC_VAL = "TYP_GBM_NPS_TRAN_AC_VAL_OBJ";
    public static final String DATABASE_COLLECTION_NPS_TRAN_ACC_VAL = "TYP_GBM_NPS_TRAN_AC_VAL";


    public static final String DATABASE_OBJECT_LEVEL_MASTER_DETAILS = "TYP_SINGLE_COL_OBJ";
    public static final String DATABASE_COLLECTION_LEVEL_MASTER_ARRAY = "TYP_SINGLE_COL";

    public static final String GENERIC_DATA_OBJECT_DETAILS = "TYP_GBM_GEN_SEL_COL_OBJ";
    public static final String GENERIC_DATA_OBJECT_ARRAY = "TYP_GBM_GEN_SEL_COL";

    /*Upload for Co Contribution recieved from CRA*/
    public static final String DATABASE_OBJECT_UPLOAD_CO_CONTRIBUTION = "GBM_COCON_FILE_RECD_TYP_OBJ";
    public static final String DATABASE_COLLECTION_UPLOAD_CO_CONTRIBUTION = "GBM_COCON_FILE_RECD_TYP";

    public static final String DATABASE_OBJECT_REFUND_CO_CONTRIBUTION = "TYP_GBM_COCONT_REFUND_OBJ";
    public static final String DATABASE_COLLECTION_REFUND_CO_CONTRIBUTION = "TYP_GBM_COCONT_REFUND";

    public static final String DATABASE_OBJECT_BRANCHES_OF_BANK_OBJ = "TYP_BANK_BRAN_OBJ";
    public static final String DATABASE_OBJECT_TYP_GBM_RBI_BOND_IN_OUT_OBJ = "TYP_GBM_RBI_BOND_IN_OUT_OBJ";
    public static final String DATABASE_OBJECT_TYP_GBM_RBI_BOND_OUT_OBJ = "TY_GBM_INWARD_DETAILS_OBJ";
    public static final String DATABASE_OBJECT_TYP_GBM_RBI_BOND_OUT_REJ_OBJ = "TY_GBM_PP_COL_REJ_DETAILS_OBJ";
    public static final String DATABASE_TYP_GBM_RBI_BOND_OUT_REJ = "TY_GBM_PP_COL_REJ_DETAILS";
    public static final String DATABASE_OBJECT_TYP_RBI_BOND_BRK_PAYMT_OBJ = "TYP_RBI_BOND_BRK_PAYMT_OBJ";
    public static final String DATABASE_OBJECT_TYP_RBI_BOND_BRK_CON_OBJ = "TYP_RBI_BOND_BRK_CON_OBJ";
    public static final String DATABASE_OBJECT_TY_GBM_PP_COL_DETAILS_OBJ = "TY_GBM_PP_COL_DETAILS_OBJ";
    public static final String DATABASE_OBJECT_TYP_RBI_BOND_BRK_PYMT_OBJ = "TYP_RBI_BOND_BRK_PYMT_OBJ";
    public static final String DATABASE_OBJECT_TYP_RBI_BOND_BRK_PAYMNT_OBJ = "TYP_RBI_BOND_BRK_PAYMNT_OBJ";

    public static final String DATABASE_OBJECT_STATE_MASTER_OBJ = "TYP_GBM_RBI_BOND_STATE_OBJ";

    public static final String DATABASE_STATE_MASTER_ARRAY = "TYP_GBM_RBI_BOND_STATE";
    public static final String DATABASE_BRANCHES_OF_BANK_ARRAY = "TYP_BANK_BRAN";
    public static final String DATABASE_TYP_RBI_BOND_BRK_PAYMT = "TYP_RBI_BOND_BRK_PAYMT";
    public static final String DATABASE_TY_GBM_PP_COL_DETAILS = "TY_GBM_PP_COL_DETAILS";
    public static final String DATABASE_TYP_RBI_BOND_BRK_CON = "TYP_RBI_BOND_BRK_CON";
    public static final String DATABASE_TYP_RBI_BOND_BRK_PYMT = "TYP_RBI_BOND_BRK_PYMT";
    public static final String DATABASE_TYP_GBM_RBI_BOND_IN_OUT = "TYP_GBM_RBI_BOND_IN_OUT";
    public static final String DATABASE_TYP_GBM_RBI_BOND_OUT = "TY_GBM_INWARD_DETAILS";
    public static final String DATABASE_TYP_RBI_BOND_BRK_PAYMNT = "TYP_RBI_BOND_BRK_PAYMNT";

    public static final String DATABASE_OBJECT_OFFICE_ACCT_HOLDER_OBJ = "TYP_OFF_ACC_PH_OBJ";
    public static final String DATABASE_OFFICE_ACCT_HOLDER_ARRAY = "TYP_OFF_ACC_PH";

    public static final String DATABASE_OBJECT_INST_DET_OBJ = "TYP_INSTRMENT_DET_OBJ";
    public static final String DATABASE_INST_DET_ARRAY = "TYP_INSTRUMENT_DET";

    public static final String DATABASE_OBJECT_TRAN_CODE_OBJ = "TYP_TRAN_CODE_OBJ";
    public static final String DATABASE_TRAN_CODES_ARRAY = "TYP_TRAN_CODE";

    public static final String DATABASE_OBJECT_OTH_BANK_DETAILS_OBJ = "TYP_OTH_BANK_OBJ";
    public static final String DATABASE_OTH_BANK_DETAILS_ARRAY = "TYP_OTH_BANK";

    public static final String DATABASE_OBJECT_OTH_BANK_BRANCHES_OBJ = "TYP_OTH_BANK_BRAN_OBJ";
    public static final String DATABASE_OTH_BANK_BRANCHES_ARRAY = "TYP_OTH_BANK_BRAN";

    public static final String DATABASE_OBJECT_CAL_ENTRIES_DETAILS_OBJ = "TYP_CAL_ENTRIES_OBJ";
    public static final String DATABASE_CAL_ENTRIES_ARRAY = "TYP_CAL_ENTRIES";


    public static final String DATABASE_OBJECT_SCH_CODE_DETAILS_OBJ = "TYP_SCH_CODE_OBJ";
    public static final String DATABASE_SCH_CODE_ARRAY = "TYP_SCH_CODE";


    public static final String DATABASE_OBJECT_USER_WORK_DETAILS_OBJ = "TYP_USER_DET_OBJ";
    public static final String DATABASE_USER_WORK_DETAILS_ARRAY = "TYP_USER_DET";


    public static final String DATABASE_OBJECT_BRANCH_IFSC_CODE_DETAILS_OBJ = "TYP_BRAN_IFSC_OBJ";
    public static final String DATABASE_BRANCH_IFSC_CODE_DETAILS_ARRAY = "TYP_BRAN_IFSC";

    /*
     *  Level Master Database operations
     */

    public static final String SP_SELECT_UNVERIFIED = "SINGLE_TABLE_PLAIN_SELECT_UNVERIFIED";

    public static final String NDF = "No Data Found";

    public static final String NDF_CONSTANT = "NDF";

    public static final String PROC_TYPE_IN_REQUEST = "0";
    public static final String PROC_TYPE_IN_RESPONSE = "1";

    public static final String EVENT_TYPE_VALIDATION = "V";
    public static final String EVENT_TYPE_TRANSACTION = "T";

    public static final String TRAN_STATUS_INITIATED = "I";

    public static final String POP_LEVEL_CODE = "1";
    public static final String POP_SP_LEVEL_CODE = "2";

    public static final String EOD_FLAG_NON_FINANCIAL_TRAN_PENDING = "UVN";
    public static final String EOD_FLAG_UNVERIFIED_TRAN_PENDING = "UVF";
    public static final String EOD_FLAG_TRAN_RECONCILE_PENDING = "REC";


    /**
     * The Constant CASH_ACCOUNT.
     */
    public static final String CASH_ACCOUNT = "PH_CASHAC";

    /**
     * The Constant RECEIPT.
     */
    public static final String TRAN_TYPE_RECEIPT = "R";

    /**
     * The Constant TRAN_TYPE.
     */
    public static final String TRAN_TYPE = "P";

    /**
     * The Constant UNLOCK_LEVEL.
     */
    public static final String UNLOCK_LEVEL_ONE = "LEV1";

    /**
     * The Constant UNLOCK_LEVEL.
     */
    public static final String UNLOCK_LEVEL_FIVE = "LEV5";


    /**
     * The Constant NPS_SCHEME_CODE.
     */
    public static final String NPS_SCHEME_CODE = "0000";


    /**
     * The Constant UNCLEARED.
     */
    public static final char UNCLEARED = 'U';

    /**
     * The Constant LOCKED.
     */
    public static final Character LOCKED = 'L';

    /**
     * The Constant CLEARED.
     */
    public static final Character CLEARED = 'G';

    /**
     * The Constant CLEARED_NOW.
     */
    public static final Character CLEARED_NOW = 'I';

    /**
     * The Constant BOUNCED.
     */
    public static final Character BOUNCED = 'O';

    /**
     * The Constant FINACLE_TO_VERIFY.
     */
    public static final Character FINACLE_TO_VERIFY = 'F';

    /**
     * The Constant VALIDATION_FAILED_MESSAGE.
     */
    public static final String VALIDATION_FAILED_MESSAGE = "Validation failed";

    /**
     * The Constant NPS_SUB_CODE_CATEGORY.
     */
    public static final String NPS_SUB_CODE_CATEGORY = "023";

    /**
     * The Constant NPS_SUB_CODE_TITLE.
     */
    public static final String NPS_SUB_CODE_TITLE = "003";

    /**
     * The Constant NPS_SUB_CODE_SCHEME.
     */
    public static final String NPS_SUB_CODE_SCHEME = "004";

    /**
     * The Constant NPS_SUB_CODE_PROOFID.
     */
    public static final String NPS_SUB_CODE_PROOFID = "005";

    /**
     * The Constant NPS_SUB_CODE_PROOFADD.
     */
    public static final String NPS_SUB_CODE_PROOFADD = "006";

    /**
     * The Constant NPS_SUB_CODE_PFMCHOICE.
     */
    public static final String NPS_SUB_CODE_PFMCHOICE = "007";

    /**
     * The Constant NPS_SUB_CODE_INCOME_RANGE.
     */
    public static final String NPS_SUB_CODE_INCOME_RANGE = "025";

    /**
     * The Constant NPS_SUB_CODE_QUALIFICATION.
     */
    public static final String NPS_SUB_CODE_QUALIFICATION = "024";

    /**
     * The Constant UOS_CORPORATE_ID.
     */
    public static final String UOS_CORPORATE_ID = "0000";

    /**
     * The Constant LITE_CORPORATE_ID.
     */
    public static final String LITE_CORPORATE_ID = "9999";

    /**
     * The Constant NPS_SUBSCRIBER_SER_CODE.
     */
    public static final String NPS_SUBSCRIBER_SER_CODE = "21";

    /**
     * The Constant NPS_SUBSCRIBER_REGISTRATION_CODE.
     */
    public static final String NPS_SUBSCRIBER_REGISTRATION_CODE = "11";

    /**
     * The Constant NPS_SUBSCRIBER_CONTRIBUTION_CODE.
     */
    public static final String NPS_SUBSCRIBER_CONTRIBUTION_CODE = "12";

    /**
     * The Constant NPS_SUBSCRIBER_MODIFICATION_CODE.
     */
    public static final String NPS_SUBSCRIBER_MODIFICATION_CODE = "17";

    /**
     * The Constant NPS_ROUND_OFF.
     */
    public static final String NPS_ROUND_OFF = "Y";

    /**
     * The Constant OTHER_BRANCH_CR_ACCOUNT.
     */
    public static final String OTHER_BRANCH_CR_ACCOUNT = "PH_OTHERBRANCHCRAC";

    /**
     * The Constant BANKS_CONNECT.
     */
    public static final String BANKS_CONNECT = "BC_MT_TT";

    /**
     * The Constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * The Constant DATE_FORMAT2.
     */
    public static final String DATE_FORMAT2 = "dd/MM/yyyy";

    /**
     * The Constant FORMAT_DATE_TO_STRING.
     */
    public final SimpleDateFormat FORMAT_DATE_TO_STRING = new SimpleDateFormat("dd/MM/yyyyy");

    /**
     * The Constant FINACLE_VERIFIED.
     */
    public static final Character FINACLE_VERIFIED = 'F';

    /**
     * The Constant TAN_PATTERN.
     */
    public static final String TAN_PATTERN = "[A-Z]{4}[0-9]{5}[A-Z]{1}";

    /**
     * The Constant FINACLE_VERIFIED.
     */
    public static final String RECORD_DELETED = "Record Already Deleted";

    /**
     * The Constant SUPER_ADMIN.
     */
    public static final String SUPER_ADMIN = "SUPER ADMIN";

    /**
     * The Constant FIRST_PART_TRAN_NUM.
     */
    public static final Integer FIRST_PART_TRAN_NUM = 1;

    /**
     * The Constant SECOND_PART_TRAN_NUM.
     */
    public static final Integer SECOND_PART_TRAN_NUM = 2;

    /**
     * The Constant THIRD_PART_TRAN_NUM.
     */
    public static final Integer THIRD_PART_TRAN_NUM = 3;

    /**
     * The Constant FOURTH_PART_TRAN_NUM.
     */
    public static final Integer FOURTH_PART_TRAN_NUM = 4;

    /**
     * The Constant OPERATION_COLUMN_MODIFY.
     */
    public static final Character OPERATION_MODE_MODIFY = 'M';

    /**
     * The Constant OPERATION_COLUMN_INSERT.
     */
    public static final Character OPERATION_MODE_INSERT = 'I';

    /**
     * The Constant OPERATION_COLUMN_DELETE.
     */
    public static final Character OPERATION_MODE_DELETE = 'D';

    /**
     * The Constant TELLER_AC.
     */
    public static final String TELLER_AC = "TELLER_AC";


    /**
     * The Constant TRAN_AMOUNT_ZERO.
     */
    public static final Double TRAN_AMOUNT_ZERO = 0.0;

    /**
     * The Constant STATE_CODE.
     */
    public static final String STATE_CODE = "055";

    /**
     * The Constant RELATIONSHIP_CODE.
     */
    public static final String RELATIONSHIP_CODE = "012";


    /**
     * The Constant FAILED.
     */
    public static final Character FAILED = 'F';

    /**
     * The Constant ENTRY_USER_ID.
     */
    public static final String ENTRY_USER_ID = "WFSRE";

    /**
     * The Constant GSPM_INTERNET_ENABLED_CONFIG_FILE_NAME.
     */
    public static final String GSPM_INTERNET_ENABLED_CONFIG_FILE_NAME = "inet";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_ICEGATE.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_CBDT = "IsInternetEnabledForCBDT";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_CBEC.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_CBEC = "IsInternetEnabledForCBEC";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_SALESTAX.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_SALESTAX = "IsInternetEnabledForSALESTAX";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_DOCA.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_DOCA = "IsInternetEnabledForDOCA";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_RDCESS.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_RDCESS = "IsInternetEnabledForRDCESS";

    /**
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_COMMON.
     */
    public static final String KEY_IS_INTERNET_ENABLED_FOR_COMMON = "IsInternetEnabledForCOMMON";

    /**
     * The Constant BOD_ERROR_SOURCE.
     */
    public static final String BOD_ERROR_SOURCE = "B";

    /**
     * The Constant EOD_ERROR_SOURCE.
     */
    public static final String EOD_ERROR_SOURCE = "E";

    /**
     * The Constant NPS_MODULE_ID.
     */
    public static final String NPS_MODULE_ID = "080";

    /**
     * The Constant NPS_PROCESS_ID.
     */
    public static final String NPS_PROCESS_ID = "NPSEODINS";

    /**
     * The Constant NPS_EOD.
     */
    public static final String NPS_EOD = "NPS_EOD";

    /**
     * The Constant PH_CLEARING.
     */
    public static final String PH_CLEARING = "PH_CLEARINGAC";

    /**
     * The Constant INITIATED.
     */
    public static final String INITIATED = "INITIATED";

    /**
     * The Constant CORPORATE_TO_INDIVIDUAL.
     */
    public static final String CORPORATE_TO_INDIVIDUAL = "1226";

    /**
     * The Constant INDIVIDUAL_TO_CORPORATE.
     */
    public static final String INDIVIDUAL_TO_CORPORATE = "1227";

    /**
     * The Constant OTHER_CORPORATE.
     */
    public static final String OTHER_CORPORATE = "1228";

    /**
     * The Constant FREQUENCY_SUB_CODE.
     */
    public static final String FREQUENCY_SUB_CODE = "002";

    public static final String CORPORATE_NPS = "Y";

    public static final String DEBIT = "D";

    /**
     * Instantiates a new common constants.
     */

    public static final String NOT_VERIFIED = "Not Verified";


    /**
     * Instantiates a new common constants.
     */


    public static final String SUBSCRIBER_SHIFTING_TYPE_INTER_POP = "1229";
    public static final String SUBSCRIBER_SHIFTING_TYPE_INTRA_POP = "1227";
    public static final String SUBSCRIBER_SHIFTING_TYPE_TRANSFER_WITHIN_POP = "1228";


    public static final String DATABASE_OBJECT_LEVEL_MASTER = "TYP_HIR_LEVEL_OB";
    public static final String DATABASE_LEVEL_MASTER_ARRAY = "TYP_HIR_LEVE";

    public static final String DATABASE_OBJECT_LEVEL_MAPPING_MASTER = "TYP_SINGLE_COL_OBJ";
    public static final String DATABASE_LEVEL_MAPPING_MASTER_ARRAY = "TYP_SINGLE_COL";


    public static final String DATABASE_OBJECT_COL_TOC = "TYP_GBM_SCSS_TOC_SLAB_OBJ";
    public static final String DATABASE_OBJECT_TOC_ARRAY = "TYP_GBM_SCSS_TOC_SLAB";

    public static final String DATABASE_OBJECT_COL_TOC_SHARING = "TYP_GBM_SCSS_TOC_SHARE_OBJ";
    public static final String DATABASE_OBJECT_TOC_SHARING_ARRAY = "TYP_GBM_SCSS_TOC_SHARE";


    public static final String DATABASE_OBJECT_DEPOSIT_ARRAY = "TY_GBM_SSA_DEPOSIT_TYP_OBJ";
    public static final String DATABASE_COLLECTION_DEPOSIT_ARRAY = "TY_GBM_SSA_DEPOSIT_TYP";


    public static final String DATABASE_OBJECT_PENALITY_ARRAY = "TYP_GBM_PENALITY_WAVER_OBJ";
    public static final String DATABASE_PENALITY_WAVER_ARRAY = "TYP_GBM_PENALITY_WAVER";


    public static final String DATABASE_OBJECT_SSA_CLOSURE_DETAILS = "TYP_SSA_CLOSURE_OBJ";
    public static final String DATABASE_SSA_CLOSURE_ARRAY = "TYP_SSA_CLOSURE";

    public static final String EOD_FLAG_INTEREST_PAYMENT_PENDING = "IPP";
    public static final String EOD_FLAG_CASH_BAL_VALIDATION = "CBV";
    public static final String EOD_FLAG_DEPOSIT_AMT_VALIDATION = "DAV";

    public static final String PPF_MODULE_ID = "009";

    public static final Character COMPLETED_CONST = 'C';

    public static final String TRAN_SUCCESS_MSG = "Successful Transaction";

    public static final String LEVEL_CODE_LINK_CELL = "1";
    public static final String LEVEL_CODE_PAY_BRANCH = "2";
    public static final String INVALID_LEVEL_CODE_MSG = "INVALID LEVEL CODE";

    public static final String DATABASE_OBJECT_GUARDIAN_DETAILS = "TYP_GBM_PPF_GUARD_OBJ";
    public static final String DATABASE_COLLECTION_GUARDIAN_DETAILS = "TYP_GBM_PPF_GUARD";

    public static final String DATABASE_PPF_OBJECT_NOMINEE_DETAILS = "TYP_GBM_PPF_NOM_OBJ";
    public static final String DATABASE_PPF_COLLECTION_NOMINEE_DETAILS = "TYP_GBM_PPF_NOM";

    public static final String DATABASE_PPF_OBJECT_LEGAL_HEIR_DETAILS = "TYP_GBM_PPF_LEG_HEIR_DET_OBJ";
    public static final String DATABASE_PPF_COLLECTION_LEGAL_HEIR_DETAILS = "TYP_GBM_PPF_LEG_HEIR_DET";

    public static final String DATABASE_OBJECT_NOMINTION_ADD = "TYP_GBM_PP_COL_DET_ARRAY";

    public static final String DATABASE_OBJECT_NOMINEE_ADD = "TYP_GBM_NOMINEE_DTLS";

    public static final String SSA_DATABASE_OBJECT_TRAN_ACC_VAL = "TYP_GBM_SSA_TRAN_AC_VAL_OBJ";
    public static final String SSA_DATABASE_COLLECTION_TRAN_ACC_VAL = "TYP_GBM_SSA_TRAN_AC_VAL";

    public static final String DATABASE_OBJECT_DEPOSIT_DETAILS = "TY_GBM_SSA_DEPOSIT_TYP_OBJ";
    public static final String DATABASE_COLLECTION_DEPOSIT_DETAILS = "TY_GBM_SSA_DEPOSIT_TYP";

    public static final String DATABASE_OBJECT_NOMINEE_DETAILS = "TYP_GBM_SCSS_NOM_DET_OBJ";
    public static final String DATABASE_COLLECTION_NOMINEE_DETAILS = "TYP_GBM_SCSS_NOM_DET";

    public static final String DATABASE_OBJECT_KVP_NOMINEE_DETAILS = "TYP_GBM_KVP_NOM_OBJ";
    public static final String DATABASE_COLLECTION_KVP_NOMINEE_DETAILS = "TYP_GBM_KVP_NOM";

    public static final String DATABASE_OBJECT_LEGAL_HEIR_DETAILS = "TYP_GBM_SCSS_LEG_HEIR_DET_OBJ";
    public static final String DATABASE_COLLECTION_LEGAL_HEIR_DETAILS = "TYP_GBM_SCSS_LEG_HEIR_DET";

    public static final String DATABASE_OBJECT_SCSS_CBS_ACCT_DETAILS = "TYP_GBM_SCSS_CBS_AC_DET_OBJ";
    public static final String DATABASE_COLLECTION_SCSS_CBS_ACCT_DETAILS = "TYP_GBM_SCSS_CBS_AC_DET";

    public static final String DATABASE_OBJECT_NPS_CBS_ACCT_DETAILS = "TYP_GBM_NPS_CBS_AC_DET_OBJ";
    public static final String DATABASE_COLLECTION_NPS_CBS_ACCT_DETAILS = "TYP_GBM_NPS_CBS_AC_DET";

    public static final String DATABASE_OBJECT_PENSION_CBS_ACCT_DETAILS = "TYP_GBM_PP_CBS_AC_DET_OBJ";
    public static final String DATABASE_COLLECTION_PENSION_CBS_ACCT_DETAILS = "TYP_GBM_PP_CBS_AC_DET";

    public static final String SCSS_CLOSURE_PAYMENT_MODE_CREDIT_ACCOUNT = "PM001";
    public static final String SCSS_CLOSURE_PAYMENT_MODE_DD_PO = "PM002";
    public static final String SCSS_CLOSURE_PAYMENT_MODE_NEFT = "PM003";
    public static final String SCSS_CLOSURE_PAYMENT_MODE_RTGS = "PM004";

    public static final String SCSS_MODULE_ID = "0501";

    public static final String SCSS_MODULE_CODE = "005";

    public static final String SCSS_VERSION_NO = "00001";

    /*gst MODULE*/
    public static final String BOD_STEP_POOL_AC_REQ = "0";
    public static final String BOD_STEP_INITIATE_BOD = "1";
    public static final String BOD_STEP_COMPLETE_BOD = "2";
    public static final String GST_MODULE_ID = "0500";
    public static final String GST_MODULE_CODE = "007";
    public static final String STAGE_INITIAL = "0";
    public static final String STAGE_ONE = "1";
    public static final String STAGE_TWO = "2";
    public static final String STAGE_THREE = "3";
    public static final String STAGE_FOUR = "4";
    public static final String LEVEL_CODE_EFP_BRANCH = "1";
    public static final String LEVEL_CODE_COLL_BRANCH = "2";
    public static final String EOD_FLAG_COLL_BRANCH_EOD_NOT_COMPLETED = "CEN";
    public static final String CLEARING_CONST = "CL";
    public static final String BOUNCE_CONST = "B";
    public static final String CLEARED_CONST = "C";
    public static final String DATABASE_OBJECT_REMITTANCE_CALENDER = "TYP_GBM_GST_REMIT_CAL_OBJ";
    public static final String DATABASE_OBJECT_REMITTANCE_CALENDER_ARRAY = "TYP_GBM_GST_REMIT_CAL";

    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER = "TYP_GBM_GST_LC_TRAN_OBJ";
    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER_ARRAY = "TYP_GBM_GST_LC_TRAN";

    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER_VERIFY = "TYP_GBM_GST_LC_AC_VAL_OBJ";
    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER_VERIFY_ARRAY = "TYP_GBM_GST_LC_AC_VAL";

    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER_VERIFY_RES = "TYP_GBM_GST_EFPB_EOD_CRES_OBJ";
    public static final String DATABASE_OBJECT_AUTOMATED_FUND_TRANSFER_VERIFY_RES_ARRAY = "TYP_GBM_GST_EFPB_EOD_CRES";

    public static final String DATABASE_OBJECT_GST_EOD_VAL = "TYP_GBM_GST_EFPB_EOD_VAL_OBJ";
    public static final String DATABASE_COLLECTION_GST_EOD_VAL = "TYP_GBM_GST_EFPB_EOD_VAL";

    public static final String DATABASE_OBJECT_GST_PRE_EOD_VAL = "TYP_GBM_GST_EFPB_PREOD_VAL_OBJ";
    public static final String DATABASE_COLLECTION_GST_PRE_EOD_VAL = "TYP_GBM_GST_EFPB_PREOD_VAL";

    public static final String DATABASE_OBJECT_GST_EOD_TRAN_RES = "TYP_GBM_GST_EFPB_EOD_CRES_OBJ";
    public static final String DATABASE_COLLECTION_GST_EOD_TRAN_RES = "TYP_GBM_GST_EFPB_EOD_CRES";

    public static final String DATABASE_OBJECT_GBM_GST_POST_RECO_OBJ = "TYP_GBM_GST_POST_RECO_OBJ";
    public static final String DATABASE_COLLECTION_GBM_GST_POST_RECO = "TYP_GBM_GST_POST_RECO";

    public static final String DATABASE_OBJECT_GST_CASE_FINANCIAL_RESOLUTION = "TYP_GBM_GST_CASE_FIN_RES_OBJ";
    public static final String DATABASE_COLLECTION_GST_CASE_FINANCIAL_RESOLUTION = "TYP_GBM_GST_CASE_FIN_RES";
    public static final String DATABASE_OBJECT_SINGLE_COLUMN = "TYP_SINGLE_COL_OBJ";
    public static final String DATABASE_COLLECTION_SINGLE_COLUMN = "TYP_SINGLE_COL";
    /* GST Luggage File Constants */
    public static final String LUGGAGE_FILE_TYPE = "ERV5";

    public static final String LUGGAGE_FILE_REPORTING_OFFICE = "04";

    public static final String LUGGAGE_FILE_ORIGIN_SYSTEM_CODE = "GSTN";

    public static final String LUGGAGE_FILE_AGENCY_BANK_CODE = "00001459";
    public static final String LUGGAGE_FILE_AGENCY_BANK_CODE_NO_PREFIX = "1459";

    public static final String LUGGAGE_FILE_PREFIX_STRING = CommonConstants.LUGGAGE_FILE_TYPE + CommonConstants.LUGGAGE_FILE_REPORTING_OFFICE + CommonConstants.LUGGAGE_FILE_ORIGIN_SYSTEM_CODE + CommonConstants.LUGGAGE_FILE_AGENCY_BANK_CODE;

    public static final String LUGGAGE_FILE_RECEIVER_RBI = "RBI";

    public static final String LUGGAGE_FILE_RECEIVER_GSTIN = "GSTN";

    public static final String LUGGAGE_FILE_DEFAULT_ADMIN_ZONE = "99";

    public static final String LUGGAGE_FILE_DEFAULT_MSG_DEF_ID = "pacs.008.001.05";

    public static final String LUGGAGE_FILE_DEFAULT_BUSINESS_SERVICE = "FIToFICustomerCreditTransfer";

    public static final String REV_LUGGAGE_FILE_TYPE = "MEV5";

    public static final String REV_LUGGAGE_FILE_DEFAULT_MSG_DEF_ID = "moe.pacs.008.001.05";


    public static final int GSTIN_LUGGAGE_FILE_SIZE_IN_BYTES = 1024 * 1024 * 10; // 10 MB
    /* GST Luggage File Constants */

    public static final String GST_MODULE_NAME = "GST";
    public static final String GST_API_CPIN_VALIDATE_USE_CASE_ID = "UC_GST_001";
    public static final String GST_API_CPIN_INSERT_USE_CASE_ID = "UC_GST_002";
    public static final String GST_API_CPIN_INQUIRY_USE_CASE_ID = "UC_GST_003";
    public static final String GST_API_LUGGAGE_FILE_FETCH_ACTION = "LUGGFILDTLS";
    public static final String GST_API_POST_RECON_RESULT_ACTION = "RECONFILDTLS";
    public static final String GST_API_ON_DEMAND_FETCH_ACTION = "ODVPMT";

    /*Kvp constants*/
    public static final String DATABASE_OBJECT_JOINT_DETAILS = "TYP_GBM_KVP_JNT_OBJ";
    public static final String DATABASE_COLLECTION_JOINT_DETAILS = "TYP_GBM_KVP_JNT";

    /*ST API*/
    public static final String ST_MODULE_NAME = "ST";
    public static final String ST_API_CIN_VALIDATE_USE_CASE_ID = "UC_ST_001";
    public static final String ST_API_CIN_INSERT_USE_CASE_ID = "UC_ST_002";
    public static final String ST_API_CIN_INQUIRY_USE_CASE_ID = "UC_ST_003";


    public static final String CBDT_API_LUGGAGE_FILE_FETCH_ACTION = "LUGGFILDTLS";
    public static final String CBDT_API_POST_RECON_RESULT_ACTION = "RECONFILDTLS";
    public static final String CBDT_API_ON_DEMAND_FETCH_ACTION = "ODVPMT";
    public static final String CBDT_API_EDMS_PFMS_RESULT_ACTION = "DMSSRG";
    public static final String CBDT_API_PFMS_SCROLL_ERROR_RESULT_ACTION = "ERRORSCONCILE";
    public static final String CBDT_ON_DEMAND_TXNCASE_ONLINE = "ONLPMT";
    public static final String CBDT_ON_DEMAND_TXNCASE_BANKRECON = "BANKRECON";
    public static final String CBDT_ON_DEMAND_TXNCASE_GRIEVANCE = "GRIEVANCE";
    public static final String CBDT_TRANMODE_RETAIL_INTERNET = "RI";
    public static final String CBDT_TRANMODE_CORP_INTERNET = "CI";
    public static final String CBDT_TRANMODE_INTERNET = "I";
    public static final String CBDT_TRANMODE_CREDIT_CARDS = "CC";
    public static final String CBDT_TRANMODE_DEBIT_CARDS = "DC";
    public static final String CBDT_TRANMODE_CASH = "C";
    public static final String CBDT_TRANMODE_TRANSFER = "T";
    public static final String CBDT_TRANMODE_CLEARING = "CL";
    public static final String CBDT_TRANMODE_DEMAND_DRAFT = "DD";

    /*State Tax*/
    /*Main Treasury and Sub Treasury Mapping*/
    public static final String DATABASE_COLLECTION_TYP_GBM_ST_TRSY_MAP = "TYP_GBM_ST_TRSY_MAP";
    public static final String DATABASE_OBJECT_TYP_GBM_ST_TRSY_MAP_OBJ = "TYP_GBM_ST_TRSY_MAP_OBJ";
    public static final String DATABASE_COLLECTION_TYP_ID = "TYP_ID";
    public static final String DATABASE_OBJECT_TYP_ID_OBJ = "TYP_ID_OBJ";
    /*Upload for State Master Definition*/
    public static final String DATABASE_OBJECT_UPLOAD_STATE_TAX_DEFINITION = "GBM_ST_UPL_TYPE_OBJ";
    public static final String DATABASE_COLLECTION_UPLOAD_STATE_TAX_DEFINITION = "TYP_GBM_ST_UPL";
    /*Transaction Maintenance Array*/
    public static final String DATABASE_COLLECTION_TYP_GBM_ST_COLL_MAST = "TYP_GBM_ST_COLL_MAST";
    public static final String DATABASE_OBJECT_TYP_GBM_ST_COLL_MAST = "TYP_GBM_ST_COLL_MAST_OBJ";
    public static final String DATABASE_COLLECTION_TYP_GBM_ST_COLL_DTLS = "TYP_GBM_ST_COLL_DTLS";
    public static final String DATABASE_OBJECT_TYP_GBM_ST_COLL_DTLS_OBJ = "TYP_GBM_ST_COLL_DTLS_OBJ";
    public static final String ST_MODULE_ID = "001";
    public static final String ST_MODULE_CODE = "007";

    public static final String CBDT_MODULE_ID = "004";
    public static final String CBDT_MODULE_CODE = "0400";
    public static final String DATABASE_OBJECT_ST_REMITTANCE_CALENDER = "TYP_GBM_ST_REMIT_CAL_OBJ";
    public static final String DATABASE_OBJECT_ST_REMITTANCE_CALENDER_ARRAY = "TYP_GBM_ST_REMIT_CAL";


    public static final String CBDT_MODULE_NAME = "CBDT";
    public static final String CBDT_API_CRN_VALIDATE_USE_CASE_ID = "UC_CBDT_001";
    public static final String CBDT_API_CRN_INSERT_USE_CASE_ID = "UC_CBDT_002";
    public static final String CBDT_API_CRN_INQUIRY_USE_CASE_ID = "UC_CBDT_003";


    /* cbdt Luggage File Constants */
    public static final String CBDT_LUGGAGE_FILE_TYPE = "ERV5";

    public static final String CBDT_LUGGAGE_FILE_TYPE_JSON = "KKBK_SCROLLFILE_";
    public static final String CBDT_LUGGAGE_FILE_TYPE_TIN_JSON = "TIN2.0_DRFTLUGGFILE_";

    public static final String CBDT_LUGGAGE_FILE_REPORTING_OFFICE = "04";

    public static final String CBDT_LUGGAGE_FILE_ORIGIN_SYSTEM_CODE = "CBDT";

    public static final String CBDT_LUGGAGE_FILE_AGENCY_BANK_CODE = "00001459";

    public static final String CBDT_LUGGAGE_FILE_PREFIX_STRING = CommonConstants.CBDT_LUGGAGE_FILE_TYPE + CommonConstants.CBDT_LUGGAGE_FILE_REPORTING_OFFICE + CommonConstants.CBDT_LUGGAGE_FILE_ORIGIN_SYSTEM_CODE + CommonConstants.CBDT_LUGGAGE_FILE_AGENCY_BANK_CODE;
    public static final String CBDT_LUGGAGE_FILE_PREFIX_STRING_FOR_PFMS = CommonConstants.CBDT_LUGGAGE_FILE_TYPE_JSON ;
    public static final String CBDT_LUGGAGE_FILE_PREFIX_STRING_FOR_TIN = CommonConstants.CBDT_LUGGAGE_FILE_TYPE_TIN_JSON ;

    public static final String CBDT_LUGGAGE_FILE_RECEIVER_RBI = "RBI";


    public static final String CBDT_LUGGAGE_FILE_DEFAULT_ADMIN_ZONE = "98";

    public static final String CBDT_LUGGAGE_FILE_DEFAULT_MSG_DEF_ID = "pacs.008.001.05";

    public static final String CBDT_LUGGAGE_FILE_DEFAULT_BUSINESS_SERVICE = "FIToFICustomerCreditTransfer";

    public static final int TIN_LUGGAGE_FILE_SIZE_IN_BYTES = 1024 * 1024 * 5; // 5 MB

    public static final int PFMS_LUGGAGE_FILE_SIZE_IN_BYTES = 1024 * 1024 * 10; // 5 MB

    public static final String DATABASE_OBJECT_GBM_CBDT_POST_RECO_OBJ = "TYP_GBM_CBDT_POST_RECO_OBJ";
    public static final String DATABASE_COLLECTION_GBM_CBDT_POST_RECO = "TYP_GBM_CBDT_POST_RECO";

    public static final String DATABASE_OBJECT_CBDT_REMITTANCE_CALENDER = "TYP_GBM_CBDT_REMIT_CAL_OBJ";
    public static final String DATABASE_OBJECT_CBDT_REMITTANCE_CALENDER_ARRAY = "TYP_GBM_CBDT_REMIT_CAL";


    public static final String PROCESS_ID_STRING = "processId";
    public static final String TREASURY_CODE_STRING = "treasuryCode";
    public static final String MONTH_STRING = "month";
    public static final String YEAR_STRING = "year";
    public static final String BRANCH_ID_STRING = "branchId";
    public static final String CENTRALIZED_FLAG_STRING = "centralizedFlag";
    public static final String SCSS_CUST_ID_STRING = "scssCustomerId";
    public static final String SCSS_ACCT_NUM_STRING = "scssAcctNo";
    public static final String AVAILABLE_VAL_BATCH_STRING = "availableValBatch";
    public static final String AVAILABLE_TRAN_BATCH_STRING = "availableTranBatch";

    public static final String APY_FREQUENCY = "frequency";
    public static final String APY_NO_INSTALLMENTS_DUES = "noOfInstallmentsOrDues";

    public static final String PENSION_FROM_DATE = "fromDate";
    public static final String PENSION_TO_DATE = "toDate";


    public static final String STATUS_MESSAGE = "Status :";
    public static final String EXCEPTION_MESSAGE = "Exception :";
    public static final String EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE = "Not able to establish connection please contact administrator";
    public static final String EXCEPTION_MESSAGE_INVALID_OFFICE_ACCT_DET = "INVALID OFFICE ACCOUNT DETAILS "; // "INVALID OFFICE ACCOUNT DETAILS "
    public static final String EXCECUTED_SQL_QUERY_MESSAGE = "Executed the sql query:";

    public static final String PROC_PARAM_USER_ID_IN = "P_USER_ID_IN";    //P_USER_ID_IN
    public static final String PROC_PARAM_USER_SOL_ID_IN = "P_USER_SOL_ID_IN";    //P_USER_SOL_ID_IN
    public static final String PROC_PARAM_BRANCH_ID_IN = "P_BRANCH_ID_IN";
    public static final String PROC_PARAM_FLAG_ID_IN = "P_FLAG_IN";    //P_BRANCH_ID_IN//P_BRANCH_ID_IN
    public static final String PROC_PARAM_CURSOR_VAL_OUT = "P_CURSOR_VAL_OUT";    //P_CURSOR_VAL_OUT
    public static final String P_TRNSF_INST_MICR_OUT = "p_trsfr_inst_micr_out";    //P_CURSOR_VAL_OUT
    public static final String PROC_PARAM_CURSOR_OUT = "P_CURSOR_OUT";    //P_CURSOR_OUT
    public static final String P_TRAN_COUNT_OUT = "P_TRAN_COUNT_OUT";
    public static final String P_FILE_TOT_AMT_OUT = "P_FILE_TOT_AMT_OUT";
    public static final String PROC_PARAM_MSG_OUT = "P_MSG_OUT";    //P_MSG_OUT
    public static final String PROC_PARAM_MSG_DESC_OUT = "P_MSG_DESC_OUT"; //P_MSG_DESC_OUT

    public static final String PROC_PARAM_ENTERER_ID_IN = "P_ENTERER_ID_IN";    //P_ENTERER_ID_IN
    public static final String PROC_PARAM_ENTERER_SOL_ID_IN = "P_ENTERER_SOL_ID_IN";    //P_ENTERER_SOL_ID_IN
    public static final String PROC_PARAM_MODIFIER_ID_IN = "P_MODIFIER_ID_IN";    //P_MODIFIER_ID_IN
    public static final String PROC_PARAM_MODIFIER_SOL_ID_IN = "P_MODIFIER_SOL_ID_IN";    //P_MODIFIER_SOL_ID_IN
    public static final String PROC_PARAM_VERIFIER_ID_IN = "P_VERIFIER_ID_IN";    //P_VERIFIER_ID_IN
    public static final String PROC_PARAM_VERIFIER_SOL_ID_IN = "P_VERIFIER_SOL_ID_IN";    //P_VERIFIER_SOL_ID_IN
    public static final String PROC_PARAM_DELETER_ID_IN = "P_DELETER_ID_IN";    //P_DELETER_ID_IN
    public static final String PROC_PARAM_DELETER_SOL_ID_IN = "P_DELETER_SOL_ID_IN";    //P_DELETER_SOL_ID_IN


    public static final String PROC_PARAM_GBM_TRAN_ID_IN = "P_GBM_TRAN_ID_IN";    //P_GBM_TRAN_ID_IN
    public static final String PROC_PARAM_SCREEN_TYPE_IN = "P_SCREEN_TYPE_IN";    //P_GBM_TRAN_ID_IN
    public static final String PROC_PARAM_CBS_TRAN_ID_IN = "P_CBS_TRAN_ID_IN";    //P_CBS_TRAN_ID_IN
    public static final String PROC_PARAM_CBS_TRAN_STAT_IN = "P_CBS_TRAN_STAT_IN";    //P_CBS_TRAN_STAT_IN
    public static final String PROC_PARAM_CBS_TRAN_STAT_DESC_IN = "P_CBS_TRAN_STATDESC_IN";    //P_CBS_TRAN_STATDESC_IN
    public static final String PROC_PARAM_TRAN_CHANNEL_TYPE_OUT = "P_CHANNEL_TYPE_OUT";    //P_CHANNEL_TYPE_OUT
    public static final String PROC_PARAM_TRAN_SOL_ID_OUT = "P_SOL_ID_OUT";    //P_SOL_ID_OUT
    public static final String PROC_PARAM_GBM_TRAN_ID_OUT = "P_GBM_TRAN_ID_OUT";    //P_GBM_TRAN_ID_OUT
    public static final String PROC_PARAM_CBS_TRAN_STAT_OUT = "P_CBS_TRAN_STAT_OUT";    //P_CBS_TRAN_STAT_OUT -
    public static final String PROC_PARAM_TRAN_DATE_IN = "P_TRAN_DATE_IN";    //P_TRAN_DATE_OUT
    public static final String PROC_P_POST_EOD_FLG_IN_IN = "P_POST_EOD_FLG_IN";    //P_POST_EOD_FLG_IN
    public static final String PROC_P_BRANCH_ID_IN_IN = "P_BRANCH_ID_IN";    //P_BRANCH_ID_IN
    public static final String PROC_P_BR_DET_REQ_FLG_IN_IN = "p_br_det_req_flg_in";    //P_BRANCH_ID_IN
    public static final String PROC_PARAM_TRAN_DATE_OUT = "P_TRAN_DATE_OUT";    //P_TRAN_DATE_OUT
    public static final String PROC_PARAM_TRAN_DET_OUT = "P_TRAN_DET_OUT";    //P_TRAN_DET_OUT
    public static final String PROC_PARAM_TRAN_SCHEME_CODE_OUT = "P_SCH_CODE_OUT";    //P_SCH_CODE_OUT
    public static final String PROC_PARAM_TRAN_SCREEN_OUT = "P_SCREEN_TRAN_CODE_OUT";    //P_SCREEN_TRAN_CODE_OUT
    public static final String CURSOR_PARAM_TRAN_ACCT_NUM = "ACCT_NUM";    //ACCT_NUM
    public static final String CURSOR_PARAM_TRAN_TYPE = "TRAN_TYPE";    //TRAN_TYPE
    public static final String CURSOR_PARAM_AMT = "AMT";    //AMT

    public static final String PROC_PARAM_PROCESS_ID_IN = "P_PROCESS_ID_IN";    //P_PROCESS_ID_IN
    public static final String PROC_PARAM_VALIDATED_ACCT_DET_IN = "P_AC_VAL_DET_IN";    //P_AC_VAL_DET_IN  ---
    public static final String PROC_PARAM_TYPE_IN = "P_TYPE_IN";    //P_TYPE_IN
    public static final String PROC_PARAM_TRAN_ACCT_DET_OUT = "P_TRAN_AC_DET_OUT";    //P_TRAN_AC_DET_OUT
    public static final String PROC_PARAM_RECORD_REMAIN_FLAG_OUT = "P_REC_REM_FLG_OUT";    //P_REC_REM_FLG_OUT
    public static final String PROC_PARAM_BATCH_REMAIN_FLAG_OUT = "P_BAT_REM_FLG_OUT";    //P_BAT_REM_FLG_OUT

    public static final String PROC_PARAM_CREATED_BY_OUT = "P_CREATED_BY_OUT";    //P_CREATED_BY_OUT
    public static final String PROC_PARAM_CREATED_DATE_OUT = "P_CREATED_DATE_OUT";    //P_CREATED_DATE_OUT
    public static final String PROC_PARAM_MODIFIED_BY_OUT = "P_MODIFIED_BY_OUT";    //P_MODIFIED_BY_OUT
    public static final String PROC_PARAM_MODIFIED_DATE_OUT = "P_MODIFIED_DATE_OUT";    //P_MODIFIED_DATE_OUT
    public static final String PROC_PARAM_VERIFIED_BY_OUT = "P_VERIFIED_BY_OUT";    //P_VERIFIED_BY_OUT
    public static final String PROC_PARAM_VERIFIED_DATE_OUT = "P_VERIFIED_DATE_OUT";    //P_VERIFIED_DATE_OUT
    public static final String PROC_PARAM_VERIFIED_STATUS_OUT = "P_VERIFIED_STATUS_OUT";    //P_VERIFIED_STATUS_OUT

    public static final String CURSOR_PARAM_OPR_ACCT_NUM = "OPR_ACCT_NUM";    //OPR_ACCT_NUM
    public static final String STATUS_STRING = "STATUS";    //STATUS
    public static final String REMARKS_CONST = "REMARKS";    //REMARKS
    public static final String CURSOR_PARAM_ACCT_BAL = "AC_BAL";    //AC_BAL
    public static final String PROC_BR_DET_OUT = "p_br_det_out";    //p_br_det_out
    public static final String PROC_DET_OUT = "p_det_out";    //p_det_out


    public static final String GBM_TRAN_ID_STRING = "GBM_TRAN_ID";
    public static final String INSTRUMENT_NO_STRING = "INSTRUMENT_NO";
    public static final String INSTRUMENT_DATE_STRING = "INSTRUMENT_DATE";
    public static final String INSTRUMENT_AMOUNT_STRING = "INSTRUMENT_AMOUNT";
    public static final String CBS_TRAN_ID_STRING = "CBS_TRAN_ID";
    public static final String TRAN_DATE_STRING = "TRAN_DATE";

    public static final String DATABASE_OBJECT_ISL_DETAILS = "TYP_SINGLE_COL_OBJ";
    public static final String DATABASE_COLLECTION_ISL_DETAILS= "TYP_SINGLE_COL";

    public static final String DATABASE_OBJECT_CBS_AVAIL = "TYP_GBM_CBS_AVAIL_OBJ";
    public static final String DATABASE_COLLECTION_CBS_AVAIL= "TYP_GBM_CBS_AVAIL";

    public static final String DATABASE_STG_OBJ_DETAILS = "TYP_GBM_UPD_STG_OBJ";
    public static final String DATABASE_REC_DET_DETAILS= "TYP_GBM_UPD_STG";

    public static final String PHOTHERBANKACCOUNT = "PH_OTHERBRANCHCRAC";
    public static final String VERIFIED_STATUS = "VERIFIED_STATUS";

    /*File Read Line Max Size*/
    public static final Integer FILE_READER_LINE_MAX_SIZE = 10000;

    public static final String P_USER_MENU_OUT ="P_USER_MENU_OUT";
    public static final String P_SOL_DATE_OUT = "P_SOL_DATE_OUT";
    public static final String P_SOL_DESC_OUT = "P_SOL_DESC_OUT";
    public static final String P_USERNAME_IN = "P_USERNAME_IN";

    public static final String P_USER_ID_IN = "P_USER_ID_IN";    //P_CREATED_BY_OUT
    public static final String P_USER_SOL_ID_IN = "P_USER_SOL_ID_IN";    //P_CREATED_DATE_OUT
    public static final String P_MSG_DESC_OUT = "P_MSG_DESC_OUT";    //P_MODIFIED_BY_OUT
    public static final String P_LUG_FILE_NAME_IN = "P_LUG_FILE_NAME_IN";    //P_MODIFIED_DATE_OUT
    public static final String P_TRAN_DATE_IN = "P_TRAN_DATE_IN";    //P_MODIFIED_DATE_OUT
    public static final String P_REC_REM_FLG_OUT = "P_REC_REM_FLG_OUT";    //P_VERIFIED_BY_OUT
    public static final String P_REC_DET_OUT = "P_REC_DET_OUT";    //P_VERIFIED_DATE_OUT
    public static final String P_MSG_OUT = "P_MSG_OUT";    //
    public static final String PROC_LUG_FILE_DATA_IN = "P_LUG_FILE_DATA_IN";    //
    public static final  String P_TRANSMISSION_ID_IN = "P_TRANSMISSION_ID_IN";
    public static final String P_TRAN_DATE_OUT = "P_TRAN_DATE_OUT";
    public static final String P_TRANSMISSION_ID_OUT = "P_TRANSMISSION_ID_OUT";
    public static final String P_FILE_SUMRY_DET_OUT = "P_FILE_SUMRY_DET_OUT";
    public static final String P_FILE_SUMRY_DET_IN = "P_FILE_SUMRY_DET_IN";
    public static final String P_FILE_DET_OUT = "P_FILE_DET_OUT";
    public static final String P_LUGG_FILE_FNAME_IN = "P_LUGG_FILE_FNAME_IN";
    public static final String P_TORCH_OUT = "P_TORCH_OUT";
    public static final String P_DATA_STRING_OUT = "P_DATA_STRING_OUT";
    public static final String P_SET_ID_OUT = "P_SET_ID_OUT";
    public static final String P_LUGG_FILE_DATA_OUT = "P_LUGG_FILE_DATA_OUT";




//
//
//    public static final String P_USER_ID_IN = "P_USER_ID_IN";    //P_CREATED_BY_OUT
//    public static final String P_USER_SOL_ID_IN = "P_USER_SOL_ID_IN";    //P_CREATED_DATE_OUT
//    public static final String P_MSG_DESC_OUT = "P_MSG_DESC_OUT";    //P_MODIFIED_BY_OUT
//    public static final String P_LUG_FILE_NAME_IN = "P_LUG_FILE_NAME_IN";    //P_MODIFIED_DATE_OUT
//    public static final String P_TRAN_DATE_IN = "P_TRAN_DATE_IN";    //P_MODIFIED_DATE_OUT
//    public static final String P_REC_REM_FLG_OUT = "P_REC_REM_FLG_OUT";    //P_VERIFIED_BY_OUT
//    public static final String P_REC_DET_OUT = "P_REC_DET_OUT";    //P_VERIFIED_DATE_OUT
//    public static final String P_MSG_OUT = "P_MSG_OUT";
    public static final String P_LUG_FILE_DATA_IN = "P_LUG_FILE_DATA_IN";


    public static final String SERVER_SIDE_ERROR = "server side error occured,pls contact system administrator";


    /* icegate Luggage File Constants */
    public static final String ICEGATE_LUGGAGE_FILE_TYPE = "ERV5";

    public static final String ICEGATE_LUGGAGE_FILE_TYPE_JSON = "KKBK_SCROLLFILE_";
    public static final String ICEGATE_LUGGAGE_FILE_TYPE_TIN_JSON = "TIN2.0_DRFTLUGGFILE_";

    public static final String ICEGATE_LUGGAGE_FILE_REPORTING_OFFICE = "04";

    public static final String ICEGATE_LUGGAGE_FILE_ORIGIN_SYSTEM_CODE = "ICGT";

    public static final String ICEGATE_LUGGAGE_FILE_AGENCY_BANK_CODE = "1995";

    public static final String ICEGATE_LUGGAGE_FILE_PREFIX_STRING = CommonConstants.ICEGATE_LUGGAGE_FILE_TYPE + CommonConstants.ICEGATE_LUGGAGE_FILE_REPORTING_OFFICE + CommonConstants.ICEGATE_LUGGAGE_FILE_ORIGIN_SYSTEM_CODE + CommonConstants.ICEGATE_LUGGAGE_FILE_AGENCY_BANK_CODE;
    public static final String ICEGATE_LUGGAGE_FILE_PREFIX_STRING_FOR_PFMS = CommonConstants.ICEGATE_LUGGAGE_FILE_TYPE_JSON ;
    public static final String ICEGATE_LUGGAGE_FILE_PREFIX_STRING_FOR_TIN = CommonConstants.ICEGATE_LUGGAGE_FILE_TYPE_TIN_JSON ;

    public static final String ICEGATE_LUGGAGE_FILE_RECEIVER_RBI = "RBI";


    public static final String ICEGATE_LUGGAGE_FILE_DEFAULT_ADMIN_ZONE = "98";

    public static final String ICEGATE_LUGGAGE_FILE_DEFAULT_MSG_DEF_ID = "pacs.008.001.05";

    public static final String ICEGATE_LUGGAGE_FILE_DEFAULT_BUSINESS_SERVICE = "FIToFICustomerCreditTransfer";

    public static final String ICEGATE_MODULE_CODE = "0400";


    public static final String ICEGATE_MODULE_NAME = "ICEGATE";
    public static final String ICEGATE_API_CRN_VALIDATE_USE_CASE_ID = "UC_ICEGATE_001";
    public static final String ICEGATE_API_CRN_INSERT_USE_CASE_ID = "UC_ICEGATE_002";
    public static final String ICEGATE_API_CRN_INQUIRY_USE_CASE_ID = "UC_ICEGATE_003";

    public static final String DATABASE_OBJECT_GBM_ICEGATE_POST_RECO_OBJ = "TYP_GBM_ICEGATE_POST_RECO_OBJ";
    public static final String DATABASE_COLLECTION_GBM_ICEGATE_POST_RECO = "TYP_GBM_ICEGATE_POST_RECO";

    public static final String DATABASE_OBJECT_ICEGATE_REMITTANCE_CALENDER = "TYP_GBM_IGATE_REMIT_CAL_OBJ";
    public static final String DATABASE_OBJECT_ICEGATE_REMITTANCE_CALENDER_ARRAY = "TYP_GBM_IGATE_REMIT_CAL";
    public static final String ICEGATE_PROC_PARAM_BRANCH_ID_IN = "p_branch_id_in ";
    public static final String ICEGATE_PROC_PARAM_MSG_OUT = "p_msg_out";
    public static final String ICEGATE_PROC_PARAM_MSG_DESC_OUT = "p_msg_desc_out";


    public static final String DATABASE_OBJECT_EFREIGHT_REMITTANCE_CALENDER = "TYP_GBM_EFR_REMIT_CAL_OBJ";
    public static final String DATABASE_OBJECT_EFREIGHT_REMITTANCE_CALENDER_ARRAY = "TYP_GBM_EFR_REMIT_CAL";

    public static final String DATABASE_OBJECT_GBM_GST_STATE_TYPE = "GBM_GST_STATE_TYPE_OBJ";
    public static final String DATABASE_GBM_GST_STATE_TYPE = "GBM_GST_STATE_TYPE";

}
