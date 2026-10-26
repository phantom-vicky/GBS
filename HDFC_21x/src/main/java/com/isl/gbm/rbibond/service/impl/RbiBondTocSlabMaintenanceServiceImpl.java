package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondTocSlabMaintenanceBO;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;
import com.isl.gbm.rbibond.service.RbiBondTocSlabMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondTocSlabMaintenanceServiceImpl implements RbiBondTocSlabMaintenanceService {

    @Autowired
    private RbiBondTocSlabMaintenanceBO rbiBondTocSlabMaintenanceBO;
    @Override
    public ResponseDataDTO selectModifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.selectModifySlab(rbiBondTocSlabMaintenanceDTO) ;
    }

    @Override
    public ResponseDataDTO selectVerifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.selectVerifySlab(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.getAddDetails(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO save(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.save(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.modify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.verify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.delete(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceBO.inquiry(rbiBondTocSlabMaintenanceDTO);
    }
}
