/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.common;

/**
 * The Enum GeneralSetupParameterValues.
 *
 * @author harishbabu.t
 */
public enum GeneralSetupParameterValues {

    /**
     * The master creation.
     */
    MASTER_CREATION("1", "MASTER CREATION NEEDED"),
    /**
     * The bulk uploaded.
     */
    BULK_UPLOADED("2", "BULK UPLOAD FACILITY NEEDED"),
    /**
     * The cbs webservice calls.
     */
    CBS_WEBSERVICE_CALLS("3", "THROUGH CBS WEBSERVICE CALLS");

    /**
     * Find by key.
     *
     * @param key the key
     * @return the general setup parameter values
     */
    public static GeneralSetupParameterValues findByKey(final String key) {
        for (final GeneralSetupParameterValues generalSetupParameterValue : values()) {
            if (generalSetupParameterValue.getKey().equals(key)) {
                return generalSetupParameterValue;
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
    private String value;

    /**
     * Instantiates a new general setup parameter values.
     *
     * @param key   the key
     * @param value the value
     */
    private GeneralSetupParameterValues(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

}
