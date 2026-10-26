package com.isl.gbm.rbibond.service.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondPanStatusUpdateForTaxBO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.service.RbiBondPanStatusUpdateForTaxService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

@Service
public class RbiBondPanStatusUpdateForTaxServiceImpl implements RbiBondPanStatusUpdateForTaxService {


    private final RbiBondPanStatusUpdateForTaxBO rbiBondPanStatusUpdateForTaxBO;

    @Autowired
    public RbiBondPanStatusUpdateForTaxServiceImpl
            (RbiBondPanStatusUpdateForTaxBO rbiBondPanStatusUpdateForTaxBO) {
        this.rbiBondPanStatusUpdateForTaxBO = rbiBondPanStatusUpdateForTaxBO;
    }
    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile, PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxBO.upload(multipartFile,dto);
    }
    @Override
    public ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxBO.download(dto);
    }
    @Override
    public ResponseDataDTO getUploadModuleName(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxBO.getUploadModuleName(dto);
    }
    @Override
    public ResponseDataDTO template(PanStatusUploadForTdsRequestAndResponseDTO dto)
            throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(null!=dto.getFileType() && "I001".equalsIgnoreCase(dto.getFileType())){
            fileResponseDto.setFileData(Base64.getEncoder().
                    encodeToString(PanStatusUpdateForTDSBulkUploadGenerator.panStatusUpdateForTdsExcelFile()));
            fileResponseDto.setFileName("PAN_STATUS_UPDATE_FOR_TDS_INTEREST"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(null!=dto.getFileType() && "R001".equalsIgnoreCase(dto.getFileType())){
            fileResponseDto.setFileData(Base64.getEncoder().
                    encodeToString(PanStatusUpdateForTDSBulkUploadGenerator.panStatusUpdateForTdsExcelFile()));
            fileResponseDto.setFileName("PAN_STATUS_UPDATE_FOR_TDS_REDEMPTION"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        return responseDataDTO;
    }
}
