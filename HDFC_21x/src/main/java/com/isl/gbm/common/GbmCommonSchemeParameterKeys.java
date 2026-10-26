package com.isl.gbm.common;

public enum GbmCommonSchemeParameterKeys {

    /**
     * Financial Year Start Date.
     */
    FIN_YR_START_DATE("FINYRSTDATE", "Financial Year Start Date"),
    /**
     * Financial Year End Date.
     */
    FIN_YR_END_DATE("FINYRENDDATE", "Financial Year End Date"),
    /**
     * Budget Year Start Date.
     */
    BUD_YR_START_DATE("BUDYRSTDATE", "Budget Year Start Date"),
    /**
     * Error Budget Year End Date.
     */
    BUD_YR_END_DATE("BUDYRENDDATE", "Budget Year End Date"),
    /**
     * Minor Age.
     */
    MINOR_AGE("MINORAGE", "Minor Age"),
    /**
     * Intrument Type Validator.
     */
    INST_TYPE_VALIDATOR("INST_TYPE_VALIDATOR", "Intrument Type Validator"),
    /**
     * Continure after EOD.
     */
    EOD_CONTINUE("EODCONTINUE", "Continure after EOD"),
    /**
     * Turn Over Commission Mode.
     */
    TURN_OVER_MODE("TOCMODE", "Turn Over Commission Mode"),
    /**
     * Minimum Amount.
     */
    MINIMUM_AMOUNT("MINIMALTOAMT", "Minimum Amount"),
    /**
     * Cash Account Placeholder.
     */
    CASH_ACCOUNT_PH("PH_CASHAC", "Cash Account Placeholder"),
    /**
     * Clearing Sundry Account Placeholder.
     */
    CLEARING_ACCOUNT_PH("PH_CLEARINGAC", "Clearing Sundry Account Placeholder"),
    /**
     * RBI Relief Account Placeholder.
     */
    RBI_RELIEF_ACCOUNT_PH("PH_HOAC", "RBI Relief Account Placeholder"),
    /**
     * DD Acount Placeholder.
     */
    DD_ACCOUNT_PH("PH_DDAC", "DD Acount Placeholder"),
    /**
     * Pay Order Account Placeholder.
     */
    PO_ACCOUNT_PH("PH_POAC", "Pay Order Account Placeholder"),
    /**
     * ECS Account Placeholder.
     */
    ECS_ACCOUNT_PH("PH_ECSAC", "ECS Account Placeholder"),
    /**
     * PH_TDS","TDS Account Placeholder.
     */
    PLACEHOLDER_TDS_ACCOUNT("PH_TDS", "TDS Account Placeholder"),
    /**
     * Other Branch Credit A/c.
     */
    OTHER_BRANCH_CR_AC_PH("PH_OTHERBRANCHCRAC", "Other Branch Credit A/c"),
    /**
     * Mail Transfer A/C Place Holder.
     */
    MAIL_TRANSFER_AC_PH("PH_MTAC", "Mail Transfer A/C Place Holder"),
    /**
     * Local RBI Account Place Holder.
     */
    LOCAL_RBI_AC_PH("PH_LOCALRBIAC", "Local RBI Account Place Holder"),
    /**
     * Bankers Chq Account.
     */
    BANKERS_CHQ_AC_PH("PH_BANKERS_CHQ_AC", "Bankers Chq Account"),
    /**
     * Focal Point Consolidation Account Place Holder.
     */
    FOCAL_POINT_CONSOLIDATE_AC_PH("PH_FPLC", "Focal Point Consolidation Account Place Holder"),
    /**
     * EOD Focal Point before TB.
     */
    EOD_FOCAL_POINT_TB("EODFPB4TB", "EOD Focal Point before TB"),
    /**
     * RBI/PSB Account Place Holder.
     */
    RBI_PSB_AC_PH("PH_LCRBIMIRRORAC", "RBI/PSB Account Place Holder"),
    /**
     * Sundry Account (Branch) Place Holder.
     */
    SUNDRY_ACC_PH("PH_SUNDRYAC", "Sundry Account (Branch) Place Holder"),
    /**
     * Sundry Account (Branch) Place Holder.
     */
    HOACLOCAL_ACC_PH("PH_HOACLOCAL", "Local Account Place Holder"),
    /**
     * Sundry Account (Branch) Place Holder.
     */
    INCOME_ACC_PH("PH_INCOMEAC", "Income Place Holder");

    /**
     * Find by key.
     *
     * @param key the key
     * @return the nps scheme parameter keys 1
     */
    public static GbmCommonSchemeParameterKeys findByKey(final String key) {
        for (final GbmCommonSchemeParameterKeys gbmCommonSchemeParameterKeys : values()) {
            if (gbmCommonSchemeParameterKeys.getKey().equals(key)) {
                return gbmCommonSchemeParameterKeys;
            }
        }
        return null;
    }

    /**
     * The key.
     */
    private String key;

    /**
     * The description.
     */
    private String description;

    /**
     * Instantiates a new gbs common scheme parameter keys 1.
     *
     * @param key         the key
     * @param description the description
     */
    private GbmCommonSchemeParameterKeys(final String key, final String description) {
        this.key = key;
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
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return this.key;
    }
}
