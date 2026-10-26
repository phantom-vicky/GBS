package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BondActualInterestPaymentService {

    ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO mergeUnMergeDownloadFile(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String halfYear, String year);
}
