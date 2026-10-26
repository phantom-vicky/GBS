package com.isl.gbm.user.management.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.UserParameterFacade;
import com.isl.gbm.user.management.dto.UserParamDto;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Vector;

@RestController
@RequestMapping("gbmultra/admin/userparams")
public class UserParameterController {

    @Autowired
    private UserParameterFacade userParameterFacade;


    @PostMapping("/getModifiedParams")
    public ResponseEntity<ResponseDataDTO> updateUserParams(){
        return userParameterFacade.updateUserParams();
    }

    @PostMapping("/modifyUserParams")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody final UserParamDto users){
        return userParameterFacade.modify(users);
    }

    @PostMapping("/getUserParams")
    public ResponseEntity<ResponseDataDTO> getUserParams(){
        return userParameterFacade.getUserParams();
    }

    @PostMapping("/getverDetailsParams")
    public ResponseEntity<ResponseDataDTO> getverDelParam(){
        return userParameterFacade.getverDelParam();
    }

    @PostMapping("/verifyUserParams")
    public ResponseEntity<ResponseDataDTO> verifySubmit(){
        return userParameterFacade.verifySubmit();
    }

    @PostMapping("/deleteUserParams")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final UserParamDto userParamDto){
        return userParameterFacade.delete(userParamDto);
    }

}
