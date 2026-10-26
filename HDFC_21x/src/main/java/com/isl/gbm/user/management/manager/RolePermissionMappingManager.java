package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.RolePermissionMappingDao;
import com.isl.gbm.user.management.dto.RolePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMappingManager {

    @Autowired
    private RolePermissionMappingDao rolePermissionMappingDao;



    public ResponseDataDTO getRoleMappingDetailsForSave(String roleId){
        return rolePermissionMappingDao.getRoleMappingDetailsForSave(roleId);
    }

    public ResponseDataDTO save(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingDao.save(rolePermissionDTO);
    }

    public ResponseDataDTO verify(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingDao.verify(rolePermissionDTO);
    }

    public ResponseDataDTO delete(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingDao.delete(rolePermissionDTO);
    }

    public ResponseDataDTO getInquiryDetails(String roleId){
        return rolePermissionMappingDao.getInquiryDetails(roleId);
    }

    public ResponseDataDTO getVerifyDetails(String roleId){
        return rolePermissionMappingDao.getVerifyDetails(roleId);
    }

    public ResponseDataDTO modify(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingDao.modify(rolePermissionDTO);
    }

    public ResponseDataDTO getModifyDetails(String roleId){
        return rolePermissionMappingDao.getModifyDetails(roleId);
    }
}
