/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.model.converter;

import com.isl.gbm.common.audit.log.model.ColumnDetails;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class ModifiedFieldsDataConverter.
 */
@Converter
public class ModifiedFieldsDataConverter implements AttributeConverter<List<ColumnDetails>, String> {

    /**
     * The Constant VALUE_DELIMITER.
     */
    private static final String VALUE_DELIMITER = "^";

    /**
     * The Constant FIELD_DELIMITER.
     */
    private static final String FIELD_DELIMITER = "||";

    /**
     * The Constant VALUE_DELIMITER_ESCAPED.
     */
    private static final String VALUE_DELIMITER_ESCAPED = "\\^";

    /**
     * The Constant FIELD_DELIMITER_ESCAPED.
     */
    private static final String FIELD_DELIMITER_ESCAPED = "\\|\\|";

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
     * Object)
     */
    @Override
    public String convertToDatabaseColumn(final List<ColumnDetails> columnDetailsList) {
        final StringBuilder builder = new StringBuilder();
        for (final ColumnDetails details : columnDetailsList) {
            if (StringUtils.isNotBlank(builder.toString())) {
                builder.append(FIELD_DELIMITER);
            }
            builder.append(details.getColumnName()).append(VALUE_DELIMITER).append(details.getPropertyName())
                    .append(VALUE_DELIMITER).append(details.getColumnDisplayName()).append(VALUE_DELIMITER)
                    .append(details.getOldValue()).append(VALUE_DELIMITER).append(details.getNewValue())
                    .append(VALUE_DELIMITER).append(details.getDataType());
        }
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     * Object)
     */
    @Override
    public List<ColumnDetails> convertToEntityAttribute(final String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return Collections.EMPTY_LIST;
        }
        final List<ColumnDetails> detailsList = new ArrayList<>();
        final String[] columns = dbData.split(FIELD_DELIMITER_ESCAPED);
        for (final String s : columns) {
            final String[] columnData = s.split(VALUE_DELIMITER_ESCAPED);
            if ((null == columnData) || (columnData.length != 6)) {
                continue;
            }
            final ColumnDetails details = new ColumnDetails();
            details.setColumnName(columnData[0]);
            details.setPropertyName(columnData[1]);
            details.setColumnDisplayName(columnData[2]);
            details.setDataType(columnData[5]);
            details.setOldValue(this.getValue(columnData[3], details.getDataType()));
            details.setNewValue(this.getValue(columnData[4], details.getDataType()));
            detailsList.add(details);
        }
        return detailsList;
    }

    /**
     * Gets the value.
     *
     * @param str the str
     * @return the value
     */
    private Object getValue(final String str, final String dataType) {
        Object obj;
        if (BooleanUtils.isFalse(StringUtils.equals(dataType, String.class.getName())) && NumberUtils.isNumber(str)) {
            try {
                switch (dataType) {
                    case "java.lang.Double":
                        obj = NumberUtils.createDouble(str);
                        break;
                    case "java.util.Date":
                        obj = NumberUtils.createLong(str);
                        break;
                    default:
                        obj = NumberUtils.createInteger(str);
                        break;
                }
            } catch (final NumberFormatException nfe) {
                obj = str;
            }
        } else {
            obj = str;
        }
        return obj;
    }

}
