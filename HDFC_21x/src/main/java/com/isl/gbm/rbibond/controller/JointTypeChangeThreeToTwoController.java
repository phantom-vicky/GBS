package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.JointTypeChangeThreeToTwoDTO;
import com.isl.gbm.rbibond.facade.JointTypeChangeThreeToTwoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/jointTypeChangeThreeToTwo")
public class JointTypeChangeThreeToTwoController {



    @Autowired
    private JointTypeChangeThreeToTwoFacade jointTypeChangeThreeToTwoFacade;



    @PostMapping(path = "/getBcifApi")
    public ResponseEntity<ResponseDataDTO> getBcifApi(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getBcifApi(jointTypeChangeThreeToTwoDTO));
    }


    @PostMapping(path = "/getMinorDetails")
    public ResponseEntity<ResponseDataDTO> getMinorDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getMinorDetails(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/validatePan")
    public ResponseEntity<ResponseDataDTO> validatePan(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.validatePan(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getWboBranchDetails")
    public ResponseEntity<ResponseDataDTO> getWboBranchDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getWboBranchDetails(jointTypeChangeThreeToTwoDTO));
    }
    @PostMapping(path = "/getAddModuleDetails")
    public ResponseEntity<ResponseDataDTO> getAddModuleDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getAddModuleDetails(jointTypeChangeThreeToTwoDTO));
    }
    @PostMapping(path = "/addVal")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.add(jointTypeChangeThreeToTwoDTO));
    }
    @PostMapping(path = "/getDetails")
    public ResponseEntity<ResponseDataDTO> getDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getDetails(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> addSubmit(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.addSubmit(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.inquiry(jointTypeChangeThreeToTwoDTO));
    }


//    @PostMapping(path = "/modifySelect")
//    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
//        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.modifySelect(jointTypeChangeThreeToTwoDTO));
//    }
//
//    @PostMapping(path = "/modify")
//    public ResponseEntity<ResponseDataDTO> modify(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
//        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.modify(jointTypeChangeThreeToTwoDTO));
//    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.verifySelect(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.verify(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.delete(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/blaNo")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.blaNo(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getblaNoInq(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getsubscriptionIdMod")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdMod(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getsubscriptionIdMod(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getsubscriptionIdInq")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getsubscriptionIdInq(jointTypeChangeThreeToTwoDTO));
    }
    @PostMapping(path = "/blaTorch")
    public ResponseEntity<ResponseDataDTO> blaTorch(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.blaTorch(jointTypeChangeThreeToTwoDTO));
    }


    @PostMapping(path = "/ModifyPanTorch")
    public ResponseEntity<ResponseDataDTO> modifyPanTorch(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.modifyPanTorch(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getModuleDetails")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getRbiBondModuleNameDetails(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerTorch(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getBrokerTorch(jointTypeChangeThreeToTwoDTO));
    }


    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getGuardianDetails(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getNonHdfcTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcTorch(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getNonHdfcTorch(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/getNonHdfcBranchTorch")
    public ResponseEntity<ResponseDataDTO> getNonHdfcBranchTorch(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.getNonHdfcBranchTorch(jointTypeChangeThreeToTwoDTO));
    }
    @PostMapping(path = "/unlockBla")
    public ResponseEntity<ResponseDataDTO> unlockBla(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.unlockBla(jointTypeChangeThreeToTwoDTO));
    }

    @PostMapping(path = "/fetchState")
    public ResponseEntity<ResponseDataDTO> fetchState(@RequestBody JointTypeChangeThreeToTwoDTO jointTypeChangeThreeToTwoDTO) {
        return APIResponseBuilder.buildResponseFromDto(jointTypeChangeThreeToTwoFacade.fetchState(jointTypeChangeThreeToTwoDTO));
    }
}
