/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dto;

/**
 * The Class ColumnDetailsDto.
 */
public class ColumnDetailsDto {

    /**
     * The column name.
     */
    private String columnName;

    /**
     * The property name.
     */
    private String propertyName;

    /**
     * The old value.
     */
    private Object oldValue;

    /**
     * The new value.
     */
    private Object newValue;

    /**
     * The data type.
     */
    private String dataType;

    /**
     * The column display name.
     */
    private String columnDisplayName;

    /**
     * Gets the column display name.
     *
     * @return the column display name
     */
    public String getColumnDisplayName() {
        return this.columnDisplayName;
    }

    /**
     * Gets the column name.
     *
     * @return the column name
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * Gets the data type.
     *
     * @return the data type
     */
    public String getDataType() {
        return this.dataType;
    }

    /**
     * Gets the new value.
     *
     * @return the new value
     */
    public Object getNewValue() {
        return this.newValue;
    }

    /**
     * Gets the old value.
     *
     * @return the old value
     */
    public Object getOldValue() {
        return this.oldValue;
    }

    /**
     * Gets the property name.
     *
     * @return the property name
     */
    public String getPropertyName() {
        return this.propertyName;
    }

    /**
     * Sets the column display name.
     *
     * @param columnDisplayName the new column display name
     */
    public void setColumnDisplayName(final String columnDisplayName) {
        this.columnDisplayName = columnDisplayName;
    }

    /**
     * Sets the column name.
     *
     * @param columnName the new column name
     */
    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    /**
     * Sets the data type.
     *
     * @param dataType the new data type
     */
    public void setDataType(final String dataType) {
        this.dataType = dataType;
    }

    /**
     * Sets the new value.
     *
     * @param newValue the new new value
     */
    public void setNewValue(final Object newValue) {
        this.newValue = newValue;
    }

    /**
     * Sets the old value.
     *
     * @param oldValue the new old value
     */
    public void setOldValue(final Object oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * Sets the property name.
     *
     * @param propertyName the new property name
     */
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ColumnDetailsDto [columnName=" + this.columnName + ", propertyName=" + this.propertyName + ", oldValue="
                + this.oldValue + ", newValue=" + this.newValue + ", dataType=" + this.dataType + ", columnDisplayName="
                + this.columnDisplayName + "]";
    }

}
