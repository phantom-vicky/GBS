package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.facade.RbiBondLevelMasterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/gbmultra/rbiBond/levelMaster")
@RestController
public class RbiBondLevelMasterController {

    @Autowired
    RbiBondLevelMasterFacade levelMasterFacade;


    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.add(levelMasterDto));
    }

    @PostMapping(path = "/addValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.addValidation(levelMasterDto));
    }


    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.inquiry(levelMasterDto));
    }

    @PostMapping(path = "/getVerifyDeleteDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.getVerifyDeleteDetails(levelMasterDto));
    }


    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.delete(levelMasterDto));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(levelMasterFacade.verify(levelMasterDto));
    }

}
