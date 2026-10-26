package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondTocSharingMaintenanceBO;
import com.isl.gbm.rbibond.dto.RbiBondTocSharingMaintenanceDTO;
import com.isl.gbm.rbibond.service.RbiBondTocSharingMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondTocSharingMaintenanceServiceImpl implements RbiBondTocSharingMaintenanceService {
    @Autowired
    private RbiBondTocSharingMaintenanceBO rbiBondTocSharingMaintenanceBO;

    @Override
    public ResponseDataDTO add(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.add(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addValidation(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.addValidation(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.modifySelect(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.modify(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.verifyDeleteSelect(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.verify(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.delete(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceBO.inquiry(rbiBondTocSharingMaintenanceDTO);
    }
}
