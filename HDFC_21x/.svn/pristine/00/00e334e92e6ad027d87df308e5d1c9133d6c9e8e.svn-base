package com.isl.gbm.rbibond.controller;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondInterestNotCreditedDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceEmailBodyDTO;
import com.isl.gbm.rbibond.dto.DashBoardReportForInwardOutwardDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.facade.RbiBondReportsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbibond/reports")
public class RbiBondReportsController {

    @Autowired
    RbiBondReportsFacade rbiBondReportsFacade;

    @PostMapping(path = "/getminorToMajorIntimationRepValues")
    public ResponseEntity<ResponseDataDTO> getminorToMajorIntimationRepValues(@RequestBody final RbiBondMinorToMajorIntimateRepDTO rbiBondInterestPaymentMisReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getminorToMajorIntimationRepValues(rbiBondInterestPaymentMisReportDto));
    }



    @PostMapping(path = "/getBondInterestPayMisReportValues")
    public ResponseEntity<ResponseDataDTO> getBondInterestPayMisReportValues(@Valid @RequestBody final RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestPayMisReportValues(rbiBondInterestPaymentMisReportDto));
    }

    @PostMapping(path = "/getBondAckNominiReport")
    public ResponseEntity<ResponseDataDTO> getBondAckNominiReport(@Valid @RequestBody final BondAckNominationDTO bondAckNominationDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondAckNominiReport(bondAckNominationDTO));
    }

    @PostMapping(path = "/getBondNominiRegsReport")
    public ResponseEntity<ResponseDataDTO> getBondNominiRegsReport(@Valid @RequestBody final BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondNominiRegsReport(bondNominiRegsReportDTO));
    }

    @PostMapping(path = "/getBondNominationDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBondNominationDownload(@Valid @RequestBody final BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondNominationDownload(bondNominiRegsReportDTO));
    }

    @PostMapping(path = "/getNoticeCancelReport")
    public ResponseEntity<ResponseDataDTO> getNoticeCancelReport(@Valid @RequestBody final BondNoticeCancelReportDTO bondNoticeCancelReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getNoticeCancelReport(bondNoticeCancelReportDTO));
    }

    @PostMapping(path = "/getAckCancelNomineReport")
    public ResponseEntity<ResponseDataDTO> getAckCancelNomineReport(@Valid @RequestBody final BondCancelNomineeReportDTO bondCancelNomineeReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getAckCancelNomineReport(bondCancelNomineeReportDTO));
    }

    @PostMapping(path = "/getCertificateInvtReport")
    public ResponseEntity<ResponseDataDTO> getCertificateInvtReport(@Valid @RequestBody final BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getCertificateInvtReport(bondCertificateInvtReportDTO));
    }

    @PostMapping(path = "/getPanValidationBondSchemeReport")
    public ResponseEntity<ResponseDataDTO> getPanValidationBondSchemeReport(@Valid @RequestBody final PANValidationReportDTO panValidationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPanValidationBondSchemeReport(panValidationReportDTO));
    }

    @PostMapping(path = "/getTaxWaiverDocumentsReport")
    public ResponseEntity<ResponseDataDTO> getTaxWaiverDocumentsReport(@Valid @RequestBody final TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTaxWaiverDocumentsReport(taxWaiverDocumentsReportDTO));
    }

    @PostMapping(path = "/getPreMaturityMarkingReport")
    public ResponseEntity<ResponseDataDTO> getPreMaturityMarkingReport(@RequestBody final PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPreMaturityMarkingReport(preMaturityMarkingReportDTO));
    }

    @PostMapping(path = "/getPanNo")
    public ResponseEntity<ResponseDataDTO> getPanNo(@Valid @RequestBody final BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPanNo(bondCertificateInvtReportDTO));
    }

    @PostMapping(path = "/getBlaAcNo")
    public ResponseEntity<ResponseDataDTO> getBlaAcNo(@Valid @RequestBody final BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaAcNo(bondCertificateInvtReportDTO));
    }

    @PostMapping(path = "/getSubscripID")
    public ResponseEntity<ResponseDataDTO> getSubscripID(@Valid @RequestBody final BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubscripID(bondCertificateInvtReportDTO));
    }

    @PostMapping(path = "/getCertificateInvtHoldReport")
    public ResponseEntity<ResponseDataDTO> getAckCancelNomineReport(@Valid @RequestBody final BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getCertificateOfHolding(bondCertificateInvtHoldReportDTO));
    }

    @PostMapping(path="/getTranSameBankReport")
    public ResponseEntity<ResponseDataDTO> getTranSameBankReport(@Valid @RequestBody final BondTransSameBankReportDTO bondTransSameBankReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTranSameBankReport(bondTransSameBankReportDTO));
    }

    @PostMapping(path="getUnverifiedReportForLc")
    public ResponseEntity<ResponseDataDTO> getUnverifiedReportForLc(@Valid @RequestBody final UnverifiedReportDto unverifiedReportDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getUnverifiedReportForLc(unverifiedReportDto));
    }
    @PostMapping(path="getUnverifiedReportForCb")
    public ResponseEntity<ResponseDataDTO> getUnverifiedReportForCb(@Valid @RequestBody final UnverifiedReportDto unverifiedReportDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getUnverifiedReportForCb(unverifiedReportDto));
    }

    @PostMapping(path = "/getSuccessFailureReportDetails")
    public ResponseEntity<ResponseDataDTO> getSuccessFailureDetails(@Valid @RequestBody final SuccessFailureReportDto successFailureReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSuccessFailureDetails(successFailureReportDto));
    }

    @PostMapping(path = "/getBondInterestTrialRunEligibleAccountsReport")
    public ResponseEntity<ResponseDataDTO> getBondInterestTrialRunEligibleAccounts(@Valid @RequestBody final BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestTrialRunEligibleAccounts(bondInterestTrialRunEligibleAccountsDto));
    }
    @PostMapping(path = "/getBondInterestTrialRunEligibleAccountsReport/getDownload")
    public ResponseEntity<ResponseDataDTO> getBondInterestTrialRunEligibleAccountsDownload(@Valid @RequestBody final BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestTrialRunEligibleAccountsDownload(bondInterestTrialRunEligibleAccountsDto));
    }
    @PostMapping(path = "/getBlaModificationReport")
    public ResponseEntity<ResponseDataDTO> getBlaModificationReport(@Valid @RequestBody final BlaModificationReportDto blaModificationReportDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaModificationReport(blaModificationReportDto));
    }

    @PostMapping(path = "/getBlaModificationReportDownload")
    public ResponseEntity<ResponseDataDTO> getBlaModificationReportDownload(@Valid @RequestBody final BlaModificationReportDto blaModificationReportDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaModificationReportDownload(blaModificationReportDto));
    }

    @PostMapping(path = "/getInterestPaymentReport")
    public ResponseEntity<ResponseDataDTO> getInterestPaymentReport(@Valid @RequestBody final InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterestPaymentReport(interestPaymentToOtherBankDTO));
    }

    @PostMapping(path = "/getinterestPaymentDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getinterestPaymentDetailsDownload(@Valid @RequestBody final InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getinterestPaymentDetailsDownload(interestPaymentToOtherBankDTO));
    }

    @PostMapping(path="/getListOfBondReport")
    public ResponseEntity<ResponseDataDTO> getListOfBondReport(@Valid @RequestBody final ListOfBondInvestmentsDTO listOfBondInvestmentsDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getListOfBondReport(listOfBondInvestmentsDTO));
    }

    @PostMapping(path="/getListOfBondReportDownload")
    public ResponseEntity<ResponseDataDTO> getListOfBondReportDownload(@Valid @RequestBody final ListOfBondInvestmentsDTO listOfBondInvestmentsDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getListOfBondReportDownload(listOfBondInvestmentsDTO));
    }

    @PostMapping(path = "getBlaStatementReport")
    public ResponseEntity<ResponseDataDTO> getBlaStatementReport(@Valid @RequestBody final BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaStatementReport(blaStatementForBondHoldersDTO));
    }

    @PostMapping(path="bondSubscriptionwithoutnomination")
    public ResponseEntity<ResponseDataDTO> bondSubscriptionwithoutnomination(@Valid @RequestBody final bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO));
    }



    @PostMapping(path="/getTdsDeductReport")
    public ResponseEntity<ResponseDataDTO> getTdsDeductReport(@Valid @RequestBody final BondTdsDeductionReportDTO bondTdsDeductionReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsDeductReport(bondTdsDeductionReportDTO));
    }

    @PostMapping(path="/getTdsDeductDetails")
    public ResponseEntity<ResponseDataDTO> getTdsDeductDetails(@Valid @RequestBody final BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsDeductDetails(bondTdsDeductionReportDTO));
    }

    @PostMapping(path="/getTdsDeductionReportCb")
    public ResponseEntity<ResponseDataDTO> getTdsDeductionReportCb(@Valid @RequestBody final BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsDeductionReportCb(bondTdsCollDeductionReportDTO));
    }

    @PostMapping(path="/financialTransactionInquiry")
    public ResponseEntity<ResponseDataDTO> financialTransactionInquiry(@RequestBody final FinancialTransactionInquiryDTO financialTransactionInquiryDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.financialTransactionInquiry(financialTransactionInquiryDTO));
    }


    @PostMapping(path="/getBranchDetails")
    public ResponseEntity<ResponseDataDTO> getBranchDetails(){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBranchDetails());
    }

    @PostMapping(path="/getBlaStatusReport")
    public ResponseEntity<ResponseDataDTO> getBlaStatusReport(@Valid @RequestBody final BlaStatusReportDTO blaStatusReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaStatusReport(blaStatusReportDTO));
    }
    @PostMapping(path="/getBondBalanceReport")
    public ResponseEntity<ResponseDataDTO> getBondBalanceReport(@Valid @RequestBody final BondBalanceReportDTO bondBalanceReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondBalanceReport(bondBalanceReportDTO));
    }

    @PostMapping(path= "/getBondBalanceReportDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBondBalanceReportDownload(@Valid @RequestBody final BondBalanceReportDTO bondBalanceReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondBalanceReportDownload(bondBalanceReportDTO));
    }

    @PostMapping(path= "/getPanValidationReportDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPanValidationReportDownload(@Valid @RequestBody final PANValidationReportDTO panValidationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPanValidationReportDownload(panValidationReportDTO));
    }

    @PostMapping(path= "/getPreMaturityMarkingReportDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPreMaturityMarkingReportDownload(@Valid @RequestBody final PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPreMaturityMarkingReportDownload(preMaturityMarkingReportDTO));
    }

    @PostMapping(path="/getReportOnTransactionDetails")
    public ResponseEntity<ResponseDataDTO> getReportOnTransactionDetails(@Valid @RequestBody final ReportTransactionDetailsDTO reportTransactionDetailsDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportOnTransactionDetails(reportTransactionDetailsDTO));
    }

    @PostMapping(path="/getReportOnTransactionDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getReportOnTransactionDetailsDownload(@Valid @RequestBody final ReportTransactionDetailsDTO reportTransactionDetailsDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportOnTransactionDetailsDownload(reportTransactionDetailsDTO));
    }

    @PostMapping(path="/getReportTransactionSubcriptionDeatails")
    public ResponseEntity<ResponseDataDTO> getReportTransactionSubcriptionDeatails(@Valid @RequestBody final ReportTransactionDetailsDTO reportTransactionDetailsDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportTransactionSubcriptionDeatails(reportTransactionDetailsDTO));
    }

    @PostMapping(path="/getBondInvestmentDueNoticeReport")
    public ResponseEntity<ResponseDataDTO> getBondInvestmentDueNoticeReport(@Valid @RequestBody final BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInvestmentDueNoticeReport(bondInvestmentDueDateNoticeDTO));
    }

