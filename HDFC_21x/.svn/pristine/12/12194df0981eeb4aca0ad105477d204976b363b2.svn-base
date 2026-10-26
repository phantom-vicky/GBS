package com.isl.gbm.rbibond.facade.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import com.isl.gbm.rbibond.facade.NsdlPanValidationRedemptionOnMaturityFacade;
import com.isl.gbm.rbibond.service.NsdlPanValidationRedemptionOnMaturityService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@Component
public class NsdlPanValidationRedemptionOnMaturityFacadeImpl implements NsdlPanValidationRedemptionOnMaturityFacade {

    @Autowired
    private NsdlPanValidationRedemptionOnMaturityService nsdlPanValidationRedemptionOnMaturityService;

    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:API")
    public ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.api(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:UPLOAD")
    public ResponseDataDTO upload(MultipartFile multipartFile,NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.upload(multipartFile,dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:UPLOAD")
    public ResponseDataDTO template(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto)
            throws IOException, InvalidFormatException {
        return nsdlPanValidationRedemptionOnMaturityService.template(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY")
    public ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.delete(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:DOWNLOAD")
    public ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.download(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY")
    public ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.verifyDownload(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:UPLOAD")
    public ResponseDataDTO getUploadModuleName(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.getUploadModuleName(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY")
    public ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.verify(dto);
    }
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:VERIFY")
    public ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.verifyApi(dto);
    }
    @Override
 @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_NSDL_PAN_VALIDATION_REDEMPTION_ON_MATURITY_SCREEN:ENQUIRY")
    public ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityService.inquiry(dto);
    }
}
