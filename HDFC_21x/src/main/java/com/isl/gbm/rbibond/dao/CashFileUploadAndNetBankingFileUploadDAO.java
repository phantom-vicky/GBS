package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadAndNetBankingFileUploadRequestDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;

public interface CashFileUploadAndNetBankingFileUploadDAO {

    public ResponseDataDTO add(CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO);
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);

}
