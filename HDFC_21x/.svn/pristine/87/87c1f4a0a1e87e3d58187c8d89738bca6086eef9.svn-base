package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.scrolls.*;
import com.isl.gbm.rbibond.facade.RbiBondScrollsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("gbmultra/rbibond/scrolls")
public class RbiBondScrollsController {

    @Autowired
    RbiBondScrollsFacade rbiBondScrollsFacade;
    @PostMapping(path = "/getBondPrincipalRepay")
    public ResponseEntity<ResponseDataDTO> getBondPrincipalRepay(@Valid @RequestBody final BondPrincipalRepayDTO bondPrincipalRepayDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBondPrincipalRepay(bondPrincipalRepayDTO));
    }

    @PostMapping(path = "/getBondInterestRepay")
    public ResponseEntity<ResponseDataDTO> getBondInterestRepay(@Valid @RequestBody final BondInterestRepayDTO bondInterestRepayDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBondInterestRepay(bondInterestRepayDTO));
    }

    @PostMapping(path = "/getBondRegisterLedgerAcc")
    public ResponseEntity<ResponseDataDTO> getBondRegisterLedgerAcc(@Valid @RequestBody final BondRegisterLedgerAccDTO bondRegisterLedgerAccDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBondRegisterLedgerAcc(bondRegisterLedgerAccDTO));
    }

    @PostMapping(path = "/getBondRegLedgerLinkCell")
    public ResponseEntity<ResponseDataDTO> getBondRegLedgerLinkCell(@Valid @RequestBody final BondRegisterLedgerLinkDTO bondRegisterLedgerLinkDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBondRegLedgerLinkCell(bondRegisterLedgerLinkDTO));
    }

    @PostMapping(path = "/getBrokerageHandlingReport")
    public ResponseEntity<ResponseDataDTO> getBrokerageHandlingReport(@Valid @RequestBody final BondBrokerageHandleDTO bondBrokerageHandleDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBrokerageHandlingReport(bondBrokerageHandleDTO));
    }

    @PostMapping(path = "/StatementOfBondsDetails")
    public ResponseEntity<ResponseDataDTO> statementOfBondsDetails(@Valid @RequestBody final StatementofBondsIssuedDTO statementofBondsIssuedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.statementOfBondsDetails(statementofBondsIssuedDTO));
    }


    @PostMapping(path = "/StatementOfChargesServicingDetails")
    public ResponseEntity<ResponseDataDTO> statementOfChargesServicingDetails(@Valid @RequestBody final SatatementOfChargesBlaDTO satatementOfChargesBlaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.statementOfChargesServicingDetails(satatementOfChargesBlaDTO));
    }
    @PostMapping(path = "/StatementOfChargesTurnoverCommissionDetails")
    public ResponseEntity<ResponseDataDTO> statementOfChargesTurnoverCommissionDetails(@Valid @RequestBody final RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.statementOfChargesTurnoverCommissionDetails(rbiBondTurnoverCommissionDTO));
    }
    @PostMapping(path = "/PrincipleScrollreportAppendixXII")
    public ResponseEntity<ResponseDataDTO> principleScrollreportAppendixXII(@Valid @RequestBody final PrincipleScrollreportAppendixXIIDto scrollreportAppendixXIIDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.principleScrollreportAppendixXII(scrollreportAppendixXIIDto));
    }

    @PostMapping(path = "/estimateOfExpenses")
    public ResponseEntity<ResponseDataDTO> estimateOfExpenses(@Valid @RequestBody final EstimateOfExpensesDto estimateOfExpensesDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.estimateOfExpenses(estimateOfExpensesDto));
    }

   @PostMapping(path = "/getSubsTextFileDetails")
   public ResponseEntity<ResponseDataDTO> getSubsTextFileDetails(@Valid @RequestBody final SusbscriptionTextFileDto susbscriptionTextFileDto) {
       return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getSubsTextFileDetails(susbscriptionTextFileDto));
   }

   @PostMapping(path = "/getRedemptionTextFileDetails")
   public ResponseEntity<ResponseDataDTO> getRedemptionTextFileDetails(@Valid @RequestBody final RedemptionTextFileDto redemptionTextFileDto) {
       return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getRedemptionTextFileDetails(redemptionTextFileDto));
   }

    @PostMapping(path = "/getMonthBrokerageDetails")
    public ResponseEntity<ResponseDataDTO> getMonthBrokerageDetails(@Valid @RequestBody final MonthlyBrokerageClaimDto monthlyBrokerageClaimDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getMonthBrokerageDetails(monthlyBrokerageClaimDto));
    }
    @PostMapping(path = "/getInterestScrollReport5106Details")
    public ResponseEntity<ResponseDataDTO> getInterestScrollReport5106Details(@Valid @RequestBody final InterestScrollReportDTO interestScrollReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getInterestScrollReport5106Details(interestScrollReportDTO));
    }

    @PostMapping(path = "/getAppendix75Details")
    public ResponseEntity<ResponseDataDTO> getAppendixDetails(@Valid @RequestBody final AppendixFourMarchDTO appendixFourMarchDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAppendixDetails(appendixFourMarchDTO));
    }

    @PostMapping(path = "/getEfillingDetails")
    public ResponseEntity<ResponseDataDTO> getEfillingDetails(@Valid @RequestBody final EfillingDTO efillingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getEfillingDetails(efillingDTO));
    }
    @PostMapping(path = "/getEfillingDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getEfillingDetailsDownload(@Valid @RequestBody final EfillingDTO efillingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getEfillingDetailsDownload(efillingDTO));
    }

    @PostMapping(path = "/getSft")
    public ResponseEntity<ResponseDataDTO> getSft(@Valid @RequestBody final SftScrollDTO sftScrollDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getSft(sftScrollDTO));
    }

    @PostMapping(path = "/getAIRDetails")
    public ResponseEntity<ResponseDataDTO> getAIRDetails(@Valid @RequestBody final AirDTO airDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAIRDetails(airDTO));
    }
    @PostMapping(path = "/getAppendixIV805Details")
    public ResponseEntity<ResponseDataDTO> getAppendixIV805Details(@Valid @RequestBody final AppendixIV805DTO appendixIV805DTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAppendixIV805Details(appendixIV805DTO));
    }
    @PostMapping(path = "/getAppendixIV85Details")
    public ResponseEntity<ResponseDataDTO> getAppendixIV85Details(@Valid @RequestBody final AppendixIV85DTO appendixIV85DTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAppendixIV85Details(appendixIV85DTO));
    }

    @PostMapping(path = "/getAnnualReport")
    public ResponseEntity<ResponseDataDTO> getAnnualReport(@Valid @RequestBody final AnnualReportDto annualReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAnnualReport(annualReportDto));
    }

    @PostMapping(path = "/getAppendixIV8Details")
    public ResponseEntity<ResponseDataDTO> getAppendixIV8Details(@Valid @RequestBody final AppendixIV8DTO appendixIV8DTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAppendixIV8Details(appendixIV8DTO));
    }


    @PostMapping(path = "/getAppendixIV65Details")
    public ResponseEntity<ResponseDataDTO> getAppendix65Details(@Valid @RequestBody final AppendixFourMar65DTO appendixFourMar65DTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getAppendix65Details(appendixFourMar65DTO));
    }

    @PostMapping(path = "/getsubScriptionMandateDetails")
    public ResponseEntity<ResponseDataDTO> getsubScriptionMandateDetails(@Valid @RequestBody final SubscriptionMandateDTO subscriptionMandateDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getsubScriptionMandateDetails(subscriptionMandateDTO));
    }

    @PostMapping(path = "/getBrokerageClaimHandlingCommissionDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerageClaimHandlingCommissionDetails(@Valid @RequestBody final BrokerageClaimAndHandlingCommissionDTO brokerageClaimDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getBrokerageClaimHandlingCommissionDetails(brokerageClaimDTO));
    }
    @PostMapping(path = "/getSchemeWiseOutstandingBalanceDetails")
    public ResponseEntity<ResponseDataDTO> getSchemeWiseOutstandingBalanceDetails( @RequestBody final SchemeWiseOutstandingBalanceDTO schemeWiseOutstandingBalanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondScrollsFacade.getSchemeWiseOutstandingBalanceDetails(schemeWiseOutstandingBalanceDTO));
    }

    @PostMapping(path = "/getHalfYearlyInterestScrollDetails")
    public ResponseEntity<ResponseDataDTO> getHalfYearlyInterestScrollDetails(
            @Valid @RequestBody final HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return APIResponseBuilder.buildResponseFromDto(
                rbiBondScrollsFacade.getHalfYearlyInterestScrollDetails(halfYearlyInterestScrollDTO));
    }

    @PostMapping(path = "/downloadHalfYearlyInterestScrollFile")
    public ResponseEntity<ResponseDataDTO> downloadHalfYearlyInterestScrollFile(
            @Valid @RequestBody final HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return APIResponseBuilder.buildResponseFromDto(
                rbiBondScrollsFacade.downloadHalfYearlyInterestScrollFile(halfYearlyInterestScrollDTO));
    }
}
