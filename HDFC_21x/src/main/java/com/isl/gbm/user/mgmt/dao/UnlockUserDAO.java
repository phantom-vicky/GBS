package com.isl.gbm.user.mgmt.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;

public interface UnlockUserDAO {

    
    ResponseDataDTO modifySelect(UserCreationRoleMappingDTO dto);

    ResponseDataDTO verifySelect(UserCreationRoleMappingDTO dto);

    ResponseDataDTO verify(UserCreationRoleMappingDTO dto);

    ResponseDataDTO delete(UserCreationRoleMappingDTO dto);

    ResponseDataDTO modify(UserCreationRoleMappingDTO dto);
}
