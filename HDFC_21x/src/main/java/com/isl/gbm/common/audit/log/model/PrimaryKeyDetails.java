/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class PrimaryKeyDetails.
 */
public class PrimaryKeyDetails implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The pk column name.
     */
    private String pkColumnName;

    /**
     * The pk property name.
     */
    private String pkPropertyName;

    /**
     * The value.
     */
    private BigDecimal value;

    /**
     * Gets the pk column name.
     *
     * @return the pk column name
     */
    public String getPkColumnName() {
        return this.pkColumnName;
    }

    /**
     * Gets the pk property name.
     *
     * @return the pk property name
     */
    public String getPkPropertyName() {
        return this.pkPropertyName;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     * Sets the pk column name.
     *
     * @param pkColumnName the new pk column name
     */
    public void setPkColumnName(final String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    /**
     * Sets the pk property name.
     *
     * @param pkPropertyName the new pk property name
     */
    public void setPkPropertyName(final String pkPropertyName) {
        this.pkPropertyName = pkPropertyName;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(final BigDecimal value) {
        this.value = value;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PrimaryKeyDetails [pkColumnName=" + this.pkColumnName + ", pkPropertyName=" + this.pkPropertyName
                + ", value=" + this.value + "]";
    }

}
