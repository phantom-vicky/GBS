package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.service.SolDateIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondTaxExcemptionMasterDTO;
import com.isl.gbm.rbibond.facade.TdsExemptionMasterFacade;
import com.isl.gbm.rbibond.service.TdsExemptionMasterService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TdsExemptionMasterFacadeImpl implements TdsExemptionMasterFacade {

    @Autowired
    TdsExemptionMasterService tdsExemptionMasterService;

    @Autowired
    SolDateIntegrationService solDateIntegrationService;


    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:ADD")
    public ResponseDataDTO getAddPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getAddPanNoDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:ADD")
    public ResponseDataDTO addCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.addCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:ADD")
    public ResponseDataDTO getCertNumber(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getCertNumber(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:ADD")
    public ResponseDataDTO getHolderAge(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getHolderAge(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:MODIFY")
    public ResponseDataDTO getModifyPanNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getModifyPanNoDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:MODIFY")
    public ResponseDataDTO getModifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getModifyDetails(bondTaxExcemptionMasterDTO);
    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:MODIFY")
    public ResponseDataDTO modifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.modifyCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:DELETE"})
    public ResponseDataDTO getVerDelPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getVerDelPanDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:VERIFY")
    public ResponseDataDTO getVerifyDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getVerifyDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:VERIFY")
    public ResponseDataDTO verifyCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.verifyCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:DELETE")
    public ResponseDataDTO getDeleteDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getDeleteDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:DELETE")
    public ResponseDataDTO deleteCutomerDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.deleteCutomerDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:INQUIRY")
    public ResponseDataDTO getInquiryPanDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getInquiryPanDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:INQUIRY")
    public ResponseDataDTO getVersionNoDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getVersionNoDetails(bondTaxExcemptionMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TAX_EXCEMPTION_MASTER:INQUIRY")
    public ResponseDataDTO getInquiryDetails(BondTaxExcemptionMasterDTO bondTaxExcemptionMasterDTO) {
        return tdsExemptionMasterService.getInquiryDetails(bondTaxExcemptionMasterDTO);
    }


    @Override
    public ResponseEntity<String> getFinancialYear() {
        final SolDateDto solDate = solDateIntegrationService.getSolDate();
        final String finYear = tdsExemptionMasterService.getFinancialYear(solDate.getSolDate());
        return new ResponseEntity<>(finYear, HttpStatus.OK);
    }
}
