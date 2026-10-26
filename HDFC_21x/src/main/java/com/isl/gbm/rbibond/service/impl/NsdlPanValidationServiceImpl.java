package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.NsdlPanValidationBO;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.dto.NsdlPanValidationBulkUploadGenerator;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRequestAndResponseDTO;
import com.isl.gbm.rbibond.service.NsdlPanValidationService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class NsdlPanValidationServiceImpl implements NsdlPanValidationService {

    @Autowired
    private NsdlPanValidationBO nsdlPanValidationBO;

    @Override
    public ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.api(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO verifyApi(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.verifyApi(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile,NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.upload(multipartFile,nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO delete(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.delete(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO download(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.download(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO verifyDownload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.verifyDownload(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO getUploadModuleName(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.getUploadModuleName(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO verify(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.verify(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO inquiry(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationBO.inquiry(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    public ResponseDataDTO template(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P002")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(NsdlPanValidationBulkUploadGenerator.incomeTaxPanToExcelFile()));
            fileResponseDto.setFileName("INCOME_TAX_PAN_STATUS"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P001")){
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(NsdlPanValidationBulkUploadGenerator.nsdlPanToExcelFile()));
            fileResponseDto.setFileName("NSDL_PAN_STATUS"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        return responseDataDTO;
    }
}
