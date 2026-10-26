package com.isl.gbm.common.dto.wrapper;

import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.dto.GbmSchemeParameterMasterDto;

public class GbmSchemeParameterAuditLogWrapper {

    private GbmSchemeParameterMasterDto dto;

    private AuditLogDto auditLogDto;

    /**
     * @return the auditLogDto
     */
    public AuditLogDto getAuditLogDto() {
        return this.auditLogDto;
    }

    /**
     * @return the dto
     */
    public GbmSchemeParameterMasterDto getDto() {
        return this.dto;
    }

    /**
     * @param auditLogDto the auditLogDto to set
     */
    public void setAuditLogDto(final AuditLogDto auditLogDto) {
        this.auditLogDto = auditLogDto;
    }

    /**
     * @param dto the dto to set
     */
    public void setDto(final GbmSchemeParameterMasterDto dto) {
        this.dto = dto;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GbmSchemeParameterAuditLogWrapper [dto=" + this.dto + ", auditLogDto=" + this.auditLogDto + "]";
    }

}
