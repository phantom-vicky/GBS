package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.ModeOfHoldingChangeDTO;
import com.isl.gbm.rbibond.facade.ModeOfHoldingChangeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/modeOfHolding")
public class ModeOfHoldingChangeController {

    @Autowired
    private ModeOfHoldingChangeFacade modeOfHoldingChangeFacade;



    @PostMapping(path = "/getBcifApi")
    public ResponseEntity<ResponseDataDTO> getBcifApi(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getBcifApi(modeOfHoldingChangeDTO));
    }


    @PostMapping(path = "/getMinorDetails")
    public ResponseEntity<ResponseDataDTO> getMinorDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getMinorDetails(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/validatePan")
    public ResponseEntity<ResponseDataDTO> validatePan(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.validatePan(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getWboBranchDetails")
    public ResponseEntity<ResponseDataDTO> getWboBranchDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getWboBranchDetails(modeOfHoldingChangeDTO));
    }
    @PostMapping(path = "/getAddModuleDetails")
    public ResponseEntity<ResponseDataDTO> getAddModuleDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getAddModuleDetails(modeOfHoldingChangeDTO));
    }
    @PostMapping(path = "/addVal")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.add(modeOfHoldingChangeDTO));
    }
    @PostMapping(path = "/getDetails")
    public ResponseEntity<ResponseDataDTO> getDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getDetails(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> addSubmit(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.addSubmit(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.inquiry(modeOfHoldingChangeDTO));
    }


    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.modifySelect(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.modify(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.verifySelect(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.verify(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.delete(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/blaNo")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.blaNo(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getblaNoInq(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getsubscriptionIdMod")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdMod(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getsubscriptionIdMod(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getsubscriptionIdInq")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getsubscriptionIdInq(modeOfHoldingChangeDTO));
    }
    @PostMapping(path = "/blaTorch")
    public ResponseEntity<ResponseDataDTO> blaTorch(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.blaTorch(modeOfHoldingChangeDTO));
    }


    @PostMapping(path = "/ModifyPanTorch")
    public ResponseEntity<ResponseDataDTO> modifyPanTorch(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.modifyPanTorch(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getModuleDetails")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getRbiBondModuleNameDetails(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerTorch(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getBrokerTorch(modeOfHoldingChangeDTO));
    }


    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getGuardianDetails(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getNonHdfcTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcTorch(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getNonHdfcTorch(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/getNonHdfcBranchTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcBranchTorch(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.getNonHdfcBranchTorch(modeOfHoldingChangeDTO));
    }
    @PostMapping(path = "/unlockBla")
    public ResponseEntity<ResponseDataDTO> unlockBla(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.unlockBla(modeOfHoldingChangeDTO));
    }

    @PostMapping(path = "/fetchState")
    public ResponseEntity<ResponseDataDTO> fetchState(@RequestBody ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return APIResponseBuilder.buildResponseFromDto(modeOfHoldingChangeFacade.fetchState(modeOfHoldingChangeDTO));
    }
}