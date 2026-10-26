package com.isl.gbm.rbibond.service.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.ModeOfHoldingChangeBo;
import com.isl.gbm.rbibond.dto.ModeOfHoldingChangeDTO;
import com.isl.gbm.rbibond.service.ModeOfHoldingChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeOfHoldingChangeServiceImpl implements ModeOfHoldingChangeService {

    @Autowired
    private ModeOfHoldingChangeBo modeOfHoldingChangeBo;


    @Override
    public ResponseDataDTO getBcifApi(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getBcifApi(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getMinorDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getMinorDetails(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO validatePan(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.validatePan(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getWboBranchDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getAddModuleDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getAddModuleDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO add(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.add(modeOfHoldingChangeDTO);
    }
   @Override
    public ResponseDataDTO getDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.addSubmit(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO inquiry(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.inquiry(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.modifySelect(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO modify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.modify(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.verifySelect(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO verify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.verify(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO delete(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.delete(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO blaNo(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.blaNo(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getblaNoInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getblaNoInq(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.blaTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO modifyPanTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.modifyPanTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdMod(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getsubscriptionIdMod(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getsubscriptionIdInq(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getRbiBondModuleNameDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getBrokerTorch(modeOfHoldingChangeDTO);
    }


    @Override
    public ResponseDataDTO getGuardianDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getGuardianDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getNonHdfcTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.getNonHdfcBranchTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO unlockBla(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.unlockBla(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO fetchState(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeBo.fetchState(modeOfHoldingChangeDTO);
    }
}
