/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.model;

import java.io.Serializable;

/**
 * The Class TableDefinitionDetails.
 */
public class TableDefinitionDetails implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The table name.
     */
    private String tableName;

    /**
     * The table property name.
     */
    private String tablePropertyName;

    /**
     * Gets the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Gets the table property name.
     *
     * @return the table property name
     */
    public String getTablePropertyName() {
        return this.tablePropertyName;
    }

    /**
     * Sets the table name.
     *
     * @param tableName the new table name
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * Sets the table property name.
     *
     * @param tablePropertyName the new table property name
     */
    public void setTablePropertyName(final String tablePropertyName) {
        this.tablePropertyName = tablePropertyName;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TableDefinitionDetails [tableName=" + this.tableName + ", tablePropertyName=" + this.tablePropertyName
                + "]";
    }

}
