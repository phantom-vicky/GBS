/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.util;

;

/**
 * The Enum TransactionStatus.
 */
public enum TransactionStatus {

    /**
     * The all.
     */
    ALL("ALL", "%"),
    /**
     * The entered.
     */
    ENTERED("ENTERED", "N"),
    /**
     * The verified.
     */
    VERIFIED("VERIFIED", "Y"),
    /**
     * The finacle.
     */
    FINACLE("FINACLE", "F"),

    DELETED("DELETED", "D"),

    POSTED("POSTED", "P");

    /**
     * The tran status name.
     */
    private String tranStatusName;

    /**
     * The tran status key.
     */
    private String tranStatusKey;

    /**
     * Instantiates a new transaction status.
     *
     * @param tranStatusName the tran status name
     * @param tranStatusKey  the tran status key
     */
    private TransactionStatus(final String tranStatusName, final String tranStatusKey) {
        this.tranStatusName = tranStatusName;
        this.tranStatusKey = tranStatusKey;
    }

    /**
     * Gets the tran status key.
     *
     * @return the tran status key
     */
    public String getTranStatusKey() {
        return this.tranStatusKey;
    }

    /**
     * Gets the tran status name.
     *
     * @return the tran status name
     */
    public String getTranStatusName() {
        return this.tranStatusName;
    }

}
