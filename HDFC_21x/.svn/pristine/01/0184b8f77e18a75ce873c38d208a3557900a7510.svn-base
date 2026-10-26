package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dao.StateMasterDao;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StateFacade {

    public ResponseDataDTO save(final MultipartFile file);
    public ResponseDataDTO getInquiryDetails(StateMasterDTO stateMasterDTO);
    public ResponseDataDTO getVerifyDetails(StateMasterDTO stateMasterDTO);
    public ResponseDataDTO verifySubmit();
    public ResponseDataDTO deleteSubmit(StateMasterDTO stateMasterDTO);
    public ResponseDataDTO dataExistsCheck();
    public ResponseEntity<ResponseDataDTO> downloadPincode()throws IOException, InvalidFormatException;


}
