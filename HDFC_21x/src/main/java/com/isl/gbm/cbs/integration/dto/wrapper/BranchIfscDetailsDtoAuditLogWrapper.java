/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto.wrapper;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;

/**
 * The Class BranchIfscDetailsDtoAuditLogWrapper.
 */
public class BranchIfscDetailsDtoAuditLogWrapper {

    /**
     * The dto.
     */
    private BranchIfscDetailsDto dto;

    /**
     * The audit log dto.
     */
    private AuditLogDto auditLogDto;

    /**
     * Gets the audit log dto.
     *
     * @return the audit log dto
     */
    public AuditLogDto getAuditLogDto() {
        return this.auditLogDto;
    }

    /**
     * Gets the dto.
     *
     * @return the dto
     */
    public BranchIfscDetailsDto getDto() {
        return this.dto;
    }

    /**
     * Sets the audit log dto.
     *
     * @param auditLogDto the new audit log dto
     */
    public void setAuditLogDto(final AuditLogDto auditLogDto) {
        this.auditLogDto = auditLogDto;
    }

    /**
     * Sets the dto.
     *
     * @param dto the new dto
     */
    public void setDto(final BranchIfscDetailsDto dto) {
        this.dto = dto;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BranchIfscDetailsDtoAuditLogWrapper [dto=" + this.dto + ", auditLogDto=" + this.auditLogDto + "]";
    }

}
