package com.isl.gbm.user.management.controller.facad.impl;


        import com.isl.gbm.common.dto.ResponseDataDTO;
        import com.isl.gbm.common.util.CommonConstants;
        import com.isl.gbm.user.management.constants.PermissionConstants;
        import com.isl.gbm.user.management.constants.UserMgmtMessages;
        import com.isl.gbm.user.management.controller.facad.UserAdministrationFacade;
        import com.isl.gbm.user.management.dto.RoleDto;
        import com.isl.gbm.user.management.dto.UserDetailsDto;
        import com.isl.gbm.user.management.dto.UserDto;
        import com.isl.gbm.user.management.dto.UserParamDto;
        import com.isl.gbm.user.management.service.UserAdministrationService;
        import com.isl.gbm.user.management.util.APIResponseBuilder;
        import com.isl.gbm.user.management.validator.UserAdministrationValidator;
        import org.apache.commons.lang3.BooleanUtils;
        import org.apache.shiro.authz.annotation.RequiresPermissions;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Component;

        import java.util.Date;
        import java.util.List;
        import java.util.Set;

@Component
public class UserAdministrationFacadeImpl implements UserAdministrationFacade {

    @Autowired
    private UserAdministrationService userAdministrationService;

    /**
     * The user administration validator.
     */
    @Autowired
    private UserAdministrationValidator userAdministrationValidator;

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ADD)
    public ResponseEntity<ResponseDataDTO> createUser(UserDto user) {
        if (BooleanUtils.isTrue(this.userAdministrationValidator.isUsernameExists(user.getUsername()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_EXISTING_USERNAME, null,
                    HttpStatus.CONFLICT);
        }

        //this.userAdministrationService.createUser(user);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.createUser(user));
        //return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ADD_USER), HttpStatus.OK);
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> modifyUserDetails(UserDetailsDto user) {
        /*if (BooleanUtils.isTrue(this.userAdministrationValidator.isUsernameExists(user.getUsername()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_EXISTING_USERNAME, null,
                    HttpStatus.CONFLICT);
        }*/

        //this.userAdministrationService.createUser(user);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.modifyUserDetails(user.getId()));
        //return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ADD_USER), HttpStatus.OK);
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> modifyUser(UserDetailsDto user) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.modifyUser(user));
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> verifyUser(UserDetailsDto user) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.
                verifyUser(user));
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> deleteUser(UserDetailsDto user) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.deleteUser(user));
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> verifyUserDetails(UserDetailsDto user) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.verifyUserDetails(user.getId()));
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ENQUIRY)
    public ResponseEntity<ResponseDataDTO> inquiryUserDetails(UserDetailsDto user) {
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(userAdministrationService.inquiryUserDetails(user.getId()));
    }




    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> modifyUser(UserDto user) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(user.getId()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }

        this.userAdministrationService.modifyUser(user);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_MODIFY_USER), HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> createUserRoleMapping(Integer userId, Set<Integer> roleIds) {
        this.userAdministrationService.createUserRoleMapping(userId, roleIds);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_USER_ROLE_MAPPING),
                HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_DELETE)
    public ResponseEntity<ResponseDataDTO> deleteUser(final Integer userId) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(userId))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.userAdministrationService.deleteUser(userId);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_DELETE_USER),
                HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_DISABLE)
    public ResponseEntity<ResponseDataDTO> disableUser(final Integer userId, final Date disabledTill) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(userId))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.userAdministrationService.disableUser(userId, disabledTill);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_DISABLE_USER),
                HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_LOCKED_USERS_ENQUIRY)
    public ResponseEntity<List<UserDto>> getLockedUsers() {
        return new ResponseEntity(this.userAdministrationService.getLockedUsers(), HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ENQUIRY)
    public ResponseEntity<List<RoleDto>> getUserRoles(final Integer userId) {
        final List<RoleDto> roles = this.userAdministrationService.getUserRoles(userId);
        return new ResponseEntity(roles, HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ENQUIRY)
    public ResponseEntity<UserDto> getUser(final Integer userId) {
        return new ResponseEntity(this.userAdministrationService.getUser(userId), HttpStatus.OK);

    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ENQUIRY)
    public ResponseEntity<ResponseDataDTO> getUserByUsername(final String username) {
        final UserDto user = this.userAdministrationService.getUserByUsername(username);
        if (user == null) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null, HttpStatus.NOT_FOUND);
        }
        return com.isl.gbm.user.management.util.APIResponseBuilder.buildSuccessResponse(CommonConstants.SUCCESS_CONST, user);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_ENQUIRY)
    public ResponseEntity<UserParamDto> getUserParam() {
        return new ResponseEntity(this.userAdministrationService.getUserParam(), HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_ENQUIRY)
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity(this.userAdministrationService.getUsers(), HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_RESET_PASSWORD_MODIFY)
    public ResponseEntity<ResponseDataDTO> updatePassword(final Integer userId, final String password) {
        this.userAdministrationService.updatePassword(userId, password);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_RESET_PASSWORD),
                HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_LOCKED_USERS_MODIFY)
    public ResponseEntity<ResponseDataDTO> unlockUser(final Integer userId) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(userId))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.userAdministrationService.unlockUser(userId);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UNLOCK_USER),
                HttpStatus.OK);
    }

    // @RequiresPermissions(PermissionConstants.USER_MGMT_LOCKED_USERS_MODIFY)
    public ResponseEntity<ResponseDataDTO> activeUser(final Integer userId) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(userId))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }
        this.userAdministrationService.activeUser(userId);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ENABLE_USER),
                HttpStatus.OK);
    }


    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_MODIFY)
    public ResponseEntity<ResponseDataDTO> updateUser(final UserDto user) {
        if (BooleanUtils.isFalse(this.userAdministrationValidator.isUserExists(user.getId()))) {
            return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.USER_NOT_EXISTS, null,
                    HttpStatus.PRECONDITION_FAILED);
        }

        this.userAdministrationService.updateUser(user);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UPDATE_USER),
                HttpStatus.OK);
    }

    @RequiresPermissions(PermissionConstants.USER_MGMT_USER_PARAMS_MODIFY)
    public ResponseEntity<ResponseDataDTO> updateUserParams(final UserParamDto userParamDto) {
        this.userAdministrationService.updateUserParams(userParamDto);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UPDATE_USER_PARAM),
                HttpStatus.OK);
    }
}
