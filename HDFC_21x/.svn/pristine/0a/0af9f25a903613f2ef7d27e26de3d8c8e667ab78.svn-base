package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.facade.BondActualInterestPaymentFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/bond-actual-payments")
public class BondActualInterestPaymentController {

    private final BondActualInterestPaymentFacade paymentFacade;

    public BondActualInterestPaymentController(final BondActualInterestPaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    @PostMapping("/add-details")
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.addDetails(paymentRequestDTO));
    }

    @PostMapping("/add-submit")
    public ResponseEntity<ResponseDataDTO> addSubmit(@Valid @RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.addDetails(paymentRequestDTO));
    }

    @PostMapping("/verify-details")
    public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.verifyDetails(paymentRequestDTO));
    }

    @PostMapping("/verify-submit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@Valid @RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.verifySubmit(paymentRequestDTO));
    }

    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiryDetails(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.inquiry(paymentRequestDTO));
    }

    @PostMapping("/reject")
    public ResponseEntity<ResponseDataDTO> rejectionDetails(@Valid @RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.reject(paymentRequestDTO));
    }

    @PostMapping("/download-file")
    public ResponseEntity<ResponseDataDTO> downloadFileFromSFTP(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.downloadFileFromSFTP(paymentRequestDTO));
    }
    @PostMapping("/mergeUnMerge-download-file")
    public ResponseEntity<ResponseDataDTO> mergeUnMergeDownloadFile(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.mergeUnMergeDownloadFile(paymentRequestDTO));
    }
    @PostMapping("/upload-text-file")
    public ResponseEntity<ResponseDataDTO> uploadFile(@RequestPart(name = "textFile") MultipartFile textFile,
                                                      @RequestPart(name = "schemeCode") String schemeCode,
                                                      @RequestPart(name = "halfYear") String halfYear,
                                                      @RequestPart(name = "year") String year
    ) {
        return APIResponseBuilder.buildResponseFromDto(this.paymentFacade.uploadFile(textFile, schemeCode, halfYear, year));
    }
}