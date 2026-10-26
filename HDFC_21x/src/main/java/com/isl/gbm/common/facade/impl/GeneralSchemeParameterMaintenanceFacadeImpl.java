package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import com.isl.gbm.common.facade.GeneralSchemeParameterMaintenanceFacade;
import com.isl.gbm.common.service.GeneralSchemeParameterMaintenanceService;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.utils.APIResponseBuilder;
import com.isl.gbm.common.validator.GeneralSchemeParameterMaintenanceValidator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GeneralSchemeParameterMaintenanceFacadeImpl implements GeneralSchemeParameterMaintenanceFacade {


    @Autowired
    private GeneralSchemeParameterMaintenanceService generalSchemeParameterMaintenanceService;

    @Autowired
    private GeneralSchemeParameterMaintenanceValidator generalSchemeParameterMaintenanceValidator;

    @Override
    @RequiresPermissions("DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:ENQUIRY")
    public ResponseEntity<List<GbmSchemeParameterAuditLogWrapper>> getGeneralSchemeParameterMaintenance(
            @PathVariable final String schemeType) {

        final List<GbmSchemeParameterAuditLogWrapper> gbmSchemeParameterMasterDtoList = this.generalSchemeParameterMaintenanceService
                .getGeneralSchemeParameterMaintenance(schemeType);
        return new ResponseEntity<>(gbmSchemeParameterMasterDtoList, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:ENQUIRY")
    public ResponseEntity<Map<String, Boolean>> getInternetEnabledForScheme(@PathVariable final String schemeType) {

        final Boolean enabled = this.generalSchemeParameterMaintenanceService.getInternetEnabledForScheme(schemeType);
        final Map<String, Boolean> result = (enabled == true) ? Collections.singletonMap("IsInetEnabled", true)
                : Collections.singletonMap("IsInetEnabled", false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<NpsGbmSubCodeMasterDto>> getSchemeTypes() {

        final List<NpsGbmSubCodeMasterDto> npsGbmSubCodeMasterDtoList = this.generalSchemeParameterMaintenanceService
                .getSchemeTypes();
        return new ResponseEntity<>(npsGbmSubCodeMasterDtoList, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:MODIFY")
    public ResponseEntity<ResponseDataDTO> updateGeneralSchemeParameterMaintenance(
            @RequestBody @Validated final List<GbmSchemeParameterAuditLogWrapper> dtoList) {

        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils
                .isFalse(this.generalSchemeParameterMaintenanceValidator.validateRecord(dtoList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse(CommonConstants.VALIDATION_FAILED_MESSAGE, errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.generalSchemeParameterMaintenanceService.updateGeneralSchemeParameterMaintenance(dtoList);
        return APIResponseBuilder.buildSuccessResponse(CommonConstants.SUCCESS_CONST, null);
    }

    @Override
    @RequiresPermissions("DC:GENERAL_SCHEME_PARAMETER_MAINTENANCE:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyGeneralSchemeParameterMaintenance(
            @RequestBody @Validated final List<GbmSchemeParameterAuditLogWrapper> dtoList) {

        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils
                .isFalse(this.generalSchemeParameterMaintenanceValidator.validateRecord(dtoList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse(CommonConstants.VALIDATION_FAILED_MESSAGE, errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.generalSchemeParameterMaintenanceService.verifyGeneralSchemeParameterMaintenance(dtoList);
        return APIResponseBuilder.buildSuccessResponse(CommonConstants.SUCCESS_CONST, null);
    }


}




