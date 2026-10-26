package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;

import com.isl.gbm.user.management.dto.ActiveUserDto;

import com.isl.gbm.user.management.manager.ActiveUserManager;
import com.isl.gbm.user.management.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveUserServiceImpl implements ActiveUserService {

    @Autowired
    ActiveUserManager activeUserManager;

    @Override
    public ResponseDataDTO getUsers(){
        return activeUserManager.getUsers();
    }

    @Override
    public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto){
        return activeUserManager.modifyUsers(activeUserDto);
    }
    @Override
    public ResponseDataDTO getVerifyDetails(){
        return activeUserManager.getVerifyDetails();
    }
    @Override
    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto){

        return activeUserManager.verifyUsers(activeUserDto);
    }
    @Override
    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto){
        return activeUserManager.deleteUsers(activeUserDto);
    }
}
