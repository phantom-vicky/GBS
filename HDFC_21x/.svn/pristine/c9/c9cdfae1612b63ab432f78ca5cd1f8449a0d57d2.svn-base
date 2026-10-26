package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;
import com.isl.gbm.rbibond.facade.RbiBondSchemeParameterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/schemeParameter")
public class RbiBondSchemeParameterController {

    @Autowired
    private RbiBondSchemeParameterFacade efreightSchemeParameterFacade;
    

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.add(efreightSchemeParameterDTO));
    }

    @PostMapping(path = "/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.modifySelect(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.unVerifySelect(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.verify(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.delete(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.modify(efreightSchemeParameterDTO));
    }

    @PostMapping(path = "/isRecordExists")
    public ResponseEntity<ResponseDataDTO> isRecordExist(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.isRecordExist(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.inquiry(rbiBondSchemeParameterDTO));
    }

    @PostMapping(path = "/interestRtCdTorch")
    public ResponseEntity<ResponseDataDTO> interestRtCdTorch(@RequestBody RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return APIResponseBuilder.buildResponseFromDto(efreightSchemeParameterFacade.interestRtCdTorch(rbiBondSchemeParameterDTO));
    }


}
