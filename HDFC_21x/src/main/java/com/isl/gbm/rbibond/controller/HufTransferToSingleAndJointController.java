package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.dto.ModeOfHoldingChangeDTO;
import com.isl.gbm.rbibond.facade.HufTransferToSingleAndJointFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/hufTransfer")
public class HufTransferToSingleAndJointController {
    private static final Logger log = LoggerFactory.getLogger(HufTransferToSingleAndJointController.class);

    @Autowired
    private HufTransferToSingleAndJointFacade hufTransferToSingleAndJointFacade;



    @PostMapping(path = "/getBcifApi")
    public ResponseEntity<ResponseDataDTO> getBcifApi(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getBcifApi(hufTransferSingleAndJointDTO));
    }


    @PostMapping(path = "/getMinorDetails")
    public ResponseEntity<ResponseDataDTO> getMinorDetails(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getMinorDetails(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getWboBranchDetails")
    public ResponseEntity<ResponseDataDTO> getWboBranchDetails(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getWboBranchDetails(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/getModuleNameAdd")
    public ResponseEntity<ResponseDataDTO> getModuleNameAdd(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getModuleNameAdd(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/addVal")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.add(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/validatePan")
    public ResponseEntity<ResponseDataDTO> validatePan(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.validatePan(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> addSubmit(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.addSubmit(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getbranchName")
    public ResponseEntity<ResponseDataDTO> getbranchName(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getbranchName(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/getbankName")
    public ResponseEntity<ResponseDataDTO> getbankName(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getbankName(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.verifySelect(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.verify(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.delete(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/blaNo")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.blaNo(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getblaNoInq(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getsubscriptionIdInq")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getsubscriptionIdInq(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/blaTorch")
    public ResponseEntity<ResponseDataDTO> blaTorch(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.blaTorch(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.inquiry(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerTorch(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getBrokerTorch(hufTransferSingleAndJointDTO));
    }


    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getGuardianDetails(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getNonHdfcTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcTorch(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getNonHdfcTorch(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/getNonHdfcBranchTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcBranchTorch(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getNonHdfcBranchTorch(hufTransferSingleAndJointDTO));
    }

    @PostMapping(path = "/fetchState")
    public ResponseEntity<ResponseDataDTO> fetchState(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.fetchState(hufTransferSingleAndJointDTO));
    }
    @PostMapping(path = "/getDetails")
    public ResponseEntity<ResponseDataDTO> getDetails(@RequestBody HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return APIResponseBuilder.buildResponseFromDto(hufTransferToSingleAndJointFacade.getDetails(hufTransferSingleAndJointDTO));
    }
}
