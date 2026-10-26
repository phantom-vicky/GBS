package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSchemeParameterBO;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;
import com.isl.gbm.rbibond.service.RbiBondSchemeParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondSchemeParameterServiceImpl implements RbiBondSchemeParameterService {

    @Autowired
    private RbiBondSchemeParameterBO efreightSchemeParameterBO;

    public ResponseDataDTO isRecordExist(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.isRecordExist(rbiBondSchemeParameterDTO);
    }

    public ResponseDataDTO add(RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return efreightSchemeParameterBO.add(efreightSchemeParameterDTO);
    }

    public ResponseDataDTO modifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.modifySelect(rbiBondSchemeParameterDTO);
    }

    public ResponseDataDTO modify(RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return efreightSchemeParameterBO.modify(efreightSchemeParameterDTO);
    }

    public ResponseDataDTO verify(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.verify(rbiBondSchemeParameterDTO);
    }

    public ResponseDataDTO unVerifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.unVerifySelect(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.delete(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.inquiry(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO interestRtCdTorch(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterBO.interestRtCdTorch(rbiBondSchemeParameterDTO);
    }

}
