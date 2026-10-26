package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.BodEodStatusInqFacade;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/gbmultra/common/inquiry/bodEodStatusInquiry")
@RestController
public class BodEodStatusInqController {

    @Autowired
    private BodEodStatusInqFacade bodEodStatusInqFacade;

    @PostMapping(path = "/getStatusInquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getStatusInquiry() {
        return APIResponseBuilder.buildResponseFromDto(bodEodStatusInqFacade.getStatusInquiry());
    }

    @PostMapping(path = "/getModuleName", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModuleName() {
        return APIResponseBuilder.buildResponseFromDto(bodEodStatusInqFacade.getModuleName());
    }

    @PostMapping(path = "/getInquiryDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody BodEodStatusInqDTO bodEodStatusInqDTO) {
        return APIResponseBuilder.buildResponseFromDto(bodEodStatusInqFacade.getInquiryDetails(bodEodStatusInqDTO));
    }

    @PostMapping(path = "/getStateTaxInquiryDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getStateTaxInquiryDetails(@RequestBody BodEodStatusInqDTO bodEodStatusInqDTO) {
        return APIResponseBuilder.buildResponseFromDto(bodEodStatusInqFacade.getStateTaxInquiryDetails(bodEodStatusInqDTO));
    }

}
