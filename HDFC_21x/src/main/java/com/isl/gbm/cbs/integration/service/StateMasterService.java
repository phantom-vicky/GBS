package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface StateMasterService {
    ResponseDataDTO save(List<StateMasterDTO> stateMasterDTOList);

    ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO);
    ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO);

    ResponseDataDTO verifySubmit();

    ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO);

    ResponseDataDTO dataExistsCheck();

    ResponseEntity<ResponseDataDTO> downloadPincode()throws IOException, InvalidFormatException;








}
