/**
 *
 */
package com.isl.gbm.transaction.channel;


import java.util.Date;

/**
 * The Class GbmTransactionMasterDto.
 *
 * @author Vasanthi
 */
public class GbmTransactionMasterDto {

    /** The sol id. */
    private String solId;
    private String gbmTranId;
    private String crn;
    private Date valueDate;
    private Boolean isAPI;

    private String head;

    private String tranDateString;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTranDateString() {
        return tranDateString;
    }

    public void setTranDateString(String tranDateString) {
        this.tranDateString = tranDateString;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Boolean getIsAPI() {
        return isAPI;
    }

    public void setIsAPI(Boolean isAPI) {
        this.isAPI = isAPI;
    }

    /** The tran ref id. */
    private String tranRefId;

    /** The tran id. */
    private String tranId;

    /** The part tran id. */
    private Integer partTranId;

    /** The finacle tran id. */
    private String finacleTranId;

    /** The finacle part tran srl num. */
    private String finaclePartTranSrlNum;

    /** The set num. */
    private String setNum;

    /** The tran posted date. */
    private Date tranPostedDate;

    /** The scheme code. */
    private String schemeCode;

    /** The tran mode. */
    private String tranMode;

    /** The tran type. */
    private String tranType;

    /** The acct num. */
    private String acctNum;

    /** The tran leg. */
    private Character tranLeg;

    /** The amount. */
    private Double amount;

    /** The tran date. */
    private Date tranDate;

    /** The navigation flag. */
    private Character navigationFlag;

    /** The remarks. */
    private String remarks;

    /** The created by. */
    private String createdBy;

    /** The created date. */
    private Date createdDate;

    /** The last modified by. */
    private String lastModifiedBy;

    /** The last modified date. */
    private Date lastModifiedDate;

    /** The verified status. */
    private Character verifiedStatus;

    /** The is deleted. */
    private Character isDeleted;

    /** The challan status. */
    private Character challanStatus;

    /** The inter sol id. */
    private String interSolId;

    /** The bank level code. */
    private String bankLevelCode;

    /** The commission. */
    private Double commission;

    /** The service tax amount. */
    private Double serviceTaxAmount;

    /** The total amount. */
    private Double totalAmount;

    /** The account name. */
    private String accountName;

    /** The user id. */
    private String userId;

    /** The zone code. */
    private String zoneCode;

    /** The zone date. */
    private Date zoneDate;

    /** The inst number. */
    private String instNumber;

    private String transactionChannel;


    private Character verified;

    private String instStatus;

    private String screenTranCode;


    /**
     * Gets the account name.
     *
     * @return the account name
     */
    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public String getAccountName() {
        return this.accountName;
    }

    /**
     * Gets the acct num.
     *
     * @return the acct num
     */
    public String getAcctNum() {
        return this.acctNum;
    }

    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return this.amount;
    }

    /**
     * Gets the bank level code.
     *
     * @return the bank level code
     */
    public String getBankLevelCode() {
        return this.bankLevelCode;
    }

    /**
     * Gets the challan status.
     *
     * @return the challan status
     */
    public Character getChallanStatus() {
        return this.challanStatus;
    }

