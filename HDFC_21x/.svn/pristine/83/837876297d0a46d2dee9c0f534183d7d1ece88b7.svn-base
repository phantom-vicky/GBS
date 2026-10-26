/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.SchemeCodeDao;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.SchemeCodes;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class SchemeCodesManager.
 */
@Component
public class SchemeCodesManager {

    /**
     * The scheme code dao.
     */
    @Autowired
    private SchemeCodeDao schemeCodeDao;

    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Builds the audit log wrapper.
     *
     * @param schemeCode the scheme code
     * @return the scheme codes dto audit log wrapper
     */
    private SchemeCodesDtoAuditLogWrapper buildAuditLogWrapper(final SchemeCodes schemeCode) {
        final SchemeCodesDtoAuditLogWrapper wrapper = new SchemeCodesDtoAuditLogWrapper();
        wrapper.setDto(this.buildSchemeCodesDto(schemeCode));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(schemeCode));
        return wrapper;
    }

    /**
     * Builds the details.
     *
     * @param schemeCodesDtoList the scheme codes dto list
     */
    public ResponseDataDTO buildDetails(final List<SchemeCodesDto> schemeCodesDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto=new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setSchemeCodesDtoList(schemeCodesDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.SC.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(schemeCodesDtoList.size()>0?schemeCodesDtoList.get(0).getFileName():null);
       return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
        /*for (final SchemeCodes schemeCode : schemeCodesList) {
            this.auditLogger.createAuditLogEntries(schemeCode, createdDate, user, null, ModuleDisplayNames.CBS, FeatureNames.SCHEME_CODES);
        }*/
    }

    /**
     * Builds the schema codes.
     *
     * @param schemeCodesDto the scheme codes dto
     * @return the scheme codes
     */
    private SchemeCodes buildSchemaCodes(final SchemeCodesDto schemeCodesDto) {
        final SchemeCodes codes = new SchemeCodes();
        codes.setId(schemeCodesDto.getId());
        codes.setSchemeCode(schemeCodesDto.getSchemeCode());
        codes.setDescription(schemeCodesDto.getDescription());
        codes.setGlSubHeadCode(schemeCodesDto.getGlSubHeadCode());
        codes.setSchemeType(schemeCodesDto.getSchemeType());
        codes.setDeleteFlag(schemeCodesDto.isDeleteFlag());
        codes.setCreatedBy(schemeCodesDto.getCreatedBy());
        codes.setCreatedDate(schemeCodesDto.getCreatedDate());
        codes.setModifiedBy(schemeCodesDto.getModifiedBy());
        codes.setModifiedDate(schemeCodesDto.getModifiedDate());
        codes.setVerified(schemeCodesDto.isVerified());
        codes.setVerifiedBy(schemeCodesDto.getVerifiedBy());
        codes.setVerifiedDate(schemeCodesDto.getVerifiedDate());
        codes.setFileName(schemeCodesDto.getFileName() !=null? schemeCodesDto.getFileName().toUpperCase():schemeCodesDto.getFileName());
        codes.setAddText1(schemeCodesDto.getAddText1());
        codes.setAddText2(schemeCodesDto.getAddText2());
        codes.setAddText3(schemeCodesDto.getAddText3());
        return codes;
    }

    /**
     * Builds the scheme codes dto.
     *
     * @param details the details
     * @return the scheme codes dto
     */
    private SchemeCodesDto buildSchemeCodesDto(final SchemeCodes details) {
        final SchemeCodesDto dto = new SchemeCodesDto();
        dto.setId(details.getId());
        dto.setSchemeCode(details.getSchemeCode());
        dto.setDescription(details.getDescription());
        dto.setGlSubHeadCode(details.getGlSubHeadCode());
        dto.setSchemeType(details.getSchemeType());
        dto.setDeleteFlag(details.isDeleteFlag());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setModifiedBy(details.getModifiedBy());
        dto.setModifiedDate(details.getModifiedDate());
        dto.setVerified(details.isVerified());
        dto.setFileName(details.getFileName());
        dto.setAddText1(details.getAddText1());
        dto.setAddText2(details.getAddText2());
        dto.setAddText3(details.getAddText3());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        return dto;
    }

    /**
     * Builds the scheme codes dto list.
     *
     * @param detailsList the details list
     * @return the list
     */
    private List<SchemeCodesDto> buildSchemeCodesDtoList(final List<SchemeCodes> detailsList) {
        final List<SchemeCodesDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final SchemeCodes details : detailsList) {
            dtoList.add(this.buildSchemeCodesDto(details));
        }
        return dtoList;
    }

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(final SchemeCodesDto dto) {
        final SchemeCodes savedData = this.schemeCodeDao.getSchemeCodes(dto.getId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final SchemeCodes schemeCode = this.buildSchemaCodes(dto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(schemeCode, user, currentDate);
            this.schemeCodeDao.deleteSchemeCodesId(schemeCode.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(schemeCode, user, currentDate);
            this.auditLogger.deleteGenModRecords(schemeCode, user, currentDate);
            this.schemeCodeDao.delete(schemeCode);
        }
    }

    /**
     * Delete scheme codes.
     *
     * @param fileName the file name
     */

    // The DeleteSchemaCodes method for delete the records
    public ResponseDataDTO deleteSchemeCodes(final String fileName,final String remarks ) {
        /*final List<SchemeCodes> schemaCodesList = this.schemeCodeDao.getUnverifiedSchemeCodesDetails(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (SchemeCodes codes : schemaCodesList) {
            this.auditLogger.deleteAuditLogRecords(codes, user, currentDate);
        }*/

       return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.SC.getCbsCode(),remarks);

    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.schemeCodeDao.fileNameExists(fileName);
    }

    /**
     * Gets the all list.
     *
     * @return the all list
     */
    public List<SchemeCodesDtoAuditLogWrapper> getAllList() {
        final List<SchemeCodes> codesList = this.schemeCodeDao.getAllList();
        final List<SchemeCodesDtoAuditLogWrapper> wrapperList = new ArrayList<>(codesList.size());
        for (final SchemeCodes schemeCode : codesList) {
            wrapperList.add(this.buildAuditLogWrapper(schemeCode));
        }
        return wrapperList;
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
    private AuditLoggerParams getAuditLoggerParams(final SchemeCodes oldEntity, final SchemeCodes newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.SCHEME_CODES);
        return loggerParams;
    }

    /**
     * Gets the scheme codes.
     *
     * @param id the id
     * @return the scheme codes
     */
    public SchemeCodesDtoAuditLogWrapper getSchemeCodes(final Integer id) {
        return this.buildAuditLogWrapper(this.schemeCodeDao.getSchemeCodes(id));
    }

    /**
     * Gets the scheme codes.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @return the scheme codes
     */
    public List<SchemeCodesDto> getSchemeCodes(final String schemeType, final String schemeCode) {
        return this.buildSchemeCodesDtoList(this.schemeCodeDao.getSchemeCodes(schemeType, schemeCode));
    }

    /**
     * Gets the scheme codes list.
     *
     * @return the scheme codes list
     */
    public List<SchemeCodesDto> getSchemeCodesList() {
        final List<SchemeCodes> schemeCodesList = this.schemeCodeDao.getSchemeCodesList();
        final List<SchemeCodesDto> schemeCodesDtoList = new ArrayList<>();
        for (final SchemeCodes schemeCodes : schemeCodesList) {
            final SchemeCodesDto schemeCodeDto = new SchemeCodesDto();
            schemeCodeDto.setSchemeCode(schemeCodes.getSchemeCode());
            schemeCodeDto.setDescription(schemeCodes.getDescription());
            schemeCodeDto.setGlSubHeadCode(schemeCodes.getGlSubHeadCode());
            schemeCodeDto.setSchemeType(schemeCodes.getSchemeType());
            schemeCodeDto.setDeleteFlag(schemeCodes.isDeleteFlag());
            schemeCodeDto.setCreatedBy(schemeCodes.getCreatedBy());
            schemeCodeDto.setCreatedDate(schemeCodes.getCreatedDate());
            schemeCodeDto.setModifiedBy(schemeCodes.getModifiedBy());
            schemeCodeDto.setModifiedDate(schemeCodes.getModifiedDate());
            schemeCodeDto.setVerified(schemeCodes.isVerified());
            schemeCodeDto.setFileName(schemeCodes.getFileName());
            schemeCodeDto.setAddText1(schemeCodes.getAddText1());
            schemeCodeDto.setAddText2(schemeCodes.getAddText2());
            schemeCodeDto.setAddText3(schemeCodes.getAddText3());
            schemeCodesDtoList.add(schemeCodeDto);
        }
        return schemeCodesDtoList;
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {


        return this.schemeCodeDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {


        return this.schemeCodeDao.getInquiryFiles();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<SchemeCodesDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<SchemeCodes> codesList = this.schemeCodeDao.getAllList();
        final List<SchemeCodesDtoAuditLogWrapper> wrapperList = new ArrayList<>(codesList.size());
        for (final SchemeCodes schemeCode : codesList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(schemeCode);
            if (BooleanUtils.isFalse(schemeCode.isVerified()) || (null != auditLogDto)) {
                final SchemeCodesDtoAuditLogWrapper wrapper = new SchemeCodesDtoAuditLogWrapper();
                wrapper.setDto(this.buildSchemeCodesDto(schemeCode));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the unverified scheme codes details.
     *
     * @param fileName the file name
     * @return the unverified scheme codes details
     */
    public List<SchemeCodesDto> getUnverifiedSchemeCodesDetails(final String fileName) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.SC.getCbsCode());
        return cbsIntegerationBulkUploadDto.getSchemeCodesDtoList();
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<SchemeCodesDto> getVerifiedRecords() {
        return this.buildSchemeCodesDtoList(this.schemeCodeDao.getVerifiedRecords());
    }

    /**
     * Save details scheme codes.
     *
     * @param dto the dto
     */
   /* public void saveDetailsSchemeCodes(final SchemeCodesDto dto) {
        final SchemeCodes schemeCodes = this.buildSchemaCodes(dto);
        schemeCodes.setCreatedBy(UserAccountUtils.getCurrentUsername());
        schemeCodes.setCreatedDate(new Date());
        this.schemeCodeDao.saveOrUpdate(schemeCodes);
        this.auditLogger.createAuditLogEntries(schemeCodes, schemeCodes.getCreatedDate(), schemeCodes.getCreatedBy(),
                null, ModuleDisplayNames.CBS, FeatureNames.SCHEME_CODES);
    }*/

    /**
     * Sets the audit logger.
     *
     * @param auditLogger the new audit logger
     */
    public void setAuditLogger(final AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    /**
     * Sets the scheme code dao.
     *
     * @param schemeCodeDao the new scheme code dao
     */
    public void setSchemeCodeDao(final SchemeCodeDao schemeCodeDao) {
        this.schemeCodeDao = schemeCodeDao;
    }

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final SchemeCodesDto dto) {
        final SchemeCodes savedData = this.schemeCodeDao.getSchemeCodes(dto.getId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final SchemeCodes schemeCode = this.buildSchemaCodes(dto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, schemeCode, currentUser,
                currentDate);
        schemeCode.setAuditable(savedData.getAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, schemeCode,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            schemeCode.setModifiedBy(currentUser);
            schemeCode.setModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.isVerified())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.schemeCodeDao.saveOrUpdate(schemeCode);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(schemeCode, currentUser, currentDate, savedData.isVerified());
            this.auditLogger.revertGenModRecords(schemeCode, currentUser, currentDate, savedData.isVerified());
        }
    }

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(final SchemeCodesDto dto) {
        final SchemeCodes codes = this.buildSchemaCodes(dto);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        codes.setId(dto.getId());
        codes.setVerified(Boolean.TRUE);
        codes.setVerifiedDate(currentDate);
        codes.setVerifiedBy(user);
        codes.setAuditable(Boolean.TRUE);
        this.auditLogger.verifyAuditLog(user, currentDate, codes);
        this.auditLogger.verifyGenModRecords(user, currentDate, codes);
        this.schemeCodeDao.verify(codes);
    }

    /**
     * Verify scheme codes.
     *
     * @param fileName the file name
     */
    // The VerifySchemeCodes method for verify Data
    public ResponseDataDTO verifySchemeCodes(final String fileName) {

      /*  final List<SchemeCodes> schemaCodesList = this.schemeCodeDao.getUnverifiedSchemeCodesDetails(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (SchemeCodes codes : schemaCodesList) {
            this.auditLogger.verifyAuditLog(user, currentDate, codes);
        }
*/
        return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.SC.getCbsCode());
    }

    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto) {
        return schemeCodeDao.saveDetailsSchemeCodes(dto);
    }
    public ResponseDataDTO inquiry(SchemeCodesDto dto) {
        return schemeCodeDao.inquiry(dto);
    }

    public ResponseDataDTO verDelDetails(SchemeCodesDto dto) {
        return schemeCodeDao.verDelDetails(dto);
    }

    public ResponseDataDTO modifyDetails(SchemeCodesDto dto) {
        return schemeCodeDao.modifyDetails(dto);
    }

    public ResponseDataDTO modify(SchemeCodesDto dto) {
        return schemeCodeDao.modify(dto);
    }


    public ResponseDataDTO verifySubmit(SchemeCodesDto dto) {
        return schemeCodeDao.verifySubmit(dto);
    }


    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto) {
        return schemeCodeDao.deleteSubmit(dto);
    }

}
