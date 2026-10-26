package com.isl.gbm.common;

public enum GbmCBDTSchemeParameterKeys {

    /**
     * The residual year start date.
     */
    RESIDUAL_YR_START("RESYRSTDATE", "Residual year start period"),
    /**
     * The residual year end date.
     */
    RESIDUAL_YR_END("RESYRENDDATE", "Residual year end period"),
    /**
     * CBDT collection a/c placeholder.
     */
    CBDT_COLLECTION_PH("PH_CBDT", "CBDT collection A/C placeholder"),
    /**
     * CBDT focal point consolidation a/c placeholder.
     */
    CBDT_FOCAL_POINT_CONSOL_PH("PH_CBDTFPCONSOL", "CBDT focal point consolidation A/C placeholder"),
    /**
     * CBDT link cell consolidation a/c placeholder.
     */
    CBDT_LINK_CELL_CONSOL_PH("PH_CBDTLCCONSOL", "CBDT link cell consolidation A/C placeholder"),
    /**
     * CBDT income a/c placeholder.
     */
    CBDT_INCOME_AC_PH("PH_CBDTINCOME", "CBDT income A/C placeholder"),
    /**
     * CBDT internet parking a/c placeholder.
     */
    CBDT_INTERNET_PARK_AC_PH("PH_CBDTINET", "CBDT internet parking A/C placeholder"),
    /**
     * Is mcp allowed if consolidation pending for Branches .
     */
    IS_MCP_ALLOWED_CONSOL_PENDING("ISMCPALLOWED", "Is MCP allowed if consolidation pending for Branches"),
    /**
     * Is Funds transfer to LC instead of FP permitted during MCP.
     */
    IS_FUND_TRANSFER_PERMITED_TO_LC("ISINTERSOLMCP", "Is Funds transfer to LC instead of FP permitted during MCP"),
    /**
     * Is crown data file received-nodal branch.
     */
    IS_CROWN_DATA_RECEIVED_NB("ISNBCCROWN", "Is crown data file received-nodal branch"),
    /**
     * Is crown data file received-link cell.
     */
    IS_CROWN_DATA_RECEIVED_LC("ISLCCROWN", "Is crown data file received-link cell"),
    /**
     * Is retention allowed.
     */
    IS_RETENTION_ALLOWED("ISRETENTION", "Is retention allowed"),
    /**
     * Error Challan Transaction Place Holder.
     */
    ERR_CHALLAN_TRAN_PH("PH_ERRORAC", "Error Challan Transaction Place Holder"),
    /**
     * Error Challan Collection Place Holder.
     */
    ERR_CHALLAN_COLL_PH("PH_ERRORCOLLAC", "Error Challan Collection Place Holder"),
    /**
     * Error Challan Nodal Branch Collection Place Holder.
     */
    ERR_CHALLAN_NB_COLL_PH("PH_ERRNBC", "Error Challan Nodal Branch Collection Place Holder"),
    /**
     * Error Challan Link Cell Collection Place Holder.
     */
    ERR_CHALLAN_LINK_COLL_PH("PH_ERRLCC", "Error Challan Link Cell Collection Place Holder"),
    /**
     * Error Challan Place Holder for Drop Challan.
     */
    ERR_CHALLAN_DROP_PH("PH_ERRSUN", "Error Challan Place Holder for Drop Challan"),
    /**
     * Receipt Running Serial No.
     */
    RECEIPT_SRL_NO("CBDTRCPTSTSRLNO", "Receipt Running Serial No"),
    /**
     * Inter-Sol Accounting Place Holder.
     */
    INTER_SOL_AC("PH_INTERSOLAC", "Inter-Sol Accounting Place Holder"),
    /**
     * Can Focal Point & Linkcell Consolidation be allowed for Non CBS Branches?
     */
    IS_NON_CBS_CONSOLIDATION("ISNONCBSCONSOL", "Can Focal Point & Linkcell Consolidation be allowed  for Non CBS Branches?"),
    /**
     * Can Gaps in scroll No be allowed, during consolidation, for CBS Branches?
     */
    IS_GAPS_IN_SCROLL_ALLOWED("ISSCROLGAPSALLOWED", "Can Gaps in scroll No be allowed, during consolidation, for CBS Branches?"),
    /**
     * CBDT Scheme HO A/C Place Holder.
     */
    SCHEME_HO_ACC_PH("PH_CBDTHOAC", "CBDT Scheme HO A/C Place Holder"),
    /**
     * Suspense Account (Branch) Place Holder.
     */
    SUSPENSE_ACC_PH("PH_SUSPENSEAC", "Suspense Account (Branch) Place Holder"),
    /**
     * Penalty Rate For Late Remittance.
     */
    PENALTY_RATE_LATE_REMITTENCE("CURRENTRATE", "Penalty Rate For Late Remittance"),
    /**
     * Penalty Rate For Late Remittance.
     */
    BANK_RATE("BANKRATE", "Bank Rate"),
    /**
     * Penalty Rate For Late Remittance.
     */
    HOAC_PH("PH_HOAC", "HOAC Placeholder"),
    /**
     * Penalty Rate For Late Remittance.
     */
    HOAC_LOCAL_PH("PH_HOACLOCAL", "HOAC LOCAL");

    /**
     * Find by key.
     *
     * @param key the key
     * @return the nps scheme parameter keys 1
     */
    public static GbmCBDTSchemeParameterKeys findByKey(final String key) {
        for (final GbmCBDTSchemeParameterKeys gbmCBDTSchemeParameterKeys : values()) {
            if (gbmCBDTSchemeParameterKeys.getKey().equals(key)) {
                return gbmCBDTSchemeParameterKeys;
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
     * Instantiates a new nps scheme parameter keys 1.
     *
     * @param key         the key
     * @param description the description
     */
    private GbmCBDTSchemeParameterKeys(final String key, final String description) {
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
