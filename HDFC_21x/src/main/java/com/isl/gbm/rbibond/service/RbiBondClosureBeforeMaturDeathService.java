package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;

public interface RbiBondClosureBeforeMaturDeathService {

    ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);
}
