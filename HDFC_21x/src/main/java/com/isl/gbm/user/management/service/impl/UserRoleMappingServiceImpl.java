package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import com.isl.gbm.user.management.manager.UserRoleMappingManager;
import com.isl.gbm.user.management.service.UserRoleMappingService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleMappingServiceImpl  implements UserRoleMappingService {

    @Autowired
    private UserRoleMappingManager userRoleMappingManager;

    @Override
    public ResponseDataDTO getUserRoleMappingDetailsForSave(String userId){
        return userRoleMappingManager.getUserRoleMappingDetailsForSave(userId);
    }

    @Override
    public ResponseDataDTO save(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingManager.save(userRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO verify(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingManager.verify(userRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(String userId){
        return userRoleMappingManager.getInquiryDetails(userId);
    }

    @Override
    public ResponseDataDTO getModifyDetails(String userId){
        return userRoleMappingManager.getModifyDetails(userId);
    }

    @Override
    public ResponseDataDTO modify(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingManager.modify(userRoleMappingDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(String userId){
        return userRoleMappingManager.getVerifyDetails(userId);
    }
}
