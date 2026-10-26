/**
 *
 */
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.service.CalendarEntriesService;
import com.isl.gbm.common.util.FileValidationUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Class CalendarEntriesValidator.
 *
 * @author harishbabu.t
 */
@Component
public class CalendarEntriesValidator {

    /** The Constant HEADERS. */
    private static final String HEADERS = "CAL_B2K_TYPE,CAL_B2K_ID,MMYYYY,DEL_FLG,HLDY_STR,STATUS,HLDY_REMARKS";

    /** The calendar entries service. */
    @Autowired
    private CalendarEntriesService calendarEntriesService;

    /**
     * Checks if is valid file.
     *
     * @param file
     *            the file
     * @param errorMessages
     *            the error messages
     * @return the boolean
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public Boolean isValidFile(final MultipartFile file, final Map<String, String> errorMessages) throws IOException {
        final String fileName = file.getOriginalFilename();
        final String extension = FilenameUtils.getExtension(fileName);
        final String contentType = file.getContentType();
        assert fileName != null;
        if (BooleanUtils.isFalse(StringUtils.equals(extension, "xls") || BooleanUtils.isFalse(StringUtils.equals("xls".toUpperCase(), FileValidationUtils.getFileTypeByUsingMagicNumbers(FileValidationUtils.convertBytesToHexaDecimal(file.getBytes())))))
                || BooleanUtils.isFalse(StringUtils.equals(contentType, "application/vnd.ms-excel")) || BooleanUtils.isTrue(FileValidationUtils.checkDoubleExtension(fileName))) {
            errorMessages.put("Invalid_File", "Please upload an valid excel file");
            return Boolean.FALSE;
        }

        if (file.isEmpty() || this.calendarEntriesService.fileNameExists(fileName)) {
            errorMessages.put("File_Exists", "File already exists");
            return Boolean.FALSE;
        }
        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 7);
    }

    /**
     * Checks if is valid record.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @param errorMessages
     *            the error messages
     * @return the boolean
     */
    public Boolean isValidRecord(final CalendarEntriesDto calendarEntriesDto, final Map<String, String> errorMessages) {
        if (this.calendarEntriesService.iscalendarEntryExists(calendarEntriesDto)) {
            errorMessages.put("Duplicate_Data", "calendar Entry Exists in DataBase");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Checks if is valid records.
     *
     * @param calendarEntriesDtoList
     *            the calendar entries dto list
     * @param errorMessages
     *            the error messages
     * @return the boolean
     */
    public Boolean isValidRecords(final List<CalendarEntriesDto> calendarEntriesDtoList,
                                  final Map<String, String> errorMessages) {
        if (CollectionUtils.isEmpty(calendarEntriesDtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }
        final List<CalendarEntriesDto> existingRecords = this.calendarEntriesService.getCalendarEntries();

        if (CollectionUtils.isEmpty(existingRecords)) {
            return Boolean.TRUE;
        }
        for (final CalendarEntriesDto calendarEntriesDto : calendarEntriesDtoList) {
            for (final CalendarEntriesDto ecistingDto : existingRecords) {
                if (StringUtils.equals(calendarEntriesDto.getCalendarCode(), ecistingDto.getCalendarCode())
                        && StringUtils.equals(calendarEntriesDto.getMonthYear(), ecistingDto.getMonthYear())) {
                    errorMessages.put("Duplicate_Data", "This month entry already exists in the database: "+calendarEntriesDto.getMonthYear());
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Sets the calendar entries service.
     *
     * @param calendarEntriesService
     *            the calendarEntriesService to set
     */
    public void setCalendarEntriesService(final CalendarEntriesService calendarEntriesService) {
        this.calendarEntriesService = calendarEntriesService;
    }

}
