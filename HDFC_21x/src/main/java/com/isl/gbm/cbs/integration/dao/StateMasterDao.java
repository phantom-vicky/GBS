package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

public interface StateMasterDao {

    public ResponseDataDTO save(List<StateMasterDTO> stateMasterDTOList);

    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO);
    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO);

    public ResponseDataDTO verifySubmit();
    public ResponseDataDTO dataExistsCheck();

    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO);


}
