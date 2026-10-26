package com.isl.gbm.common.dto;

import jakarta.validation.constraints.NotBlank;

public class ColumnValueDTO {

    @NotBlank(message = "Column name should not be empty")
    private String colName;

    private String value;


    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ColumnValueDTO{" + "colName='" + colName + '\'' + ", value='" + value + '\'' + '}';
    }
}
