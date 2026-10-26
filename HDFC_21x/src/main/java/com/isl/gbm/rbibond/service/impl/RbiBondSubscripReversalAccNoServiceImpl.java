package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSubscripReversalAccNoBO;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;
import com.isl.gbm.rbibond.service.RbiBondSubscripReversalAccNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondSubscripReversalAccNoServiceImpl implements RbiBondSubscripReversalAccNoService {

    @Autowired

    RbiBondSubscripReversalAccNoBO rbiBondSubscripReversalAccNoBO;

    @Override
    public ResponseDataDTO add(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.add(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.addValidation(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO verify(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.verify(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO delete(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.delete(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.inquiry(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoBO.verifySelect(bondSubscripReversalAccNoDTO);
    }
}
