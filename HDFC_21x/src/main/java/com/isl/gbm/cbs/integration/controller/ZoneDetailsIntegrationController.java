/*
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.GbmOciViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.service.ZoneDetailsIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * The Class ZoneDetailsIntegrationController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class ZoneDetailsIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(ZoneDetailsIntegrationController.class);

    /**
     * The zone service.
     */
    @Autowired
    private ZoneDetailsIntegrationService zoneService;


    @GetMapping(value = "/mockservices/instrumentdetails")
    public ResponseEntity<List<GbmOciViewDto>> getCbsInstrumentDetails() {

            final List<GbmOciViewDto> ociViewList = this.zoneService.getCbsInstrumentDetails();
            return new ResponseEntity<>(ociViewList, HttpStatus.OK);

    }

    @GetMapping("/setnumdetails")
    public ResponseEntity<List<GbmOciViewDto>> getClearingSetNumDetails(
            @RequestParam(name = "instrumentNumber", required = true) final String instrumentNumber,
            @RequestParam(name = "solId", required = true) final String solId,
            @RequestParam(name = "zoneCode", required = true) final String zoneCode,
            @RequestParam(name = "zoneDate", required = true) final String zoneDate) throws ParseException {

            Date date = null;
                date = DateUtils.parseDate(zoneDate, "dd/MM/yyyy");

            return new ResponseEntity<>(this.zoneService.getClearingSetNumDetails(instrumentNumber, solId, zoneCode, date),
                    HttpStatus.OK);

    }

    @PostMapping(value = "/clearingzonedetails")
    public ResponseEntity<List<GbmOciViewDto>> getClearingZoneDetails(
            @Validated @RequestBody final GbmOciViewDto ociViewDto) {

            return new ResponseEntity<>(this.zoneService.getClearingZoneDetails(ociViewDto), HttpStatus.OK);

    }

    @PostMapping(value = "/zonedetails")
    public ResponseEntity<List<GbmOzhViewDto>> getZoneDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            final List<GbmOzhViewDto> dtoList = this.zoneService.getZoneDetails(cbsCommonDTo.getSolId(), cbsCommonDTo.getZoneCode());
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }
}
