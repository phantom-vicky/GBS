package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRedemptionOnMaturityReqAndResDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface NsdlPanValidationRedemptionOnMaturityDAO {
ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO verifyApiRequest(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO verifyApiResponse(PanValidationRetriggerResponseDTO dto);
ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO, String userName,String solId);
ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO inquiryDetails(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
ResponseDataDTO upload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto);
}
