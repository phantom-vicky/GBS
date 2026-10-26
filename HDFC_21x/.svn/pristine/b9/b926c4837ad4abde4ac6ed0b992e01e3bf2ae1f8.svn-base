package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;
import com.isl.gbm.rbibond.dto.RbiBondNominationDto;
import com.isl.gbm.rbibond.facade.RbiBondClosureBeforeMaturFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/rbiBond/accClosureBefMaturity")
@RestController
public class RbiBondClosureBeforeMaturController {

  @Autowired
  RbiBondClosureBeforeMaturFacade rbiBondClosureBeforeMaturFacade;


    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.getAddDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getVerDelDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.getVerDelDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getInqDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInqDetails(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.getInqDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.add(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.verify(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureBeforeMaturFacade.delete(rbiBondClosureBeforeMaturDTO));
    }


}


