package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;
import com.isl.gbm.rbibond.facade.RbiBondAckReceiptFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/gbmultra/rbibond/ackReceipt")
public class RbiBondAckReceiptController {
    @Autowired
    RbiBondAckReceiptFacade rbiBondAckReceiptFacade;


    @PostMapping(path = "/add",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAckReceiptFacade.add(rbiBondAckReceiptDTO));
    }


    @PostMapping(path = "/addTorch",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> addTorch(@RequestBody RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAckReceiptFacade.addTorch(rbiBondAckReceiptDTO));
    }




//    @PostMapping(path = "/addValidation",produces ="application/json")
//    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
//        return APIResponseBuilder.buildResponseFromDto(rbiBondAckReceiptFacade.addValidation(rbiBondAckReceiptDTO));
//    }
    @PostMapping(path ="/inquiry",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAckReceiptFacade.inquiry(rbiBondAckReceiptDTO));
    }

}
