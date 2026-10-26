package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface InstrumentDetailsFacade {

    public ResponseEntity<ResponseDataDTO> create(final InstrumentDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final InstrumentDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getAllRecords();

    public ResponseEntity<InstrumentDetailsDtoAuditLogWrapper> getInstrumentDetails(final Integer instrumentId);

    public ResponseEntity<Map<String, String>> getInstrumentTypes();

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<List<InstrumentDetailsDto>> getUnverifiedInstruments(final String fileName);

    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getUnverifiedRecords();

    public ResponseEntity<List<InstrumentDetailsDto>> getVerifiedRecords();

    public ResponseEntity<ResponseDataDTO> update(final InstrumentDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file) throws IOException;

    public ResponseEntity<ResponseDataDTO> verify(final InstrumentDetailsDto dto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    public ResponseDataDTO addDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO inquiry(InstrumentDetailsDto dto);

    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modify(InstrumentDetailsDto dto);

    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto);

    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto);
}
