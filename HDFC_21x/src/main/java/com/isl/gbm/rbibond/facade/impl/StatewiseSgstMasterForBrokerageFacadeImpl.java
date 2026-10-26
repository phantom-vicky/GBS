package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.StatewiseSgstMasterForBrokerageDto;
import com.isl.gbm.rbibond.facade.StatewiseSgstMasterForBrokerageFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StatewiseSgstMasterForBrokerageFacadeImpl implements StatewiseSgstMasterForBrokerageFacade {

    @Autowired
    private com.isl.gbm.rbibond.service.StatewiseSgstMasterForBrokerageService statewiseSgstMasterForBrokerageService;


//    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD")
//    public ResponseDataDTO getAddDetails(RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
//        return efreightLevelMasterMappingService.getAddDetails(efreightLevelMasterMappingDTO);
//    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ADD")
    public ResponseDataDTO add(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.add(statewiseSgstMasterForBrokerageDto);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:ENQUIRY")
    public ResponseDataDTO inquiry(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.inquiry(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY")
    public ResponseDataDTO getModifyDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.getModifyDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY")
    public ResponseDataDTO getVerifyDeleteDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.getVerifyDeleteDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:VERIFY")
    public ResponseDataDTO verify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.verify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_level_master_mapping:MODIFY")
    public ResponseDataDTO modify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.modify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO delete(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.delete(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getStateDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageService.getStateDetails(statewiseSgstMasterForBrokerageDto);
    }


}

