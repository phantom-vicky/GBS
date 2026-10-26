package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SchemeCodesFacade {

    public ResponseEntity<ResponseDataDTO> delete(final SchemeCodesDto dto);

    public ResponseEntity<ResponseDataDTO> deleteSchemeCodes(final String fileName,final String remarks);

    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<SchemeCodesDtoAuditLogWrapper> getSchemeCodeById(final Integer id);

    public ResponseEntity<List<SchemeCodesDto>> getUnverifiedBankDetails(final String fileName);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();


    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<SchemeCodesDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file);

   // public ResponseEntity<ResponseDataDTO> saveDetailsSchemeCodes(final SchemeCodesDto dto);

    public ResponseEntity<ResponseDataDTO> update(final SchemeCodesDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final SchemeCodesDto dto);

    public ResponseEntity<ResponseDataDTO> verifySchemeCodes(final String fileName);

    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto);

    public ResponseDataDTO inquiry(SchemeCodesDto dto);

    public ResponseDataDTO verDelDetails(SchemeCodesDto dto);

    public ResponseDataDTO modifyDetails(SchemeCodesDto dto);

    public ResponseDataDTO modify(SchemeCodesDto dto);

    public ResponseDataDTO verifySubmit(SchemeCodesDto dto);

    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto);
}
