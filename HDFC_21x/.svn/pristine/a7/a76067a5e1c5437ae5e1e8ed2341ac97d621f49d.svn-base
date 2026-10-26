package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondReportsBO;
import com.isl.gbm.rbibond.dao.impl.RbiBondReportsDAOImpl;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.service.RbiBondReportsService;
import com.isl.gbm.rbibond.service.RecordLockService;
import com.isl.gbm.rbibond.util.NetBankingTransectionPdfGenerationHelper;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.rbibond.util.TextPart;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.attoparser.dom.DOMWriter.writeText;

@Service
@Component
public class RbiBondReportsServiceImpl implements RbiBondReportsService {

    private static final Logger log = LoggerFactory.getLogger(RbiBondReportsServiceImpl.class);

    private final RbiBondReportsBO rbiBondReportsBO;
    private final RecordLockService service;
    private final NetBankingTransectionPdfGenerationHelper netBankingTransectionPdfGenerationHelper;
    private final Sftp sftp;

    @Autowired
    public RbiBondReportsServiceImpl(RbiBondReportsBO rbiBondReportsBO,
                                     RecordLockService service,
                                     NetBankingTransectionPdfGenerationHelper netBankingTransectionPdfGenerationHelper,
                                     Sftp sftp) {
        this.rbiBondReportsBO = rbiBondReportsBO;
        this.service = service;
        this.netBankingTransectionPdfGenerationHelper = netBankingTransectionPdfGenerationHelper;
        this.sftp = sftp;
    }

    @Value("${sftp.certificate.of.printing}")
    private String remoteLocalDir;

    @Value("${pan.validation.stub}")
    private String stubForValidation;

    @Value("${sftp.client.host}")
    private String host;


    @Value("${sftp.client.port}")
    private String port;

    @Value("${sftp.client.username}")
    private String userName;


    @Value("${sftp.client.password}")
    private String password;

    String hostKey="StrictHostKeyChecking";








