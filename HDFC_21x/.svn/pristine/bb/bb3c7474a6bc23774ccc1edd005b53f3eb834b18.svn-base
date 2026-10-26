package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.UserParamDto;
import com.isl.gbm.user.management.manager.UserParameterManager;
import com.isl.gbm.user.management.service.UserParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserParameterServiceImpl implements UserParameterService {
    @Autowired
    private UserParameterManager userParameterManager;

    @Override
    public ResponseDataDTO updateUserParams(){
        return userParameterManager.updateUserParams();
    }

    @Override
    public ResponseDataDTO modify(UserParamDto users){
        return userParameterManager.modify(users);
    }

    @Override
    public ResponseDataDTO getUserParams(){
        return userParameterManager.getUserParams();
    }

    @Override
    public ResponseDataDTO getverDelParam(){
        return userParameterManager.getverDelParam();
    }

    @Override
    public ResponseDataDTO verifySubmit(){
        return userParameterManager.verifySubmit();
    }

    @Override
    public ResponseDataDTO delete(UserParamDto userParamDto){
        return userParameterManager.delete(userParamDto);
    }


}
