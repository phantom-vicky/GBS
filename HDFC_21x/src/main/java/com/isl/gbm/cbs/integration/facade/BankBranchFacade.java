package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BankBranchFacade {

    public ResponseEntity<ResponseDataDTO> create(final BankBranchDto bankBranchDto);

    public ResponseEntity<ResponseDataDTO> delete(final BankBranchDto bankBranchDto);

    public ResponseEntity<ResponseDataDTO> deleteBanks(final String fileName, final String remarks);

    public ResponseEntity<List<BankBranchDto>> getAllBankDetails();

    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getAllBankDetailsForInquiry();

    public ResponseEntity<BankBranchDtoAuditLogWrapper> getBankBranchDetails(final int id);

    public ResponseEntity<Map<Integer, String>> getBankBranchStatus();

    public ResponseEntity<List<BankBranchDto>> getSolIdandDescription();

    public ResponseEntity<List<BankBranchDto>> getUnverifiedBankDetails(final String fileName);

    public ResponseEntity<List<BankBranchDto>> getInquiryBankDetails(final String fileName);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<BankBranchDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file);

    public ResponseEntity<ResponseDataDTO> update(final BankBranchDto bankBranchDto);

    public ResponseEntity<ResponseDataDTO> verify(final BankBranchDto bankBranchDto);

    public ResponseEntity<ResponseDataDTO> verifyBanks(final String fileName);

    public ResponseDataDTO addDetails(BankBranchDto dto);

    public ResponseDataDTO inquiry(BankBranchDto dto);

    public ResponseDataDTO verDelDetails(BankBranchDto dto);

    public ResponseDataDTO modifyDetails(BankBranchDto dto);

    public ResponseDataDTO modify(BankBranchDto dto);

    public ResponseDataDTO verifySubmit(BankBranchDto dto);

    public ResponseDataDTO deleteSubmit(BankBranchDto dto);
    List<BankBranchMaster> getAllBranchId(BankBranchDto dto);

    public ResponseEntity<ResponseDataDTO> getUnverifiedBankDetailsDownload(CbsCommonDTo dto) throws IOException, InvalidFormatException;
}
