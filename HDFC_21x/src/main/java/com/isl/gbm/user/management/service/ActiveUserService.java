package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.ActiveUserDto;

import java.util.List;


public interface ActiveUserService {


    public ResponseDataDTO getUsers();

    public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto);

    public ResponseDataDTO getVerifyDetails();

    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto);

    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto);

}