    @Override
    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO) {
        return rbiBondReportsBO.getBondAckNominiReport(bondAckNominationDTO);
    }

    @Override
    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO) {
        return rbiBondReportsBO.getminorToMajorIntimationRepValues(rbiBondMinorToMajorIntimateRepDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto) {
        return rbiBondReportsBO.getBondInterestPayMisReportValues(rbiBondInterestPaymentMisReportDto);
    }

    @Override
    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return rbiBondReportsBO.getBondNominiRegsReport(bondNominiRegsReportDTO);
    }

    @Override
    public ResponseDataDTO getBondNominationDownload(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return rbiBondReportsBO.getBondNominationDownload(bondNominiRegsReportDTO);
    }

    @Override
    public ResponseDataDTO getNoticeCancelReport(BondNoticeCancelReportDTO bondNoticeCancelReportDTO) {
        return rbiBondReportsBO.getNoticeCancelReport(bondNoticeCancelReportDTO);
    }

    @Override
    public ResponseDataDTO getAckCancelNomineReport(BondCancelNomineeReportDTO bondCancelNomineeReportDTO) {
        return rbiBondReportsBO.getAckCancelNomineReport(bondCancelNomineeReportDTO);
    }

    @Override
    public ResponseDataDTO getCertificateInvtReport(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsBO.getCertificateInvtReport(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getPanValidationBondSchemeReport(PANValidationReportDTO panValidationReportDTO) {
        return rbiBondReportsBO.getPanValidationBondSchemeReport(panValidationReportDTO);
    }

    @Override
    public ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO) {
        return rbiBondReportsBO.getTaxWaiverDocumentsReport(taxWaiverDocumentsReportDTO);
    }

//    @Override
//    public ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO dataPatchUploadRequestDTO) {
//        return rbiBondReportsBO.uploadDataPatch(file, dataPatchUploadRequestDTO);
//    }

    @Override
    public ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return rbiBondReportsBO.getPreMaturityMarkingReport(preMaturityMarkingReportDTO);
    }



    @Override
    public ResponseDataDTO getPanNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsBO.getPanNo(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getBlaAcNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsBO.getBlaAcNo(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getSubscripID(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsBO.getSubscripID(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getCertificateOfHolding(BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO) {
        return rbiBondReportsBO.getCertificateOfHolding(bondCertificateInvtHoldReportDTO);
    }

    @Override
    public ResponseDataDTO getTranSameBankReport(BondTransSameBankReportDTO bondTransSameBankReportDTO) {
        return rbiBondReportsBO.getTranSameBankReport(bondTransSameBankReportDTO);
    }

    @Override
    public ResponseDataDTO getUnverifiedReportRbi(UnverifiedReportDto unverifiedReportDto) {
        return rbiBondReportsBO.getUnverifiedReportRbi(unverifiedReportDto);
    }

    @Override
    public ResponseDataDTO getListOfBondReport(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        return rbiBondReportsBO.getListOfBondReport(listOfBondInvestmentsDTO);
    }

    @Override
    public ResponseDataDTO getListOfBondReportDownload(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        return rbiBondReportsBO.getListOfBondReportDownload(listOfBondInvestmentsDTO);
    }

    @Override
    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {
        return rbiBondReportsBO.getBlaStatementReport(blaStatementForBondHoldersDTO);
    }

    @Override
    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO) {
        return rbiBondReportsBO.bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) {
        return rbiBondReportsBO.getTdsDeductReport(bondTdsDeductionReportDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsBO.getTdsDeductionReportCb(bondTdsCollDeductionReportDTO);
    }


    @Override
    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {
        return rbiBondReportsBO.getTdsDeductDetails(bondTdsDeductionReportDTO);

    }

    @Override
    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) {
        return rbiBondReportsBO.financialTransactionInquiry(financialTransactionInquiryDTO);
    }

    @Override
    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO) {
        return rbiBondReportsBO.getBlaStatusReport(blaStatusReportDTO);
    }


    @Override
    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto) {
        return rbiBondReportsBO.getBondLedgerAccDet(bondLedgerAccDto);
    }

    @Override
    public ResponseDataDTO getRegBondLedgeAccDownload(BondLedgerAccDto bondLedgerAccDto) {
        return rbiBondReportsBO.getRegBondLedgeAccDownload(bondLedgerAccDto);
    }

    @Override
    public ResponseDataDTO getBranchDetails() {
        return rbiBondReportsBO.getBranchDetails();
    }

    @Override
    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsBO.getReportTransactionSubcriptionDeatails(reportTransactionDetailsDTO);
    }

    @Override
    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO) {
        return rbiBondReportsBO.getBondBalanceReport(bondBalanceReportDTO);
    }

    @Override
    public ResponseDataDTO getBondBalanceReportDownload(BondBalanceReportDTO bondBalanceReportDTO) {
        return rbiBondReportsBO.getBondBalanceReportDownload(bondBalanceReportDTO);
    }

    @Override
    public ResponseDataDTO getPanValidationReportDownload(PANValidationReportDTO panValidationReportDTO) {
        return rbiBondReportsBO.getPanValidationReportDownload(panValidationReportDTO);
    }

    @Override
    public ResponseDataDTO getPreMaturityMarkingReportDownload(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return rbiBondReportsBO.getPreMaturityMarkingReportDownload(preMaturityMarkingReportDTO);
    }

    @Override
    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsBO.getReportOnTransactionDetails(reportTransactionDetailsDTO);
    }

    @Override
    public ResponseDataDTO getReportOnTransactionDetailsDownload(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsBO.getReportOnTransactionDetailsDownload(reportTransactionDetailsDTO);
    }

    @Override
    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO) {
        return rbiBondReportsBO.getBondInvestmentDueNoticeReport(bondInvestmentDueDateNoticeDTO);
    }

    @Override
    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) {
        return rbiBondReportsBO.getInterestRunCreditReport(bondInterestRunCreditDTO);
    }

    @Override
    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) {
        return rbiBondReportsBO.getInterestTrailRunReportPayment(interestTrailRunPaymentDTO);
    }

    @Override
    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) {
        return rbiBondReportsBO.getInterRunCrdtCollBranReport(bondInterRunCrdtCollBranDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsBO.getBondInterestCalculatedReport(bondInterestCalculatedDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsBO.getBondInterestCalculatedReportDetails(bondInterestCalculatedDTO);
    }

    @Override
    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) {
        return rbiBondReportsBO.getInterPayPendingReport(bondInterestPayPendingDTO);
    }

    @Override
    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO) {
        return rbiBondReportsBO.getAccHolderInquiryReport(bondAccHolderInquiryReportDTO);
    }

    @Override
    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO) {
        return rbiBondReportsBO.getBlaNameSearchReport(bondBlaNameSearchReportDTO);
    }

    @Override

    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO) {
        return rbiBondReportsBO.getBondCollectingdReportDetails(bondInteresDTO);
    }

    @Override

    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        return rbiBondReportsBO.getPreClosureRepaymentReport(bondPrecloserRepayReportDTO);
    }

    @Override
    public ResponseDataDTO getPreClosureRepaymentDownload(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        return rbiBondReportsBO.getPreClosureRepaymentDownload(bondPrecloserRepayReportDTO);
    }

    @Override
    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        return rbiBondReportsBO.getStatementBrokerageDetails(statementOfBrokeragePaidDTO);
    }

    @Override
    public ResponseDataDTO getStatementBrokerageDownload(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        return rbiBondReportsBO.getStatementBrokerageDownload(statementOfBrokeragePaidDTO);
    }

    @Override

    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsBO.getReportTorchBla(bondTdsCollDeductionReportDTO);
    }

    @Override
    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO) {
        return rbiBondReportsBO.getDailyRemittanceLcReport(bondDailyRemLcDTO);
    }

    @Override
    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO) {
        return rbiBondReportsBO.getSubsAccDetails(subscriptionAccountDTO);
    }

    @Override
    public ResponseDataDTO getSubsAccDetailsDownload(SubscriptionAccountDTO subscriptionAccountDTO) {
        return rbiBondReportsBO.getSubsAccDetailsDownload(subscriptionAccountDTO);
    }

    @Override
    public ResponseDataDTO getSubsAmtDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getSubsAmtDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getAccClosedDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getAccClosedDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getBlaAccNumDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getBlaAccNumDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getHolderTypeDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getHolderTypeDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getMaturedAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getMaturedAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getMinorAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getMinorAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getNriAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsBO.getNriAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO transferInOutDTO) {
        return rbiBondReportsBO.getTransferInOutDetails(transferInOutDTO);
    }

    @Override
    public ResponseDataDTO getTransferInOutDownload(TransferInOutDTO transferInOutDTO) {
        return rbiBondReportsBO.getTransferInOutDownload(transferInOutDTO);
    }

    @Override
    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return rbiBondReportsBO.getBondRepaymentDetails(bondRepaymentReportDto);
    }

    @Override
    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return rbiBondReportsBO.getBodEodStatusInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    public ResponseDataDTO getModuleName() {
        return rbiBondReportsBO.getModuleName();
    }

    @Override
    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) {
        return rbiBondReportsBO.getInterPayPendingCollReport(bondInterestPayPendingCollDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsBO.getBrokerageInvoiceBondScheme(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsBO.getBrokerageAgencyCode(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsBO.generateBrokerRes(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return rbiBondReportsBO.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        if (null != bondLedgerAccountMaintenanceDTO && null != bondLedgerAccountMaintenanceDTO.getPanDet() && !bondLedgerAccountMaintenanceDTO.getPanDet().isEmpty()) {
            for (String pan : bondLedgerAccountMaintenanceDTO.getPanDet()) {
                service.unlockByPan(pan);
            }
        }
        return rbiBondReportsBO.unlockBlaSubmit(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto) {
        return rbiBondReportsBO.getSuccessFailureDetails(successFailureReportDto);
    }

    @Override
    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO) {
        return rbiBondReportsBO.getPendingBrokeragePaymentClaimDetails(pendingBrokeragePaymentClaimStatusDTO);
    }

    @Override
    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO) {
        return rbiBondReportsBO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);
    }

    //    @Override
    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return rbiBondReportsBO.getTdsRemittanceFinconFileDetails(tdsRemittanceFinconFileDTO);
    }

    @Override
    public ResponseDataDTO getTdsRemittanceFinconFileDetailsDownload(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        return rbiBondReportsBO.getTdsRemittanceFinconFileDetailsDownload(tdsRemittanceFinconFileDTO);

    }

//    @Override
//    public ResponseDataDTO getBondType(BondLedgerAccDto bondLedgerAccDto) {
//        return rbiBondReportsBO.getBondType(bondLedgerAccDto);
//    }

    @Override
    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccounts) {
        return rbiBondReportsBO.getBondInterestTrialRunEligibleAccounts(bondInterestTrialRunEligibleAccounts);
    }

    @Override
    public ResponseDataDTO getBondInterestTrialRunEligibleAccountsDownload(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccounts) {
        return rbiBondReportsBO.getBondInterestTrialRunEligibleAccountsDownload(bondInterestTrialRunEligibleAccounts);
    }

    @Override
    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto) {
        return rbiBondReportsBO.getBlaModificationReport(blaModificationReportDto);
    }

    @Override
    public ResponseDataDTO getBlaModificationReportDownload(BlaModificationReportDto blaModificationReportDto) {
        return rbiBondReportsBO.getBlaModificationReportDownload(blaModificationReportDto);
    }

    @Override
    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        return rbiBondReportsBO.getInterestPaymentReport(interestPaymentToOtherBankDTO);
    }

    @Override
    public ResponseDataDTO getinterestPaymentDetailsDownload(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        return rbiBondReportsBO.getinterestPaymentDetailsDownload(interestPaymentToOtherBankDTO);

    }

    @Override
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return rbiBondReportsBO.getTDSExemptionSubmitNonSubmitReport(tdsExemptionSubmitNonSubmitReportDTO);
    }

    @Override
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitDownload(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return rbiBondReportsBO.getTDSExemptionSubmitNonSubmitDownload(tdsExemptionSubmitNonSubmitReportDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO) {
        return rbiBondReportsBO.getBrokerageInvoiceEmailBodyLetterDetails(brokerageInvoiceEmailBodyDTO);
    }

    @Override
    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO) {
        return rbiBondReportsBO.getCallBackReportDetails(callBackReportDTO);
    }

    @Override
    public ResponseDataDTO getCallBackReportDownload(CallBackReportDTO callBackReportDTO) {
        return rbiBondReportsBO.getCallBackReportDownload(callBackReportDTO);
    }

    @Override
    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO) {
        return rbiBondReportsBO.getEtdsDetails(etdsDTO);
    }

    @Override
    public ResponseDataDTO getEtdsDetailsDownload(EtdsDTO etdsDTO) {
        return rbiBondReportsBO.getEtdsDetailsDownload(etdsDTO);
    }

    @Override
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return rbiBondReportsBO.getSubsAmtBLAMaturedAccClosed(subsAmtBLAMaturedAccClosedDTO);
    }

    @Override
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosedDownload(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return rbiBondReportsBO.getSubsAmtBLAMaturedAccClosedDownload(subsAmtBLAMaturedAccClosedDTO);
    }

    @Override
    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return rbiBondReportsBO.getTdsSummaryForTaxAudit(tdsSummaryForTaxAuditDTO);
    }

    @Override
    public ResponseDataDTO getReportDetailsDownload(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return rbiBondReportsBO.getReportDetailsDownload(tdsSummaryForTaxAuditDTO);
    }

    @Override
    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return rbiBondReportsBO.getNomineeReport(nomineeContinuationReportDTO);
    }

    @Override
    public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return rbiBondReportsBO.getNomineeReportDownload(nomineeContinuationReportDTO);
    }

    @Override
    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO) {
        return rbiBondReportsBO.getReportCancellationSubsCatCbt(reportCancellationSubsCatCbtDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return rbiBondReportsBO.getBrokerageClaimMonthly(brokerageClaimMonthlyDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageClaimMonthlyDownload(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return rbiBondReportsBO.getBrokerageClaimMonthlyDownload(brokerageClaimMonthlyDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return rbiBondReportsBO.getBondInterestNotCredited(bondInterestNotCreditedDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestNotCreditedDownload(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return rbiBondReportsBO.getBondInterestNotCreditedDownload(bondInterestNotCreditedDTO);
    }

    @Override
    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto) {
        return rbiBondReportsBO.getBondRepaymentReport(bondRepaymentReportDto);
    }

    @Override
    public ResponseDataDTO getBondRepaymentReportDownload(BondRepaymentReportDto bondRepaymentReportDto) {
        return rbiBondReportsBO.getBondRepaymentReportDownload(bondRepaymentReportDto);
    }

    @Override
    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO) {
        return rbiBondReportsBO.getMinorAccountDetails(minorAccountDetailsDTO);
    }

    @Override
    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO) {
        return rbiBondReportsBO.getBLASubscriptionNominationDetails(blaSubscriptionNominationDTO);
    }

    @Override
    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO) {
        return rbiBondReportsBO.getAcknowledgement(acknowledgementDTO);
    }

    @Override
    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO) {
        return rbiBondReportsBO.getAcknowledgementconfirmation(acknowledgementconfirmationDTO);
    }

    @Override
    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO) {
        return rbiBondReportsBO.getFreezeUnfreezeDetails(freezeUnFreezeDTO);
    }

    @Override
    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO) {
        return rbiBondReportsBO.getReportOnUnverifiedDetails(reportOnUnverifiedDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO) {
        return rbiBondReportsBO.getTdsDeductionTdsrate(tdsDeductionDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductionTdsrateDownload(TdsDeductionDTO tdsDeductionDTO) {
        return rbiBondReportsBO.getTdsDeductionTdsrateDownload(tdsDeductionDTO);
    }

    @Override
    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO) {
        return rbiBondReportsBO.getPreIntimationAdviceNoticeDetails(preIntimationAdviceNoticeDTO);
    }

    @Override
    public ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO) {
        return rbiBondReportsBO.getPostIntimationAdviceNoticeDetails(postIntimationAdviceNoticeDTO);
    }

    @Override
    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO) {
        return rbiBondReportsBO.getPanAadharDetails(panAadhaarDTO);
    }

    @Override
    public ResponseDataDTO getPanAadharDetailsDownload(PanAadhaarDTO panAadhaarDTO) {
        return rbiBondReportsBO.getPanAadharDetailsDownload(panAadhaarDTO);
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO) {
        return rbiBondReportsBO.getHalfYearlyInterestAdvice(halfYearlyInterestAdviceDTO);
    }

    @Override
    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return rbiBondReportsBO.getModeOfHoldingChangeDetails(modeOfHoldingReportDto);
    }

    @Override
    public ResponseDataDTO getModeOfHoldingDownload(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return rbiBondReportsBO.getModeOfHoldingDownload(modeOfHoldingReportDto);
    }

    @Override
    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO) {
        return rbiBondReportsBO.getBrokerEnrolledDetails(brokerEnrolledDTO);
    }

    @Override
    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto) {
        return rbiBondReportsBO.getFutureTdsDetails(futureTdsDto);
    }

    @Override
    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO) {
        return rbiBondReportsBO.getBondTransferIn(bondTransferInDTO);
    }

    @Override
    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return rbiBondReportsBO.getDisablingDeactivating(disablingDeactivatingDTO);
    }

    @Override
    public ResponseDataDTO getDisablingDeactivatingDownload(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return rbiBondReportsBO.getDisablingDeactivatingDownload(disablingDeactivatingDTO);
    }

    @Override
    public ResponseDataDTO getDashBoardReportForInwardOutwardDownload(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO) {
        return rbiBondReportsBO.getDashBoardReportForInwardOutwardDownload(dashBoardReportForInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO) {
        return rbiBondReportsBO.getTdsEligibility(tdsEligibilityDTO);
    }

    @Override
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO DashBoardReportForInwardOutwardDTO) {
        return rbiBondReportsBO.getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getTdsEligibilityDownload(TdsEligibilityDTO tdsEligibilityDTO) {
        return rbiBondReportsBO.getTdsEligibilityDownload(tdsEligibilityDTO);
    }

    byte[] zipData;

    @Override
    public ResponseDataDTO getDetailsOfPdf(NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) throws IOException {

        if (!netBankingTransectionPdfDTO.getFlag().equalsIgnoreCase("Z")) {
            ResponseDataDTO ResponseDataDTO2 = rbiBondReportsBO.getDetailsOfPdf(netBankingTransectionPdfDTO);
            return ResponseDataDTO2;
        }


        ResponseDataDTO ResponseDataDTO2 = rbiBondReportsBO.getDetailsOfPdf(netBankingTransectionPdfDTO);

        if (!ResponseDataDTO2.getMessage().equalsIgnoreCase("SUCCESS")) {
            return ResponseDataDTO2;
        }


        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(netBankingTransectionPdfGenerationHelper.generatePdfFile(ResponseDataDTO2)));
        fileResponseDto.setFileName("Net Banking Transaction for all holders - Report .pdf");
        responseDataDTO.setResult(fileResponseDto);
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        return responseDataDTO;


    }


    @Override
    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO) {
        return rbiBondReportsBO.getJointTypeDetails(jointTypeThreeToTwoReportDTO);
    }

    @Override
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO) throws Exception {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        if (certificatePrintingDTO.getFlag().equalsIgnoreCase("C") || certificatePrintingDTO.getFlag().equalsIgnoreCase("T")) {
            return rbiBondReportsBO.getCpTotalDetails(certificatePrintingDTO);
        } else if (certificatePrintingDTO.getFlag().equalsIgnoreCase("D") || certificatePrintingDTO.getFlag().equalsIgnoreCase("S")) {


            String sftpDownlo = certificatePrintingDTO.getFlag();

            certificatePrintingDTO.setFlag("D");
            ResponseDataDTO responseDataDTO1 = rbiBondReportsBO.getCpTotalDetails(certificatePrintingDTO);

            if (responseDataDTO1.getMessage() != null && responseDataDTO1.getMessage().equalsIgnoreCase("SUCCESS")) {

                FileResponseDto fileResponseDto = new FileResponseDto();

                String fileName = certificatePrintingDTO.getValueDateFormat() + ".zip";


                if (sftpDownlo.equalsIgnoreCase("D")) {

                    try {

                        byte[] zipData = RbiBondReportsServiceImpl.generateZip(responseDataDTO1, certificatePrintingDTO.getSchemeName());

                        fileResponseDto.setFileData(Base64.getEncoder().encodeToString(zipData));

                        fileResponseDto.setFileName(fileName);

                        responseDataDTO.setResult(fileResponseDto);

                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);

                        log.info("ZIP File Generated Successfully");

                    } catch (Exception e) {

                        e.printStackTrace();
                        responseDataDTO.setMessage(CommonConstants.FAILURE_CONST);
                        log.error("Failed to generate ZIP file");

                    }
                    return responseDataDTO;

                }

                if (sftpDownlo.equalsIgnoreCase("S")) {

                    String validFlag;

                    byte[] zipBytes = RbiBondReportsServiceImpl.generateZip(responseDataDTO1, certificatePrintingDTO.getSchemeName());
//                  ✅ Upload to SFTP

                    if (stubForValidation != null && stubForValidation.equalsIgnoreCase("YES")) {
                        validFlag = this.uploadToLocalSftp(zipBytes, fileName);
                    }

                    else {
                         validFlag = this.uploadToSftp(zipBytes, fileName);
                    }


                    if (validFlag != null && validFlag.equalsIgnoreCase("S"))
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    if (validFlag != null && validFlag.equalsIgnoreCase("F"))
                        responseDataDTO.setMessage(CommonConstants.FAILURE_CONST);
                    return responseDataDTO;

                } else {
                    responseDataDTO.setMessage(CommonConstants.FAILURE_CONST);
                    return responseDataDTO;
                }

            } else {
                responseDataDTO.setMessage(responseDataDTO1.getMessage());
                return responseDataDTO;

            }

        }
        else {
            responseDataDTO.setMessage(CommonConstants.FAILURE_CONST);
            return responseDataDTO;
        }

    }

    @Override
    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO) {
        return rbiBondReportsBO.getHufDeathMarkingDetails(hufDeathMarkingReportDTO);
    }

    public static byte[] generateZip(ResponseDataDTO responseDataDTO,String schName) throws Exception {



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        try {

            List<CertificatePrintingDTO> cohDetList = null;
            List<CertificatePrintingDTO> ackDetList = null;
            List<CertificatePrintingDTO> mailFDetList = null;
            List<CertificatePrintingDTO> mailNFDetList = null;
            Map<String, List<CertificatePrintingDTO>> groupedByBlaNo = new HashMap<>();
            Map<String, List<CertificatePrintingDTO>> groupedByAckBlaNo = new HashMap<>();


            if (responseDataDTO != null && responseDataDTO.getResult() != null) {

                Map<String, List<CertificatePrintingDTO>> resultMap =
                        (Map<String, List<CertificatePrintingDTO>>) responseDataDTO.getResult();

                if (resultMap != null && !resultMap.isEmpty()) {
                    cohDetList = resultMap.get("cohDet");
                    ackDetList = resultMap.get("ackDet");
                    mailFDetList = resultMap.get("mailfDet");
                    mailNFDetList = resultMap.get("mailNfDet");


                }


            }

            if (cohDetList != null && !cohDetList.isEmpty()) {
                groupedByBlaNo = cohDetList.stream()
                        .collect(Collectors.groupingBy(CertificatePrintingDTO::getBlaNo));
            }

            if (ackDetList != null && !ackDetList.isEmpty()) {
                groupedByAckBlaNo = ackDetList.stream()
                        .collect(Collectors.groupingBy(CertificatePrintingDTO::getBlaNo));
            }


            if (groupedByAckBlaNo != null && !groupedByAckBlaNo.isEmpty()) {

                for (Map.Entry<String, List<CertificatePrintingDTO>> entryAck : groupedByAckBlaNo.entrySet()) {

                    String blaNo = entryAck.getKey();
                    List<CertificatePrintingDTO> recordsAck = entryAck.getValue();

                    if (recordsAck == null || recordsAck.isEmpty()) {
                        continue;
                    }


                    byte[] ackPdf = RbiBondReportsServiceImpl.generateAcknowledgement(recordsAck, schName);

                    if (ackPdf != null) {
                      //  addFile(zos, blaNo + "/acknowledgement_letter.pdf", ackPdf);
                        addFile(zos, blaNo + "/" + blaNo + "_Letter.pdf", ackPdf);
                    }
                }
            }


            if (groupedByBlaNo != null && !groupedByBlaNo.isEmpty()) {

                for (Map.Entry<String, List<CertificatePrintingDTO>> entry : groupedByBlaNo.entrySet()) {

                    String blaNo = entry.getKey();
                    List<CertificatePrintingDTO> records = entry.getValue();

                    if (records == null || records.isEmpty()) {
                        continue;
                    }

                    // Generate PDFs
                    byte[] certPdf = RbiBondReportsServiceImpl.generateExactCertificate(records, schName);

                    if (certPdf != null) {
                    //    addFile(zos, blaNo + "/certificate_of_holding.pdf", certPdf);
                        addFile(zos, blaNo + "/" + blaNo + ".pdf", certPdf);

                    }
                }
            }



                addFile(zos, "Control File.txt", controlFileMailFound(mailFDetList));

                addFile(zos, "Control File MailNotFound.txt", controlFileMailNotFound(mailNFDetList));


        }
        catch(Exception e) {
            e.printStackTrace();
        }

      finally {
            zos.close();
        }
        return baos.toByteArray();
    }

    private static void addFile(ZipOutputStream zos, String path, byte[] data) throws IOException {
        ZipEntry entry = new ZipEntry(path);
        zos.putNextEntry(entry);
        zos.write(data);
        zos.closeEntry();
    }

    public static byte[] generateExactCertificate(List<CertificatePrintingDTO> list, String schemeName) throws Exception {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PDDocument document = new PDDocument();

    PDPage page = new PDPage(PDRectangle.A4);
    document.addPage(page);

    PDPageContentStream content = new PDPageContentStream(document, page);

    float margin = 40;
    float y = 800;

    CertificatePrintingDTO x = list.get(0);

    InputStream is = RbiBondReportsServiceImpl.class.getClassLoader()
            .getResourceAsStream("static/HDFC-Logo-new.png");

    PDImageXObject logo = PDImageXObject.createFromByteArray(document, is.readAllBytes(), "logo");

        float fontSize        = CERT_FONT_BODY;
        float fontSizeSmall   = CERT_FONT_SMALL;
        float fontSizeHeading = CERT_FONT_HEADING;

        float pageWidthh = PDRectangle.A4.getWidth();
        float pageCenterX = pageWidthh / 2;

// Pass a larger right-boundary coordinate (e.g., 530 instead of 420)
// so it has more room to grow towards the left without crowding the center.
        writeTextRightAligned(content, safe(x.getWboOffice()), 560, y, 11, true);
        y = drawCertificateHeader(content, logo, x, schemeName, margin);

        writeTextt(content, "To,", margin, y, fontSizeSmall, PDType1Font.TIMES_ROMAN);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String date = LocalDate.now().format(formatter);

        writeTextt(content, "Date: ", 420, y, fontSizeSmall, PDType1Font.TIMES_BOLD);
        float dateLabelWidth = PDType1Font.TIMES_BOLD.getStringWidth("Date: ") / 1000f * fontSizeSmall;
        writeTextt(content, date, 420 + dateLabelWidth, y, fontSizeSmall, PDType1Font.TIMES_BOLD);

        String cityLine = (safe(x.getCity() + ", " + safe(x.getPinCode()))).trim();
        String stateLine = (safe(x.getState()) + " " + "INDIA ").trim();

        if (cityLine.startsWith(",")) {
            cityLine = cityLine.substring(1).trim();
        }

        String[] addressLines = {
                safe(x.getHolderName()),
                x.getAddress() != null && !x.getAddress().trim().isEmpty() ? safe(x.getAddress()) + "," : safe(x.getAddress()),
                /*x.getAddressTwo() != null && !x.getAddressTwo().trim().isEmpty() ? safe(x.getAddressTwo()) + "," : safe(x.getAddressTwo()),
                x.getAddressThree() != null && !x.getAddressThree().trim().isEmpty() ? safe(x.getAddressThree()) + "," : safe(x.getAddressThree()),
               */
                (x.getAddressTwo() != null && !x.getAddressTwo().trim().isEmpty() ? safe(x.getAddressTwo()) + ", " : "") +
                        (x.getAddressThree() != null && !x.getAddressThree().trim().isEmpty() ? safe(x.getAddressThree()) + "," : ""),
                cityLine,
                stateLine,

        };

        y -= 18;
        boolean firstAddressLine = true;

        for (String addressLine : addressLines) {
            if (addressLine.trim().isEmpty()) {
                continue;
            }

            if (!firstAddressLine) {
                y -= 16;
            }

            writeTextt(content, addressLine, margin, y, fontSizeSmall, PDType1Font.TIMES_ROMAN);
            firstAddressLine = false;
        }

        y -= 26;

        PDFont normalFont = PDType1Font.TIMES_ROMAN;
        PDFont boldFont = PDType1Font.TIMES_BOLD;

        float pageWidth = PDRectangle.A4.getWidth();
        float usableWidth = pageWidth - (2 * margin);

        List<TextPart> parts = new ArrayList<>();
        parts.add(new TextPart("We hereby certify that Shri/Smt ", normalFont, fontSize, false));
        parts.add(new TextPart(safe(x.getHolderName()), boldFont, fontSize, true));
        parts.add(new TextPart(" / Joint holder is/are the holder(s) of ", normalFont, fontSize, false));
        parts.add(new TextPart(toTitleCase(safe(schemeName)), boldFont, fontSize, false));
        parts.add(new TextPart(" held in the Bond Ledger Account No. ", normalFont, fontSize, false));
        parts.add(new TextPart(safe(x.getBlaNo()), boldFont, fontSize, true));
        parts.add(new TextPart(" with ", normalFont, fontSize, false));
        parts.add(new TextPart(safe(x.getWboOffice()).toUpperCase(Locale.ENGLISH), boldFont, fontSize, true));
        parts.add(new TextPart(" as per details given below: ", normalFont, fontSize, false));

        y = writeStyledParagraph(content, parts, margin, y, usableWidth);
        y -= (fontSize * 1.3f);

        String descc = "Interest on the bonds is payable semi-annually with resetting of the coupon rate every " +
                formatDayMonth(x.getIntPaymentDate1()) + " & " +
                formatDayMonth(x.getIntPaymentDate2()) + ". " +
                        "This is issued subject to the terms and conditions laid down in Government of India Notification No. " +
                safe(x.getGovtNotNo()) + " dated June 26, 2020.";

        y = writeParagraph(content, descc, margin, y, usableWidth, normalFont, fontSize);
        y -= 10;

        String heading = "Details of Bonds held in the Bond Ledger Account";
        PDFont headingFont = PDType1Font.TIMES_BOLD;
        float fontSizee = fontSizeHeading;
        float xx = pageCenterX - ((headingFont.getStringWidth(heading) / 1000f * fontSizee) / 2);

        writeTextt(content, heading, xx, y, fontSizee, headingFont);

        float textWidth = headingFont.getStringWidth(heading) / 1000f * fontSizee;

        content.setLineWidth(1f);
        content.moveTo(xx, y - 2);
        content.lineTo(xx + textWidth, y - 2);
        content.stroke();

        y -= 25;

        // --- TABLE CONFIGURATION UPDATED (10 COLUMNS) ---
        float[] colWidths = CERTIFICATE_BONDS_TABLE_COL_WIDTHS;

        // Adjusted alignments for 10 columns: Certificate No removed
        boolean[] rightAligned = {false, false, true, false, true, false, false, true, true, false};

    float tableWidth = 0;
    for (float w : colWidths) tableWidth += w;

    float tableStartY = y;

    y = drawMergedHeader(content, margin, y);
        String[] rowTotall = {"1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10."};
        y = drawTableRow(content, rowTotall, colWidths, margin, y);

        int sr = 1;

    double invesAmtTotal = 0.0;
    double transferAmtTotal = 0.0;
    double repaymentAmtTotal = 0.0;
    double balanceAmtTotal = 0.0;

        for (CertificatePrintingDTO c : list) {
            if (c.getInvestAmount() != null && !c.getInvestAmount().isEmpty()) {
                invesAmtTotal += Double.parseDouble(c.getInvestAmount());
            }
            if (c.getTransferAmount() != null && !c.getTransferAmount().isEmpty()) {
                transferAmtTotal += Double.parseDouble(c.getTransferAmount());
            }
            if (c.getRepaymentAmount() != null && !c.getRepaymentAmount().isEmpty()) {
                repaymentAmtTotal += Double.parseDouble(c.getRepaymentAmount());
            }
            if (c.getBalance() != null && !c.getBalance().isEmpty()) {
                balanceAmtTotal += Double.parseDouble(c.getBalance());
            }
        }

        for (CertificatePrintingDTO c : list) {

        if (y < 120) {
            content.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);

                content = new PDPageContentStream(document, page);
                y = drawCertificateHeader(content, logo, x, schemeName, margin);

            tableStartY = y;
            y = drawMergedHeader(content, margin, y);
        }

        String[] row = {
                String.valueOf(sr++),
                    formatCertificateTableDate(c.getInvestDate()),
                format(c.getInvestAmount()),
                    formatDateMonth(c.getTransferDate()),
                format(c.getTransferAmount()),
                    formatDateMonth(c.getDueDate()),
                    formatDateMonth(c.getRepaymentDate()),
                format(c.getRepaymentAmount()),
                format(c.getBalance()),
                ""
        };

            y = drawTableRow(content, row, colWidths, margin, y, rightAligned);
        }
        String[] rowTotal = {
                "",
                "",
                (invesAmtTotal != 0) ? format(String.valueOf(invesAmtTotal)) : "",
                "",
                (transferAmtTotal != 0) ? format(String.valueOf(transferAmtTotal)) : "",
                "",
                "",
                (repaymentAmtTotal != 0) ? format(String.valueOf(repaymentAmtTotal)) : "",
                (balanceAmtTotal != 0) ? format(String.valueOf(balanceAmtTotal)) : "",
                ""
        };

        y = drawTableRow(content, rowTotal, colWidths, margin, y, rightAligned, PDType1Font.TIMES_BOLD);
        y -= (10 + (fontSize * 1.3f));

        if (y - ((fontSize * 1.3f) * 6) < margin) {
            content.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            content = new PDPageContentStream(document, page);
            y = drawCertificateHeader(content, logo, x, schemeName, margin);
        }

        String totalWordsPrefix = "Total Investment Amount ";
        String amountInWordsLabel = "(Amount In Words): ";
        String totalWordsValue = convertToWords(invesAmtTotal).toUpperCase();

        List<TextPart> totalParts = new ArrayList<>();
        totalParts.add(new TextPart(totalWordsPrefix, PDType1Font.TIMES_ROMAN, fontSize, false));
        totalParts.add(new TextPart(amountInWordsLabel, PDType1Font.TIMES_BOLD, fontSize, false));
        totalParts.add(new TextPart(totalWordsValue, PDType1Font.TIMES_BOLD, fontSize, false));

        y = writeStyledParagraph(content, totalParts, margin, y, usableWidth);

        y -= 20;

        String officeLabel = "Name of the Receiving Office: HDFC Bank Ltd, ";
        y = writeParagraph(content,
                officeLabel + safe(x.getWboOffice()),
                margin,
                y,
                usableWidth,
                PDType1Font.TIMES_ROMAN,
                fontSize
        );

    String branchCodes = list.stream()
            .map(c -> safe(c.getBondSubscriptionId()))
            .filter(code -> code != null && !code.isEmpty())
                .distinct()
                .collect(Collectors.joining(", "));
        y = writeParagraph(content,
                "Branch Code / Ref No: " + branchCodes,
                margin,
                y,
                usableWidth,
                PDType1Font.TIMES_ROMAN,
                fontSize
        );

        String label = "Mode of Operation: ";
        String value = safe(x.getModeOfHolding());
        y = writeWrappedLabelValue(content, label, value, margin, y, usableWidth, fontSize);

        String labell = "Applicant Holder(s): ";
        String valuee = "(1) " + safe(x.getHolderName())
                + (x.getSecondJointBlaName() != null && !x.getSecondJointBlaName().isEmpty()
                ? ", (2) " + x.getSecondJointBlaName()
                : "")
                + (x.getThirdJointBlaName() != null && !x.getThirdJointBlaName().isEmpty()
                ? ", (3) " + x.getThirdJointBlaName()
                : "");

        y = writeWrappedLabelValue(content, labell, valuee, margin, y, usableWidth, fontSize);

        y -= 8;
        if (y - 130 < margin) {
            content.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            content = new PDPageContentStream(document, page);
            y = drawCertificateHeader(content, logo, x, schemeName, margin);
        }

        ClassPathResource res1 = new ClassPathResource("static/nageswaranNewBg.png");
        ClassPathResource res2 = new ClassPathResource("static/kesavan.png");

        PDImageXObject sig1 = PDImageXObject.createFromByteArray(
                document,
                res1.getInputStream().readAllBytes(),
                "sig1"
        );

        PDImageXObject sig2 = PDImageXObject.createFromByteArray(
                document,
                res2.getInputStream().readAllBytes(),
                "sig2"
        );

        content.drawImage(sig2, margin, y - 50, 120, 65);
        content.drawImage(sig1, 400, y - 50, 120, 65);

    String nomineeIds = list.stream()
            .map(c -> safe(c.getNomineeID()))
            .filter(code -> code != null && !code.isEmpty())
                .distinct()
                .collect(Collectors.joining(", "));

        y -= 40 + (fontSizeSmall * 1.3f);

        y = writeParagraph(content,
                "Nomination Registered at Sr No: " + nomineeIds,
                margin,
                y,
                usableWidth,
                PDType1Font.TIMES_ROMAN,
                fontSizeSmall
        );

        y -= 10;
        if (y - ((fontSizeSmall * 1.3f) * 2) < margin) {
            content.close();
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            content = new PDPageContentStream(document, page);
            y = drawCertificateHeader(content, logo, x, schemeName, margin);
        }

        y = writeParagraph(content,
                "Note: This certificate is non-negotiable. It is a record of investment for the information of the holder and need not be surrendered, at the time of redemption.",
                margin, y, usableWidth, PDType1Font.TIMES_ROMAN, fontSizeSmall);

        y -= 3;

        content.close();

        float footerMargin = 40f;
        float footerUsableWidth = PDRectangle.A4.getWidth() - (2 * footerMargin);

        for (PDPage docPage : document.getPages()) {
            try (PDPageContentStream footerStream = new PDPageContentStream(
                    document, docPage, PDPageContentStream.AppendMode.APPEND, true, true)) {

                float footerY = 35f;

                footerStream.setLineWidth(0.5f);
                footerStream.moveTo(footerMargin, footerY + 15f);
                footerStream.lineTo(footerMargin + footerUsableWidth, footerY + 15f);
                footerStream.stroke();

                writeParagraph(footerStream,
                        "HDFC Bank Ltd,, CMS–GOI Bonds – 2nd Floor, GKS Technology Park, No. 94, SP Estate Bus Stand, Wavin Main Road, Mogappair, Chennai – 600058. Mail id : rrb.chennai@hdfc.bank.in",
                        footerMargin, footerY, footerUsableWidth, PDType1Font.TIMES_ROMAN, fontSizeSmall);
            }
        }

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    private static void writeTextRightAligned(PDPageContentStream content,
                                              String text,
                                              float rightX,
                                              float y,
                                              float fontSize,
                                              boolean isBold) throws IOException {
        if (text == null || text.trim().isEmpty()) {
            return;
        }

        PDFont font = isBold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN;

        // Calculate exact visual width of the string
        float textWidth = (font.getStringWidth(text) / 1000f) * fontSize;

        // Shift starting X position further to the left based on length and new rightX boundary
        float startX = rightX - textWidth;

        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(startX, y);
        content.showText(text);
        content.endText();
    }

    private static float writeStyledParagraph(
            PDPageContentStream content,
            List<TextPart> parts,
            float x,
            float y,
            float width) throws IOException {

        float currentX = x;
        float currentY = y;

//        float lineHeight = 13;

        // line-height 1.3 of the largest part, as in the print screen
        float maxFontSize = 0;
        for (TextPart part : parts) {
            if (part.fontSize > maxFontSize) {
                maxFontSize = part.fontSize;
            }
        }

        float lineHeight = maxFontSize * 1.3f;

        for (TextPart part : parts) {

//            String[] words = part.text.split("(?<= )|(?= )");

            // a word wider than the whole line is split, so it can never overflow the page
            List<String> words = new ArrayList<>();
            for (String w : safe(part.text).split("(?<= )|(?= )")) {
                words.addAll(splitToFit(w, width, part.font, part.fontSize));
            }

            for (String word : words) {

                float wordWidth =
                        part.font.getStringWidth(word)
                                / 1000f * part.fontSize;

                // If word doesn't fit, move to next line
                if (currentX + wordWidth > x + width && currentX > x) {
                    currentX = x;
                    currentY -= lineHeight;
                }

                // Write text
                content.beginText();
                content.setFont(part.font, part.fontSize);
                content.newLineAtOffset(currentX, currentY);
                content.showText(word);
                content.endText();

                // Underline only the actual text, not spaces
                if (part.underline && !word.trim().isEmpty()) {

                    float underlineY = currentY - 1.5f;

                    content.setLineWidth(0.5f);

                    content.moveTo(currentX, underlineY);
                    content.lineTo(
                            currentX + wordWidth,
                            underlineY
                    );

                    content.stroke();
                }

                currentX += wordWidth;
            }
        }

        return currentY;
    }
    private static void underline(PDPageContentStream content,
                           String text,
                           float x,
                           float y,
                           int fontSize) throws IOException {

        PDFont font = PDType1Font.TIMES_BOLD;

        float textWidth = font.getStringWidth(text) / 1000 * fontSize;

        content.moveTo(x, y - 2);
        content.lineTo(x + textWidth, y - 2);
        content.stroke();
    }



    private static void underlinee(PDPageContentStream content,
                           String text,
                           float x,
                           float y,
                           int fontSize,
                           PDFont font) throws IOException {

        float textWidth = font.getStringWidth(text) / 1000 * fontSize;

        content.moveTo(x, y - 2); // Position the underline slightly below the text
        content.lineTo(x + textWidth, y - 2);
        content.stroke();
    }


    private static void underlineee(
            PDPageContentStream content,
            String text,
            float x,
            float y,
            float fontSize,
            PDFont font) throws IOException {

        // Calculate the exact width of the text
        float textWidth = font.getStringWidth(text) / 1000f * fontSize;

        // Underline
        content.setLineWidth(1f);

        content.moveTo(x, y - 2);

        content.lineTo(x + textWidth, y - 2);

        content.stroke();
    }


    private static String safe(String val) {
        return val == null ? "" : val;
    }

    /**
     * Displays N/A wherever acknowledgement data is null or blank.
     */
    private static String valueOrNA(String val) {
        return val == null || val.trim().isEmpty() ? "N/A" : val.trim();
    }

    private static String acknowledgementFieldOrNA(String val) {
        return val == null || val.trim().isEmpty() ? "NA" : val.trim();
    }


    // ---- font sizes taken from rbibond_certificate_investment_hold_report print screen ----
    // print screen px * 0.80 (print zoom) * 0.75 (px to pt)
    private static final float CERT_FONT_BODY    = 13;   // 22px  - certifying paragraph / details block
    private static final float CERT_FONT_TITLE   = 16;   // 1.9em - Government of India / Certificate of Holding
    private static final float CERT_FONT_OFFICE  = 11;   // 1.3em - branch of the bank
    private static final float CERT_FONT_SMALL   = 12;   // 20px  - Annexure 1 / Form 'A' / To, / address / notes
    private static final float CERT_FONT_SCHEME  = 14;   // 1.6em - scheme name / Bond Ledger Account No
    private static final float CERT_FONT_HEADING = 15;   // 25px  - Details of Bonds held in the Bond Ledger Account

    private static final float CERT_PAGE_TOP = 800;

    // Details of Bonds table: wider Transfer Date (index 4); total width unchanged
    private static final float[] CERTIFICATE_BONDS_TABLE_COL_WIDTHS = new float[] {
            18f, 55f, 70f, 55f, 70f, 49f, 55f, 70f, 70f, 31.28f
    };

    // ---- acknowledgement letter page limits ----
    // the letterhead is drawn from this y, the same as the original first page
