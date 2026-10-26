package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
import com.isl.gbm.rbibond.facade.InterstRateCodeMaintenanceFacade;
import com.isl.gbm.rbibond.service.InterstRateCodeMaintenanceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterstRateCodeMaintenanceFacadeImpl implements InterstRateCodeMaintenanceFacade {

    @Autowired
    private InterstRateCodeMaintenanceService interstRateCodeMaintenanceService;


    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ADD")
    public ResponseDataDTO add(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.add(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY")
    public ResponseDataDTO modifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.modifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:MODIFY")
    public ResponseDataDTO modify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.modify(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY")
    public ResponseDataDTO verify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.verify(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR,value = {"GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY"})
    public ResponseDataDTO unVerifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.unVerifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:VERIFY")
    public ResponseDataDTO delete(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.delete(rateCodeMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_interest_rate_code_maintenance:ENQUIRY")
    public ResponseDataDTO inquiry(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceService.inquiry(rateCodeMaintenanceDTO);
    }
}
