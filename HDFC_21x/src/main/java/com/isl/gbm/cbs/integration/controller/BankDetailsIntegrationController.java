/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.BankDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.service.BankDetailsIntegrationService;
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
 * @author Vasanthi
 *
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class BankDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(BankDetailsIntegrationController.class);

    @Autowired
    private BankDetailsIntegrationService bankDetailsIntegrationService;


    @PostMapping(value = "/bankdetails")
    public ResponseEntity<List<BankDetailsIntegrationDto>> getBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<BankDetailsIntegrationDto> dtoList = bankDetailsIntegrationService.getBankDetails(cbsCommonDTo.getBankCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

}
