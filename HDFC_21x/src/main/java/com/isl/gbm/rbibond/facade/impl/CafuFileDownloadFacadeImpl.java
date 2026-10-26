package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CafuFileDownloadDTO;
import com.isl.gbm.rbibond.facade.CafuFileDownloadFacade;
import com.isl.gbm.rbibond.service.CafuFileDownloadService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CafuFileDownloadFacadeImpl implements CafuFileDownloadFacade {

    @Autowired
    CafuFileDownloadService cafuFileDownloadService;


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_CAFU_FILE_DOWNLOAD:REPORT")
    public ResponseDataDTO getCafuFile(CafuFileDownloadDTO cafuFileDownloadDTO) {
        return cafuFileDownloadService.getCafuFile(cafuFileDownloadDTO);
    }
}
