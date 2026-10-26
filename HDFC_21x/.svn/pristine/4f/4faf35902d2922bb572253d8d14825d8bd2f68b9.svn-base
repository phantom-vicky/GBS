package com.isl.gbm.rbibond.bo.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.DashBoardViewForPhysicalAndInetBO;
import com.isl.gbm.rbibond.dao.DashBoardViewForPhysicalAndInetDAO;
import com.isl.gbm.rbibond.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DashBoardViewForPhysicalAndInetBoImpl implements DashBoardViewForPhysicalAndInetBO {
    @Autowired
    DashBoardViewForPhysicalAndInetDAO dashBoardViewForPhysicalAndInetDAO;

    @Override
    public ResponseDataDTO add(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.add(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }
    @Override
    public ResponseDataDTO getFileTypeList() {
        return dashBoardViewForPhysicalAndInetDAO.getFileTypeList();
    }

    @Override
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.verify(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.inquiry(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO addValidation(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.addValidation(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO addRejection(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetDAO.addRejection(cashFileUploadVerifySelectRequestDTO);
    }
}