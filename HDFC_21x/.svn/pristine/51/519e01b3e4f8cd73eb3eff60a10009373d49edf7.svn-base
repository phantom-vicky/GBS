/*
 *
 */
package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.utils.APIResponseBuilder;
import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserMgmtMessages;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.management.controller.facad.UserAdministrationFacade;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.service.UserAdministrationService;
import com.isl.gbm.user.management.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.*;


/**
 * The Class UserAdministrationController.
 */
@RestController
@RequestMapping("/gbmultra/admin")
public class UserAdministrationController {

    /**
     * The user administration service.
     */
    @Autowired
    private UserAdministrationService userAdministrationService;

    /**
     * The user administration userAdministrationFacade.
     */
    @Autowired
    private UserAdministrationFacade userAdministrationFacade;


    @Autowired
    private UserService userService;
    /**
     * Creates the user.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping("/user/add")
    @AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> createUser(@Valid @RequestBody final UserDto user) {
        return this.userAdministrationFacade.createUser(user);
    }

    @PostMapping("/api/testValidation")
    public ResponseEntity<?> userCreation(@Valid @RequestBody TestValidationDTO testValidationDTO, BindingResult result){
//        if(result.hasErrors()){
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()) {
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            return new ResponseEntity<>(errors, HttpStatus.OK);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


/*
    @PostMapping("/user/modify")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> modifyUser(@RequestBody final UserDto user) {
        return this.userAdministrationFacade.modifyUser(user);
    }*/

