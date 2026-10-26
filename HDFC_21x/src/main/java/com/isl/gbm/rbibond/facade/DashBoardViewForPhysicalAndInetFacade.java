package com.isl.gbm.rbibond.facade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
public interface DashBoardViewForPhysicalAndInetFacade {
    public ResponseDataDTO add(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO getFileTypeList();
    public ResponseDataDTO verifySelect(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO verify(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO delete(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO inquiry(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO addRejection(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
    public ResponseDataDTO addValidation(CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO);
}
