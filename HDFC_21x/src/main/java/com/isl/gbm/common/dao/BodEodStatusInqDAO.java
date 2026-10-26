package com.isl.gbm.common.dao;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;

public interface BodEodStatusInqDAO {

    public ResponseDataDTO getStatusInquiry();

    public ResponseDataDTO getModuleName();

    public ResponseDataDTO getInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO);

    public ResponseDataDTO getStateTaxInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO);
}
