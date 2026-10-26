package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;

public interface RbiBondAckReceiptDAO {


    public ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO ) ;

    public ResponseDataDTO detailAdd(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);

    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);



//    public ResponseDataDTO addValidation(RbiBondAckReceiptDTO rbiBondAckReceiptDTO);
}
