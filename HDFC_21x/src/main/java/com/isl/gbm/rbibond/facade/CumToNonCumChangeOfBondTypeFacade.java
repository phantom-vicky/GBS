package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.CumToNonCumChangeOfBondTypeDTO;

public interface CumToNonCumChangeOfBondTypeFacade {

    ResponseDataDTO getPantorchInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO add(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO getAddDashBoard(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO addSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO inquiry(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO modifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO modify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO verifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO verify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO submitVerifyForm(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getsubscriptionId(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO blaNo(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getPantorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO getblaNoInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO blaTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO modifyPanTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO getRbiBondModuleNameDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getRbiBondModuleNameDetailsInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO getBrokerTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getsubscriptionIdInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

    ResponseDataDTO getGuardianDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getPantorchVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getblaNoVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getRbiBondModuleNameDetailsVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO getsubscriptionIdVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);
    ResponseDataDTO deleteSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO);

}
