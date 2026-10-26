package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.scrolls.*;
import com.isl.gbm.rbibond.facade.RbiBondScrollsFacade;
import com.isl.gbm.rbibond.service.RbiBondScrollsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondScrollsFacadeImpl implements RbiBondScrollsFacade {


    @Autowired
    private RbiBondScrollsService rbiBondScrollsService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRINCIPAL_REPAYMENT_SCROLL:SCROLL")
    public ResponseDataDTO getBondPrincipalRepay(BondPrincipalRepayDTO bondPrincipalRepayDTO) {
      return rbiBondScrollsService.getBondPrincipalRepay(bondPrincipalRepayDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_REPAYMENT_SCROLL:SCROLL")
    public ResponseDataDTO getBondInterestRepay(BondInterestRepayDTO bondInterestRepayDTO) {
        return rbiBondScrollsService.getBondInterestRepay(bondInterestRepayDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_COLL_BRANCH_SCROLL:SCROLL")
    public ResponseDataDTO getBondRegisterLedgerAcc(BondRegisterLedgerAccDTO bondRegisterLedgerAccDTO) {
        return rbiBondScrollsService.getBondRegisterLedgerAcc(bondRegisterLedgerAccDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REGISTER_LEDGER_ACC_LINK_CELL_SCROLL:SCROLL")
    public ResponseDataDTO getBondRegLedgerLinkCell(BondRegisterLedgerLinkDTO bondRegisterLedgerLinkDTO) {
        return rbiBondScrollsService.getBondRegLedgerLinkCell(bondRegisterLedgerLinkDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_CHARGES_BROKERAGE_HANDLE_COMM_SCROLL:SCROLL")
    public ResponseDataDTO getBrokerageHandlingReport(BondBrokerageHandleDTO bondBrokerageHandleDTO) {
        return rbiBondScrollsService.getBrokerageHandlingReport(bondBrokerageHandleDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL")
    public ResponseDataDTO statementOfBondsDetails(StatementofBondsIssuedDTO statementofBondsIssuedDTO) {
        return rbiBondScrollsService.statementOfBondsDetails(statementofBondsIssuedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BONDS_ISSUED_REPAID_FOR_THE_MONTH_SCROLL:SCROLL")
    public ResponseDataDTO statementOfChargesServicingDetails(SatatementOfChargesBlaDTO satatementOfChargesBlaDTO) {
        return rbiBondScrollsService.statementOfChargesServicingDetails(satatementOfChargesBlaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL")
    public ResponseDataDTO statementOfChargesTurnoverCommissionDetails(RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO) {
        return rbiBondScrollsService.statementOfChargesTurnoverCommissionDetails(rbiBondTurnoverCommissionDTO);
    }
    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL")
    public ResponseDataDTO principleScrollreportAppendixXII(PrincipleScrollreportAppendixXIIDto scrollreportAppendixXIIDto) {
        return rbiBondScrollsService.principleScrollreportAppendixXII(scrollreportAppendixXIIDto);
    }

    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_STATEMENT_OF_CHARGES_TURNOVER_COMMISSION:SCROLL")
    public ResponseDataDTO estimateOfExpenses(EstimateOfExpensesDto estimateOfExpensesDto) {
        return rbiBondScrollsService.estimateOfExpenses(estimateOfExpensesDto);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:SUBSCRIPTION_TEXT_FILE_REPORTING_RBI:SCROLL")
    public ResponseDataDTO getSubsTextFileDetails(SusbscriptionTextFileDto susbscriptionTextFileDto) {
        return rbiBondScrollsService.getSubsTextFileDetails(susbscriptionTextFileDto);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:REDEMPTION_TEXT_FILE_REPORTING_RBI:SCROLL")
    public ResponseDataDTO getRedemptionTextFileDetails(RedemptionTextFileDto redemptionTextFileDto) {
        return rbiBondScrollsService.getRedemptionTextFileDetails(redemptionTextFileDto);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:MONTHLY_BROKERAGE_CLAIM_SCROLL:SCROLL")
    public ResponseDataDTO getMonthBrokerageDetails(MonthlyBrokerageClaimDto monthlyBrokerageClaimDto) {
        return rbiBondScrollsService.getMonthBrokerageDetails(monthlyBrokerageClaimDto);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:INTEREST_SCROLL_REPORT_5106:SCROLL")
    public ResponseDataDTO getInterestScrollReport5106Details(InterestScrollReportDTO interestScrollReportDTO) {
        return rbiBondScrollsService.getInterestScrollReport5106Details(interestScrollReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:APPENDIX_IV_75_MAR_2024:SCROLL")
    public ResponseDataDTO getAppendixDetails(AppendixFourMarchDTO appendixFourMarchDTO) {
        return rbiBondScrollsService.getAppendixDetails(appendixFourMarchDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:E_FILLING:SCROLL")
    public ResponseDataDTO getEfillingDetails(EfillingDTO efillingDTO) {
        return rbiBondScrollsService.getEfillingDetails(efillingDTO);
    }
    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:E_FILLING:SCROLL")
    public ResponseDataDTO getEfillingDetailsDownload(EfillingDTO efillingDTO) {
        return rbiBondScrollsService.getEfillingDetailsDownload(efillingDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:SFT_SCROLL:SCROLL")
    public ResponseDataDTO getSft(SftScrollDTO sftScrollDTO) {
        return rbiBondScrollsService.getSft(sftScrollDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:AIR:SCROLL")
    public ResponseDataDTO getAIRDetails(AirDTO airDTO) {
        return rbiBondScrollsService.getAIRDetails(airDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:APPENDIX_IV_805_MAR_2024:SCROLL")
    public ResponseDataDTO getAppendixIV805Details(AppendixIV805DTO appendixIV805DTO) {
        return rbiBondScrollsService.getAppendixIV805Details(appendixIV805DTO);
    }

    @Override
    public ResponseDataDTO getAnnualReport(AnnualReportDto annualReportDto) {
        return rbiBondScrollsService.getAnnualReport(annualReportDto);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:APPENDIX_IV_8_MAR_2024:SCROLL")
    public ResponseDataDTO getAppendixIV8Details(AppendixIV8DTO appendixIV8DTO) {
        return rbiBondScrollsService.getAppendixIV8Details(appendixIV8DTO);
    }

    @Override
    public ResponseDataDTO getAppendixIV85Details(AppendixIV85DTO appendixIV85DTO) {
        return rbiBondScrollsService.getAppendixIV85Details(appendixIV85DTO);
    }

@Override
    public ResponseDataDTO getAppendix65Details(AppendixFourMar65DTO appendixFourMar65DTO){

        return  rbiBondScrollsService.getAppendix65Details(appendixFourMar65DTO);
    }

@Override
    public ResponseDataDTO getsubScriptionMandateDetails(SubscriptionMandateDTO subscriptionMandateDTO){

        return  rbiBondScrollsService.getsubScriptionMandateDetails(subscriptionMandateDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:BROKERAGE_CLAIM_HANDLING_COMMISSION:SCROLL")
    public ResponseDataDTO getBrokerageClaimHandlingCommissionDetails(BrokerageClaimAndHandlingCommissionDTO brokerageClaimDTO){
        return rbiBondScrollsService.getBrokerageClaimHandlingCommissionDetails(brokerageClaimDTO);
    }

    @Override
   // @RequiresPermissions("GBM_SCROLL_RBIBOND:BROKERAGE_CLAIM_HANDLING_COMMISSION:SCROLL")
    public ResponseDataDTO getSchemeWiseOutstandingBalanceDetails(SchemeWiseOutstandingBalanceDTO schemeWiseOutstandingBalanceDTO){
        return rbiBondScrollsService.getSchemeWiseOutstandingBalanceDetails(schemeWiseOutstandingBalanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:HALF_YEARLY_INTEREST_SCROLL:SCROLL")
    public ResponseDataDTO getHalfYearlyInterestScrollDetails(HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return rbiBondScrollsService.getHalfYearlyInterestScrollDetails(halfYearlyInterestScrollDTO);
    }

    @Override
    @RequiresPermissions("GBM_SCROLL_RBIBOND:HALF_YEARLY_INTEREST_SCROLL:SCROLL")
    public ResponseDataDTO downloadHalfYearlyInterestScrollFile(HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return rbiBondScrollsService.downloadHalfYearlyInterestScrollFile(halfYearlyInterestScrollDTO);
    }

}
