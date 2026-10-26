package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondClosureBeforeMaturDeathBo;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;
import com.isl.gbm.rbibond.service.RbiBondClosureBeforeMaturDeathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondClosureBeforeMaturDeathServiceImpl implements RbiBondClosureBeforeMaturDeathService {

    @Autowired
    RbiBondClosureBeforeMaturDeathBo rbiBondClosureBeforeMaturDeathBo;

    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathBo.delete(rbiBondClosureBeforeMaturDTO);
    }
}
