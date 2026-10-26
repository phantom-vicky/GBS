package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondBlaDeathMarkingDTO;
import com.isl.gbm.rbibond.facade.RbiBondBlaDeathMarkingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbibond/blaDeathMarking")
public class RbiBondBlaDeathMarkingController {


    @Autowired
    RbiBondBlaDeathMarkingFacade rbiBondBlaDeathMarkingFacade;


    @PostMapping(path = "/addSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getAddPanNoDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getAddPanNoDetails(rbiBondBlaDeathMarkingDTO));
    }

    @PostMapping(path = "/addDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getAddDetails(rbiBondBlaDeathMarkingDTO));
    }

    @PostMapping(path = "/add",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> addCutomerDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.addCutomerDetails(rbiBondBlaDeathMarkingDTO));
    }


    @PostMapping(path = "/verDelSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getVerDelPanDetails(rbiBondBlaDeathMarkingDTO));
    }

    @PostMapping(path = "/verifyDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getVerifyDetails(rbiBondBlaDeathMarkingDTO));
    }


    @PostMapping(path = "/verify",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> verifyCutomerDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.verifyCutomerDetails(rbiBondBlaDeathMarkingDTO));
    }



    @PostMapping(path = "/deleteDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getDeleteDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getDeleteDetails(rbiBondBlaDeathMarkingDTO));
    }


    @PostMapping(path = "/delete",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> deleteCutomerDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.deleteCutomerDetails(rbiBondBlaDeathMarkingDTO));
    }


    @PostMapping(path = "/inquirySelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getInquiryPanDetails(rbiBondBlaDeathMarkingDTO));
    }

    @PostMapping(path = "/inquiryDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondBlaDeathMarkingFacade.getInquiryDetails(rbiBondBlaDeathMarkingDTO));
    }



}
