package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.facade.SolDateFacade;
import com.isl.gbm.cbs.integration.service.SolDateService;
import com.isl.gbm.cbs.integration.validator.SolDateValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SolDateFacadeImpl implements SolDateFacade {

    /**
     * The sol date validator.
     */
    @Autowired
    private SolDateValidator solDateValidator;

    /**
     * The sol date service.
     */
    @Autowired
    private SolDateService solDateService;


    /**
     * Builds the error response.
     *
     * @param message       the message
     * @param errorMessages the error messages
     * @return the response entity
     */
    private ResponseEntity<ResponseDataDTO> buildErrorResponse(final String message,
                                                               final Map<String, String> errorMessages) {
        final ResponseDataDTO errorDto = this.buildResponse(message);
        errorDto.setResult(errorMessages);
        return new ResponseEntity<>(errorDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Builds the response.
     *
     * @param message the message
     * @return the response data DTO
     */
    private ResponseDataDTO buildResponse(final String message) {
        final ResponseDataDTO dataDto = new ResponseDataDTO();
        dataDto.setMessage(message);
        return dataDto;
    }

    /**
     * Gets record.
     *
     * @return record
     */
  /*  @RequiresPermissions("GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:ENQUIRY")
    public ResponseEntity<SolDateDto> getSolDateRecord() {
        return new ResponseEntity<>(this.solDateService.getSolDateRecord(), HttpStatus.OK);
    }*/

    public ResponseDataDTO getSolDateRecordInquiry() {
        return solDateService.getSolDateRecordInquiry();
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
   /* @RequiresPermissions({"GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:ADD", "GBM_CBS_MASTER_UPDATION:POPULATION_OF_SOL_DATE:MODIFY"})
    public ResponseEntity<ResponseDataDTO> saveSolDateDetails(final SolDateDto dto) {
      *//*  if (BooleanUtils.isTrue(this.solDateValidator.isValidDate(dto))) {
            return this.buildErrorResponse("Please select current date or future date.", null);
        }
        if (BooleanUtils.isTrue(this.solDateValidator.isDataUpdated())) {
            return this.buildErrorResponse("Data has already updated today.", null);
        }*//*
        this.solDateService.saveDetailsSolDate(dto);
        final ResponseDataDTO dataDto = this.buildResponse("Data saved/updated successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }*/

   @Override
    public ResponseDataDTO saveSolDateDetails(SolDateDto dto) {
        return solDateService.saveDetailsSolDate(dto);
    }

}
