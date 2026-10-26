package com.isl.gbm.common.dao;

import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;

public interface InterestRateCodeDAO {

    public ResponseDataDTO add(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO getModify(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO modify(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO getVerifyDeleteDetails(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO verify(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO delete(InterestRateCodeRequestDTO interestRateCodeRequestDTO);

    public ResponseDataDTO inquiry(InterestRateCodeRequestDTO interestRateCodeRequestDTO);
}