    /**
     * Gets the commission.
     *
     * @return the commission
     */
    public Double getCommission() {
        return this.commission;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the finacle part tran srl num.
     *
     * @return the finacle part tran srl num
     */
    public String getFinaclePartTranSrlNum() {
        return this.finaclePartTranSrlNum;
    }

    /**
     * Gets the finacle tran id.
     *
     * @return the finacle tran id
     */
    public String getFinacleTranId() {
        return this.finacleTranId;
    }

    /**
     * Gets the inst number.
     *
     * @return the inst number
     */
    public String getInstNumber() {
        return this.instNumber;
    }

    /**
     * Gets the inter sol id.
     *
     * @return the inter sol id
     */
    public String getInterSolId() {
        return this.interSolId;
    }

    /**
     * Gets the checks if is deleted.
     *
     * @return the checks if is deleted
     */
    public Character getIsDeleted() {
        return this.isDeleted;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the navigation flag.
     *
     * @return the navigation flag
     */
    public Character getNavigationFlag() {
        return this.navigationFlag;
    }

    /**
     * Gets the part tran id.
     *
     * @return the part tran id
     */
    public Integer getPartTranId() {
        return this.partTranId;
    }

    /**
     * Gets the remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * Gets the service tax amount.
     *
     * @return the service tax amount
     */
    public Double getServiceTaxAmount() {
        return this.serviceTaxAmount;
    }

    /**
     * Gets the sets the num.
     *
     * @return the sets the num
     */
    public String getSetNum() {
        return this.setNum;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return this.solId;
    }

    /**
     * Gets the total amount.
     *
     * @return the total amount
     */
    public Double getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * Gets the tran date.
     *
     * @return the tran date
     */


    /**
     * Gets the tran id.
     *
     * @return the tran id
     */
    public String getTranId() {
        return this.tranId;
    }

    /**
     * Gets the tran leg.
     *
     * @return the tran leg
     */
    public Character getTranLeg() {
        return this.tranLeg;
    }

    /**
     * Gets the tran mode.
     *
     * @return the tran mode
     */
    public String getTranMode() {
        return this.tranMode;
    }

    /**
     * Gets the tran posted date.
     *
     * @return the tran posted date
     */
    public Date getTranPostedDate() {
        return this.tranPostedDate;
    }

    /**
     * Gets the tran ref id.
     *
     * @return the tran ref id
     */
    public String getTranRefId() {
        return this.tranRefId;
    }

    /**
     * Gets the tran type.
     *
     * @return the tran type
     */
    public String getTranType() {
        return this.tranType;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Gets the verified status.
     *
     * @return the verified status
     */
    public Character getVerifiedStatus() {
        return this.verifiedStatus;
    }

    /**
     * Gets the zone code.
     *
     * @return the zone code
     */
    public String getZoneCode() {
        return this.zoneCode;
    }

    /**
     * Gets the zone date.
     *
     * @return the zone date
     */
    public Date getZoneDate() {
        return this.zoneDate;
    }

    /**
     * Sets the account name.
     *
     * @param accountName
     *            the new account name
     */
    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    /**
     * Sets the acct num.
     *
     * @param acctNum
     *            the new acct num
     */
    public void setAcctNum(final String acctNum) {
        this.acctNum = acctNum;
    }

    /**
     * Sets the amount.
     *
     * @param amount
     *            the new amount
     */
    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    /**
     * Sets the bank level code.
     *
     * @param bankLevelCode
     *            the new bank level code
     */
    public void setBankLevelCode(final String bankLevelCode) {
        this.bankLevelCode = bankLevelCode;
    }

    /**
     * Sets the challan status.
     *
     * @param challanStatus
     *            the new challan status
     */
    public void setChallanStatus(final Character challanStatus) {
        this.challanStatus = challanStatus;
    }

    /**
     * Sets the commission.
     *
     * @param commission
     *            the new commission
     */
    public void setCommission(final Double commission) {
        this.commission = commission;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy
     *            the new created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate
     *            the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the finacle part tran srl num.
     *
     * @param finaclePartTranSrlNum
     *            the new finacle part tran srl num
     */
    public void setFinaclePartTranSrlNum(final String finaclePartTranSrlNum) {
        this.finaclePartTranSrlNum = finaclePartTranSrlNum;
    }

    /**
     * Sets the finacle tran id.
     *
     * @param finacleTranId
     *            the new finacle tran id
     */
    public void setFinacleTranId(final String finacleTranId) {
        this.finacleTranId = finacleTranId;
    }

    /**
     * Sets the inst number.
     *
     * @param instNumber
     *            the new inst number
     */
    public void setInstNumber(final String instNumber) {
        this.instNumber = instNumber != null ? instNumber : "";
    }

    /**
     * Sets the inter sol id.
     *
     * @param interSolId
     *            the new inter sol id
     */
    public void setInterSolId(final String interSolId) {
        this.interSolId = interSolId;
    }

    /**
     * Sets the checks if is deleted.
     *
     * @param isDeleted
     *            the new checks if is deleted
     */
    public void setIsDeleted(final Character isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy
     *            the new last modified by
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate
     *            the new last modified date
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the navigation flag.
     *
     * @param navigationFlag
     *            the new navigation flag
     */
    public void setNavigationFlag(final Character navigationFlag) {
        this.navigationFlag = navigationFlag;
    }

    /**
     * Sets the part tran id.
     *
     * @param partTranId
     *            the new part tran id
     */
    public void setPartTranId(final Integer partTranId) {
        this.partTranId = partTranId;
    }

    /**
     * Sets the remarks.
     *
     * @param remarks
     *            the new remarks
     */
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * Sets the scheme code.
     *
     * @param schemeCode
     *            the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * Sets the service tax amount.
     *
     * @param serviceTaxAmount
     *            the new service tax amount
     */
    public void setServiceTaxAmount(final Double serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    /**
     * Sets the sets the num.
     *
     * @param setNum
     *            the new sets the num
     */
    public void setSetNum(final String setNum) {
        this.setNum = setNum;
    }

    /**
     * Sets the sol id.
     *
     * @param solId
     *            the new sol id
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the total amount.
     *
     * @param totalAmount
     *            the new total amount
     */
    public void setTotalAmount(final Double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Boolean getAPI() {
        return isAPI;
    }

    public void setAPI(Boolean API) {
        isAPI = API;
    }


    /**
     * Sets the tran id.
     *
     * @param tranId
     *            the new tran id
     */
    public void setTranId(final String tranId) {
        this.tranId = tranId;
    }

    /**
     * Sets the tran leg.
     *
     * @param tranLeg
     *            the new tran leg
     */
    public void setTranLeg(final Character tranLeg) {
        this.tranLeg = tranLeg;
    }

    /**
     * Sets the tran mode.
     *
     * @param tranMode
     *            the new tran mode
     */
    public void setTranMode(final String tranMode) {
        this.tranMode = tranMode;
    }

    /**
     * Sets the tran posted date.
     *
     * @param tranPostedDate
     *            the new tran posted date
     */
    public void setTranPostedDate(final Date tranPostedDate) {
        this.tranPostedDate = tranPostedDate;
    }

    /**
     * Sets the tran ref id.
     *
     * @param tranRefId
     *            the new tran ref id
     */
    public void setTranRefId(final String tranRefId) {
        this.tranRefId = tranRefId;
    }

    /**
     * Sets the tran type.
     *
     * @param tranType
     *            the new tran type
     */
    public void setTranType(final String tranType) {
        this.tranType = tranType;
    }

    /**
     * Sets the user id.
     *
     * @param userId
     *            the new user id
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * Sets the verified status.
     *
     * @param verifiedStatus
     *            the new verified status
     */
    public void setVerifiedStatus(final Character verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    /**
     * Sets the zone code.
     *
     * @param zoneCode
     *            the new zone code
     */
    public void setZoneCode(final String zoneCode) {
        this.zoneCode = zoneCode;
    }

    /**
     * Sets the zone date.
     *
     * @param zoneDate
     *            the new zone date
     */
    public void setZoneDate(final Date zoneDate) {
        this.zoneDate = zoneDate;
    }


    public String getTransactionChannel() {
        return transactionChannel;
    }

    public void setTransactionChannel(String transactionChannel) {
        this.transactionChannel = transactionChannel;
    }

    public Character getVerified() {
        return verified;
    }

    public void setVerified(Character verified) {
        this.verified = verified;
    }

    public String getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(String instStatus) {
        this.instStatus = instStatus;
    }

    public String getScreenTranCode() {
        return screenTranCode;
    }

    public void setScreenTranCode(String screenTranCode) {
        this.screenTranCode = screenTranCode;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }
}
