package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface BondSubAccClosureBeforeMaturityDAO {

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String paymentDate);

    ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO);

    ResponseDataDTO addDetailsForDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO addSubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifyBLADetails(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO delete(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO);
}
