package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;
import com.isl.gbm.rbibond.facade.RbiBondNomineeContinuationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
@RestController
@RequestMapping("gbmultra/rbiBond/nomineeContinuation")
public class RbiBondNomineeContinuationController {

    @Autowired
    private RbiBondNomineeContinuationFacade rbiBondNomineeContinuationFacade;


    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getAddDetails(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/getWboBranchDetails")
    public ResponseEntity<ResponseDataDTO> getWboBranchDetails(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getWboBranchDetails(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/getAddValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddValidation(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getAddValidation(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.add(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/getInqDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInqDetails(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getInqDetails(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/getVerDelDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getVerDelDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getPanValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPanValidation(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.getPanValidation(rbiBondClosureBeforeMaturDTO));
    }


    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.verify(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondNomineeContinuationFacade.delete(rbiBondClosureBeforeMaturDTO));
    }


}