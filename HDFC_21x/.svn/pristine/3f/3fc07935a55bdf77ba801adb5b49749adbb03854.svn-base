package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRequestAndResponseDTO;
import com.isl.gbm.rbibond.facade.NsdlPanValidationFacade;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbiBond/nsdlPan")
public class NsdlPanValidationController {
    @Autowired
    NsdlPanValidationFacade nsdlPanValidationFacade;

    @PostMapping(path = "/validation/api")
    public ResponseEntity<ResponseDataDTO> api(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.api(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/verifyApi")
    public ResponseEntity<ResponseDataDTO> verifyApi(@RequestBody NsdlPanValidationRequestAndResponseDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.verifyApi(dto));
    }

    @RequestMapping(value = "/validation/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file, @RequestPart NsdlPanValidationRequestAndResponseDTO dto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.upload(file,dto));
    }
    @PostMapping(path = "/validation/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.delete(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/getUploadModuleName")
    public ResponseEntity<ResponseDataDTO> getUploadModuleName(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.getUploadModuleName(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.verify(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/download")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.download(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/verifyDownload")
    public ResponseEntity<ResponseDataDTO> verifyDownload(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.verifyDownload(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.inquiry(nsdlPanValidationRequestAndResponseDTO));
    }
    @PostMapping(path = "/validation/template")
    public ResponseEntity<ResponseDataDTO> template(@RequestBody NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) throws IOException, InvalidFormatException {
        return APIResponseBuilder.buildResponseFromDto(nsdlPanValidationFacade.template(nsdlPanValidationRequestAndResponseDTO));
    }
}
