package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OfficeAccountPlaceHolderFacade {

  //  public ResponseEntity<ResponseDataDTO> create(final OfficeAccountPlaceHolderDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final OfficeAccountPlaceHolderDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<OfficeAccountPlaceHolderDtoAuditLogWrapper> getOfficeAccountPlaceHolderDetails(final Integer officeId);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getUnverifiedRecords(final String fileName);

    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> update(final OfficeAccountPlaceHolderDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file)
            throws IOException;

    public ResponseEntity<ResponseDataDTO> verify(final OfficeAccountPlaceHolderDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto);

}
