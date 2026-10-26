package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanStatusUploadForTdsRequestAndResponseDTO;

public interface RbiBondPanStatusUpdateForTaxDao {
    ResponseDataDTO download(PanStatusUploadForTdsRequestAndResponseDTO dto);
    ResponseDataDTO upload(PanStatusUploadForTdsRequestAndResponseDTO dto);
}
