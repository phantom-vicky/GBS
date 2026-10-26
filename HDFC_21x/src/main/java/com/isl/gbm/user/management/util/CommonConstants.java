/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.user.management.util;

import java.text.SimpleDateFormat;

// TODO: Auto-generated Javadoc

/**
 * The Class CommonConstants.
 *
 * @author Vasanthi
 */
public final class CommonConstants {
    /* USER_ID and SOL_ID are hardcoded for time being */

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
     * The Constant TRAN_LEG_DEBIT.
     */
    public static final Character TRAN_LEG_DEBIT = 'D';

    /**
     * The Constant TRAN_LEG_CREDIT.
     */
    public static final Character TRAN_LEG_CREDIT = 'C';

    /**
     * The Constant USER_ID.
     */
    public static final String USER_ID = "MANOJ6";

    /**
     * The Constant TRAN_OPERATION_ADD.
     */
    public static final char TRAN_OPERATION_ADD = 'A';

    /**
     * The Constant NPS_SCHEME_CODE.
     */
    public static final String NPS_SCHEME_CODE = "0000";

    /**
     * The Constant NO.
     */
    public static final Character NO = 'N';

    /**
     * The Constant YES.
     */
    public static final Character YES = 'Y';

    /**
     * The Constant COMPLETED.
     */
    public static final String COMPLETED = "C";

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
     * The Constant SOL_ID.
     */
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
    public static final SimpleDateFormat FORMAT_DATE_TO_STRING = new SimpleDateFormat("dd/MM/yyyyy");

    /**
     * The Constant FINACLE_VERIFIED.
     */
    public static final Character FINACLE_VERIFIED = 'F';

    /**
     * The Constant PAN_PATTERN.
     */
    public static final String PAN_PATTERN = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

    /**
     * The Constant TAN_PATTERN.
     */
    public static final String TAN_PATTERN = "[A-Z]{4}[0-9]{5}[A-Z]{1}";

    /**
     * The Constant EMAIL_PATTERN.
     */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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
     * The Constant YES_STATUS.
     */
    public static final String YES_STATUS = "Y";

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
     * The Constant DELETE.
     */
    public static final Character DELETE = 'D';

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
     * The Constant KEY_IS_INTERNET_ENABLED_FOR_CBDT.
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

    /**
     * Instantiates a new common constants.
     */

    public static final String NOT_VERIFIED = "Not Verified";

    /**
     * The Constant VERIFIED.
     */
    public static final String VERIFIED = "Verified";

    /**
     * Instantiates a new common constants.
     */

    public static final Number TRUE = 1;

    public static final Number FALSE = 0;

    public static final String PREFIX = "UPD";
    public static final String SUCCESS = "S";
    public static final String FAILURE = "F";
    public static final String NORESPONSE = "X";

    private CommonConstants() {
        // private constructor
    }
}
