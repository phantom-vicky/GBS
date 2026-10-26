package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondAccountingEntriesDTO;
import com.isl.gbm.rbibond.facade.RbiBondAccountingEntriesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbsultra/rbiBond/accountEntries")
public class RbiBondAccountingEntriesController {

    @Autowired
    RbiBondAccountingEntriesFacade rbiBondAccountingEntriesFacade;

    @RequestMapping(value = "/getModuleName")
    public ResponseEntity<ResponseDataDTO> getModuleName(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getModuleName(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/getVersionNo")
    public ResponseEntity<ResponseDataDTO> getVersionNo(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getVersionNo(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/getVerifyDetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getVerifyDetails(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/getModifyModuleName")
    public ResponseEntity<ResponseDataDTO> getModifyModuleName(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getModifyModuleName(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/getVerifyModuleName")
    public ResponseEntity<ResponseDataDTO> getVerifyModuleName(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getVerifyModuleName(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/add")
    public ResponseEntity<ResponseDataDTO> addAccountEntriesValues(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.addAccountEntriesValues(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.inquiryDetails(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/modify")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.modifySubmit(rbiBondAccountingEntriesDTO));
    }

    @RequestMapping(value = "/getModifyDetails")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.getModifyDetails(rbiBondAccountingEntriesDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.verifySubmit(rbiBondAccountingEntriesDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody final RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondAccountingEntriesFacade.deleteSubmit(rbiBondAccountingEntriesDTO));
    }
}