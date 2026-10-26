/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.transaction.channel;

/**
 * The Enum TransactionType.
 */
public enum TransactionType {

    /**
     * The cash.
     */
    CASH("CASH", "C"),
    /**
     * The transfer.
     */
    TRANSFER("TRANSFER", "T"),
    /**
     * The clearing.
     */
    CLEARING("CLEARING", "CL");

    public static TransactionType findByKey(final String key) {
        for (final TransactionType transactionType : values()) {
            if (transactionType.getTranTypeKey().equals(key)) {
                return transactionType;
            }
        }
        return null;
    }

    /**
     * The tran type name.
     */
    private String tranTypeName;

    /**
     * The tran type key.
     */
    private String tranTypeKey;

    /**
     * Instantiates a new transaction type.
     *
     * @param tranTypeName the tran type name
     * @param tranTypeKey  the tran type key
     */
    private TransactionType(final String tranTypeName, final String tranTypeKey) {
        this.tranTypeName = tranTypeName;
        this.tranTypeKey = tranTypeKey;
    }

    /**
     * Gets the tran type key.
     *
     * @return the tran type key
     */
    public String getTranTypeKey() {
        return this.tranTypeKey;
    }

    /**
     * Gets the tran type name.
     *
     * @return the tran type name
     */
    public String getTranTypeName() {
        return this.tranTypeName;
    }

}
