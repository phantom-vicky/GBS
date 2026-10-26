package com.isl.gbm.user.mgmt.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.mgmt.dao.UserCreationRoleMappingDAO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.service.UserCreationRoleMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserCreationRoleMappingServiceimpl implements UserCreationRoleMappingService {



    @Autowired
    private UserCreationRoleMappingDAO userCreationRoleMappingDAO;

    @Override
    public ResponseDataDTO getRoleDetails() {
        return userCreationRoleMappingDAO.getRoleDetails();
    }

    @Override
    public ResponseDataDTO getBranchDetails() {
        return userCreationRoleMappingDAO.getBranchDetails();
    }

    @Override
    public ResponseDataDTO validateUsername(String username) {
        return userCreationRoleMappingDAO.validateUsername(username);
    }
    @Override
    public ResponseDataDTO validateEmployeeCode(String employeeCode) {
        return userCreationRoleMappingDAO.validateEmployeeCode(employeeCode);
    }

    @Override
    public ResponseDataDTO validateEmail(String email) {
        return userCreationRoleMappingDAO.validateEmail(email);
    }


    @Override
    public ResponseDataDTO add(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.add(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO inquiry(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.inquiry(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.modifySelect(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.verifySelect(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO verify(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.verify(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO delete(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.delete(userCreationRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO modify(UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return userCreationRoleMappingDAO.modify(userCreationRoleMappingDTO);
    }


}
