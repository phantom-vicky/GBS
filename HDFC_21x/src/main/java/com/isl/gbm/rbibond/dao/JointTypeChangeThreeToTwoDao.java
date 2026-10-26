package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.JointTypeChangeThreeToTwoDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface JointTypeChangeThreeToTwoDao {

    ResponseDataDTO getAddModuleDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getMinorDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);
    ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    ResponseDataDTO validatePanRequest(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getWboBranchDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO add(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);
    ResponseDataDTO getDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO addSubmit(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO inquiry(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

//    ResponseDataDTO modifySelect(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);
//
//    ResponseDataDTO modify(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO verifySelect(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO verify(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO delete(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO blaNo(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getblaNoInq(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getsubscriptionIdMod(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getsubscriptionIdInq(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO blaTorch(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO modifyPanTorch(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getRbiBondModuleNameDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getBrokerTorch(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getGuardianDetails(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getNonHdfcTorch(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getNonHdfcBranchTorch(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO unlockBla(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);

    ResponseDataDTO fetchState(JointTypeChangeThreeToTwoDTO JointTypeChangeThreeToTwoDTO);
    
    
    
    
}
