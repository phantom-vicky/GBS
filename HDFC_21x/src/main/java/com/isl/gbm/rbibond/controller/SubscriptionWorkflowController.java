package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.SubcriptionWorkFlowDTO;
import com.isl.gbm.rbibond.facade.SubscriptionWorkflowFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/subsWrkFlow")
public class SubscriptionWorkflowController {


    @Autowired
    SubscriptionWorkflowFacade subscriptionWorkflowFacade;


    @PostMapping(path = "/addValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.addValidation(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.getAddDetails(subcriptionWorkFlowDTO));
    }


    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.add(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.inquiry(subcriptionWorkFlowDTO));
    }


    @PostMapping(path = "/getTranValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getTranValidation(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return new ResponseEntity(this.subscriptionWorkflowFacade.getTranValidation(subcriptionWorkFlowDTO), HttpStatus.OK);
    }

    @PostMapping(path = "/verifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.verifySelect(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.verify(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.delete(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.modifySelect(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/modify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.modify(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/getApplicationDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getApplicationDetails(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.getApplicationDetails(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/getBrokerDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getBrokerDetails(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.getBrokerDetails(subcriptionWorkFlowDTO));
    }

    @PostMapping(path = "/getOtherBankDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getOtherBankDetails(@RequestBody SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionWorkflowFacade.getOtherBankDetails(subcriptionWorkFlowDTO));
    }



}
