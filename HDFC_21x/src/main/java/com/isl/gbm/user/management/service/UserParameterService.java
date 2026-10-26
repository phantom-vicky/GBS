package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserParamDto;

public interface UserParameterService {

    ResponseDataDTO updateUserParams();
    ResponseDataDTO modify(UserParamDto users);
    ResponseDataDTO getUserParams();
    ResponseDataDTO getverDelParam();
    ResponseDataDTO verifySubmit();
    ResponseDataDTO delete(UserParamDto userParamDto);
}
