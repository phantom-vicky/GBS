package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.facade.DropDownMasterRBIBondFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/drop-down-master")
public class DropDownMasterRBIBondController {

@Autowired
   DropDownMasterRBIBondFacade downMasterRBIBondFacade;


    @PostMapping("/add-details")

    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.addDetails(dropDownMasterDTO));

    }



    @PostMapping("/modify-details")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.modifyDetails(dropDownMasterDTO));

    }

    @PostMapping("/modify-submit")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.modifySubmit(dropDownMasterDTO));
    }

    @PostMapping("/verify-details")

    public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.verifyDetails(dropDownMasterDTO));
    }

    @PostMapping("/verify-submit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.verifySubmit(dropDownMasterDTO));
    }

    @PostMapping("/inquiry")

    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.inquiry(dropDownMasterDTO));
    }

    @PostMapping("/reject")
    public ResponseEntity<ResponseDataDTO> reject(@RequestBody DropDownMasterDTO dropDownMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(downMasterRBIBondFacade.reject(dropDownMasterDTO));
    }

}
