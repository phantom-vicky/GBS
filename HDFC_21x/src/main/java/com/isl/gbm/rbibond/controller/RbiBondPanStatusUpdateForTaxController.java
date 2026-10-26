package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.PanStatusUploadForTdsRequestAndResponseDTO;
import com.isl.gbm.rbibond.facade.RbiBondPanStatusUpdateForTaxFacade;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbiBond/panStatus/upload/forTds")
public class RbiBondPanStatusUpdateForTaxController {
    @Autowired
    private RbiBondPanStatusUpdateForTaxFacade rbiBondPanStatusUpdateForTaxFacade;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file,
                                                  @RequestPart PanStatusUploadForTdsRequestAndResponseDTO dto){
        return APIResponseBuilder.buildResponseFromDto(rbiBondPanStatusUpdateForTaxFacade.upload(file,dto));
    }
    @PostMapping(path = "/getUploadModuleName")
    public ResponseEntity<ResponseDataDTO> getUploadModuleName(
            @RequestBody PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondPanStatusUpdateForTaxFacade.getUploadModuleName(dto));
    }
    @PostMapping(path = "/download")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondPanStatusUpdateForTaxFacade.download(dto));
    }
    @PostMapping(path = "/template")
    public ResponseEntity<ResponseDataDTO> template(@RequestBody PanStatusUploadForTdsRequestAndResponseDTO dto)
            throws IOException, InvalidFormatException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondPanStatusUpdateForTaxFacade.template(dto));
    }
}
