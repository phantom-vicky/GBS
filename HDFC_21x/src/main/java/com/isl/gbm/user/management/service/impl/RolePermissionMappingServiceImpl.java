package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dto.RolePermissionDTO;
import com.isl.gbm.user.management.manager.RolePermissionMappingManager;
import com.isl.gbm.user.management.service.RolePermissionMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionMappingServiceImpl implements RolePermissionMappingService {
    @Autowired
    private RolePermissionMappingManager rolePermissionMappingManager;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO getRoleMappingDetailsForSave(String roleId){
        return rolePermissionMappingManager.getRoleMappingDetailsForSave(roleId);
    }

    @Override
    public ResponseDataDTO save(RolePermissionDTO rolePermissionDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(rolePermissionDTO.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return rolePermissionMappingManager.save(rolePermissionDTO);
    }

    @Override
    public ResponseDataDTO verify(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingManager.verify(rolePermissionDTO);
    }

    @Override
    public ResponseDataDTO delete(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingManager.delete(rolePermissionDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(String roleId){
        return rolePermissionMappingManager.getInquiryDetails(roleId);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(String roleId){
        return rolePermissionMappingManager.getVerifyDetails(roleId);
    }

    @Override
    public ResponseDataDTO modify(RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingManager.modify(rolePermissionDTO);
    }

    @Override
    public ResponseDataDTO getModifyDetails(String roleId){
        return rolePermissionMappingManager.getModifyDetails(roleId);
    }
}
