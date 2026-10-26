package com.isl.gbm.user.management.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.controller.facad.ActiveUserFacade;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.dto.UserParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gbmultra/admin/activeUser")
public class ActiveUserController {

    @Autowired
    ActiveUserFacade activeUserFacade;

    @PostMapping(path = "/getUsers")
    public ResponseEntity<ResponseDataDTO> getUsers() {
        return APIResponseBuilder.buildResponseFromDto(activeUserFacade.getUsers());
    }

    @PostMapping(path = "/modifysubmit")
    public ResponseEntity<ResponseDataDTO> modifyUsers(@RequestBody ActiveUserDto activeUserDto)  {
        return APIResponseBuilder.buildResponseFromDto(activeUserFacade.modifyUsers(activeUserDto));
    }

    @PostMapping(path = "/getVerifyDetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails() {
        return APIResponseBuilder.buildResponseFromDto(activeUserFacade.getVerifyDetails());
    }

    @PostMapping(path = "/verifyUsers")
    public ResponseEntity<ResponseDataDTO> verifyUsers(@RequestBody ActiveUserDto activeUserDto) {
        return APIResponseBuilder.buildResponseFromDto(activeUserFacade.verifyUsers(activeUserDto));
    }

    @PostMapping(path = "/deleteUsers")
    public ResponseEntity<ResponseDataDTO> deleteUsers(@RequestBody final ActiveUserDto activeUserDto) {
        return APIResponseBuilder.buildResponseFromDto(activeUserFacade.deleteUsers(activeUserDto));
    }


}
