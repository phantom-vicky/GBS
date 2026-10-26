/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.controller;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.common.audit.log.common.AuditLogRecordType;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.facade.AuditLogReportFacade;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Class AuditLogReportController.
 */
@RestController
@RequestMapping("/gbmultra/auditlog/report")
public class AuditLogReportController {

    /**
     * The audit log report facade.
     */
    @Autowired
    AuditLogReportFacade auditLogReportFacade;


    /**
     * Gets the audit log record types.
     *
     * @return the audit log record types
     */
    @GetMapping("/recordtypes")
    public ResponseEntity<List<AuditLogRecordType>> getAuditLogRecordTypes() {
        return new ResponseEntity<>(this.auditLogReportFacade.getAuditLogRecordTypes(), HttpStatus.OK);
    }

    /**
     * Gets the branch level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the branch level audit report details
     */
    @PostMapping("/branch")
    @RequiresPermissions("AUDIT_LOG_REPORT:AUDIT_REPORT_AT_DC_LEVEL_FOR_ANY_SOL:REPORT")
    public ResponseEntity<List<AuditLogReportDto>> getBranchLevelAuditReportDetails(
            @RequestBody final AuditLogReportParams auditLogReportParams) {
        // pop sp wise
        final List<AuditLogReportDto> dtoList = this.auditLogReportFacade
                .getBranchLevelAuditReportDetails(auditLogReportParams);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the dc parameterization details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the dc parameterization details
     */
    @PostMapping("/dcparameterization")
    @RequiresPermissions("AUDIT_LOG_REPORT:AUDIT_REPORT_DC_PARAMETERIZATIONS:REPORT")
    public ResponseEntity<List<AuditLogDto>> getDcParameterizationDetails(
            @RequestBody final AuditLogReportParams auditLogReportParams) {
        final List<AuditLogDto> dtoList = this.auditLogReportFacade.getDcParameterizationDetails(auditLogReportParams);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the gu level audit report details
     */
    @PostMapping("/gulevel/branch")
    @RequiresPermissions("AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_BRANCH_LEVEL:REPORT")
    public ResponseEntity<List<AuditLogDto>> getGuLevelAuditReportDetails(
            @RequestBody final AuditLogReportParams auditLogReportParams) {
        final List<AuditLogDto> dtoList = this.auditLogReportFacade.getGuLevelAuditReportDetails(auditLogReportParams);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the higher branch pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the higher branch pop sp sol ids
     */
    @GetMapping("/gulevel/higherbranch/solids")
    public ResponseEntity<List<SolDetailsIntegrationDto>> getHigherBranchPopSpSolIds(
            @RequestParam(name = "moduleDisplayName", required = true) final String moduleDisplayName) {
        // branches under a pop
        return new ResponseEntity<>(this.auditLogReportFacade.getHigherBranchPopSpSolIds(moduleDisplayName),
                HttpStatus.OK);
    }

    /**
     * Gets the higher gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the higher gu level audit report details
     */
    @PostMapping("/gulevel/higherbranch")
    @RequiresPermissions("AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_HIGHER_LEVEL_BRANCHES:REPORT")
    public ResponseEntity<List<AuditLogReportDto>> getHigherGuLevelAuditReportDetails(
            @RequestBody final AuditLogReportParams auditLogReportParams) {
        // report of branches coming under a pop
        final List<AuditLogReportDto> dtoList = this.auditLogReportFacade
                .getHigherGuLevelAuditReportDetails(auditLogReportParams);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    /**
     * Gets the operation modes.
     *
     * @return the operation modes
     */
    @GetMapping("/operationmodes")
    public ResponseEntity<List<OperationModes>> getOperationModes() {
        return new ResponseEntity<>(this.auditLogReportFacade.getOperationModes(), HttpStatus.OK);
    }

    /**
     * Gets the pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the pop sp sol ids
     */
    @GetMapping("/branch/popsp/solids")
    public ResponseEntity<List<SolDetailsIntegrationDto>> getPopSpSolIds(
            @RequestParam(name = "moduleDisplayName", required = true) final String moduleDisplayName) {
        return new ResponseEntity<>(this.auditLogReportFacade.getPopSpSolIds(moduleDisplayName), HttpStatus.OK);
    }

}
