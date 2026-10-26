package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondBlaDeathMarkingDTO;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;
import com.isl.gbm.rbibond.facade.RbiBondHUFDeathMarkingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbibond/hufDeathMarking")
public class RbiBondHUFDeathMarkingController {

    @Autowired
    RbiBondHUFDeathMarkingFacade rbiBondHUFDeathMarkingFacade;

    @PostMapping(path = "/addSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getAddPanNoDetails(@RequestBody RbiBondHUFDeathMarkingDTO  rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getAddPanNoDetails(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/addDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getAddDetails(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/coParcenrSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getCoParcenerDtls(@RequestBody RbiBondHUFDeathMarkingDTO  rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getCoParcenerDtls(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/add",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> addCutomerDetails(@RequestBody RbiBondHUFDeathMarkingDTO  rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.addCutomerDetails(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/verDelSelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getVerDelPanDetails(rbiBondHUFDeathMarkingDTO));
    }



    @PostMapping(path = "/verifyDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getVerifyDetails(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/verify",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> verifyCutomerDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.verifyCutomerDetails(rbiBondHUFDeathMarkingDTO));
    }


    @PostMapping(path = "/delete",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> deleteCutomerDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.deleteCutomerDetails(rbiBondHUFDeathMarkingDTO));
    }
    @PostMapping(path = "/inquirySelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getInquiryPanDetails(rbiBondHUFDeathMarkingDTO));
    }

    @PostMapping(path = "/inquiryDetails",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondHUFDeathMarkingFacade.getInquiryDetails(rbiBondHUFDeathMarkingDTO));
    }

}
