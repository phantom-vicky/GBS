package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.controller.facad.UserParameterFacade;
import com.isl.gbm.user.management.dto.UserParamDto;
import com.isl.gbm.user.management.service.UserParameterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserParameterFacadeImpl implements UserParameterFacade {

    @Autowired
    private UserParameterService userParameterService;

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_MODIFY)
    public ResponseEntity<ResponseDataDTO> updateUserParams() {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.updateUserParams());
    }


    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_MODIFY)
    public ResponseEntity<ResponseDataDTO> modify(UserParamDto users) {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.modify(users));
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_ENQUIRY)
    public ResponseEntity<ResponseDataDTO> getUserParams() {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.getUserParams());
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_VERDEL)
    public ResponseEntity<ResponseDataDTO> getverDelParam() {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.getverDelParam());
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_VERDEL)
    public ResponseEntity<ResponseDataDTO> verifySubmit() {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.verifySubmit());
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_VERDEL)
    public ResponseEntity<ResponseDataDTO> delete(UserParamDto userParamDto) {
        return APIResponseBuilder.buildResponseFromDto(userParameterService.delete(userParamDto));
    }

}
