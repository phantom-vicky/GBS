package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondTransferInDTO;
import com.isl.gbm.rbibond.facade.RbiBondTransferInFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/gbmultra/rbiBond/bondTransferIn")
public class RbiBondTransferInController {


   @Autowired
    RbiBondTransferInFacade rbiBondTransferInFacade;


    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.getblaNoInq(rbiBondTransferInDTO));
    }
    @PostMapping(path = "/getsubscriptionIdInq")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.getsubscriptionIdInq(rbiBondTransferInDTO));
    }
    @PostMapping(path = "/panTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> panTorch(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.panTorch(rbiBondTransferInDTO));
    }

    @PostMapping(path = "/addTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addTorch(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.addTorch(rbiBondTransferInDTO));
    }


    @PostMapping(path = "/otherBankDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> otherBankDetails() throws SQLException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.otherBankDetails());
    }


    @PostMapping(path = "/bondTransferInAdd", produces = "application/json")
    public ResponseEntity<ResponseDataDTO>  bondTransferInAdd(@RequestBody RbiBondTransferInDTO rbiBondTransferInAddDto ) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.bondTransferInAdd(rbiBondTransferInAddDto));
    }

    @PostMapping(path = "/verifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.verifySelect(rbiBondTransferInDTO));

    }


    @PostMapping(path = "/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.modifySelect(rbiBondTransferInDTO));

    }

    @PostMapping(path = "/modifyPAN", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifyPAN(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.modifyPAN(rbiBondTransferInDTO));

    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.delete(rbiBondTransferInDTO));

    }

    @PostMapping(path = "/modify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.modify(rbiBondTransferInDTO));

    }

    @PostMapping(path = "/getGuardianDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.getGuardianDetails(rbiBondTransferInDTO));

    }

    @PostMapping(path = "/validatePan")
    public ResponseEntity<ResponseDataDTO> validatePan(@RequestBody RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.validatePan(bondLedgerAccountMaintenanceDTO));
    }
    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verfify(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.verify(rbiBondTransferInDTO));
    }


    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondTransferInDTO rbiBondTransferInDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTransferInFacade.inquiry(rbiBondTransferInDTO));

    }

}
