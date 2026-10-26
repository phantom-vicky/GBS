package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.InterestRateCodeRequestDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.InterestRateCodeFacade;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/gbmultra/common/master/interest/rate/code")
@RestController
public class InterestRateCodeController {

    @Autowired
    InterestRateCodeFacade interestRateCodeFacade;

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody final InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.add(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/getModify")
    public ResponseEntity<ResponseDataDTO> getModify(@RequestBody final InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.getModify(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/modify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.modify(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/getVerifyDeleteDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.getVerifyDeleteDetails(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.verify(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.delete(interestRateCodeRequestDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody InterestRateCodeRequestDTO interestRateCodeRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(interestRateCodeFacade.inquiry(interestRateCodeRequestDTO));
    }
}
