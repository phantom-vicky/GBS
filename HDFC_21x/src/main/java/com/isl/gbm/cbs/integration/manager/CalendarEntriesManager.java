/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CalendarEntriesDao;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.CalendarEntries;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class CalendarEntriesManager.
 *
 * @author harishbabu.t
 */
@Component
public class CalendarEntriesManager {

    /** The Calendar entries dao. */
    @Autowired
    private CalendarEntriesDao calendarEntriesDao;

    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;
    @Autowired
    private AuditLogger auditLogger;

    private CalendarEntriesDtoAuditLogWrapper buildAuditLogWrapper(final CalendarEntries calendarEntries) {
        final CalendarEntriesDtoAuditLogWrapper wrapper = new CalendarEntriesDtoAuditLogWrapper();
        wrapper.setDto(this.getCalendarEntry(calendarEntries));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(calendarEntries));
        return wrapper;
    }

    /**
     * Builds the calendar entries.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return the calendar entries
     */
    private CalendarEntries buildCalendarEntries(final CalendarEntriesDto calendarEntriesDto) {

        final CalendarEntries calendarEntries = new CalendarEntries();
        calendarEntries.setId(calendarEntries.getId());
        calendarEntries.setAdditionalText1(calendarEntriesDto.getAdditionalText1());
        calendarEntries.setAdditionalText2(calendarEntriesDto.getAdditionalText2());
        calendarEntries.setAdditionalText3(calendarEntriesDto.getAdditionalText3());
        calendarEntries.setCalendarCode(calendarEntriesDto.getCalendarCode());
        calendarEntries.setCalendarType(calendarEntriesDto.getCalendarType());
        calendarEntries.setCreatedBy(calendarEntriesDto.getCreatedBy());
        calendarEntries.setCreatedDate(new Date());
        calendarEntries.setDeleteFlag(calendarEntriesDto.isDeleteFlag());
        calendarEntries.setFileName(calendarEntriesDto.getFileName() !=null ? calendarEntriesDto.getFileName().toUpperCase():calendarEntriesDto.getFileName());
        calendarEntries.setHolidayString(calendarEntriesDto.getHolidayString());
        calendarEntries.setId(calendarEntriesDto.getId());
        calendarEntries.setLastModifiedBy(calendarEntriesDto.getLastModifiedBy());
        calendarEntries.setLastModifiedDate(calendarEntriesDto.getLastModifiedDate());
        calendarEntries.setMonthYear(calendarEntriesDto.getMonthYear());
        calendarEntries.setStatus(calendarEntriesDto.getStatus());
        calendarEntries.setVerifiedBy(calendarEntriesDto.getVerifiedBy());
        calendarEntries.setVerifiedDate(calendarEntriesDto.getVerifiedDate());
        calendarEntries.setVerifiedFlag(calendarEntriesDto.isVerifiedFlag());
        return calendarEntries;
    }

    /**
     * Creates the.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    public void create(final CalendarEntriesDto calendarEntriesDto) {
        CalendarEntries master = this.buildCalendarEntries(calendarEntriesDto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        calendarEntriesDao.create(master);

        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.CBS_MAINTENANCE_OF_CALENDAR_ENTRIES);
    }

    /**
     * Delete.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    public void delete(final CalendarEntriesDto calendarEntriesDto) {


        final CalendarEntries savedData = this.calendarEntriesDao.getCalendarEntry(calendarEntriesDto.getId());

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final CalendarEntries master = this.buildCalendarEntries(calendarEntriesDto);
        if (BooleanUtils.isNotTrue(savedData.isVerifiedFlag())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.calendarEntriesDao.deleteId(calendarEntriesDto.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
            this.calendarEntriesDao.delete(calendarEntriesDto.getId());
        }
        //calendarEntriesDto.setDeleteFlag(Boolean.TRUE);
        //this.calendarEntriesDao.create(this.buildCalendarEntries(calendarEntriesDto));
    }

    /**
     * Delete.
     *
     * @param fileName
     *            the file name
     */
    public ResponseDataDTO delete(final String fileName,final String remarks) {
  /*      final List<CalendarEntries> calendarEntriesList = this.calendarEntriesDao.getUnverifiedEntries(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (CalendarEntries master : calendarEntriesList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
       return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.MOCE.getCbsCode(),remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName
     *            the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {

        return this.calendarEntriesDao.fileNameExists(fileName);
    }

    /**
     * Gets the all entries.
     *
     * @return the all entries
     */
    public List<CalendarEntriesDtoAuditLogWrapper> getAllEntries() {
        final List<CalendarEntries> calendarEntriesList = this.calendarEntriesDao.getAllEntries();

        final List<CalendarEntriesDtoAuditLogWrapper> wrapperList = new ArrayList<>(calendarEntriesList.size());
        for (final CalendarEntries master : calendarEntriesList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isTrue(master.isAuditable())) {
                final CalendarEntriesDtoAuditLogWrapper wrapper = new CalendarEntriesDtoAuditLogWrapper();
                wrapper.setDto(this.getCalendarEntry(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            } else if (StringUtils.isBlank(master.getFileName())) {
                final CalendarEntriesDtoAuditLogWrapper wrapper = new CalendarEntriesDtoAuditLogWrapper();
                wrapper.setDto(this.getCalendarEntry(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the calendar entries.
     *
     * @return the calendar entries
     */
    public List<CalendarEntriesDto> getCalendarEntries() {

        final List<CalendarEntries> calendarEntriesList = this.calendarEntriesDao.getCalendarEntries();
        return this.getCalendarEntriesDtoList(calendarEntriesList);
    }

    /**
     * Gets the calendar entries dto list.
     *
     * @param calendarEntriesList
     *            the calendar entries list
     * @return the calendar entries dto list
     */
    private List<CalendarEntriesDto> getCalendarEntriesDtoList(final List<CalendarEntries> calendarEntriesList) {

        final List<CalendarEntriesDto> resultList = new ArrayList<>(CollectionUtils.size(calendarEntriesList));
        for (final CalendarEntries calendarEntrie : calendarEntriesList) {
            final CalendarEntriesDto calendarEntriesDto = new CalendarEntriesDto();
            calendarEntriesDto.setId(calendarEntrie.getId());
            calendarEntriesDto.setAdditionalText1(calendarEntrie.getAdditionalText1());
            calendarEntriesDto.setAdditionalText2(calendarEntrie.getAdditionalText2());
            calendarEntriesDto.setAdditionalText3(calendarEntrie.getAdditionalText3());
            calendarEntriesDto.setCalendarCode(calendarEntrie.getCalendarCode());
            calendarEntriesDto.setCalendarType(calendarEntrie.getCalendarType());
            calendarEntriesDto.setDeleteFlag(calendarEntrie.isDeleteFlag());
            calendarEntriesDto.setFileName(calendarEntrie.getFileName());
            calendarEntriesDto.setHolidayString(calendarEntrie.getHolidayString());
            calendarEntriesDto.setMonthYear(calendarEntrie.getMonthYear());
            calendarEntriesDto.setStatus(calendarEntrie.getStatus());
            calendarEntriesDto.setCreatedBy(calendarEntrie.getCreatedBy());
            calendarEntriesDto.setCreatedDate(calendarEntrie.getCreatedDate());
            calendarEntriesDto.setVerifiedBy(calendarEntrie.getVerifiedBy());
            calendarEntriesDto.setVerifiedDate(calendarEntrie.getVerifiedDate());
            calendarEntriesDto.setLastModifiedBy(calendarEntrie.getLastModifiedBy());
            calendarEntriesDto.setLastModifiedDate(calendarEntrie.getLastModifiedDate());
            calendarEntriesDto.setVerifiedFlag(calendarEntrie.isVerifiedFlag());
            resultList.add(calendarEntriesDto);
        }
        return resultList;
    }

    /**
     * Gets the calendar entry.
     *
     * @param calendarEntries
     *            the calendar entries
     * @return the calendar entry
     */
    private CalendarEntriesDto getCalendarEntry(final CalendarEntries calendarEntries) {
        final CalendarEntriesDto calendarEntriesDto = new CalendarEntriesDto();
        calendarEntriesDto.setId(calendarEntries.getId());
        calendarEntriesDto.setAdditionalText1(calendarEntries.getAdditionalText1());
        calendarEntriesDto.setAdditionalText2(calendarEntries.getAdditionalText2());
        calendarEntriesDto.setAdditionalText3(calendarEntries.getAdditionalText3());
        calendarEntriesDto.setCalendarCode(calendarEntries.getCalendarCode());
        calendarEntriesDto.setCalendarType(calendarEntries.getCalendarType());
        calendarEntriesDto.setDeleteFlag(calendarEntries.isDeleteFlag());
        calendarEntriesDto.setFileName(calendarEntries.getFileName());
        calendarEntriesDto.setHolidayString(calendarEntries.getHolidayString());
        calendarEntriesDto.setMonthYear(calendarEntries.getMonthYear());
        calendarEntriesDto.setStatus(calendarEntries.getStatus());
        calendarEntriesDto.setCreatedBy(calendarEntries.getCreatedBy());
        calendarEntriesDto.setCreatedDate(calendarEntries.getCreatedDate());
        calendarEntriesDto.setVerifiedBy(calendarEntries.getVerifiedBy());
        calendarEntriesDto.setVerifiedDate(calendarEntries.getVerifiedDate());
        calendarEntriesDto.setLastModifiedBy(calendarEntries.getLastModifiedBy());
        calendarEntriesDto.setLastModifiedDate(calendarEntries.getLastModifiedDate());
        calendarEntriesDto.setVerifiedFlag(calendarEntries.isVerifiedFlag());
        return calendarEntriesDto;
    }

    /**
     * Gets the calendar entry.
     *
     * @param id
     *            the id
     * @return the calendar entry
     */
    public CalendarEntriesDtoAuditLogWrapper getCalendarEntry(final Integer id) {
        //final CalendarEntries calendarEntries = this.calendarEntriesDao.getCalendarEntry(id);
        return this.buildAuditLogWrapper(this.calendarEntriesDao.getCalendarEntry(id));
    }

    /**
     * Gets the unverified entries.
     *
     * @return the unverified entries
     */
    public List<CalendarEntriesDtoAuditLogWrapper> getUnverifiedEntries() {
        final List<CalendarEntries> calendarEntriesList = this.calendarEntriesDao.getAllEntries();

        final List<CalendarEntriesDtoAuditLogWrapper> wrapperList = new ArrayList<>(calendarEntriesList.size());
        for (final CalendarEntries master : calendarEntriesList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerifiedFlag()) || (null != auditLogDto)) {

                final CalendarEntriesDtoAuditLogWrapper wrapper = new CalendarEntriesDtoAuditLogWrapper();
                wrapper.setDto(this.getCalendarEntry(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }

			/*else if(StringUtils.isBlank(master.getFileName())){
				final CalendarEntriesDtoAuditLogWrapper wrapper = new CalendarEntriesDtoAuditLogWrapper();
				wrapper.setDto(this.getCalendarEntry(master));
				wrapper.setAuditLogDto(auditLogDto);
				wrapperList.add(wrapper);
			}*/
        }
        return wrapperList;
    }

    /**
     * Gets the unverified entries.
     *
     * @param fileName
     *            the file name
     * @return the unverified entries
     */
    public List<CalendarEntriesDto> getUnverifiedEntries(final String fileName) {

      CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.MOCE.getCbsCode());
        return cbsIntegerationBulkUploadDto.getCalendarEntriesDtoList();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {

        return this.calendarEntriesDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {

        return this.calendarEntriesDao.getInquiryFiles();
    }


    /**
     * Checks if is calendar entry exists.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return true, if is calendar entry exists
     */
    public boolean iscalendarEntryExists(final CalendarEntriesDto calendarEntriesDto) {

        return this.calendarEntriesDao.iscalendarEntryExists(calendarEntriesDto.getCalendarCode(),
                calendarEntriesDto.getMonthYear());
    }

    /**
     * Save entries.
     *
     * @param calendarEntriesDtoList
     *            the calendar entries dto list
     */
    public ResponseDataDTO saveEntries(final List<CalendarEntriesDto> calendarEntriesDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setCalendarEntriesDtoList(calendarEntriesDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.MOCE.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(calendarEntriesDtoList.size() > 0 ? calendarEntriesDtoList.get(0).getFileName() : null);
        return  this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);

       /* for (final CalendarEntries master : calendarEntriesList) {
            this.auditLogger.createAuditLogEntries(master, createdDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_MAINTENANCE_OF_CALENDAR_ENTRIES);
        }*/
    }


    /**
     * Update.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    public void update(final CalendarEntriesDto calendarEntriesDto) {
        //calendarEntriesDto.setVerifiedFlag(Boolean.FALSE);
        //calendarEntriesDto.setLastModifiedBy(null);
        //calendarEntriesDto.setLastModifiedDate(new Date());
        //this.calendarEntriesDao.create(this.buildCalendarEntries(calendarEntriesDto));

        final CalendarEntries savedData = this.calendarEntriesDao.getCalendarEntry(calendarEntriesDto.getId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        calendarEntriesDto.setVerifiedFlag(Boolean.FALSE);
        calendarEntriesDto.setLastModifiedBy(currentUser);
        calendarEntriesDto.setLastModifiedDate(currentDate);
        final CalendarEntries master = this.buildCalendarEntries(calendarEntriesDto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
        master.setAuditable(savedData.isAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            master.setLastModifiedBy(currentUser);
            master.setLastModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.isVerifiedFlag())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.calendarEntriesDao.update(master);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(master, currentUser, currentDate, savedData.isVerifiedFlag());
            this.auditLogger.revertGenModRecords(master, currentUser, currentDate, savedData.isVerifiedFlag());
        }
    }


    private AuditLoggerParams getAuditLoggerParams(final CalendarEntries oldEntity,
                                                   final CalendarEntries newEntity, final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_MAINTENANCE_OF_CALENDAR_ENTRIES);
        return loggerParams;
    }

    /**
     * Verify.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    public void verify(final CalendarEntriesDto calendarEntriesDto) {

        final CalendarEntries master = new CalendarEntries();
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(calendarEntriesDto.getId());
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.calendarEntriesDao.verify(calendarEntriesDto.getId());
        //calendarEntriesDto.setVerifiedFlag(Boolean.TRUE);
        //calendarEntriesDto.setVerifiedDate(new Date());
        //calendarEntriesDto.setVerifiedBy(null);
        //this.calendarEntriesDao.create(this.buildCalendarEntries(calendarEntriesDto));
    }

    /**
     * Verify.
     *
     * @param fileName
     *            the file name
     */
    public ResponseDataDTO verify(final String fileName) {

      /*  final List<CalendarEntries> calendarEntriesList = this.calendarEntriesDao.getUnverifiedEntries(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (CalendarEntries master : calendarEntriesList) {
            this.auditLogger.verifyAuditLog(user, currentDate, master);
        }
*/
        return  this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.MOCE.getCbsCode());
    }

    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.addDetails(calendarEntriesDto);
    }


    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.inquiry(calendarEntriesDto);
    }

    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.verifyDetails(calendarEntriesDto);
    }

    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.deleteDetails(calendarEntriesDto);
    }

    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.getVerifyDetails(calendarEntriesDto);
    }

    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.getModifyDetails(calendarEntriesDto);
    }

    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesDao.modifyDetails(calendarEntriesDto);
    }

}
