package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondInterestNotCreditedDTO;
import com.isl.gbm.rbibond.dto.DashBoardReportForInwardOutwardDTO;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceEmailBodyDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface RbiBondReportsBO {
    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO);

    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO);

    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto);

    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO);

    public ResponseDataDTO getBondNominationDownload(BondNominiRegsReportDTO bondNominiRegsReportDTO);

    public ResponseDataDTO getNoticeCancelReport(BondNoticeCancelReportDTO bondNoticeCancelReportDTO);

    public ResponseDataDTO getAckCancelNomineReport(BondCancelNomineeReportDTO bondCancelNomineeReportDTO);

    public ResponseDataDTO getCertificateInvtReport(BondCertificateInvtReportDTO bondCertificateInvtReportDTO);

    public ResponseDataDTO getPanValidationBondSchemeReport(PANValidationReportDTO panValidationReportDTO);


    public ResponseDataDTO getPanNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO);

    public ResponseDataDTO getBlaAcNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO);

    public ResponseDataDTO getSubscripID(BondCertificateInvtReportDTO bondCertificateInvtReportDTO);

    public ResponseDataDTO getCertificateOfHolding(BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO);

    public ResponseDataDTO getTranSameBankReport(BondTransSameBankReportDTO bondTransSameBankReportDTO);

    public ResponseDataDTO getUnverifiedReportRbi(UnverifiedReportDto unverifiedReportDto);

    public ResponseDataDTO getListOfBondReport(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO);
    public ResponseDataDTO getListOfBondReportDownload(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO);

    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO);

    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO);

    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO);

    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO);

    byte[] excelFileGeneratorForTdsCb(List<Map<String, Object>> results, BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) throws IOException;

    byte[] pdfFileGeneratorForTdsCb(List<Map<String, Object>> results, BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) throws IOException;

    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException;

    byte[] excelFileGeneratorForTDSReport(List<Map<String, Object>> results, BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException;

    byte[] pdfFileGeneratorForTDSReport(List<Map<String, Object>> results, BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException;

    byte[] excelFileGeneratorForInterestPayMisReport(HashMap<String, Object> resultMap) throws IOException;

    byte[] pdfFileGeneratorForInterestPayMisReport(HashMap<String, Object> resultMap) throws IOException;

    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO);

//    byte[] excelFileGeneratorForFtiReport(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) throws IOException;
//
//    byte[] pdfFileGeneratorForFtiReport(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) throws DocumentException;

    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO);

    ResponseDataDTO getBranchDetails();

    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO);
    public ResponseDataDTO getBondBalanceReportDownload(BondBalanceReportDTO bondBalanceReportDTO);

    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);
    public ResponseDataDTO getReportOnTransactionDetailsDownload(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO);

    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO);

    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto);
    public ResponseDataDTO getRegBondLedgeAccDownload(BondLedgerAccDto bondLedgerAccDto);

    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO);

    byte[] csvFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException;

    byte[] pipeSeparatedFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException;

    byte[] excelFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException;

    byte[] excelFileGeneratorForBondLedgerAccountReport(BondLedgerAccDto bondLedgerAccDto) throws IOException;

    byte[] pdfFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException;

    byte[] excelFileGeneratorForInterestRunReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) throws IOException;

    byte[] pdfFileGeneratorForInterestRunReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) throws IOException;

    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO);

//    byte[] excelFileGeneratorForInterRunRepaymentReport(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) throws IOException;
//
//    byte[] pdfFileGeneratorForInterRunRepaymentReport(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) throws DocumentException;

    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO);

    byte[] excelFileGeneratorForInterRunCollBranchReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) throws IOException;

    byte[] pdfFileGeneratorForInterRunCollBranchReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) throws IOException;

    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    byte[] pdfFileGeneratorForBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) throws IOException;

    byte[] excelFileGeneratorForBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) throws IOException;

    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO);

    byte[] excelFileGeneratorForInterestPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) throws IOException;

    byte[] pdfFileGeneratorForInterestPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) throws IOException;

    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO);

    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO);

    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO);

