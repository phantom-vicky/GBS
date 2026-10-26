package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CafuFileDownloadBO;
import com.isl.gbm.rbibond.dto.CafuFileDownloadDTO;
import com.isl.gbm.rbibond.service.CafuFileDownloadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CafuFileDownloadServiceImpl implements CafuFileDownloadService {


    @Autowired
    CafuFileDownloadBO cafuFileDownloadBO;


    @Override

    public ResponseDataDTO getCafuFile(CafuFileDownloadDTO cafuFileDownloadDTO) {
        return cafuFileDownloadBO.getCafuFile(cafuFileDownloadDTO);
    }



}
