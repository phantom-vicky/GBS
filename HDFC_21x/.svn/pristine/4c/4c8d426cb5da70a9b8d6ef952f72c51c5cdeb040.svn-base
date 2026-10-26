package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondScrollsBO;
import com.isl.gbm.rbibond.dto.scrolls.*;
import com.isl.gbm.rbibond.service.RbiBondScrollsService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.RbiBondScrollsDAO;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.user.management.util.UserAccountUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondScrollsServiceImpl implements RbiBondScrollsService {

    @Autowired
    private RbiBondScrollsBO rbiBondScrollsBO;

    @Autowired
    private RbiBondScrollsDAO rbiBondScrollsDAO;


    @Override
    public ResponseDataDTO getBondPrincipalRepay(BondPrincipalRepayDTO bondPrincipalRepayDTO) {
        return rbiBondScrollsBO.getBondPrincipalRepay(bondPrincipalRepayDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestRepay(BondInterestRepayDTO bondInterestRepayDTO) {
        return rbiBondScrollsBO.getBondInterestRepay(bondInterestRepayDTO);
    }

    @Override
    public ResponseDataDTO getBondRegisterLedgerAcc(BondRegisterLedgerAccDTO bondRegisterLedgerAccDTO) {
        return rbiBondScrollsBO.getBondRegisterLedgerAcc(bondRegisterLedgerAccDTO);
    }

    @Override
    public ResponseDataDTO getBondRegLedgerLinkCell(BondRegisterLedgerLinkDTO bondRegisterLedgerLinkDTO) {
        return rbiBondScrollsBO.getBondRegLedgerLinkCell(bondRegisterLedgerLinkDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageHandlingReport(BondBrokerageHandleDTO bondBrokerageHandleDTO) {
        return rbiBondScrollsBO.getBrokerageHandlingReport(bondBrokerageHandleDTO);
    }

    @Override
    public ResponseDataDTO statementOfBondsDetails(StatementofBondsIssuedDTO statementofBondsIssuedDTO) {
        return rbiBondScrollsBO.statementOfBondsDetails(statementofBondsIssuedDTO);
    }

    @Override
    public ResponseDataDTO statementOfChargesServicingDetails(SatatementOfChargesBlaDTO satatementOfChargesBlaDTO) {
        return rbiBondScrollsBO.statementOfChargesServicingDetails(satatementOfChargesBlaDTO);
    }

    @Override
    public ResponseDataDTO statementOfChargesTurnoverCommissionDetails(RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO) {
        return rbiBondScrollsBO.statementOfChargesTurnoverCommissionDetails(rbiBondTurnoverCommissionDTO);
    }

    @Override
    public ResponseDataDTO principleScrollreportAppendixXII(PrincipleScrollreportAppendixXIIDto scrollreportAppendixXIIDto) {
        return rbiBondScrollsBO.principleScrollreportAppendixXII(scrollreportAppendixXIIDto);
    }

    @Override
    public ResponseDataDTO estimateOfExpenses(EstimateOfExpensesDto estimateOfExpensesDto) {
        return rbiBondScrollsBO.estimateOfExpenses(estimateOfExpensesDto);
    }

    @Override
    public ResponseDataDTO getSubsTextFileDetails(SusbscriptionTextFileDto susbscriptionTextFileDto) {
        return rbiBondScrollsBO.getSubsTextFileDetails(susbscriptionTextFileDto);
    }

    @Override
    public ResponseDataDTO getRedemptionTextFileDetails(RedemptionTextFileDto redemptionTextFileDto) {
        return rbiBondScrollsBO.getRedemptionTextFileDetails(redemptionTextFileDto);
    }

    @Override
    public ResponseDataDTO getMonthBrokerageDetails(MonthlyBrokerageClaimDto monthlyBrokerageClaimDto) {
        return rbiBondScrollsBO.getMonthBrokerageDetails(monthlyBrokerageClaimDto);
    }

    @Override
    public ResponseDataDTO getInterestScrollReport5106Details(InterestScrollReportDTO interestScrollReportDTO) {
        return rbiBondScrollsBO.getInterestScrollReport5106Details(interestScrollReportDTO);
    }

    @Override
    public ResponseDataDTO getAppendixDetails(AppendixFourMarchDTO appendixFourMarchDTO) {
        return rbiBondScrollsBO.getAppendixDetails(appendixFourMarchDTO);
    }

    @Override
    public ResponseDataDTO getEfillingDetails(EfillingDTO efillingDTO) {
        return rbiBondScrollsBO.getEfillingDetails(efillingDTO);
    }
    @Override
    public ResponseDataDTO getEfillingDetailsDownload(EfillingDTO efillingDTO) {
        return rbiBondScrollsBO.getEfillingDetailsDownload(efillingDTO);
    }

    @Override
    public ResponseDataDTO getSft(SftScrollDTO sftScrollDTO) {
        return rbiBondScrollsBO.getSft(sftScrollDTO);
    }

    @Override
    public ResponseDataDTO getAIRDetails(AirDTO airDTO) {
        return rbiBondScrollsBO.getAIRDetails(airDTO);
    }

    @Override
    public ResponseDataDTO getAppendixIV805Details(AppendixIV805DTO appendixIV805DTO) {
        return rbiBondScrollsBO.getAppendixIV805Details(appendixIV805DTO);
    }

    @Override
    public ResponseDataDTO getAnnualReport(AnnualReportDto annualReportDto) {
        return rbiBondScrollsBO.getAnnualReport(annualReportDto);
    }

    @Override
    public ResponseDataDTO getAppendixIV8Details(AppendixIV8DTO appendixIV8DTO) {
        return rbiBondScrollsBO.getAppendixIV8Details(appendixIV8DTO);
    }

    @Override
    public ResponseDataDTO getAppendixIV85Details(AppendixIV85DTO appendixIV85DTO) {
        return rbiBondScrollsBO.getAppendixIV85Details(appendixIV85DTO);
    }

    @Override
    public ResponseDataDTO getAppendix65Details(AppendixFourMar65DTO apendixFourMar65DTO){
        return  rbiBondScrollsBO.getAppendix65Details(apendixFourMar65DTO);
    }

  @Override
    public ResponseDataDTO getsubScriptionMandateDetails(SubscriptionMandateDTO subscriptionMandateDTO){
      return  rbiBondScrollsBO.getsubScriptionMandateDetails(subscriptionMandateDTO);
  }

  @Override
    public ResponseDataDTO getBrokerageClaimHandlingCommissionDetails(BrokerageClaimAndHandlingCommissionDTO brokerageClaimDTO){
      return  rbiBondScrollsBO.getBrokerageClaimHandlingCommissionDetails(brokerageClaimDTO);
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestScrollDetails(HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return rbiBondScrollsBO.getHalfYearlyInterestScrollDetails(halfYearlyInterestScrollDTO);
    }
    @Override
    public ResponseDataDTO downloadHalfYearlyInterestScrollFile(HalfYearlyInterestScrollDTO request) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            HyDownloadContext context = buildHalfYearlyDownloadContext(request);
            validateHalfYearlyDownloadContext(context, request.getDownloadType());
            HyGeneratedFile file = generateHalfYearlyScrollFile(context, request.getDownloadType());
            FileResponseDto fileResponseDto = new FileResponseDto();
            fileResponseDto.setFileName(file.getFileName());
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(file.getContent()));
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        } catch (IllegalArgumentException ex) {
            responseDataDTO.setMessage(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            responseDataDTO.setMessage(ErrorMessages.DATABASE_ERROR);
        }
        return responseDataDTO;
    }

    private HyDownloadContext buildHalfYearlyDownloadContext(HalfYearlyInterestScrollDTO request) {
        HyDownloadContext context = new HyDownloadContext();
        context.setCriteria(copyHalfYearlyScrollCriteria(request));
        context.setBranchId(Optional.ofNullable(UserAccountUtils.getCurrentSolId()).orElse(""));
        context.setBranchName(Optional.ofNullable(UserAccountUtils.getSolDesc()).orElse(""));
        context.setSignatoryUsername(Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse(""));
        List<HalfYearlyInterestScrollDTO> annexureRows = fetchHalfYearlyScrollList(request, "A");
        context.setAnnexureRows(annexureRows);
        context.setDetailRows(Collections.emptyList());
        context.setPdoRows(fetchHalfYearlyScrollList(request, "P"));
        context.setPdoFileLines(fetchHalfYearlyScrollList(request, "D"));

        HalfYearlyInterestScrollDTO letterMeta = null;
        List<HalfYearlyInterestScrollDTO> letterRows = fetchHalfYearlyScrollList(request, "L");
        if (!letterRows.isEmpty()) {
            letterMeta = letterRows.get(0);
        } else if (!annexureRows.isEmpty()) {
            letterMeta = new HalfYearlyInterestScrollDTO();
            letterMeta.setTotalInterestAmount(annexureRows.get(0).getTotalInterestAmount());
            letterMeta.setPaymentDateLabel(annexureRows.get(0).getBreakUpPaymentDate());
        }
        context.setLetterMeta(letterMeta);
        return context;
    }

    private HalfYearlyInterestScrollDTO copyHalfYearlyScrollCriteria(HalfYearlyInterestScrollDTO src) {
        HalfYearlyInterestScrollDTO copy = new HalfYearlyInterestScrollDTO();
        copy.setSchemeCode(src.getSchemeCode());
        copy.setSchemeName(src.getSchemeName());
        copy.setSchemeStatus(src.getSchemeStatus());
        copy.setInterestPaymentType(src.getInterestPaymentType());
        copy.setHalfYearMonth(src.getHalfYearMonth());
        copy.setYear(src.getYear());
        copy.setFromDate(src.getFromDate());
        copy.setToDate(src.getToDate());
        copy.setFinancialYear(src.getFinancialYear());
        return copy;
    }

    @SuppressWarnings("unchecked")
    private List<HalfYearlyInterestScrollDTO> fetchHalfYearlyScrollList(HalfYearlyInterestScrollDTO request, String flag) {
        HalfYearlyInterestScrollDTO query = copyHalfYearlyScrollCriteria(request);
        query.setFlag(flag);
        ResponseDataDTO response = rbiBondScrollsDAO.getHalfYearlyInterestScrollDetails(query);
        if (response.getResult() == null) {
            return Collections.emptyList();
        }
        Map<String, Object> resultMap = (Map<String, Object>) response.getResult();
        Object listObj = resultMap.get("list");
        if (listObj instanceof List) {
            return (List<HalfYearlyInterestScrollDTO>) listObj;
        }
        return Collections.emptyList();
    }

    /** Max rupees per 22-digit file row (900 crore). */
    private static final long PDO_FILE_MAX_RUPEES_PER_ROW = 9_000_000_000L;

    private static class HyDownloadContext {
        private HalfYearlyInterestScrollDTO criteria;
        private String branchId;
        private String branchName;
        private String signatoryUsername;
        private List<HalfYearlyInterestScrollDTO> detailRows = new ArrayList<>();
        private HalfYearlyInterestScrollDTO letterMeta;
        private List<HalfYearlyInterestScrollDTO> annexureRows = new ArrayList<>();
        private List<HalfYearlyInterestScrollDTO> pdoRows = new ArrayList<>();
        private List<HalfYearlyInterestScrollDTO> pdoFileLines = new ArrayList<>();

        public HalfYearlyInterestScrollDTO getCriteria() {
            return criteria;
        }

        public void setCriteria(HalfYearlyInterestScrollDTO criteria) {
            this.criteria = criteria;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getSignatoryUsername() {
            return signatoryUsername;
        }

        public void setSignatoryUsername(String signatoryUsername) {
            this.signatoryUsername = signatoryUsername;
        }

        public List<HalfYearlyInterestScrollDTO> getDetailRows() {
            return detailRows;
        }

        public void setDetailRows(List<HalfYearlyInterestScrollDTO> detailRows) {
            this.detailRows = detailRows != null ? detailRows : new ArrayList<>();
        }

        public HalfYearlyInterestScrollDTO getLetterMeta() {
            return letterMeta;
        }

        public void setLetterMeta(HalfYearlyInterestScrollDTO letterMeta) {
            this.letterMeta = letterMeta;
        }

        public List<HalfYearlyInterestScrollDTO> getAnnexureRows() {
            return annexureRows;
        }

        public void setAnnexureRows(List<HalfYearlyInterestScrollDTO> annexureRows) {
            this.annexureRows = annexureRows != null ? annexureRows : new ArrayList<>();
        }

        public List<HalfYearlyInterestScrollDTO> getPdoRows() {
            return pdoRows;
        }

        public void setPdoRows(List<HalfYearlyInterestScrollDTO> pdoRows) {
            this.pdoRows = pdoRows != null ? pdoRows : new ArrayList<>();
        }

        public List<HalfYearlyInterestScrollDTO> getPdoFileLines() {
            return pdoFileLines;
        }

        public void setPdoFileLines(List<HalfYearlyInterestScrollDTO> pdoFileLines) {
            this.pdoFileLines = pdoFileLines != null ? pdoFileLines : new ArrayList<>();
        }
    }

    private static class HyGeneratedFile {
        private final byte[] content;
        private final String fileName;

        public HyGeneratedFile(byte[] content, String fileName) {
            this.content = content;
            this.fileName = fileName;
        }

        public byte[] getContent() {
            return content;
        }

        public String getFileName() {
            return fileName;
        }
    }

    private void validateHalfYearlyDownloadContext(HyDownloadContext ctx, String downloadType) {
        if (ctx == null || downloadType == null) {
            throw new IllegalArgumentException("Invalid download request.");
        }
        String type = downloadType.toUpperCase(Locale.ENGLISH);
        switch (type) {
            case "ANNEXURE":
                if (ctx.getAnnexureRows().isEmpty()) {
                    throw new IllegalArgumentException(CommonConstants.NDF);
                }
                break;
            case "CERTIFICATION":
                if (ctx.getAnnexureRows().isEmpty() && ctx.getLetterMeta() == null) {
                    throw new IllegalArgumentException(CommonConstants.NDF);
                }
                break;
            case "PDO_TALLY":
                if (ctx.getPdoRows().isEmpty()) {
                    throw new IllegalArgumentException(CommonConstants.NDF);
                }
                break;
            case "PDO_22_DIGIT":
                if (ctx.getPdoFileLines().isEmpty()) {
                    throw new IllegalArgumentException(CommonConstants.NDF);
                }
                break;
            default:
                break;
        }
    }

    private HyGeneratedFile generateHalfYearlyScrollFile(HyDownloadContext ctx, String downloadType) {
        if (ctx == null || ctx.getCriteria() == null || downloadType == null) {
            throw new IllegalArgumentException("Invalid download request.");
        }
        switch (downloadType.toUpperCase(Locale.ENGLISH)) {
            case "CERTIFICATION":
                return certificationLetter(ctx);
            case "ANNEXURE":
                return annexureLetter(ctx);
            case "PDO_TALLY":
                return pdoTallyLetter(ctx);
            case "PDO_22_DIGIT":
                return pdo22DigitFile(ctx);
            default:
                throw new IllegalArgumentException("Unknown download type: " + downloadType);
        }
    }

    private HyGeneratedFile certificationLetter(HyDownloadContext ctx) {
        BigDecimal total = resolveTotalInterestAmount(ctx);
        if (total == null) {
            throw new IllegalArgumentException("Unable to derive total interest amount for the letter.");
        }
        HalfYearlyInterestScrollDTO c = ctx.getCriteria();
        HalfYearlyInterestScrollDTO letter = ctx.getLetterMeta();
        String letterDate = formatLetterDate(LocalDate.now());
        String halfYearEnded = letter != null && notBlank(letter.getHalfYearEnded())
                ? letter.getHalfYearEnded() : deriveHalfYearEndedLabel(c);
        String investmentFrom = letter != null && notBlank(letter.getInvestmentFromDate())
                ? letter.getInvestmentFromDate() : deriveInvestmentFromDate(c.getFinancialYear());
        String investmentTo = letter != null && notBlank(letter.getInvestmentToDate())
                ? letter.getInvestmentToDate() : deriveInvestmentToDate(c.getFinancialYear());
        String paymentDateLabel = letter != null && notBlank(letter.getPaymentDateLabel())
                ? letter.getPaymentDateLabel() : "As per annexure enclosed";
        String signatoryName = letter != null && notBlank(letter.getSignatoryName())
                ? letter.getSignatoryName() : "";
        String signatoryId = letter != null && notBlank(letter.getSignatoryEmployeeId())
                ? letter.getSignatoryEmployeeId()
                : nullToEmpty(ctx.getSignatoryUsername());
        String schemeLabel = buildSchemeLabelForLetter(c);
        String amountFormatted = formatIndianNumber(total);
        String amountWords = convertNumberToWords(total);

        String html = wordHtmlPrefix("12pt", "'Times New Roman',serif") +
                "<p class='right'>" + escapeHtml(letterDate) + "</p>" +
                "<p>The General Manager<br/>Reserve Bank of India<br/>Central Accounts Section<br/>Nagpur</p>" +
                "<p style='margin-top:18px;text-align:justify;'>We, the Internal Auditors of <b>HDFC BANK</b> certify that the interest reimbursement figures for the half year ended <b>" +
                escapeHtml(halfYearEnded) + "</b> for the investments received between <b>" +
                escapeHtml(investmentFrom) + "</b> and <b>" + escapeHtml(investmentTo) +
                "</b> has been checked 100% and found to be correct. We also certify that the amount has already been paid to the investors after deducting applicable Tax as per the prevailing IT rules.</p>" +
                "<p style='margin-top:16px;'>Details are as under :</p>" +
                "<table aria-label='Half yearly interest summary'><tr>" +
                "<th>Sr.No</th><th>Name of the Branch</th><th>Scheme</th><th>Interest Amount (Rs.)</th><th>Date of Payment</th>" +
                "</tr><tr>" +
                "<td class='center'>1</td>" +
                "<td>" + escapeHtml(ctx.getBranchName()) + "</td>" +
                "<td>" + escapeHtml(schemeLabel) + "</td>" +
                "<td class='right'>" + amountFormatted + "</td>" +
                "<td>" + escapeHtml(paymentDateLabel) + "</td>" +
                "</tr><tr>" +
                "<td colspan='3'></td>" +
                "<td class='right'><b>" + amountFormatted + "</b></td>" +
                "<td></td></tr></table>" +
                "<p style='margin-top:16px;'>Total Amount in words:</p>" +
                "<p><b>" + escapeHtml(amountWords) + "</b></p>" +
                "<p style='margin-top:16px;text-align:justify;'>We certify that the claim does not contain any interest payment(s) that violate Section 23 of the Government Securities Act, 2006</p>" +
                "<p style='margin-top:24px;'>Thanking you,</p>" +
                "<p>Yours faithfully,</p>" +
                "<p style='margin-top:8px;'><b>For HDFC BANK LTD</b></p>" +
                "<p style='margin-top:28px;'>" + escapeHtml(signatoryName) + "</p>" +
                "<p>" + escapeHtml(signatoryId) + "</p>" +
                "</body></html>";
        return wordDoc("Half_Yearly_Interest_Certification_Letter.doc", html);
    }

    private HyGeneratedFile annexureLetter(HyDownloadContext ctx) {
        BigDecimal total = resolveTotalInterestAmount(ctx);
        if (total == null) {
            throw new IllegalArgumentException("Unable to derive total interest amount for the annexure.");
        }
        String schemeLabel = buildSchemeLabelForLetter(ctx.getCriteria());
        String amountFormatted = formatIndianNumber(total);
        String amountWords = convertNumberToWords(total);
        List<BreakUpLine> breakUpLines = resolveAnnexureBreakUpLines(ctx, total);
        int rowCount = breakUpLines.size();
        HalfYearlyInterestScrollDTO firstAnnex = ctx.getAnnexureRows().isEmpty() ? null : ctx.getAnnexureRows().get(0);
        HalfYearlyInterestScrollDTO letter = ctx.getLetterMeta();
        String signatoryName = firstAnnex != null && notBlank(firstAnnex.getSignatoryName()) ? firstAnnex.getSignatoryName()
                : (letter != null && notBlank(letter.getSignatoryName()) ? letter.getSignatoryName() : "Sachin Kewate");
        String signatoryId = firstAnnex != null && notBlank(firstAnnex.getSignatoryEmployeeId()) ? firstAnnex.getSignatoryEmployeeId()
                : (letter != null && notBlank(letter.getSignatoryEmployeeId()) ? letter.getSignatoryEmployeeId() : "S9132");
        String signatoryDept = firstAnnex != null && notBlank(firstAnnex.getSignatoryDepartment())
                ? firstAnnex.getSignatoryDepartment() : "Internal Audit";
        String signatoryLine = escapeHtml(signatoryName);
        if (notBlank(signatoryId)) {
            signatoryLine += " (" + escapeHtml(signatoryId) + ")";
        }

        StringBuilder tableBody = new StringBuilder();
        for (int idx = 0; idx < breakUpLines.size(); idx++) {
            BreakUpLine line = breakUpLines.get(idx);
            tableBody.append("<tr>");
            if (idx == 0) {
                tableBody.append("<td rowspan='").append(rowCount).append("'>").append(escapeHtml(schemeLabel)).append("</td>");
                tableBody.append("<td class='right' rowspan='").append(rowCount).append("'>").append(amountFormatted).append("</td>");
            }
            tableBody.append("<td>").append(escapeHtml(line.details)).append("</td>");
            tableBody.append("<td class='right'>").append(escapeHtml(line.amountFormatted)).append("</td>");
            tableBody.append("<td>").append(escapeHtml(line.paymentDate)).append("</td></tr>");
        }
        tableBody.append("<tr><td colspan='2'><b>Total</b></td>");
        tableBody.append("<td class='right'><b>").append(amountFormatted).append("</b></td>");
        tableBody.append("<td class='right'><b>").append(amountFormatted).append("</b></td>");
        tableBody.append("<td>(Rounded off to nearest decimal)</td></tr>");
        tableBody.append("<tr><td colspan='5'><b>Total Amount in words</b><br/>").append(escapeHtml(amountWords)).append("</td></tr>");

        String html = wordHtmlPrefix("11pt", "'Times New Roman',serif") +
                "<p class='center'>Classification - Internal</p>" +
                "<table aria-label='Annexure'><tr>" +
                "<td colspan='5' class='center'><b>HDFC BANK LTD</b><br/><b>Annexure</b></td></tr><tr>" +
                "<th>Scheme</th><th>Total Interest Amount</th><th>Break Up Details</th><th>Break Up Amount</th><th>Payment Date</th>" +
                "</tr>" + tableBody +
                "</table>" +
                "<p style='margin-top:28px;'>Thanking you,</p><p>Yours faithfully,</p><p><b>For HDFC BANK LTD</b></p>" +
                "<p style='margin-top:24px;'>" + signatoryLine + "</p>" +
                "<p>" + escapeHtml(signatoryDept) + "</p></body></html>";
        return wordDoc("Half_Yearly_Interest_Annexure.doc", html);
    }

    private HyGeneratedFile pdoTallyLetter(HyDownloadContext ctx) {
        List<PdoTallyRow> tallyRows = resolvePdoTallyRows(ctx);
        if (tallyRows.isEmpty()) {
            throw new IllegalArgumentException("Unable to derive PDO-wise interest tally data.");
        }
        String schemeLabel = buildPdoTallySchemeLabel(ctx);
        String title = "PDO-WISE INTEREST TALLY " + schemeLabel;
        String totalLabel = "TOTAL Interest claim @ " + schemeLabel;
        BigDecimal totalAmount = BigDecimal.ZERO;
        boolean hasTotal = false;
        StringBuilder tableBody = new StringBuilder();
        for (PdoTallyRow row : tallyRows) {
            if (row.amount != null) {
                totalAmount = totalAmount.add(row.amount);
                hasTotal = true;
            }
            tableBody.append("<tr><td class='col-code'>").append(escapeHtml(row.pdoCode)).append("</td><td class='col-name'>")
                    .append(escapeHtml(row.pdoName)).append("</td><td class='right col-amt'>")
                    .append(escapeHtml(row.amountFormatted)).append("</td></tr>");
        }
        if (!hasTotal) {
            BigDecimal fallback = resolveTotalInterestAmount(ctx);
            if (fallback != null) {
                totalAmount = fallback;
                hasTotal = true;
            }
        }
        if (!hasTotal) {
            throw new IllegalArgumentException("Unable to derive total interest amount for the tally.");
        }
        String totalFormatted = formatIndianNumber(totalAmount);
        appendPdoTallyTotalRow(tableBody, totalLabel, totalFormatted);

        String html = buildPdoTallyWordHtml(title, tableBody.toString());
        return wordDoc("Half_Yearly_Interest_PDO_Tally.doc", html);
    }

    private HyGeneratedFile pdo22DigitFile(HyDownloadContext ctx) {
        List<String> lines = resolvePdoInterestFileLinesFromProc(ctx);
        if (lines.isEmpty()) {
            List<PdoTallyRow> tallyRows = resolvePdoTallyRows(ctx);
            if (tallyRows.isEmpty()) {
                throw new IllegalArgumentException("Unable to derive PDO-wise amounts for the interest file.");
            }
            String monthYear = derivePdoRecordMonthYear(ctx.getCriteria());
            if (monthYear.length() != 6) {
                throw new IllegalArgumentException("Unable to derive month/year (MMYYYY) for the interest file.");
            }
            for (PdoTallyRow row : tallyRows) {
                if (row.amount == null || row.amount.compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                String pdoDigits = formatPdoCodeForFile(row.pdoCode, ctx.getBranchId());
                for (long chunk : splitPdoFileAmountChunks(row.amount)) {
                    lines.add(buildPdoInterestRecord(pdoDigits, chunk, monthYear));
                }
            }
        }
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("No valid PDO interest records could be generated.");
        }
        String body = String.join("\r\n", lines) + "\r\n";
        return new HyGeneratedFile(body.getBytes(StandardCharsets.UTF_8), "Half_Yearly_PDO_Interest.txt");
    }

    private void appendPdoTallyTotalRow(StringBuilder tableBody, String totalLabel, String totalFormatted) {
        tableBody.append("<tr class='shaded'><td colspan='2' class='col-name'><b>")
                .append(escapeHtml(totalLabel))
                .append("</b></td><td class='right col-amt'><b>")
                .append(totalFormatted)
                .append("</b></td></tr>");
    }

    private String buildPdoTallyWordHtml(String title, String tableBodyHtml) {
        return wordHtmlPrefix("11pt", "Arial,sans-serif",
                "body{margin:24px 16px;}" +
                        ".shaded{background:#d9d9d9;}.title-row{text-align:center;font-weight:bold;}" +
                        "table.tally-table{width:100%;table-layout:fixed;}" +
                        ".col-code{width:12%;}.col-name{width:auto;}.col-amt{width:18%;white-space:nowrap;}") +
                "<p class='center' style='margin-bottom:10px;'>Classification - Internal</p>" +
                "<table class='tally-table' aria-label='PDO wise interest tally'>" +
                "<tr class='shaded title-row'><td colspan='3'>" + escapeHtml(title) + "</td></tr>" +
                "<tr class='shaded'><th class='col-code'>PDO Code</th><th class='col-name'>PDO Name</th>" +
                "<th class='col-amt'>Interest Amount</th></tr>" +
                tableBodyHtml +
                "</table></body></html>";
    }

    private List<String> resolvePdoInterestFileLinesFromProc(HyDownloadContext ctx) {
        List<String> lines = new ArrayList<>();
        for (HalfYearlyInterestScrollDTO row : ctx.getPdoFileLines()) {
            if (row == null || !notBlank(row.getPdoInterestFileLine())) {
                continue;
            }
            lines.add(row.getPdoInterestFileLine().trim());
        }
        return lines;
    }

    private String wordHtmlPrefix(String fontSize, String fontFamily, String extraCss) {
        return "<html xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:w=\"urn:schemas-microsoft-com:office:word\">" +
                "<head><meta charset='utf-8'><style>" +
                "body{font-family:" + fontFamily + ";font-size:" + fontSize + ";line-height:1.45;margin:40px;}" +
                "table{border-collapse:collapse;width:100%;margin-top:12px;}" +
                "th,td{border:1px solid #000;padding:6px;vertical-align:top;}" +
                "th{text-align:center;font-weight:bold;}" +
                ".right{text-align:right;}.center{text-align:center;}" +
                (extraCss != null ? extraCss : "") +
                "</style></head><body>";
    }

    private String wordHtmlPrefix(String fontSize, String fontFamily) {
        return wordHtmlPrefix(fontSize, fontFamily, null);
    }

    private HyGeneratedFile wordDoc(String fileName, String html) {
        byte[] bom = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        byte[] body = html.getBytes(StandardCharsets.UTF_8);
        byte[] content = new byte[bom.length + body.length];
        System.arraycopy(bom, 0, content, 0, bom.length);
        System.arraycopy(body, 0, content, bom.length, body.length);
        return new HyGeneratedFile(content, fileName);
    }

    private class BreakUpLine {
        String details;
        String amountFormatted;
        String paymentDate;
    }

    private class PdoTallyRow {
        String pdoCode;
        String pdoName;
        BigDecimal amount;
        String amountFormatted;
    }

    private List<BreakUpLine> resolveAnnexureBreakUpLines(HyDownloadContext ctx, BigDecimal totalAmount) {
        List<BreakUpLine> lines = new ArrayList<>();
        for (HalfYearlyInterestScrollDTO row : ctx.getAnnexureRows()) {
            if (notBlank(row.getBreakUpDetails()) || notBlank(row.getBreakUpAmount())) {
                BreakUpLine line = new BreakUpLine();
                line.details = nullToEmpty(row.getBreakUpDetails());
                BigDecimal amt = parseIndianAmount(row.getBreakUpAmount());
                line.amountFormatted = amt != null ? formatIndianNumber(amt) : nullToEmpty(row.getBreakUpAmount());
                line.paymentDate = notBlank(row.getBreakUpPaymentDate()) ? row.getBreakUpPaymentDate()
                        : paymentDateFallback(ctx);
                lines.add(line);
            }
        }
        if (!lines.isEmpty()) {
            return lines;
        }
        BreakUpLine fallback = new BreakUpLine();
        fallback.details = "Payment thru ACH";
        fallback.amountFormatted = formatIndianNumber(totalAmount);
        fallback.paymentDate = paymentDateFallback(ctx);
        lines.add(fallback);
        return lines;
    }

    private String paymentDateFallback(HyDownloadContext ctx) {
        HalfYearlyInterestScrollDTO letter = ctx.getLetterMeta();
        if (letter != null && notBlank(letter.getPaymentDateLabel())) {
            return letter.getPaymentDateLabel();
        }
        return deriveAnnexurePaymentDate(ctx.getCriteria());
    }

    private List<PdoTallyRow> resolvePdoTallyRows(HyDownloadContext ctx) {
        List<PdoTallyRow> result = new ArrayList<>();
        for (HalfYearlyInterestScrollDTO row : ctx.getPdoRows()) {
            if (!notBlank(row.getPdoCode()) && !notBlank(row.getPdoName()) && !notBlank(row.getPdoInterestAmount())) {
                continue;
            }
            PdoTallyRow tally = new PdoTallyRow();
            tally.pdoCode = nullToEmpty(row.getPdoCode());
            tally.pdoName = nullToEmpty(row.getPdoName());
            tally.amount = parseIndianAmount(row.getPdoInterestAmount());
            tally.amountFormatted = tally.amount != null ? formatIndianNumber(tally.amount)
                    : nullToEmpty(row.getPdoInterestAmount());
            result.add(tally);
        }
        if (!result.isEmpty()) {
            return result;
        }
        BigDecimal total = resolveTotalInterestAmount(ctx);
        if (total == null) {
            return result;
        }
        PdoTallyRow fallback = new PdoTallyRow();
        fallback.pdoCode = nullToEmpty(ctx.getBranchId());
        fallback.pdoName = nullToEmpty(ctx.getBranchName());
        fallback.amount = total;
        fallback.amountFormatted = formatIndianNumber(total);
        result.add(fallback);
        return result;
    }

    private String buildPdoTallySchemeLabel(HyDownloadContext ctx) {
        for (HalfYearlyInterestScrollDTO row : ctx.getPdoRows()) {
            if (notBlank(row.getPdoTallySchemeLabel())) {
                return row.getPdoTallySchemeLabel();
            }
        }
        return nullToEmpty(ctx.getCriteria().getSchemeName());
    }

    private BigDecimal resolveTotalInterestAmount(HyDownloadContext ctx) {
        HalfYearlyInterestScrollDTO letter = ctx.getLetterMeta();
        if (letter != null && notBlank(letter.getTotalInterestAmount())) {
            return parseIndianAmount(letter.getTotalInterestAmount());
        }
        BigDecimal total = BigDecimal.ZERO;
        boolean hasValue = false;
        for (HalfYearlyInterestScrollDTO row : ctx.getDetailRows()) {
            BigDecimal val = parseIndianAmount(row.getGrossAmount());
            if (val == null) {
                val = parseIndianAmount(row.getAmountPaid());
            }
            if (val != null) {
                total = total.add(val);
                hasValue = true;
            }
        }
        if (!hasValue) {
            for (HalfYearlyInterestScrollDTO row : ctx.getAnnexureRows()) {
                BigDecimal val = parseIndianAmount(row.getBreakUpAmount());
                if (val != null) {
                    total = total.add(val);
                    hasValue = true;
                }
            }
        }
        return hasValue ? total : null;
    }

    private String buildSchemeLabelForLetter(HalfYearlyInterestScrollDTO c) {
        String suffix = "RR".equalsIgnoreCase(c.getInterestPaymentType())
                ? "Reject Re-initiate Int Claim" : "Half Yearly Int Claim";
        return nullToEmpty(c.getSchemeName()) + " (" + suffix + ")";
    }

    private String deriveHalfYearEndedLabel(HalfYearlyInterestScrollDTO c) {
        String month = nullToEmpty(c.getHalfYearMonth()).toLowerCase(Locale.ENGLISH);
        String yr = nullToEmpty(c.getYear());
        if (month.startsWith("jan")) {
            return "June " + yr;
        }
        if (month.startsWith("jul")) {
            return "December " + yr;
        }
        return nullToEmpty(c.getHalfYearMonth()) + (yr.isEmpty() ? "" : " " + yr);
    }

    private String deriveInvestmentFromDate(String financialYear) {
        if (financialYear == null || !financialYear.contains("-")) {
            return "";
        }
        return "1st July " + financialYear.split("-")[0].trim();
    }

    private String deriveInvestmentToDate(String financialYear) {
        if (financialYear == null || !financialYear.contains("-")) {
            return "";
        }
        String[] parts = financialYear.split("-");
        return parts.length > 1 ? "30th June " + parts[1].trim() : "";
    }

    private String deriveAnnexurePaymentDate(HalfYearlyInterestScrollDTO c) {
        String month = nullToEmpty(c.getHalfYearMonth()).toLowerCase(Locale.ENGLISH);
        int yr = parseIntSafe(c.getYear());
        if (yr <= 0) {
            return "";
        }
        String yy = String.valueOf(yr).substring(Math.max(0, String.valueOf(yr).length() - 2));
        if (month.startsWith("jan")) {
            return "01-Jul-" + yy;
        }
        if (month.startsWith("jul")) {
            return "01-Jan-" + String.valueOf(yr + 1).substring(Math.max(0, String.valueOf(yr + 1).length() - 2));
        }
        return "";
    }

    private String derivePdoRecordMonthYear(HalfYearlyInterestScrollDTO c) {
        if ("RR".equalsIgnoreCase(c.getInterestPaymentType()) && notBlank(c.getToDate())) {
            String monthYear = monthYearFromDateString(c.getToDate());
            if (notBlank(monthYear)) {
                return monthYear;
            }
        }
        String month = nullToEmpty(c.getHalfYearMonth()).toLowerCase(Locale.ENGLISH);
        String yr = nullToEmpty(c.getYear()).replaceAll("\\D", "");
        if (yr.length() == 2) {
            yr = "20" + yr;
        }
        if (month.startsWith("jan") && yr.length() == 4) {
            return "06" + yr;
        }
        if (month.startsWith("jul") && yr.length() == 4) {
            return "12" + yr;
        }
        if (yr.length() == 4) {
            return "00" + yr;
        }
        return "";
    }

    /** Builds MMyyyy from the to-date string as sent by the UI (no date normalization). */
    private String monthYearFromDateString(String dateStr) {
        String trimmed = nullToEmpty(dateStr).trim();
        if (trimmed.isEmpty()) {
            return "";
        }
        String[] slashParts = trimmed.split("/");
        if (slashParts.length == 3) {
            String monthPart = slashParts[1].replaceAll("\\D", "");
            String yearPart = slashParts[2].replaceAll("\\D", "");
            if (!monthPart.isEmpty() && yearPart.length() >= 4) {
                int month = Integer.parseInt(monthPart);
                return String.format(Locale.ENGLISH, "%02d%s", month, yearPart.substring(0, 4));
            }
        }
        String[] dashParts = trimmed.split("-");
        if (dashParts.length == 3 && dashParts[2].length() >= 4) {
            String monthPart = dashParts[1].replaceAll("\\D", "");
            String yearPart = dashParts[2].replaceAll("\\D", "");
            if (!monthPart.isEmpty() && yearPart.length() >= 4) {
                int month = Integer.parseInt(monthPart);
                return String.format(Locale.ENGLISH, "%02d%s", month, yearPart.substring(0, 4));
            }
        }
        return "";
    }

    private String formatLetterDate(LocalDate date) {
        int day = date.getDayOfMonth();
        String ordinal = "th";
        if (day % 10 == 1 && day != 11) {
            ordinal = "st";
        } else if (day % 10 == 2 && day != 12) {
            ordinal = "nd";
        } else if (day % 10 == 3 && day != 13) {
            ordinal = "rd";
        }
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return day + ordinal + " " + month + " " + date.getYear();
    }

    private BigDecimal parseIndianAmount(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return new BigDecimal(value.replace(",", "").trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String formatIndianNumber(BigDecimal num) {
        if (num == null) {
            return "0.00";
        }
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("en-IN"));
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(num.setScale(2, RoundingMode.HALF_UP));
    }

    private String convertNumberToWords(BigDecimal num) {
        if (num == null) {
            return "";
        }
        long number = num.setScale(0, RoundingMode.FLOOR).longValue();
        int paise = num.subtract(new BigDecimal(number)).multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP).intValue();
        String result = convertLessThanCrore(number).trim().replace("Lakh", "Lac");
        result = "Rupees " + result;
        if (paise > 0) {
            result += " and " + convertLessThanThousand(paise).trim() + " Paise";
        }
        return result + " only.";
    }

    private String convertLessThanCrore(long number) {
        if (number == 0) {
            return "";
        }
        StringBuilder words = new StringBuilder();
        if (number >= 10000000) {
            words.append(convertLessThanThousand(number / 10000000)).append(" Crore ");
            number %= 10000000;
        }
        if (number >= 100000) {
            words.append(convertLessThanThousand(number / 100000)).append(" Lakh ");
            number %= 100000;
        }
        if (number >= 1000) {
            words.append(convertLessThanThousand(number / 1000)).append(" Thousand ");
            number %= 1000;
        }
        if (number > 0) {
            words.append(convertLessThanThousand(number));
        }
        return words.toString();
    }

    private static final String[] ONES = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String convertLessThanThousand(long n) {
        if (n <= 0) {
            return "";
        }
        if (n >= 1000) {
            return convertLessThanCrore(n);
        }
        if (n < 20) {
            return ONES[(int) n];
        }
        if (n < 100) {
            return TENS[(int) (n / 10)] + (n % 10 != 0 ? " " + ONES[(int) (n % 10)] : "");
        }
        return ONES[(int) (n / 100)] + " Hundred" + (n % 100 != 0 ? " " + convertLessThanThousand(n % 100) : "");
    }

    private String formatPdoCodeForFile(String pdoCode, String branchIdFallback) {
        String digits = nullToEmpty(pdoCode).replaceAll("\\D", "");
        if (digits.isEmpty()) {
            digits = nullToEmpty(branchIdFallback).replaceAll("\\D", "");
        }
        if (digits.length() > 4) {
            digits = digits.substring(digits.length() - 4);
        }
        while (digits.length() < 4) {
            digits = "0" + digits;
        }
        return digits;
    }

    private List<Long> splitPdoFileAmountChunks(BigDecimal amountRupees) {
        long remaining = amountRupees.setScale(0, RoundingMode.HALF_UP).longValue();
        List<Long> chunks = new ArrayList<>();
        while (remaining > 0) {
            long chunk = Math.min(remaining, PDO_FILE_MAX_RUPEES_PER_ROW);
            chunks.add(chunk);
            remaining -= chunk;
        }
        return chunks;
    }

    private String buildPdoInterestRecord(String pdoCode4, long rupeesWhole, String monthYear6) {
        String amountField = String.valueOf(rupeesWhole);
        while (amountField.length() < 12) {
            amountField = "0" + amountField;
        }
        if (amountField.length() > 12) {
            amountField = amountField.substring(amountField.length() - 12);
        }
        return pdoCode4 + amountField + monthYear6;
    }

    private String escapeHtml(String text) {
        return nullToEmpty(text)
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    private boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private int parseIntSafe(String s) {
        try {
            return Integer.parseInt(nullToEmpty(s).replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    @Override
    public ResponseDataDTO getSchemeWiseOutstandingBalanceDetails(SchemeWiseOutstandingBalanceDTO schemeWiseOutstandingBalanceDTO){
        return  rbiBondScrollsBO.getSchemeWiseOutstandingBalanceDetails(schemeWiseOutstandingBalanceDTO);
    }
}
