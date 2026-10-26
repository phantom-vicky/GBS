/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vasanthi
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GbmBctView implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private String bankCode;


    private String bankBranchRemarks;

    private String bcbCode;


    private String branchAddr1;

    /** The branch addr 2. */

    private String branchAddr2;

    /** The branch addr 3. */

    private String branchAddr3;


    private String branchCityCode;

    /** The branch state code. */

    private String branchStateCode;

    /** The branch pin code. */

    private String branchPinCode;


    private String branchCode;


    private String branchName;

    /** The bank weekly off. */

    private Character branchWeeklyOff;

    /** The delete flag. */
    @JsonIgnore

    private Character deleteFlag;

    /** The micr centre code. */

    private String micrCentreCode;

    /** The micr bank code. */
    private String micrBankCode;

    /** The micr branch code. */

    private String micrBranchCode;

    /** The ts count. */

    private Number tsCount;


    private String clgRefCode;


    private String clgRepCode;


    private String countryCode;


    private String collAgentBankCode;


    private String collAgentBranchCode;


    private String coBranchCode;


    private Number daysForValueDate;


    private String dcAlias;


    private String ddIssBranchCode;


    private String ddIssBranchName;


    private String doBranchCode;


    private String faxNum;


    private Character fcIbtAlwdFlag;


    private String freeCode1;


    private String freeCode2;


    private String freeCode3;


    private Character frnBranchFlag;


    private Character inwardTTFlag;


    private Date lchgTime;


    private String lchgUserId;


    private Character localClgFlag;


    private String ourOfficeBankCode;


    private String ourOfficeCode;


    private Character outwardTTFlag;


    private Date rcreTime;


    private String phoneNumber;


    private String rcreUserId;


    private String routingBankCode;


    private String routingBranchCode;


    private Character splCollCentreFlag;


    private String tlxNum;


    private Character tradeFinanceBranch;

    /*
     * public GbmBctView(final String bankCode, final String branchCode, final
     * String branchName) {
     *
     * this.bankCode = bankCode; this.branchCode = branchCode; this.branchName =
     * branchName; }
     */

    /**
     * @return the bankBranchRemarks
     */
    public String getBankBranchRemarks() {
        return this.bankBranchRemarks;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * @return the bcbCode
     */
    public String getBcbCode() {
        return this.bcbCode;
    }

    /**
     * @return the branchAddr1
     */
    public String getBranchAddr1() {
        return this.branchAddr1;
    }

    /**
     * @return the branchAddr2
     */
    public String getBranchAddr2() {
        return this.branchAddr2;
    }

    /**
     * @return the branchAddr3
     */
    public String getBranchAddr3() {
        return this.branchAddr3;
    }

    /**
     * @return the branchCityCode
     */
    public String getBranchCityCode() {
        return this.branchCityCode;
    }

    /**
     * @return the branchCode
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * @return the branchPinCode
     */
    public String getBranchPinCode() {
        return this.branchPinCode;
    }

    /**
     * @return the branchStateCode
     */
    public String getBranchStateCode() {
        return this.branchStateCode;
    }

    /**
     * @return the branchWeeklyOff
     */
    public Character getBranchWeeklyOff() {
        return this.branchWeeklyOff;
    }

    /**
     * @return the clgRefCode
     */
    public String getClgRefCode() {
        return this.clgRefCode;
    }

    /**
     * @return the clgRepCode
     */
    public String getClgRepCode() {
        return this.clgRepCode;
    }

    /**
     * @return the coBranchCode
     */
    public String getCoBranchCode() {
        return this.coBranchCode;
    }

    /**
     * @return the collAgentBankCode
     */
    public String getCollAgentBankCode() {
        return this.collAgentBankCode;
    }

    /**
     * @return the collAgentBranchCode
     */
    public String getCollAgentBranchCode() {
        return this.collAgentBranchCode;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @return the daysForValueDate
     */
    public Number getDaysForValueDate() {
        return this.daysForValueDate;
    }

    /**
     * @return the dcAlias
     */
    public String getDcAlias() {
        return this.dcAlias;
    }

    /**
     * @return the ddIssBranchCode
     */
    public String getDdIssBranchCode() {
        return this.ddIssBranchCode;
    }

    /**
     * @return the ddIssBranchName
     */
    public String getDdIssBranchName() {
        return this.ddIssBranchName;
    }

    /**
     * @return the deleteFlag
     */
    public Character getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * @return the doBranchCode
     */
    public String getDoBranchCode() {
        return this.doBranchCode;
    }

    /**
     * @return the faxNum
     */
    public String getFaxNum() {
        return this.faxNum;
    }

    /**
     * @return the fcIbtAlwdFlag
     */
    public Character getFcIbtAlwdFlag() {
        return this.fcIbtAlwdFlag;
    }

    /**
     * @return the freeCode1
     */
    public String getFreeCode1() {
        return this.freeCode1;
    }

    /**
     * @return the freeCode2
     */
    public String getFreeCode2() {
        return this.freeCode2;
    }

    /**
     * @return the freeCode3
     */
    public String getFreeCode3() {
        return this.freeCode3;
    }

    /**
     * @return the frnBranchFlag
     */
    public Character getFrnBranchFlag() {
        return this.frnBranchFlag;
    }

    /**
     * @return the inwardTTFlag
     */
    public Character getInwardTTFlag() {
        return this.inwardTTFlag;
    }

    /**
     * @return the lchgTime
     */
    public Date getLchgTime() {
        return this.lchgTime;
    }

    /**
     * @return the lchgUserId
     */
    public String getLchgUserId() {
        return this.lchgUserId;
    }

    /**
     * @return the localClgFlag
     */
    public Character getLocalClgFlag() {
        return this.localClgFlag;
    }

    /**
     * @return the micrBankCode
     */
    public String getMicrBankCode() {
        return this.micrBankCode;
    }

    /**
     * @return the micrBranchCode
     */
    public String getMicrBranchCode() {
        return this.micrBranchCode;
    }

    /**
     * @return the micrCentreCode
     */
    public String getMicrCentreCode() {
        return this.micrCentreCode;
    }

    /**
     * @return the ourOfficeBankCode
     */
    public String getOurOfficeBankCode() {
        return this.ourOfficeBankCode;
    }

    /**
     * @return the ourOfficeCode
     */
    public String getOurOfficeCode() {
        return this.ourOfficeCode;
    }

    /**
     * @return the outwardTTFlag
     */
    public Character getOutwardTTFlag() {
        return this.outwardTTFlag;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @return the rcreTime
     */
    public Date getRcreTime() {
        return this.rcreTime;
    }

    /**
     * @return the rcreUserId
     */
    public String getRcreUserId() {
        return this.rcreUserId;
    }

    /**
     * @return the routingBankCode
     */
    public String getRoutingBankCode() {
        return this.routingBankCode;
    }

    /**
     * @return the routingBranchCode
     */
    public String getRoutingBranchCode() {
        return this.routingBranchCode;
    }

    /**
     * @return the splCollCentreFlag
     */
    public Character getSplCollCentreFlag() {
        return this.splCollCentreFlag;
    }

    /**
     * @return the tlxNum
     */
    public String getTlxNum() {
        return this.tlxNum;
    }

    /**
     * @return the tradeFinanceBranch
     */
    public Character getTradeFinanceBranch() {
        return this.tradeFinanceBranch;
    }

    /**
     * @return the tsCount
     */
    public Number getTsCount() {
        return this.tsCount;
    }

    /**
     * @param bankBranchRemarks
     *            the bankBranchRemarks to set
     */
    public void setBankBranchRemarks(final String bankBranchRemarks) {
        this.bankBranchRemarks = bankBranchRemarks;
    }

    /**
     * @param bankCode
     *            the bankCode to set
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @param bcbCode
     *            the bcbCode to set
     */
    public void setBcbCode(final String bcbCode) {
        this.bcbCode = bcbCode;
    }

    /**
     * @param branchAddr1
     *            the branchAddr1 to set
     */
    public void setBranchAddr1(final String branchAddr1) {
        this.branchAddr1 = branchAddr1;
    }

    /**
     * @param branchAddr2
     *            the branchAddr2 to set
     */
    public void setBranchAddr2(final String branchAddr2) {
        this.branchAddr2 = branchAddr2;
    }

    /**
     * @param branchAddr3
     *            the branchAddr3 to set
     */
    public void setBranchAddr3(final String branchAddr3) {
        this.branchAddr3 = branchAddr3;
    }

    /**
     * @param branchCityCode
     *            the branchCityCode to set
     */
    public void setBranchCityCode(final String branchCityCode) {
        this.branchCityCode = branchCityCode;
    }

    /**
     * @param branchCode
     *            the branchCode to set
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @param branchName
     *            the branchName to set
     */
    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    /**
     * @param branchPinCode
     *            the branchPinCode to set
     */
    public void setBranchPinCode(final String branchPinCode) {
        this.branchPinCode = branchPinCode;
    }

    /**
     * @param branchStateCode
     *            the branchStateCode to set
     */
    public void setBranchStateCode(final String branchStateCode) {
        this.branchStateCode = branchStateCode;
    }

    /**
     * @param branchWeeklyOff
     *            the branchWeeklyOff to set
     */
    public void setBranchWeeklyOff(final Character branchWeeklyOff) {
        this.branchWeeklyOff = branchWeeklyOff;
    }

    /**
     * @param clgRefCode
     *            the clgRefCode to set
     */
    public void setClgRefCode(final String clgRefCode) {
        this.clgRefCode = clgRefCode;
    }

    /**
     * @param clgRepCode
     *            the clgRepCode to set
     */
    public void setClgRepCode(final String clgRepCode) {
        this.clgRepCode = clgRepCode;
    }

    /**
     * @param coBranchCode
     *            the coBranchCode to set
     */
    public void setCoBranchCode(final String coBranchCode) {
        this.coBranchCode = coBranchCode;
    }

    /**
     * @param collAgentBankCode
     *            the collAgentBankCode to set
     */
    public void setCollAgentBankCode(final String collAgentBankCode) {
        this.collAgentBankCode = collAgentBankCode;
    }

    /**
     * @param collAgentBranchCode
     *            the collAgentBranchCode to set
     */
    public void setCollAgentBranchCode(final String collAgentBranchCode) {
        this.collAgentBranchCode = collAgentBranchCode;
    }

    /**
     * @param countryCode
     *            the countryCode to set
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @param daysForValueDate
     *            the daysForValueDate to set
     */
    public void setDaysForValueDate(final Number daysForValueDate) {
        this.daysForValueDate = daysForValueDate;
    }

    /**
     * @param dcAlias
     *            the dcAlias to set
     */
    public void setDcAlias(final String dcAlias) {
        this.dcAlias = dcAlias;
    }

    /**
     * @param ddIssBranchCode
     *            the ddIssBranchCode to set
     */
    public void setDdIssBranchCode(final String ddIssBranchCode) {
        this.ddIssBranchCode = ddIssBranchCode;
    }

    /**
     * @param ddIssBranchName
     *            the ddIssBranchName to set
     */
    public void setDdIssBranchName(final String ddIssBranchName) {
        this.ddIssBranchName = ddIssBranchName;
    }

    /**
     * @param deleteFlag
     *            the deleteFlag to set
     */
    public void setDeleteFlag(final Character deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * @param doBranchCode
     *            the doBranchCode to set
     */
    public void setDoBranchCode(final String doBranchCode) {
        this.doBranchCode = doBranchCode;
    }

    /**
     * @param faxNum
     *            the faxNum to set
     */
    public void setFaxNum(final String faxNum) {
        this.faxNum = faxNum;
    }

    /**
     * @param fcIbtAlwdFlag
     *            the fcIbtAlwdFlag to set
     */
    public void setFcIbtAlwdFlag(final Character fcIbtAlwdFlag) {
        this.fcIbtAlwdFlag = fcIbtAlwdFlag;
    }

    /**
     * @param freeCode1
     *            the freeCode1 to set
     */
    public void setFreeCode1(final String freeCode1) {
        this.freeCode1 = freeCode1;
    }

    /**
     * @param freeCode2
     *            the freeCode2 to set
     */
    public void setFreeCode2(final String freeCode2) {
        this.freeCode2 = freeCode2;
    }

    /**
     * @param freeCode3
     *            the freeCode3 to set
     */
    public void setFreeCode3(final String freeCode3) {
        this.freeCode3 = freeCode3;
    }

    /**
     * @param frnBranchFlag
     *            the frnBranchFlag to set
     */
    public void setFrnBranchFlag(final Character frnBranchFlag) {
        this.frnBranchFlag = frnBranchFlag;
    }

    /**
     * @param inwardTTFlag
     *            the inwardTTFlag to set
     */
    public void setInwardTTFlag(final Character inwardTTFlag) {
        this.inwardTTFlag = inwardTTFlag;
    }

    /**
     * @param lchgTime
     *            the lchgTime to set
     */
    public void setLchgTime(final Date lchgTime) {
        this.lchgTime = lchgTime;
    }

    /**
     * @param lchgUserId
     *            the lchgUserId to set
     */
    public void setLchgUserId(final String lchgUserId) {
        this.lchgUserId = lchgUserId;
    }

    /**
     * @param localClgFlag
     *            the localClgFlag to set
     */
    public void setLocalClgFlag(final Character localClgFlag) {
        this.localClgFlag = localClgFlag;
    }

    /**
     * @param micrBankCode
     *            the micrBankCode to set
     */
    public void setMicrBankCode(final String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * @param micrBranchCode
     *            the micrBranchCode to set
     */
    public void setMicrBranchCode(final String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * @param micrCentreCode
     *            the micrCentreCode to set
     */
    public void setMicrCentreCode(final String micrCentreCode) {
        this.micrCentreCode = micrCentreCode;
    }

    /**
     * @param ourOfficeBankCode
     *            the ourOfficeBankCode to set
     */
    public void setOurOfficeBankCode(final String ourOfficeBankCode) {
        this.ourOfficeBankCode = ourOfficeBankCode;
    }

    /**
     * @param ourOfficeCode
     *            the ourOfficeCode to set
     */
    public void setOurOfficeCode(final String ourOfficeCode) {
        this.ourOfficeCode = ourOfficeCode;
    }

    /**
     * @param outwardTTFlag
     *            the outwardTTFlag to set
     */
    public void setOutwardTTFlag(final Character outwardTTFlag) {
        this.outwardTTFlag = outwardTTFlag;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param rcreTime
     *            the rcreTime to set
     */
    public void setRcreTime(final Date rcreTime) {
        this.rcreTime = rcreTime;
    }

    /**
     * @param rcreUserId
     *            the rcreUserId to set
     */
    public void setRcreUserId(final String rcreUserId) {
        this.rcreUserId = rcreUserId;
    }

    /**
     * @param routingBankCode
     *            the routingBankCode to set
     */
    public void setRoutingBankCode(final String routingBankCode) {
        this.routingBankCode = routingBankCode;
    }

    /**
     * @param routingBranchCode
     *            the routingBranchCode to set
     */
    public void setRoutingBranchCode(final String routingBranchCode) {
        this.routingBranchCode = routingBranchCode;
    }

    /**
     * @param splCollCentreFlag
     *            the splCollCentreFlag to set
     */
    public void setSplCollCentreFlag(final Character splCollCentreFlag) {
        this.splCollCentreFlag = splCollCentreFlag;
    }

    /**
     * @param tlxNum
     *            the tlxNum to set
     */
    public void setTlxNum(final String tlxNum) {
        this.tlxNum = tlxNum;
    }

    /**
     * @param tradeFinanceBranch
     *            the tradeFinanceBranch to set
     */
    public void setTradeFinanceBranch(final Character tradeFinanceBranch) {
        this.tradeFinanceBranch = tradeFinanceBranch;
    }

    /**
     * @param tsCount
     *            the tsCount to set
     */
    public void setTsCount(final Number tsCount) {
        this.tsCount = tsCount;
    }

}
