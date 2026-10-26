package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.RejectionReasonDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RejectionReasonMasterBo;
import com.isl.gbm.rbibond.dao.RejectionReasonMasterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RejectionReasonMasterBoImpl implements RejectionReasonMasterBo {


    @Autowired
    private RejectionReasonMasterDAO rejectionReasonMasterDAO;

    @Override
    public ResponseDataDTO addDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.addDetails(rejectionReasonDTO);
    }



    @Override
    public ResponseDataDTO modifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.modifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.modifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.verifyDetails(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.verifySubmit(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.inquiry(rejectionReasonDTO);
    }

    @Override
    public ResponseDataDTO reject(RejectionReasonDTO rejectionReasonDTO) {
        return rejectionReasonMasterDAO.reject(rejectionReasonDTO);
    }
}
