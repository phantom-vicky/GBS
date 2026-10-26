package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.controller.facad.RolePermissionMappingFacade;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RolePermissionDTO;
import com.isl.gbm.user.management.service.RolePermissionMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMappingFacadeImpl implements RolePermissionMappingFacade {

    @Autowired
    private RolePermissionMappingService rolePermissionMappingService;

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_ADD)
    public ResponseEntity<ResponseDataDTO> getRoleMappingDetailsForSave(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.getRoleMappingDetailsForSave(rolePermissionDTO.getRoleId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_ADD)
    public ResponseEntity<ResponseDataDTO> save(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.save(rolePermissionDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_VERIFY)
    public ResponseEntity<ResponseDataDTO> verify(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.verify(rolePermissionDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_VERIFY)
    public ResponseEntity<ResponseDataDTO> delete(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.delete(rolePermissionDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_ENQUIRY)
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.getInquiryDetails(rolePermissionDTO.getRoleId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_VERIFY)
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.getVerifyDetails(rolePermissionDTO.getRoleId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_MODIFY)
    public ResponseEntity<ResponseDataDTO> modify(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.modify(rolePermissionDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_ROLE_PERMISSION_MAPPING_MODIFY)
    public ResponseEntity<ResponseDataDTO> getModifyDetails(RolePermissionDTO rolePermissionDTO) {
        return APIResponseBuilder.buildResponseFromDto(rolePermissionMappingService.getModifyDetails(rolePermissionDTO.getRoleId()));
    }

}
