package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BlaOpeningCumSubscriptionBo;

import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.service.BlaOpeningCumSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlaOpeningCumSubscriptionServiceImpl implements BlaOpeningCumSubscriptionService {

    @Autowired
    private BlaOpeningCumSubscriptionBo blaOpeningCumSubscriptionBo;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getBcifApi(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getMinorDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getMinorDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getWboBranchDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getAddDashBoard(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getAddDashBoard(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.add(bondLedgerAccountMaintenanceDTO);
    }
  @Override
    public ResponseDataDTO validatePan(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.validatePan(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.addSubmit(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.inquiry(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getbranchName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getbranchName(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getbankName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getbankName(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.verify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(bondLedgerAccountMaintenanceDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return blaOpeningCumSubscriptionBo.delete(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO blaNo(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.blaNo(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getblaNoInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getblaNoInq(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.blaTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifyPanTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.modifyPanTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdMod(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getsubscriptionIdMod(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getsubscriptionIdInq(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getRbiBondModuleNameDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getBrokerTorch(bondLedgerAccountMaintenanceDTO);
    }


    @Override
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getNonHdfcTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.getNonHdfcBranchTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO fetchState(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionBo.fetchState(bondLedgerAccountMaintenanceDTO);
    }
}
