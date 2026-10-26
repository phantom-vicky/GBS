package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationResponseDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BrokerageInvoiceConfirmationService {

    ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);
    ResponseDataDTO upload(MultipartFile file,  String brokerageInvoiceConfirmationVerifyDTO,BrokerageInvoiceConfirmationVerifyDTO dto);

    ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO);

    ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseEntity<ResponseDataDTO> paymentConfirmation() throws IOException, InvalidFormatException;
}
