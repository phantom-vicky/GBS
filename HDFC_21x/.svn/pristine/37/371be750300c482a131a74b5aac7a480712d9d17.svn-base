package com.isl.gbm.user.mgmt.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import com.isl.gbm.user.mgmt.facade.UserCreationRoleMappingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gbsultra/user/usrCrtRleCreation")
public class UserCreationRoleMappingController {


    @Autowired
    private UserCreationRoleMappingFacade userCreationRoleMappingFacade;


    @PostMapping(path = "/getBranchDetails")
    public ResponseEntity<ResponseDataDTO> getBranchDetails() {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.getBranchDetails());
    }

    @GetMapping(path = "/validateUsername")
    public ResponseEntity<ResponseDataDTO> validateUsername(@RequestParam(name = "username", required = true) final String username) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.validateUsername(username));
    }

    @GetMapping(path = "/validateEmployeeCode")
    public ResponseEntity<ResponseDataDTO> validateEmployeeCode(@RequestParam(name = "employeeCode", required = true) final String employeeCode) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.validateEmployeeCode(employeeCode));
    }

    @GetMapping(path = "/validateEmail")
    public ResponseEntity<ResponseDataDTO> validateEmail(@RequestParam(name = "email", required = true) final String email) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.validateEmail(email));
    }

    @PostMapping(path = "/getRoleDetails")
    public ResponseEntity<ResponseDataDTO> getRoleDetails() {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.getRoleDetails());
    }


    @PostMapping(path = "/add")
    @AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> add(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.add(userCreationRoleMappingDTO));
    }


    @PostMapping(path = "/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.inquiry(userCreationRoleMappingDTO));
    }

    @PostMapping(path = "/modifySelect")
    public ResponseEntity<ResponseDataDTO> modifySelect(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.modifySelect(userCreationRoleMappingDTO));
    }

    @PostMapping(path = "/verifySelect")
    public ResponseEntity<ResponseDataDTO> verifySelect(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.verifySelect(userCreationRoleMappingDTO));
    }


    @PostMapping(path = "/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.verify(userCreationRoleMappingDTO));
    }

    @PostMapping(path = "/delete")
    @AdminLog(logType = UserMgmtOperations.DELETE_USER)
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.delete(userCreationRoleMappingDTO));
    }

    @PostMapping(path = "/modify")
    @AdminLog(logType = UserMgmtOperations.UPDATE_USER)
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody UserCreationRoleMappingDTO userCreationRoleMappingDTO) {
        return APIResponseBuilder.buildResponseFromDto(userCreationRoleMappingFacade.modify(userCreationRoleMappingDTO));
    }




}
