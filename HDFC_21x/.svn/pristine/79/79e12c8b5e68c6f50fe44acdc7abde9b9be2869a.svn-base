/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.SubscriberDTO;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.CustomerDetails;
import com.isl.gbm.cbs.integration.dto.CustomerDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * The Class CustomerDetailsIntegrationController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class CustomerDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(CustomerDetailsIntegrationController.class);

    /**
     * The customer details integration service.
     */
    @Autowired
    private CustomerDetailsIntegrationService customerDetailsIntegrationService;




    @PostMapping(value = "/getCustomeraccountdetails")
    public ResponseEntity<List<CustomerDetailsIntegrationDto>> getCustomerAccountDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<CustomerDetailsIntegrationDto> dtoList = this.customerDetailsIntegrationService
                    .getCustomerAccountDetails(cbsCommonDTo.getCustomerId(), cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the customer details.
     *
     * @param customerId    the customer id
     * @param accountNumber the account number
     * @return the customer details
     */
    @GetMapping(value = "/customerdetails")
    public ResponseEntity<CustomerDetailsValidatorDTO> getCustomerDetails(
            @RequestParam(name = "customerId", required = true) final String customerId,
            @RequestParam(name = "accountNumber", required = true) final String accountNumber) {

            final CustomerDetailsValidatorDTO dtoList = this.customerDetailsIntegrationService
                    .getCustomerDetails(customerId, accountNumber);
            if (dtoList.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS)) {
//                ResponseDataDTO responseDataDTO = subscriberRegistrationFacade.getBankDetailsForRegistration(dtoList.getCustomerDetails().getSolId());
                ResponseDataDTO responseDataDTO = null;
                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {

                    SubscriberDTO subscriberDTO = (SubscriberDTO) responseDataDTO.getResult();
                    dtoList.getCustomerDetails().setMicrCode(subscriberDTO.getMicrCode());
                    dtoList.getCustomerDetails().setIfscCode(subscriberDTO.getIfscCode());
                    dtoList.getCustomerDetails().setBankName(subscriberDTO.getBankCode());
                    dtoList.getCustomerDetails().setBranchName(subscriberDTO.getBranchName());
                    //dtoList.getCustomerDetails().setDob();
                }
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the customer account details.
     *
//     * @param customerId    the customer id
//     * @param accountNumber the account number
     * @return the customer account details
     */
    @PostMapping(value = "/customeraccountdetails")
    public ResponseEntity<List<CustomerDetailsIntegrationDto>> getCustomerAccountDetails(
            @RequestBody CustomerDetails customerDetails) {

            final List<CustomerDetailsIntegrationDto> dtoList = this.customerDetailsIntegrationService
                    .getCustomerAccountDetails(customerDetails.getCustomerId(), customerDetails.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    /**
     * Gets the customer details.
     *
//     * @param customerId    the customer id
//     * @param accountNumber the account number
     * @return the customer details
     */
    @PostMapping(value = "/customerdetails")
    public ResponseEntity<CustomerDetailsValidatorDTO> getCustomerDetails(
            @RequestBody CustomerDetails customerDetails) {

            final CustomerDetailsValidatorDTO dtoList = this.customerDetailsIntegrationService
                    .getCustomerDetails(customerDetails.getCustomerId(), customerDetails.getAccountNumber());
            if (dtoList.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS)) {
                ResponseDataDTO responseDataDTO = null;
//                ResponseDataDTO responseDataDTO = subscriberRegistrationFacade.getBankDetailsForRegistration(dtoList.getCustomerDetails().getSolId());
                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {

                    SubscriberDTO subscriberDTO = (SubscriberDTO) responseDataDTO.getResult();
                    dtoList.getCustomerDetails().setMicrCode(subscriberDTO.getMicrCode());
                    dtoList.getCustomerDetails().setIfscCode(subscriberDTO.getIfscCode());
                    dtoList.getCustomerDetails().setBankName(subscriberDTO.getBankCode());
                    dtoList.getCustomerDetails().setBranchName(subscriberDTO.getBranchName());
                    //dtoList.getCustomerDetails().setDob();
                }
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


    @PostMapping(value = "/customerdetail")
    public ResponseEntity<CustomerDetailsValidatorDTO> getCustomerDetail(
            @RequestBody CustomerDetails customerDetails) {

        final CustomerDetailsValidatorDTO dtoList = this.customerDetailsIntegrationService
                .getCustomerDetail(customerDetails.getCustomerId(), customerDetails.getAccountNumber());
        if (dtoList.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS)) {
            ResponseDataDTO responseDataDTO = null;
//            ResponseDataDTO responseDataDTO = subscriberRegistrationFacade.getBankDetailsForRegistration(dtoList.getCustomerDetails().getSolId());
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {

                SubscriberDTO subscriberDTO = (SubscriberDTO) responseDataDTO.getResult();
                dtoList.getCustomerDetails().setMicrCode(subscriberDTO.getMicrCode());
                dtoList.getCustomerDetails().setIfscCode(subscriberDTO.getIfscCode());
                dtoList.getCustomerDetails().setBankName(subscriberDTO.getBankCode());
                dtoList.getCustomerDetails().setBranchName(subscriberDTO.getBranchName());
                //dtoList.getCustomerDetails().setDob();
            }
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }




    @PostMapping(value = "/customerdetails/validate")
    public ResponseEntity<CustomerDetailsValidatorDTO> validateCustomerAccountDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final CustomerDetailsValidatorDTO dtoList = this.customerDetailsIntegrationService
                    .validateCustomerAccountDetails(cbsCommonDTo.getCustomerId(), cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/customerdetails/accountvalidate")
    public ResponseEntity<CustomerDetailsValidatorDTO> validateAccountDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            final CustomerDetailsValidatorDTO dtoList = this.customerDetailsIntegrationService
                    .validateAccountDetails(cbsCommonDTo.getAccountNumber());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

}
