package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CafuFileDownloadBO;
import com.isl.gbm.rbibond.dao.CafuFileDownloadDao;
import com.isl.gbm.rbibond.dto.CafuFileDownloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CafuFileDownloadBOImpl implements CafuFileDownloadBO {


    @Autowired
    CafuFileDownloadDao cafuFileDownloadDao;


    @Override
    public ResponseDataDTO getCafuFile(CafuFileDownloadDTO cafuFileDownloadDTO) {
        return cafuFileDownloadDao.getCafuFile(cafuFileDownloadDTO);
    }
}
