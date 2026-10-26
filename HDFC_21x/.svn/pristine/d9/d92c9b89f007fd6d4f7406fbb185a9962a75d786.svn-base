/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/

package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.NpsSystemParameterDetailsDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;
import com.isl.gbm.common.facade.NpsSystemParameterFacade;
import com.isl.gbm.common.service.NpsSystemParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Class NpsSystemParameterController.
 */
@RestController
@RequestMapping("/gbmultra/nps/sysparams")
public class NpsSystemParameterController {

    /**
     * The nps system parameter service.
     */
    @Autowired
    private NpsSystemParameterService npsSystemParameterService;

    @Autowired
    private NpsSystemParameterFacade npsSystemParameterFacade;


    /**
     * Gets the sys parm details list.
     *
     * @return the sys parm details list
     */
    @RequestMapping(value = "/getSysParmDetailsList", method = RequestMethod.GET)
    public ResponseEntity<List<NpsSystemParameterDetailsDto>> getSysParmDetailsList() {
        final List<NpsSystemParameterDetailsDto> resultList = this.npsSystemParameterService.getSysParamDetailsList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCommonDetailsList", method = RequestMethod.GET)
    public ResponseEntity<List<NpsSystemParameterDetailsDto>> getCommonDetailsList() {
        final List<NpsSystemParameterDetailsDto> resultList = this.npsSystemParameterService.getCommonDetailsList();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * Gets the sys parm list.
     *
     * @return the sys parm list
     */
    @RequestMapping(value = "/getSysParamList", method = RequestMethod.GET)
    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmList() {
        return this.npsSystemParameterFacade.getSysParmList();
    }

    @RequestMapping(value = "/getCommonList", method = RequestMethod.GET)
    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getCommonList() {
        return this.npsSystemParameterFacade.getCommonList();
    }


    @RequestMapping(value = "/getSysParamModuleList", method = RequestMethod.GET)
    public ResponseEntity<List<NpsSystemParameterAuditLogWrapper>> getSysParmModuleList(@RequestParam String moduleId) {
        return this.npsSystemParameterFacade.getSysParmModuleList(moduleId);
    }


    /**
     * Update sys param.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequestMapping(value = "/updateSysParam", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> updateSysParam(
            @RequestBody final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        return this.npsSystemParameterFacade.updateSysParam(wrapperList);
    }

    /**
     * Verify sys param.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySysParam(
            @RequestBody final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        return this.npsSystemParameterFacade.verifySysParam(wrapperList);
    }

}
