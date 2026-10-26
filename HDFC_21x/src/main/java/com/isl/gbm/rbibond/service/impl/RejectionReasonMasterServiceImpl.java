package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.RejectionReasonDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RejectionReasonMasterBo;
import com.isl.gbm.rbibond.service.RejectionReasonMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectionReasonMasterServiceImpl implements RejectionReasonMasterService {

@Autowired
    RejectionReasonMasterBo rejectionReasonMasterBo;


    @Override
    public ResponseDataDTO addDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.addDetails(rejectionReasonDTO);
    }


    @Override
    public ResponseDataDTO modifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.modifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.modifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.verifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.verifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.inquiry(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO reject(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterBo.reject(rejectionReasonDTO);
    }
}
