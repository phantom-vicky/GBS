/*
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.NpsGbmPlaceholderDto;
import com.isl.gbm.cbs.integration.service.AccountPlaceHolderIntegrationService;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class AccountPlaceHolderIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(AccountPlaceHolderIntegrationController.class);

    private static final String ERROR_MESSAGE = "account number should have atleast 3 characters";

    @Autowired
    private AccountPlaceHolderIntegrationService accountPlaceHolderIntegrationService;


    @RequestMapping(value = "/clearingaccount", method = RequestMethod.POST)
    @Validated
    public ResponseEntity<List<NpsGbmPlaceholderDto>> getClearingAccountDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<NpsGbmPlaceholderDto> dataDto = this.accountPlaceHolderIntegrationService
                    .getClearingAccountDetails(cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    @RequestMapping(value = "/validateBacid", method = RequestMethod.POST)
    public ResponseEntity<ValidatorWrapperDTO> validateBacid(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final ValidatorWrapperDTO dataDto = this.accountPlaceHolderIntegrationService
                    .validateBacid(cbsCommonDTo.getBacid());
            return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

}
