package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.DropDownMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;

public interface DropDownMasterRBIBondFacade {

    public ResponseDataDTO addDetails(DropDownMasterDTO dropDownMasterDTO);

    public ResponseDataDTO modifyDetails(DropDownMasterDTO dropDownMasterDTO);
    public ResponseDataDTO modifySubmit(DropDownMasterDTO dropDownMasterDTO);
    public ResponseDataDTO verifyDetails(DropDownMasterDTO dropDownMasterDTO);
    public ResponseDataDTO verifySubmit(DropDownMasterDTO dropDownMasterDTO);
    public ResponseDataDTO inquiry(DropDownMasterDTO dropDownMasterDTO);
    public ResponseDataDTO reject(DropDownMasterDTO dropDownMasterDTO);

}
