package com.isl.gbm.rbibond.service;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface NsdlPanValidationRedemptionOnMaturityService{
    ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO getUploadModuleName(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO template(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto)
            throws IOException, InvalidFormatException;
    ResponseDataDTO upload(MultipartFile multipartFiles, NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
}
