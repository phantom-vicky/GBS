package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.CancellationSubscriptionCatCbtFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/CancelSub")
public class CancellationSubscriptionCatCbtController {

    @Autowired
    private CancellationSubscriptionCatCbtFacade cancellationSubscriptionCatCbtFacade;

    @PostMapping(path = "/addCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> addCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.addCloserSubCatCbt(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/verifyRejectCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> verifyRejectCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.verifyRejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/addSubCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> addSubCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.addSubCloserSubCatCbt(brokerMasterMainDTO));
    }

    @PostMapping(path = "/verifyCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> verifyCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.verifyCloserSubCatCbt(bondLedgerAccountMaintenanceDTO));
    }


    @PostMapping(path = "/rejectCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> rejectCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.rejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO));
    }

    @PostMapping(path = "/inquiryCloserSubCatCbt")
    public ResponseEntity<ResponseDataDTO> inquiryCloserSubCatCbt(@RequestBody BondLedgerAccountMaintenanceDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(cancellationSubscriptionCatCbtFacade.inquiryCloserSubCatCbt(brokerMasterMainDTO));
    }

}