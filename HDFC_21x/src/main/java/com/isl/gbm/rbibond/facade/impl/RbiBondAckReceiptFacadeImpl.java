package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAckReceiptBo;
import com.isl.gbm.rbibond.dto.RbiBondAckReceiptDTO;
//import com.isl.gbm.rbibond.facade.RbiBondAckReceiptFacade;
import com.isl.gbm.rbibond.facade.RbiBondAckReceiptFacade;
import com.isl.gbm.rbibond.service.RbiBondAckReceiptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondAckReceiptFacadeImpl implements RbiBondAckReceiptFacade {
    @Autowired
    private RbiBondAckReceiptService rbiBondAckReceiptService;

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ADD")
    public ResponseDataDTO add(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return rbiBondAckReceiptService.add(rbiBondAckReceiptDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return rbiBondAckReceiptService.inquiry(rbiBondAckReceiptDTO);

    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BOND_ACK_RECEIPT:ADD")
    public ResponseDataDTO addTorch(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
        return rbiBondAckReceiptService.addTorch(rbiBondAckReceiptDTO);
    }
//    @Override
//    @RequiresPermissions("")
//    public ResponseDataDTO addValidation(RbiBondAckReceiptDTO rbiBondAckReceiptDTO) {
//        return rbiBondAckReceiptService.addValidation(rbiBondAckReceiptDTO);
//    }
}