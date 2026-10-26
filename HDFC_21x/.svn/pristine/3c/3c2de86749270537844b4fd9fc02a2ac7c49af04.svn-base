package com.isl.gbm.rbibond.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.facade.BrokerageInvoiceConfirmationFacade;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("gbmultra/rbiBond/invoiceConfirmation")
public class BrokerageInvoiceConfirmationController {

    @Autowired
    private BrokerageInvoiceConfirmationFacade brokerageInvoiceConfirmationFacade;

    @PostMapping(path = "/addTorch", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getAgencyCode(@RequestBody BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.getAgencyCode(brokerageInvoiceConfirmationDTO));
    }

    @PostMapping(path = "/getInvoiceConfirmationDetails", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInvoiceConfirmationDetails(@RequestBody BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.getInvoiceConfirmationDetails(brokerageInvoiceConfirmationDTO));
    }

    @PostMapping(path = "/invoiceVerify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> invoiceVerify(@RequestBody BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.invocieVerify(brokerageInvoiceConfirmationVerifyDTO));
    }

    @PostMapping(path = "/verifySubmit", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.verifySubmit(brokerageInvoiceConfirmationVerifyDTO));
    }

    @PostMapping(path = "/getInvoiceConfirmationVerify", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInvoiceConfirmationVerify(@RequestBody BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.getInvoiceConfirmationVerify(brokerageInvoiceConfirmationVerifyDTO));
    }

    @PostMapping(path = "/getInvoiceConfirmationInquiry", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getInvoiceConfirmationInquiry(@RequestBody BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.getInvoiceConfirmationInquiry(brokerageInvoiceConfirmationInquiryDTO));
    }

    @PostMapping(path = "/submitDeleteForm", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> submitDeleteForm(@RequestBody BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.submitDeleteForm(brokerageInvoiceConfirmationDTO));
    }

    @PostMapping(path = "/getDeleteSubmit", produces = "application/json")
    public ResponseEntity<ResponseDataDTO> getDeleteSubmit(@RequestBody BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO) {
        return APIResponseBuilder.buildResponseFromDto(brokerageInvoiceConfirmationFacade.getDeleteSubmit(brokerageInvoiceConfirmationVerifyDTO));
    }



    @RequestMapping(value = "/fileUpload/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(
            @RequestPart("file") final MultipartFile file,
            @RequestPart("brokerageInvoiceConfirmationDTO") String requestDTO) throws IOException {

        // Check if the file is empty
        if (file.isEmpty()) {
            return null;
        }
// Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize JSON string to DTO
        BrokerageInvoiceConfirmationVerifyDTO dto = objectMapper.readValue(requestDTO, BrokerageInvoiceConfirmationVerifyDTO.class);

        // Call the service layer to handle the file and DTO
        ResponseDataDTO response = brokerageInvoiceConfirmationFacade.upload(file, requestDTO,dto);

        // Check for any error response from the facade
        if (response == null) {
            return null;
        }

        // Return a successful response
        return APIResponseBuilder.buildResponseFromDto(response);
    }

    @RequestMapping(value = "/paymentConfirmation", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> paymentConfirmation() throws IOException, InvalidFormatException {
        return brokerageInvoiceConfirmationFacade.paymentConfirmation();
    }
}
