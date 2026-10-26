package com.isl.gbm.common.bo.impl;

import com.isl.gbm.common.bo.BodEodStatusInqBO;
import com.isl.gbm.common.dao.BodEodStatusInqDAO;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BodEodStatusInqBOImpl implements BodEodStatusInqBO {

    @Autowired
    private BodEodStatusInqDAO bodEodStatusInqDAO;

    @Override
    public ResponseDataDTO getStatusInquiry() {
        return bodEodStatusInqDAO.getStatusInquiry();
    }

    @Override
    public ResponseDataDTO getModuleName() {
        return bodEodStatusInqDAO.getModuleName();
    }

    @Override
    public ResponseDataDTO getInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqDAO.getInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    public ResponseDataDTO getStateTaxInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqDAO.getStateTaxInquiryDetails(bodEodStatusInqDTO);
    }
}
