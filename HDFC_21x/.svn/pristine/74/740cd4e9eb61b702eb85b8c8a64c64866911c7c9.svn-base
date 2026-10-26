package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.RejectionReasonDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.facade.RejectionReasonMasterFacade;
import com.isl.gbm.rbibond.service.RejectionReasonMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectionReasonMasterFacadeImpl implements RejectionReasonMasterFacade {
    @Autowired
    RejectionReasonMasterService rejectionReasonMasterService;

    @Override
    public ResponseDataDTO addDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.addDetails(rejectionReasonDTO);
    }


    @Override
    public ResponseDataDTO modifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.modifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.modifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.verifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.verifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.inquiry(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO reject(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterService.reject(rejectionReasonDTO);
    }
}
