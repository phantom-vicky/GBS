package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.PreMaturityRequestDTO;
import com.isl.gbm.rbibond.facade.PreMaturityClosureMarkingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/prematurity/marking")
public class PreMaturityClosureMarkingController {

    @Autowired
    PreMaturityClosureMarkingFacade preMaturityClosureMarkingFacade;

    @RequestMapping(value = "/addDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.addDetails(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/addTorch", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> addTorch(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.addTorch(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.add(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/verifyDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.verifyDetails(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/verifyTorch", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyTorch(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.verifyTorch(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.verify(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.delete(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/modifyDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.modifyDetails(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/modifyTorch", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifyTorch(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.modifyTorch(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.modify(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/inquiryDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.inquiryDetails(preMaturityRequestDTO));
    }
    @RequestMapping(value = "/inquiryTorch", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiryTorch(@RequestBody PreMaturityRequestDTO preMaturityRequestDTO){
        return APIResponseBuilder.buildResponseFromDto(preMaturityClosureMarkingFacade.inquiryTorch(preMaturityRequestDTO));
    }
}
