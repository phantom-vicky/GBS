package com.isl.gbm.user.management.controller.facad;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import org.springframework.http.ResponseEntity;

public interface UserRoleMappingFacade {

    ResponseEntity<ResponseDataDTO> getUserRoleMappingDetailsForSave(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> save(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> verify(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> getInquiryDetails(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> getModifyDetails(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> modify(UserRoleMappingDTO userRoleMappingDTO);

    ResponseEntity<ResponseDataDTO> getVerifyDetails(UserRoleMappingDTO userRoleMappingDTO);
}
