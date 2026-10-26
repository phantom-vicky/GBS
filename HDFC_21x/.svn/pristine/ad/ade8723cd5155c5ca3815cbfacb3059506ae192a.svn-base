/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.InstrumentDetails;
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
import java.util.Map;

/**
 * The Class InstrumentDetailsManager.
 */
@Component
public class InstrumentDetailsManager {

    /**
     * The instrument details dao.
     */
    @Autowired
    private InstrumentDetailsDao instrumentDetailsDao;

    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Builds the instrument details.
     *
     * @param instrumentDetailsDto the instrument details dto
     * @return the instrument details
     */
    private InstrumentDetails buildInstrumentDetails(final InstrumentDetailsDto instrumentDetailsDto) {
        final InstrumentDetails details = new InstrumentDetails();
        details.setId(instrumentDetailsDto.getInstrumentId());
        if (!StringUtils.equalsIgnoreCase(instrumentDetailsDto.getAdditionalText1(), null)) {
            details.setAdditionalText1(instrumentDetailsDto.getAdditionalText1().toUpperCase());
        }
        if (!StringUtils.equalsIgnoreCase(instrumentDetailsDto.getAdditionalText2(), null)) {
            details.setAdditionalText2(instrumentDetailsDto.getAdditionalText2().toUpperCase());
        }
        if (!StringUtils.equalsIgnoreCase(instrumentDetailsDto.getAdditionalText3(), null)) {
            details.setAdditionalText3(instrumentDetailsDto.getAdditionalText3().toUpperCase());
        }
        details.setCreatedBy(instrumentDetailsDto.getCreatedBy());
        details.setCreatedDate(instrumentDetailsDto.getCreatedDate());
        details.setDrCrIndicator(instrumentDetailsDto.getDrCrIndicator());
        details.setFileName(instrumentDetailsDto.getFileName() != null ? instrumentDetailsDto.getFileName().toUpperCase() : instrumentDetailsDto.getFileName());
        details.setInstrumentType(instrumentDetailsDto.getInstrumentType());
        details.setModifiedBy(instrumentDetailsDto.getModifiedBy());
        details.setModifiedDate(instrumentDetailsDto.getModifiedDate());
        details.setSchemeCode(instrumentDetailsDto.getSchemeCode());
        details.setVerified(instrumentDetailsDto.isVerified());
        details.setVerifiedBy(instrumentDetailsDto.getVerifiedBy());
        details.setVerifiedDate(instrumentDetailsDto.getVerifiedDate());
        return details;
    }

    /**
     * Builds the instrument details dto.
     *
     * @param details the details
     * @return the instrument details dto
     */
    private InstrumentDetailsDto buildInstrumentDetailsDto(final InstrumentDetails details) {
        final InstrumentDetailsDto dto = new InstrumentDetailsDto();
        dto.setInstrumentId(details.getId());
        dto.setAdditionalText1(details.getAdditionalText1());
        dto.setAdditionalText2(details.getAdditionalText2());
        dto.setAdditionalText3(details.getAdditionalText3());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setDrCrIndicator(details.getDrCrIndicator());
        dto.setFileName(details.getFileName());
        dto.setInstrumentType(details.getInstrumentType());
        dto.setModifiedBy(details.getModifiedBy());
        dto.setModifiedDate(details.getModifiedDate());
        dto.setSchemeCode(details.getSchemeCode());
        dto.setVerified(details.isVerified());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        return dto;
    }

