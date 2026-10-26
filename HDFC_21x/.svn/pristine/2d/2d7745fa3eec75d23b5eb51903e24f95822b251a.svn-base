package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationInquiryDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationResponseDTO;
import com.isl.gbm.rbibond.dto.BrokerageInvoiceConfirmationVerifyDTO;
import com.isl.gbm.rbibond.facade.BrokerageInvoiceConfirmationFacade;
import com.isl.gbm.rbibond.service.BrokerageInvoiceConfirmationService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class BrokerageInvoiceConfirmationFacadeImpl implements BrokerageInvoiceConfirmationFacade {

    @Autowired
    private BrokerageInvoiceConfirmationService brokerageInvoiceConfirmationService;

    @Override
    public ResponseDataDTO getAgencyCode(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationService.getAgencyCode(brokerageInvoiceConfirmationDTO);

    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationDetails(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationService.getInvoiceConfirmationDetails(brokerageInvoiceConfirmationDTO);

    }

    @Override
    public ResponseDataDTO invocieVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationService.invoiceVerify(brokerageInvoiceConfirmationVerifyDTO);

    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationVerify(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationService.getInvoiceConfirmationVerify(brokerageInvoiceConfirmationVerifyDTO);

    }

    @Override
    public ResponseDataDTO getInvoiceConfirmationInquiry(BrokerageInvoiceConfirmationInquiryDTO brokerageInvoiceConfirmationInquiryDTO){
        return brokerageInvoiceConfirmationService.getInvoiceConfirmationInquiry(brokerageInvoiceConfirmationInquiryDTO);

    }

    @Override
    public ResponseDataDTO submitDeleteForm(BrokerageInvoiceConfirmationDTO brokerageInvoiceConfirmationDTO){
        return brokerageInvoiceConfirmationService.submitDeleteForm(brokerageInvoiceConfirmationDTO);

    }

    @Override
    public ResponseDataDTO getDeleteSubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationService.getDeleteSubmit(brokerageInvoiceConfirmationVerifyDTO);

    }

    @Override
    public ResponseDataDTO verifySubmit(BrokerageInvoiceConfirmationVerifyDTO brokerageInvoiceConfirmationVerifyDTO){
        return brokerageInvoiceConfirmationService.verifySubmit(brokerageInvoiceConfirmationVerifyDTO);

    }


    @Override
    public ResponseDataDTO upload(MultipartFile file, String brokerageInvoiceConfirmationVerifyDTO,BrokerageInvoiceConfirmationVerifyDTO dto){
        return brokerageInvoiceConfirmationService.upload(file,brokerageInvoiceConfirmationVerifyDTO,dto);

    }

    @Override
    public ResponseEntity<ResponseDataDTO> paymentConfirmation() throws IOException, InvalidFormatException{
        return brokerageInvoiceConfirmationService.paymentConfirmation();
    }


}
