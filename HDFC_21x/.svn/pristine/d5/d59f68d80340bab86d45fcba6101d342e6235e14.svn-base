package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.BondLedgerAccountMaintenanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/bla")
public class BondLedgerAccountMaintenanceController {

    @Autowired
    private BondLedgerAccountMaintenanceFacade bondLedgerAccountMaintenanceFacade;


    @PostMapping(path = "/getBcifApi")
    public ResponseEntity<ResponseDataDTO> getBcifApi(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.getBcifApi(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.add(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getBlaNumDetails")
    public ResponseEntity<ResponseDataDTO> getBlaNumDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.getBlaNumDetails(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.getGuardianDetails(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.inquiry(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/getTdsRateCode")
    public ResponseEntity<ResponseDataDTO> getTdsRateCode(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.getTdsRateCode(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.modifySelect(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.modify(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.verifySelect(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.verify(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondLedgerAccountMaintenanceFacade.delete(bondLedgerAccountMaintenanceDTO));
    }

}
