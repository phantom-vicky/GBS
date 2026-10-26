/*
 *
 */
package com.isl.gbm.cbs.integration.controller;


import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.facade.SolDateFacade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The Class SolDateController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class SolDateController {

    private static final Logger log = LoggerFactory.getLogger(SolDateController.class);


    @Autowired
    private SolDateFacade solDateFacade;


    /**
     * Gets record.
     *
     * @return record
     */
/*    @RequestMapping(value = "/master/soldate", method = RequestMethod.POST)
    public ResponseEntity<SolDateDto> getSolDateRecord() {

            return solDateFacade.getSolDateRecord();

    }*/
    @PostMapping(path = "/master/soldate")
    public ResponseEntity<ResponseDataDTO> getSolDateRecordInquiry() {
        return APIResponseBuilder.buildResponseFromDto(solDateFacade.getSolDateRecordInquiry());
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
 /*   @RequestMapping(value = "/master/soldate/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveSolDateDetails(@Validated @RequestBody final SolDateDto dto) {

            return solDateFacade.saveSolDateDetails(dto);

    }*/

    @PostMapping(path = "/master/soldate/save")
    public ResponseEntity<ResponseDataDTO> saveSolDateDetails(@RequestBody final SolDateDto dto) {
        return APIResponseBuilder.buildResponseFromDto(solDateFacade.saveSolDateDetails(dto));
    }

}
