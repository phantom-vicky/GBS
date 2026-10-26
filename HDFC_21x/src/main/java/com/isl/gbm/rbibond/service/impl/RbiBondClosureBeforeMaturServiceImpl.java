package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondClosureBeforeMaturBo;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;
import com.isl.gbm.rbibond.service.RbiBondClosureBeforeMaturService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondClosureBeforeMaturServiceImpl implements RbiBondClosureBeforeMaturService {

    @Autowired
    RbiBondClosureBeforeMaturBo rbiBondClosureBeforeMaturBo;

    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturBo.delete(rbiBondClosureBeforeMaturDTO);
    }
}
