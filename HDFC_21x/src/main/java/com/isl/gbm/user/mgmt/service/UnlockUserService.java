package com.isl.gbm.user.mgmt.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;

public interface UnlockUserService {

    
    ResponseDataDTO modifySelect(UserCreationRoleMappingDTO hdfcUserDTO);

    ResponseDataDTO verifySelect(UserCreationRoleMappingDTO hdfcUserDTO);

    ResponseDataDTO verify(UserCreationRoleMappingDTO hdfcUserDTO);

    ResponseDataDTO delete(UserCreationRoleMappingDTO hdfcUserDTO);

    ResponseDataDTO modify(UserCreationRoleMappingDTO hdfcUserDTO);
    
    
}
