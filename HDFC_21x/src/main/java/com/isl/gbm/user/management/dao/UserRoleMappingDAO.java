package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;

public interface UserRoleMappingDAO {
    ResponseDataDTO getUserRoleMappingDetailsForSave(String userId);

    ResponseDataDTO save(UserRoleMappingDTO userRoleMappingDTO);

    ResponseDataDTO verify(UserRoleMappingDTO userRoleMappingDTO);

    ResponseDataDTO getInquiryDetails(String userId);

    ResponseDataDTO getModifyDetails(String userId);

    ResponseDataDTO modify(UserRoleMappingDTO userRoleMappingDTO);

    ResponseDataDTO getVerifyDetails(String userId);
}
