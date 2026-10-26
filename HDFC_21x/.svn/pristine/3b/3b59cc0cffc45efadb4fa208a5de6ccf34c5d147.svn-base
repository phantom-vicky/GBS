package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.model.User;

import java.util.List;

public interface ActiveUserDao {

    public ResponseDataDTO getUsers();
    public User validateUserStatus(String userName);


    public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto);

    public ResponseDataDTO getVerifyDetails();

    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto);

    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto);
}
