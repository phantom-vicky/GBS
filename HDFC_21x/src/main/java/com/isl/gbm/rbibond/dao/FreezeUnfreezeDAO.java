package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;

public interface FreezeUnfreezeDAO {

    ResponseDataDTO getAddDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO getAddPANDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO getAddBLADetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO addSubmit(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO getInquiryDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO getInquiryPANDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO getInquiryBLADetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO verify(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO delete(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO verifySelect(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO verifyBLADetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO verifyPANDetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO modifySelect(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO modifySubmit(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO modifyBLADetails(FreezUnfreezDTO freezUnfreezDTO);

    ResponseDataDTO modifyPANDetails(FreezUnfreezDTO freezUnfreezDTO);
}
