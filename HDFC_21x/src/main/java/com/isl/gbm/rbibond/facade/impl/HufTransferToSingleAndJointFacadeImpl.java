package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.facade.HufTransferToSingleAndJointFacade;
import com.isl.gbm.rbibond.service.HufTransferToSingleAndJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HufTransferToSingleAndJointFacadeImpl implements HufTransferToSingleAndJointFacade {
    @Autowired
    private HufTransferToSingleAndJointService hufTransferToSingleAndJointService;


    @Override
    public ResponseDataDTO getBcifApi(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getBcifApi(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getMinorDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getMinorDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getWboBranchDetails(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO add(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.add(hufTransferSingleAndJointDTO);
    }
    public ResponseDataDTO validatePan(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.validatePan(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getModuleNameAdd(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getModuleNameAdd(hufTransferSingleAndJointDTO);
    }
    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO addSubmit(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.addSubmit(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO inquiry(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.inquiry(hufTransferSingleAndJointDTO);
    }
    public ResponseDataDTO getbranchName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getbranchName(hufTransferSingleAndJointDTO);
    }
    public ResponseDataDTO getbankName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getbankName(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verifySelect(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.verifySelect(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.verify(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.delete(hufTransferSingleAndJointDTO);
    }
    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO blaNo(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.blaNo(hufTransferSingleAndJointDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getblaNoInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getblaNoInq(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO blaTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.blaTorch(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getsubscriptionIdInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getsubscriptionIdInq(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getBrokerTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getBrokerTorch(hufTransferSingleAndJointDTO);
    }



    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getGuardianDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getGuardianDetails(hufTransferSingleAndJointDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getNonHdfcTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getNonHdfcTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getNonHdfcBranchTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getNonHdfcBranchTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO fetchState(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.fetchState(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointService.getDetails(hufTransferSingleAndJointDTO);
    }

}
