package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;
import com.isl.gbm.rbibond.facade.AgeBseLkIPdDetlPmteEncasmetFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbiBond/ageBasedEncasement")
public class AgeBaseLkIPdDealPateEncaseController {

    private static final Logger log = LoggerFactory.getLogger(AgeBaseLkIPdDealPateEncaseController.class);

    @Autowired
    private AgeBseLkIPdDetlPmteEncasmetFacade ageBseLkIPdDetlPmteEncasmetFacade;


    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.add(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.addValidation(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.verifySelect(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.verify(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.modifySelect(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.modify(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.inquiry(ageBseLkIPdDetlPmteEncasmetDTO));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        log.info("{}: {}", "Request: ", ageBseLkIPdDetlPmteEncasmetDTO);
        return APIResponseBuilder.buildResponseFromDto(ageBseLkIPdDetlPmteEncasmetFacade.delete(ageBseLkIPdDetlPmteEncasmetDTO));
    }







}
