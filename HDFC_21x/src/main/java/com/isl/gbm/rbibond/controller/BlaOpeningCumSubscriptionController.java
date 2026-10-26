package com.isl.gbm.rbibond.controller;
import com.isl.gbm.Utils.RecordLockDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.BlaOpeningCumSubscriptionFacade;
import com.isl.gbm.rbibond.service.RecordLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("gbmultra/rbiBond/blaCumSub")
public class BlaOpeningCumSubscriptionController {
    private static final Logger log = LoggerFactory.getLogger(BlaOpeningCumSubscriptionController.class);
    @Autowired
    RecordLockService lockService;
    @Autowired
    private BlaOpeningCumSubscriptionFacade blaOpeningCumSubscriptionFacade;



    @PostMapping(path = "/getBcifApi")
    public ResponseEntity<ResponseDataDTO> getBcifApi(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getBcifApi(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/getMinorDetails")
    public ResponseEntity<ResponseDataDTO> getMinorDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getMinorDetails(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getWboBranchDetails")
    public ResponseEntity<ResponseDataDTO> getWboBranchDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getWboBranchDetails(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/getAddDashBoard")
    public ResponseEntity<ResponseDataDTO> getAddDashBoard(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getAddDashBoard(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/addVal")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.add(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/validatePan")
    public ResponseEntity<ResponseDataDTO> validatePan(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.validatePan(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> addSubmit(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.addSubmit(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.inquiry(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/getbranchName")
    public ResponseEntity<ResponseDataDTO> getbranchName(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getbranchName(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/getbankName")
    public ResponseEntity<ResponseDataDTO> getbankName(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getbankName(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.modifySelect(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.modify(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.verifySelect(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.verify(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.delete(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/blaNo")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.blaNo(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getblaNoInq(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getsubscriptionIdMod")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdMod(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getsubscriptionIdMod(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getsubscriptionIdInq")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getsubscriptionIdInq(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/blaTorch")
    public ResponseEntity<ResponseDataDTO> blaTorch(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.blaTorch(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/ModifyPanTorch")
    public ResponseEntity<ResponseDataDTO> modifyPanTorch(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.modifyPanTorch(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getModuleDetails")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getRbiBondModuleNameDetails(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerTorch(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getBrokerTorch(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getGuardianDetails(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getNonHdfcTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcTorch(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getNonHdfcTorch(brokerMasterMainDTO));
    }

    @PostMapping(path = "/getNonHdfcBranchTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcBranchTorch(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.getNonHdfcBranchTorch(brokerMasterMainDTO));
    }
    @PostMapping(path = "/unlockBla")
    public ResponseEntity<ResponseDataDTO> unlockBla(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.unlockBla(brokerMasterMainDTO));
    }

    @PostMapping(path = "/fetchState")
    public ResponseEntity<ResponseDataDTO> fetchState(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(blaOpeningCumSubscriptionFacade.fetchState(brokerMasterMainDTO));
    }

    @PostMapping(path="/lock")
    public ResponseDataDTO lock(@RequestBody RecordLockDTO lockInfo) {
        ResponseDataDTO dto = new ResponseDataDTO();
        boolean success = lockService.lockRecord(lockInfo.getRecordId(),lockInfo.getPan());
        if (!success) {
            dto.setMessage("LOCKED");
            dto.setStatusCode("0"); // failure
            log.info("Record Already Locked", LocalDateTime.now());
        } else {
            dto.setMessage("SUCCESS");
            dto.setStatusCode("1"); // success
            log.info("Record LOCKED successfully", LocalDateTime.now());
        }
        return dto;
    }

    @PostMapping(path="/unlock")
    public ResponseDataDTO unlock(@RequestBody RecordLockDTO lockInfo) {
        ResponseDataDTO dto=new ResponseDataDTO();
        boolean wasLocked = lockService.isLocked(lockInfo.getRecordId());
        if (wasLocked) {
            lockService.unlockByRecordId(lockInfo.getRecordId());
            dto.setMessage("Record unlocked successfully");
            dto.setStatusCode("1"); // success
            log.info("Record unlocked successfully", LocalDateTime.now());
        } else {
            dto.setMessage("Record was not locked");
            dto.setStatusCode("0"); // failure
            log.info("Record was not locked", LocalDateTime.now());
        }
        return dto;
    }
}