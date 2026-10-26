package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;
import org.springframework.web.multipart.MultipartFile;


public interface BrokerageInvoiceConfirmationBO {

    ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);
    ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);
    ResponseDataDTO upload(MultipartFile file, String brokerageInvoiceConfirmationVerifyDTO, BrokerageInvoiceConfirmationVerifyDTO dto);


    ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO);

    ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);
}
