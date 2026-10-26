package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.facade.DashBoardViewForPhysicalAndInetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("gbmultra/rbiBond/dashBoardView")
public class DashBoardViewForPhysicalAndInetController {

@Autowired
DashBoardViewForPhysicalAndInetFacade dashBoardViewForPhysicalAndInetFacade;

@PostMapping("/getFileTypeList")
public ResponseEntity<ResponseDataDTO> getFileTypeList() throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.getFileTypeList());
}
@PostMapping("/add")
public ResponseEntity<ResponseDataDTO> add(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.add(cashFileUploadVerifySelectRequestDTO));
}

@PostMapping("/verifySelect")
public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadAndNetBankingFileUploadRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.verifySelect(cashFileUploadAndNetBankingFileUploadRequestDTO));
}

@PostMapping("/verify")
public ResponseEntity<ResponseDataDTO> verify(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.verify(cashFileUploadVerifySelectRequestDTO));
}

@PostMapping("/delete")
public ResponseEntity<ResponseDataDTO> delete(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.delete(cashFileUploadVerifySelectRequestDTO));
}

@PostMapping("/inquiry")
public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.inquiry(cashFileUploadVerifySelectRequestDTO));
}

@PostMapping( "/addValidation")
public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.addValidation(cashFileUploadVerifySelectRequestDTO));
}

@PostMapping("/addRejection")
public ResponseEntity<ResponseDataDTO> addRejection(@RequestBody CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO) throws IOException {
return APIResponseBuilder.buildResponseFromDto(dashBoardViewForPhysicalAndInetFacade.addRejection(cashFileUploadVerifySelectRequestDTO));
}

}
