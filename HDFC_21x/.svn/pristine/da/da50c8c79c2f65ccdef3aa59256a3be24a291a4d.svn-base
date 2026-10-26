package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.bo.CommonLevelMasterBO;
import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.service.CommonLevelMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonLevelMasterServiceImpl implements CommonLevelMasterService {

    @Autowired
    CommonLevelMasterBO commonLevelMasterBO;

    @Override
    public ResponseDataDTO inquiry(String schemeCode) {
        return commonLevelMasterBO.inquiry(schemeCode);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(String schemeCode) {
        return commonLevelMasterBO.getVerifyDeleteDetails(schemeCode);
    }

    @Override
    public ResponseDataDTO delete(String schemeCode) {
        return commonLevelMasterBO.delete(schemeCode);
    }

    @Override
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        return commonLevelMasterBO.verify(levelMasterDto);
    }

    @Override
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        return commonLevelMasterBO.add(levelMasterDto);
    }
}
