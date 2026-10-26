package com.isl.gbm.common.bo.impl;

import com.isl.gbm.common.bo.CommonLevelMasterBO;
import com.isl.gbm.common.dao.CommonLevelMasterDAO;
import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CommonLevelMasterBOImpl implements CommonLevelMasterBO {

    @Autowired
    CommonLevelMasterDAO commonLevelMasterDAO;

//    @Autowired
//    GstLevelMasterDAO gstLevelMasterDAO;

    @Override
    public ResponseDataDTO inquiry(String schemeCode) {
        if (!StringUtils.endsWithIgnoreCase(schemeCode, CommonConstants.GST_MODULE_ID))
            return commonLevelMasterDAO.inquiry(schemeCode);
        else
            return null;
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(String schemeCode) {
        if (!StringUtils.endsWithIgnoreCase(schemeCode, CommonConstants.GST_MODULE_ID))
            return commonLevelMasterDAO.getVerifyDeleteDetails(schemeCode);
        else
            return null;
    }

    @Override
    public ResponseDataDTO delete(String schemeCode) {
        if (!StringUtils.endsWithIgnoreCase(schemeCode, CommonConstants.GST_MODULE_ID))
            return commonLevelMasterDAO.delete(schemeCode);
        else
            return null;
    }

    @Override
    public ResponseDataDTO verify(LevelMasterDto levelMasterDto) {
        if (!StringUtils.endsWithIgnoreCase(levelMasterDto.getSchemeCode(), CommonConstants.GST_MODULE_ID))
            return commonLevelMasterDAO.verify(levelMasterDto);
        else
            return null;
    }

    @Override
    public ResponseDataDTO add(LevelMasterDto levelMasterDto) {
        if (!StringUtils.endsWithIgnoreCase(levelMasterDto.getSchemeCode(), CommonConstants.GST_MODULE_ID))
            return commonLevelMasterDAO.add(levelMasterDto);
        else
            return null;
    }
}
