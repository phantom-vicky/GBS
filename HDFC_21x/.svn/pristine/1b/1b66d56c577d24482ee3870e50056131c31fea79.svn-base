/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.GeneralSetupDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GeneralSetupDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.GeneralSetupFacade;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.utils.APIResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The Class GeneralSetupController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class GeneralSetupController {

    private static final Logger log = LoggerFactory.getLogger(GeneralSetupController.class);

    /**
     * The general setup service.
     */
    @Autowired
    private GeneralSetupFacade generalSetupFacade;


    /**
     * Gets the general setup details.
     *
     * @return the general setup details
     */
    @RequestMapping(value = "/generalsetup", method = RequestMethod.POST)
    public ResponseEntity<List<GeneralSetupDtoAuditLogWrapper>> getGeneralSetupDetails() {

            final List<GeneralSetupDtoAuditLogWrapper> wrapperList = this.generalSetupFacade.getGeneralSetupDetails();
            return new ResponseEntity<>(wrapperList, HttpStatus.OK);

    }

    /**
     * Gets the general setup details.
     *
     * @param paramName the param name
     * @return the general setup details
     */
    @RequestMapping(value = "/generalsetup/{paramName}", method = RequestMethod.GET)
    public ResponseEntity<GeneralSetupDto> getGeneralSetupDetails(@PathVariable final String paramName) {

            final GeneralSetupDto result = this.generalSetupFacade.getGeneralSetupDetails(paramName);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequestMapping(value = "/generalsetup/update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@RequestBody final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {

            this.generalSetupFacade.update(wrapperList);
            final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param wrapperList the wrapper list
     * @return the response entity
     */
    @RequestMapping(value = "/generalsetup/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {

            this.generalSetupFacade.verify(wrapperList);
            final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
