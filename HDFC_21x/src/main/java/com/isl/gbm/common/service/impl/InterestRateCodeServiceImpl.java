package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.bo.InterestRateCodeBO;
import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.service.InterestRateCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestRateCodeServiceImpl implements InterestRateCodeService {

    @Autowired
    InterestRateCodeBO interestRateCodeBO;

    @Override
    public ResponseDataDTO add(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.add(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO getModify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.getModify(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO modify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.modify(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.getVerifyDeleteDetails(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO verify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.verify(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.delete(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeBO.inquiry(interestRateCodeRequestDTO);
    }
}
