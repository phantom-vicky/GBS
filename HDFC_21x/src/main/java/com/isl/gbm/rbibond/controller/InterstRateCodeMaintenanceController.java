package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
import com.isl.gbm.rbibond.facade.InterstRateCodeMaintenanceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbsultra/rbiBond/intRtCdMain")
public class InterstRateCodeMaintenanceController {


    @Autowired
    private InterstRateCodeMaintenanceFacade interstRateCodeMaintenanceFacade;



    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.add(rateCodeMaintenanceDTO));
    }

    @PostMapping(path = "/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.modifySelect(rateCodeMaintenanceDTO));
    }

    @PostMapping(path = "/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.unVerifySelect(rateCodeMaintenanceDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.verify(rateCodeMaintenanceDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.delete(rateCodeMaintenanceDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.modify(rateCodeMaintenanceDTO));
    }


    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return APIResponseBuilder.buildResponseFromDto(interstRateCodeMaintenanceFacade.inquiry(rateCodeMaintenanceDTO));
    }




}
