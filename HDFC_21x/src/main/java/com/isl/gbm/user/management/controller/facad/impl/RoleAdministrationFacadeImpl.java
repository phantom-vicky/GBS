package com.isl.gbm.user.management.controller.facad.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.constants.UserMgmtMessages;
import com.isl.gbm.user.management.controller.facad.RoleAdministrationFacade;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.dto.UserDetailsDto;
import com.isl.gbm.user.management.service.RoleAdministrationService;
import com.isl.gbm.user.management.util.APIResponseBuilder;
import com.isl.gbm.user.management.validator.RoleAdministrationValidator;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RoleAdministrationFacadeImpl implements RoleAdministrationFacade {

    @Autowired
    private RoleAdministrationService roleAdministrationService;

    /**
     * The role administration validator.
     */
    @Autowired
    private RoleAdministrationValidator roleAdministrationValidator;


    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> createRole(RoleDto roleDto) {
        if (BooleanUtils.isTrue(this.roleAdministrationValidator.isRoleNameExists(roleDto.getName()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_EXISTING_ROLE, null,
                    HttpStatus.CONFLICT);
        }

        this.roleAdministrationService.createRole(roleDto);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ADD_ROLE),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_MODIFY)
    public ResponseEntity<ResponseDataDTO> updateRole(RoleDto roleDto) {
        if (BooleanUtils.isFalse(this.roleAdministrationValidator.isRoleExists(roleDto.getId()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_ROLE_NOT_FOUND, null,
                    HttpStatus.NOT_FOUND);
        }

        this.roleAdministrationService.updateRole(roleDto);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UPDATE_ROLE),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ENQUIRY)
    public ResponseEntity<RoleDto> getRole(Integer roleId) {
        return new ResponseEntity<>(this.roleAdministrationService.getRole(roleId), HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_DELETE)
    public ResponseEntity<ResponseDataDTO> deleteRole(Integer id) {
        if (BooleanUtils.isTrue(this.roleAdministrationValidator.isRoleAssigned(id))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_ROLE_ASSIGNED, null,
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.roleAdministrationService.deleteRole(id);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_DELETE_ROLE),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_MODIFY)
    public ResponseEntity<ResponseDataDTO> createRolePermissionMapping(Integer roleId, Set<Integer> permissionIds) {
        if (BooleanUtils.isFalse(this.roleAdministrationValidator.isRoleExists(roleId))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_ROLE_NOT_FOUND, null,
                    HttpStatus.NOT_FOUND);
        }

        this.roleAdministrationService.createRolePermissionMapping(roleId, permissionIds);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ROLE_PERMISSION_MAPPING),
                HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ENQUIRY)
    public ResponseEntity<List<RoleDto>> getRoles() {
        final List<RoleDto> roles = this.roleAdministrationService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ENQUIRY)
    public ResponseEntity<List<PermissionDto>> getPermissions(Integer roleId) {
        final List<PermissionDto> permissions = this.roleAdministrationService.getRolePermissions(roleId);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> saveRole(RoleDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.saveRole(roleDto));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> modifyRole(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.modifyRole(roleDto));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> deleteRole(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.deleteRole(roleDto));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> verifyRole(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.verifyRole(roleDto));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> inquiryRoleDetails(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.inquiryRoleDetails(roleDto.getId()));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> modifyRoleDetails(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.modifyRoleDetails(roleDto.getId()));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_ADD)
    public ResponseEntity<ResponseDataDTO> verifyRoleDetails(RoleDetailsDto roleDto) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(roleAdministrationService.verifyRoleDetails(roleDto.getId()));
    }

}
