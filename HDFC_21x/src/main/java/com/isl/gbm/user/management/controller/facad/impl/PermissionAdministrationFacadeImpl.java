package com.isl.gbm.user.management.controller.facad.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.constants.UserMgmtMessages;
import com.isl.gbm.user.management.controller.facad.PermissionAdministrationFacade;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.service.PermissionAdministrationService;
import com.isl.gbm.user.management.util.APIResponseBuilder;
import com.isl.gbm.user.management.validator.PermissionAdministrationValidator;
import com.isl.gbm.user.management.validator.UserManagementCommonValidator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionAdministrationFacadeImpl implements PermissionAdministrationFacade {
    /**
     * The user permission service.
     */
    @Autowired
    private PermissionAdministrationService permissionAdministrationService;

    /**
     * The user permission validator.
     */
    @Autowired
    private PermissionAdministrationValidator permissionAdministrationServiceValidator;

    /**
     * The user management common validator.
     */
    @Autowired
    private UserManagementCommonValidator userManagementCommonValidator;

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_PERMISSION_ADD)
    public ResponseEntity<ResponseDataDTO> createPermission(PermissionDto permissionDto) {
        if (BooleanUtils.isFalse(this.userManagementCommonValidator.isValidFeatureId(permissionDto.getFeatureId()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_INVALID_FEATURE_ID, null,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (BooleanUtils.isTrue(
                this.permissionAdministrationServiceValidator.isPermissionExists(permissionDto.getDisplayName()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_EXISTING_PERMISSION, null,
                    HttpStatus.CONFLICT);
        }

        this.permissionAdministrationService.createPermission(permissionDto);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ADD_PERMISSION),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_PERMISSION_MODIFY)
    public ResponseEntity<ResponseDataDTO> updatePermission(PermissionDto permissionDto) {
        this.permissionAdministrationService.updatePermission(permissionDto);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UPDATE_PERMISSION),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_PERMISSION_DELETE)
    public ResponseEntity<ResponseDataDTO> deletePermission(Integer id) {
        if (BooleanUtils.isTrue(this.permissionAdministrationServiceValidator.isParentPermission(id))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_PARENT_PERMISSION, null,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.permissionAdministrationService.deletePermission(id);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_DELETE_PERMISSION),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_PERMISSION_ENQUIRY)
    public ResponseEntity<List<PermissionDto>> getPermissions() {
        final List<PermissionDto> permissions = this.permissionAdministrationService.getPermissions();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @Override
    public com.isl.gbm.common.dto.ResponseDataDTO getMenuLinks() {
        return permissionAdministrationService.getMenuLinks();
    }
}
