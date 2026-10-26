package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;

public interface FreezeUnfreezeService {

    ResponseDataDTO getAddDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO inquiry(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO verify(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO modifySelect(FreezUnfreezDTO freezUnfreezDTO);

}
