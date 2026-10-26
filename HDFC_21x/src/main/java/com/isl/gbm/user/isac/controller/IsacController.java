package com.isl.gbm.user.isac.controller;
import com.isl.gbm.user.isac.dto.*;
import com.isl.gbm.user.isac.service.IsacService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gbmultra/isac/api")
public class IsacController {
    private static final Logger log = LoggerFactory.getLogger(IsacController.class);

    @Autowired
    IsacService isacService;
    @PostMapping(value = "/userCreation")
    public ResponseEntity<IsacResponseDTO> userCreation(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult result){
        log.info("Controller UserCreation Request: {}", userRequestDTO);
        return new ResponseEntity<>(isacService.userCreation(userRequestDTO, result),HttpStatus.OK);
    }

    @PostMapping(value = "/userModify")
    public ResponseEntity<IsacResponseDTO> userModify(@Valid @RequestBody IsacUserRequestModifyDTO userRequestDTO, BindingResult result){
        log.info("Controller userModify Request: {}", userRequestDTO);
        return new ResponseEntity<>(isacService.userModify(userRequestDTO, result),HttpStatus.OK);
    }

    @PostMapping(value = "/unlockUser")
    public ResponseEntity<IsacResponseDTO> unlockUser(@Valid @RequestBody UnlockUserRequestDTO unlockUserRequestDTO, BindingResult result){
        log.info("Controller unlockUser Request: {}", unlockUserRequestDTO);
        return new ResponseEntity<>(isacService.unlockUser(unlockUserRequestDTO, result),HttpStatus.OK);

    }

    @PostMapping(value = "/sackUser")
    public ResponseEntity<IsacResponseDTO> sackUser(@Valid @RequestBody SackUserRequestDTO sackUserRequestDTO, BindingResult result){
        log.info("Controller sackUser Request: {}", sackUserRequestDTO);
        return new ResponseEntity<>(isacService.sackUser(sackUserRequestDTO, result),HttpStatus.OK);

    }

    @PostMapping(value = "/enableDisableRevokeUser")
    public ResponseEntity<IsacResponseDTO> enableDisableRevokeUser(@Valid @RequestBody UserEnableDisableRevokeRequestDTO userEnableDisableRevokeRequestDTO, BindingResult result){
        log.info("Controller enableDisableRevokeUser Request: {}", userEnableDisableRevokeRequestDTO);
        return new ResponseEntity<>(isacService.enableDisableRevokeUser(userEnableDisableRevokeRequestDTO, result),HttpStatus.OK);

    }

}
