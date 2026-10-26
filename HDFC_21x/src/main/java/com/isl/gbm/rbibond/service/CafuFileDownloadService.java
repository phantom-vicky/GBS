package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CafuFileDownloadDTO;

public interface CafuFileDownloadService {

    public ResponseDataDTO getCafuFile(CafuFileDownloadDTO cafuFileDownloadDTO);
}
