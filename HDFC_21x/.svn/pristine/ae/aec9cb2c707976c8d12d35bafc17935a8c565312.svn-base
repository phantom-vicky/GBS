package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDetailsFacade {

    public ResponseEntity<ResponseDataDTO> create(final GbmUserDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final GbmUserDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<GbmUserDetailsDto>> getUnverifiedInstruments(final String fileName);

    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<GbmUserDetailsDto> getUserWorkDetails(final Integer gbmUserDetailsId);

    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getVerifiedRecords();

    public ResponseEntity<List<GbmUserDetailsDto>> list();

    public ResponseEntity<ResponseDataDTO> update(final GbmUserDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file);

    public ResponseEntity<ResponseDataDTO> verify(final GbmUserDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    ResponseEntity<ResponseDataDTO> add(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modifySubmit(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modifySelect(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> inquiry(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifyDeleteSelect(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifySubmit(GbmUserDetailsDto dto);

    ResponseEntity<ResponseDataDTO> deleteSubmit(GbmUserDetailsDto dto);
}
