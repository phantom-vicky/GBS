package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.DashBoardViewForPhysicalAndInetBO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import com.isl.gbm.rbibond.service.DashBoardViewForPhysicalAndInetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardViewForPhysicalAndInetServiceImpl implements DashBoardViewForPhysicalAndInetService {
    @Autowired
    DashBoardViewForPhysicalAndInetBO dashBoardViewForPhysicalAndInetBO;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;


    @Override
    public ResponseDataDTO add(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.add(cashFileUploadVerifySelectRequestDTO);
    }
    @Override
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }
    @Override
    public ResponseDataDTO getFileTypeList() {
        return dashBoardViewForPhysicalAndInetBO.getFileTypeList();
    }
    @Override
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.verify(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.inquiry(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO addValidation(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetBO.addValidation(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO addRejection(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(cashFileUploadVerifySelectRequestDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return dashBoardViewForPhysicalAndInetBO.addRejection(cashFileUploadVerifySelectRequestDTO);
    }
}
