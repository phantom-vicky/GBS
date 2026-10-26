package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAckReceiptBo;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;
import com.isl.gbm.rbibond.service.RbiBondAckReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondAckReceiptServiceImpl implements RbiBondAckReceiptService {

    @Autowired
    RbiBondAckReceiptBo rbiBondAckReceiptBo;

    @Override
    public ResponseDataDTO add(RbiBondAckReceiptDTO rbiBondAckReceiptDTO){
        return rbiBondAckReceiptBo.add(rbiBondAckReceiptDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO){
        return rbiBondAckReceiptBo.inquiry(rbiBondAckReceiptDTO);
    }

    @Override
    public ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return rbiBondAckReceiptBo.addTorch(rbiBondAckReceiptDTO);
    }

//    @Override
//    public ResponseDataDTO addValidation(RbiBondAckReceiptDTO rbiBondAckReceiptDTO){
//        return rbiBondAckReceiptBo.addValidation(rbiBondAckReceiptDTO);
//    }
}
