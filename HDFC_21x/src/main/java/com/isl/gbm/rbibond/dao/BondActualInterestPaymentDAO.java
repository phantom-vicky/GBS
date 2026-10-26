package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;

public interface BondActualInterestPaymentDAO {

    ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO addSubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String halfYear, String year);
    ResponseDataDTO downloadMergedUnMerged(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO);
}
