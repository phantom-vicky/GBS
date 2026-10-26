package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.CommonFlagDTO;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.facade.CommonFlagFacade;
import com.isl.gbm.rbibond.service.CommonFlagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonFlagFacadeImpl implements CommonFlagFacade {

    @Autowired
    private CommonFlagService commonFlagService;

    @Override

    public ResponseDataDTO getModifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.getModifyDetails(commonFlagDTO);
    }


    @Override

    public ResponseDataDTO modify(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.modify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO getVerifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.getVerifyDetails(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO verify(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.verify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO delete(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.delete(commonFlagDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagService.getInquiryDetails(commonFlagDTO);
    }



}
