package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CashFileUploadAndNetBankingFileUploadBO;
import com.isl.gbm.rbibond.dto.CashFileUploadAndNetBankingFileUploadRequestDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import com.isl.gbm.rbibond.service.CashFileUploadAndNetBankingFileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CashFileUploadAndNetBankingFileUploadServiceImpl implements CashFileUploadAndNetBankingFileUploadService {
   private CashFileUploadAndNetBankingFileUploadBO cashFileUploadAndNetBankingFileUploadBO;
   public CashFileUploadAndNetBankingFileUploadServiceImpl(CashFileUploadAndNetBankingFileUploadBO cashFileUploadAndNetBankingFileUploadBO){
       this.cashFileUploadAndNetBankingFileUploadBO=cashFileUploadAndNetBankingFileUploadBO;
   }
    @Override
    public ResponseDataDTO add(MultipartFile[] file,CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadBO.add(file,cashFileUploadAndNetBankingFileUploadRequestDTO);
    }
    @Override
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadBO.verifySelect(cashFileUploadVerifySelectRequestDTO);
    }
    @Override
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadBO.verify(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadBO.delete(cashFileUploadVerifySelectRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) {
        return cashFileUploadAndNetBankingFileUploadBO.inquiry(cashFileUploadVerifySelectRequestDTO);
    }
}
