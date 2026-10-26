package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;
import com.isl.gbm.rbibond.facade.RbiBondClosureBeforeMaturDeathFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/rbiBond/accClosureBefMaturityDeath")
@RestController
public class RbiBondClosureBeforeMaturDeathController {

  @Autowired
  RbiBondClosureBeforeMaturDeathFacade rbiBondClosureBeforeMaturDeathFacade;


    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.getAddDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getVerDelDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.getVerDelDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getInqDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInqDetails(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.getInqDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.add(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.verify(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturDeathFacade.delete(rbiBondClosureBeforeMaturDTO));
    }


}


