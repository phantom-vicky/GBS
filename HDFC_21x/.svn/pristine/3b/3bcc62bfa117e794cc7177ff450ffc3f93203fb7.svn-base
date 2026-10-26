package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BranchIfscDetailsFacade {

    public ResponseEntity<ResponseDataDTO> create(final BranchIfscDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final BranchIfscDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<BranchIfscDetailsDtoAuditLogWrapper> getBranchIfscDetails(final Integer branchDetailsId);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<BranchIfscDetailsDto>> getUnverifiedInstruments(final String fileName);

    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<BranchIfscDetailsDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> update(final BranchIfscDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file);

    public ResponseEntity<ResponseDataDTO> verify(final BranchIfscDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    ResponseEntity<ResponseDataDTO> add(BranchIfscDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modify(BranchIfscDetailsDto dto);

    ResponseEntity<ResponseDataDTO> modifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseEntity<ResponseDataDTO> inquiry(BranchIfscDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifyDeleteSelect(BranchIfscDetailsDto dto);

    ResponseEntity<ResponseDataDTO> verifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseEntity<ResponseDataDTO> deleteSubmit(CommonGenericTableDto commonGenericTableDto);
}
