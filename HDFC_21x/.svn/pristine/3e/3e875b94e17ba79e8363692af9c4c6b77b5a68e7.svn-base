package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;
import com.isl.gbm.rbibond.dto.marker.BondSubscripReversalAccNoDTOAdd;
import com.isl.gbm.rbibond.dto.marker.BondSubscripReversalAccNoDTOVerifyDelete;
import com.isl.gbm.rbibond.facade.RbiBondSubscripReversalAccNoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/rbibond/subReveral")
public class RbiBondSubscripReversalAccNoController {

    @Autowired
    RbiBondSubscripReversalAccNoFacade rbiBondSubscripReversalAccNoFacade;

    @PostMapping(path = "/add",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> add(@Validated(BondSubscripReversalAccNoDTOAdd.class) @RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.add(bondSubscripReversalAccNoDTO));
    }

    @PostMapping(path = "/addValidation",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.addValidation(bondSubscripReversalAccNoDTO));
    }

    @PostMapping(path = "/verify",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> verify(@Validated(BondSubscripReversalAccNoDTOVerifyDelete.class) @RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.verify(bondSubscripReversalAccNoDTO));
    }

    @PostMapping(path = "/verifySelect",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.verifySelect(bondSubscripReversalAccNoDTO));
    }

    @PostMapping(path = "/delete",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> delete(@Validated(BondSubscripReversalAccNoDTOVerifyDelete.class)@RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.delete(bondSubscripReversalAccNoDTO));
    }

    @PostMapping(path = "/inquiry",produces ="application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSubscripReversalAccNoFacade.inquiry(bondSubscripReversalAccNoDTO));
    }


}
