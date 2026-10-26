package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.CommonLevelMasterFacade;
import com.isl.gbm.common.service.CommonLevelMasterService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonLevelMasterFacadeImpl implements CommonLevelMasterFacade {

    @Autowired
    CommonLevelMasterService commonLevelMasterService;

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:COMMON_LEVEL_MASTER:ENQUIRY","GBM_DC_KVP:KVP_LEVEL_MASTER:ENQUIRY","GBM_DC_SSA:SSA_LEVEL_MASTER:ENQUIRY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_LEVEL_MASTER:ENQUIRY", "GBM_DC_CBDT:CBDT_LEVEL_MASTER:ENQUIRY", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:ENQUIRY", "DC:COMMON_LEVEL_MASTER:ENQUIRY", "GBM_DC_KVP:KVP_LEVEL_MASTER:ENQUIRY", "GBM_DC_SSA:SSA_LEVEL_MASTER:ENQUIRY","GBM_DC_ICEGATE:ICEGATE_LEVEL_MASTER:ENQUIRY"})
    public ResponseDataDTO inquiry(String schemeCode) {
        return commonLevelMasterService.inquiry(schemeCode);
    }

    @Override
    //@RequiresPermissions(logical = Logical.OR, value = {"DC:COMMON_LEVEL_MASTER:VERIFY ","GBM_DC_KVP:KVP_LEVEL_MASTER:VERIFY","GBM_DC_SSA:SSA_LEVEL_MASTER:VERIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_LEVEL_MASTER:VERIFY", "GBM_DC_CBDT:CBDT_LEVEL_MASTER:VERIFY", "GBM_DC_CBDT:CBDT_LEVEL_MASTER:DELETE", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:DELETE", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:VERIFY", "DC:COMMON_LEVEL_MASTER:VERIFY ", "GBM_DC_KVP:KVP_LEVEL_MASTER:VERIFY", "GBM_DC_SSA:SSA_LEVEL_MASTER:VERIFY","GBM_DC_ICEGATE:ICEGATE_LEVEL_MASTER:VERIFY"})
    public ResponseDataDTO getVerifyDeleteDetails(String schemeCode) {
        return commonLevelMasterService.getVerifyDeleteDetails(schemeCode);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:COMMON_LEVEL_MASTER:DELETE","GBM_DC_KVP:KVP_LEVEL_MASTER:DELETE","GBM_DC_SSA:SSA_LEVEL_MASTER:DELETE"})
   // @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_LEVEL_MASTER:DELETE", "GBM_DC_CBDT:CBDT_LEVEL_MASTER:DELETE", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:DELETE", "DC:COMMON_LEVEL_MASTER:DELETE", "GBM_DC_KVP:KVP_LEVEL_MASTER:DELETE", "GBM_DC_SSA:SSA_LEVEL_MASTER:DELETE","GBM_DC_ICEGATE:ICEGATE_LEVEL_MASTER:DELETE"})
    public ResponseDataDTO delete(String schemeCode) {
        return commonLevelMasterService.delete(schemeCode);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:COMMON_LEVEL_MASTER:VERIFY","GBM_DC_KVP:KVP_LEVEL_MASTER:VERIFY","GBM_DC_SSA:SSA_LEVEL_MASTER:VERIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_LEVEL_MASTER:VERIFY", "GBM_DC_CBDT:CBDT_LEVEL_MASTER:VERIFY", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:VERIFY", "DC:COMMON_LEVEL_MASTER:VERIFY", "GBM_DC_KVP:KVP_LEVEL_MASTER:VERIFY", "GBM_DC_SSA:SSA_LEVEL_MASTER:VERIFY","GBM_DC_ICEGATE:ICEGATE_LEVEL_MASTER:VERIFY"})
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        return commonLevelMasterService.verify(levelMasterDto);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:COMMON_LEVEL_MASTER:ADD","GBM_DC_KVP:KVP_LEVEL_MASTER:ADD","GBM_DC_SSA:SSA_LEVEL_MASTER:ADD"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_LEVEL_MASTER:ADD", "GBM_DC_SCSS:SCSS_LEVEL_MASTER:ADD", "DC:COMMON_LEVEL_MASTER:ADD", "GBM_DC_KVP:KVP_LEVEL_MASTER:ADD", "GBM_DC_SSA:SSA_LEVEL_MASTER:ADD","GBM_DC_CBDT:CBDT_LEVEL_MASTER:ADD","GBM_DC_ICEGATE:ICEGATE_LEVEL_MASTER:ADD"})
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        return commonLevelMasterService.add(levelMasterDto);
    }
}
