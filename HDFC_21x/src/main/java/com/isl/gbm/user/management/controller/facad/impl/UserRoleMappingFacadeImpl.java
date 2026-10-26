package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.controller.facad.UserRoleMappingFacade;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import com.isl.gbm.user.management.service.UserRoleMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMappingFacadeImpl implements UserRoleMappingFacade {

    @Autowired
    private UserRoleMappingService userRoleMappingService;

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_ADD)
    public ResponseEntity<ResponseDataDTO> getUserRoleMappingDetailsForSave(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.getUserRoleMappingDetailsForSave(userRoleMappingDTO.getUserId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_ADD)
    public ResponseEntity<ResponseDataDTO> save(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.save(userRoleMappingDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_VERIFY)
    public ResponseEntity<ResponseDataDTO> verify(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.verify(userRoleMappingDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_ENQUIRY)
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.getInquiryDetails(userRoleMappingDTO.getUserId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_VERIFY)
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.getVerifyDetails(userRoleMappingDTO.getUserId()));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_MODIFY)
    public ResponseEntity<ResponseDataDTO> modify(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.modify(userRoleMappingDTO));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_ROLE_MAPPING_MODIFY)
    public ResponseEntity<ResponseDataDTO> getModifyDetails(UserRoleMappingDTO userRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userRoleMappingService.getModifyDetails(userRoleMappingDTO.getUserId()));
    }
}
