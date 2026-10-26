package com.isl.gbm.user.mgmt.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;

public interface UserCreationRoleMappingFacade {



    public ResponseDataDTO getBranchDetails();


    public ResponseDataDTO getRoleDetails();


    ResponseDataDTO validateUsername(String username);

    ResponseDataDTO validateEmployeeCode(String employeeCode);

    ResponseDataDTO validateEmail(String email);

    ResponseDataDTO add(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO inquiry(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO modifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO verifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO verify(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO delete(UserCreationRoleMappingDTO userCreationRoleMappingDTO);

    ResponseDataDTO modify(UserCreationRoleMappingDTO userCreationRoleMappingDTO);


}
