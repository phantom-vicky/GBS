package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.StateMasterDao;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StateMasterManager {
    @Autowired
    StateMasterDao stateMasterDao;

    public ResponseDataDTO save(final List<StateMasterDTO> stateMasterDTOList) {
     return this.stateMasterDao.save(stateMasterDTOList);
    }

    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO) {

        ResponseDataDTO responseDataDTO=null;
        responseDataDTO=this.stateMasterDao.getInquiryDetails(stateMasterDTO);
        return responseDataDTO;
    }

    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO) {

        ResponseDataDTO responseDataDTO=null;
        responseDataDTO=this.stateMasterDao.getVerifyDetails(stateMasterDTO);
        return responseDataDTO;
    }

    public ResponseDataDTO verifySubmit() {

        ResponseDataDTO responseDataDTO=null;
        responseDataDTO=this.stateMasterDao.verifySubmit();
        return responseDataDTO;
    }

    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO) {

        return stateMasterDao.deleteSubmit(stateMasterDTO);
    }

    public ResponseDataDTO dataExistsCheck() {

        ResponseDataDTO responseDataDTO=null;
        responseDataDTO=this.stateMasterDao.dataExistsCheck();
        return responseDataDTO;
    }







}
