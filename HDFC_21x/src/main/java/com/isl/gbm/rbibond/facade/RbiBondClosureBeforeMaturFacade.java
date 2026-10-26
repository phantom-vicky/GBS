package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;

public interface RbiBondClosureBeforeMaturFacade {


     ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);

     ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);

     ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);

     ResponseDataDTO add(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);

     ResponseDataDTO delete(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);

     ResponseDataDTO verify(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO);
}




