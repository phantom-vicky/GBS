/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentStatusDto;
import com.isl.gbm.cbs.integration.dto.InstrumentTypesIntegrationDto;
import com.isl.gbm.cbs.integration.service.InstrumentTypesIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Msocadmin
 *
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class InstrumentTypesIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(InstrumentTypesIntegrationController.class);
    @Autowired
    private InstrumentTypesIntegrationService instrumentTypesIntegrationService;


    @PostMapping(value = "/instrumenttypes")
    public ResponseEntity<List<InstrumentTypesIntegrationDto>> getInstrumentTypes(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<InstrumentTypesIntegrationDto> dtoList = this.instrumentTypesIntegrationService.getSchmCode(cbsCommonDTo.getSolId(),
                    cbsCommonDTo.getForacid());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }

    @PostMapping(value = "/instrumentcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstrumentStatusDto> getInstrumentCheck(
            @RequestBody CbsCommonDTo cbsCommonDTo) {
            log.info("The request For instrumentcheck " + cbsCommonDTo.getForacid(),cbsCommonDTo.getInstrumentNum());
            final InstrumentStatusDto dtoList = this.instrumentTypesIntegrationService.getInstrumentCheck(cbsCommonDTo.getInstrumentNum(),
                    cbsCommonDTo.getForacid());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);

    }
}
