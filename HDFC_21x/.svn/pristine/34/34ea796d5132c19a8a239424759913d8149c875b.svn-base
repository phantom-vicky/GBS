package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.facade.RbiBondClosureOnMaturDeathFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/rbiBond/accClosureOnMaturityDeath")
@RestController
public class RbiBondClosureOnMaturDeathController {

  @Autowired
  RbiBondClosureOnMaturDeathFacade rbiBondClosureOnMaturDeathFacade;


    @PostMapping(path = "/getAddDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddDetails(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.getAddDetails(rbiBondClosureBeforeMaturDTO));
    }
    @PostMapping(path = "/getAddValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAddValidation(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.getAddValidation(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getPanValidation", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getPanValidation(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.getPanValidation(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getVerDelDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getVerDelDetails(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.getVerDelDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/getInqDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInqDetails(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.getInqDetails(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.add(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/verify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.verify(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping(path = "/delete", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.delete(rbiBondClosureBeforeMaturDTO));
    }

    @PostMapping("/upload-text-file")
    public ResponseEntity<ResponseDataDTO> uploadFile(@RequestPart(name = "textFile") MultipartFile textFile,
                                                      @RequestPart(name = "schemeCode") String schemeCode,
                                                      @RequestPart(name = "paymentDate") String paymentDate
    ) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.uploadFile(textFile, schemeCode, paymentDate));
    }


    @PostMapping("/download-file")
    public ResponseEntity<ResponseDataDTO> downloadFileFromSFTP(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondClosureOnMaturDeathFacade.downloadFileFromSFTP(paymentRequestDTO));
    }



}


