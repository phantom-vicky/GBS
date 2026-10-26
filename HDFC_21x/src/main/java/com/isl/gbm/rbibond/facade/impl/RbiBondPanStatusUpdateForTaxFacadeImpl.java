package com.isl.gbm.rbibond.facade.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanStatusUploadForTdsRequestAndResponseDTO;
import com.isl.gbm.rbibond.facade.RbiBondPanStatusUpdateForTaxFacade;
import com.isl.gbm.rbibond.service.RbiBondPanStatusUpdateForTaxService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
public class RbiBondPanStatusUpdateForTaxFacadeImpl implements RbiBondPanStatusUpdateForTaxFacade {

    @Autowired
    private RbiBondPanStatusUpdateForTaxService rbiBondPanStatusUpdateForTaxService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:UPLOAD")
    public ResponseDataDTO upload(MultipartFile multipartFile, PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxService.upload(multipartFile,dto);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:UPLOAD")
    public ResponseDataDTO template(PanStatusUploadForTdsRequestAndResponseDTO dto)
            throws IOException, InvalidFormatException {
        return rbiBondPanStatusUpdateForTaxService.template(dto);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:DOWNLOAD")
    public ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxService.download(dto);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_STATUS_UPDATE_FOR_TDS_SCREEN:UPLOAD")
    public ResponseDataDTO getUploadModuleName(PanStatusUploadForTdsRequestAndResponseDTO dto) {
        return rbiBondPanStatusUpdateForTaxService.getUploadModuleName(dto);
    }
}
