package com.isl.gbm.common.bo.impl;

import com.isl.gbm.common.bo.InterestRateCodeBO;
import com.isl.gbm.common.dao.InterestRateCodeDAO;
import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterestRateCodeBOImpl implements InterestRateCodeBO {

    @Autowired
    InterestRateCodeDAO interestRateCodeDAO;

    @Override
    public ResponseDataDTO add(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.add(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO getModify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.getModify(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.inquiry(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO delete(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.delete(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO verify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.verify(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.getVerifyDeleteDetails(interestRateCodeRequestDTO);
    }

    @Override
    public ResponseDataDTO modify(InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return interestRateCodeDAO.modify(interestRateCodeRequestDTO);
    }
}
