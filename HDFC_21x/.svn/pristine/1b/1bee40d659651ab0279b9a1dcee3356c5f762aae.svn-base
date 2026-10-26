package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondNominationDto;
import com.isl.gbm.rbibond.facade.RbiBondNominationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "gbmultra/rbiBond/bondNomination")
@RestController
public class RbiBondNominationController {

    @Autowired
    RbiBondNominationFacade rbiBondNominationFacade;


    @PostMapping(path = "/addModify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addModify(@Validated @RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.addModify(rbiBondNominationDto));
    }

    @PostMapping(path = "/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.modifySelect(rbiBondNominationDto));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.inquiry(rbiBondNominationDto));
    }

    @PostMapping(path = "/verifyDeleteSelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.getVerifyDeleteDetails(rbiBondNominationDto));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete( @RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.delete(rbiBondNominationDto));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.verify(rbiBondNominationDto));
    }

    @PostMapping(path = "/modifyPanTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifyPanTorch(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.modifyPanTorch(rbiBondNominationDto));
    }

    @PostMapping(path = "/inquiryPanTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiryPanTorch(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.inquiryPanTorch(rbiBondNominationDto));
    }

    @PostMapping(path = "/blaNo", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.blaNo(rbiBondNominationDto));
    }

    @PostMapping(path = "/getInqBlaNo", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInqBlaNo(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.getInqBlaNo(rbiBondNominationDto));
    }

    @PostMapping(path = "/getsubscriptionIdMod", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdMod(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.getsubscriptionIdMod(rbiBondNominationDto));
    }

    @PostMapping(path = "/getsubscriptionIdInq", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.getsubscriptionIdInq(rbiBondNominationDto));
    }

    @PostMapping(path = "/nomineeValidation")
    public ResponseEntity<ResponseDataDTO> nomineeValidation(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.nomineeValidation(rbiBondNominationDto));
    }
    @PostMapping(path = "/fetchState")
    public ResponseEntity<ResponseDataDTO> fetchState(@RequestBody RbiBondNominationDto rbiBondNominationDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNominationFacade.fetchState(rbiBondNominationDto));
    }

}


