package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadAndNetBankingFileUploadRequestDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface CashFileUploadAndNetBankingFileUploadBO {

    public ResponseDataDTO add(MultipartFile[] file,CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO);
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);

}
