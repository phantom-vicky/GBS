package com.isl.gbm.common.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSharingMaintenanceDTO;
import com.isl.gbm.common.facade.TocSharingMaintenanceFacade;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/common/tocSharing")
public class TocSharingMaintenanceController {

    @Autowired
    private TocSharingMaintenanceFacade tocSharingMaintenanceFacade;

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.add(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.addValidation(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.modifySelect(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.modify(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.verifyDeleteSelect(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.verify(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.delete(tocSharingMaintenanceDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody final TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(tocSharingMaintenanceFacade.inquiry(tocSharingMaintenanceDTO));
    }

}
