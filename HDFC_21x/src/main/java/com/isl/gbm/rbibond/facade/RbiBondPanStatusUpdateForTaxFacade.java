package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanStatusUploadForTdsRequestAndResponseDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RbiBondPanStatusUpdateForTaxFacade {

    ResponseDataDTO getUploadModuleName(PanStatusUploadForTdsRequestAndResponseDTO dto);
    ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO dto);
    ResponseDataDTO template(PanStatusUploadForTdsRequestAndResponseDTO dto) throws IOException, InvalidFormatException;
    ResponseDataDTO upload(MultipartFile multipartFiles, PanStatusUploadForTdsRequestAndResponseDTO dto);
}
