package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.rbibond.dto.ScssGttAccountOpeningDto;
import com.isl.gbm.transaction.channel.CbsTransactionResponseDetails;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DdTransactionApiDto {
    private String status;
    private String cbsTranStatusDesc;
    private String respCode;
    private String scssAccountNumber;
    private String accountNum;

    public String getCbsTranStatusDesc() {
        return cbsTranStatusDesc;
    }

    public void setCbsTranStatusDesc(String cbsTranStatusDesc) {
        this.cbsTranStatusDesc = cbsTranStatusDesc;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    private Character scssTranLeg;

    public Character getScssTranLeg() {
        return scssTranLeg;
    }

    public void setScssTranLeg(Character scssTranLeg) {
        this.scssTranLeg = scssTranLeg;
    }

    public String getScssAccountNumber() {
        return scssAccountNumber;
    }

    public void setScssAccountNumber(String scssAccountNumber) {
        this.scssAccountNumber = scssAccountNumber;
    }



    private CbsTransactionResponseDetails cbsTransactionResponseDetails;
    private DdTransactionApiSuccessDto ddTransactionApiSuccessDto;
    private ScssGttAccountOpeningDto scssGttAccountOpeningDto;
    private ScssTransferOutOtherBankDTO transferDto;

    List<ScssTransferOutOtherBankDTO> scssGttAccountOpeningDtoList1;

    public List<ScssTransferOutOtherBankDTO> getScssGttAccountOpeningDtoList1() {
        return scssGttAccountOpeningDtoList1;
    }

    public void setScssGttAccountOpeningDtoList1(List<ScssTransferOutOtherBankDTO> scssGttAccountOpeningDtoList1) {
        this.scssGttAccountOpeningDtoList1 = scssGttAccountOpeningDtoList1;
    }

    public ScssTransferOutOtherBankDTO getTransferDto() {
        return transferDto;
    }

    public void setTransferDto(ScssTransferOutOtherBankDTO transferDto) {
        this.transferDto = transferDto;
    }

    public Double getScssAmount() {
        return scssAmount;
    }

    public void setScssAmount(Double scssAmount) {
        this.scssAmount = scssAmount;
    }

    private Double scssAmount;

    public ScssGttAccountOpeningDto getScssGttAccountOpeningDto() {
        return scssGttAccountOpeningDto;
    }

    public void setScssGttAccountOpeningDto(ScssGttAccountOpeningDto scssGttAccountOpeningDto) {
        this.scssGttAccountOpeningDto = scssGttAccountOpeningDto;
    }

    public DdTransactionApiSuccessDto getDdTransactionApiSuccessDto() {
        return ddTransactionApiSuccessDto;
    }

    public void setDdTransactionApiSuccessDto(DdTransactionApiSuccessDto ddTransactionApiSuccessDto) {
        this.ddTransactionApiSuccessDto = ddTransactionApiSuccessDto;
    }

    public CbsTransactionResponseDetails getCbsTransactionResponseDetails() {
        return cbsTransactionResponseDetails;
    }

    public void setCbsTransactionResponseDetails(CbsTransactionResponseDetails cbsTransactionResponseDetails) {
        this.cbsTransactionResponseDetails = cbsTransactionResponseDetails;
    }

    private String transferToBank;
    private String custAcctNumber;

    /** The request uuid. */
    private String requestUuid;

    /** The cbs transaction id. */
    private String cbsTransactionId;

    /** The cbs transaction date. */
    private Date cbsTransactionDate;

    /** The error code. */
    private String errorCode;

    /** The error description. */
    private String errorDescription;

    /** The error source. */
    private String errorSource;

    public String getRequestUuid() {
        return requestUuid;
    }

    public void setRequestUuid(String requestUuid) {
        this.requestUuid = requestUuid;
    }

    public String getCbsTransactionId() {
        return cbsTransactionId;
    }

    public void setCbsTransactionId(String cbsTransactionId) {
        this.cbsTransactionId = cbsTransactionId;
    }

    public Date getCbsTransactionDate() {
        return cbsTransactionDate;
    }

    public void setCbsTransactionDate(Date cbsTransactionDate) {
        this.cbsTransactionDate = cbsTransactionDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public Character getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(Character tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPartTranSerialNumber() {
        return partTranSerialNumber;
    }

    public void setPartTranSerialNumber(String partTranSerialNumber) {
        this.partTranSerialNumber = partTranSerialNumber;
    }

    public String getFinacleResponseStatus() {
        return finacleResponseStatus;
    }

    public void setFinacleResponseStatus(String finacleResponseStatus) {
        this.finacleResponseStatus = finacleResponseStatus;
    }

    /** The error type. */
    private String errorType;

    /** The set number. */
    private String setNumber;

    /** The tran status. */
    private Character tranStatus;

    /** The message. */
    private String message;

    /** The part tran serial number. */
    private String partTranSerialNumber;

    /** The finacle response status. */
    private String finacleResponseStatus;


    public String getCustAcctNumber() {
        return custAcctNumber;
    }

    public void setCustAcctNumber(String custAcctNumber) {
        this.custAcctNumber = custAcctNumber;
    }

    public String getTransferToBank() {
        return transferToBank;
    }

    public void setTransferToBank(String transferToBank) {
        this.transferToBank = transferToBank;
    }

    public Date getCrvalueDate() {
        return crvalueDate;
    }

    private DepositsDTO depositsDTO;

    private String clientId;
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public DepositsDTO getDepositsDTO() {
        return depositsDTO;
    }

    public void setDepositsDTO(DepositsDTO depositsDTO) {
        this.depositsDTO = depositsDTO;
    }

    private String respStatusCode;

    private String respMessage;

    private String requestType;

    private String referenceNumber;
    private String gbmTranId;
    private String TimeZone;


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

    private String ppoNumber;

    private String paymentType;



    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    public String getBankId() {
        return BankId;
    }

    public void setBankId(String bankId) {
        BankId = bankId;
    }

    private String LanguageId="en_US";
    private String BankId;

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
    }

    private String aadhar;
    private String solId;
    private String crpartTranType;
    private String crAcct;
    private String crcrncyCode="INR";
    private Date crvalueDate;

    public void setCrvalueDate(Date crvalueDate) {
        this.crvalueDate = crvalueDate;
    }

    public Date getCrtranDate() {
        return crtranDate;
    }

    private Date crtranDate;

    public void setCrtranDate(Date crtranDate) {
        this.crtranDate = crtranDate;
    }

    private Double crtranAmt;

    public Double getCrtranAmt() {
        return crtranAmt;
    }

    public void setCrtranAmt(Double crtranAmt) {
        this.crtranAmt = crtranAmt;
    }

    private String crtranParticulars;
    private String draweebrCode;
    private String draweebankCode="023";
    private String payeeName;
    private String purAcct;
    private String purcrncyCode;
    private String pursolId;
    private String drpartTranType;

    public String getSolId() {
        return solId;
    }

    public void setSolId(String solId) {
        this.solId = solId;
    }

    public String getCrpartTranType() {
        return crpartTranType;
    }

    public void setCrpartTranType(String crpartTranType) {
        this.crpartTranType = crpartTranType;
    }

    public String getCrAcct() {
        return crAcct;
    }

    public void setCrAcct(String crAcct) {
        this.crAcct = crAcct;
    }

    public String getCrcrncyCode() {
        return crcrncyCode;
    }

    public void setCrcrncyCode(String crcrncyCode) {
        this.crcrncyCode = crcrncyCode;
    }



    public String getCrtranParticulars() {
        return crtranParticulars;
    }

    public void setCrtranParticulars(String crtranParticulars) {
        this.crtranParticulars = crtranParticulars;
    }

    public String getDraweebrCode() {
        return draweebrCode;
    }

    public void setDraweebrCode(String draweebrCode) {
        this.draweebrCode = draweebrCode;
    }

    public String getDraweebankCode() {
        return draweebankCode;
    }

    public void setDraweebankCode(String draweebankCode) {
        this.draweebankCode = draweebankCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPurAcct() {
        return purAcct;
    }

    public void setPurAcct(String purAcct) {
        this.purAcct = purAcct;
    }

    public String getPurcrncyCode() {
        return purcrncyCode;
    }

    public void setPurcrncyCode(String purcrncyCode) {
        this.purcrncyCode = purcrncyCode;
    }

    public String getPursolId() {
        return pursolId;
    }

    public void setPursolId(String pursolId) {
        this.pursolId = pursolId;
    }

    public String getDrpartTranType() {
        return drpartTranType;
    }

    public void setDrpartTranType(String drpartTranType) {
        this.drpartTranType = drpartTranType;
    }

    public String getDrAcct() {
        return drAcct;
    }

    public void setDrAcct(String drAcct) {
        this.drAcct = drAcct;
    }

    public String getDrcrncyCode() {
        return drcrncyCode;
    }

    public void setDrcrncyCode(String drcrncyCode) {
        this.drcrncyCode = drcrncyCode;
    }
//

    public Date getDrvalueDate() {
        return drvalueDate;
    }

    public void setDrvalueDate(Date drvalueDate) {
        this.drvalueDate = drvalueDate;
    }

    public Date getDrtranDate() {
        return drtranDate;
    }

    public void setDrtranDate(Date drtranDate) {
        this.drtranDate = drtranDate;
    }

    public String getdServiceRequestVersion() {
        return dServiceRequestVersion;
    }

    public void setdServiceRequestVersion(String dServiceRequestVersion) {
        this.dServiceRequestVersion = dServiceRequestVersion;
    }

    public Double getDrtranAmt() {
        return drtranAmt;
    }

    public void setDrtranAmt(Double drtranAmt) {
        this.drtranAmt = drtranAmt;
    }
//    public String getDrtranAmt() {
//        return drtranAmt;
//    }
//
//    public void setDrtranAmt(String drtranAmt) {
//        this.drtranAmt = drtranAmt;
//    }

    public String getDrtranParticulars() {
        return drtranParticulars;
    }

    public void setDrtranParticulars(String drtranParticulars) {
        this.drtranParticulars = drtranParticulars;
    }

    private String drAcct;
    private String drcrncyCode;
    private Date drvalueDate;
    private Date drtranDate;
    private Double drtranAmt;
    private String drtranParticulars;

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getsOprAcctNo() {
        return sOprAcctNo;
    }

    public void setsOprAcctNo(String sOprAcctNo) {
        this.sOprAcctNo = sOprAcctNo;
    }

    public String getsMTTScript() {
        return sMTTScript;
    }

    public void setsMTTScript(String sMTTScript) {
        this.sMTTScript = sMTTScript;
    }

    public String getRequestUUID() {
        return RequestUUID;
    }

    public void setRequestUUID(String requestUUID) {
        RequestUUID = requestUUID;
    }

    public String getServiceRequestId() {
        return ServiceRequestId;
    }

    public void setServiceRequestId(String serviceRequestId) {
        ServiceRequestId = serviceRequestId;
    }


    public String getsChannelId() {
        return sChannelId;
    }

    public void setsChannelId(String sChannelId) {
        this.sChannelId = sChannelId;
    }

    public String getsMessageDateTime() {
        return sMessageDateTime;
    }

    public void setsMessageDateTime(String sMessageDateTime) {
        this.sMessageDateTime = sMessageDateTime;
    }

    private String sOprAcctNo;
    private String sMTTScript="FI_GBM_XferTranAdd_DDA_Custom.scr";
    private String RequestUUID;
    private String ServiceRequestId="executeFinacleScript";
    private String dServiceRequestVersion="10.2";
    private String sChannelId="COR";
    private String sMessageDateTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespStatusCode() {
        return respStatusCode;
    }

    public void setRespStatusCode(String respStatusCode) {
        this.respStatusCode = respStatusCode;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getErrorMessageCode() {
        return errorMessageCode;
    }

    public void setErrorMessageCode(String errorMessageCode) {
        this.errorMessageCode = errorMessageCode;
    }

    private String aadharNumber;

    private String accountNo;

    private String errorMessageCode;


    private List<Map<String, Object>> acctDetails;

    private List<Map<String, Object>> tranDetails;

    private List<Map<String, Object>> nomineeDetails;

    private List<Map<String, Object>> instDetails;

    private List<Map<String, Object>> depositDetails;

    private String depAmount;

    private String finYearAmt;

    private String creditActNum;

    private String debitActNum;

    private String debitFlag;

    private String creditFlag;

    private String custId;

    private String ssaAcctId;

    private String depositAmt;

//    private String tranType;

    private String creditActType;

    private String debitActType;

    private String instType;
    private String instNum;
//    private String zoneCode;
    private String isInterSolTorch;
    private String isInterSol;
    private String instDate;

//    private String zoneDate;

    private String setId;

    private String valueDate;

//    private String remarks;


//    private String createdBy;

//    private String createdDate;

    private String verifiedBy;

    private String verifiedDate;

    private String verifyStatus;

//    private String gbmTranId;

    private String channelType;

    private String cbsTranId;

    private String cbsTranStatus;

    private String cbsTranDescription;

    private String isCbsTransaction;

    private String baseBranch;

    public String getTranRefId() {
        return tranRefId;
    }

    public void setTranRefId(String tranRefId) {
        this.tranRefId = tranRefId;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public Integer getPartTranId() {
        return partTranId;
    }

    public void setPartTranId(Integer partTranId) {
        this.partTranId = partTranId;
    }

    public String getFinacleTranId() {
        return finacleTranId;
    }

    public void setFinacleTranId(String finacleTranId) {
        this.finacleTranId = finacleTranId;
    }

    public String getFinaclePartTranSrlNum() {
        return finaclePartTranSrlNum;
    }

    public void setFinaclePartTranSrlNum(String finaclePartTranSrlNum) {
        this.finaclePartTranSrlNum = finaclePartTranSrlNum;
    }

    public String getSetNum() {
        return setNum;
    }

    public void setSetNum(String setNum) {
        this.setNum = setNum;
    }

    public Date getTranPostedDate() {
        return tranPostedDate;
    }

    public void setTranPostedDate(Date tranPostedDate) {
        this.tranPostedDate = tranPostedDate;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }


    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public Character getTranLeg() {
        return tranLeg;
    }

    public void setTranLeg(Character tranLeg) {
        this.tranLeg = tranLeg;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Character getNavigationFlag() {
        return navigationFlag;
    }

    public void setNavigationFlag(Character navigationFlag) {
        this.navigationFlag = navigationFlag;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Character getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(Character verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public Character getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Character isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Character getChallanStatus() {
        return challanStatus;
    }

    public void setChallanStatus(Character challanStatus) {
        this.challanStatus = challanStatus;
    }

    public String getInterSolId() {
        return interSolId;
    }

    public void setInterSolId(String interSolId) {
        this.interSolId = interSolId;
    }

    public String getBankLevelCode() {
        return bankLevelCode;
    }

    public void setBankLevelCode(String bankLevelCode) {
        this.bankLevelCode = bankLevelCode;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(Double serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getZoneDate() {
        return zoneDate;
    }

    public void setZoneDate(Date zoneDate) {
        this.zoneDate = zoneDate;
    }

    public String getInstNumber() {
        return instNumber;
    }

    public void setInstNumber(String instNumber) {
        this.instNumber = instNumber;
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

    public String getPpoNumber() {
        return ppoNumber;
    }

    public void setPpoNumber(String ppoNumber) {
        this.ppoNumber = ppoNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public Boolean getAPI() {
        return isAPI;
    }

    public void setAPI(Boolean API) {
        isAPI = API;
    }

    public String getTranDateString() {
        return tranDateString;
    }

    public void setTranDateString(String tranDateString) {
        this.tranDateString = tranDateString;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    //    private String solId;
//    private String gbmTranId;
    private String crn;
//    private Date valueDate;
    private Boolean isAPI;

    private String tranDateString;

    private String narration;

    private String monthYear;

    public String getBaseBranch() {
        return baseBranch;
    }

    public String getIsInterSolTorch() {
        return isInterSolTorch;
    }

    public void setIsInterSolTorch(String isInterSolTorch) {
        this.isInterSolTorch = isInterSolTorch;
    }

    public String getIsInterSol() {
        return isInterSol;
    }

    public void setIsInterSol(String isInterSol) {
        this.isInterSol = isInterSol;
    }

    public void setBaseBranch(String baseBranch) {
        this.baseBranch = baseBranch;
    }

    public String getIsCbsTransaction() {
        return isCbsTransaction;
    }

    public void setIsCbsTransaction(String isCbsTransaction) {
        this.isCbsTransaction = isCbsTransaction;
    }

    public String getCbsTranId() {
        return cbsTranId;
    }

    public void setCbsTranId(String cbsTranId) {
        this.cbsTranId = cbsTranId;
    }

    public String getCbsTranStatus() {
        return cbsTranStatus;
    }

    public void setCbsTranStatus(String cbsTranStatus) {
        this.cbsTranStatus = cbsTranStatus;
    }

    public String getCbsTranDescription() {
        return cbsTranDescription;
    }

    public void setCbsTranDescription(String cbsTranDescription) {
        this.cbsTranDescription = cbsTranDescription;
    }

//    public String getGbmTranId() {
//        return gbmTranId;
//    }
//
//    public void setGbmTranId(String gbmTranId) {
//        this.gbmTranId = gbmTranId;
//    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    private Map<String, Object> acctOldNewMap;

    private Map<String, Object> tranOldNewMap;

    private Map<String, Object> nomOldNewMap;

    private Map<String, Object> depOldNewMap;

    private List<Map<String, Object>> nomineeOldNewDetails;

    private List<Map<String, Object>> instOldNewDetails;

    private List<GbmTransactionMasterDto> tranList;
    private List<DdTransactionApiDto> scssGttAccountOpeningDtoList;

    public void setScssGttAccountOpeningDtoList(List<DdTransactionApiDto> scssGttAccountOpeningDtoList) {
        this.scssGttAccountOpeningDtoList = scssGttAccountOpeningDtoList;
    }

    public List<GbmTransactionMasterDto> getTranList() {
        return tranList;
    }

    public void setTranList(List<GbmTransactionMasterDto> tranList) {
        this.tranList = tranList;
    }

    public List<Map<String, Object>> getInstOldNewDetails() {
        return instOldNewDetails;
    }

    public void setInstOldNewDetails(List<Map<String, Object>> instOldNewDetails) {
        this.instOldNewDetails = instOldNewDetails;
    }

    public List<Map<String, Object>> getNomineeOldNewDetails() {
        return nomineeOldNewDetails;
    }

    public void setNomineeOldNewDetails(List<Map<String, Object>> nomineeOldNewDetails) {
        this.nomineeOldNewDetails = nomineeOldNewDetails;
    }

    public Map<String, Object> getAcctOldNewMap() {
        return acctOldNewMap;
    }

    public void setAcctOldNewMap(Map<String, Object> acctOldNewMap) {
        this.acctOldNewMap = acctOldNewMap;
    }

    public Map<String, Object> getTranOldNewMap() {
        return tranOldNewMap;
    }

    public void setTranOldNewMap(Map<String, Object> tranOldNewMap) {
        this.tranOldNewMap = tranOldNewMap;
    }

    public Map<String, Object> getNomOldNewMap() {
        return nomOldNewMap;
    }

    public void setNomOldNewMap(Map<String, Object> nomOldNewMap) {
        this.nomOldNewMap = nomOldNewMap;
    }

    public Map<String, Object> getDepOldNewMap() {
        return depOldNewMap;
    }

    public void setDepOldNewMap(Map<String, Object> depOldNewMap) {
        this.depOldNewMap = depOldNewMap;
    }




    private List<DepositDetailsDTO> depositList;

    public List<DepositDetailsDTO> getDepositList() {
        return depositList;
    }

    public void setDepositList(List<DepositDetailsDTO> depositList) {
        this.depositList = depositList;
    }


    public String getSsaAcctId() {
        return ssaAcctId;
    }

    public void setSsaAcctId(String ssaAcctId) {
        this.ssaAcctId = ssaAcctId;
    }

    public String getDepositAmt() {
        return depositAmt;
    }

    public void setDepositAmt(String depositAmt) {
        this.depositAmt = depositAmt;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }



    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getInstNum() {
        return instNum;
    }

    public void setInstNum(String instNum) {
        this.instNum = instNum;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getInstDate() {
        return instDate;
    }

    public void setInstDate(String instDate) {
        this.instDate = instDate;
    }

//    public String getZoneDate() {
//        return zoneDate;
//    }
//
//    public void setZoneDate(String zoneDate) {
//        this.zoneDate = zoneDate;
//    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }



    public String getDepAmount() {
        return depAmount;
    }

    public String getFinYearAmt() {
        return finYearAmt;
    }

    public void setFinYearAmt(String finYearAmt) {
        this.finYearAmt = finYearAmt;
    }

    public void setDepAmount(String depAmount) {
        this.depAmount = depAmount;
    }

    public List<Map<String, Object>> getTranDetails() {
        return tranDetails;
    }

    public void setTranDetails(List<Map<String, Object>> tranDetails) {
        this.tranDetails = tranDetails;
    }

    public List<Map<String, Object>> getNomineeDetails() {
        return nomineeDetails;
    }

    public void setNomineeDetails(List<Map<String, Object>> nomineeDetails) {
        this.nomineeDetails = nomineeDetails;
    }

    public List<Map<String, Object>> getInstDetails() {
        return instDetails;
    }

    public void setInstDetails(List<Map<String, Object>> instDetails) {
        this.instDetails = instDetails;
    }

    public List<Map<String, Object>> getDepositDetails() {
        return depositDetails;
    }

    public void setDepositDetails(List<Map<String, Object>> depositDetails) {
        this.depositDetails = depositDetails;
    }

    public List<Map<String, Object>> getAcctDetails() {
        return acctDetails;
    }

    public void setAcctDetails(List<Map<String, Object>> acctDetails) {
        this.acctDetails = acctDetails;
    }




    private String scssCustId;

    private String scssCustomerName;

    private String scssAcctNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String accountType;

    private String creditAcctNum;

    private String debitAcctNum;



    private String transferDebitFlag;

    private String transferCreditFlag;

    private String transferDebitAcctNum;

    private String transfercreditAcctNum;

    private String branchOut;

    private String transferFeeAmt;

    private String tranOut;



    private String transferBankId;

    private String transferBranchId;

    private String transferThrough;

    private String proceedCreditAcctNum;

    private String transferWaiver;



    private String cbsTranStatusDescription;



    private String creditAmt;

    private String creditTranLeg;

    private String debitAmt;

    private String debitTranLeg;



    private String transferType;

    private List<List<ColumnValueDTO>> customerMasterDetailsDtoList;

    private List<Map<String, Object>> masterDetailsValueModel;

    private List<Map<String, Object>> nominationDetailsValueModel;

    public List<ScssTransferOutOtherBankDTO> scssTransferOutOtherBankDTOList;

    public List<DdTransactionApiDto> getScssGttAccountOpeningDtoList;

    public List<DdTransactionApiDto> getScssGttAccountOpeningDtoList() {
        return scssGttAccountOpeningDtoList;
    }

    public List<DdTransactionApiDto> getGetScssGttAccountOpeningDtoList() {
        return getScssGttAccountOpeningDtoList;
    }

    public void setGetScssGttAccountOpeningDtoList(List<DdTransactionApiDto> getScssGttAccountOpeningDtoList) {
        this.getScssGttAccountOpeningDtoList = getScssGttAccountOpeningDtoList;
    }

    public String getScssCustomerName() {
        return scssCustomerName;
    }

    public void setScssCustomerName(String scssCustomerName) {
        this.scssCustomerName = scssCustomerName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<ScssTransferOutOtherBankDTO> getScssTransferOutOtherBankDTOList() {
        return scssTransferOutOtherBankDTOList;
    }

    public void setScssTransferOutOtherBankDTOList(List<ScssTransferOutOtherBankDTO> scssTransferOutOtherBankDTOList) {
        this.scssTransferOutOtherBankDTOList = scssTransferOutOtherBankDTOList;
    }



    public String getScssCustId() {
        return scssCustId;
    }

    public void setScssCustId(String scssCustId) {
        this.scssCustId = scssCustId;
    }

    public String getScssAcctNum() {
        return scssAcctNum;
    }

    public void setScssAcctNum(String scssAcctNum) {
        this.scssAcctNum = scssAcctNum;
    }

    public List<Map<String, Object>> getMasterDetailsValueModel() {
        return masterDetailsValueModel;
    }

    public void setMasterDetailsValueModel(List<Map<String, Object>> masterDetailsValueModel) {
        this.masterDetailsValueModel = masterDetailsValueModel;
    }

    public List<Map<String, Object>> getNominationDetailsValueModel() {
        return nominationDetailsValueModel;
    }

    public void setNominationDetailsValueModel(List<Map<String, Object>> nominationDetailsValueModel) {
        this.nominationDetailsValueModel = nominationDetailsValueModel;
    }

    public String getCreditAcctNum() {
        return creditAcctNum;
    }

    public void setCreditAcctNum(String creditAcctNum) {
        this.creditAcctNum = creditAcctNum;
    }

    public String getDebitAcctNum() {
        return debitAcctNum;
    }

    public void setDebitAcctNum(String debitAcctNum) {
        this.debitAcctNum = debitAcctNum;
    }



    public String getTransferDebitFlag() {
        return transferDebitFlag;
    }

    public void setTransferDebitFlag(String transferDebitFlag) {
        this.transferDebitFlag = transferDebitFlag;
    }

    public String getTransferCreditFlag() {
        return transferCreditFlag;
    }

    public void setTransferCreditFlag(String transferCreditFlag) {
        this.transferCreditFlag = transferCreditFlag;
    }

    public String getTransferDebitAcctNum() {
        return transferDebitAcctNum;
    }

    public void setTransferDebitAcctNum(String transferDebitAcctNum) {
        this.transferDebitAcctNum = transferDebitAcctNum;
    }

    public String getTransfercreditAcctNum() {
        return transfercreditAcctNum;
    }

    public void setTransfercreditAcctNum(String transfercreditAcctNum) {
        this.transfercreditAcctNum = transfercreditAcctNum;
    }

    public String getBranchOut() {
        return branchOut;
    }

    public void setBranchOut(String branchOut) {
        this.branchOut = branchOut;
    }

    public String getTransferFeeAmt() {
        return transferFeeAmt;
    }

    public void setTransferFeeAmt(String transferFeeAmt) {
        this.transferFeeAmt = transferFeeAmt;
    }


    public String getTranOut() {
        return tranOut;
    }

    public void setTranOut(String tranOut) {
        this.tranOut = tranOut;
    }



    public String getTransferBankId() {
        return transferBankId;
    }

    public void setTransferBankId(String transferBankId) {
        this.transferBankId = transferBankId;
    }

    public String getTransferThrough() {
        return transferThrough;
    }

    public void setTransferThrough(String transferThrough) {
        this.transferThrough = transferThrough;
    }

    public String getProceedCreditAcctNum() {
        return proceedCreditAcctNum;
    }

    public void setProceedCreditAcctNum(String proceedCreditAcctNum) {
        this.proceedCreditAcctNum = proceedCreditAcctNum;
    }

    public String getTransferWaiver() {
        return transferWaiver;
    }

    public void setTransferWaiver(String transferWaiver) {
        this.transferWaiver = transferWaiver;
    }



    public List<List<ColumnValueDTO>> getCustomerMasterDetailsDtoList() {
        return customerMasterDetailsDtoList;
    }

    public void setCustomerMasterDetailsDtoList(List<List<ColumnValueDTO>> customerMasterDetailsDtoList) {
        this.customerMasterDetailsDtoList = customerMasterDetailsDtoList;
    }



    public String getCbsTranStatusDescription() {
        return cbsTranStatusDescription;
    }

    public void setCbsTranStatusDescription(String cbsTranStatusDescription) {
        this.cbsTranStatusDescription = cbsTranStatusDescription;
    }


//    public List<ScssGttAccountOpeningDto> getScssGttAccountOpeningDtoList() {
//        return scssGttAccountOpeningDtoList;
//    }
//
//    public void setScssGttAccountOpeningDtoList(List<ScssGttAccountOpeningDto> scssGttAccountOpeningDtoList) {
//        this.scssGttAccountOpeningDtoList = scssGttAccountOpeningDtoList;
//    }


    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getCreditTranLeg() {
        return creditTranLeg;
    }

    public void setCreditTranLeg(String creditTranLeg) {
        this.creditTranLeg = creditTranLeg;
    }

    public String getDebitAmt() {
        return debitAmt;
    }

    public void setDebitAmt(String debitAmt) {
        this.debitAmt = debitAmt;
    }

    public String getDebitTranLeg() {
        return debitTranLeg;
    }

    public void setDebitTranLeg(String debitTranLeg) {
        this.debitTranLeg = debitTranLeg;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }




    private String ppfAcctId;

    private String isOtherBankTransfer;

    private String isWaived;

    private String waivedTranMode;

    private List<Map<String, Object>> nomineeOldNewMapDetails;

    private List<Map<String, Object>> acctOldNewMapDetails;



    private String feeCreditActNum;

    private String feeDebitActNum;

    private String feeCreditFlag;

    private String feeDebitFlag;

    private String totDepAmt;

    private String transferFee;

    private String gstAmt;

    private String transferBank;

    private String transferBranch;

    private String feeCreditActType;

    private String feeDebitActType;

    private String modifiedBy;

    private String modifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getFeeCreditActType() {
        return feeCreditActType;
    }

    public void setFeeCreditActType(String feeCreditActType) {
        this.feeCreditActType = feeCreditActType;
    }

    public String getFeeDebitActType() {
        return feeDebitActType;
    }

    public void setFeeDebitActType(String feeDebitActType) {
        this.feeDebitActType = feeDebitActType;
    }

    public String getCreditActType() {
        return creditActType;
    }

    public void setCreditActType(String creditActType) {
        this.creditActType = creditActType;
    }

    public String getDebitActType() {
        return debitActType;
    }

    public void setDebitActType(String debitActType) {
        this.debitActType = debitActType;
    }

    public String getTransferBank() {
        return transferBank;
    }

    public void setTransferBank(String transferBank) {
        this.transferBank = transferBank;
    }

    public String getTransferBranch() {
        return transferBranch;
    }

    public void setTransferBranch(String transferBranch) {
        this.transferBranch = transferBranch;
    }

    public String getTransferBranchId() {
        return transferBranchId;
    }

    public void setTransferBranchId(String transferBranchId) {
        this.transferBranchId = transferBranchId;
    }

    public String getTranMode() {
        return tranMode;
    }

    public void setTranMode(String tranMode) {
        this.tranMode = tranMode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreditActNum() {
        return creditActNum;
    }

    public void setCreditActNum(String creditActNum) {
        this.creditActNum = creditActNum;
    }

    public String getDebitActNum() {
        return debitActNum;
    }

    public void setDebitActNum(String debitActNum) {
        this.debitActNum = debitActNum;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getDebitFlag() {
        return debitFlag;
    }

    public void setDebitFlag(String debitFlag) {
        this.debitFlag = debitFlag;
    }

    public String getFeeCreditActNum() {
        return feeCreditActNum;
    }

    public void setFeeCreditActNum(String feeCreditActNum) {
        this.feeCreditActNum = feeCreditActNum;
    }

    public String getFeeDebitActNum() {
        return feeDebitActNum;
    }

    public void setFeeDebitActNum(String feeDebitActNum) {
        this.feeDebitActNum = feeDebitActNum;
    }

    public String getFeeCreditFlag() {
        return feeCreditFlag;
    }

    public void setFeeCreditFlag(String feeCreditFlag) {
        this.feeCreditFlag = feeCreditFlag;
    }

    public String getFeeDebitFlag() {
        return feeDebitFlag;
    }

    public void setFeeDebitFlag(String feeDebitFlag) {
        this.feeDebitFlag = feeDebitFlag;
    }

    public String getTotDepAmt() {
        return totDepAmt;
    }

    public void setTotDepAmt(String totDepAmt) {
        this.totDepAmt = totDepAmt;
    }

    public String getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(String transferFee) {
        this.transferFee = transferFee;
    }

    public String getGstAmt() {
        return gstAmt;
    }

    public void setGstAmt(String gstAmt) {
        this.gstAmt = gstAmt;
    }

    public List<Map<String, Object>> getNomineeOldNewMapDetails() {
        return nomineeOldNewMapDetails;
    }

    public void setNomineeOldNewMapDetails(List<Map<String, Object>> nomineeOldNewMapDetails) {
        this.nomineeOldNewMapDetails = nomineeOldNewMapDetails;
    }

    public List<Map<String, Object>> getAcctOldNewMapDetails() {
        return acctOldNewMapDetails;
    }

    public void setAcctOldNewMapDetails(List<Map<String, Object>> acctOldNewMapDetails) {
        this.acctOldNewMapDetails = acctOldNewMapDetails;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPpfAcctId() {
        return ppfAcctId;
    }

    public void setPpfAcctId(String ppfAcctId) {
        this.ppfAcctId = ppfAcctId;
    }

    public String getIsOtherBankTransfer() {
        return isOtherBankTransfer;
    }

    public void setIsOtherBankTransfer(String isOtherBankTransfer) {
        this.isOtherBankTransfer = isOtherBankTransfer;
    }

    public String getIsWaived() {
        return isWaived;
    }

    public void setIsWaived(String isWaived) {
        this.isWaived = isWaived;
    }

    public String getWaivedTranMode() {
        return waivedTranMode;
    }

    public void setWaivedTranMode(String waivedTranMode) {
        this.waivedTranMode = waivedTranMode;
    }

}
