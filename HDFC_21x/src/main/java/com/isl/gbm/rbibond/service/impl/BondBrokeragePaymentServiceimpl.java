package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BondBrokeragePaymentBO;
import com.isl.gbm.rbibond.dto.BondBrokeragePaymentDTO;
import com.isl.gbm.rbibond.service.BondBrokeragePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BondBrokeragePaymentServiceimpl  implements BondBrokeragePaymentService {

    @Autowired
    private BondBrokeragePaymentBO bondBrokeragePaymentBO;

    @Override
    public ResponseDataDTO getBrokerDetails(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.getBrokerDetails(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO add(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.add(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.addValidation(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.inquiry(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.verifySelect(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO verify(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.verify(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO delete(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentBO.delete(bondBrokeragePaymentDTO);
    }


}
