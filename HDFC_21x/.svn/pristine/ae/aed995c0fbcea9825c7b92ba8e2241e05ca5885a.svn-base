package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;
import com.isl.gbm.rbibond.facade.RbiBondCodeMappingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/codeMapping")
public class RbiBondCodeMappingController {

    @Autowired
    private RbiBondCodeMappingFacade rbiBondCodeMappingFacade;


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.add(rbiBondTdsParameterDTO));
    }


    @GetMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation() {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.addValidation());
    }

    @PostMapping(path = "/inquiryDetails")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.inquiryDetails(rbiBondTdsParameterDTO));
    }


    @PostMapping(path = "/verifyDeleteDetails")
    public ResponseEntity<ResponseDataDTO> verifyDeleteDetails(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.verifyDeleteDetails(rbiBondTdsParameterDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.verify(rbiBondTdsParameterDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.delete(rbiBondTdsParameterDTO));
    }

    @PostMapping(path = "/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.modifyDetails(rbiBondTdsParameterDTO));
    }

    @PostMapping(path = "/modifySubmit")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.modifySubmit(rbiBondTdsParameterDTO));
    }

    @PostMapping(path = "/getBranchCode")
    public ResponseEntity<ResponseDataDTO> getBranchCode(@RequestBody RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondCodeMappingFacade.getBranchCode(rbiBondTdsParameterDTO));
    }





}
