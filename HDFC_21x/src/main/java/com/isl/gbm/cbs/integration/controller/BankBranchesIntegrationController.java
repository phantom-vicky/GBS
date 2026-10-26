/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.BankBranchesIntegrationDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.service.BankBranchesIntegrationService;
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
public class BankBranchesIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(BankBranchesIntegrationController.class);

    @Autowired
    private BankBranchesIntegrationService bankBranchesIntegrationService;


    @PostMapping(value = "/bankbranchdetails")
    public ResponseEntity<List<BankBranchesIntegrationDto>> getBankBranchesDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<BankBranchesIntegrationDto> dtoList = bankBranchesIntegrationService.getBankBranchesDetails(cbsCommonDTo.getBankCode(),
                    cbsCommonDTo.getBranchCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

}
