package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CalendarEntriesFacade {

    public ResponseEntity<ResponseDataDTO> create(final CalendarEntriesDto calendarEntriesDto);

    public ResponseEntity<ResponseDataDTO> delete(final CalendarEntriesDto calendarEntriesDto);

    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks);

    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getAllEntries();

    public ResponseEntity<CalendarEntriesDtoAuditLogWrapper> getCalendarEntry(final Integer calendarId);

    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getUnverifiedEntries();

    public ResponseEntity<List<CalendarEntriesDto>> getUnverifiedEntries(final String fileName);

    public ResponseEntity<List<String>> getUnverifiedFiles();

    public ResponseEntity<List<String>> getInquiryFiles();

    public ResponseEntity<ResponseDataDTO> saveEntries(final MultipartFile file);

    public ResponseEntity<ResponseDataDTO> update(final CalendarEntriesDto calendarEntriesDto);

    public ResponseEntity<ResponseDataDTO> verify(final CalendarEntriesDto calendarEntriesDto);

    public ResponseEntity<ResponseDataDTO> verify(final String fileName);

    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto);
}
