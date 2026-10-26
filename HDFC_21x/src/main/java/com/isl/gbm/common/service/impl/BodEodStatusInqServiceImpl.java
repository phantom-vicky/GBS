package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.bo.BodEodStatusInqBO;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.service.BodEodStatusInqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodEodStatusInqServiceImpl implements BodEodStatusInqService {

    @Autowired
    private BodEodStatusInqBO bodEodStatusInqBO;

    @Override
    public ResponseDataDTO getStatusInquiry() {
        return bodEodStatusInqBO.getStatusInquiry();
    }

    @Override
    public ResponseDataDTO getModuleName() {
        return bodEodStatusInqBO.getModuleName();
    }

    @Override
    public ResponseDataDTO getInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqBO.getInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    public ResponseDataDTO getStateTaxInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqBO.getStateTaxInquiryDetails(bodEodStatusInqDTO);
    }
}
