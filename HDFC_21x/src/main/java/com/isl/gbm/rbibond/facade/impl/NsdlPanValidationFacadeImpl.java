package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRequestAndResponseDTO;
import com.isl.gbm.rbibond.facade.NsdlPanValidationFacade;
import com.isl.gbm.rbibond.service.NsdlPanValidationService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class NsdlPanValidationFacadeImpl implements NsdlPanValidationFacade {

    @Autowired
    private NsdlPanValidationService nsdlPanValidationService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:API")
    public ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.api(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:UPLOAD")
    public ResponseDataDTO upload(MultipartFile multipartFile,NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.upload(multipartFile,nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:UPLOAD")
    public ResponseDataDTO template(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) throws IOException, InvalidFormatException {
        return nsdlPanValidationService.template(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY")
    public ResponseDataDTO delete(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.delete(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY")
    public ResponseDataDTO verifyApi(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.verifyApi(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:DOWNLOAD")
    public ResponseDataDTO download(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.download(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY")
    public ResponseDataDTO verifyDownload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.verifyDownload(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:UPLOAD")
    public ResponseDataDTO getUploadModuleName(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.getUploadModuleName(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:VERIFY")
    public ResponseDataDTO verify(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.verify(nsdlPanValidationRequestAndResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_SCREEN:ENQUIRY")
    public ResponseDataDTO inquiry(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationService.inquiry(nsdlPanValidationRequestAndResponseDTO);
    }

}
