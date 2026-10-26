package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunForRedemptionDto;
import com.isl.gbm.rbibond.facade.RbiBondTrialRunForRedemptionFacade;
import com.isl.gbm.rbibond.service.RbiBondTrialRunForRedemptionService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RbiBondTrialRunForRedemptionFacadeImpl implements RbiBondTrialRunForRedemptionFacade {

    @Autowired
    RbiBondTrialRunForRedemptionService rbiBondTrialRunForRedemptionService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO add(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.add(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO addTorch(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.addTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getTrialRunProcess(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.getTrialRunProcess(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getBlaNo(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.getBlaNo(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getTrialRunDetailsReport(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) throws IOException {
        return rbiBondTrialRunForRedemptionService.getTrialRunDetailsReport(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO interestPaymentAddTorch(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.interestPaymentAddTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO intPayUnverifiedRecords(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.intPayUnverifiedRecords(rbiBondCentralizedInterestTrailRunDto);
    }



    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO interestPaymentStatus(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.interestPaymentStatus(rbiBondCentralizedInterestTrailRunDto);
    }


    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO getPanTorchDetails(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.getPanTorchDetails(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ADD")
    public ResponseDataDTO download(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.download(rbiBondCentralizedInterestTrailRunDto);

    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:VERIFY")
    public ResponseDataDTO verifySelect(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest) {
        return rbiBondTrialRunForRedemptionService.verifySelect(rbiBondCentralizedInterestTrailRunDtoRequest);
    }

    @Override
    public ResponseDataDTO getInterestTrailRunVerify(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return rbiBondTrialRunForRedemptionService.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest);
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.verifySubmit(rbiBondCentralizedInterestTrailRunDto);

    }

    @Override
    public ResponseDataDTO delete(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondTrialRunForRedemptionService.delete(rbiBondCentralizedInterestTrailRunDto);
    }


}
