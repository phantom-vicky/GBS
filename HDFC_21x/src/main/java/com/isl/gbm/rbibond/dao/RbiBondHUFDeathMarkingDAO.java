package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;

public interface RbiBondHUFDeathMarkingDAO {
    ResponseDataDTO getAddPanNoDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getCoParcenerDtls(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO addCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getVerDelPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getVerifyDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO verifyCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO deleteCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getInquiryPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getInquiryDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);

    ResponseDataDTO getAddDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO);
}
