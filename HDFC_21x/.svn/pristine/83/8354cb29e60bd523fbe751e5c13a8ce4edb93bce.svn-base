package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondInterestNotCreditedDTO;
import com.isl.gbm.rbibond.dto.DashBoardReportForInwardOutwardDTO;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceEmailBodyDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.facade.RbiBondReportsFacade;
import com.isl.gbm.rbibond.service.RbiBondReportsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class RbiBondReportsFacadeImpl implements RbiBondReportsFacade {
    @Autowired
    private RbiBondReportsService rbiBondReportsService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_NOMINATION_REPORT:REPORT")
    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO) {
        return rbiBondReportsService.getBondAckNominiReport(bondAckNominationDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_MINOR_MAJOR_INITIMATION_REPORT:REPORT")
    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO) {
        return rbiBondReportsService.getminorToMajorIntimationRepValues(rbiBondMinorToMajorIntimateRepDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_DC:BOND_INTEREST_PAY_MIS_REPORT:REPORT")
    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto) {
        return rbiBondReportsService.getBondInterestPayMisReportValues(rbiBondInterestPaymentMisReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_NOMINATION_REGISTER:REPORT")
    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return rbiBondReportsService.getBondNominiRegsReport(bondNominiRegsReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_NOMINATION_REGISTER:REPORT")
    public ResponseDataDTO getBondNominationDownload(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return rbiBondReportsService.getBondNominationDownload(bondNominiRegsReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NOTICE_CANCEL_NOMINATION_REPORT:REPORT")
    public ResponseDataDTO getNoticeCancelReport(BondNoticeCancelReportDTO bondNoticeCancelReportDTO) {
        return rbiBondReportsService.getNoticeCancelReport(bondNoticeCancelReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACK_CANCEL_NOMINATION_REPORT:REPORT")
    public ResponseDataDTO getAckCancelNomineReport(BondCancelNomineeReportDTO bondCancelNomineeReportDTO) {
        return rbiBondReportsService.getAckCancelNomineReport(bondCancelNomineeReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT")
    public ResponseDataDTO getCertificateInvtReport(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsService.getCertificateInvtReport(bondCertificateInvtReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PAN_VALIDATION_REPORT:REPORT")
    public ResponseDataDTO getPanValidationBondSchemeReport(PANValidationReportDTO panValidationReportDTO) {
        return rbiBondReportsService.getPanValidationBondSchemeReport(panValidationReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:Tax_Waiver_Documents_Report:REPORT")
    public ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO) {
        return rbiBondReportsService.getTaxWaiverDocumentsReport(taxWaiverDocumentsReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PRE_MATURITY_MARKING_REPORT:REPORT")
    public ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return rbiBondReportsService.getPreMaturityMarkingReport(preMaturityMarkingReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT")
    public ResponseDataDTO getPanNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsService.getPanNo(bondCertificateInvtReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT")
    public ResponseDataDTO getBlaAcNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsService.getBlaAcNo(bondCertificateInvtReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_INVEST_REPORT:REPORT")
    public ResponseDataDTO getSubscripID(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsService.getSubscripID(bondCertificateInvtReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CERTIFICATE_OF_HOLD_REPORT:REPORT")
    public ResponseDataDTO getCertificateOfHolding(BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO) {
        return rbiBondReportsService.getCertificateOfHolding(bondCertificateInvtHoldReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_SAME_BANK_REPORT:REPORT")
    public ResponseDataDTO getTranSameBankReport(BondTransSameBankReportDTO bondTransSameBankReportDTO) {
        return rbiBondReportsService.getTranSameBankReport(bondTransSameBankReportDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:LIST_OF_BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO_REPORT:REPORT")
    public ResponseDataDTO getListOfBondReport(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        return rbiBondReportsService.getListOfBondReport(listOfBondInvestmentsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:LIST_OF_BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO_REPORT:REPORT")
    public ResponseDataDTO getListOfBondReportDownload(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        return rbiBondReportsService.getListOfBondReportDownload(listOfBondInvestmentsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT")
    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {
        return rbiBondReportsService.getBlaStatementReport(blaStatementForBondHoldersDTO);
    }
 @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATEMENT_FOR_BONDHOLDERS_REPORT:REPORT")
    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO) {
        return rbiBondReportsService.bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_REPORT:REPORT")
    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) {
        return rbiBondReportsService.getTdsDeductReport(bondTdsDeductionReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_CB_REPORT:REPORT")
    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsService.getTdsDeductionReportCb(bondTdsCollDeductionReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:UNVERIFIED_RECORD_LC:ENQUIRY")
    public ResponseDataDTO getUnverifiedReportForLc(UnverifiedReportDto unverifiedReportDto) {
        return rbiBondReportsService.getUnverifiedReportRbi(unverifiedReportDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:UNVERIFIED_RECORD_CB:ENQUIRY")
    public ResponseDataDTO getUnverifiedReportForCb(UnverifiedReportDto unverifiedReportDto) {
        return rbiBondReportsService.getUnverifiedReportRbi(unverifiedReportDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_FTI_REPORT_LC:ENQUIRY")
    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) {
        return rbiBondReportsService.financialTransactionInquiry(financialTransactionInquiryDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_REPORT:REPORT")
    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {
        return rbiBondReportsService.getTdsDeductDetails(bondTdsDeductionReportDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_STATUS_REPORT:REPORT")
    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO) {
        return rbiBondReportsService.getBlaStatusReport(blaStatusReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT")
    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto) {
        return rbiBondReportsService.getBondLedgerAccDet(bondLedgerAccDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT")
    public ResponseDataDTO getRegBondLedgeAccDownload(BondLedgerAccDto bondLedgerAccDto) {
        return rbiBondReportsService.getRegBondLedgeAccDownload(bondLedgerAccDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_BALANCE_REPORT:REPORT")
    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO) {
        return rbiBondReportsService.getBondBalanceReport(bondBalanceReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_BALANCE_REPORT:REPORT")
    public ResponseDataDTO getBondBalanceReportDownload(BondBalanceReportDTO bondBalanceReportDTO) {
        return rbiBondReportsService.getBondBalanceReportDownload(bondBalanceReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PAN_VALIDATION_REPORT:REPORT")
    public ResponseDataDTO getPanValidationReportDownload(PANValidationReportDTO panValidationReportDTO) {
        return rbiBondReportsService.getPanValidationReportDownload(panValidationReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PRE_MATURITY_MARKING_REPORT:REPORT")
    public ResponseDataDTO getPreMaturityMarkingReportDownload(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return rbiBondReportsService.getPreMaturityMarkingReportDownload(preMaturityMarkingReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT")
    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsService.getReportOnTransactionDetails(reportTransactionDetailsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT")
    public ResponseDataDTO getReportOnTransactionDetailsDownload(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsService.getReportOnTransactionDetailsDownload(reportTransactionDetailsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INVESTMENT_DUE_DATE_NOTICE_REPORT:REPORT")
    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO) {
        return rbiBondReportsService.getBondInvestmentDueNoticeReport(bondInvestmentDueDateNoticeDTO);
    }

    @Override
    public ResponseDataDTO getBranchDetails() {
        return rbiBondReportsService.getBranchDetails();
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_CENTER_REPORT:REPORT")
    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) {
        return rbiBondReportsService.getInterestRunCreditReport(bondInterestRunCreditDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TDS_DEDUCTION_CB_REPORT:REPORT")
    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsService.getReportTorchBla(bondTdsCollDeductionReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_TRANSACTION_DETAILS_FOR_BOND:REPORT")
    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsService.getReportTransactionSubcriptionDeatails(reportTransactionDetailsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_PAYMENT_WISE_REPORT:REPORT")
    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) {
        return rbiBondReportsService.getInterestTrailRunReportPayment(interestTrailRunPaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACTUAL_INTEREST_CRDT_COLLECT_BRANCH_REPORT:REPORT")
    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) {
        return rbiBondReportsService.getInterRunCrdtCollBranReport(bondInterRunCrdtCollBranDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_DC:BOND_INTEREST_NOT_CALCULATED_REPORT:REPORT")
    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsService.getBondInterestCalculatedReport(bondInterestCalculatedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_DC:BOND_INTEREST_NOT_CALCULATED_REPORT:REPORT")
    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsService.getBondInterestCalculatedReportDetails(bondInterestCalculatedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_DC_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_REPORT:REPORT")
    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) {
        return rbiBondReportsService.getInterPayPendingReport(bondInterestPayPendingDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_HOLDER_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO) {
        return rbiBondReportsService.getAccHolderInquiryReport(bondAccHolderInquiryReportDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_LEDGER_INQUIRY_NAME_SEARCH_REPORT:REPORT")
    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO) {
        return rbiBondReportsService.getBlaNameSearchReport(bondBlaNameSearchReportDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRECLOSURE_REPAYMENT_REPORT:REPORT")
    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        return rbiBondReportsService.getPreClosureRepaymentReport(bondPrecloserRepayReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRECLOSURE_REPAYMENT_REPORT:REPORT")
    public ResponseDataDTO getPreClosureRepaymentDownload(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        return rbiBondReportsService.getPreClosureRepaymentDownload(bondPrecloserRepayReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_NOT_CALCULATED_REPORT_COLLECTING_BRANCH:REPORT")
    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO) {
        return rbiBondReportsService.getBondCollectingdReportDetails(bondInteresDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BROKERAGE_PAID_AND_UNPAID:REPORT")
    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokegePaidDTO) {
        return rbiBondReportsService.getStatementBrokerageDetails(statementOfBrokegePaidDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:STATEMENT_OF_BROKERAGE_PAID_AND_UNPAID:REPORT")
    public ResponseDataDTO getStatementBrokerageDownload(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        return rbiBondReportsService.getStatementBrokerageDownload(statementOfBrokeragePaidDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBI:BOD_EOD_STATUS_INQUIRY_DC:REPORT", "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:REPORT", "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:REPORT"})
    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return rbiBondReportsService.getBodEodStatusInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBI:BOD_EOD_STATUS_INQUIRY_DC:REPORT", "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:REPORT", "GBM_RBI_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:REPORT"})
    public ResponseDataDTO getModuleName() {
        return rbiBondReportsService.getModuleName();
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAYMENT_PENDING_COLL_BRANCH_REPORT:REPORT")
    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) {
        return rbiBondReportsService.getInterPayPendingCollReport(bondInterestPayPendingCollDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsService.getBrokerageInvoiceBondScheme(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsService.getBrokerageAgencyCode(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsService.generateBrokerRes(brokerageInvoiceDownloadDTO);
    }



    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DAILY_REMITTANCE_LC_REPORT:REPORT")
    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO) {
        return rbiBondReportsService.getDailyRemittanceLcReport(bondDailyRemLcDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO){
        return rbiBondReportsService.getSubsAccDetails(subscriptionAccountDTO);
    }

    @Override
    public ResponseDataDTO getSubsAccDetailsDownload(SubscriptionAccountDTO subscriptionAccountDTO) {
        return rbiBondReportsService.getSubsAccDetailsDownload(subscriptionAccountDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getSubsAmtDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getSubsAmtDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getAccClosedDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getAccClosedDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getBlaAccNumDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getBlaAccNumDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getHolderTypeDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getHolderTypeDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getMaturedAccDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getMaturedAccDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getMinorAccDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getMinorAccDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getNriAccDetails( BondInquiryDto bondInquiryDto){
        return rbiBondReportsService.getNriAccDetails(bondInquiryDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_IN_TRANSFER_OUT_REPORT:REPORT")
    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO transferInOutDTO){
        return rbiBondReportsService.getTransferInOutDetails(transferInOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRANSFER_IN_TRANSFER_OUT_REPORT:REPORT")
    public ResponseDataDTO getTransferInOutDownload(TransferInOutDTO transferInOutDTO) {
        return rbiBondReportsService.getTransferInOutDownload(transferInOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT")
    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return rbiBondReportsService.getBondRepaymentDetails(bondRepaymentReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:UNLOCK_BLA:REPORT")
    public ResponseDataDTO unlockBla( BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){
        return rbiBondReportsService.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:UNLOCK_BLA:REPORT")
    public ResponseDataDTO unlockBlaSubmit( BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO){
        return rbiBondReportsService.unlockBlaSubmit(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:SUCCESS_FAILURE_FOR_BROKERAGE_REPORT:REPORT")
    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto){
        return rbiBondReportsService.getSuccessFailureDetails(successFailureReportDto);
    }


    //interesttrialrun
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_TRIAL_RUN_ELIGIBLE_ACCOUNTS_REPORT:REPORT")
    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto){
        return rbiBondReportsService.getBondInterestTrialRunEligibleAccounts(bondInterestTrialRunEligibleAccountsDto);
}    //interesttrialrun
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_INTEREST_TRIAL_RUN_ELIGIBLE_ACCOUNTS_REPORT:REPORT")
    public ResponseDataDTO getBondInterestTrialRunEligibleAccountsDownload(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto){
        return rbiBondReportsService.getBondInterestTrialRunEligibleAccountsDownload(bondInterestTrialRunEligibleAccountsDto);
}
    //blamodification
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_MODIFICATION_REPORT:REPORT")
    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto){
        return rbiBondReportsService.getBlaModificationReport(blaModificationReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_MODIFICATION_REPORT:REPORT")
    public ResponseDataDTO getBlaModificationReportDownload(BlaModificationReportDto blaModificationReportDto) {
        return rbiBondReportsService.getBlaModificationReportDownload(blaModificationReportDto);
    }

    //interest payment to other bank report(non-hdfc)
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:INTEREST_PAYMENT_TO_OTHER_BANK_REPORT:REPORT")
    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO){
        return rbiBondReportsService.getInterestPaymentReport(interestPaymentToOtherBankDTO);
    }

    @Override
    public ResponseDataDTO getinterestPaymentDetailsDownload(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        return rbiBondReportsService.getinterestPaymentDetailsDownload(interestPaymentToOtherBankDTO);

    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PENDING_BRKGE_PAYMENT_CLAIM_DETAILS:REPORT")
    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO) {
        return rbiBondReportsService.getPendingBrokeragePaymentClaimDetails(pendingBrokeragePaymentClaimStatusDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_ACTUAL_INTEREST_RUN_CREDITED_REPORT:REPORT")
    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO) {
        return rbiBondReportsService.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);
    }

    @Override
    @RequiresPermissions(" GBM_RBIBOND_GU_FUNCTIONALITY:TDS_REMITTANCE_FINCON_FILE_REPORT:REPORT")
    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return rbiBondReportsService.getTdsRemittanceFinconFileDetails(tdsRemittanceFinconFileDTO);
    }

    @Override
    @RequiresPermissions(" GBM_RBIBOND_GU_FUNCTIONALITY:TDS_REMITTANCE_FINCON_FILE_REPORT:REPORT")
    public ResponseDataDTO getTdsRemittanceFinconFileDetailsDownload(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return rbiBondReportsService.getTdsRemittanceFinconFileDetailsDownload(tdsRemittanceFinconFileDTO);

    }

//    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_REG_BOND_LEDGER_ACCT_IND_REPORT:REPORT")
//    public ResponseDataDTO getBondType(BondLedgerAccDto bondLedgerAccDto) {
//        return rbiBondReportsService.getBondType(bondLedgerAccDto);
//    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_INVOICE_EMAIL_BODY_LETTER_REPORT:REPORT")
    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO) {
        return rbiBondReportsService.getBrokerageInvoiceEmailBodyLetterDetails(brokerageInvoiceEmailBodyDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:CALLBACK_REPORT:REPORT")
    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO) {
        return rbiBondReportsService.getCallBackReportDetails(callBackReportDTO);
    }
  @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:CALLBACK_REPORT:REPORT")
    public ResponseDataDTO getCallBackReportDownload(CallBackReportDTO callBackReportDTO) {
        return rbiBondReportsService.getCallBackReportDownload(callBackReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_Exemption_Submitted_Non_Submitted:REPORT")
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return rbiBondReportsService.getTDSExemptionSubmitNonSubmitReport(tdsExemptionSubmitNonSubmitReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_Exemption_Submitted_Non_Submitted:REPORT")
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitDownload(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return rbiBondReportsService.getTDSExemptionSubmitNonSubmitDownload(tdsExemptionSubmitNonSubmitReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:E-TDS_REPORT:REPORT")
    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO) {
        return rbiBondReportsService.getEtdsDetails(etdsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:E-TDS_REPORT:REPORT")
    public ResponseDataDTO getEtdsDetailsDownload(EtdsDTO etdsDTO) {
        return rbiBondReportsService.getEtdsDetailsDownload(etdsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return rbiBondReportsService.getSubsAmtBLAMaturedAccClosed(subsAmtBLAMaturedAccClosedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_SUBS_ACCOUNT_INQUIRY_REPORT:REPORT")
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosedDownload(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return rbiBondReportsService.getSubsAmtBLAMaturedAccClosedDownload(subsAmtBLAMaturedAccClosedDTO);
    }

    //tds summary for tax audit
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_SUMMARY_TAX_AUDIT_REPORT:REPORT")
    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return rbiBondReportsService.getTdsSummaryForTaxAudit(tdsSummaryForTaxAuditDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_SUMMARY_TAX_AUDIT_REPORT:REPORT")
    public ResponseDataDTO getReportDetailsDownload(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return rbiBondReportsService.getReportDetailsDownload(tdsSummaryForTaxAuditDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:NOMINEE_CONTINUATION_REPORT:REPORT")
    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return rbiBondReportsService.getNomineeReport(nomineeContinuationReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:NOMINEE_CONTINUATION_REPORT:REPORT")
    public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return rbiBondReportsService.getNomineeReportDownload(nomineeContinuationReportDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_FOR_CANCELLATION_SUBSCRIPTION_CAT_CBT:REPORT")
    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO) {
        return rbiBondReportsService.getReportCancellationSubsCatCbt(reportCancellationSubsCatCbtDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME&EXPENCE:REPORT")
    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return rbiBondReportsService.getBrokerageClaimMonthly(brokerageClaimMonthlyDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME&EXPENCE:REPORT")
    public ResponseDataDTO getBrokerageClaimMonthlyDownload(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return rbiBondReportsService.getBrokerageClaimMonthlyDownload(brokerageClaimMonthlyDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INTEREST_NOT_CREDITED_REPORT:REPORT")
    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return rbiBondReportsService.getBondInterestNotCredited(bondInterestNotCreditedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_INTEREST_NOT_CREDITED_REPORT:REPORT")
    public ResponseDataDTO getBondInterestNotCreditedDownload(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return rbiBondReportsService.getBondInterestNotCreditedDownload(bondInterestNotCreditedDTO);

    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT")
    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto) {
        return rbiBondReportsService.getBondRepaymentReport(bondRepaymentReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_BOND_REPAYMENT_REPORT:REPORT")
    public ResponseDataDTO getBondRepaymentReportDownload(BondRepaymentReportDto bondRepaymentReportDto) {
        return rbiBondReportsService.getBondRepaymentReportDownload(bondRepaymentReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:MINOR_ACCOUNT_DETAILS:REPORT")
    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO) {
        return rbiBondReportsService.getMinorAccountDetails(minorAccountDetailsDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BLA_SUBSCRIPTION_NOMINATION_DETAILED:REPORT")
    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO) {
        return rbiBondReportsService.getBLASubscriptionNominationDetails(blaSubscriptionNominationDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:ACKNOWLEDGEMENT_OF_APPOINTMENT_OF_NOMINATION:REPORT")
    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO) {
        return rbiBondReportsService.getAcknowledgement(acknowledgementDTO);
    }

    @Override
    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO) {
        return rbiBondReportsService.getAcknowledgementconfirmation(acknowledgementconfirmationDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:FREEZE_UN_FREEZE:REPORT")
    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO) {
        return rbiBondReportsService.getFreezeUnfreezeDetails(freezeUnFreezeDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:REPORT_ON_UNVERIFIED_RECORDS_DC:ENQUIRY")
    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO) {
        return rbiBondReportsService.getReportOnUnverifiedDetails(reportOnUnverifiedDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT:REPORT")
    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO) {
        return rbiBondReportsService.getTdsDeductionTdsrate(tdsDeductionDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT:REPORT")
    public ResponseDataDTO getTdsDeductionTdsrateDownload(TdsDeductionDTO tdsDeductionDTO) {
        return rbiBondReportsService.getTdsDeductionTdsrateDownload(tdsDeductionDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_ELIGIBILITY_REPORT:REPORT")
    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO) {
        return rbiBondReportsService.getTdsEligibility(tdsEligibilityDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TDS_ELIGIBILITY_REPORT:REPORT")
    public ResponseDataDTO getTdsEligibilityDownload(TdsEligibilityDTO tdsEligibilityDTO) {
        return rbiBondReportsService.getTdsEligibilityDownload(tdsEligibilityDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PRE_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT")
    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO) {
        return rbiBondReportsService.getPreIntimationAdviceNoticeDetails(preIntimationAdviceNoticeDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:POST_INTIMATION_ADVICE_NOTICE_MATURITY_CASES:REPORT")
    public ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO) {
        return rbiBondReportsService.getPostIntimationAdviceNoticeDetails(postIntimationAdviceNoticeDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:HALF_YEARLY_INTEREST_ADVICE:REPORT")
    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO) {
        return rbiBondReportsService.getHalfYearlyInterestAdvice(halfYearlyInterestAdviceDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:MODE_OF_HOLDING_CHANGE_REPORT:REPORT")
    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return rbiBondReportsService.getModeOfHoldingChangeDetails(modeOfHoldingReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:MODE_OF_HOLDING_CHANGE_REPORT:REPORT")
    public ResponseDataDTO getModeOfHoldingDownload(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return rbiBondReportsService.getModeOfHoldingDownload(modeOfHoldingReportDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BROKER_APPOINTMENT_ENROLLED_REPORT_LETTER:REPORT")
    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO) {
        return rbiBondReportsService.getBrokerEnrolledDetails(brokerEnrolledDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:BOND_TRANSFER_IN_FROM_SAME_BANK:REPORT")
    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO) {
        return rbiBondReportsService.getBondTransferIn(bondTransferInDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DISABLING_DEACTIVATING_OF_BROKERS:REPORT")
    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return rbiBondReportsService.getDisablingDeactivating(disablingDeactivatingDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DISABLING_DEACTIVATING_OF_BROKERS:REPORT")
    public ResponseDataDTO getDisablingDeactivatingDownload(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return rbiBondReportsService.getDisablingDeactivatingDownload(disablingDeactivatingDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DASHBOARD_INWARD_OUTWARD_REPORT:REPORT")
    public ResponseDataDTO getDashBoardReportForInwardOutwardDownload(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO) {
        return rbiBondReportsService.getDashBoardReportForInwardOutwardDownload(dashBoardReportForInwardOutwardDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:FUTURE_TDS_CALCULATION_REPORT:REPORT")
    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto) {
        return rbiBondReportsService.getFutureTdsDetails(futureTdsDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_PAN_AADHAR:REPORT")
    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO) {
        return rbiBondReportsService.getPanAadharDetails(panAadhaarDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:REPORT_ON_PAN_AADHAR:REPORT")
    public ResponseDataDTO getPanAadharDetailsDownload(PanAadhaarDTO panAadhaarDTO) {
        return rbiBondReportsService.getPanAadharDetailsDownload(panAadhaarDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:NET_BANKING_TRANSECTION_FOR_ALL_HOLDERS:REPORT")
    public ResponseDataDTO  getDetailsOfPdf( NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) throws IOException{

        return rbiBondReportsService.getDetailsOfPdf(netBankingTransectionPdfDTO);

    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DASHBOARD_INWARD_OUTWARD_REPORT:REPORT")
    public ResponseDataDTO  getDashBoardReportForInwardOutward( DashBoardReportForInwardOutwardDTO DashBoardReportForInwardOutwardDTO) {

        return rbiBondReportsService.getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO);


    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:NET_BANKING_TRANSECTION_FOR_ALL_HOLDERS:REPORT")
    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO){
        return rbiBondReportsService.getJointTypeDetails(jointTypeThreeToTwoReportDTO);
}

    @Override
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO)throws Exception{
        return rbiBondReportsService.getCpTotalDetails(certificatePrintingDTO);
    }

//    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DATA_PATCH_UPLOAD_REPORT:REPORT")
//    public ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO dataPatchUploadRequestDTO) {
////        return rbiBondReportsService.uploadDataPatch(file, dataPatchUploadRequestDTO);
//        return null;
//    }

    @Override
    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO) {
        return rbiBondReportsService.getHufDeathMarkingDetails(hufDeathMarkingReportDTO);
    }


}
