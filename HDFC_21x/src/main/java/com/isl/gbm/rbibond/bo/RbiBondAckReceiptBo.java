package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;

public interface RbiBondAckReceiptBo {
    public ResponseDataDTO add(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);

    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);

    ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);

//    public ResponseDataDTO addValidation(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);
}
