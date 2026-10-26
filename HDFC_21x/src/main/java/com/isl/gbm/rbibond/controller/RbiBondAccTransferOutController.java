package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondAccTransferOutDTO;
import com.isl.gbm.rbibond.facade.RbiBondAccTransferOutFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/accTranFrOut")
public class RbiBondAccTransferOutController {

    @Autowired
    private RbiBondAccTransferOutFacade rbiBondAccTransferOutFacade;



    @PostMapping(path = "/getOtherBankDetails")
    public ResponseEntity<ResponseDataDTO> getOtherBankDetails(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.getOtherBankDetails(rbiBondAccTransferOutDTO));
    }
    

    @PostMapping(path = "/getNomineeDetails")
    public ResponseEntity<ResponseDataDTO> getNomineeDetails(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.getNomineeDetails(rbiBondAccTransferOutDTO));
    }

    

    

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.add(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.addValidation(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.inquiry(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.verifySelect(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.verify(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.delete(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.modifySelect(rbiBondAccTransferOutDTO));
    }


    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.modify(rbiBondAccTransferOutDTO));
    }

    @PostMapping(path = "/tranValidation")
    public ResponseEntity<ResponseDataDTO> tranValidation(@RequestBody RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccTransferOutFacade.tranValidation(rbiBondAccTransferOutDTO));
    }


}
