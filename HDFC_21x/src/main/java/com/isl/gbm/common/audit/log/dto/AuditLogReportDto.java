/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dto;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;

import java.util.List;

/**
 * The Class AuditLogReportDto.
 */
public class AuditLogReportDto {

    /**
     * The sol details integration dto.
     */
    private SolDetailsIntegrationDto solDetailsIntegrationDto;

    /**
     * The audit log dto list.
     */
    private List<AuditLogDto> auditLogDtoList;

    /**
     * Gets the audit log dto list.
     *
     * @return the audit log dto list
     */
    public List<AuditLogDto> getAuditLogDtoList() {
        return this.auditLogDtoList;
    }

    /**
     * Gets the sol details integration dto.
     *
     * @return the sol details integration dto
     */
    public SolDetailsIntegrationDto getSolDetailsIntegrationDto() {
        return this.solDetailsIntegrationDto;
    }

    /**
     * Sets the audit log dto list.
     *
     * @param auditLogDtoList the new audit log dto list
     */
    public void setAuditLogDtoList(final List<AuditLogDto> auditLogDtoList) {
        this.auditLogDtoList = auditLogDtoList;
    }

    /**
     * Sets the sol details integration dto.
     *
     * @param solDetailsIntegrationDto the new sol details integration dto
     */
    public void setSolDetailsIntegrationDto(final SolDetailsIntegrationDto solDetailsIntegrationDto) {
        this.solDetailsIntegrationDto = solDetailsIntegrationDto;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AuditLogReportDto [solDetailsIntegrationDto=" + this.solDetailsIntegrationDto + ", auditLogDtoList="
                + this.auditLogDtoList + "]";
    }

}
