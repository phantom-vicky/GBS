/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

/**
 * The Class NpsGbmPlaceholderDto.
 *
 * @author Mahesh.R
 */
public class NpsGbmPlaceholderDto {

    /**
     * The bank account id.
     */
    private String bankAccountId;

    /**
     * The bank account id description.
     */
    private String bankAccountIdDescription;

    /**
     * Gets the bank account id.
     *
     * @return the bankAccountId
     */
    public String getBankAccountId() {
        return this.bankAccountId;
    }

    /**
     * Gets the bank account id description.
     *
     * @return the bankAccountIdDescription
     */
    public String getBankAccountIdDescription() {
        return this.bankAccountIdDescription;
    }

    /**
     * Sets the bank account id.
     *
     * @param bankAccountId the bankAccountId to set
     */
    public void setBankAccountId(final String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    /**
     * Sets the bank account id description.
     *
     * @param bankAccountIdDescription the bankAccountIdDescription to set
     */
    public void setBankAccountIdDescription(final String bankAccountIdDescription) {
        this.bankAccountIdDescription = bankAccountIdDescription;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "NpsGbmPlaceholderDto [bankAccountId=" + this.bankAccountId + ", bankAccountIdDescription="
                + this.bankAccountIdDescription + "]";
    }

}
