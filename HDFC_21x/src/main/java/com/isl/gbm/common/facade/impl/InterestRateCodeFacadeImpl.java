package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.InterestRateCodeFacade;
import com.isl.gbm.common.service.InterestRateCodeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterestRateCodeFacadeImpl implements InterestRateCodeFacade {

    @Autowired
    InterestRateCodeService interestRateCodeService;

    @Override
    //  @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:ADD","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:ADD","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ADD"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ADD", "DC:INTEREST_RATE_CODE_MAINTENANCE:ADD", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:ADD", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ADD"})
    public ResponseDataDTO add(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.add(interestRateCodeRequestDTO);
    }

    @Override
    //  @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:MODIFY","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY"})
    public ResponseDataDTO getModify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.getModify(interestRateCodeRequestDTO);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:MODIFY","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "DC:INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:MODIFY", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:MODIFY"})
    public ResponseDataDTO modify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.modify(interestRateCodeRequestDTO);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:VERIFY","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY"})
    public ResponseDataDTO getVerifyDeleteDetails(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.getVerifyDeleteDetails(interestRateCodeRequestDTO);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:VERIFY","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "DC:INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:VERIFY", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:VERIFY"})
    public ResponseDataDTO verify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.verify(interestRateCodeRequestDTO);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:DELETE","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:DELETE","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:DELETE"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:DELETE", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:DELETE", "DC:INTEREST_RATE_CODE_MAINTENANCE:DELETE", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:DELETE", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:DELETE"})
    public ResponseDataDTO delete(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.delete(interestRateCodeRequestDTO);
    }

    @Override
    // @RequiresPermissions(logical = Logical.OR, value = {"DC:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY","GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY","GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ENQUIRY"})
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_PPF_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ENQUIRY", "GBM_DC_SCSS:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY", "DC:INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY", "GBM_DC_KVP:KVP_INTEREST_RATE_CODE_MAINTENANCE:ENQUIRY", "GBM_DC_SSA:SSA_INTEREST_RATE_CODE_MAINTENANCE_FOR_INVESTMENT_SCHEMES:ENQUIRY"})
    public ResponseDataDTO inquiry(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeService.inquiry(interestRateCodeRequestDTO);
    }
}
