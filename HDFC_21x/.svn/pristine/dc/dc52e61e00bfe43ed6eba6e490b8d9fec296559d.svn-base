package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondBrokeragePaymentDTO;
import com.isl.gbm.rbibond.facade.BondBrokeragePaymentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/brokeragePayment")
public class BondBrokeragePaymentController {
    
    
    @Autowired
    private BondBrokeragePaymentFacade bondBrokeragePaymentFacade;



    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerDetails(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.getBrokerDetails(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.add(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.addValidation(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.verifySelect(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.verify(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.inquiry(bondBrokeragePaymentDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.delete(bondBrokeragePaymentDTO));
    }

    @PostMapping(path = "/tranValidation")
    public ResponseEntity<ResponseDataDTO> tranValidation(@RequestBody BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondBrokeragePaymentFacade.tranValidation(bondBrokeragePaymentDTO));
    }



}
