package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondLedgerKartaDTO;

public interface RbiBondLedgerKartaBO {

    ResponseDataDTO getTorchPan(RbiBondLedgerKartaDTO rbiBondLedgerKartDTO);

    ResponseDataDTO getModifyTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartDTO);

    ResponseDataDTO getInquiryTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO getModifyBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO getVerDelTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO modifySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO addModify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO inquiry(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO inquirySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO inquiryPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO verDelSelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO verify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);

    ResponseDataDTO delete(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO);
}