//    private static final float ACK_PAGE_TOP = 780;

    private static final float ACK_PAGE_TOP = 800;
    // minimal blank space kept at the bottom of each acknowledgement page
    private static final float ACK_BOTTOM_MARGIN = 35;
    private static final float ACK_SIGNATURE_IMAGE_HEIGHT = 72f;
    private static final float ACK_SIGNATURE_GAP_AFTER_IMAGE = 4f;
    // space used by GOI Bonds Operations and the email line after the signature
    private static final float ACK_SIGNATURE_FOOTER_HEIGHT = 52f;

    // height of the block after the nomination table through the email footer
//    private static final float ACK_CLOSING_HEIGHT = 360;

    private static final float ACK_CLOSING_BLOCK_HEIGHT = 300;

    private static final String[] ACK_NOMINATION_GUIDELINES = {
            "1.An OCI cannot be appointed as a nominee in FRSB Bonds.",
            "2.Only Resident Indians / NRI's are permitted to be a nominee in FRSB.",
            "3.In the event a Non-Resident Indian (NRI) nominee (at the time of investment) subsequently changes his/her citizenship status to OCI, the investor is required to change his nomination to an eligible individual, i.e., Resident Indian / NRI. In case, the investor did not change or cancel his/her nomination in his lifetime, the existing nomination would become null & void.",
            "4.If an OCI nominee is the sole legal heir of the investor, his/her claim for transfer of the GOI Bonds can be considered, provided, the bonds are held till maturity and the legal heir agrees to forego the interest from the date of transfer. The repatriation of maturity proceeds will be subject to extant FEMA guidelines"
    };



    private static float drawCertificateHeader(PDPageContentStream content,
                                               PDImageXObject logo,
                                               CertificatePrintingDTO x,
                                               String schemeName,
                                               float margin) throws IOException {

        float y = CERT_PAGE_TOP;

        float pageWidth = PDRectangle.A4.getWidth();
        float pageCenterX = pageWidth / 2;

        PDFont boldFont = PDType1Font.TIMES_BOLD;

        // logo sized as per print screen (250px X 50px)
        content.drawImage(logo, margin, y - 22, 150, 32);

        String text1 = "Government of India";
        String text2 = "Certificate of Holding";

        float text1X = pageCenterX
                - ((boldFont.getStringWidth(text1) / 1000f * CERT_FONT_TITLE) / 2);

        writeTextt(content, text1, text1X, y, CERT_FONT_TITLE, boldFont);
        underlineee(content, text1, text1X, y, CERT_FONT_TITLE, boldFont);

        float text2X = pageCenterX
                - ((boldFont.getStringWidth(text2) / 1000f * CERT_FONT_TITLE) / 2);

        writeTextt(content, text2, text2X, y - 20, CERT_FONT_TITLE, boldFont);
        underlineee(content, text2, text2X, y - 20, CERT_FONT_TITLE, boldFont);

//        // branch of the bank - right aligned & bold, as in the print screen
//        String wboOffice = safe(x.getWboOffice());
//        float wboWidth = boldFont.getStringWidth(wboOffice) / 1000f * CERT_FONT_OFFICE;
//
//        writeTextt(content, wboOffice, pageWidth - margin - wboWidth, y, CERT_FONT_OFFICE, boldFont);

        y -= 48;

        // ================= SUB HEADER =================
        String textAnnexure = "Annexure 1";

        writeTextt(content, textAnnexure, margin, y, CERT_FONT_SMALL, boldFont);
        underlineee(content, textAnnexure, margin, y, CERT_FONT_SMALL, boldFont);

        // scheme name - centered, as in the print screen
//        String schemeText = safe(schemeName);

        // first letter of every word upper case, the rest lower case,
        // same as toTitleCase() on the certificate of holding print screen
        String schemeText = toTitleCase(safe(schemeName));
        float schemeWidth = PDType1Font.TIMES_ROMAN.getStringWidth(schemeText) / 1000f * CERT_FONT_SCHEME;

        writeTextt(content, schemeText, pageCenterX - (schemeWidth / 2), y, CERT_FONT_SCHEME, PDType1Font.TIMES_ROMAN);

        // the blank line was above the Bond Ledger Account No row, it now goes below it
//        float formAndBlaY = y - 22 - (CERT_FONT_SCHEME * 1.3f);

        float formAndBlaY = y - 22;

        writeTextt(content, "Form 'A'", margin, formAndBlaY, CERT_FONT_SMALL, boldFont);

        // Bond Ledger Account No - centered & underlined, number in bold
        String blaLabel = "Bond Ledger Account No: ";
        String blaNo = safe(x.getBlaNo());

        float blaLabelWidth = PDType1Font.TIMES_ROMAN.getStringWidth(blaLabel) / 1000f * CERT_FONT_SCHEME;
        float blaNoWidth = boldFont.getStringWidth(blaNo) / 1000f * CERT_FONT_SCHEME;

        float blaX = pageCenterX - ((blaLabelWidth + blaNoWidth) / 2);

//        float blaY = y - 22;

        float blaY = formAndBlaY;

        writeTextt(content, blaLabel, blaX, blaY, CERT_FONT_SCHEME, PDType1Font.TIMES_ROMAN);
        writeTextt(content, blaNo, blaX + blaLabelWidth, blaY, CERT_FONT_SCHEME, boldFont);

        content.setLineWidth(0.7f);
        content.moveTo(blaX, blaY - 2);
        content.lineTo(blaX + blaLabelWidth + blaNoWidth, blaY - 2);
        content.stroke();

//        return y - 45;

        // usual gap below the header plus one blank line under the Bond Ledger Account No
//        return blaY - 23;

        return blaY - 23 - (CERT_FONT_SCHEME * 1.3f);
    }


    /**
     * Breaks a single word that is wider than the available width into chunks that fit,
     * so a long value can never be drawn past the right margin.
     */
    private static List<String> splitToFit(String word, float maxWidth,
                                           PDFont font, float fontSize) throws IOException {

        List<String> chunks = new ArrayList<>();

        if (word == null || word.isEmpty()) {
            chunks.add("");
            return chunks;
        }

        if (font.getStringWidth(word) / 1000f * fontSize <= maxWidth) {
            chunks.add(word);
            return chunks;
        }

        StringBuilder chunk = new StringBuilder();

        for (char ch : word.toCharArray()) {

            String test = chunk.toString() + ch;

            if (font.getStringWidth(test) / 1000f * fontSize > maxWidth && chunk.length() > 0) {
                chunks.add(chunk.toString());
                chunk = new StringBuilder(String.valueOf(ch));
            } else {
                chunk = new StringBuilder(test);
            }
        }

        if (chunk.length() > 0) {
            chunks.add(chunk.toString());
        }

        return chunks;
    }


    /**
     * Writes a bold label followed by a normal value, wrapping onto as many lines as needed.
     * Returns the y position of the next free line.
     */
    private static float writeWrappedLabelValue(PDPageContentStream content,
                                                String label, String value,
                                                float x, float y, float width,
                                                float fontSize) throws IOException {

        List<TextPart> parts = new ArrayList<>();

        parts.add(new TextPart(label, PDType1Font.TIMES_BOLD, fontSize, false));
        parts.add(new TextPart(safe(value), PDType1Font.TIMES_ROMAN, fontSize, false));

        return writeStyledParagraph(content, parts, x, y, width) - (fontSize * 1.3f);
    }


    private static float drawMergedHeader(PDPageContentStream content, float startX, float y) throws IOException {

        float h = 22;
        float[] w = CERTIFICATE_BONDS_TABLE_COL_WIDTHS;
        float x = startX;

        // 1. Sr No
        drawCell(content, x, y, w[0], h * 2, "Sr No");
        x += w[0];
        drawCell(content, x, y, w[1] + w[2], h, "Investment");
        drawCell(content, x, y - h, w[1], h, "Date");
        drawCell(content, x + w[1], y - h, w[2], h, "Amount");
        x += w[1] + w[2];
        drawCell(content, x, y, w[3] + w[4], h, "Transfer");
        drawCell(content, x, y - h, w[3], h, "Date");
        drawCell(content, x + w[3], y - h, w[4], h, "Amount");
        x += w[3] + w[4];
        drawCell(content, x, y, w[5], h * 2, "Due Date of Repayment");
        x += w[5];
        drawCell(content, x, y, w[6] + w[7], h, "Repayment");
        drawCell(content, x, y - h, w[6], h, "Date");
        drawCell(content, x + w[6], y - h, w[7], h, "Amount");
        x += w[6] + w[7];
        drawCell(content, x, y, w[8], h * 2, "Balance");
        x += w[8];
        drawCell(content, x, y, w[9], h * 2, "Initials under Bank's seal");

        return y - (h * 2);
    }
    // 1. Overload: 5 parameters (defaults to center alignment and TIMES_ROMAN font)
    private static float drawTableRow(PDPageContentStream content, String[] row,
                                      float[] colWidths, float startX, float y) throws IOException {
        return drawTableRow(content, row, colWidths, startX, y, null, PDType1Font.TIMES_ROMAN);
    }

    // 2. Overload: 6 parameters (allows alignment, defaults to TIMES_ROMAN font)
    private static float drawTableRow(PDPageContentStream content, String[] row,
                                      float[] colWidths, float startX, float y,
                                      boolean[] rightAligned) throws IOException {
        return drawTableRow(content, row, colWidths, startX, y, rightAligned, PDType1Font.TIMES_ROMAN);
    }

    // 3. Master Method: 7 parameters (accepts custom font like TIMES_BOLD)
    private static float drawTableRow(PDPageContentStream content, String[] row,
                                      float[] colWidths, float startX, float y,
                                      boolean[] rightAligned, PDFont font) throws IOException {

        float h = 18;
        float x = startX;
        float rowFontSize = 9;

        for (int i = 0; i < row.length; i++) {

            content.addRect(x, y - h, colWidths[i], h);
            content.stroke();

            String cellText = row[i] != null ? row[i] : "";

            // Dynamically calculate width based on the passed font
            float textWidth = font.getStringWidth(cellText) / 1000f * rowFontSize;

            float textX;
            if (rightAligned != null && i < rightAligned.length && rightAligned[i]) {
                textX = x + colWidths[i] - textWidth - 3;
            } else {
                textX = x + ((colWidths[i] - textWidth) / 2);
            }

            if (textX < x + 1) {
                textX = x + 1;
            }

            content.beginText();
            content.setFont(font, rowFontSize); // Renders using passed font
            content.newLineAtOffset(textX, y - ((h + rowFontSize) / 2) + 1);
            content.showText(cellText);
            content.endText();

            x += colWidths[i];
        }

        return y - h;
    }

    private static void drawCell(PDPageContentStream content,
                                 float x, float y, float width, float height,
                                 String text) throws IOException {

        content.addRect(x, y - height, width, height);
        content.stroke();

        float cellFontSize = 9;
        PDFont cellFont = PDType1Font.TIMES_BOLD;

        List<String> lines = wrapText(safe(text), width - 4, cellFont, cellFontSize);

        float lineHeight = cellFontSize + 1.5f;
        float blockHeight = lines.size() * lineHeight;
        float textY = y - ((height - blockHeight) / 2) - cellFontSize;

        for (String line : lines) {
            float lineWidth = cellFont.getStringWidth(line) / 1000f * cellFontSize;
            float lineX = x + ((width - lineWidth) / 2);
            if (lineX < x + 1) {
                lineX = x + 1;
            }

            content.beginText();
            content.setFont(cellFont, cellFontSize);
            content.newLineAtOffset(lineX, textY);
            content.showText(line);
            content.endText();

            textY -= lineHeight;
        }
    }

    private static void drawTableBorder(PDPageContentStream content,
                                        float x, float y,
                                        float width, float height) throws IOException {

        content.addRect(x, y - height, width, height);
        content.stroke();
    }

    private static void writeText(PDPageContentStream content, String text,
                                  float x, float y, int size, boolean bold) throws IOException {

        content.beginText();
        content.setFont(bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN, size);        content.newLineAtOffset(x, y);
        content.showText(text != null ? text : "");
        content.endText();
    }


    private static void writeTextt(
            PDPageContentStream content,
            String text,
            float x,
            float y,
            float fontSize,
            PDFont font) throws IOException {

        content.beginText();

        content.setFont(font, fontSize);

        content.newLineAtOffset(x, y);

        content.showText(text != null ? text : "");

        content.endText();
    }


    private static float writeParagraph(PDPageContentStream content,
                                        String text, float x, float y,
                                        float width,
                                        PDFont font, float fontSize) throws IOException {

        List<String> lines = wrapText(text, width, font, fontSize);

        for (String line : lines) {

            writeTextt(content, line, x, y, fontSize, font);
            y -= (fontSize * 1.3f);
        }

        return y;
    }


    private static List<String> wrapText(String text, float maxWidth,
                                         PDFont font, float fontSize) throws IOException {

        List<String> lines = new ArrayList<>();
        List<String> words = new ArrayList<>();
        for (String word : safe(text).split(" ")) {
            words.addAll(splitToFit(word, maxWidth, font, fontSize));
        }

        StringBuilder line = new StringBuilder();

        for (String word : words) {

            String testLine = line.length() == 0 ? word : line + " " + word;

            float size = font.getStringWidth(testLine) / 1000 * fontSize;

            if (size > maxWidth && line.length() > 0) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(testLine);
            }
        }

        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }

    private static String formatDateMonth(String date) {

        String value = safe(date);

        String[] p = value.split("-");

        if (p.length != 3 || p[1].isEmpty()) {
            return value;
        }

        String month = p[1].substring(0, 1).toUpperCase()
                + p[1].substring(1).toLowerCase();

        return p[0] + "-" + month + "-" + p[2];
    }

    private static final String[] CERT_TABLE_MONTH_ABBR = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    private static String formatCertificateTableDate(String date) {

        String value = safe(date).trim();

        if (value.isEmpty()) {
            return "";
        }

        if (value.length() >= 10 && value.charAt(4) == '-' && value.charAt(7) == '-') {
            try {
                String datePart = value.split("\\s+")[0];
                String[] parts = datePart.split("-");

                if (parts.length == 3 && parts[0].length() == 4) {
                    int monthNum = Integer.parseInt(parts[1]);
                    int dayNum = Integer.parseInt(parts[2]);

                    if (monthNum >= 1 && monthNum <= 12) {
                        return String.format("%02d-%s-%s",
                                dayNum,
                                CERT_TABLE_MONTH_ABBR[monthNum - 1],
                                parts[0]);
                    }
                }
            } catch (NumberFormatException ignored) {

            }
        }

        return formatDateMonth(value);
    }


    private static final String[] MONTH_NAMES = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };


    private static String formatDayMonth(String value) {

        String text = safe(value).trim();

        if (text.isEmpty()) {
            return "";
        }

        String[] parts = text.split("/");

        if (parts.length < 2) {
            return text;
        }

        int day;
        int month;

        try {
            day = Integer.parseInt(parts[0].trim());
            month = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            return text;
        }

        if (day < 1 || day > 31 || month < 1 || month > 12) {
            return text;
        }

        return parts[0].trim() + ordinalSuffix(day) + " " + MONTH_NAMES[month - 1];
    }


    private static String ordinalSuffix(int day) {

        if (day >= 11 && day <= 13) {
            return "th";
        }

        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }


    private static String toTitleCase(String value) {

        if (value == null || value.isEmpty()) {
            return "";
        }

        char[] chars = value.toLowerCase().toCharArray();

        boolean startOfWord = true;

        for (int i = 0; i < chars.length; i++) {

            if (Character.isLetterOrDigit(chars[i])) {

                if (startOfWord) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }

                startOfWord = false;
            } else {
                startOfWord = true;
            }
        }

        return new String(chars);
    }


    private static String format(String val) {
        if (val == null || val.isEmpty()) {
            return " ";
        }
        return String.format("%,.2f", Double.parseDouble(val));
    }



    public static String convertToWords(double amount) {

        long rupees = (long) amount;
        int paise = (int) Math.round((amount - rupees) * 100);

        String result = "";

        if (rupees == 0) {
            result = "Zero";
        } else {
            result = convertNumber(rupees);
        }

        if (paise > 0) {
            result += " and " + convertNumber(paise) + " Paise";
        }

        return "Rupees " + result + " Only";
    }
    private static final String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };


    private static String convertNumber(long number) {

        if (number == 0) {
            return "";
        }

        if (number < 20) {
            return units[(int) number];
        }

        if (number < 100) {
            return (tens[(int) number / 10] + " " + units[(int) number % 10]).trim();
        }

        if (number < 1000) {
            return (units[(int) number / 100] + " Hundred "
                    + convertNumber(number % 100)).trim();
        }

        if (number < 100000) {
            return (convertNumber(number / 1000) + " Thousand "
                    + convertNumber(number % 1000)).trim();
        }

        if (number < 10000000) {
            long lakh = number / 100000;
            return (convertNumber(lakh)
                    + (lakh > 1 ? " Lakhs " : " Lakh ")
                    + convertNumber(number % 100000)).trim();
        }

        long crore = number / 10000000;
        return (convertNumber(crore)
                + (crore > 1 ? " Crores " : " Crore ")
                + convertNumber(number % 10000000)).trim();
    }

    public static float drawAcknowledgementHeader(PDDocument document,
                                                  PDPageContentStream content,
                                                  float top) throws IOException {

        float y = top;
        float margin = 40f; // matches your certificate margin

        try {
            InputStream is = RbiBondReportsServiceImpl.class.getClassLoader()
                    .getResourceAsStream("static/HDFC-Logo-new.png");
            if (is != null) {
                PDImageXObject logo = PDImageXObject.createFromByteArray(
                        document, is.readAllBytes(), "logo");

                // --- EXACT MATCH TO CERTIFICATE LOGO DIMENSIONS & POSITION ---
                content.drawImage(logo, margin, y - 22, 150, 32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] addressLines = {
                "HDFC Bank Ltd.",
                "CMS-GOI Bonds - 2nd Floor,",
                "GKS Technology Park,",
                "No-94, SP Estate Bus Stand,",
                "Wavin Main Road, Mogappair,",
                "Chennai - 600058,",
                "Tel: 044 6912 9917"
        };

        float rightX = 420;
        y -= 20;

        for (String line : addressLines) {
            content.beginText();
            content.setFont(PDType1Font.TIMES_ROMAN, 10);
            content.newLineAtOffset(rightX, y);
            content.showText(line);
            content.endText();
            y -= 12;
        }

        y -= 15;

        return y;
    }


    private static float ensureAcknowledgementPageSpace(PDDocument document,
                                                        PDPageContentStream[] contentRef,
                                                        float y,
                                                        float requiredHeight) throws IOException {

        if (y - requiredHeight >= ACK_BOTTOM_MARGIN) {
            return y;
        }

        contentRef[0].close();

        PDPage nextPage = new PDPage(PDRectangle.A4);
        document.addPage(nextPage);

        contentRef[0] = new PDPageContentStream(document, nextPage);

//        return ACK_PAGE_TOP - 20;

        return drawAcknowledgementHeader(document, contentRef[0], ACK_PAGE_TOP);
    }

    private static float addAcknowledgementWrappedText(PDDocument document,
                                                       PDPageContentStream[] contentRef,
                                                       String text,
                                                       float y,
                                                       boolean bold) throws IOException {

        if (text == null) {
            return y;
        }

        PDFont font = bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN;
        float fontSize = 12f;
        float lineHeight = fontSize * 1.1f;
        float width = PDRectangle.A4.getWidth() - 80f;

        for (String line : wrapText(text, width, font, fontSize)) {
            y = ensureAcknowledgementPageSpace(document, contentRef, y, lineHeight);
            writeTextt(contentRef[0], line, 40f, y, fontSize, font);
            y -= lineHeight;
        }

        return y;
    }

    private static float addAcknowledgementText(PDDocument document,
                                                PDPageContentStream[] contentRef,
                                                String text,
                                                float y,
                                                boolean bold) throws IOException {

        if (text == null) {
            return y;
        }

        float lineHeight = 14f;
        y = ensureAcknowledgementPageSpace(document, contentRef, y, lineHeight);
        PDPageContentStream content = contentRef[0];

        content.beginText();
        content.setFont(bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN, 11);
        content.newLineAtOffset(40, y);
        content.showText(text);
        content.endText();

        return y - lineHeight;
    }


    private static float drawAcknowledgementSignature(PDPageContentStream content,
                                                      float yAfterSignOff,
                                                      PDImageXObject sign) throws IOException {

        final float imageWidth = 180f;
        final float imageX = 25f;
        final float gapAfterSignOff = 10f;

        float anchorY = yAfterSignOff - gapAfterSignOff;
        float imageBottom = anchorY - ACK_SIGNATURE_IMAGE_HEIGHT;


        content.drawImage(sign, imageX, imageBottom, imageWidth, ACK_SIGNATURE_IMAGE_HEIGHT);
        return imageBottom - ACK_SIGNATURE_GAP_AFTER_IMAGE;
    }


    private static float estimateAcknowledgementClosingBlockHeight() throws IOException {

        float lineHeight = 14f;
        float width = PDRectangle.A4.getWidth() - 80;
        PDFont font = PDType1Font.TIMES_ROMAN;
        float fontSize = 11f;

        String intro =
                "We would also like to confirm that the ACH details for the captioned Bond ledger account registered with us are as follows:";

        float height = 10;
        height += wrapText(intro, width, font, fontSize).size() * lineHeight;
        height += 10;
        height += 4 * lineHeight;
        height += 15;
        height += 2 * lineHeight;
        height += 10;
        height += 2 * lineHeight;
        height += 14;
        height += lineHeight;
        height += 15;
        height += 2 * lineHeight;
        height += 10;
        height += 10f + ACK_SIGNATURE_IMAGE_HEIGHT + ACK_SIGNATURE_GAP_AFTER_IMAGE + 14f;
        height += 2 * lineHeight;

        return height;
    }



    private static float acknowledgementAchColonX() throws IOException {

        String[] labels = {
                "CUSTOMER NAME",
                "BANK NAME",
                "ACCOUNT NUMBER",
                "MICR CODE / IFSC CODE"
        };

        PDFont font = PDType1Font.TIMES_BOLD;
        float fontSize = 11f;
        float maxWidth = 0f;

        for (String label : labels) {
            float labelWidth = font.getStringWidth(label) / 1000f * fontSize;
            maxWidth = Math.max(maxWidth, labelWidth);
        }

        return 40f + maxWidth + 4f;
    }


    private static float drawAcknowledgementAchField(PDPageContentStream content,
                                                     float y,
                                                     String label,
                                                     String value) throws IOException {

        PDFont font = PDType1Font.TIMES_BOLD;
        float fontSize =11f;
        float colonX = acknowledgementAchColonX();

        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(40, y);
        content.showText(label);
        content.endText();

        content.beginText();
        content.setFont(font, fontSize);
        content.newLineAtOffset(colonX, y);
        content.showText(": " + value);
        content.endText();

        return y - 14f;
    }


    private static float drawAcknowledgementNominationGuidelines(PDDocument document,
                                                                 PDPageContentStream[] contentRef,
                                                                 float y) throws IOException {

        PDPageContentStream content = contentRef[0];

        float margin = 40;
        float width = PDRectangle.A4.getWidth() - 80;
        float fontSize = 12;
        float lineHeight = fontSize * 1.1f;
        float indent = 15f; // Horizontal space for the bullet number (e.g., "1. ")

        PDFont normalFont = PDType1Font.TIMES_ROMAN;
        PDFont boldFont = PDType1Font.TIMES_BOLD;

            y -= 20;

        y = ensureAcknowledgementPageSpace(document, contentRef, y, lineHeight + 5);
        content = contentRef[0];

        String text = "Revised Guidelines on Nomination:-";

        writeTextt(content, text, margin, y, fontSize, boldFont);

        float textWidth = boldFont.getStringWidth(text) / 1000 * fontSize;

        content.moveTo(margin, y - 2);
        content.lineTo(margin + textWidth, y - 2);
        content.stroke();

        y -= lineHeight;
        y -= 5;

        for (String guideline : ACK_NOMINATION_GUIDELINES) {

            // 1. Separate "1." from the rest of the string
            int dotIndex = guideline.indexOf(".");
            String numberPrefix = (dotIndex != -1) ? guideline.substring(0, dotIndex + 1) : "";
            String textContent = (dotIndex != -1) ? guideline.substring(dotIndex + 1).trim() : guideline;

            // 2. Wrap text using the available width minus the indent offset
            List<String> lines = wrapText(textContent, width - indent, normalFont, fontSize);

            for (int i = 0; i < lines.size(); i++) {

                y = ensureAcknowledgementPageSpace(document, contentRef, y, lineHeight);
                content = contentRef[0];

                // On the first line of the item, draw the number at the margin
                if (i == 0 && !numberPrefix.isEmpty()) {
                    writeTextt(content, numberPrefix, margin, y, fontSize, normalFont);
                }

                // Draw text lines starting at margin + indent
                writeTextt(content, lines.get(i), margin + indent, y, fontSize, normalFont);
                y -= lineHeight;
            }

            y -= 4; // Space between bullet points
        }

        return y;
    }

    public static byte[] generateAcknowledgement(List<CertificatePrintingDTO> details, String schName) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();

        try {


            if (details != null && !details.isEmpty()) {

                CertificatePrintingDTO record = details.get(0);

                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                PDPageContentStream content = new PDPageContentStream(document, page);



                float y = drawAcknowledgementHeader(document, content, ACK_PAGE_TOP);


                String currentDate = LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH));

                y = addText(content, "Date: " + currentDate, y, true);
                y -= 10;

                y = addText(content, safe(record.getHolderName()), y, true);

                if (record.getAddress() != null && !record.getAddress().trim().isEmpty()) {
                    y = addText(content, record.getAddress() + ",", y, false);
                }

               /* if (record.getAddressTwo() != null && !record.getAddressTwo().trim().isEmpty()) {
                    y = addText(content, record.getAddressTwo() + ",", y, false);
                }

                if (record.getAddressThree() != null && !record.getAddressThree().trim().isEmpty()) {
                    y = addText(content, record.getAddressThree() + ",", y, false);
                }*/

                StringBuilder addressLine = new StringBuilder();

                String addrTwo = record.getAddressTwo();
                String addrThree = record.getAddressThree();

                boolean hasAddrTwo = (addrTwo != null && !addrTwo.trim().isEmpty());
                boolean hasAddrThree = (addrThree != null && !addrThree.trim().isEmpty());

                if (hasAddrTwo) {
                    addressLine.append(addrTwo.trim()).append(", ");
                }
                if (hasAddrThree) {
                    addressLine.append(addrThree.trim()).append(", ");
                }

// Only add text if at least one of them has data
                if (addressLine.length() > 0) {
                    y = addText(content, addressLine.toString(), y, false);
                }

                if (record.getCity() != null && !record.getCity().trim().isEmpty()) {
                    y = addText(content, record.getCity() + ","+ record.getPincode(), y, false);
                }

                if (record.getState() != null && !record.getState().trim().isEmpty()) {
                    y = addText(content, record.getState() + "," + "INDIA ", y, false);
                }
/*
                if (record.getPincode() != null && !record.getPincode().trim().isEmpty()) {
                    y = addText(content, "INDIA " + record.getPincode(), y, false);
                }*/

            y -= 15;

                DateTimeFormatter inputFormatter =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy");

                DateTimeFormatter outputFormatter =
                        DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);

                String transactionDate = LocalDate
                        .parse(record.getTransactionDate(), inputFormatter)
                        .format(outputFormatter);
            y = addText(content, "Dear Customer,", y, false);
          y -= 15;

                float x = 40;
                float fontSize = 14;

            String refPrefix = "Ref : ";
                String refContent = "BLA No. " + record.getBlaNo()
                        + ", Transaction Date : " + transactionDate;

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, fontSize);
            content.newLineAtOffset(x, y);
            content.showText(refPrefix + refContent);
            content.endText();

            float refPrefixWidth =
                    PDType1Font.TIMES_BOLD.getStringWidth(refPrefix) / 1000 * fontSize;

            float refContentWidth =
                    PDType1Font.TIMES_BOLD.getStringWidth(refContent) / 1000 * fontSize;

                float totalWidth = refPrefixWidth + refContentWidth;

                content.moveTo(x, y - 2);
                content.lineTo(x + totalWidth, y - 2);
                content.stroke();

                y -= 20;


                String subPrefix = "Sub : ";
                String subContent =
                        "Confirmation on Registration of Nomination & ACH Mandate";

            content.beginText();
            content.setFont(PDType1Font.TIMES_BOLD, fontSize);
            content.newLineAtOffset(x, y);
            content.showText(subPrefix + subContent);
            content.endText();

            float subPrefixWidth =
                    PDType1Font.TIMES_BOLD.getStringWidth(subPrefix) / 1000 * fontSize;

            float subContentWidth =
                    PDType1Font.TIMES_BOLD.getStringWidth(subContent) / 1000 * fontSize;

                float totalSubWidth = subPrefixWidth + subContentWidth;

                content.moveTo(x, y - 2);
                content.lineTo(x + totalSubWidth, y - 2);
            content.stroke();

            y -= 25;

            y = addText(content,
                    "We acknowledge receipt of your request for registering of Nomination/ACH mandate made in favour for your",
                    y, false);


                String ackSchemeName = record.getSchemeName() == null || record.getSchemeName().trim().isEmpty()
                        ? schName
                        : record.getSchemeName();

