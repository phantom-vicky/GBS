package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.CumToNonCumChangeOfBondTypeDTO;
import com.isl.gbm.rbibond.facade.CumToNonCumChangeOfBondTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/cumToNonCumChange")
public class CumToNonCumChangeOfBondTypeController {

    @Autowired
    private CumToNonCumChangeOfBondTypeFacade cumToNonCumChangeOfBondTypeFacade;



  
    @PostMapping(path = "/getAddDashBoard")
    public ResponseEntity<ResponseDataDTO> getAddDashBoard(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getAddDashBoard(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/addVal")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.add(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> addSubmit(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.addSubmit(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.inquiry(cumToNonCumChangeOfBondTypeDTO));
    }


    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.modifySelect(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/addcustomerMaster")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.modify(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.verifySelect(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.verify(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/submitVerifyForm")
    public ResponseEntity<ResponseDataDTO> submitVerifyForm(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.submitVerifyForm(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getPantorch")
    public ResponseEntity<ResponseDataDTO> getPantorch(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getPantorch(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getPantorchInq")
    public ResponseEntity<ResponseDataDTO> getPantorchInq(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getPantorchInq(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/blaNo")
    public ResponseEntity<ResponseDataDTO> blaNo(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.blaNo(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/getblaNoInq")
    public ResponseEntity<ResponseDataDTO> getblaNoInq(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getblaNoInq(cumToNonCumChangeOfBondTypeDTO));
    }

@PostMapping(path = "/getsubscriptionIdInq")
public ResponseEntity<ResponseDataDTO> getsubscriptionIdInq(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
    return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getsubscriptionIdInq(cumToNonCumChangeOfBondTypeDTO));
}
    @PostMapping(path = "/getsubscriptionId")
    public ResponseEntity<ResponseDataDTO> getsubscriptionId(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getsubscriptionId(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/blaTorch")
    public ResponseEntity<ResponseDataDTO> blaTorch(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.blaTorch(cumToNonCumChangeOfBondTypeDTO));
    }


    @PostMapping(path = "/ModifyPanTorch")
    public ResponseEntity<ResponseDataDTO> modifyPanTorch(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.modifyPanTorch(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/getModuleDetails")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetails(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getRbiBondModuleNameDetails(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getModuleDetailsInq")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetailsInq(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getRbiBondModuleNameDetailsInq(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getBrokerDetails")
    public ResponseEntity<ResponseDataDTO> getBrokerTorch(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getBrokerTorch(cumToNonCumChangeOfBondTypeDTO));
    }


    @PostMapping(path = "/getGuardianDetails")
    public ResponseEntity<ResponseDataDTO> getGuardianDetails(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getGuardianDetails(cumToNonCumChangeOfBondTypeDTO));
    }
    //verify details
    @PostMapping(path = "/getModuleDetailsVer")
    public ResponseEntity<ResponseDataDTO> getRbiBondModuleNameDetailsVer(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getRbiBondModuleNameDetailsVer(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getsubscriptionIdVer")
    public ResponseEntity<ResponseDataDTO> getsubscriptionIdVer(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getsubscriptionIdVer(cumToNonCumChangeOfBondTypeDTO));
    }

    @PostMapping(path = "/getblaNoVer")
    public ResponseEntity<ResponseDataDTO> getblaNoVer(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getblaNoVer(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/getPantorchVer")
    public ResponseEntity<ResponseDataDTO> getPantorchVer(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.getPantorchVer(cumToNonCumChangeOfBondTypeDTO));
    }
    @PostMapping(path = "/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return APIResponseBuilder.buildResponseFromDto(cumToNonCumChangeOfBondTypeFacade.deleteSubmit(cumToNonCumChangeOfBondTypeDTO));
    }
}