package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.UserParameterDao;
import com.isl.gbm.user.management.dto.UserParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserParameterManager {

    @Autowired
    private UserParameterDao userParameterDao;


    public ResponseDataDTO updateUserParams(){
        return userParameterDao.updateUserParams();
    }

    public ResponseDataDTO modify(UserParamDto users){
        return userParameterDao.modify(users);
    }

    public ResponseDataDTO getUserParams(){
        return userParameterDao.getUserParams();
    }

    public ResponseDataDTO getverDelParam(){
        return userParameterDao.getverDelParam();
    }

    public ResponseDataDTO verifySubmit(){
        return userParameterDao.verifySubmit();
    }

    public ResponseDataDTO delete(UserParamDto userParamDto){
        return userParameterDao.delete(userParamDto);
    }
}
