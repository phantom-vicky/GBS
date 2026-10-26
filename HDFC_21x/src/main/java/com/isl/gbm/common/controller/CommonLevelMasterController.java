package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.LevelMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.CommonLevelMasterFacade;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/gbmultra/common/level/master")
@RestController
public class CommonLevelMasterController {

    @Autowired
    CommonLevelMasterFacade commonLevelMasterFacade;


    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(commonLevelMasterFacade.add(levelMasterDto));
    }


    @GetMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestParam(name = "schemeCode") final String schemeCode) {
        return APIResponseBuilder.buildResponseFromDto(commonLevelMasterFacade.inquiry(schemeCode));
    }

    @GetMapping(path = "/getVerifyDeleteDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerifyDeleteDetails(@RequestParam(name = "schemeCode") final String schemeCode) {
        return APIResponseBuilder.buildResponseFromDto(commonLevelMasterFacade.getVerifyDeleteDetails(schemeCode));
    }


    @DeleteMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestParam(name = "schemeCode") final String schemeCode) {
        return APIResponseBuilder.buildResponseFromDto(commonLevelMasterFacade.delete(schemeCode));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody LevelMasterDto levelMasterDto) {
        return APIResponseBuilder.buildResponseFromDto(commonLevelMasterFacade.verify(levelMasterDto));
    }

}
