package com.isl.gbm.rbibond.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.SubscriptionAccClosingOnMaturityDTO;
import com.isl.gbm.rbibond.facade.SubscriptionAccClosingOnMaturityFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("gbmultra/rbiBond/subsAccClsOnMry")
public class SubscriptionAccClosingOnMaturityController {


    @Autowired
    private SubscriptionAccClosingOnMaturityFacade subscriptionAccClosingOnMaturityFacade;

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDataDTO> add(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.add(subscriptionAccClosingOnMaturityDTO));
    }


    @PostMapping(path = "/addValidation")
    public ResponseEntity<ResponseDataDTO> addValidation(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.addValidation(subscriptionAccClosingOnMaturityDTO));
    }


    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.verifySelect(subscriptionAccClosingOnMaturityDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.verify(subscriptionAccClosingOnMaturityDTO));
    }


    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.inquiry(subscriptionAccClosingOnMaturityDTO));
    }


    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return APIResponseBuilder.buildResponseFromDto(subscriptionAccClosingOnMaturityFacade.delete(subscriptionAccClosingOnMaturityDTO));
    }

    @PostMapping("/download-file")
    public ResponseEntity<ResponseDataDTO> downloadFileFromSFTP(@RequestBody BondActualInterestPaymentRequestDTO paymentRequestDTO) throws Exception {
        return APIResponseBuilder.buildResponseFromDto(this.subscriptionAccClosingOnMaturityFacade.downloadFileFromSFTP(paymentRequestDTO));
    }

    @PostMapping("/upload-text-file")
    public ResponseEntity<ResponseDataDTO> uploadFile(@RequestPart(name = "textFile") MultipartFile textFile,
                                                      @RequestPart(name = "schemeCode") String schemeCode,
                                                      @RequestPart(name = "fromDate") String fromDate,
                                                      @RequestPart(name = "toDate") String toDate
    ) {
        return APIResponseBuilder.buildResponseFromDto(this.subscriptionAccClosingOnMaturityFacade.uploadFile(textFile, schemeCode, fromDate, toDate));
    }

}
