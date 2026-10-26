package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondTocSharingMaintenanceBO;
import com.isl.gbm.rbibond.dao.RbiBondTocSharingMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RbiBondTocSharingMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RbiBondTocSharingMaintenanceBoImpl implements RbiBondTocSharingMaintenanceBO {

    @Autowired
        private RbiBondTocSharingMaintenanceDAO rbiBondTocSharingMaintenanceDAO;

        @Override
        public ResponseDataDTO add(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.add(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO addValidation(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.addValidation(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO modifySelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.modifySelect(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO modify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.modify(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO verifyDeleteSelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.verifyDeleteSelect(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO verify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.verify(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO delete(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.delete(rbiBondTocSharingMaintenanceDTO);
        }

        @Override
        public ResponseDataDTO inquiry(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
            return rbiBondTocSharingMaintenanceDAO.inquiry(rbiBondTocSharingMaintenanceDTO);
        }
}
