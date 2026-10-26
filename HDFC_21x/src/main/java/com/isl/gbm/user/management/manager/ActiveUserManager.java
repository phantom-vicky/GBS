package com.isl.gbm.user.management.manager;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.ActiveUserDao;

import com.isl.gbm.user.management.dto.ActiveUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveUserManager {


    @Autowired
    private ActiveUserDao activeUserDao;


    public ResponseDataDTO getUsers(){

        return activeUserDao.getUsers();
    }

//    public ResponseDataDTO modifyUsers(List<ActiveUserDto> userList){
//
//        return activeUserDao.modifyUsers(userList);
//    }
public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto){

    return activeUserDao.modifyUsers(activeUserDto);
}
    public ResponseDataDTO getVerifyDetails(){

        return activeUserDao.getVerifyDetails();
    }

    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto){

        return activeUserDao.verifyUsers(activeUserDto);
    }

    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto){

        return activeUserDao.deleteUsers(activeUserDto);
    }



}
