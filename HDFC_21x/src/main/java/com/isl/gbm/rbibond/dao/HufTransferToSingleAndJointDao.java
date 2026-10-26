package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface HufTransferToSingleAndJointDao {

    ResponseDataDTO getModuleNameAdd(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getMinorDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getWboBranchDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO add(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
    ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    ResponseDataDTO validatePanRequest(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
    ResponseDataDTO addSubmit(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO inquiry(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
    ResponseDataDTO getbankName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
    ResponseDataDTO getbranchName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO verifySelect(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO verify(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO delete(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO blaNo(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getblaNoInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getsubscriptionIdInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO blaTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getBrokerTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getGuardianDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getNonHdfcTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO getNonHdfcBranchTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);

    ResponseDataDTO fetchState(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
    ResponseDataDTO getDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO);
}
