package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondLevelMasterMappingDTO;
import com.isl.gbm.rbibond.facade.RbiBondLevelMasterMappingFacade;
import com.isl.gbm.rbibond.service.RbiBondLevelMasterMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondLevelMasterMappingFacadeImpl implements RbiBondLevelMasterMappingFacade {

    @Autowired
    private RbiBondLevelMasterMappingService efreightLevelMasterMappingService;

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD")
    public ResponseDataDTO getAddDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.getAddDetails(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD")
    public ResponseDataDTO add(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.add(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.inquiry(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY")
    public ResponseDataDTO getModifyDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.getModifyDetails(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY")
    public ResponseDataDTO getVerifyDeleteDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.getVerifyDeleteDetails(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY")
    public ResponseDataDTO verify(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.verify(efreightLevelMasterMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY")
    public ResponseDataDTO modify(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.modify(efreightLevelMasterMappingDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return efreightLevelMasterMappingService.delete(efreightLevelMasterMappingDTO);
    }


}
