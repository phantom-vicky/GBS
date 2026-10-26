package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;

import java.util.List;

public interface BrokerageInvoiceConfirmationDAO {

    ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO invoiceVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO upload(List<BrokerageInvoiceConfirmationVerifyDTO> dtoList,BrokerageInvoiceConfirmationVerifyDTO dto1);

    ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO);

    ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO);

    ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);

    ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO);
}
