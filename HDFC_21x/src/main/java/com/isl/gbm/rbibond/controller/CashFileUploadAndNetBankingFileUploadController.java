package com.isl.gbm.rbibond.controller;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.CashFileUploadAndNetBankingFileUploadRequestDTO;
import com.isl.gbm.rbibond.dto.CashFileUploadVerifySelectRequestDTO;
import com.isl.gbm.rbibond.facade.CashFileUploadAndNetBankingFileUploadFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@Validated
@RequestMapping("gbmultra/rbiBond/cashAndNetBanking")
public class CashFileUploadAndNetBankingFileUploadController {
    private CashFileUploadAndNetBankingFileUploadFacade cashFileUploadAndNetBankingFileUploadFacade;
public CashFileUploadAndNetBankingFileUploadController(CashFileUploadAndNetBankingFileUploadFacade cashFileUploadAndNetBankingFileUploadFacade){
    this.cashFileUploadAndNetBankingFileUploadFacade=cashFileUploadAndNetBankingFileUploadFacade;
}
    @RequestMapping(value = "/fileUpload/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@RequestPart("file") final MultipartFile[] file,@RequestPart CashFileUploadAndNetBankingFileUploadRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(cashFileUploadAndNetBankingFileUploadFacade.add(file,cashFileUploadAndNetBankingFileUploadRequestDTO));
    }

    @PostMapping("/fileUpload/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(cashFileUploadAndNetBankingFileUploadFacade.verifySelect(cashFileUploadVerifySelectRequestDTO));
    }

    @PostMapping("/fileUpload/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(cashFileUploadAndNetBankingFileUploadFacade.verify(cashFileUploadVerifySelectRequestDTO));
    }

    @PostMapping("/fileUpload/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(cashFileUploadAndNetBankingFileUploadFacade.delete(cashFileUploadVerifySelectRequestDTO));
    }

    @PostMapping("/fileUpload/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(cashFileUploadAndNetBankingFileUploadFacade.inquiry(cashFileUploadVerifySelectRequestDTO));
    }
}