    @PostMapping("/user/createUserRoleMapping")
    @AdminLog(logType = UserMgmtOperations.UPDATE_USER_ROLE_MAPPING)
    public ResponseEntity<ResponseDataDTO> createUserRoleMapping(@RequestBody final UserAdminDTO userAdminDTO) {
        return this.userAdministrationFacade.createUserRoleMapping(userAdminDTO.getUserId(), userAdminDTO.getRoleIds());
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return the response entity
     */
    @DeleteMapping("/user/{id}")
    @AdminLog(logType = UserMgmtOperations.DELETE_USER)
    public ResponseEntity<ResponseDataDTO> deleteUser(@PathVariable("id") final Integer userId) {
        return this.userAdministrationFacade.deleteUser(userId);
    }

    @PostMapping("/user/disable")
    @AdminLog(logType = UserMgmtOperations.DISABLE_USER)
    public ResponseEntity<ResponseDataDTO> disableUser(@RequestBody UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.disableUser(userAdminDTO.getUserId(), userAdminDTO.getDisabledTill());
    }

    /**
     * Gets the active users.
     *
     * @return the active users
     */
/*    @GetMapping("/activeusers")
    public ResponseEntity<List<ActiveUserDto>> getActiveUsers() {
        return new ResponseEntity<>(this.userAdministrationService.getActiveUsers(), HttpStatus.OK);
    }*/

    @PostMapping("/activeusersforsack")
    public ResponseEntity<List<ActiveUserDto>> getActiveUsersForSack(@RequestBody UserAdminSackDTO userAdminSackDTO) {
        return new ResponseEntity<>(this.userAdministrationService.getActiveUsersForSack(userAdminSackDTO), HttpStatus.OK);
    }

    /**
     * Gets the locked users.
     *
     * @return the locked users
     */
    @PostMapping("/lockedusers")
    public ResponseEntity<List<UserDto>> getLockedUsers() {

        return this.userAdministrationFacade.getLockedUsers();
    }

    @PostMapping("/user/role")
    public ResponseEntity<List<RoleDto>> getRoles(@RequestBody UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.getUserRoles(userAdminDTO.getUserId());
    }

    @PostMapping("/user/getUser")
    public ResponseEntity<UserDto> getUser(@RequestBody UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.getUser(userAdminDTO.getUserId());
    }

    @PostMapping("/user/search")
    public ResponseEntity<ResponseDataDTO> getUserByUsername(@RequestBody UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.getUserByUsername(userAdminDTO.getUserName());
    }

    /**
     * Gets the userparams.
     *
     * @return the userparams
     */
    @PostMapping("/user/params/getUserParams")
    public ResponseEntity<UserParamDto> getUserParams() {

        return this.userAdministrationFacade.getUserParam();
    }

    /**
     * Gets the users.
     *
     * @return the users
     */
/*    @GetMapping("/user/all")

    public ResponseEntity<List<UserDto>> getUsers() {
        return this.userAdministrationFacade.getUsers();
    }*/

    @PostMapping("/user/password/reset")
    @AdminLog(logType = UserMgmtOperations.RESET_PASSWORD)
    public ResponseEntity<ResponseDataDTO> resetPassword(@RequestBody final PasswordRestDTO password) {
        return this.userAdministrationFacade.updatePassword(password.getUserId(), password.getPassword());
    }

    @PostMapping("/sackusers")
    public ResponseEntity<ResponseDataDTO> sackusers(@RequestBody final List<ActiveUserDto> userList) {
        if (CollectionUtils.isNotEmpty(userList)) {

            this.userAdministrationService.sackUsers(userList);
        }

        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SACK_USERS_SUCCESS),
                HttpStatus.OK);
    }

    @PostMapping("/user/unlock")
    @AdminLog(logType = UserMgmtOperations.UNLOCK_USER)
    public ResponseEntity<ResponseDataDTO> unlockUser(@RequestBody final UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.unlockUser(userAdminDTO.getUserId());
    }

    @PostMapping("/user/active")
    @AdminLog(logType = UserMgmtOperations.UNLOCK_USER)
    public ResponseEntity<ResponseDataDTO> activeUser(@RequestBody final UserAdminDTO userAdminDTO) {

        return this.userAdministrationFacade.activeUser(userAdminDTO.getUserId());
    }


    @PostMapping("/user/updateUser")
    @AdminLog(logType = UserMgmtOperations.UPDATE_USER)
    public ResponseEntity<ResponseDataDTO> updateUser(@RequestBody final UserDto user) {

        return this.userAdministrationFacade.updateUser(user);
    }

    /**
     * Update user params.
     *
     * @param userParamDto the user param dto
     * @return the response entity
     */
    @PostMapping("/user/params/saveUserParams")
    @AdminLog(logType = UserMgmtOperations.UPDATE_USER_PARAMS)
    public ResponseEntity<ResponseDataDTO> updateUserParams(@Validated @RequestBody final UserParamDto userParamDto) {

        return this.userAdministrationFacade.updateUserParams(userParamDto);
    }






    @PostMapping("/user/modifyUserDetails")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> modifyUserDetails(@RequestBody final UserDetailsDto user) {
        return this.userAdministrationFacade.modifyUserDetails(user);
    }

    @PostMapping("/user/modify")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> modifyUser(@RequestBody final UserDetailsDto user) {
        return this.userAdministrationFacade.modifyUser(user);
    }

    @PostMapping("/user/verifyUserDetails")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> verifyUserDetails(@RequestBody final UserDetailsDto user) {
        return this.userAdministrationFacade.verifyUserDetails(user);
    }

    @PostMapping("/user/verify")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> verifyUser(@RequestBody final UserDetailsDto user) {
        ResponseEntity<ResponseDataDTO> responseDataDTO = this.userAdministrationFacade.verifyUser(user);
          this.userService.updatePassword(user.getPassword(),user.getId());
         return responseDataDTO;

    }

    @PostMapping("/user/delete")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> deleteUser(@RequestBody final UserDetailsDto user) {
        return this.userAdministrationFacade.deleteUser(user);
    }


    @PostMapping("/user/inquiry")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> inquiryUserDetails(@RequestBody final UserDetailsDto user) {
        return this.userAdministrationFacade.inquiryUserDetails(user);
    }
}
