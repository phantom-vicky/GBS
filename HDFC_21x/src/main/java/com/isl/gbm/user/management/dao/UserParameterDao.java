package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserParamDto;

public interface UserParameterDao {

    ResponseDataDTO updateUserParams();
    ResponseDataDTO modify(UserParamDto users);
    ResponseDataDTO getUserParams();
    ResponseDataDTO getverDelParam();
    ResponseDataDTO verifySubmit();
    ResponseDataDTO delete(UserParamDto userParamDto);
}
