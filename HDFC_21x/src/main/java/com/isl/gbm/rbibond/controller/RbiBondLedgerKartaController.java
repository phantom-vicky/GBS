package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondLedgerKartaDTO;
import com.isl.gbm.rbibond.facade.RbiBondLedgerKartaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/ledgerKarta")
public class RbiBondLedgerKartaController {

    @Autowired
    RbiBondLedgerKartaFacade rbiBondLedgerKartaFacade;

    @PostMapping(path="/getTorchPan", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTorchPan(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.getTorchPan(rbiBondLedgerKartaDTO));
    }


    @PostMapping(path="/getModifyBla", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModifyTorchBla(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.getModifyTorchBla(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path="/getInquiryBla", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInquiryTorchBla( @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.getInquiryTorchBla(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path="/getModifyBlaTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModifyBla( @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.getModifyBla(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path="/getVerDelBla", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelTorchBla( @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.getVerDelTorchBla(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path="/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect( @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.modifySelect(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path="/addModify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addModify(@Validated @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO){
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.addModify(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.inquiry(rbiBondLedgerKartaDTO));
    }
    @PostMapping(path = "/inquirySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquirySelect(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.inquirySelect(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path = "/inquiryPan", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiryPan(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.inquiryPan(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path = "/verDelSelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verDelSelect(@RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.verDelSelect(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.verify(rbiBondLedgerKartaDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondLedgerKartaFacade.delete(rbiBondLedgerKartaDTO));
    }


}
