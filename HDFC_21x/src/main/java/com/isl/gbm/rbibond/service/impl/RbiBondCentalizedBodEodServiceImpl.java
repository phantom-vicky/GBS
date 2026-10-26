package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondCentalizedBodEodBo;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;
import com.isl.gbm.rbibond.service.RbiBondCentalizedBodEodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondCentalizedBodEodServiceImpl implements RbiBondCentalizedBodEodService {

    @Autowired
 private RbiBondCentalizedBodEodBo centalizedBODBO;
    @Override
    public ResponseDataDTO add(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.add(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.getDate(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.getLevelBranch(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.startEodProcess(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getEodDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.getEodDate(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getEodLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODBO.getEodLevelBranch(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getTimeValidation() {
        return centalizedBODBO.getTimeValidation();
    }
}
