package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BrokerMasterMainDTO;
import com.isl.gbm.rbibond.facade.BrokerMasterMaintainanceFacade;
import com.isl.gbm.rbibond.facade.RbiBondCodeMappingFacade;
import com.isl.gbm.rbibond.service.BrokerMasterMaintainanceService;
import com.isl.gbm.rbibond.service.RbiBondCodeMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrokerMasterMaintainanceFacadeimpl implements BrokerMasterMaintainanceFacade {



    @Autowired
    private BrokerMasterMaintainanceService brokerMasterMaintainanceService;



    @Override
   // @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD")
    public ResponseDataDTO add(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.add(brokerMasterMainDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD")
    public ResponseDataDTO addValidation(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.addValidation(brokerMasterMainDTO);
    }

    @Override
  //  @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiryDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.inquiryDetails(brokerMasterMainDTO);
    }

    @Override
   // @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO verifyDeleteDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.verifyDeleteDetails(brokerMasterMainDTO);
    }

    @Override
 //  @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.verify(brokerMasterMainDTO);
    }

    @Override
  //  @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.delete(brokerMasterMainDTO);
    }

    @Override
  //  @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY")
    public ResponseDataDTO modifyDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.modifyDetails(brokerMasterMainDTO);
    }

    @Override
  //  @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySubmit(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.modifySubmit(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO getBranchCode(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.getBranchCode(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceService.getNonHdfcTorch(brokerMasterMainDTO);
    }
}
