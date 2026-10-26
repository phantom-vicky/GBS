package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondInterestNotCreditedDTO;
import com.isl.gbm.rbibond.dto.DashBoardReportForInwardOutwardDTO;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceEmailBodyDTO;
import com.isl.gbm.rbibond.dto.reports.*;
//import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RbiBondReportsService {

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

    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException;

    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO);

    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO);

    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto);
    public ResponseDataDTO getRegBondLedgeAccDownload(BondLedgerAccDto bondLedgerAccDto);

    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO);
    public ResponseDataDTO getBondBalanceReportDownload(BondBalanceReportDTO bondBalanceReportDTO);

    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);
    public ResponseDataDTO getReportOnTransactionDetailsDownload(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO);

    ResponseDataDTO getBranchDetails();

    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO);

    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO);

    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO);

    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO);

    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO);

    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO);

    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO);

    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO);

    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO);
    public ResponseDataDTO getPreClosureRepaymentDownload(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO);

    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO);
    public ResponseDataDTO getStatementBrokerageDownload(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO);

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

    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO transferInOutDTO);
    public ResponseDataDTO getTransferInOutDownload(TransferInOutDTO transferInOutDTO);

    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException;

    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO);

    public ResponseDataDTO getModuleName();

    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO);

    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto);

    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO);

    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO);

//    public ResponseDataDTO getBondType(BondLedgerAccDto bondLedgerAccDto);

    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO);

    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO);
    public ResponseDataDTO getCallBackReportDownload(CallBackReportDTO callBackReportDTO);

    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO);
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitDownload(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO);

    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO);
    public ResponseDataDTO getTdsRemittanceFinconFileDetailsDownload(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO);

    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto);
    public ResponseDataDTO getBondInterestTrialRunEligibleAccountsDownload(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto);

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

    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO);

    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO);

    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO);

    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO);

    public  ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO);


    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO);

    public ResponseDataDTO getTdsDeductionTdsrateDownload(TdsDeductionDTO tdsDeductionDTO);

    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO);

    public ResponseDataDTO getPanAadharDetailsDownload(PanAadhaarDTO panAadhaarDTO);

    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO);

    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO);
    public ResponseDataDTO getTdsEligibilityDownload(TdsEligibilityDTO tdsEligibilityDTO);

    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto);

    public ResponseDataDTO getModeOfHoldingDownload(ModeOfHoldingReportDto modeOfHoldingReportDto);

    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO);

    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO);
    public ResponseDataDTO getDisablingDeactivatingDownload(DisablingDeactivatingDTO disablingDeactivatingDTO);
    public ResponseDataDTO getDashBoardReportForInwardOutwardDownload(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO);
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO DashBoardReportForInwardOutwardDTO);

    public ResponseDataDTO  getDetailsOfPdf( NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) throws IOException;

    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO);

    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto);

    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO);
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO)throws Exception;

    ResponseDataDTO getPanValidationReportDownload(PANValidationReportDTO panValidationReportDTO);

    ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO);

    ResponseDataDTO getPreMaturityMarkingReportDownload(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO);

    ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO);

//    public ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO dataPatchUploadRequestDTO);

    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO);


}
