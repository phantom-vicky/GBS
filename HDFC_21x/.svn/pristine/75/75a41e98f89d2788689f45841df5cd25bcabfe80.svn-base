package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.NsdlPanValidationRedemptionOnMaturityBO;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.dto.NsdlPanValidationBulkUploadGenerator;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import com.isl.gbm.rbibond.service.NsdlPanValidationRedemptionOnMaturityService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

@Service
public class NsdlPanValidationRedemptionOnMaturityServiceImpl implements NsdlPanValidationRedemptionOnMaturityService {


    private final NsdlPanValidationRedemptionOnMaturityBO nsdlPanValidationRedemptionOnMaturityBO;

    @Autowired
    public NsdlPanValidationRedemptionOnMaturityServiceImpl
            (NsdlPanValidationRedemptionOnMaturityBO nsdlPanValidationRedemptionOnMaturityBO) {
        this.nsdlPanValidationRedemptionOnMaturityBO = nsdlPanValidationRedemptionOnMaturityBO;
    }
    @Override
    public ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.api(dto);
    }
    @Override
    public ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.verifyApi(dto);
    }
    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile,NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.upload(multipartFile,dto);
    }
    @Override
    public ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.delete(dto);
    }
    @Override
    public ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.download(dto);
    }
    @Override
    public ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.verifyDownload(dto);
    }
    @Override
    public ResponseDataDTO getUploadModuleName(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.getUploadModuleName(dto);
    }
    @Override
    public ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.verify(dto);
    }
    @Override
    public ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityBO.inquiry(dto);
    }
    @Override
    public ResponseDataDTO template(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto)
            throws IOException, InvalidFormatException {
        FileResponseDto fileResponseDto = new FileResponseDto();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(dto.getFileType().equalsIgnoreCase("P002")){
            fileResponseDto.setFileData(Base64.getEncoder().
                    encodeToString(NsdlPanValidationBulkUploadGenerator.incomeTaxPanToExcelFile()));
            fileResponseDto.setFileName("INCOME_TAX_PAN_STATUS"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        if(dto.getFileType().equalsIgnoreCase("P001")){
            fileResponseDto.setFileData(Base64.getEncoder().
                    encodeToString(NsdlPanValidationBulkUploadGenerator.nsdlPanToExcelFile()));
            fileResponseDto.setFileName("NSDL_PAN_STATUS"+ ".xlsx");
            responseDataDTO.setResult(fileResponseDto);
            responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        }
        return responseDataDTO;
    }
}
