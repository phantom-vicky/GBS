package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
import com.isl.gbm.rbibond.facade.DropDownMasterRBIBondFacade;
import com.isl.gbm.rbibond.service.DropDownMasterRBIBondService;
import com.isl.gbm.rbibond.service.InterstRateCodeMaintenanceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DropDownMasterRBIBondFacadeImpl implements DropDownMasterRBIBondFacade {



    @Autowired
    private DropDownMasterRBIBondService dropDownMasterRBIBondService;

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ADD")
    public ResponseDataDTO addDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.addDetails(dropDownMasterDTO);
    }


    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY")
    public ResponseDataDTO modifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.modifyDetails(dropDownMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY")
    public ResponseDataDTO modifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.modifySubmit(dropDownMasterDTO);
    }

    @Override
//    @RequiresPermissions(logical = Logical.OR,value = {"GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY"})
    public ResponseDataDTO verifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.verifyDetails(dropDownMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY")
    public ResponseDataDTO verifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.verifySubmit(dropDownMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ENQUIRY")
    public ResponseDataDTO inquiry(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.inquiry(dropDownMasterDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY")
    public ResponseDataDTO reject(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondService.reject(dropDownMasterDTO);
    }

}
