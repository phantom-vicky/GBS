package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.BondInterestPaymentFileDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;

public interface RbiBondClosureOnMaturDeathDao {



    ResponseDataDTO getAddDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getAddValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getInqDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO add(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO delete(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO verify(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);


    ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto);
    ResponseDataDTO updatePANStatus(PanValidationRetriggerResponseDTO responseDTO);

    ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO verifySubmit(RbiBondClosureOnMaturDeathDTO paymentRequestDTO);

    ResponseDataDTO uploadFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO, String schemeCode, String paymentDate);

    ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO);
    ResponseDataDTO downloadFileManual(BondActualInterestPaymentRequestDTO paymentRequestDTO);

    ResponseDataDTO processPaymentFile(BondInterestPaymentFileDTO bondInterestPaymentFileDTO);

}
