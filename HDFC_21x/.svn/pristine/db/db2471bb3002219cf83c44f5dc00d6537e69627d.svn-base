package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondTransferInDTO;

import java.sql.SQLException;

public interface RbiBondTransferInDao {

    public ResponseDataDTO getblaNoInq(RbiBondTransferInDTO rbiBondTransferInDTO);
    public ResponseDataDTO getsubscriptionIdInq(RbiBondTransferInDTO rbiBondTransferInDTO);
    public ResponseDataDTO panTorch(RbiBondTransferInDTO rbiBondTransferInDTO);

    public ResponseDataDTO addTorch(RbiBondTransferInDTO rbiBondTransferInDTO);


    public ResponseDataDTO  otherBankDetails() throws SQLException;

    public ResponseDataDTO bondTransferInAdd(RbiBondTransferInDTO rbiBondTransferInAddDto);

    public ResponseDataDTO modifySelect(RbiBondTransferInDTO rbiBondTransferInDTO);
    public ResponseDataDTO modifyPAN(RbiBondTransferInDTO rbiBondTransferInDTO);

    public ResponseDataDTO verifySelect(RbiBondTransferInDTO rbiBondTransferInDTO);

    public ResponseDataDTO verify(RbiBondTransferInDTO rbiBondTransferInDTO);

    public ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto);

    public ResponseDataDTO delete(RbiBondTransferInDTO rbiBondTransferInDTO);

    public ResponseDataDTO modify(RbiBondTransferInDTO rbiBondTransferInDTO);
    public ResponseDataDTO getGuardianDetails(RbiBondTransferInDTO rbiBondTransferInDTO);
    ResponseDataDTO apiResponse(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    ResponseDataDTO validatePanRequest(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO);

    public ResponseDataDTO inquiry(RbiBondTransferInDTO rbiBondTransferInDTO);




}
