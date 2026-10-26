package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSubscripReversalAccNoBO;
import com.isl.gbm.rbibond.dao.RbiBondSubscripReversalAccNoDAO;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSubscripReversalAccNoBOImpl implements RbiBondSubscripReversalAccNoBO {

    @Autowired
    RbiBondSubscripReversalAccNoDAO rbiBondSubscripReversalAccNoDAO;

    @Override
    public ResponseDataDTO add(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.add(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.addValidation(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO verify(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.verify(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO delete(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.delete(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.inquiry(bondSubscripReversalAccNoDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoDAO.verifySelect(bondSubscripReversalAccNoDTO);
    }
}
