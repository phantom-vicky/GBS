package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.DropDownMasterRBIBondBO;
import com.isl.gbm.rbibond.service.DropDownMasterRBIBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropDownMasterRBIBondServiceImpl implements DropDownMasterRBIBondService {

    @Autowired
    DropDownMasterRBIBondBO dropDownMasterRBIBondBO;

    @Override
    public ResponseDataDTO addDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.addDetails(dropDownMasterDTO);
    }


    @Override
    public ResponseDataDTO modifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.modifyDetails(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.modifySubmit(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.verifyDetails(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.verifySubmit(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO inquiry(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.inquiry(dropDownMasterDTO);
    }

    @Override
    public ResponseDataDTO reject(DropDownMasterDTO dropDownMasterDTO) {
        return dropDownMasterRBIBondBO.reject(dropDownMasterDTO);
    }

}
