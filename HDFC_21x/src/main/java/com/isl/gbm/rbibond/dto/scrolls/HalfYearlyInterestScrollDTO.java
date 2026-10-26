package com.isl.gbm.rbibond.dto.scrolls;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HalfYearlyInterestScrollDTO {

    private String schemeCode;
    private String schemeName;
    private String schemeStatus;
    /** HY = Half Yearly Payment, RR = Reject Re-initiate Payment */
    private String interestPaymentType;
    private String halfYearMonth;
    private String year;
    private String fromDate;
    private String toDate;
    private String financialYear;
    private String flag;
    /** CERTIFICATION, ANNEXURE, PDO_TALLY, PDO_22_DIGIT */
    private String downloadType;

    private String blaNumber;
    private String paymentAmount;
    private String principalAmount;
    private String dueDate;
    private String grossAmount;
    private String interestPaidForLastBrokenPeriod;
    private String interestPaidForFirstBrokenPeriod;
    private String creditedToBankAccount;
    private String amountPaid;
    private String remarks;
    private String initials;

    /** Certification letter (flag L) */
    private String halfYearEnded;
    private String investmentFromDate;
    private String investmentToDate;
    private String totalInterestAmount;
    private String paymentDateLabel;
    private String signatoryName;
    private String signatoryEmployeeId;

    /** Annexure letter (flag A) */
    private String breakUpDetails;
    private String breakUpAmount;
    private String breakUpPaymentDate;
    private String signatoryDepartment;

    /** PDO-wise interest tally (flag P) */
    private String pdoCode;
    private String pdoName;
    private String pdoInterestAmount;
    private String pdoTallySchemeLabel;

    /** Preformatted 22-digit PDO interest file row (flag D), same pattern as Brokerage text file lines. */
    private String pdoInterestFileLine;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getInterestPaymentType() {
        return interestPaymentType;
    }

    public void setInterestPaymentType(String interestPaymentType) {
        this.interestPaymentType = interestPaymentType;
    }

    public String getHalfYearMonth() {
        return halfYearMonth;
    }

    public void setHalfYearMonth(String halfYearMonth) {
        this.halfYearMonth = halfYearMonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getBlaNumber() {
        return blaNumber;
    }

    public void setBlaNumber(String blaNumber) {
        this.blaNumber = blaNumber;
    }

    public String getPaymentAmount() { return paymentAmount; }

    public void setPaymentAmount(String paymentAmount) { this.paymentAmount = paymentAmount; }

    public String getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(String principalAmount) {
        this.principalAmount = principalAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getInterestPaidForLastBrokenPeriod() {
        return interestPaidForLastBrokenPeriod;
    }

    public void setInterestPaidForLastBrokenPeriod(String interestPaidForLastBrokenPeriod) {
        this.interestPaidForLastBrokenPeriod = interestPaidForLastBrokenPeriod;
    }

    public String getInterestPaidForFirstBrokenPeriod() {
        return interestPaidForFirstBrokenPeriod;
    }

    public void setInterestPaidForFirstBrokenPeriod(String interestPaidForFirstBrokenPeriod) {
        this.interestPaidForFirstBrokenPeriod = interestPaidForFirstBrokenPeriod;
    }

    public String getCreditedToBankAccount() {
        return creditedToBankAccount;
    }

    public void setCreditedToBankAccount(String creditedToBankAccount) {
        this.creditedToBankAccount = creditedToBankAccount;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getHalfYearEnded() {
        return halfYearEnded;
    }

    public void setHalfYearEnded(String halfYearEnded) {
        this.halfYearEnded = halfYearEnded;
    }

    public String getInvestmentFromDate() {
        return investmentFromDate;
    }

    public void setInvestmentFromDate(String investmentFromDate) {
        this.investmentFromDate = investmentFromDate;
    }

    public String getInvestmentToDate() {
        return investmentToDate;
    }

    public void setInvestmentToDate(String investmentToDate) {
        this.investmentToDate = investmentToDate;
    }

    public String getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public void setTotalInterestAmount(String totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public String getPaymentDateLabel() {
        return paymentDateLabel;
    }

    public void setPaymentDateLabel(String paymentDateLabel) {
        this.paymentDateLabel = paymentDateLabel;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getSignatoryEmployeeId() {
        return signatoryEmployeeId;
    }

    public void setSignatoryEmployeeId(String signatoryEmployeeId) {
        this.signatoryEmployeeId = signatoryEmployeeId;
    }

    public String getBreakUpDetails() {
        return breakUpDetails;
    }

    public void setBreakUpDetails(String breakUpDetails) {
        this.breakUpDetails = breakUpDetails;
    }

    public String getBreakUpAmount() {
        return breakUpAmount;
    }

    public void setBreakUpAmount(String breakUpAmount) {
        this.breakUpAmount = breakUpAmount;
    }

    public String getBreakUpPaymentDate() {
        return breakUpPaymentDate;
    }

    public void setBreakUpPaymentDate(String breakUpPaymentDate) {
        this.breakUpPaymentDate = breakUpPaymentDate;
    }

    public String getSignatoryDepartment() {
        return signatoryDepartment;
    }

    public void setSignatoryDepartment(String signatoryDepartment) {
        this.signatoryDepartment = signatoryDepartment;
    }

    public String getPdoCode() {
        return pdoCode;
    }

    public void setPdoCode(String pdoCode) {
        this.pdoCode = pdoCode;
    }

    public String getPdoName() {
        return pdoName;
    }

    public void setPdoName(String pdoName) {
        this.pdoName = pdoName;
    }

    public String getPdoInterestAmount() {
        return pdoInterestAmount;
    }

    public void setPdoInterestAmount(String pdoInterestAmount) {
        this.pdoInterestAmount = pdoInterestAmount;
    }

    public String getPdoTallySchemeLabel() {
        return pdoTallySchemeLabel;
    }

    public void setPdoTallySchemeLabel(String pdoTallySchemeLabel) {
        this.pdoTallySchemeLabel = pdoTallySchemeLabel;
    }

    public String getPdoInterestFileLine() {
        return pdoInterestFileLine;
    }

    public void setPdoInterestFileLine(String pdoInterestFileLine) {
        this.pdoInterestFileLine = pdoInterestFileLine;
    }
}
