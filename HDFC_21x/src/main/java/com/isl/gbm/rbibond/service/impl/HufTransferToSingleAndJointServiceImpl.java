package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.HufTransferToSingleAndJointBo;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.service.HufTransferToSingleAndJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HufTransferToSingleAndJointServiceImpl implements HufTransferToSingleAndJointService {
    @Autowired
    private HufTransferToSingleAndJointBo hufTransferToSingleAndJointBo;


    @Override
    public ResponseDataDTO getBcifApi(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getBcifApi(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getMinorDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getMinorDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getWboBranchDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getModuleNameAdd(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getModuleNameAdd(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO add(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.add(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO validatePan(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.validatePan(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.addSubmit(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO inquiry(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.inquiry(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getbranchName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getbranchName(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getbankName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getbankName(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.verifySelect(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO verify(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.verify(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO delete(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.delete(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO blaNo(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.blaNo(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getblaNoInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getblaNoInq(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.blaTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getsubscriptionIdInq(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getBrokerTorch(hufTransferSingleAndJointDTO);
    }


    @Override
    public ResponseDataDTO getGuardianDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getGuardianDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getNonHdfcTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getNonHdfcBranchTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO fetchState(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.fetchState(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointBo.getDetails(hufTransferSingleAndJointDTO);
    }

}