    /**
     * Builds the instrument details dto list.
     *
     * @param detailsList the details list
     * @return the list
     */
    private List<InstrumentDetailsDto> buildInstrumentDetailsDtoList(final List<InstrumentDetails> detailsList) {
        final List<InstrumentDetailsDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final InstrumentDetails details : detailsList) {
            dtoList.add(this.buildInstrumentDetailsDto(details));
        }
        return dtoList;
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(final InstrumentDetailsDto dto) {
        final InstrumentDetails details = this.buildInstrumentDetails(dto);
        details.setCreatedBy(UserAccountUtils.getCurrentUsername());
        details.setCreatedDate(new Date());
        this.instrumentDetailsDao.saveOrUpdate(details);
        this.auditLogger.createAuditLogEntries(details, details.getCreatedDate(), details.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.INSTRUMENT_DETAILS);
    }

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(final InstrumentDetailsDto dto) {
        final InstrumentDetails savedData = this.instrumentDetailsDao.getInstrumentDetails(dto.getInstrumentId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final InstrumentDetails details = this.buildInstrumentDetails(dto);

        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(details, user, currentDate);
            this.instrumentDetailsDao.deleteInstrument(details.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(details, user, currentDate);
            this.auditLogger.deleteGenModRecords(details, user, currentDate);
        }
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(final String fileName, final String remarks) {
       /* final List<InstrumentDetails> masterList = this.instrumentDetailsDao
                .getUnverifiedInstruments(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (InstrumentDetails master : masterList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
        return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName, CbsBulkUploadEnum.ID.getCbsCode(),remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.instrumentDetailsDao.fileNameExists(fileName);
    }

    /**
     * Gets the audit logger params.
     *
     * @param oldEntity   the old entity
     * @param newEntity   the new entity
     * @param currentUser the current user
     * @param currentDate the current date
     * @return the audit logger params
     */
    private AuditLoggerParams getAuditLoggerParams(final InstrumentDetails oldEntity, final InstrumentDetails newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.INSTRUMENT_DETAILS);
        return loggerParams;
    }

    /**
     * Gets the instrument details.
     *
     * @param instrumentId the instrument id
     * @return the instrument details
     */
    public InstrumentDetailsDtoAuditLogWrapper getInstrumentDetails(final Integer instrumentId) {
        final InstrumentDetails details = this.instrumentDetailsDao.getInstrumentDetails(instrumentId);
        final InstrumentDetailsDto dto = this.buildInstrumentDetailsDto(details);
        final InstrumentDetailsDtoAuditLogWrapper wrapper = new InstrumentDetailsDtoAuditLogWrapper();
        wrapper.setDto(dto);
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(details));
        return wrapper;
    }

    /**
     * Gets the instrument details.
     *
     * @param instrumentType the instrument type
     * @param schemeCode     the scheme code
     * @return the instrument details
     */
    public List<InstrumentDetailsDto> getInstrumentDetails(final String instrumentType, final String schemeCode) {
        return this.buildInstrumentDetailsDtoList(
                this.instrumentDetailsDao.getInstrumentDetails(instrumentType, schemeCode));
    }

    /**
     * Gets the instrument types.
     *
     * @return the instrument types
     */
    public Map<String, String> getInstrumentTypes() {
        return this.instrumentDetailsDao.getInstrumentTypes();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.instrumentDetailsDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.instrumentDetailsDao.getInquiryFiles();
    }

    /**
     * Gets the unverified instruments.
     *
     * @param fileName the file name
     * @return the unverified instruments
     */
    public List<InstrumentDetailsDto> getUnverifiedInstruments(final String fileName) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName, CbsBulkUploadEnum.ID.getCbsCode());
        return cbsIntegerationBulkUploadDto.getInstrumentDetailsDtoList();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<InstrumentDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<InstrumentDetails> recordList = this.instrumentDetailsDao.list();
        final List<InstrumentDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(recordList.size());
        for (final InstrumentDetails details : recordList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(details);
            if (BooleanUtils.isFalse(details.isVerified()) || (null != auditLogDto)) {
                final InstrumentDetailsDtoAuditLogWrapper wrapper = new InstrumentDetailsDtoAuditLogWrapper();
                wrapper.setDto(this.buildInstrumentDetailsDto(details));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<InstrumentDetailsDto> getVerifiedRecords() {
        final List<InstrumentDetails> recordList = this.instrumentDetailsDao.list();
        final List<InstrumentDetailsDto> dtoList = new ArrayList<>(recordList.size());
        for (final InstrumentDetails details : recordList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(details);
            if (BooleanUtils.isTrue(details.isVerified()) && (null == auditLogDto)) {
                dtoList.add((this.buildInstrumentDetailsDto(details)));
            }
        }
        return dtoList;
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<InstrumentDetailsDtoAuditLogWrapper> list() {
        final List<InstrumentDetails> detailsList = this.instrumentDetailsDao.list();
        final List<InstrumentDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(detailsList.size());
        for (final InstrumentDetails details : detailsList) {
            final InstrumentDetailsDtoAuditLogWrapper wrapper = new InstrumentDetailsDtoAuditLogWrapper();
            wrapper.setDto(this.buildInstrumentDetailsDto(details));
            wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(details));
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    /**
     * Sets the audit logger.
     *
     * @param auditLogger the new audit logger
     */
    public void setAuditLogger(final AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    /**
     * Sets the instrument details dao.
     *
     * @param instrumentDetailsDao the new instrument details dao
     */
    public void setInstrumentDetailsDao(final InstrumentDetailsDao instrumentDetailsDao) {
        this.instrumentDetailsDao = instrumentDetailsDao;
    }

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final InstrumentDetailsDto dto) {
        final InstrumentDetails savedData = this.instrumentDetailsDao.getInstrumentDetails(dto.getInstrumentId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final InstrumentDetails details = this.buildInstrumentDetails(dto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, details, currentUser, currentDate);
        details.setAuditable(savedData.isAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, details,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            details.setModifiedBy(currentUser);
            details.setModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.isVerified())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.instrumentDetailsDao.saveOrUpdate(details);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(details, currentUser, currentDate, savedData.isVerified());
            this.auditLogger.revertGenModRecords(details, currentUser, currentDate, savedData.isVerified());
        }
    }

    /**
     * Upload.
     *
     * @param instrumentDtoList the instrument dto list
     */
    public ResponseDataDTO upload(final List<InstrumentDetailsDto> instrumentDtoList) {
        final List<InstrumentDetails> instrumentDetailsList = new ArrayList<>(instrumentDtoList.size());
        for (final InstrumentDetailsDto instrumentdto : instrumentDtoList) {
            instrumentDetailsList.add(this.buildInstrumentDetails(instrumentdto));
        }
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setInstrumentDetailsDtoList(instrumentDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.ID.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(instrumentDtoList.size() > 0 ? instrumentDtoList.get(0).getFileName() : null);
        return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
        /*for (final InstrumentDetails instrumentDetails : instrumentDetailsList) {
            this.auditLogger.createAuditLogEntries(instrumentDetails, currentDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.INSTRUMENT_DETAILS);
        }*/
    }

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(final InstrumentDetailsDto dto) {
        final InstrumentDetails details = this.buildInstrumentDetails(dto);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        details.setId(dto.getInstrumentId());
        details.setVerified(Boolean.TRUE);
        details.setVerifiedDate(currentDate);
        details.setVerifiedBy(user);
        details.setAuditable(Boolean.TRUE);
        this.auditLogger.verifyAuditLog(user, currentDate, details);
        this.auditLogger.verifyGenModRecords(user, currentDate, details);
        this.instrumentDetailsDao.verify(details);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {
       /* final List<InstrumentDetails> bankBranchList = this.instrumentDetailsDao
                .getUnverifiedInstruments(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (InstrumentDetails master : bankBranchList) {
            this.auditLogger.verifyAuditLog(user, currentDate, master);
        }*/
        return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName, CbsBulkUploadEnum.ID.getCbsCode());
    }

    public ResponseDataDTO addDetails(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.addDetails(dto);
    }

    public ResponseDataDTO inquiry(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.inquiry(dto);
    }

    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.verDelDetails(dto);
    }

    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.modifyDetails(dto);
    }

    public ResponseDataDTO modify(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.modify(dto);
    }


    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.verifySubmit(dto);
    }


    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto) {
        return instrumentDetailsDao.deleteSubmit(dto);
    }

}
