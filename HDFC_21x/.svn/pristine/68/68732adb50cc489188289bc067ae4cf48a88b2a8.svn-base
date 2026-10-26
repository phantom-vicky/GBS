/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.service.SolDetailsIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Class SolDetailsIntegrationController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class SolDetailsIntegrationController {
    //gbmultra/cbs/integration/soldetails?solId=
    /**
     * The service.
     */

    private static final Logger log = LoggerFactory.getLogger(SolDetailsIntegrationController.class);
    @Autowired
    private SolDetailsIntegrationService service;


    /**
     * Gets the bank branches details.
     *
     * @param bankCode   the bank code
     * @param branchCode the branch code
     * @param solId      the sol id
     * @return the bank branches details
     */
    @GetMapping("/solbankbranches")
    public ResponseEntity<List<SolDetailsIntegrationDto>> getBankBranchesDetails(
            @RequestParam(name = "bankCode", required = true) final String bankCode,
            @RequestParam(name = "branchCode", required = true) final String branchCode,
            @RequestParam(name = "solId", required = true) final String solId) {

            return new ResponseEntity<>(this.service.getBankBranchesDetails(bankCode, branchCode, solId), HttpStatus.OK);

    }

    @PostMapping(value = "/soldetails")
    public ResponseEntity<List<SolDetailsIntegrationDto>> getSolDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return new ResponseEntity<>(this.service.getSolDetails(cbsCommonDTo.getSolId()), HttpStatus.OK);

    }
}
