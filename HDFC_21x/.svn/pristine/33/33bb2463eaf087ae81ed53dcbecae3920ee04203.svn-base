package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondInwardOutwardDTO;
import com.isl.gbm.rbibond.facade.BondInwardOutwardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gbmultra/rbiBond/inwardOutward")
public class BondInwardOutwardController {
    
    
    @Autowired
    private BondInwardOutwardFacade bondInwardOutwardFacade;



    @PostMapping(path = "/getAddInRefNo")
    public ResponseEntity<ResponseDataDTO> getAddInRefNo(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.getAddInRefNo(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/addOutward")
    public ResponseEntity<ResponseDataDTO> addOutward(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.addOutward(bondInwardOutwardDTO));
    }

    @PostMapping(path = "/rejectOutward")
    public ResponseEntity<ResponseDataDTO> rejectOutward(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.rejectOutward(bondInwardOutwardDTO));
    }

    @PostMapping(path = "/addInward")
    public ResponseEntity<ResponseDataDTO> addInward(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.addInward(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/getPanNoTor")
    public ResponseEntity<ResponseDataDTO> getPanNoTor(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.getPanNoTor(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/getBlaNoTor")
    public ResponseEntity<ResponseDataDTO> getBlaNoTor(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.getBlaNoTor(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/getBlaSubsIdTor")
    public ResponseEntity<ResponseDataDTO> getBlaSubsIdTor(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.getBlaSubsIdTor(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/inrRefNum")
    public ResponseEntity<ResponseDataDTO> inrRefNum(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.inrRefNum(bondInwardOutwardDTO));
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<ResponseDataDTO> upload(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.upload(bondInwardOutwardDTO));
    }

    @PostMapping(path = "/download")
    public ResponseEntity<ResponseDataDTO> download(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.download(bondInwardOutwardDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.delete(bondInwardOutwardDTO));
    }

    @PostMapping(path = "/tranValidation")
    public ResponseEntity<ResponseDataDTO> tranValidation(@RequestBody BondInwardOutwardDTO bondInwardOutwardDTO) {
        return APIResponseBuilder.buildResponseFromDto(bondInwardOutwardFacade.tranValidation(bondInwardOutwardDTO));
    }

}