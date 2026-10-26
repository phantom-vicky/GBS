/**
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 */
package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.dto.NpsSystemParameterDetailsDto;
import com.isl.gbm.common.dto.NpsSystemParameterDto;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;
import com.isl.gbm.common.manager.NpsSystemParameterManager;
import com.isl.gbm.common.service.NpsSystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class NpsSystemParameterServiceImpl.
 */
@Service
@Transactional
public class NpsSystemParameterServiceImpl implements NpsSystemParameterService {

    /**
     * The manager.
     */
    @Autowired
    private NpsSystemParameterManager manager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.nps.service.NpsSystemParameterService#getSysParamDetails(java.
     * lang.String)
     */
    @Override
    public NpsSystemParameterDto getSysParamDetails(final String parameterId) {
        return this.manager.getSysParamDetails(parameterId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.nps.service.NpsSystemParameterService#getSysParmDetailsList()
     */
    @Override
    public List<NpsSystemParameterDetailsDto> getSysParamDetailsList() {
        return this.manager.getSysParamDetailsList();
    }

    @Override
    public List<NpsSystemParameterDetailsDto> getCommonDetailsList() {
        return this.manager.getCommonDetailsList();
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.service.NpsSystemParameterService#getSysParmList()
     */
    @Override
    public List<NpsSystemParameterAuditLogWrapper> getSysParamList() {
        return this.manager.getSysParamList();
    }


    @Override
    public List<NpsSystemParameterAuditLogWrapper> getCommonList() {
        return this.manager.getCommonList();
    }


    @Override
    public List<NpsSystemParameterAuditLogWrapper> getSysParamModuleList(String moduleId) {
        return this.manager.getSysParamModuleList(moduleId);
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final NpsSystemParameterManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.nps.service.NpsSystemParameterService#updateSysParam(java.
     * util.List)
     */
    @Override
    public void updateSysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        this.manager.updateSysParam(wrapperList);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.nps.service.NpsSystemParameterService#verifySysParam(java.util.
     * List)
     */
    @Override
    public void verifySysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        this.manager.verifySysParam(wrapperList);
    }

}
