package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import com.isl.gbm.rbibond.facade.RbiBondCentralizedInterestTrailRunFacade;
import com.isl.gbm.rbibond.service.RbiBondCentralizedInterestTrailRunService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RbiBondCentralizedInterestTrailRunFacadeImpl implements RbiBondCentralizedInterestTrailRunFacade {

    @Autowired
    RbiBondCentralizedInterestTrailRunService rbiBondCentralizedInterestTrailRunService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.add(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.addTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.getTrialRunProcess(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.getReportTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY", "GBM_DC_RBIBOND:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:REPORT", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_INTEREST_TRAIL_RUN_ELIGIBLE_ACCOUNTS:ENQUIRY"})
    public ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException {
        return rbiBondCentralizedInterestTrailRunService.getTrialRunDetailsReport(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.interestPaymentAddTorch(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.intPayUnverifiedRecords(rbiBondCentralizedInterestTrailRunDto);
    }



    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.interestPaymentStatus(rbiBondCentralizedInterestTrailRunDto);
    }


    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.getInterestPayBatchTranDetails(rbiBondCentralizedInterestTrailRunDto);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:ADD")
    public ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.download(rbiBondCentralizedInterestTrailRunDto);

    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_TRIAL_RUN:VERIFY")
    public ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        return rbiBondCentralizedInterestTrailRunService.verifySelect(rbiBondCentralizedInterestTrailRunDtoRequest);
    }

    @Override
    public ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return rbiBondCentralizedInterestTrailRunService.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest);
    }
  @Override
    public ResponseDataDTO getInterestTrailRunVerifyDownload(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return rbiBondCentralizedInterestTrailRunService.getInterestTrailRunVerifyDownload(rbiBondCentralizedInterestTrailRunDtoRequest);
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.verifySubmit(rbiBondCentralizedInterestTrailRunDto);

    }

    @Override
    public ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return rbiBondCentralizedInterestTrailRunService.delete(rbiBondCentralizedInterestTrailRunDto);
    }


}
