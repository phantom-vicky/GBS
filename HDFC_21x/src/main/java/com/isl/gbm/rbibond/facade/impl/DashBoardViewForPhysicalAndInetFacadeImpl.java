package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import com.isl.gbm.rbibond.facade.DashBoardViewForPhysicalAndInetFacade;
import com.isl.gbm.rbibond.service.DashBoardViewForPhysicalAndInetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DashBoardViewForPhysicalAndInetFacadeImpl implements DashBoardViewForPhysicalAndInetFacade {
    @Autowired
    DashBoardViewForPhysicalAndInetService dashBoardViewForPhysicalAndInetService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD")
    public ResponseDataDTO add(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.add(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY")
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }
   @Override
    public ResponseDataDTO getFileTypeList() {
        return dashBoardViewForPhysicalAndInetService.getFileTypeList();
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY")
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.verify(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:VERIFY")
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:INQUIRY")
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO CashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.inquiry(CashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD")
    public ResponseDataDTO addValidation(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.addValidation(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_DASH_BOARD_VIEW_PHYSICAL_INET:ADD")
    public ResponseDataDTO addRejection(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return dashBoardViewForPhysicalAndInetService.addRejection(cashFileUploadVerifySelectRequestDTO);
    }
}