//    @PostMapping(path = "/getBondType")
//    public ResponseEntity<ResponseDataDTO> getBondType(@Valid @RequestBody BondLedgerAccDto bondLedgerAccDto) {
//        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondType(bondLedgerAccDto));
//    }

    @PostMapping(path="/getBondLedgerAccDet")
    public ResponseEntity<ResponseDataDTO> getBondLedgerAccDet(@Valid @RequestBody final BondLedgerAccDto bondLedgerAccDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondLedgerAccDet(bondLedgerAccDto));
    }

    @PostMapping(path="/getRegBondLedgeAccDownload")
    public ResponseEntity<ResponseDataDTO> getRegBondLedgeAccDownload(@Valid @RequestBody final BondLedgerAccDto bondLedgerAccDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getRegBondLedgeAccDownload(bondLedgerAccDto));
    }

    @PostMapping(path="/getReportTorchBla")
    public ResponseEntity<ResponseDataDTO> getReportTorchBla(@Valid @RequestBody final BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportTorchBla(bondTdsCollDeductionReportDTO));
    }

    @PostMapping(path="/getInterestRunCreditReport")
    public ResponseEntity<ResponseDataDTO> getInterestRunCreditReport(@Valid @RequestBody final BondInterestRunCreditDTO bondInterestRunCreditDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterestRunCreditReport(bondInterestRunCreditDTO));
    }

    @PostMapping(path="/getInterestTrailRunReportPayment")
    public ResponseEntity<ResponseDataDTO> getInterestTrailRunReportPayment(@Valid @RequestBody final InterestTrailRunPaymentDTO interestTrailRunPaymentDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterestTrailRunReportPayment(interestTrailRunPaymentDTO));
    }


    @PostMapping(path="/getInterRunCrdtCollBranReport")
    public ResponseEntity<ResponseDataDTO> getInterRunCrdtCollBranReport(@Valid @RequestBody final BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterRunCrdtCollBranReport(bondInterRunCrdtCollBranDTO));
    }


    @PostMapping(path="/getBondInterestCalculatedReport")
    public ResponseEntity<ResponseDataDTO> getBondInterestCalculatedReport(@Valid @RequestBody final BondInterestCalculatedDTO bondInterestCalculatedDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestCalculatedReport(bondInterestCalculatedDTO));
    }



    @PostMapping(path="/getBondInterestCalculatedReportDetails")
    public ResponseEntity<ResponseDataDTO> getBondInterestCalculatedReportDetails(@Valid @RequestBody final BondInterestCalculatedDTO bondInterestCalculatedDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestCalculatedReportDetails(bondInterestCalculatedDTO));
    }


    @PostMapping(path="/getInterPayPendingReport")
    public ResponseEntity<ResponseDataDTO> getInterPayPendingReport(@Valid @RequestBody final BondInterestPayPendingDTO bondInterestPayPendingDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterPayPendingReport(bondInterestPayPendingDTO));
    }


    @PostMapping(path="/getInterPayPendingCollReport")
    public ResponseEntity<ResponseDataDTO> getInterPayPendingCollReport(@Valid @RequestBody final BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getInterPayPendingCollReport(bondInterestPayPendingCollDTO));
    }

    @PostMapping(path="/getAccHolderInquiryReport")
    public ResponseEntity<ResponseDataDTO> getAccHolderInquiryReport(@Valid @RequestBody final BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getAccHolderInquiryReport(bondAccHolderInquiryReportDTO));
    }

    @PostMapping(path="/getBlaNameSearchReport")
    public ResponseEntity<ResponseDataDTO> getBlaNameSearchReport(@Valid @RequestBody final BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaNameSearchReport(bondBlaNameSearchReportDTO));
    }

    @PostMapping(path="/getBondCollectingdReportDetails")
    public ResponseEntity<ResponseDataDTO> getBondCollectingdReportDetails(@Valid @RequestBody final BondInteresDTO bondInteresDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondCollectingdReportDetails(bondInteresDTO));
    }

    @PostMapping(path="/getPreClosureRepaymentReport")
    public ResponseEntity<ResponseDataDTO> getPreClosureRepaymentReport(@Valid @RequestBody final BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPreClosureRepaymentReport(bondPrecloserRepayReportDTO));
    }

    @PostMapping(path="/getPreClosureRepaymentDownload")
    public ResponseEntity<ResponseDataDTO> getPreClosureRepaymentDownload(@Valid @RequestBody final BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPreClosureRepaymentDownload(bondPrecloserRepayReportDTO));
    }


    @PostMapping(path="/getStatementBrokerageDetails")
    public ResponseEntity<ResponseDataDTO> getStatementBrokerageDetails(@Valid @RequestBody final StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getStatementBrokerageDetails(statementOfBrokeragePaidDTO));
    }

    @PostMapping(path="/getStatementBrokerageDownload")
    public ResponseEntity<ResponseDataDTO> getStatementBrokerageDownload(@Valid @RequestBody final StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getStatementBrokerageDownload(statementOfBrokeragePaidDTO));
    }

    @PostMapping(path="/getDailyRemittanceLcReport")
    public ResponseEntity<ResponseDataDTO> getDailyRemittanceLcReport(@Valid @RequestBody final BondDailyRemLcDTO bondDailyRemLcDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDailyRemittanceLcReport(bondDailyRemLcDTO));
    }

    @PostMapping(path="/getSubsAccDetails")
    public ResponseEntity<ResponseDataDTO> getSubsAccDetails(@Valid @RequestBody final SubscriptionAccountDTO subscriptionAccountDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubsAccDetails(subscriptionAccountDTO));
    }

    @PostMapping(path="/getSubsAccDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getSubsAccDetailsDownload(@Valid @RequestBody final SubscriptionAccountDTO subscriptionAccountDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubsAccDetailsDownload(subscriptionAccountDTO));
    }

    @PostMapping(path="/getSubsAmtDetails")
    public ResponseEntity<ResponseDataDTO> getSubsAmtDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubsAmtDetails(bondInquiryDto));
    }

    @PostMapping(path="/getAccClosedDetails")
    public ResponseEntity<ResponseDataDTO> getAccClosedDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getAccClosedDetails(bondInquiryDto));
    }

    @PostMapping(path="/getBlaAccNumDetails")
    public ResponseEntity<ResponseDataDTO> getBlaAccNumDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBlaAccNumDetails(bondInquiryDto));
    }

    @PostMapping(path="/getHolderTypeDetails")
    public ResponseEntity<ResponseDataDTO> getHolderTypeDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getHolderTypeDetails(bondInquiryDto));
    }

    @PostMapping(path="/getMaturedAccDetails")
    public ResponseEntity<ResponseDataDTO> getMaturedAccDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getMaturedAccDetails(bondInquiryDto));
    }

    @PostMapping(path="/getMinorAccDetails")
    public ResponseEntity<ResponseDataDTO> getMinorAccDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getMinorAccDetails(bondInquiryDto));
    }

    @PostMapping(path="/getNriAccDetails")
    public ResponseEntity<ResponseDataDTO> getNriAccDetails(@Valid @RequestBody final BondInquiryDto bondInquiryDto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getNriAccDetails(bondInquiryDto));
    }

    @PostMapping(path="/getTransferInOutDetails")
    public ResponseEntity<ResponseDataDTO> getTransferInOutDetails(@Valid @RequestBody final TransferInOutDTO transferInOutDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTransferInOutDetails(transferInOutDTO));
    }

    @PostMapping(path="/getTransferInOutDownload")
    public ResponseEntity<ResponseDataDTO> getTransferInOutDownload(@Valid @RequestBody final TransferInOutDTO transferInOutDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTransferInOutDownload(transferInOutDTO));
    }

    @PostMapping(path="/getBondRepaymentDetails")
    public ResponseEntity<ResponseDataDTO> getBondRepaymentDetails(@RequestBody final BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondRepaymentDetails(bondRepaymentReportDto));
    }

    @PostMapping(path="/getBondRepaymentReport")
    public ResponseEntity<ResponseDataDTO> getBondRepaymentReport(@RequestBody final BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondRepaymentReport(bondRepaymentReportDto));
    }

    @PostMapping(path="/getBondRepaymentReportDownload")
    public ResponseEntity<ResponseDataDTO> getBondRepaymentReportDownload(@RequestBody final BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondRepaymentReportDownload(bondRepaymentReportDto));
    }

    @PostMapping(path = "/getBodEodStatusInquiryDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBodEodStatusInquiryDetails(@RequestBody BodEodStatusInqDTO bodEodStatusInqDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBodEodStatusInquiryDetails(bodEodStatusInqDTO));
    }

    @PostMapping(path = "/getModuleName", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModuleName() {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getModuleName());
    }

    /*Brokerage Invoice Download */
    @PostMapping(path = "/getBrokerageInvoiceBondScheme", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerageInvoiceBondScheme(@RequestBody BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerageInvoiceBondScheme(brokerageInvoiceDownloadDTO));
    }

    @PostMapping(path = "/getBrokerageAgencyCode", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerageAgencyCode(@RequestBody BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerageAgencyCode(brokerageInvoiceDownloadDTO));
    }

    @PostMapping(path = "/generateBrokerRes", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> generateBrokerRes( @Validated @RequestBody BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.generateBrokerRes(brokerageInvoiceDownloadDTO));
    }

    @PostMapping(path="/unlockBla")
    public ResponseEntity<ResponseDataDTO> unlockBla(@Valid @RequestBody final BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.unlockBla(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path="/unlockBlaSubmit")
    public ResponseEntity<ResponseDataDTO> unlockBlaSubmit(@Valid @RequestBody final BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.unlockBlaSubmit(bondLedgerAccountMaintenanceDTO));
    }

