package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondLevelMasterBO;
import com.isl.gbm.rbibond.service.RbiBondLevelMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondLevelMasterServiceImpl implements RbiBondLevelMasterService {

    @Autowired
    RbiBondLevelMasterBO levelMasterBO;

    @Override
    public ResponseDataDTO inquiry(LevelMasterDto levelMasterDto) {
        return levelMasterBO.inquiry(levelMasterDto);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(LevelMasterDto levelMasterDto) {
        return levelMasterBO.getVerifyDeleteDetails(levelMasterDto);
    }

    @Override
    public ResponseDataDTO delete(LevelMasterDto levelMasterDto) {
        return levelMasterBO.delete(levelMasterDto);
    }

    @Override
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        return levelMasterBO.verify(levelMasterDto);
    }

    @Override
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        return levelMasterBO.add(levelMasterDto);
    }

    @Override
    public ResponseDataDTO addValidation(LevelMasterDto levelMasterDto) {
        return levelMasterBO.addValidation(levelMasterDto);
    }
}
