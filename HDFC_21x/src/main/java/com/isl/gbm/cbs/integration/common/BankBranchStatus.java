/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.common;

/**
 * The Enum BankBranchStatus.
 */
public enum BankBranchStatus {

    /**
     * The active.
     */
    ACTIVE(1, "Active"),
    /**
     * The merged.
     */
    MERGED(2, "Merged"),
    /**
     * The closed.
     */
    CLOSED(3, "Closed");

    /**
     * Find by status.
     *
     * @param status the status
     * @return the bank branch status
     */
    public static BankBranchStatus findByStatus(final int status) {
        for (final BankBranchStatus bankBranchStatus : values()) {
            if (bankBranchStatus.getStatus() == status) {
                return bankBranchStatus;
            }
        }
        return null;
    }

    /**
     * The status.
     */
    private int status;

    /**
     * The description.
     */
    private String description;

    /**
     * Instantiates a new bank branch status.
     *
     * @param status      the status
     * @param description the description
     */
    private BankBranchStatus(final int status, final String description) {
        this.status = status;
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
     * Gets the status.
     *
     * @return the status
     */
    public int getStatus() {
        return this.status;
    }
}
