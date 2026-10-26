/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dao.impl;

import com.isl.gbm.common.audit.log.exception.AuditLogException;
import com.isl.gbm.common.audit.log.model.ColumnDetails;
import com.isl.gbm.common.util.CommonConstants;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Date;

/**
 * The Class AuditLogDaoUtil.
 */
public class AuditLogDaoUtil {

    /**
     * The Constant LOGGER.
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogDaoUtil.class);

    /**
     * The Constant NULL_STRING.
     */
    private static final String NULL_STRING = "null";

    /**
     * Gets the value.
     *
     * @param details the details
     * @return the value
     */
    public static Object getValue(final ColumnDetails details) {
        if ((null != details.getNewValue())
                && BooleanUtils.isFalse(StringUtils.equalsIgnoreCase(NULL_STRING, details.getNewValue().toString()))) {
            try {
                final Object object;
                final Constructor<?> ctor;
                final Class<?> clazz = Class.forName(details.getDataType());
                if (StringUtils.equals(Date.class.getName(), details.getDataType())) {
                    ctor = clazz.getConstructor(long.class);
                    object = ctor.newInstance(Long.valueOf(details.getNewValue().toString()));
                } else if (StringUtils.equals(Character.class.getName(), details.getDataType())) {
                    ctor = clazz.getConstructor(char.class);
                    object = ctor.newInstance(details.getNewValue().toString().charAt(0));
                } else {
                    ctor = clazz.getConstructor(String.class);
                    object = ctor.newInstance(details.getNewValue().toString());
                }
                return object;
            } catch (final ReflectiveOperationException | RuntimeException e) {
                LOGGER.error(CommonConstants.EXCEPTION_MESSAGE, e);
                throw new AuditLogException("Data Parsing Failed");
            }
        }
        return null;
    }

    /**
     * Instantiates a new audit log dao util.
     */
    private AuditLogDaoUtil() {
        // private constructor
    }

}
