/**
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 */
package com.isl.gbm.common.service;

import com.isl.gbm.common.dto.NpsSystemParameterDetailsDto;
import com.isl.gbm.common.dto.NpsSystemParameterDto;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;

import java.util.List;

/**
 * The Interface NpsSystemParameterService.
 */
public interface NpsSystemParameterService {

    /**
     * Gets the sys param details.
     *
     * @param parameterId the parameter id
     * @return the sys param details
     */
    NpsSystemParameterDto getSysParamDetails(String parameterId);

    /**
     * Gets the sys param details list.
     *
     * @return the sys param details list
     */
    List<NpsSystemParameterDetailsDto> getSysParamDetailsList();


    List<NpsSystemParameterDetailsDto> getCommonDetailsList();

    /**
     * Gets the sys param list.
     *
     * @return the sys param list
     */
    List<NpsSystemParameterAuditLogWrapper> getSysParamList();

    List<NpsSystemParameterAuditLogWrapper> getCommonList();


    List<NpsSystemParameterAuditLogWrapper> getSysParamModuleList(String moduleId);

    /**
     * Update sys param.
     *
     * @param wrapperList the wrapper list
     */
    void updateSysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList);

    /**
     * Verify sys param.
     *
     * @param wrapperList the wrapper list
     */
    void verifySysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList);

}
