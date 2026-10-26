package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BrokerMasterMainDTO;
import com.isl.gbm.rbibond.facade.BrokerMasterMaintainanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/brokerMaster")
public class BrokerMasterMaintenanceController {

    @Autowired
    private BrokerMasterMaintainanceFacade brokerMasterMaintainanceFacade;


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.add(brokerMasterMainDTO));
    }


    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.addValidation(brokerMasterMainDTO));
    }

    @PostMapping(path = "/inquiryDetails")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.inquiryDetails(brokerMasterMainDTO));
    }


    @PostMapping(path = "/verifyDeleteDetails")
    public ResponseEntity<ResponseDataDTO> verifyDeleteDetails(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.verifyDeleteDetails(brokerMasterMainDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.verify(brokerMasterMainDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.delete(brokerMasterMainDTO));
    }


    @PostMapping(path = "/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.modifyDetails(brokerMasterMainDTO));
    }

    @PostMapping(path = "/modifySubmit")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.modifySubmit(brokerMasterMainDTO));
    }

    @PostMapping(path = "/getBranchCode")
    public ResponseEntity<ResponseDataDTO> getBranchCode(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.getBranchCode(brokerMasterMainDTO));
    }

    @PostMapping(path = "/getNonHdfcTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcTorch(@RequestBody BrokerMasterMainDTO brokerMasterMainDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerMasterMaintainanceFacade.getNonHdfcTorch(brokerMasterMainDTO));
    }




}
