package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CafuFileDownloadDTO;



public interface CafuFileDownloadFacade {

    public ResponseDataDTO getCafuFile( CafuFileDownloadDTO cafuFileDownloadDTO);

}
