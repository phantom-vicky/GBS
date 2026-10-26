package com.isl.gbm.rbibond.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.common.dto.ColumnValueDTO;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RbiBondAccountingEntriesDTO {
    private String moduleId;
    private String schemeName;
    private String schemeCode;
    private String schemeStatus;
    private String versionNo;
    private String effectiveFromDate;
    private String parameterEffectiveFromDate;
    private String parameterEffectiveToDate;
    private String bondSchemeEffectiveFromDate;
    private String bondSchemeEffectiveToDate;
    private String suspenseAcctCmsDebit;
    private String suspenseAcctInetDebit;
    private String rrbPayCredit;
    private String rrbPayDebit;
    private String rbiClgNostroWithRbi;
    private String siaRrbRbiNagpurBrokDebit91;
    private String siaRemitSuspAccCredit1;
    private String siaRrbRbiNagpurBrokDebit92;
    private String siaRemitSuspAccCredit2;
    private String siaRrbRbiNagpurBrokDebit93;
    private String siaRemitSuspAccCredit3;
    private String biaRemitSuspAccDebit1;
    private String biaBrkgEarnedRbiBondsCredit1;
    private String biaRemitSuspAccDebit2;
    private String biaBrkgEarnedRbiBondsCredit2;
    private String gstRemitSuspAccDebit1;
    private String gstRespGstAccCredit1;
    private String gstRrbRbiNagpurBrokAccDebit1;
    private String gstRemitSuspAccCredit1;
    private String hciRemitSuspAccDebit;
    private String hciCommEarnedCredit;
    private String gstRemitSuspAccDebit2;
    private String gstRespGstAccCredit2;
    private String gstRrbRbiNagpurBrokAccDebit2;
    private String gstRemitSuspAccCredit2;
    private String rvbRbiClgDebit;
    private String rvbRrbRbiNagpurBrokAccCredit;
    private String glRbiClgDebit;
    private String glRrbRbiNagpurBrokAccCredit;
    private String hyglIntRecevAccDebit;
    private String hyglMinorDiffRoundingOff;
    private String hyglTdsInterestSecurCredit;
    private String hyglRbiBondTransitAccCredit1;
    private String hyglEcsSettleAccCredit;
    private String hyglRbiBondTransitAccCredit2;
    private String hyglCmsDisbCtrlCliFundingCredit;
    private String hypmRbiBondTransitAccDebit1;
    private String hypmEcsSettleAccDebit;
    private String hypmRbiBondTransitAccDebit2;
    private String hypmCmsDisbCtrlCliFundingDebit;
    private String hypmCustAccCredit;
    private String uponRecRbiClgDebit;
    private String uponRecInterestRecevableAccCredit;
    private String redempPrpReceivAccDebit;
    private String redemIntReceivAccDebit;
    private String redemCmsDisbCtrlCliFundingCredit;
    private String redemRbiBondTransitAccCredit;
    private String redemTdsSecur193Credit;
    private String redemCustomCasaAccCredit;
    private String redemRbiBondTransitAccDebit;
    private String redemCmsDisbCtrlCliFundingDebit;
    private String redemCustomAccCredit;
    private String prfRbiClgDebit;
    private String prfPrpReceivAccCredit;
    private String prfInterestReceivableAccCredit;
    private String sctcBrokExpRbiBondRrbDebit;
    private String sctcBrokExpRbiBondRrbRcmCredit;
    private String sctcVendorAndBrokersAccCredit;

    @Valid
    private List<ColumnValueDTO> columnValues;
    private String pageType;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String verifiedDate;
    private String previousOperationType;
    private String verifiedStatus;
    private String deleteRemarks;
    private String pkColumnValues;
    private String rejectionRemarks;

    private Map<String, String> accountingEntriesListModel;
    private Map<String, Object> accountingEntriesOldNewValueModel;

    public Map<String, Object> getAccountingEntriesOldNewValueModel() {
        return accountingEntriesOldNewValueModel;
    }


    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public void setAccountingEntriesOldNewValueModel(Map<String, Object> accountingEntriesOldNewValueModel) {
        this.accountingEntriesOldNewValueModel = accountingEntriesOldNewValueModel;
    }

    public String getPkColumnValues() {
        return pkColumnValues;
    }

    public void setPkColumnValues(String pkColumnValues) {
        this.pkColumnValues = pkColumnValues;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getDeleteRemarks() {
        return deleteRemarks;
    }

    public void setDeleteRemarks(String deleteRemarks) {
        this.deleteRemarks = deleteRemarks;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getParameterEffectiveFromDate() {
        return parameterEffectiveFromDate;
    }

    public void setParameterEffectiveFromDate(String parameterEffectiveFromDate) {
        this.parameterEffectiveFromDate = parameterEffectiveFromDate;
    }

    public String getParameterEffectiveToDate() {
        return parameterEffectiveToDate;
    }

    public void setParameterEffectiveToDate(String parameterEffectiveToDate) {
        this.parameterEffectiveToDate = parameterEffectiveToDate;
    }

    public String getBondSchemeEffectiveFromDate() {
        return bondSchemeEffectiveFromDate;
    }

    public void setBondSchemeEffectiveFromDate(String bondSchemeEffectiveFromDate) {
        this.bondSchemeEffectiveFromDate = bondSchemeEffectiveFromDate;
    }

    public String getBondSchemeEffectiveToDate() {
        return bondSchemeEffectiveToDate;
    }

    public void setBondSchemeEffectiveToDate(String bondSchemeEffectiveToDate) {
        this.bondSchemeEffectiveToDate = bondSchemeEffectiveToDate;
    }

    public RbiBondAccountingEntriesDTO() {
    }

    public RbiBondAccountingEntriesDTO(Map<String, String> accountingEntriesListModel) {
        this.pageType= pageType;
        this.accountingEntriesListModel = accountingEntriesListModel;
    }

    public String getSuspenseAcctCmsDebit() {
        return suspenseAcctCmsDebit;
    }

    public void setSuspenseAcctCmsDebit(String suspenseAcctCmsDebit) {
        this.suspenseAcctCmsDebit = suspenseAcctCmsDebit;
    }

    public String getSuspenseAcctInetDebit() {
        return suspenseAcctInetDebit;
    }

    public void setSuspenseAcctInetDebit(String suspenseAcctInetDebit) {
        this.suspenseAcctInetDebit = suspenseAcctInetDebit;
    }

    public String getRrbPayCredit() {
        return rrbPayCredit;
    }

    public void setRrbPayCredit(String rrbPayCredit) {
        this.rrbPayCredit = rrbPayCredit;
    }

    public String getRrbPayDebit() {
        return rrbPayDebit;
    }

    public void setRrbPayDebit(String rrbPayDebit) {
        this.rrbPayDebit = rrbPayDebit;
    }

    public String getRbiClgNostroWithRbi() {
        return rbiClgNostroWithRbi;
    }

    public void setRbiClgNostroWithRbi(String rbiClgNostroWithRbi) {
        this.rbiClgNostroWithRbi = rbiClgNostroWithRbi;
    }

    public String getSiaRrbRbiNagpurBrokDebit91() {
        return siaRrbRbiNagpurBrokDebit91;
    }

    public void setSiaRrbRbiNagpurBrokDebit91(String siaRrbRbiNagpurBrokDebit91) {
        this.siaRrbRbiNagpurBrokDebit91 = siaRrbRbiNagpurBrokDebit91;
    }

    public String getSiaRemitSuspAccCredit1() {
        return siaRemitSuspAccCredit1;
    }

    public void setSiaRemitSuspAccCredit1(String siaRemitSuspAccCredit1) {
        this.siaRemitSuspAccCredit1 = siaRemitSuspAccCredit1;
    }

    public String getSiaRrbRbiNagpurBrokDebit92() {
        return siaRrbRbiNagpurBrokDebit92;
    }

    public void setSiaRrbRbiNagpurBrokDebit92(String siaRrbRbiNagpurBrokDebit92) {
        this.siaRrbRbiNagpurBrokDebit92 = siaRrbRbiNagpurBrokDebit92;
    }

    public String getSiaRemitSuspAccCredit2() {
        return siaRemitSuspAccCredit2;
    }

    public void setSiaRemitSuspAccCredit2(String siaRemitSuspAccCredit2) {
        this.siaRemitSuspAccCredit2 = siaRemitSuspAccCredit2;
    }

    public String getSiaRrbRbiNagpurBrokDebit93() {
        return siaRrbRbiNagpurBrokDebit93;
    }

    public void setSiaRrbRbiNagpurBrokDebit93(String siaRrbRbiNagpurBrokDebit93) {
        this.siaRrbRbiNagpurBrokDebit93 = siaRrbRbiNagpurBrokDebit93;
    }

    public String getSiaRemitSuspAccCredit3() {
        return siaRemitSuspAccCredit3;
    }

    public void setSiaRemitSuspAccCredit3(String siaRemitSuspAccCredit3) {
        this.siaRemitSuspAccCredit3 = siaRemitSuspAccCredit3;
    }

    public String getBiaRemitSuspAccDebit1() {
        return biaRemitSuspAccDebit1;
    }

    public void setBiaRemitSuspAccDebit1(String biaRemitSuspAccDebit1) {
        this.biaRemitSuspAccDebit1 = biaRemitSuspAccDebit1;
    }

    public String getBiaBrkgEarnedRbiBondsCredit1() {
        return biaBrkgEarnedRbiBondsCredit1;
    }

    public void setBiaBrkgEarnedRbiBondsCredit1(String biaBrkgEarnedRbiBondsCredit1) {
        this.biaBrkgEarnedRbiBondsCredit1 = biaBrkgEarnedRbiBondsCredit1;
    }

    public String getBiaRemitSuspAccDebit2() {
        return biaRemitSuspAccDebit2;
    }

    public void setBiaRemitSuspAccDebit2(String biaRemitSuspAccDebit2) {
        this.biaRemitSuspAccDebit2 = biaRemitSuspAccDebit2;
    }

    public String getBiaBrkgEarnedRbiBondsCredit2() {
        return biaBrkgEarnedRbiBondsCredit2;
    }

    public void setBiaBrkgEarnedRbiBondsCredit2(String biaBrkgEarnedRbiBondsCredit2) {
        this.biaBrkgEarnedRbiBondsCredit2 = biaBrkgEarnedRbiBondsCredit2;
    }

    public String getGstRemitSuspAccDebit1() {
        return gstRemitSuspAccDebit1;
    }

    public void setGstRemitSuspAccDebit1(String gstRemitSuspAccDebit1) {
        this.gstRemitSuspAccDebit1 = gstRemitSuspAccDebit1;
    }

    public String getGstRespGstAccCredit1() {
        return gstRespGstAccCredit1;
    }

    public void setGstRespGstAccCredit1(String gstRespGstAccCredit1) {
        this.gstRespGstAccCredit1 = gstRespGstAccCredit1;
    }

    public String getGstRrbRbiNagpurBrokAccDebit1() {
        return gstRrbRbiNagpurBrokAccDebit1;
    }

    public void setGstRrbRbiNagpurBrokAccDebit1(String gstRrbRbiNagpurBrokAccDebit1) {
        this.gstRrbRbiNagpurBrokAccDebit1 = gstRrbRbiNagpurBrokAccDebit1;
    }

    public String getGstRemitSuspAccCredit1() {
        return gstRemitSuspAccCredit1;
    }

    public void setGstRemitSuspAccCredit1(String gstRemitSuspAccCredit1) {
        this.gstRemitSuspAccCredit1 = gstRemitSuspAccCredit1;
    }

    public String getHciRemitSuspAccDebit() {
        return hciRemitSuspAccDebit;
    }

    public void setHciRemitSuspAccDebit(String hciRemitSuspAccDebit) {
        this.hciRemitSuspAccDebit = hciRemitSuspAccDebit;
    }

    public String getHciCommEarnedCredit() {
        return hciCommEarnedCredit;
    }

    public void setHciCommEarnedCredit(String hciCommEarnedCredit) {
        this.hciCommEarnedCredit = hciCommEarnedCredit;
    }

    public String getGstRemitSuspAccDebit2() {
        return gstRemitSuspAccDebit2;
    }

    public void setGstRemitSuspAccDebit2(String gstRemitSuspAccDebit2) {
        this.gstRemitSuspAccDebit2 = gstRemitSuspAccDebit2;
    }

    public String getGstRespGstAccCredit2() {
        return gstRespGstAccCredit2;
    }

    public void setGstRespGstAccCredit2(String gstRespGstAccCredit2) {
        this.gstRespGstAccCredit2 = gstRespGstAccCredit2;
    }

    public String getGstRrbRbiNagpurBrokAccDebit2() {
        return gstRrbRbiNagpurBrokAccDebit2;
    }

    public void setGstRrbRbiNagpurBrokAccDebit2(String gstRrbRbiNagpurBrokAccDebit2) {
        this.gstRrbRbiNagpurBrokAccDebit2 = gstRrbRbiNagpurBrokAccDebit2;
    }

    public String getGstRemitSuspAccCredit2() {
        return gstRemitSuspAccCredit2;
    }

    public void setGstRemitSuspAccCredit2(String gstRemitSuspAccCredit2) {
        this.gstRemitSuspAccCredit2 = gstRemitSuspAccCredit2;
    }

    public String getRvbRbiClgDebit() {
        return rvbRbiClgDebit;
    }

    public void setRvbRbiClgDebit(String rvbRbiClgDebit) {
        this.rvbRbiClgDebit = rvbRbiClgDebit;
    }

    public String getRvbRrbRbiNagpurBrokAccCredit() {
        return rvbRrbRbiNagpurBrokAccCredit;
    }

    public void setRvbRrbRbiNagpurBrokAccCredit(String rvbRrbRbiNagpurBrokAccCredit) {
        this.rvbRrbRbiNagpurBrokAccCredit = rvbRrbRbiNagpurBrokAccCredit;
    }

    public String getGlRbiClgDebit() {
        return glRbiClgDebit;
    }

    public void setGlRbiClgDebit(String glRbiClgDebit) {
        this.glRbiClgDebit = glRbiClgDebit;
    }

    public String getGlRrbRbiNagpurBrokAccCredit() {
        return glRrbRbiNagpurBrokAccCredit;
    }

    public void setGlRrbRbiNagpurBrokAccCredit(String glRrbRbiNagpurBrokAccCredit) {
        this.glRrbRbiNagpurBrokAccCredit = glRrbRbiNagpurBrokAccCredit;
    }

    public String getHyglIntRecevAccDebit() {
        return hyglIntRecevAccDebit;
    }

    public void setHyglIntRecevAccDebit(String hyglIntRecevAccDebit) {
        this.hyglIntRecevAccDebit = hyglIntRecevAccDebit;
    }

    public String getHyglMinorDiffRoundingOff() {
        return hyglMinorDiffRoundingOff;
    }

    public void setHyglMinorDiffRoundingOff(String hyglMinorDiffRoundingOff) {
        this.hyglMinorDiffRoundingOff = hyglMinorDiffRoundingOff;
    }

    public String getHyglTdsInterestSecurCredit() {
        return hyglTdsInterestSecurCredit;
    }

    public void setHyglTdsInterestSecurCredit(String hyglTdsInterestSecurCredit) {
        this.hyglTdsInterestSecurCredit = hyglTdsInterestSecurCredit;
    }

    public String getHyglRbiBondTransitAccCredit1() {
        return hyglRbiBondTransitAccCredit1;
    }

    public void setHyglRbiBondTransitAccCredit1(String hyglRbiBondTransitAccCredit1) {
        this.hyglRbiBondTransitAccCredit1 = hyglRbiBondTransitAccCredit1;
    }

    public String getHyglEcsSettleAccCredit() {
        return hyglEcsSettleAccCredit;
    }

    public void setHyglEcsSettleAccCredit(String hyglEcsSettleAccCredit) {
        this.hyglEcsSettleAccCredit = hyglEcsSettleAccCredit;
    }

    public String getHyglRbiBondTransitAccCredit2() {
        return hyglRbiBondTransitAccCredit2;
    }

    public void setHyglRbiBondTransitAccCredit2(String hyglRbiBondTransitAccCredit2) {
        this.hyglRbiBondTransitAccCredit2 = hyglRbiBondTransitAccCredit2;
    }

    public String getHyglCmsDisbCtrlCliFundingCredit() {
        return hyglCmsDisbCtrlCliFundingCredit;
    }

    public void setHyglCmsDisbCtrlCliFundingCredit(String hyglCmsDisbCtrlCliFundingCredit) {
        this.hyglCmsDisbCtrlCliFundingCredit = hyglCmsDisbCtrlCliFundingCredit;
    }

    public String getHypmRbiBondTransitAccDebit1() {
        return hypmRbiBondTransitAccDebit1;
    }

    public void setHypmRbiBondTransitAccDebit1(String hypmRbiBondTransitAccDebit1) {
        this.hypmRbiBondTransitAccDebit1 = hypmRbiBondTransitAccDebit1;
    }

    public String getHypmEcsSettleAccDebit() {
        return hypmEcsSettleAccDebit;
    }

    public void setHypmEcsSettleAccDebit(String hypmEcsSettleAccDebit) {
        this.hypmEcsSettleAccDebit = hypmEcsSettleAccDebit;
    }

    public String getHypmRbiBondTransitAccDebit2() {
        return hypmRbiBondTransitAccDebit2;
    }

    public void setHypmRbiBondTransitAccDebit2(String hypmRbiBondTransitAccDebit2) {
        this.hypmRbiBondTransitAccDebit2 = hypmRbiBondTransitAccDebit2;
    }

    public String getHypmCmsDisbCtrlCliFundingDebit() {
        return hypmCmsDisbCtrlCliFundingDebit;
    }

    public void setHypmCmsDisbCtrlCliFundingDebit(String hypmCmsDisbCtrlCliFundingDebit) {
        this.hypmCmsDisbCtrlCliFundingDebit = hypmCmsDisbCtrlCliFundingDebit;
    }

    public String getHypmCustAccCredit() {
        return hypmCustAccCredit;
    }

    public void setHypmCustAccCredit(String hypmCustAccCredit) {
        this.hypmCustAccCredit = hypmCustAccCredit;
    }

    public String getUponRecRbiClgDebit() {
        return uponRecRbiClgDebit;
    }

    public void setUponRecRbiClgDebit(String uponRecRbiClgDebit) {
        this.uponRecRbiClgDebit = uponRecRbiClgDebit;
    }

    public String getUponRecInterestRecevableAccCredit() {
        return uponRecInterestRecevableAccCredit;
    }

    public void setUponRecInterestRecevableAccCredit(String uponRecInterestRecevableAccCredit) {
        this.uponRecInterestRecevableAccCredit = uponRecInterestRecevableAccCredit;
    }

    public String getRedempPrpReceivAccDebit() {
        return redempPrpReceivAccDebit;
    }

    public void setRedempPrpReceivAccDebit(String redempPrpReceivAccDebit) {
        this.redempPrpReceivAccDebit = redempPrpReceivAccDebit;
    }

    public String getRedemIntReceivAccDebit() {
        return redemIntReceivAccDebit;
    }

    public void setRedemIntReceivAccDebit(String redemIntReceivAccDebit) {
        this.redemIntReceivAccDebit = redemIntReceivAccDebit;
    }

    public String getRedemCmsDisbCtrlCliFundingCredit() {
        return redemCmsDisbCtrlCliFundingCredit;
    }

    public void setRedemCmsDisbCtrlCliFundingCredit(String redemCmsDisbCtrlCliFundingCredit) {
        this.redemCmsDisbCtrlCliFundingCredit = redemCmsDisbCtrlCliFundingCredit;
    }

    public String getRedemRbiBondTransitAccCredit() {
        return redemRbiBondTransitAccCredit;
    }

    public void setRedemRbiBondTransitAccCredit(String redemRbiBondTransitAccCredit) {
        this.redemRbiBondTransitAccCredit = redemRbiBondTransitAccCredit;
    }

    public String getRedemTdsSecur193Credit() {
        return redemTdsSecur193Credit;
    }

    public void setRedemTdsSecur193Credit(String redemTdsSecur193Credit) {
        this.redemTdsSecur193Credit = redemTdsSecur193Credit;
    }

    public String getRedemCustomCasaAccCredit() {
        return redemCustomCasaAccCredit;
    }

    public void setRedemCustomCasaAccCredit(String redemCustomCasaAccCredit) {
        this.redemCustomCasaAccCredit = redemCustomCasaAccCredit;
    }

    public String getRedemRbiBondTransitAccDebit() {
        return redemRbiBondTransitAccDebit;
    }

    public void setRedemRbiBondTransitAccDebit(String redemRbiBondTransitAccDebit) {
        this.redemRbiBondTransitAccDebit = redemRbiBondTransitAccDebit;
    }

    public String getRedemCmsDisbCtrlCliFundingDebit() {
        return redemCmsDisbCtrlCliFundingDebit;
    }

    public void setRedemCmsDisbCtrlCliFundingDebit(String redemCmsDisbCtrlCliFundingDebit) {
        this.redemCmsDisbCtrlCliFundingDebit = redemCmsDisbCtrlCliFundingDebit;
    }

    public String getRedemCustomAccCredit() {
        return redemCustomAccCredit;
    }

    public void setRedemCustomAccCredit(String redemCustomAccCredit) {
        this.redemCustomAccCredit = redemCustomAccCredit;
    }

    public String getPrfRbiClgDebit() {
        return prfRbiClgDebit;
    }

    public void setPrfRbiClgDebit(String prfRbiClgDebit) {
        this.prfRbiClgDebit = prfRbiClgDebit;
    }

    public String getPrfPrpReceivAccCredit() {
        return prfPrpReceivAccCredit;
    }

    public void setPrfPrpReceivAccCredit(String prfPrpReceivAccCredit) {
        this.prfPrpReceivAccCredit = prfPrpReceivAccCredit;
    }

    public String getPrfInterestReceivableAccCredit() {
        return prfInterestReceivableAccCredit;
    }

    public void setPrfInterestReceivableAccCredit(String prfInterestReceivableAccCredit) {
        this.prfInterestReceivableAccCredit = prfInterestReceivableAccCredit;
    }

    public String getSctcBrokExpRbiBondRrbDebit() {
        return sctcBrokExpRbiBondRrbDebit;
    }

    public void setSctcBrokExpRbiBondRrbDebit(String sctcBrokExpRbiBondRrbDebit) {
        this.sctcBrokExpRbiBondRrbDebit = sctcBrokExpRbiBondRrbDebit;
    }

    public String getSctcBrokExpRbiBondRrbRcmCredit() {
        return sctcBrokExpRbiBondRrbRcmCredit;
    }

    public void setSctcBrokExpRbiBondRrbRcmCredit(String sctcBrokExpRbiBondRrbRcmCredit) {
        this.sctcBrokExpRbiBondRrbRcmCredit = sctcBrokExpRbiBondRrbRcmCredit;
    }

    public String getSctcVendorAndBrokersAccCredit() {
        return sctcVendorAndBrokersAccCredit;
    }

    public void setSctcVendorAndBrokersAccCredit(String sctcVendorAndBrokersAccCredit) {
        this.sctcVendorAndBrokersAccCredit = sctcVendorAndBrokersAccCredit;
    }

    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public Map<String, String> getAccountingEntriesListModel() {
        return accountingEntriesListModel;
    }

    public void setAccountingEntriesListModel(Map<String, String> accountingEntriesListModel) {
        this.accountingEntriesListModel = accountingEntriesListModel;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getSchemeStatus() {
        return schemeStatus;
    }

    public void setSchemeStatus(String schemeStatus) {
        this.schemeStatus = schemeStatus;
    }

    public String getEffectiveFromDate() {
        return effectiveFromDate;
    }

    public void setEffectiveFromDate(String effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }
}
