package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.CommonFlagDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CommonFlagBo;
import com.isl.gbm.rbibond.service.CommonFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonFlagServiceImpl implements CommonFlagService {

    @Autowired
    CommonFlagBo commonFlagBo;


    @Override

    public ResponseDataDTO getModifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.getModifyDetails(commonFlagDTO);
    }


    @Override

    public ResponseDataDTO modify(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.modify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO getVerifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.getVerifyDetails(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO verify(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.verify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO delete(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.delete(commonFlagDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagBo.getInquiryDetails(commonFlagDTO);
    }
}