// First part of the scheme paragraph
                List<TextPart> schemeParts1 = new ArrayList<>();
                schemeParts1.add(new TextPart("investment in ", PDType1Font.TIMES_ROMAN, 12, false));
                schemeParts1.add(new TextPart(valueOrNA(ackSchemeName), PDType1Font.TIMES_BOLD, 12, false));
                schemeParts1.add(new TextPart(
                        " scheme under section 9B(4) of the Public Debt Act, 1944.",
                        PDType1Font.TIMES_ROMAN, 12, false));

                y = writeStyledParagraph(content, schemeParts1, 40, y, PDRectangle.A4.getWidth() - 80);

                y -= 25f;
                List<TextPart> schemeParts2 = new ArrayList<>();
                schemeParts2.add(new TextPart(
                        "The Certificate of Holding is issued for Bond ledger account no ",
                        PDType1Font.TIMES_ROMAN, 12, false));
                schemeParts2.add(new TextPart(valueOrNA(record.getBlaNo()), PDType1Font.TIMES_BOLD, 12, false));
                schemeParts2.add(new TextPart(" under ", PDType1Font.TIMES_ROMAN, 12, false));
                schemeParts2.add(new TextPart(valueOrNA(record.getBondType()), PDType1Font.TIMES_BOLD, 12, false));
                schemeParts2.add(new TextPart(" scheme.", PDType1Font.TIMES_ROMAN, 12, false));
                y = writeStyledParagraph(content, schemeParts2, 40, y, PDRectangle.A4.getWidth() - 80) - 14;
            y = addText(content,
                        "We have registered the nomination  as per details below:" ,
                    y, false);

            y -= 15;

            PDPageContentStream[] contentRef = { content };
            y = ensureAcknowledgementPageSpace(document, contentRef, y, 44f);
            content = contentRef[0];

            y = drawNominationTable(document, contentRef, y, details);

            content = contentRef[0];

            y = drawAcknowledgementNominationGuidelines(document, contentRef, y);

            content = contentRef[0];

            y -= 10;


            y = ensureAcknowledgementPageSpace(document, contentRef, y, 28f);
            content = contentRef[0];

            y = addAcknowledgementWrappedText(document, contentRef,
                    "We would also like to confirm that the ACH details for the captioned Bond ledger account registered with us are as follows:",
                    y, false);
            content = contentRef[0];

            y -= 10;

                // four ACH detail lines
                y = ensureAcknowledgementPageSpace(document, contentRef, y, 56f);
                content = contentRef[0];

                y = drawAcknowledgementAchField(content, y, "CUSTOMER NAME",
                        acknowledgementFieldOrNA(record.getHolderName()).toUpperCase(Locale.ENGLISH));
                y = drawAcknowledgementAchField(content, y, "BANK NAME",
                        acknowledgementFieldOrNA(record.getBankName()).toUpperCase(Locale.ENGLISH));
                y = drawAcknowledgementAchField(content, y, "ACCOUNT NUMBER",
                        acknowledgementFieldOrNA(record.getAccNo()).toUpperCase(Locale.ENGLISH));
                y = drawAcknowledgementAchField(content, y, "MICR CODE / IFSC CODE",
                        acknowledgementFieldOrNA(record.getMicrCode()).toUpperCase(Locale.ENGLISH));

            y -= 15;

                y = addAcknowledgementWrappedText(document, contentRef,
                    "In case of any mismatch in the above mentioned details and in the enclosed Bond ledger certificate, we request you to",
                    y, false);
                content = contentRef[0];
                y = addAcknowledgementWrappedText(document, contentRef,
                    "kindly revert within 10 days from the receipt of this letter.",
                    y, false);
                content = contentRef[0];

            y -= 10;

                y = addAcknowledgementWrappedText(document, contentRef,
                        "To help us serve you better, please ensure that you quote your Bond ledger account number in all your communication with us.",
                    y, false);
                content = contentRef[0];

                y -= 14;

                y = addAcknowledgementWrappedText(document, contentRef,
                    "Thanking you and assuring best of our services at all times.",
                    y, false);
                content = contentRef[0];

            y -= 15;
                float signBlockHeight = 14f + 14f + 10f + ACK_SIGNATURE_IMAGE_HEIGHT;
                y = ensureAcknowledgementPageSpace(document, contentRef, y, signBlockHeight);
                content = contentRef[0];

                y = addAcknowledgementText(document, contentRef, "Yours sincerely,", y, false);
                content = contentRef[0];
                y = addAcknowledgementText(document, contentRef, "For HDFC Bank Ltd", y, false);
                content = contentRef[0];
                try {

                InputStream is = RbiBondReportsServiceImpl.class
                        .getResourceAsStream("/static/nageswaranNewBg.png");

                if (is == null) {
                    throw new RuntimeException("Image not found!");
                }

                PDImageXObject sign = PDImageXObject.createFromByteArray(
                        document, is.readAllBytes(), "sign");

                    y = drawAcknowledgementSignature(contentRef[0], y, sign);
                    content = contentRef[0];

            } catch (Exception e) {
                e.printStackTrace();
            }

                y = ensureAcknowledgementPageSpace(document, contentRef, y, ACK_SIGNATURE_FOOTER_HEIGHT);
                content = contentRef[0];
                y -= 15;
                y = addAcknowledgementText(document, contentRef, "GOI Bonds Operations.", y, false);
                content = contentRef[0];
                y = addAcknowledgementWrappedText(document, contentRef,
                        "In case of any further clarifications, please contact us on rrb.chennai@hdfc.bank.in",
                    y, false);
                content = contentRef[0];
            content.close();
            }

        document.save(baos);

    }
    catch (Exception e){
        e.printStackTrace();
        log.error(CommonConstants.EXCEPTION_MESSAGE, e);

    }
    finally {

        document.close();
    }

    return baos.toByteArray();
}

    private static float drawNominationTable(PDDocument document,
                                             PDPageContentStream[] contentRef,
            float y,
            List<CertificatePrintingDTO> nomineeList) throws IOException {

        PDPageContentStream content = contentRef[0];

        float startX = 40;

        float rowHeight = 22;

        float[] columnWidths = {
                35,   // Sr No
                75,   // Branch Ref No
                60,  // COI No
                135,  // Nominee Name
                75,   // Nomination ID
                75,   // Guardian Name
                60    // Nominee NRI(Y/N)
        };

        String[] headers = {
                "SR. NO.",
                "Branch Ref No.",
                "Certificate No",
                "NOMINEE NAME",
                "NOMINATION ID",
                "GUARDIAN NAME",
                "Nominee NRI(Y/N)"

        };


        float nextX = startX;


        rowHeight = computeNominationRowHeight(headers, columnWidths, true);
        y = drawNominationRow(content, startX, y, columnWidths, headers, rowHeight, true);

        if (nomineeList == null || nomineeList.isEmpty()) {

            String[] row = {
                    "1",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A",
                    "N/A"
            };

            for (int i = 0; i < row.length; i++) {

                content.addRect(nextX, y - rowHeight, columnWidths[i], rowHeight);
                content.stroke();


                drawNominationCellText(content, nextX, y, columnWidths[i], rowHeight, row[i], false);

            nextX += columnWidths[i];
        }

            y -= rowHeight;
            rowHeight = computeNominationRowHeight(row, columnWidths, false);
            y = drawNominationRow(content, startX, y, columnWidths, row, rowHeight, false);

        } else {

            int srNo = 1;

            for (CertificatePrintingDTO dto : nomineeList) {

                // carry the remaining rows onto a new page with the header repeated
                String[] row = {

                        String.valueOf(srNo++),

//                        safe(dto.getBranchRefNo()),
                        valueOrNA(dto.getBranchRefNo()),

//                        safe(dto.getCohNo()),
                        valueOrNA(dto.getCohNo()),

//                        safe(dto.getName()),
                        valueOrNA(dto.getName()),

//                        safe(dto.getNominationUnder()),
                        valueOrNA(dto.getNominationUnder()),

//                        safe(dto.getGuardianName())
                        valueOrNA(dto.getGuardianName()),
                        valueOrNA(dto.getStatus())

                };

                rowHeight = computeNominationRowHeight(row, columnWidths, false);

                if (y - rowHeight < ACK_BOTTOM_MARGIN) {

                    content.close();

                    PDPage nextPage = new PDPage(PDRectangle.A4);
                    document.addPage(nextPage);

                    content = new PDPageContentStream(document, nextPage);
                    contentRef[0] = content;

//                    y = ACK_PAGE_TOP;

                    y = drawAcknowledgementHeader(document, content, ACK_PAGE_TOP);

                    rowHeight = computeNominationRowHeight(headers, columnWidths, true);
                    y = drawNominationRow(content, startX, y, columnWidths, headers, rowHeight, true);
                }


                y = drawNominationRow(content, startX, y, columnWidths, row, rowHeight, false);
            }
        }

        return y;
    }

    private static float computeNominationRowHeight(String[] cells,
                                                    float[] columnWidths,
                                                    boolean bold) throws IOException {

        PDFont font = bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN;
        float fontSize = 12;
        float lineHeight = fontSize + 2;
        float minRowHeight = 22;
        float verticalPadding = 6;
        int maxLines = 1;

        int columnCount = Math.min(cells.length, columnWidths.length);
        for (int i = 0; i < columnCount; i++) {
            List<String> lines = wrapText(valueOrNA(cells[i]), columnWidths[i] - 4, font, fontSize);
            maxLines = Math.max(maxLines, lines.size());
        }

        return Math.max(minRowHeight, (maxLines * lineHeight) + verticalPadding);
    }

    private static float drawNominationRow(PDPageContentStream content,
                                           float startX, float y,
                                           float[] columnWidths, String[] cells,
                                           float rowHeight, boolean bold) throws IOException {

        float nextX = startX;

        for (int i = 0; i < cells.length; i++) {

            content.addRect(nextX, y - rowHeight, columnWidths[i], rowHeight);
            content.stroke();


            drawNominationCellText(content, nextX, y, columnWidths[i], rowHeight,
                    valueOrNA(cells[i]), bold);

            nextX += columnWidths[i];
        }

        return y - rowHeight;
    }


    private static void drawNominationCellText(PDPageContentStream content,
                                               float cellX, float y, float cellWidth,
                                               float rowHeight, String text,
                                               boolean bold) throws IOException {

        PDFont font = bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN;
        float fontSize = 10;
        float paddingX = 2;
        float maxTextWidth = cellWidth - (paddingX * 2);
        String cellText = valueOrNA(text);

        List<String> lines = wrapText(cellText, maxTextWidth, font, fontSize);
        float lineHeight = fontSize + 2;
        float blockHeight = lines.size() * lineHeight;
        float textY = y - ((rowHeight - blockHeight) / 2) - fontSize;

        for (String line : lines) {
            float textWidth = font.getStringWidth(line) / 1000f * fontSize;
            float textX = cellX + Math.max(paddingX, (cellWidth - textWidth) / 2f);

            content.beginText();
            content.setFont(font, fontSize);
            content.newLineAtOffset(textX, textY);
            content.showText(line);
            content.endText();

            textY -= lineHeight;
        }
    }
    private static float addText(PDPageContentStream content, String text, float y, boolean bold) throws Exception {
        if (text == null) return y;

        content.beginText();
        content.setFont(bold ? PDType1Font.TIMES_BOLD : PDType1Font.TIMES_ROMAN, 12);
        content.newLineAtOffset(40, y);
        content.showText(text);
        content.endText();

        return y - 14;
    }



    private static String formatMaturityDate(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "";
        }

        try {
            SimpleDateFormat inputFormat =
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

            SimpleDateFormat outputFormat =
                    new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);

            return outputFormat.format(inputFormat.parse(value));
        } catch (ParseException e) {
            log.error("Invalid maturity date: {}", value, e);
            return value;
        }
    }
    private static String formatInvestmentAmount(String amount) {
        if (amount == null || amount.trim().isEmpty()) {
            return "";
        }
        try {
            BigDecimal value = new BigDecimal(amount.trim())
                    .setScale(2, RoundingMode.HALF_UP);
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
            formatter.setMinimumFractionDigits(2);
            formatter.setMaximumFractionDigits(2);
            return formatter.format(value);
        } catch (NumberFormatException e) {
            return amount;
        }
    }


    private static byte[] controlFileMailFound(List<CertificatePrintingDTO> list) {

        StringBuilder sb = new StringBuilder();

        // Header with TAB separator
        sb.append("Mail_TO\tMail_CC\tMail_BCC\tSubject\tAttachment\tSMS_TO\tBarcode\tBLA_No\tCFD\tAppl_Name\tInvest_Amt\tMaturity Date\n");

        for (CertificatePrintingDTO folder : list) {
            String maturityDate = formatMaturityDate(folder.getMaturityDate());
            String cfdDate = formatMaturityDate(folder.getCfd());
            String investAmt = formatInvestmentAmount(folder.getInvestAmt());
            // Data with TAB separator
            sb.append(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                    Objects.toString(folder.getMailId(), ""),
                    Objects.toString(folder.getMailCc(), ""),
                    Objects.toString(folder.getMailBcc(), ""),
                    Objects.toString(folder.getSubject(), ""),
                    Objects.toString(folder.getAttachment(), ""),
                    Objects.toString(folder.getSmsTo(), ""),
                    Objects.toString(folder.getBarcode(), ""),
                    Objects.toString(folder.getBlaNo(), ""),
                    Objects.toString(cfdDate, ""),
                    Objects.toString(folder.getApplName(), ""),
                    Objects.toString(investAmt, ""),
                    Objects.toString(maturityDate, "")
            ));
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    // Generate readme.txt
        private static byte[] controlFileMailNotFound(List<CertificatePrintingDTO> list) {

        StringBuilder sb = new StringBuilder();

        // Header with TAB separator
        sb.append("Mail_TO\tMail_CC\tMail_BCC\tSubject\tAttachment\tSMS_TO\tBarcode\tBLA Number\tInvest Amt\tMaturity Date\n");

        for (CertificatePrintingDTO folder : list) {
            String maturityDate = formatMaturityDate(folder.getMaturityDate());
            String investAmt = formatInvestmentAmount(folder.getInvestAmt());
            // Data with TAB separator
            sb.append(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                    Objects.toString(folder.getMailId(), ""),
                    Objects.toString(folder.getMailCc(), ""),
                    Objects.toString(folder.getMailBcc(), ""),
                    Objects.toString(folder.getSubject(), ""),
                    Objects.toString(folder.getAttachment(), ""),
                    Objects.toString(folder.getSmsTo(), ""),
                    Objects.toString(folder.getBarcode(), ""),
                    Objects.toString(folder.getBlaNo(), ""),
                    Objects.toString(investAmt, ""),
                    Objects.toString(maturityDate, "")
            ));
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }


    public String uploadToLocalSftp(byte[] fileData, String fileName) throws Exception {

        Session session = null;
        Channel channel = null;
        ChannelSftp sftpChannel = null;

        try {


            JSch jsch = new JSch();

            session = jsch.getSession(userName, host, Integer.parseInt(port));

            session.setPassword(password);

            Properties config = new Properties();

            config.put(hostKey, "no");

            session.setConfig(config);

            session.connect();


            log.info("Session Connected");
            channel = session.openChannel("sftp");
            channel.connect();
            log.info("SFTP Channel Connected");
            sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(remoteLocalDir);


            try (OutputStream os = sftpChannel.put(fileName)) {

                os.write(fileData);

                os.flush();

                log.info("ZIP File Uploaded Successfully");
                return "S";
            }

        } catch (com.jcraft.jsch.JSchException e) {


            log.error("SFTP Connection Error");
            e.printStackTrace();
            return "F";


        } catch (com.jcraft.jsch.SftpException e) {

            log.error("SFTP File Upload Error");
            e.printStackTrace();
            return "F";

        } catch (Exception e) {

            log.error("Internal Server Error");
            e.printStackTrace();
            return "F";


        } finally {

            // Close SFTP channel
            if (sftpChannel != null) {
                sftpChannel.exit();
            }

            // Disconnect channel
            if (channel != null) {
                channel.disconnect();
            }

            // Disconnect session
            if (session != null) {
                session.disconnect();
            }

            log.error("SFTP Connection Closed");
        }

    }


    public String uploadToSftp(byte[] fileData, String fileName) {

        ChannelSftp sftpChannel = null;
        OutputStream os = null;

        try {

            sftpChannel = sftp.createSftp();

            sftpChannel.cd(remoteLocalDir);

            os = sftpChannel.put(fileName);

            os.write(fileData);

            os.flush();

            log.info("ZIP File Uploaded Successfully");

            return "S";

        } catch (Exception e) {

            log.error("ZIP File Upload Failed", e);

            return "F";

        } finally {

            try {

                if (os != null) {
                    os.close();
                }

            } catch (Exception e) {
                log.error("Error while closing OutputStream", e);
            }

            sftp.disconnect(sftpChannel);
        }
    }




}