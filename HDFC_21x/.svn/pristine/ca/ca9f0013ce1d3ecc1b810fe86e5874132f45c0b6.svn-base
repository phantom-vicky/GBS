package com.isl.gbm.cbs.integration.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.rbibond.dto.ColumnValueConditionDto;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonGenericTableDto implements Serializable {
    @NotBlank
    private String table;

    private Long pkColumnValue;

    private String id;

    @NotBlank
    private String sp;

    @Valid
    List<ColumnValueDTO> columnValues;


    private List<String> columnList;

    @Valid
    private List<ColumnValueConditionDto> conditionList;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public Long getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(Long pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }

    public List<ColumnValueConditionDto> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<ColumnValueConditionDto> conditionList) {
        this.conditionList = conditionList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
        setPrimaryKeyValue();
    }

    private void setPrimaryKeyValue() {
        if (!CollectionUtils.isEmpty(this.columnValues)) {
            Iterator<ColumnValueDTO> columnValueDTOIterator = columnValues.iterator();
            ColumnValueDTO tempColumnValueDTO = null;
            while (columnValueDTOIterator.hasNext()) {
                tempColumnValueDTO = columnValueDTOIterator.next();
                if (((String) tempColumnValueDTO.getColName()).equals("ID")) {
                    pkColumnValue = Long.parseLong(tempColumnValueDTO.getValue());
                    columnValueDTOIterator.remove();
                    break;
                }
            }
        }

    }

    /**
     * Validation Block
     */
    @AssertTrue(message = "Invalid SP")
    private boolean isValidSP() {
        if (PensionCommonConstants.VALID_SP_LIST.contains(sp))
            return true;
        return false;
    }


    @AssertTrue(message = "Primary key column value should not be empty")
    private boolean isValidByPrimaryKeyValue() {
        if (PensionCommonConstants.PRIMARY_KEY_REQUIRED_SP_LIST.contains(sp) && (pkColumnValue == null || pkColumnValue == 0))
            return false;
        return true;
    }

    @AssertTrue(message = "Column values should not be empty")
    private boolean isValidDtoByColumnValues() {
        if (PensionCommonConstants.COLUMN_VALUES_REQUIRED_SP_LIST.contains(sp) && CollectionUtils.isEmpty(columnValues))
            return false;
        return true;
    }

    @AssertTrue(message = "Column List should not be empty")
    private boolean isValidDtoByColumnList() {
        if (PensionCommonConstants.COLUMN_LIST_REQUIRED_SP_LIST.contains(sp) && CollectionUtils.isEmpty(columnList))
            return false;
        return true;
    }

    @AssertTrue(message = "Invalid Conjunctions in Condition")
    public boolean isConjunctionsValid() {
        if (CollectionUtils.isEmpty(conditionList)) {
            return true;
        } else {
            /**
             * Set First Condition's Conjunction as NULL
             */
            conditionList.get(0).setConjunction(null);

            if (conditionList.size() > 1) {
                for (int index = 1; index < conditionList.size(); index++)
                    if (StringUtils.isEmpty(conditionList.get(index).getConjunction()))
                        return false;
                return true;
            } else {
                return true;
            }

        }

    }


}
