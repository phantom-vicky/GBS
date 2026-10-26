package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dao.StateMasterDao;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.facade.StateFacade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class StateIntegrationController {
    private static final Logger log = LoggerFactory.getLogger(StateIntegrationController.class);

    @Autowired
    StateFacade stateFacade;

    @RequestMapping(value = "/bulkupload/stCyPin/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveDetails(@RequestPart("file") final MultipartFile file) {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.save(file));
    }

    @RequestMapping(value = "/bulkupload/stCyPin/getInquiryDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody StateMasterDTO stateMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.getInquiryDetails(stateMasterDTO));
    }

    @RequestMapping(value = "/bulkupload/stCyPin/getVerifyDetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> getVerifyDetails( @RequestBody StateMasterDTO stateMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.getVerifyDetails(stateMasterDTO));
    }

    @RequestMapping(value = "/bulkupload/stCyPin/verifySubmit", method = RequestMethod.GET)
    public ResponseEntity<ResponseDataDTO> verifySubmit() {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.verifySubmit());
    }

    @RequestMapping(value = "/bulkupload/stCyPin/dataExistsCheck", method = RequestMethod.GET)
    public ResponseEntity<ResponseDataDTO> dataExistsCheck() {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.dataExistsCheck());
    }

    @RequestMapping(value = "/bulkupload/stCyPin/deleteSubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody StateMasterDTO stateMasterDTO) {
        return APIResponseBuilder.buildResponseFromDto(stateFacade.deleteSubmit(stateMasterDTO));
    }

    @RequestMapping(value = "/bulkupload/stCyPin/downloadPincode", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> downloadPincode() throws IOException, InvalidFormatException {
        return stateFacade.downloadPincode();
    }
}
