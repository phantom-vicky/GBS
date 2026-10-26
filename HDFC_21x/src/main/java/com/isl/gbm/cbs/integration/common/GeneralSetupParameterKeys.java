/**
 *
 */
package com.isl.gbm.cbs.integration.common;

/**
 * The Enum GeneralSetupKeys.
 *
 * @author harishbabu.t
 */
public enum GeneralSetupParameterKeys {

    /** The user details session mechanism. */
    GBM_INT_01("GBM_INT_01", "To take the users details & session mechanism from CBS, for GBM"),
    /** The transaction date. */
    GBM_INT_02("GBM_INT_02", "To take the transaction date (business date)"),
    /** The branch desc from cbs. */
    GBM_INT_03("GBM_INT_03", "To take the branch description from CBS, Maintainance of bank code, branch code, uniform branch code, address details for each branch"),
    /** The office type accounts for collection. */
    GBM_INT_04("GBM_INT_04", "To identify  office type of accounts used by the bank for collection, suspense etc.,"),
    /** The gbm int 05. */
    GBM_INT_05("GBM_INT_05", "To identify  office type of accounts used by the bank for collection, suspense etc.,"),
    /** The account existence freeze check. */
    GBM_INT_06("GBM_INT_06", "To check for the existence of the account number & validations like Account freeze etc.,"),
    /** The transaction maintainance done through cheques. */
    GBM_INT_07("GBM_INT_07", "To maintain transactions done through cheques from the customer accounts."),
    /** The get account list. */
    GBM_INT_08("GBM_INT_08", "To get the list of accounts based on short name, scheme code, scheme type"),

    /** The get instruments list permitted for entered accnum. */
    GBM_INT_09("GBM_INT_09", "To get the list of instruments permitted for the entered account number."),

    /** The get customer account balance. */
    GBM_INT_10("GBM_INT_10", "To get the account balance of the customer account"),

    /** The actual financial transactions. */
    GBM_INT_11("GBM_INT_11", "To push actual financial transactions in the CBS & enquire the same in GBM with status"),

    /** The clearing instruments. */
    GBM_INT_12("GBM_INT_12", "The clearing instruments will normally be lodged in a zone opened in CBS for the day"),

    /** The individual instrument details lits. */
    GBM_INT_13("GBM_INT_13", "List of individual instrument details in the CBS to get the instrument status information during reconciliation of clearing"),

    /** The open zones from cbs. */
    GBM_INT_14("GBM_INT_14", "To get the open zones from CBS, for clearing transactions"),

    /** The instruments types. */
    GBM_INT_15("GBM_INT_15", "To get different types of instruments used in clearing transactions"),

    /** The bank code and name. */
    GBM_INT_16("GBM_INT_16", "To get bank code & corresponding bank name"),

    /** The branches under banks. */
    GBM_INT_17("GBM_INT_17", "To get branches coming under different banks"),

    /** The micr instruments sort codes. */
    GBM_INT_18("MICR_INSTRUMENTS_SORT_CODES", "To get sort codes pertaining to MICR instruments"),

    /** The tally accounts collection. */
    GBM_INT_19("GBM_INT_19", "To hit transactions in CBS, to tally the accounts collection & funds transfer to Govt A/c."),

    /** The holiday check cbs. */
    GBM_INT_20("GBM_INT_20", "To arrive at the next working day in a branch  & check whether that date is a holiday in CBS."),

    /** The office customer accounts for schemes. */
    GBM_INT_21("GBM_INT_21", "To create office & customer accounts under different schemes launched by the bank"),

    /** The current day transactions inquiry. */
    GBM_INT_22("GBM_INT_22", "To provide inquiry of all current business day financial transactions done in GBM"),

    /** The gbm transactions history. */
    GBM_INT_23("GBM_INT_23", "To provide history of all financial transactions done in GBM"),

    /** The valid session check. */
    GBM_INT_24("GBM_INT_24", "To ensure that the user has a valid session in CBS, anytime the user clicks the GBM links"),

    /** The authorize transactions. */
    GBM_INT_25("GBM_INT_25", "To authorize the transactions whose work class is greater than the predefined value."),

    /** The eod control. */
    // GBM_INT_26("EOD_CONTROL", "To control the initiation of branch EOD in CBS
    // without GBM MCP completion"),

    GBM_INT_26("GBM_INT_26", "To control the initiation of branch EOD in CBS without GBM MCP completion"),

    /** The collection fund transfer handling. */
    GBM_INT_27("COLLECTION_FUND_TRANSFER_HANDLING", "To handle the transfer of collection funds to the Government account"),

    /** The ifsc lookup. */
    GBM_INT_28("GBM_INT_28", "To lookup Branch IFSC code"),

    /** The customer details. */
    GBM_INT_29("GBM_INT_29", "To fetch customer details"),

    /** The customer address details. */
    GBM_INT_30("GBM_INT_30", "To fetch customer Address Details"),

    /** The customer demographic details. */
    GBM_INT_31("GBM_INT_31", "To fetch customer demographic Details");

    /**
     * Find by key.
     *
     * @param Integration_TP_ID
     *            the integration T P ID
     * @return the nps scheme parameter keys 1
     */
    public static GeneralSetupParameterKeys findByIntTPID(final String Integration_TP_ID) {
        for (final GeneralSetupParameterKeys generalSetupParameterKeys : values()) {
            if (generalSetupParameterKeys.getIntegrationTpId().equals(Integration_TP_ID)) {
                return generalSetupParameterKeys;
            }
        }
        return null;
    }

    /** The Integration T P ID. */
    private String integrationTpId;

    /** The description. */
    private String description;

    /**
     * Instantiates a new general setup parameter keys.
     *
     * @param integration_TP_ID
     *            the integration T P ID
     * @param description
     *            the description
     */
    private GeneralSetupParameterKeys(final String integration_TP_ID, final String description) {
        this.integrationTpId = integration_TP_ID;
        this.description = description;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the integration tp id.
     *
     * @return the integration_tp_id
     */
    public String getIntegrationTpId() {
        return this.integrationTpId;
    }

}
