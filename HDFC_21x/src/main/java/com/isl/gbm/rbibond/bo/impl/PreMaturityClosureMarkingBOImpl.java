package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.PreMaturityClosureMarkingBO;
import com.isl.gbm.rbibond.dao.PreMaturityClosureMarkingDAO;
import com.isl.gbm.rbibond.dto.PreMaturityRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreMaturityClosureMarkingBOImpl implements PreMaturityClosureMarkingBO {

    @Autowired
    PreMaturityClosureMarkingDAO preMaturityClosureMarkingDAO;

    @Override
    public ResponseDataDTO addDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.addDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO addTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.addTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO modifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.modifyDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO modifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.modifyTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO verifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.verifyDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO verifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.verifyTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO inquiryDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.inquiryDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO inquiryTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.inquiryTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO add(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.add(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO modify(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.modify(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO verify(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.verify(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO delete(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingDAO.delete(preMaturityRequestDTO);
    }
}
