package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CumToNonCumChangeOfBondTypeBo;
import com.isl.gbm.rbibond.dao.CumToNonCumChangeOfBondTypeDAO;
import com.isl.gbm.rbibond.dto.CumToNonCumChangeOfBondTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CumToNonCumChangeOfBondTypeBoImpl implements CumToNonCumChangeOfBondTypeBo {

    @Autowired
    private CumToNonCumChangeOfBondTypeDAO cumToNonCumChangeOfBondTypeDAO;



    @Override
    public ResponseDataDTO getPantorchInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getPantorchInq(cumToNonCumChangeOfBondTypeDTO);
    }

  
    @Override
    public ResponseDataDTO getAddDashBoard(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getAddDashBoard(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO add(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.add(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.addSubmit(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.inquiry(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.modifySelect(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO modify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.modify(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.verifySelect(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO verify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.verify(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO submitVerifyForm(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.submitVerifyForm(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO blaNo(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.blaNo(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getPantorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getPantorch(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getsubscriptionIdInq(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionId(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getsubscriptionId(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO blaTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.blaTorch(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getblaNoInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getblaNoInq(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO modifyPanTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.modifyPanTorch(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getRbiBondModuleNameDetails(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getRbiBondModuleNameDetailsInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getRbiBondModuleNameDetailsInq(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getBrokerTorch(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getGuardianDetails(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetailsVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getRbiBondModuleNameDetailsVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getPantorchVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getPantorchVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getblaNoVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getblaNoVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getsubscriptionIdVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.getsubscriptionIdVer(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO deleteSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeDAO.deleteSubmit(cumToNonCumChangeOfBondTypeDTO);
    }


}
