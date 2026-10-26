/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.model.converter;

import com.isl.gbm.user.management.constants.FeatureNames;
import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * The Class FeatureDisplayNameConverter.
 */
@Converter
public class FeatureDisplayNameConverter implements AttributeConverter<FeatureNames, String> {

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.
     * Object)
     */
    @Override
    public String convertToDatabaseColumn(final FeatureNames featureName) {
        if (null == featureName) {
            return StringUtils.EMPTY;
        }
        return featureName.getFeatureDisplayName();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.
     * Object)
     */
    @Override
    public FeatureNames convertToEntityAttribute(final String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        return FeatureNames.findByFeatureDisplayName(dbData);
    }
}
