/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.exception;

/**
 * The Class AuditLogException.
 */
public class AuditLogException extends RuntimeException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new audit log exception.
     *
     * @param message the message
     */
    public AuditLogException(final String message) {
        super(message);
    }

    /**
     * Instantiates a new audit log exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public AuditLogException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
