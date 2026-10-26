package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;

public interface BondActualInterestPaymentBO {

    ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String halfYear, String year);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO downloadMergedUnMerged(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO);
}
