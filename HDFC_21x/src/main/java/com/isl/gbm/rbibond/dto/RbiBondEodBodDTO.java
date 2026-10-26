package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondEodBodDTO {
    private String levelName;
    private String moduleName;
    private String bodDate;
    private String eodDate;
    private String branchId;
    private String reqType;
    private String finEntryFlgOut;
    private List<RbiBondEodBodDTO> centalizedEodBodDTOList;
    List<HashMap<String, String>> branchCode;
    List<HashMap<String, String>> levelCodeList;


    private String procTypeIn;

    private String recPayType;
    private String cashDepCheckFlg;

    private String cbsEntryFlag;

    private String eodStatusDesc;

    private String channelType;
    private String tranDate;
    private String schemeCode;
    private String screenTranCode;
    private String finEntryFlg;
    private String eodStatus;
    private String eodFailedReason;
    private String eodDesc;
    private String accNum;
    private String accType;
    private String gbmTranId;
    private String cbsTranId;
    private String cbsTranStatus;
    private String cbsTranStatusDesc;

    private String typeFlag;
    private String amount;

    private String bondColAccCenFlag;

    private String bondConAccCenFlag;

    private String bondRecAccCenFlag;

    private String bondPayAccCenFlag;

    private String bondCashCenFlag;

    private String bondCashAcc;

    private String bondColAcc;

    private String bondConAcc;

    private String bondRecAcc;

    private String bondPayAcc;

    private List<String> gbmTranIdList;


    private List<AccountValidationDto> cashAccountDet;

    private AccountValidationDto depositAccountDet;

    private List<AccountValidationDto> collectionAccountDet;

    private List<AccountValidationDto> consolidatedAccountDet;

    private List<AccountValidationDto> payableAccountDet;

    private List<AccountValidationDto> receivableAccountDet;

    private List<GbmTransactionMasterDto> tranList;

    private List<RbiBondEodBodDTO> accValidationList;

    private List<TransactionReponseDto> transactionReponseDtos;

    private TransactionReponseDto transactionReponseDto;

    private List<RbiBondEodBodDTO.ErrorDescDto> nonFinancialUnverifiedTranList;

    private List<RbiBondEodBodDTO.ErrorDescDto> financialUnverifiedTranList;

    private List<RbiBondEodBodDTO> amtValidationList;


    public static class ErrorDescDto {
        private String moduleName;

        private String linkName;

        private String desc;

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getFinEntryFlgOut() {
        return finEntryFlgOut;
    }

    public void setFinEntryFlgOut(String finEntryFlgOut) {
        this.finEntryFlgOut = finEntryFlgOut;
    }

    public TransactionReponseDto getTransactionReponseDto() {
        return transactionReponseDto;
    }

    public void setTransactionReponseDto(TransactionReponseDto transactionReponseDto) {
        this.transactionReponseDto = transactionReponseDto;
    }

    public List<String> getGbmTranIdList() {
        return gbmTranIdList;
    }

    public void setGbmTranIdList(List<String> gbmTranIdList) {
        this.gbmTranIdList = gbmTranIdList;
    }

    public List<RbiBondEodBodDTO> getAccValidationList() {
        return accValidationList;
    }

    public void setAccValidationList(List<RbiBondEodBodDTO> accValidationList) {
        this.accValidationList = accValidationList;
    }


    public List<RbiBondEodBodDTO> getAmtValidationList() {
        return amtValidationList;
    }

    public void setAmtValidationList(List<RbiBondEodBodDTO> amtValidationList) {
        this.amtValidationList = amtValidationList;
    }

    public List<AccountValidationDto> getCashAccountDet() {
        return cashAccountDet;
    }

    public void setCashAccountDet(List<AccountValidationDto> cashAccountDet) {
        this.cashAccountDet = cashAccountDet;
    }

    public AccountValidationDto getDepositAccountDet() {
        return depositAccountDet;
    }

    public void setDepositAccountDet(AccountValidationDto depositAccountDet) {
        this.depositAccountDet = depositAccountDet;
    }

    public List<AccountValidationDto> getCollectionAccountDet() {
        return collectionAccountDet;
    }

    public void setCollectionAccountDet(List<AccountValidationDto> collectionAccountDet) {
        this.collectionAccountDet = collectionAccountDet;
    }

    public List<AccountValidationDto> getConsolidatedAccountDet() {
        return consolidatedAccountDet;
    }

    public void setConsolidatedAccountDet(List<AccountValidationDto> consolidatedAccountDet) {
        this.consolidatedAccountDet = consolidatedAccountDet;
    }

    public List<AccountValidationDto> getPayableAccountDet() {
        return payableAccountDet;
    }

    public void setPayableAccountDet(List<AccountValidationDto> payableAccountDet) {
        this.payableAccountDet = payableAccountDet;
    }

    public List<AccountValidationDto> getReceivableAccountDet() {
        return receivableAccountDet;
    }

    public void setReceivableAccountDet(List<AccountValidationDto> receivableAccountDet) {
        this.receivableAccountDet = receivableAccountDet;
    }

    public List<GbmTransactionMasterDto> getTranList() {
        return tranList;
    }

    public void setTranList(List<GbmTransactionMasterDto> tranList) {
        this.tranList = tranList;
    }

    public List<TransactionReponseDto> getTransactionReponseDtos() {
        return transactionReponseDtos;
    }

    public void setTransactionReponseDtos(List<TransactionReponseDto> transactionReponseDtos) {
        this.transactionReponseDtos = transactionReponseDtos;
    }

    public List<ErrorDescDto> getNonFinancialUnverifiedTranList() {
        return nonFinancialUnverifiedTranList;
    }

    public void setNonFinancialUnverifiedTranList(List<ErrorDescDto> nonFinancialUnverifiedTranList) {
        this.nonFinancialUnverifiedTranList = nonFinancialUnverifiedTranList;
    }

    public List<ErrorDescDto> getFinancialUnverifiedTranList() {
        return financialUnverifiedTranList;
    }

    public void setFinancialUnverifiedTranList(List<ErrorDescDto> financialUnverifiedTranList) {
        this.financialUnverifiedTranList = financialUnverifiedTranList;
    }

    public String getEodStatusDesc() {
        return eodStatusDesc;
    }

    public void setEodStatusDesc(String eodStatusDesc) {
        this.eodStatusDesc = eodStatusDesc;
    }

    public String getBondCashCenFlag() {
        return bondCashCenFlag;
    }

    public void setBondCashCenFlag(String bondCashCenFlag) {
        this.bondCashCenFlag = bondCashCenFlag;
    }

    public String getBondCashAcc() {
        return bondCashAcc;
    }

    public void setBondCashAcc(String bondCashAcc) {
        this.bondCashAcc = bondCashAcc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBondColAccCenFlag() {
        return bondColAccCenFlag;
    }

    public void setBondColAccCenFlag(String bondColAccCenFlag) {
        this.bondColAccCenFlag = bondColAccCenFlag;
    }

    public String getBondConAccCenFlag() {
        return bondConAccCenFlag;
    }

    public void setBondConAccCenFlag(String bondConAccCenFlag) {
        this.bondConAccCenFlag = bondConAccCenFlag;
    }

    public String getBondRecAccCenFlag() {
        return bondRecAccCenFlag;
    }

    public void setBondRecAccCenFlag(String bondRecAccCenFlag) {
        this.bondRecAccCenFlag = bondRecAccCenFlag;
    }

    public String getBondPayAccCenFlag() {
        return bondPayAccCenFlag;
    }

    public void setBondPayAccCenFlag(String bondPayAccCenFlag) {
        this.bondPayAccCenFlag = bondPayAccCenFlag;
    }

    public String getBondColAcc() {
        return bondColAcc;
    }

    public void setBondColAcc(String bondColAcc) {
        this.bondColAcc = bondColAcc;
    }

    public String getBondConAcc() {
        return bondConAcc;
    }

    public void setBondConAcc(String bondConAcc) {
        this.bondConAcc = bondConAcc;
    }

    public String getBondRecAcc() {
        return bondRecAcc;
    }

    public void setBondRecAcc(String bondRecAcc) {
        this.bondRecAcc = bondRecAcc;
    }

    public String getBondPayAcc() {
        return bondPayAcc;
    }

    public void setBondPayAcc(String bondPayAcc) {
        this.bondPayAcc = bondPayAcc;
    }

    public String getCbsEntryFlag() {
        return cbsEntryFlag;
    }

    public void setCbsEntryFlag(String cbsEntryFlag) {
        this.cbsEntryFlag = cbsEntryFlag;
    }

    public String getProcTypeIn() {
        return procTypeIn;
    }

    public void setProcTypeIn(String procTypeIn) {
        this.procTypeIn = procTypeIn;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getGbmTranId() {
        return gbmTranId;
    }

    public void setGbmTranId(String gbmTranId) {
        this.gbmTranId = gbmTranId;
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

    public String getCbsTranStatusDesc() {
        return cbsTranStatusDesc;
    }

    public void setCbsTranStatusDesc(String cbsTranStatusDesc) {
        this.cbsTranStatusDesc = cbsTranStatusDesc;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getRecPayType() {
        return recPayType;
    }

    public void setRecPayType(String recPayType) {
        this.recPayType = recPayType;
    }

    public String getCashDepCheckFlg() {
        return cashDepCheckFlg;
    }

    public void setCashDepCheckFlg(String cashDepCheckFlg) {
        this.cashDepCheckFlg = cashDepCheckFlg;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getScreenTranCode() {
        return screenTranCode;
    }

    public void setScreenTranCode(String screenTranCode) {
        this.screenTranCode = screenTranCode;
    }

    public String getFinEntryFlg() {
        return finEntryFlg;
    }

    public void setFinEntryFlg(String finEntryFlg) {
        this.finEntryFlg = finEntryFlg;
    }

    public String getEodStatus() {
        return eodStatus;
    }

    public void setEodStatus(String eodStatus) {
        this.eodStatus = eodStatus;
    }

    public String getEodFailedReason() {
        return eodFailedReason;
    }

    public void setEodFailedReason(String eodFailedReason) {
        this.eodFailedReason = eodFailedReason;
    }

    public String getEodDesc() {
        return eodDesc;
    }

    public void setEodDesc(String eodDesc) {
        this.eodDesc = eodDesc;
    }

    public String getEodDate() {
        return eodDate;
    }

    public void setEodDate(String eodDate) {
        this.eodDate = eodDate;
    }

    public List<HashMap<String, String>> getLevelCodeList() {
        return levelCodeList;
    }

    public void setLevelCodeList(List<HashMap<String, String>> levelCodeList) {
        this.levelCodeList = levelCodeList;
    }

    public List<HashMap<String, String>> getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(List<HashMap<String, String>> branchCode) {
        this.branchCode = branchCode;
    }

    public List<RbiBondEodBodDTO> getCentalizedEodBodDTOList() {
        return centalizedEodBodDTOList;
    }

    public void setCentalizedEodBodDTOList(List<RbiBondEodBodDTO> centalizedEodBodDTOList) {
        this.centalizedEodBodDTOList = centalizedEodBodDTOList;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBodDate() {
        return bodDate;
    }

    public void setBodDate(String bodDate) {
        this.bodDate = bodDate;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "RbiBondEodBodDTO{" +
                "levelName='" + levelName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", bodDate='" + bodDate + '\'' +
                ", eodDate='" + eodDate + '\'' +
                ", branchId='" + branchId + '\'' +
                ", centalizedEodBodDTOList=" + centalizedEodBodDTOList +
                ", branchCode=" + branchCode +
                ", levelCodeList=" + levelCodeList +
                ", procTypeIn='" + procTypeIn + '\'' +
                ", recPayType='" + recPayType + '\'' +
                ", cashDepCheckFlg='" + cashDepCheckFlg + '\'' +
                ", cbsEntryFlag='" + cbsEntryFlag + '\'' +
                ", eodStatusDesc='" + eodStatusDesc + '\'' +
                ", channelType='" + channelType + '\'' +
                ", tranDate='" + tranDate + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", screenTranCode='" + screenTranCode + '\'' +
                ", finEntryFlg='" + finEntryFlg + '\'' +
                ", eodStatus='" + eodStatus + '\'' +
                ", eodFailedReason='" + eodFailedReason + '\'' +
                ", eodDesc='" + eodDesc + '\'' +
                ", accNum='" + accNum + '\'' +
                ", accType='" + accType + '\'' +
                ", gbmTranId='" + gbmTranId + '\'' +
                ", cbsTranId='" + cbsTranId + '\'' +
                ", cbsTranStatus='" + cbsTranStatus + '\'' +
                ", cbsTranStatusDesc='" + cbsTranStatusDesc + '\'' +
                ", typeFlag='" + typeFlag + '\'' +
                ", amount='" + amount + '\'' +
                ", bondColAccCenFlag='" + bondColAccCenFlag + '\'' +
                ", bondConAccCenFlag='" + bondConAccCenFlag + '\'' +
                ", bondRecAccCenFlag='" + bondRecAccCenFlag + '\'' +
                ", bondPayAccCenFlag='" + bondPayAccCenFlag + '\'' +
                ", bondCashCenFlag='" + bondCashCenFlag + '\'' +
                ", bondCashAcc='" + bondCashAcc + '\'' +
                ", bondColAcc='" + bondColAcc + '\'' +
                ", bondConAcc='" + bondConAcc + '\'' +
                ", bondRecAcc='" + bondRecAcc + '\'' +
                ", bondPayAcc='" + bondPayAcc + '\'' +
                ", gbmTranIdList=" + gbmTranIdList +
                ", cashAccountDet=" + cashAccountDet +
                ", depositAccountDet=" + depositAccountDet +
                ", collectionAccountDet=" + collectionAccountDet +
                ", consolidatedAccountDet=" + consolidatedAccountDet +
                ", payableAccountDet=" + payableAccountDet +
                ", receivableAccountDet=" + receivableAccountDet +
                ", tranList=" + tranList +
                ", accValidationList=" + accValidationList +
                ", transactionReponseDtos=" + transactionReponseDtos +
                ", transactionReponseDto=" + transactionReponseDto +
                ", nonFinancialUnverifiedTranList=" + nonFinancialUnverifiedTranList +
                ", financialUnverifiedTranList=" + financialUnverifiedTranList +
                '}';
    }
}
