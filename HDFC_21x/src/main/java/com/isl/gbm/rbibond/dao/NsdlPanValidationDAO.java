package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.NsdlPanValidationRequestAndResponseDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface NsdlPanValidationDAO {
    ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO verifyApiRequest(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO, String userName,String solId);
    ResponseDataDTO verify(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO delete(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO download(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO verifyDownload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO inquiry(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO inquiryDetails(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
    ResponseDataDTO upload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO);
}
