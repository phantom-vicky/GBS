package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.CommonFlagDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CommonFlagBo;
import com.isl.gbm.rbibond.dao.CommonFlagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonFlagBoImpl implements CommonFlagBo {


@Autowired
    CommonFlagDAO commonFlagDAO;

    @Override

    public ResponseDataDTO getModifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.getModifyDetails(commonFlagDTO);
    }


    @Override

    public ResponseDataDTO modify(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.modify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO getVerifyDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.getVerifyDetails(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO verify(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.verify(commonFlagDTO);
    }

    @Override

    public ResponseDataDTO delete(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.delete(commonFlagDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(CommonFlagDTO commonFlagDTO) {
        return commonFlagDAO.getInquiryDetails(commonFlagDTO);
    }
}
