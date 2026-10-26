package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondLevelMasterBO;
import com.isl.gbm.rbibond.dao.RbiBondLevelMasterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondLevelMasterBOImpl implements RbiBondLevelMasterBO {

    @Autowired
    RbiBondLevelMasterDAO levelMasterDAO;


    @Override
    public ResponseDataDTO inquiry(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.inquiry(levelMasterDto);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.getVerifyDeleteDetails(levelMasterDto);
    }

    @Override
    public ResponseDataDTO delete(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.delete(levelMasterDto);
    }

    @Override
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.verify(levelMasterDto);
    }

    @Override
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.add(levelMasterDto);
    }

    @Override
    public ResponseDataDTO addValidation(LevelMasterDto levelMasterDto) {
        return levelMasterDAO.addValidation(levelMasterDto);
    }
}
