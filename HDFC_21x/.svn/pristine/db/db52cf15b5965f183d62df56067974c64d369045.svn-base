package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.facade.RbiBondLevelMasterFacade;
import com.isl.gbm.rbibond.service.RbiBondLevelMasterService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondLevelMasterFacadeImpl implements RbiBondLevelMasterFacade {

    @Autowired
    private RbiBondLevelMasterService levelMasterService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ENQUIRY"})
    public ResponseDataDTO inquiry(LevelMasterDto levelMasterDto) {
        return levelMasterService.inquiry(levelMasterDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY"})
    public ResponseDataDTO getVerifyDeleteDetails(LevelMasterDto levelMasterDto) {
        return levelMasterService.getVerifyDeleteDetails(levelMasterDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY"})
    public ResponseDataDTO delete(LevelMasterDto levelMasterDto) {
        return levelMasterService.delete(levelMasterDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:VERIFY"})
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        return levelMasterService.verify(levelMasterDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBI:GBM_RBI_BOND_LEVEL_MASTER:ADD"})
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        return levelMasterService.add(levelMasterDto);
    }

    @Override
    public ResponseDataDTO addValidation(LevelMasterDto levelMasterDto) {
        return levelMasterService.addValidation(levelMasterDto);
    }
}
