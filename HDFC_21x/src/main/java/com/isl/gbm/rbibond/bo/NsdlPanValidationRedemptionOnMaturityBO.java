package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import org.springframework.web.multipart.MultipartFile;
public interface NsdlPanValidationRedemptionOnMaturityBO{
    ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO getUploadModuleName(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
    ResponseDataDTO upload(MultipartFile multipartFiles, NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
}
