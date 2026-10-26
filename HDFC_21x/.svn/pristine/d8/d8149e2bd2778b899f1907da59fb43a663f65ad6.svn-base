package com.isl.gbm.user.mgmt.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.facade.UnlockUserFacade;
import com.isl.gbm.user.mgmt.service.UnlockUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UnlockUserFacadeimpl implements UnlockUserFacade {

    @Autowired
    private UnlockUserService unlockUserService;


    @Override
    @RequiresPermissions("GBM_USER_MGMT:UNL_USR:MODIFY")
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO dto) {
        return unlockUserService.modifySelect(dto);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:UNL_USR:VER/REJ")
//    @RequiresPermissions(logical = Logical.OR, value = {"GBM_USER_MGMT:UNL_USR:VERIFY", "GBM_USER_MGMT:UNL_USR:REJECT"})
    public ResponseDataDTO verifySelect(UserCreationRoleMappingDTO dto) {
        return unlockUserService.verifySelect(dto);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:UNL_USR:VER/REJ")
    public ResponseDataDTO verify(UserCreationRoleMappingDTO dto) {
        return unlockUserService.verify(dto);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:UNL_USR:VER/REJ")
    public ResponseDataDTO delete(UserCreationRoleMappingDTO dto) {
        return unlockUserService.delete(dto);
    }

    @Override
    @RequiresPermissions("GBM_USER_MGMT:UNL_USR:MODIFY")
    public ResponseDataDTO modify(UserCreationRoleMappingDTO dto) {
        return unlockUserService.modify(dto);
    }


}
