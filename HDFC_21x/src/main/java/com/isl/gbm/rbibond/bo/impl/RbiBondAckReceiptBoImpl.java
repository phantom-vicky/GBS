package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAckReceiptBo;
import com.isl.gbm.rbibond.dao.RbiBondAckReceiptDAO;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  RbiBondAckReceiptBoImpl implements RbiBondAckReceiptBo {

    @Autowired
    RbiBondAckReceiptDAO rbiBondAckReceiptDAO;
    @Override
    public ResponseDataDTO add(RbiBondAckReceiptDTO rbiBondAckReceiptDTO){
        return rbiBondAckReceiptDAO.detailAdd(rbiBondAckReceiptDTO);
    }

    @Override
    public ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return rbiBondAckReceiptDAO.addTorch(rbiBondAckReceiptDTO);
    }



    @Override
    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO){
        return rbiBondAckReceiptDAO.inquiry(rbiBondAckReceiptDTO);
    }




}
