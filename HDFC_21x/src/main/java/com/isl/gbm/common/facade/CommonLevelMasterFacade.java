package com.isl.gbm.common.facade;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;

public interface CommonLevelMasterFacade {

    public ResponseDataDTO inquiry(String schemeCode);

    public ResponseDataDTO getVerifyDeleteDetails(String schemeCode);

    public ResponseDataDTO delete(String schemeCode);

    public ResponseDataDTO verify(LevelMasterDto levelMasterDto);

    public ResponseDataDTO add(LevelMasterDto levelMasterDto);
}
