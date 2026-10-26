package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.UserRoleMappingDAO;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMappingManager {

    @Autowired
    private UserRoleMappingDAO userRoleMappingDAO;


    public ResponseDataDTO getUserRoleMappingDetailsForSave(String userId){
        return userRoleMappingDAO.getUserRoleMappingDetailsForSave(userId);
    }

    public ResponseDataDTO save(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingDAO.save(userRoleMappingDTO);
    }

    public ResponseDataDTO verify(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingDAO.verify(userRoleMappingDTO);
    }

    public ResponseDataDTO getInquiryDetails(String userId){
        return userRoleMappingDAO.getInquiryDetails(userId);
    }

    public ResponseDataDTO getModifyDetails(String userId){
        return userRoleMappingDAO.getModifyDetails(userId);
    }

    public ResponseDataDTO modify(UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingDAO.modify(userRoleMappingDTO);
    }

    public ResponseDataDTO getVerifyDetails(String userId){
        return userRoleMappingDAO.getVerifyDetails(userId);
    }
}
