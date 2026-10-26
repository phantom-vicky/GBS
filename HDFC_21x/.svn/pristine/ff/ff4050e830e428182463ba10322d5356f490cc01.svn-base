/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.common;

/**
 * The Enum InstrumentTypes.
 */
public enum InstrumentTypes {

    /**
     * The wd.
     */
    WD("WD", "Withdrawal Slips"),

    /**
     * The sp.
     */
    SP("SP", "SP"),

    /**
     * The chq.
     */
    CHQ("CHQ", "Cheques"),

    /**
     * The dds.
     */
    DDS("DDS", "Demand Draft"),

    /**
     * The sbchq.
     */
    SBCHQ("SBCHQ", "SB Cheques"),

    /**
     * The wf.
     */
    WF("WF", "Withdrawal Forms");

    /**
     * Find by instrument type.
     *
     * @param instrumentType the instrument type
     * @return the instrument types
     */
    public static InstrumentTypes findByInstrumentType(final String instrumentType) {
        for (final InstrumentTypes instrumentTypes : values()) {
            if (instrumentTypes.getInstrumentType().equals(instrumentType)) {
                return instrumentTypes;
            }
        }
        return null;
    }

    /**
     * The instrument type.
     */
    private String instrumentType;

    /**
     * The description.
     */
    private String description;

    /**
     * Instantiates a new instrument types.
     *
     * @param instrumentType the instrument type
     * @param description    the description
     */
    private InstrumentTypes(final String instrumentType, final String description) {
        this.instrumentType = instrumentType;
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
     * Gets the instrument type.
     *
     * @return the instrument type
     */
    public String getInstrumentType() {
        return this.instrumentType;
    }
}
