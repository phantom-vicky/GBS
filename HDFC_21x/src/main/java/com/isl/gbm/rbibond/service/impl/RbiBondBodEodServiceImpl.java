package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondBodEodBo;
import com.isl.gbm.rbibond.service.RbiBondBodEodService;
import com.isl.gbm.rbibond.dto.BodEodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RbiBondBodEodServiceImpl implements RbiBondBodEodService {

    @Autowired
    private RbiBondBodEodBo rbiBondBodEodBo;

    @Override
    public ResponseDataDTO startBodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodBo.startBodProcess(bodEodDto);
    }


    @Override
    public ResponseDataDTO startLinkCellEodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodBo.startLinkCellEodProcess(bodEodDto);
    }

    @Override
    public ResponseDataDTO startPayBranchEodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodBo.startPayBranchEodProcess(bodEodDto);
    }

    @Override
    public ResponseDataDTO getTimeValidation() {
        return rbiBondBodEodBo.getTimeValidation();
    }
}
