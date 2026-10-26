package com.isl.gbm.user.mgmt.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.facade.UnlockUserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gbmultra/userMgmt/unlock")
public class UnlockUserController {


    @Autowired
    private UnlockUserFacade unlockUserFacade;


    //@PostMapping(path = "/modifySelect")
    @GetMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect() {
        return APIResponseBuilder.buildResponseFromDto(unlockUserFacade.modifySelect(null));
    }
    
    @PostMapping(path = "/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody UserCreationRoleMappingDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(unlockUserFacade.modify(dto));
    }


    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody UserCreationRoleMappingDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(unlockUserFacade.verifySelect(dto));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody UserCreationRoleMappingDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(unlockUserFacade.verify(dto));
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody UserCreationRoleMappingDTO dto) {
        return APIResponseBuilder.buildResponseFromDto(unlockUserFacade.delete(dto));
    }






}
