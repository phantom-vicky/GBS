package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.ActiveUserFacade;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ActiveUserFacadeImpl implements ActiveUserFacade {

    @Autowired
    ActiveUserService activeUserService;

    @Override
    public ResponseDataDTO getUsers() {
        return activeUserService.getUsers();

    }

    @Override
    public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto) {
    return activeUserService.modifyUsers(activeUserDto);

    }
    @Override
    public ResponseDataDTO getVerifyDetails() {
        return activeUserService.getVerifyDetails();

    }
    @Override
    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto) {
        return activeUserService.verifyUsers(activeUserDto);

    }
    @Override
    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto) {
        return activeUserService.deleteUsers(activeUserDto);

    }

}
