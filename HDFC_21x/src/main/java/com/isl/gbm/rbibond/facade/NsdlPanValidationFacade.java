package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRequestAndResponseDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NsdlPanValidationFacade {
    ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO verifyApi(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO verify(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO delete(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO getUploadModuleName(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO download(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO verifyDownload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO inquiry(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO template(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) throws IOException, InvalidFormatException;
    ResponseDataDTO upload(MultipartFile multipartFiles,NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);

}
