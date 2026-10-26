package com.isl.gbm.user.mgmt.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.facade.UserCreationRoleMappingFacade;
import com.isl.gbm.user.mgmt.service.UserCreationRoleMappingService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserCreationRoleMappingFacadeimpl implements UserCreationRoleMappingFacade {


    @Autowired
    private UserCreationRoleMappingService userCreationRoleMappingService;


    @Override
    public ResponseDataDTO getBranchDetails() {
        return userCreationRoleMappingService.getBranchDetails();
    }



    @Override
    public ResponseDataDTO getRoleDetails() {
        return userCreationRoleMappingService.getRoleDetails();
    }

    @Override
    public ResponseDataDTO validateUsername(String username) {
        return userCreationRoleMappingService.validateUsername(username);
    }

    @Override
    public ResponseDataDTO validateEmployeeCode(String employeeCode) {
        return userCreationRoleMappingService.validateEmployeeCode(employeeCode);
    }

    @Override
    public ResponseDataDTO validateEmail(String email) {
        return userCreationRoleMappingService.validateEmail(email);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:ADD")
    public ResponseDataDTO add(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.add(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:ENQUIRY")
    public ResponseDataDTO inquiry(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.inquiry(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:MODIFY")
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.modifySelect(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_USER_MGMT:USR_CRT_RLE_MPG:VERIFY", "GBM_USER_MGMT:USR_CRT_RLE_MPG:REJECT"})
    public ResponseDataDTO verifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.verifySelect(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:VERIFY")
    public ResponseDataDTO verify(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.verify(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:REJECT")
    public ResponseDataDTO delete(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.delete(userCreationRoleMappingDTO);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:USR_CRT_RLE_MPG:MODIFY")
    public ResponseDataDTO modify(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingService.modify(userCreationRoleMappingDTO);
    }


}
