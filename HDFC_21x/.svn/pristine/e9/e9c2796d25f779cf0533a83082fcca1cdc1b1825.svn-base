package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondLevelMasterMappingDTO;
import com.isl.gbm.rbibond.facade.RbiBondLevelMasterMappingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gbmultra/dc/rbiBond/levelMaster/mapping")
public class RbiBondLevelMasterMappingController {
    

    @Autowired
    private RbiBondLevelMasterMappingFacade efreightLevelMasterFacade;

    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.getAddDetails(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.add(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.inquiry(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/getModifyDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.getModifyDetails(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/getVerifyDeleteDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.getVerifyDeleteDetails(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.verify(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/modify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.modify(efreightLevelMasterMappingDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondLevelMasterMappingDTO efreightLevelMasterMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightLevelMasterFacade.delete(efreightLevelMasterMappingDTO));
    }
}
