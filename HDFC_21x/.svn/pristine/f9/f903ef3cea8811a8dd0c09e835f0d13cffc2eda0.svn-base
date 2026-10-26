package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;
import com.isl.gbm.rbibond.facade.CacAckNumMaintenanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("gbmultra/rbiBond/cacAckNum")
public class CacAckNumMaintenanceController {
    @Autowired
    private CacAckNumMaintenanceFacade cacAckNumMaintenanceFacade;

   @PostMapping(path="/add")
    public ResponseEntity<ResponseDataDTO>add(@Validated @RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
       return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.add(cacAckNumMaintenanceDto));
   }

    @PostMapping(path="/addDet")
    public ResponseEntity<ResponseDataDTO>addDet(@RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.addDet(cacAckNumMaintenanceDto));
    }


    @PostMapping(path="/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO>verifyDeleteSelect(@RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.verifyDeleteSelect(cacAckNumMaintenanceDto));
    }

    @PostMapping(path="/inquiry")
    public ResponseEntity<ResponseDataDTO>inquiry(@RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.inquiry(cacAckNumMaintenanceDto));
    }

    @PostMapping(path="/verify")
    public ResponseEntity<ResponseDataDTO>verify(@Validated @RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.verify(cacAckNumMaintenanceDto));
    }

    @PostMapping(path="/delete")
    public ResponseEntity<ResponseDataDTO>delete(@Validated @RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.delete(cacAckNumMaintenanceDto));
    }

    @PostMapping(path="/modifySelect")
    public ResponseEntity<ResponseDataDTO>modifySelect(@RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.modifySelect(cacAckNumMaintenanceDto));
    }

    @PostMapping(path="/modify")
    public ResponseEntity<ResponseDataDTO>modify(@RequestBody CacAckNumMaintenanceDto cacAckNumMaintenanceDto){
        return APIResponseBuilder.buildResponseFromDto(cacAckNumMaintenanceFacade.modify(cacAckNumMaintenanceDto));
    }
}
