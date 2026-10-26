package com.isl.gbm.user.mgmt.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dao.UnlockUserDAO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.service.UnlockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UnlockUserServiceimpl implements UnlockUserService {

    

    @Autowired
    private UnlockUserDAO unlockUserDAO;


    @Override
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO dto) {
        return unlockUserDAO.modifySelect(dto);
    }

    @Override
    public ResponseDataDTO verifySelect(UserCreationRoleMappingDTO dto) {
        return unlockUserDAO.verifySelect(dto);
    }

    @Override
    public ResponseDataDTO verify(UserCreationRoleMappingDTO dto) {
        return unlockUserDAO.verify(dto);
    }

    @Override
    public ResponseDataDTO delete(UserCreationRoleMappingDTO dto) {
        return unlockUserDAO.delete(dto);
    }

    @Override
    public ResponseDataDTO modify(UserCreationRoleMappingDTO dto) {
        return unlockUserDAO.modify(dto);
    }


}
