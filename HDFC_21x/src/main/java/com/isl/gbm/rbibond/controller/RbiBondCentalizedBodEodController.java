package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;
import com.isl.gbm.rbibond.facade.RbiBondCentalizedBodEodFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/rbiBond/centailized")
public class RbiBondCentalizedBodEodController {

    @Autowired
    RbiBondCentalizedBodEodFacade centalizedBODFacade;
    
    @PostMapping(path="/bod/startBodProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.add(centalizedEodBodDTO));
    }
    @PostMapping(path="/bod/getDate", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDate(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.getDate(centalizedEodBodDTO));
    }
    @PostMapping(path="/bod/getLevelBranch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getLevelBranch(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.getLevelBranch(centalizedEodBodDTO));
    }

    @PostMapping(path="/Eod/startEodProcess", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> startEodProcess(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.startEodProcess(centalizedEodBodDTO));
    }
    @PostMapping(path="/Eod/getDate", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getEodDate(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.getEodDate(centalizedEodBodDTO));
    }
    @PostMapping(path="/Eod/getLevelBranch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getEodLevelBranch(@RequestBody RbiBondEodBodDTO centalizedEodBodDTO){
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.getEodLevelBranch(centalizedEodBodDTO));
    }
    @PostMapping(path="/Eod/getTimeValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTimeValidation() {
        return APIResponseBuilder.buildResponseFromDto(centalizedBODFacade.getTimeValidation());
    }


}