//    byte[] pdfFileGeneratorForBondInterestCollectingReport(BondInteresDTO bondInteresDTO) throws DocumentException;
//
//    byte[] excelFileGeneratorForBondInterestCollectingReport(BondInteresDTO bondInteresDTO) throws IOException;

    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO);
    public ResponseDataDTO getPreClosureRepaymentDownload(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO);

    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO);

    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO);
    public ResponseDataDTO getSubsAccDetailsDownload(SubscriptionAccountDTO subscriptionAccountDTO);

    public ResponseDataDTO getSubsAmtDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getAccClosedDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getBlaAccNumDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getHolderTypeDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getMaturedAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getMinorAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getNriAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO bondInquiryDto);
    public ResponseDataDTO getTransferInOutDownload(TransferInOutDTO transferInOutDTO);

    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO);
    public ResponseDataDTO getStatementBrokerageDownload(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO);

    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException;

    byte[] excelFileGeneratorForBondRepay(List<Map<String, Object>> results, BondRepaymentReportDto bondRepaymentReportDto) throws IOException;

    byte[] pdfFileGeneratorForBondRepay(List<Map<String, Object>> results, BondRepaymentReportDto bondRepaymentReportDto) throws IOException;

    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO);

    public ResponseDataDTO getModuleName();

    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO);

    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    byte[] pdfFileGeneratorForBondInterestPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) throws IOException;

    byte[] excelFileGeneratorForBondInterestPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) throws IOException;

    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto);

    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO);

    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO);

//    public ResponseDataDTO getBondType(BondLedgerAccDto bondLedgerAccDto);

    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO getCallBackReportDetails);

    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO);
    public ResponseDataDTO getCallBackReportDownload(CallBackReportDTO callBackReportDTO);

    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO);
    public ResponseDataDTO getTdsRemittanceFinconFileDetailsDownload(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO);

    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO);
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitDownload(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO);

    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccounts);
    public ResponseDataDTO getBondInterestTrialRunEligibleAccountsDownload(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccounts);

    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto);
    public ResponseDataDTO getBlaModificationReportDownload(BlaModificationReportDto blaModificationReportDto);

    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO);
    public ResponseDataDTO getinterestPaymentDetailsDownload(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO);

    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO);
    public ResponseDataDTO getEtdsDetailsDownload(EtdsDTO etdsDTO);

    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO);
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosedDownload(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO);

    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO);
    public ResponseDataDTO getReportDetailsDownload(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO);

    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO);
    public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO);

    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO);

    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO);
    public ResponseDataDTO getBrokerageClaimMonthlyDownload(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO);

    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO);
    public ResponseDataDTO getBondInterestNotCreditedDownload(BondInterestNotCreditedDTO bondInterestNotCreditedDTO);

    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto);
    public ResponseDataDTO getBondRepaymentReportDownload(BondRepaymentReportDto bondRepaymentReportDto);

    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO);

    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO);

    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO);

    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO);

    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO);

    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO);

    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO);


    public ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO);


    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO);

    public ResponseDataDTO getTdsDeductionTdsrateDownload(TdsDeductionDTO tdsDeductionDTO);

    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO);
    public ResponseDataDTO getTdsEligibilityDownload(TdsEligibilityDTO tdsEligibilityDTO);

    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO);

    public ResponseDataDTO getPanAadharDetailsDownload(PanAadhaarDTO panAadhaarDTO);

    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO);

    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto);

    public ResponseDataDTO getModeOfHoldingDownload(ModeOfHoldingReportDto modeOfHoldingReportDto);

    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO);

    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO);

    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO);
    public ResponseDataDTO getDisablingDeactivatingDownload(DisablingDeactivatingDTO disablingDeactivatingDTO);
    public ResponseDataDTO getDashBoardReportForInwardOutwardDownload(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO);

    public ResponseDataDTO  getDetailsOfPdf( NetBankingTransectionPdfDTO netBankingTransectionPdfDTO);

    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto);

    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO);
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO);
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO);



    ResponseDataDTO getPanValidationReportDownload(PANValidationReportDTO panValidationReportDTO);

    ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO);

    ResponseDataDTO getPreMaturityMarkingReportDownload(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO);

    ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO);

//    ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO dataPatchUploadRequestDTO);

    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO);

}