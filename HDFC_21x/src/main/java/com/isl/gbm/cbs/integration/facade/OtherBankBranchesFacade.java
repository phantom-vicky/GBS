package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OtherBankBranchesFacade {

    public ResponseEntity<ResponseDataDTO> create(final OtherBankBranchesDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final OtherBankBranchesDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<OtherBankBranchesDtoAuditLogWrapper> getOtherBankBranchesDetails(final Integer branchId);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<OtherBankBranchesDto>> getUnverifiedRecords(final String fileName);

    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedRecords();

    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedBankBranchesList(final String bankCode, final String branchCode);

    public ResponseEntity<ResponseDataDTO> update(final OtherBankBranchesDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file)
            throws IOException;

    public ResponseEntity<ResponseDataDTO> verify(final OtherBankBranchesDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    ResponseEntity<ResponseDataDTO> add(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> inquiry(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> verifyDeleteSelect(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> verifySubmit(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> deleteSubmit(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> modifySubmit(OtherBankBranchesDto dto);

    ResponseEntity<ResponseDataDTO> modifySelect(OtherBankBranchesDto dto);
}
