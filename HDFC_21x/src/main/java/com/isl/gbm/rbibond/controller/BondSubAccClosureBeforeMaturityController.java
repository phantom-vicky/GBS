package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.facade.BondSubAccClosureBeforeMaturityFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/apis/bond-sub-acc-before-closure-maturity")
public class BondSubAccClosureBeforeMaturityController {

    private BondSubAccClosureBeforeMaturityFacade facade;

    public BondSubAccClosureBeforeMaturityController(BondSubAccClosureBeforeMaturityFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/add-details")
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.facade.addDetails(paymentRequestDTO));
    }

    @PostMapping("/verify-details")
    public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.facade.verifyDetails(paymentRequestDTO));
    }

    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.facade.inquiry(paymentRequestDTO));
    }

    @PostMapping("/download-file")
    public ResponseEntity<ResponseDataDTO> downloadFileFromSFTP(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(this.facade.downloadFileFromSFTP(paymentRequestDTO));
    }

    @PostMapping("/upload-text-file")
    public ResponseEntity<ResponseDataDTO> uploadFile(@RequestPart(name = "textFile") MultipartFile textFile,
                                                      @RequestPart(name = "schemeCode") String schemeCode,
                                                      @RequestPart(name = "paymentDate") String paymentDate
    ) {
        return APIResponseBuilder.buildResponseFromDto(this.facade.uploadFile(textFile, schemeCode, paymentDate));
    }

}
