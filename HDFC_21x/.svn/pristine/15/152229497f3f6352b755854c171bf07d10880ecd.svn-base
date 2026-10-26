package com.isl.gbm.common;

public enum SchemeCodes {

    /**
     * The nps.
     */
    NPS("0200", "New Pension Scheme"),
    /**
     * The tax.
     */
    TAX("004", "Tax Collection"),
    /**
     * The bond.
     */
    BOND("002", "Relief Bond"),
    /**
     * The ppf.
     */
    PPF("001", "Public Provident Fund"),
    /**
     * The pension.
     */
    PENSION("003", "Pension payment"),
    /**
     * The scss.
     */
    SCSS("051", "Senior Citizen Saving Scheme"),
    /**
     * The common.
     */
    COMMON("0000", "Senior Citizen Saving Scheme"),
    /**
     * The cbdt.
     */
    CBDT("0004", "Central Board of Direct Taxation Saving Scheme"),
    /**
     * The cbec.
     */
    CBEC("0005", "Central Board of Excise and Customs Scheme"),
    /**
     * The rdcess.
     */
    RDCESS("0272", "RDCESS Scheme"),
    /**
     * The doca.
     */
    DOCA("0007", "DOCA Scheme"),
    /**
     * The salestax.
     */
    SALESTAX("0006", "SALESTAX Scheme");

    /**
     * Find by key.
     *
     * @param key the key
     * @return the scheme code values
     */
    public static SchemeCodes findByKey(final String key) {
        for (final SchemeCodes schemeCodes : values()) {
            if (schemeCodes.getSchemeCode().equals(key)) {
                return schemeCodes;
            }
        }
        return null;
    }

    /**
     * The scheme code.
     */
    private String schemeCode;

    /**
     * The scheme name.
     */
    private String schemeName;

    /**
     * Instantiates a new scheme codes.
     *
     * @param schemeCode the scheme code
     * @param schemeName the scheme name
     */
    private SchemeCodes(final String schemeCode, final String schemeName) {
        this.schemeCode = schemeCode;
        this.schemeName = schemeName;
    }

    /**
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * Gets the scheme name.
     *
     * @return the scheme name
     */
    public String getSchemeName() {
        return this.schemeName;
    }


}
