package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondTocSlabMaintenanceBO;
import com.isl.gbm.rbibond.dao.RbiBondTocSlabMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondTocSlabMaintenanceBoImpl implements RbiBondTocSlabMaintenanceBO {
    @Autowired
    private RbiBondTocSlabMaintenanceDAO rbiBondTocSlabMaintenanceDAO;

    @Override
    public ResponseDataDTO selectModifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.selectModifySlab(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO selectVerifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.selectVerifySlab(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.getAddDetails(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO save(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.save(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.modify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.verify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.delete(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceDAO.inquiry(rbiBondTocSlabMaintenanceDTO);
    }
}
