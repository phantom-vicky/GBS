package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import com.isl.gbm.rbibond.facade.RbiBondCommonServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class RbiBondCommonServiceController {

    @Autowired
    private RbiBondCommonServiceFacade efreightCommonServiceFacade;

    @PostMapping(path = "/rbiBond/getModuleDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModuleDetails(@Valid @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getModuleDetails(efreightMetaDataRequestDto));
    }

    @PostMapping(path = "/rbiBond/getModule", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModule(@Valid @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getModule(efreightMetaDataRequestDto));
    }
    @PostMapping(path = "/rbiBond/getFinancialYear", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getFinancialYear( @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getFinancialYear(efreightMetaDataRequestDto));
    }

    @PostMapping(path = "/rbiBond/getReferenceData", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getReferenceData(@Valid @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getMetaData(efreightMetaDataRequestDto.getReferenceId()));
    }
    @PostMapping(path = "/rbiBond/getDocumentDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDocumentDetails() {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getDocumentDetails());
    }

    @PostMapping(path = "/rbiBond/getRemarksList", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getRemarksList(@Valid @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getRemarksList(efreightMetaDataRequestDto.getReferenceId()));
    }

    @PostMapping(path = "/rbiBond/getMetaData", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> apyMetaData(@Valid @RequestBody RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getMetaDatList(efreightMetaDataRequestDto.getReferenceId()));
    }

    @PostMapping(path = "/rbiBond/genericTableOperationsSp", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> genericTableOperationsBySp(@Valid @RequestBody RbiBondCommonGenericTableDto efreightCommonGenericTableDto) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.genericTableOperationsBySp(efreightCommonGenericTableDto));
    }

    @PostMapping(path = "/rbiBond/getGbmSolDate")
    public ResponseEntity<ResponseDataDTO> getGbmSolDate() {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getGbmSolDate());
    }

    @PostMapping(path = "/rbiBond/getCommonMetaData")
    public ResponseEntity<ResponseDataDTO> getCommonMetaData(@RequestBody CommonDataDTO commonDataDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightCommonServiceFacade.getCommonMetaData(commonDataDTO));
    }
}
