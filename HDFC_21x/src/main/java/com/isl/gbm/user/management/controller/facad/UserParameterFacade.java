package com.isl.gbm.user.management.controller.facad;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserParamDto;
import org.springframework.http.ResponseEntity;

public interface UserParameterFacade {

    ResponseEntity<ResponseDataDTO> updateUserParams();

    ResponseEntity<ResponseDataDTO> modify(UserParamDto users);

    ResponseEntity<ResponseDataDTO> getUserParams();

    ResponseEntity<ResponseDataDTO> getverDelParam();

    ResponseEntity<ResponseDataDTO> verifySubmit();
    ResponseEntity<ResponseDataDTO> delete(UserParamDto userParamDto);
}
