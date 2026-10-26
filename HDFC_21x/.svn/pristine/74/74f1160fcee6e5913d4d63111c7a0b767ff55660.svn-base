package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadAndNetBankingFileUploadRequestDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import com.isl.gbm.rbibond.facade.CashFileUploadAndNetBankingFileUploadFacade;
import com.isl.gbm.rbibond.service.CashFileUploadAndNetBankingFileUploadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CashFileUploadAndNetBankingFileUploadFacadeImpl implements CashFileUploadAndNetBankingFileUploadFacade {
    private CashFileUploadAndNetBankingFileUploadService cashFileUploadAndNetBankingFileUploadService;
    public CashFileUploadAndNetBankingFileUploadFacadeImpl(CashFileUploadAndNetBankingFileUploadService cashFileUploadAndNetBankingFileUploadService){
        this.cashFileUploadAndNetBankingFileUploadService=cashFileUploadAndNetBankingFileUploadService;
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:ADD")
    public ResponseDataDTO add(MultipartFile[] file,CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadService.add(file,cashFileUploadAndNetBankingFileUploadRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:VERIFY")
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadService.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:VERIFY")
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadService.verify(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:VERIFY")
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadService.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CASH_FILE_UPLOAD_NET_BANKING_UPLOAD:INQUIRY")
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadService.inquiry(cashFileUploadVerifySelectRequestDTO);
    }
}
