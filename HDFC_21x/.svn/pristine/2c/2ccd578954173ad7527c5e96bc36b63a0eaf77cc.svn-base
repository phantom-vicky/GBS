package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColumnValueConditionDto {

    @NotBlank(message = "Column name should not be empty")
    private String colName;

    @NotBlank(message = "Column value should not be empty")
    private String value;

    @NotBlank(message = "Condition should not be empty")
    private String condition;

    @NotNull(message = "Order should not be empty")
    private Long order;

    private String conjunction;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConjunction() {
        return conjunction;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        if (order != 0)
            this.order = order;

    }
}
