/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.AccountExistsAmountDTO;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.NpsGbmGamViewDto;
import com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * The Class AccountDetailsIntegrationController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class AccountDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(AccountDetailsIntegrationController.class);

    /**
     * The service.
     */
    @Autowired
    private AccountDetailsIntegrationService service;

    /**
     * The nps rejection service.
     */


    /**
     * Gets the acc dtls.
     *
     * @param npsGbmGamViewDto the nps gbm gam view dto
     * @return the acc dtls
     */
    @RequestMapping(value = "/accountDtls", method = RequestMethod.POST)
    public ResponseEntity<List<NpsGbmGamViewDto>> getAccDtls(@RequestBody final NpsGbmGamViewDto npsGbmGamViewDto) {

            final List<NpsGbmGamViewDto> dtoList = this.service.getAccDtls(npsGbmGamViewDto);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }


    @PostMapping(value = "/accountNumberDetails")
    public ResponseEntity<List<NpsGbmGamViewDto>> getAccountDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<NpsGbmGamViewDto> dtoList = this.service.getFinacleAccountDetails(cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }


    @PostMapping(value = "/accountNumberExists")
    public ResponseEntity<List<NpsGbmGamViewDto>> getAccountExists(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<NpsGbmGamViewDto> dtoList = this.service.getAccountDetails(cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    /**
     * Gets the account details.
     *
     * @param accountNumber the account number
     * @param solId         the sol id
     * @return the account details
     */
    @GetMapping(value = "/accountdetails")
    public ResponseEntity<List<NpsGbmGamViewDto>> getAccountDetails(
            @RequestParam(name = "accountNumber", required = true) final String accountNumber,
            @RequestParam(name = "solId", required = false) final String solId) {

            final List<NpsGbmGamViewDto> dtoList = this.service.getAccountDetails(accountNumber, solId);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    /**
     * Gets the acid.
     *
     * @param forAcid the for acid
     * @return the acid
     */
    @GetMapping(value = "/acid")
    public ResponseEntity<String> getAcid(@RequestParam(name = "forAcid", required = true) final String forAcid) {

            return new ResponseEntity<>(this.service.getAcid(forAcid), HttpStatus.OK);

    }


	/*@GetMapping(value = "/bacidaccountdetails")
	public ResponseEntity<List<NpsGbmGamViewDto>> getActDetails(
			@RequestParam(name = "paramName", required = true) final String paramName,
			@RequestParam(name = "solId", required = false) final String solId) {

		final String bacid = this.npsRejectionService.getAccountNumber(solId, paramName,
				CommonConstants.NPS_SCHEME_CODE);
		final List<NpsGbmGamViewDto> dtoList = this.service.getActDetails(bacid, solId);
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}*/

    @PostMapping(value = "/bacidaccountdetails")
    public ResponseEntity<List<NpsGbmGamViewDto>> getActDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<NpsGbmGamViewDto> dtoList = this.service.getActDetails(cbsCommonDTo.getBacid(), cbsCommonDTo.getSolId());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    @GetMapping(value = "/corporateaccountdetails")
    public ResponseEntity<List<NpsGbmGamViewDto>> getCorporateAccountDetails(
            @RequestParam(name = "corporateId", required = true) final String corporateId,
            @RequestParam(name = "solId", required = false) final String solId) {

//            final String accountNumber = this.npsRejectionService.getCorporateCreditNumber(corporateId);
            final String accountNumber = corporateId;
            final List<NpsGbmGamViewDto> dtoList = this.service.getAccountDetails(accountNumber, solId);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }


    @GetMapping(value = "/finacleaccountdtls")
    public ResponseEntity<List<NpsGbmGamViewDto>> getFinacleAccountDetails() {

            return new ResponseEntity(this.service.getFinacleAccountDetails(), HttpStatus.OK);

    }

    @GetMapping(value = "/sieffectivebalance")
    public ResponseEntity<Double> getSiEffectiveBalance(
            @RequestParam(name = "accountNumber", required = true) final String accountNumber) {

            return new ResponseEntity<>(this.service.getSiEffectiveBalance(accountNumber), HttpStatus.OK);

    }

    @GetMapping(value = "/effectivebalance")
    public ResponseEntity<CustomerDetailsValidatorDTO> getEffectiveBalance(
            @RequestParam(name = "accountNumber", required = true) final String accountNumber) {

            return new ResponseEntity(this.service.getEffectiveBalance(accountNumber), HttpStatus.OK);

    }

    @PostMapping(value = "/accountNumberExistsSolid")
    public ResponseEntity<List<NpsGbmGamViewDto>> accountNumberExistsSolid(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<NpsGbmGamViewDto> dtoList = this.service.accountNumberExistsSolid(cbsCommonDTo.getAccountNumber(), cbsCommonDTo.getSolId());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
    @PostMapping(value = "/accountNumberWithAmount")
    public ResponseEntity<List<AccountExistsAmountDTO>> getAccountNumberWithAmount(@RequestBody AccountExistsAmountDTO accountExistsAmountDTO) {

        final List<AccountExistsAmountDTO> dtoList = this.service.getAccountNumberWithAmount(accountExistsAmountDTO.getAcctNum(),accountExistsAmountDTO.getAmount());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
    @PostMapping(value = "/getCustomAcctInqApi")
    public ResponseEntity<List<AccountExistsAmountDTO>> getCustomAcctInqApi(@RequestBody AccountExistsAmountDTO accountExistsAmountDTO) {

        final List<AccountExistsAmountDTO> dtoList = this.service.getCustomAcctInqApi(accountExistsAmountDTO.getAcctNum());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
}
