package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.facade.PanValidationRetriggerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gbmultra/rbiBond/panValidation/retrigger")
public class PanValidationRetriggerController {
    @Autowired
    PanValidationRetriggerFacade panValidationRetriggerFacade;

    @PostMapping("/details")
    public ResponseEntity<ResponseDataDTO> details(@RequestBody PanValidationRetriggerDTO panValidationRetriggerDTO){
        return APIResponseBuilder.buildResponseFromDto(panValidationRetriggerFacade.details(panValidationRetriggerDTO));
    }
    @PostMapping("/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO){
        return APIResponseBuilder.buildResponseFromDto(panValidationRetriggerFacade.verify(panValidationRetriggerResponseDTO));
    }

    @PostMapping( "/validate")
    public ResponseEntity<ResponseDataDTO> validate(@RequestBody PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO){
        return APIResponseBuilder.buildResponseFromDto(panValidationRetriggerFacade.validate(panValidationRetriggerResponseDTO));
    }
}
