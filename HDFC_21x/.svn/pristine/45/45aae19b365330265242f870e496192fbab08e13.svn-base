package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BondSubAccClosureBeforeMaturityService {

    ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String paymentDate);

    ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);
}
