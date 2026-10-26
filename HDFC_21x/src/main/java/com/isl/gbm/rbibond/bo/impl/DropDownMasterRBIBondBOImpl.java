package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.DropDownMasterRBIBondBO;
import com.isl.gbm.rbibond.dao.DropDownMasterRBIBondDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DropDownMasterRBIBondBOImpl implements DropDownMasterRBIBondBO {

    @Autowired
    private DropDownMasterRBIBondDAO dropDownMasterRBIBondDAO;

    @Override
    public ResponseDataDTO addDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.addDetails(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.modifyDetails(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.modifySubmit(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.verifyDetails(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.verifySubmit(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO inquiry(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.inquiry(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO reject(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondDAO.reject(dropDownMasterDTO);
    }


}
