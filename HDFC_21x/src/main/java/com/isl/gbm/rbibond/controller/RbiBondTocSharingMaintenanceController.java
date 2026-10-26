package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondTocSharingMaintenanceDTO;
import com.isl.gbm.rbibond.facade.RbiBondTocSharingMaintenanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/tocSharing")
public class RbiBondTocSharingMaintenanceController {
    @Autowired
    private RbiBondTocSharingMaintenanceFacade rbiBondTocSharingMaintenanceFacade;

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.add(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.addValidation(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.modifySelect(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.modify(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.verifyDeleteSelect(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.verify(rbiBondTocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final RbiBondTocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.delete(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody final RbiBondTocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondTocSharingMaintenanceFacade.inquiry(tocSharingMaintenanceDTO));
    }

}