//    Pending Brokerage Payment Status & Claim Status
//    @PostMapping(path= "/getPendingBrokeragePaymentClaimDetails")
   /* Pending Brokerage Payment Status & Claim Status */
    @PostMapping(path= "/getPendingBrokeragePaymentClaimDetails")
    public ResponseEntity<ResponseDataDTO> getPendingBrokeragePaymentClaimDetails(@Valid @RequestBody PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPendingBrokeragePaymentClaimDetails(pendingBrokeragePaymentClaimStatusDTO));
    }

    //   BOND ACTUAL INTEREST RUN CREDITED REPORT
    @PostMapping(path = "/getBondActualInterestRunCreditedDetails")
    public ResponseEntity<ResponseDataDTO> getBondActualInterestRunCreditedDetails(@Valid @RequestBody BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO));
    }

    //   TDS REMITTANCE FINCON FILE REPORT
    @PostMapping(path = "/getTdsRemittanceFinconFileDetails")
    public ResponseEntity<ResponseDataDTO> getTdsRemittanceFinconFileDetails(@Valid @RequestBody TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsRemittanceFinconFileDetails(tdsRemittanceFinconFileDTO));
    }

    @PostMapping(path = "/getTdsRemittanceFinconFileDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getTdsRemittanceFinconFileDetailsDownload(@Valid @RequestBody TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsRemittanceFinconFileDetailsDownload(tdsRemittanceFinconFileDTO));
    }

    @PostMapping(path = "/TDSExemptionSubmitNonSubmitReport")
    public ResponseEntity<ResponseDataDTO> getTDSExemptionSubmitNonSubmitReport(@Valid @RequestBody TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTDSExemptionSubmitNonSubmitReport(tdsExemptionSubmitNonSubmitReportDTO));
    }

    @PostMapping(path = "/TDSExemptionSubmitNonSubmitDownload")
    public ResponseEntity<ResponseDataDTO> getTDSExemptionSubmitNonSubmitDownload(@Valid @RequestBody TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTDSExemptionSubmitNonSubmitDownload(tdsExemptionSubmitNonSubmitReportDTO));
    }

    //   E-TDS Report
    @PostMapping(path = "/getEtdsDetails")
    public ResponseEntity<ResponseDataDTO> getEtdsDetails(@Valid @RequestBody EtdsDTO etdsDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getEtdsDetails(etdsDTO));
    }

    @PostMapping(path = "/getEtdsDetailsDownload")
    public ResponseEntity<ResponseDataDTO> getEtdsDetailsDownload(@Valid @RequestBody EtdsDTO etdsDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getEtdsDetailsDownload(etdsDTO));
    }

    /* Brokerage Invoice & Email Body Letter */
    @PostMapping(path= "/getBrokerageInvoiceEmailBodyLetter", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerageInvoiceEmailBodyLetterDetails(@Valid @RequestBody BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerageInvoiceEmailBodyLetterDetails(brokerageInvoiceEmailBodyDTO));
    }

    //tds summary for tax audit
    @PostMapping(path= "/getTdsSummaryForTaxAudit", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTdsSummaryForTaxAudit(@Valid @RequestBody TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsSummaryForTaxAudit(tdsSummaryForTaxAuditDTO));
    }

    @PostMapping(path= "/getReportDetailsDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getReportDetailsDownload(@Valid @RequestBody TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportDetailsDownload(tdsSummaryForTaxAuditDTO));
    }
    //Report On Pan-Aadhaar non-linkage
    @PostMapping(path= "/getPanAadharDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPanAadharDetails(@Valid @RequestBody PanAadhaarDTO panAadhaarDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPanAadharDetails(panAadhaarDTO));
    }

    @PostMapping(path= "/getPanAadharDetailsDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPanAadharDetailsDownload(@Valid @RequestBody PanAadhaarDTO panAadhaarDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPanAadharDetailsDownload(panAadhaarDTO));
    }

    /* Callback Report */
    @PostMapping(path= "/getCallBackReportDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getCallBackReportDetails(@Valid @RequestBody CallBackReportDTO callBackReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getCallBackReportDetails(callBackReportDTO));
    }
 @PostMapping(path= "/getCallBackReportDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getCallBackReportDownload(@Valid @RequestBody CallBackReportDTO callBackReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getCallBackReportDownload(callBackReportDTO));
    }

    @PostMapping(path= "/subsAmtBLAMaturedAccClosed", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getSubsAmtBLAMaturedAccClosed(@Valid @RequestBody SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubsAmtBLAMaturedAccClosed(subsAmtBLAMaturedAccClosedDTO));
    }

    @PostMapping(path= "/subsAmtBLAMaturedAccClosedDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getSubsAmtBLAMaturedAccClosedDownload(@Valid @RequestBody SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getSubsAmtBLAMaturedAccClosedDownload(subsAmtBLAMaturedAccClosedDTO));
    }

    @PostMapping(path = "/getNomineeReport", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getNomineeReport(@Valid @RequestBody NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getNomineeReport(nomineeContinuationReportDTO));
    }

    @PostMapping(path = "/getNomineeReportDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getNomineeReportDownload(@Valid @RequestBody NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getNomineeReportDownload(nomineeContinuationReportDTO));
    }

    @PostMapping(path= "/getReportCancellationSubsCatCbt", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getReportCancellationSubsCatCbt(@Valid @RequestBody ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportCancellationSubsCatCbt(reportCancellationSubsCatCbtDTO));
    }

    @PostMapping(path= "/getBrokerageClaimMonthlyDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerageClaimMonthlyDownload(@Valid @RequestBody BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerageClaimMonthlyDownload(brokerageClaimMonthlyDTO));
    }

    @PostMapping(path= "/getBrokerageClaimMonthly", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerageClaimMonthly(@Valid @RequestBody BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerageClaimMonthly(brokerageClaimMonthlyDTO));
    }

    @PostMapping(path= "/getBondInterestNotCredited", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBondInterestNotCredited(@Valid @RequestBody BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestNotCredited(bondInterestNotCreditedDTO));
    }

    @PostMapping(path= "/getBondInterestNotCreditedDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBondInterestNotCreditedDownload(@Valid @RequestBody BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondInterestNotCreditedDownload(bondInterestNotCreditedDTO));
    }

    @PostMapping(path= "/getMinorAccountDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getMinorAccountDetails(@Valid @RequestBody MinorAccountDetailsDTO minorAccountDetailsDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getMinorAccountDetails(minorAccountDetailsDTO));
    }

    @PostMapping(path= "/getBLASubscriptionNominationDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBLASubscriptionNominationDetails(@Valid @RequestBody BLASubscriptionNominationDTO blaSubscriptionNominationDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBLASubscriptionNominationDetails(blaSubscriptionNominationDTO));
    }

    @PostMapping(path= "/getAcknowledgement", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAcknowledgement(@Valid @RequestBody AcknowledgementDTO acknowledgementDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getAcknowledgement(acknowledgementDTO));
    }

    @PostMapping(path= "/getAcknowledgementconfirmation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAcknowledgementconfirmation(@Valid @RequestBody AcknowledgementconfirmationDTO acknowledgementconfirmationDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getAcknowledgementconfirmation(acknowledgementconfirmationDTO));
    }

    @PostMapping(path= "/getFreezeUnfreezeDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getFreezeUnfreezeDetails(@Valid @RequestBody FreezeUnFreezeDTO freezeUnFreezeDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getFreezeUnfreezeDetails(freezeUnFreezeDTO));
    }
    @PostMapping(path= "/getReportOnUnverifiedDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getReportOnUnverifiedDetails(@Valid @RequestBody ReportOnUnverifiedDTO reportOnUnverifiedDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getReportOnUnverifiedDetails(reportOnUnverifiedDTO));
    }
    @PostMapping(path= "/getTdsDeductionTdsrate", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTdsDeductionTdsrate(@Valid @RequestBody TdsDeductionDTO tdsDeductionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsDeductionTdsrate(tdsDeductionDTO));
    }

    @PostMapping(path = "/getTdsDeductionTdsrateDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTdsDeductionTdsrateDownload(@Valid @RequestBody TdsDeductionDTO tdsDeductionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsDeductionTdsrateDownload(tdsDeductionDTO));
    }

    @PostMapping(path= "/getPreIntimationAdviceNoticeDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPreIntimationAdviceNoticeDetails(@Valid @RequestBody PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPreIntimationAdviceNoticeDetails(preIntimationAdviceNoticeDTO));
    }




    @PostMapping(path= "/getPostIntimationAdviceNoticeDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPostIntimationAdviceNoticeDetails(@Valid @RequestBody PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getPostIntimationAdviceNoticeDetails(postIntimationAdviceNoticeDTO));
    }



    @PostMapping(path= "/getHalfYearlyInterestAdvice", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getHalfYearlyInterestAdvice(@Valid @RequestBody HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getHalfYearlyInterestAdvice(halfYearlyInterestAdviceDTO));
    }

    @PostMapping(path= "/getModeOfHoldingChangeDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModeOfHoldingChangeDetails(@Valid @RequestBody ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getModeOfHoldingChangeDetails(modeOfHoldingReportDto));
    }

    @PostMapping(path= "/getModeOfHoldingDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModeOfHoldingDownload(@Valid @RequestBody ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getModeOfHoldingDownload(modeOfHoldingReportDto));
    }

    @PostMapping(path= "/getTdsEligibility", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTdsEligibility(@Valid @RequestBody TdsEligibilityDTO tdsEligibilityDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsEligibility(tdsEligibilityDTO));
    }

    @PostMapping(path= "/getTdsEligibilityDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTdsEligibilityDownload(@Valid @RequestBody TdsEligibilityDTO tdsEligibilityDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getTdsEligibilityDownload(tdsEligibilityDTO));
    }

    @PostMapping(path= "/getBrokerAppointmentEnrolledDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerEnrolledDetails(@Valid @RequestBody BrokerEnrolledDTO brokerEnrolledDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBrokerEnrolledDetails(brokerEnrolledDTO));
    }

    @PostMapping(path="/getDetailsOfPdf")
    public ResponseEntity<ResponseDataDTO> downloadPdfFile(@Valid @RequestBody final NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDetailsOfPdf(netBankingTransectionPdfDTO));
    }

    @PostMapping(path= "/getBondTransferIn", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBondTransferIn(@Valid @RequestBody BondTransferInDTO bondTransferInDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getBondTransferIn(bondTransferInDTO));
    }

    @PostMapping(path= "/getDisablingDeactivating", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDisablingDeactivating(@Valid @RequestBody DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDisablingDeactivating(disablingDeactivatingDTO));
    }

    @PostMapping(path= "/getDisablingDeactivatingDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDisablingDeactivatingDownload(@Valid @RequestBody DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDisablingDeactivatingDownload(disablingDeactivatingDTO));
    }

    @PostMapping(path= "/getFutureTdsDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getFutureTdsDetails(@Valid @RequestBody FutureTdsDto futureTdsDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getFutureTdsDetails(futureTdsDto));
    }

    @PostMapping(path= "/getJointTypeDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getJointTypeDetails(@Valid @RequestBody JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getJointTypeDetails(jointTypeThreeToTwoReportDTO));
    }

    @PostMapping(path= "/getDashBoardReportForInwardOutwardDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDashBoardReportForInwardOutwardDownload(@Valid @RequestBody final DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDashBoardReportForInwardOutwardDownload(dashBoardReportForInwardOutwardDTO));
    }

    @PostMapping(path= "/getDashBoardReportForInwardOutward", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDashBoardReportForInwardOutward(@Valid @RequestBody DashBoardReportForInwardOutwardDTO DashBoardReportForInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO));
    }

    @PostMapping(path= "/getCpTotalDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getCpTotalDetails(@RequestBody CertificatePrintingDTO certificatePrintingDTO)throws Exception {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getCpTotalDetails(certificatePrintingDTO));
    }

//    @RequestMapping(value = "/dataPatchUpload/upload", method = RequestMethod.POST)
//    public ResponseEntity<ResponseDataDTO> uploadDataPatch(@RequestPart("file") final MultipartFile file,
//                                                           @RequestPart DataPatchUploadRequestDTO dto) {
//        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.uploadDataPatch(file, dto));
//    }

    @PostMapping(path= "/getHufDeathMarkingDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getHufDeathMarkingDetails(@RequestBody HUFDeathMarkingReportDTO hufDeathMarkingReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportsFacade.getHufDeathMarkingDetails(hufDeathMarkingReportDTO));
    }
}