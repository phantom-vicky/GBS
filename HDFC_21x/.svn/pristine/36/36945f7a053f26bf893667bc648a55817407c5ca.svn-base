package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OtherBankDetailsFacade {

    public ResponseEntity<ResponseDataDTO> deleteBanks(final String fileName,final String remarks);

    public ResponseEntity<ResponseDataDTO> deleteOtherBankRecords(final OtherBankDetailsDto otherBankDetailsDto);

    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<OtherBankDetailsDtoAuditLogWrapper> getOtherBankDetails(final Integer id);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<OtherBankDetailsDto>> getUnverifiedOtherBankDetails(final String fileName);

    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<OtherBankDetailsDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file) throws IOException;

    public ResponseEntity<ResponseDataDTO> saveEntryRecord(final OtherBankDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> update(final OtherBankDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> verifyOtherBankDetails(final String fileName);

    public ResponseEntity<ResponseDataDTO> verifyOtherBankEntryRecord(final OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> add(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modifySubmit(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modifySelect(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> inquiry(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifyDeleteSelect(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifySubmit(OtherBankDetailsDto dto);

    ResponseEntity<ResponseDataDTO> deleteSubmit(OtherBankDetailsDto dto);
}
