package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;
import com.isl.gbm.rbibond.facade.FreezeUnfreezeFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbsultra/rbiBond/freezeUnfreeze") // gbsultra/rbiBond/freezeUnfreeze/getAddDetails
public class FreezeUnFreezeController {


    private final FreezeUnfreezeFacade freezeUnfreezeFacade;

    public FreezeUnFreezeController(FreezeUnfreezeFacade freezeUnfreezeFacade) {
        this.freezeUnfreezeFacade = freezeUnfreezeFacade;
    }

    @PostMapping(path = "/getAddDetails")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody FreezUnfreezDTO freezUnfreezDTO) {
        return APIResponseBuilder.buildResponseFromDto(freezeUnfreezeFacade.getAddDetails(freezUnfreezDTO));
    }

    @PostMapping(path = "/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody FreezUnfreezDTO freezUnfreezDTO) {
        return APIResponseBuilder.buildResponseFromDto(freezeUnfreezeFacade.modifySelect(freezUnfreezDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody FreezUnfreezDTO freezUnfreezDTO) {
        return APIResponseBuilder.buildResponseFromDto(freezeUnfreezeFacade.inquiry(freezUnfreezDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody FreezUnfreezDTO freezUnfreezDTO) {
        return APIResponseBuilder.buildResponseFromDto(freezeUnfreezeFacade.verify(freezUnfreezDTO));
    }
}
