package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface RbiBondReportsDAO {

    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO);

    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO);

    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto);

    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO);

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

    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO);

    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO);

    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO);

    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO);

    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException;

    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO);

    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO);

    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO);

    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO);

    public ResponseDataDTO getBranchDetails();

    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto);

    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO);

    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO);

    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO);

    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO);

    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO);

    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO);

    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO);

    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO);

    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO);

    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO);

    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO);

    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO);

    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO);

    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO);

    public ResponseDataDTO getSubsAmtDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getAccClosedDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getBlaAccNumDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getHolderTypeDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getMaturedAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getMinorAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getNriAccDetails(BondInquiryDto bondInquiryDto);

    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO transferInOutDTO);

    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException;

    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO);

    public ResponseDataDTO getModuleName();

    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO);

    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO getStatusEnquiry(RbiBondMetaDataRequestDto metaDataRequestDto);

    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO);

    public ResponseDataDTO  getDetailsOfPdf( NetBankingTransectionPdfDTO netBankingTransectionPdfDTO);

    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto);

    public ResponseDataDTO getSchemeSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto);

    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO);

    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO);

    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO);

    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO);

    ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO);

    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO);

    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto);

    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto);

    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO);

    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO);

    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO);

    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO);

    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO);
    public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO);

    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO);

    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO);

    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO);

    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto);

    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO);

    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO);

    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO);

    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO);

    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO);

    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO);

    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO);

    public  ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO);


    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO);

    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO);

    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO);

    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO);

    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto);

    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO);

    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO);

    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO);
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO);

    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto);

    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO);
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO);

    ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO);

    ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO);

//    ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO dataPatchUploadRequestDTO);

    ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO);


}