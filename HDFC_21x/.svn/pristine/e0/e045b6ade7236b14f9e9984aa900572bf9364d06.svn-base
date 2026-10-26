package com.isl.gbm.rbibond.externalservice.test;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.dto.AccountValidationRequestDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.externalservice.service.FtsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("external-service")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ExternalService externalService;

    @Autowired
    private FtsService ftsService;

    /**
     * Endpoint for testing account validation.
     * @param requestDTO the request data for account validation
     * @return ResponseEntity with ResponseDataDTO
     */
    @PostMapping("/api/test-account-validation")
    public ResponseEntity<ResponseDataDTO> testAccountValidation(@RequestBody AccountValidationRequestDTO requestDTO) {
        logger.info("Received account validation request: {}", requestDTO);

        ResponseDataDTO responseData = externalService.accountValidationFTS(requestDTO);

        return ResponseEntity.status(HttpStatus.valueOf(Integer.parseInt(responseData.getStatusCode()))).body(responseData);
    }

    /**
     * Endpoint for handling PAN validation requests.
     * @param panDetailsDTO the request data containing PAN details
     * @return ResponseEntity with ResponseDataDTO
     */
    @PostMapping(value = "/retrieveResponse")
    public ResponseEntity<ResponseDataDTO> handlePANValServiceRequest(@RequestBody PanDetailsDTO panDetailsDTO) {
        logger.info("Received PAN validation request: {}", panDetailsDTO);

        ResponseDataDTO responseData = externalService.panValidation(panDetailsDTO);

        logger.info("PAN validation response: {}", responseData);

        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(responseData.getStatusCode()));
        return ResponseEntity.status(status).body(responseData);
    }

    /**
     * Endpoint for handling FTS requests.
     * @param ackNum the request data containing ACK Number
     * @return ResponseEntity with ResponseDataDTO
     */
    @GetMapping(value = "/fts/request")
    public ResponseEntity<ResponseDataDTO> handleFTSServiceRequest(@RequestParam String ackNum) {
        logger.info("Received FTS request: {}", ackNum);

        ResponseDataDTO responseData = ftsService.getFileDetails(ackNum);

        logger.info("FTS validation response: {}", responseData);

        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(responseData.getStatusCode()));
        return ResponseEntity.status(status).body(responseData);
    }

}
