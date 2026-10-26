package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionCodesFacade {

    public ResponseEntity<ResponseDataDTO> create(final TransactionCodesDto transactionCodeDto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<ResponseDataDTO> delete(final TransactionCodesDto transactionCodesdto);

    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<TransactionCodesDtoAuditLogWrapper> getTransactionDetails(final Integer id);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<TransactionCodesDto>> getUnverifiedTransactions(final String fileName);

    public ResponseEntity<List<TransactionCodesDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> update(final TransactionCodesDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    public ResponseEntity<ResponseDataDTO> verify(final TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> add(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> modifySubmit(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> modifySelect(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> inquiry(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> verifyDeleteSelect(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> verifySubmit(TransactionCodesDto dto);

    ResponseEntity<ResponseDataDTO> deleteSubmit(TransactionCodesDto dto);
}
