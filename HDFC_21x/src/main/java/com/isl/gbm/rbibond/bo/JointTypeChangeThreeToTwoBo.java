package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.JointTypeChangeThreeToTwoDTO;

public interface JointTypeChangeThreeToTwoBo {


    ResponseDataDTO getBcifApi(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getMinorDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);
    ResponseDataDTO validatePan(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getWboBranchDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getAddModuleDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO add(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);
    ResponseDataDTO getDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO addSubmit(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO inquiry(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

//    ResponseDataDTO modifySelect(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);
//
//    ResponseDataDTO modify(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO verifySelect(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO verify(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO delete(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO blaNo(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getblaNoInq(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO blaTorch(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO modifyPanTorch(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getsubscriptionIdMod(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getsubscriptionIdInq(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getRbiBondModuleNameDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getBrokerTorch(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getGuardianDetails(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getNonHdfcTorch(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO getNonHdfcBranchTorch(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO unlockBla(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);

    ResponseDataDTO fetchState(JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO);
}