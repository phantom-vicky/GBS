package com.isl.gbm.rbibond.controller;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.SchemeMasterRequestResponseDTO;
import com.isl.gbm.rbibond.facade.RbiBondSchemeMasterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/schemeMaster")
public class RbiBondSchemeMasterController {
    @Autowired
    RbiBondSchemeMasterFacade rbiBondSchemeMasterFacade;

    @PostMapping(path = "/modifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.modifySelect(schemeMasterRequestResponseDTO));
    }
    @PostMapping(path = "/verifySelect", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.verifySelect(schemeMasterRequestResponseDTO));
    }
    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.add(schemeMasterRequestResponseDTO));
    }

    @PostMapping(path = "/inquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.inquiry(schemeMasterRequestResponseDTO));
    }
    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.verify(schemeMasterRequestResponseDTO));
    }

    @PostMapping(path = "/modify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.modify(schemeMasterRequestResponseDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondSchemeMasterFacade.delete(schemeMasterRequestResponseDTO));
    }
}
