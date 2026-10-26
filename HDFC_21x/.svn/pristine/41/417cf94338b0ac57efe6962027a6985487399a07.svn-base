/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.SolDateData;
import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.service.SolDateIntegrationService;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.util.CommonConstants;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * The Class SolDateIntegrationController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class SolDateIntegrationController {

    private static final Logger log = LoggerFactory.getLogger(SolDateIntegrationController.class);

    /**
     * The sol date integration service.
     */
    @Autowired
    private SolDateIntegrationService solDateIntegrationService;


    /**
     * Gets the sol date.
     *
     * @return the sol date
     */
    @RequestMapping(value = "/soldate", method = RequestMethod.POST)
    public ResponseEntity<SolDateDto> getSolDate() {

            SolDateData solDateData = null;
            SolDateDto solDateDto = solDateIntegrationService.getSolDate();
		/*if(solDateDto!=null){
			solDateData=new SolDateData();
			solDateData.setSolDate(solDateDto.getSolDate().getTime());
			solDateData.setModifiedDate(solDateDto.getModifiedDate());
			solDateData.setAddlText1(solDateDto.getAddlText1());
			solDateData.setAddlText2(solDateDto.getAddlText2());
			solDateData.setAddlText3(solDateDto.getAddlText3());
		}*/

            return new ResponseEntity<>(solDateDto, HttpStatus.OK);

    }

    /**
     * Checks if is document submitted recently(In last six months).
     *
     * @param date the date (pattern = "yyyy-MM-dd")
     * @return the response entity
     */
    @RequestMapping(value = "/soldate/validatedocumentdate", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> isDocumentSubmittedRecently(
            @RequestParam(name = "documentDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date) {

            return new ResponseEntity<>(
                    BooleanUtils.toStringTrueFalse(this.solDateIntegrationService.isDocumentSubmittedRecently(date)),
                    HttpStatus.OK);

    }

}
