package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import com.isl.gbm.rbibond.facade.RbiBondCentralizedInterestTrailRunFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rbiBond/interest/trial/run")
public class RbiBondCentralizedInterestTrailRunController {


    @Autowired
    RbiBondCentralizedInterestTrailRunFacade rbiBondCentralizedInterestTrailRunFacade;

    @PostMapping(path = "/addTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addTorch(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.addTorch(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path="/getInterestPayBatchTranDetails", produces = "application/json")
    public  ResponseEntity<ResponseDataDTO>  getInterestPayBatchTranDetails(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto ){
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getInterestPayBatchTranDetails(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.add(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/verifySubmit", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.verifySubmit(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.delete(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getTrialRunProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTrialRunProcess(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getTrialRunProcess(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getReportTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getReportTorch(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getReportTorch(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/getTrialRunDetailsReport", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTrialRunDetailsReport(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getTrialRunDetailsReport(rbiBondCentralizedInterestTrailRunDto));
    }

    /*Interest Payment*/

    @PostMapping(path = "/getIntPayUnverifiedRecords", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> intPayUnverifiedRecords(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.intPayUnverifiedRecords(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/interestPaymentAddTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> interestPaymentAddTorch(@RequestBody RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.interestPaymentAddTorch(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/interestPaymentStatus", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> interestPaymentStatus(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.interestPaymentStatus(rbiBondCentralizedInterestTrailRunDto));
    }


    @PostMapping(path = "/download", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.download(rbiBondCentralizedInterestTrailRunDto));
    }

    @PostMapping(path = "/verifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.verifySelect(rbiBondCentralizedInterestTrailRunDtoRequest));
    }

    @PostMapping(path = "/getInterestTrailRunVerify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInterestTrailRunVerify(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getInterestTrailRunVerify(rbiBondCentralizedInterestTrailRunDtoRequest));
    }
    @PostMapping(path = "/getInterestTrailRunVerifyDownload", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInterestTrailRunVerifyDownload(@RequestBody RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCentralizedInterestTrailRunFacade.getInterestTrailRunVerifyDownload(rbiBondCentralizedInterestTrailRunDtoRequest));
    }
}
