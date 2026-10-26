package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunForRedemptionDto;
import com.isl.gbm.rbibond.facade.RbiBondTrialRunForRedemptionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbiBond/redemption/trial/run")
public class RbiBondTrialRunForRedemptionController {


    @Autowired
    RbiBondTrialRunForRedemptionFacade rbiBondTrialRunForRedemptionFacade;

    @PostMapping(path = "/addTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addTorch(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.addTorch(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path="/getPanTorchDetails", produces = "application/json")
    public  ResponseEntity<ResponseDataDTO>  getPanTorchDetails(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto ){
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.getPanTorchDetails(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.add(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/verifySubmit", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.verifySubmit(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.delete(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getTrialRunProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTrialRunProcess(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.getTrialRunProcess(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getBlaNo", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBlaNo(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.getBlaNo(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getTrialRunDetailsReport", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTrialRunDetailsReport(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.getTrialRunDetailsReport(rbiBondCentralizedInterestTrailRunDto));
    }

    /*Interest Payment*/

    @PostMapping(path = "/getIntPayUnverifiedRecords", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> intPayUnverifiedRecords(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.intPayUnverifiedRecords(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/interestPaymentAddTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> interestPaymentAddTorch(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.interestPaymentAddTorch(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/interestPaymentStatus", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> interestPaymentStatus(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.interestPaymentStatus(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path = "/download", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.download(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/verifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.verifySelect(rbiBondCentralizedInterestTrailRunDtoRequest));
    }

    @PostMapping(path = "/getInterestTrailRunVerify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInterestTrailRunVerify(@RequestBody RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTrialRunForRedemptionFacade.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest));
    }
}
