package com.isl.gbm.user.management.controller;

import com.isl.gbm.cbs.integration.service.OtherBankDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.annotations.UserLog;
import com.isl.gbm.user.management.constants.UserMgmtMessages;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.management.dto.PasswordDto;
import com.isl.gbm.user.management.service.UserService;
import com.isl.gbm.user.management.util.APIResponseBuilder;
import com.isl.gbm.user.management.util.UserAccountUtils;
import com.isl.gbm.user.management.validator.UserValidator;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Set;


/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/gbmultra/user")
public class UserController {

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * The user validator.
     */
    @Autowired
    private UserValidator userValidator;

    @Autowired
    OtherBankDetailsService otherBankDetailsService;

    /**
     * Instantiates a new user controller.
     *
     * @param userService the user service
     * @param userValidator the user validator
     */


    /**
     * Send heartbeat.
     *
     * @return the response entity
     */
    @GetMapping("/heartbeat")
    public ResponseEntity<ResponseDataDTO> sendHeartbeat() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Reset password.
     *
     * @param password the password
     * @return the response entity
     * @paramrequest the request
     */
    @PostMapping("/password/reset")
    @UserLog(logType = UserMgmtOperations.RESET_PASSWORD)
    public ResponseEntity<ResponseDataDTO> resetPassword(@Valid @RequestBody PasswordDto password) {
        if (BooleanUtils.isFalse(this.userValidator.allowOldPasswords())) {
            Integer oldPasswordExclusionCount = this.userService.getUserOldPasswordExclusionCount();
            if (BooleanUtils.isTrue(this.userValidator.isPasswordRecentlyUsed(UserAccountUtils.decodeAesPassword(password.getPassword()), oldPasswordExclusionCount)))
                return APIResponseBuilder.buildErrorResponse(String.format(UserMgmtMessages.ERROR_RESET_PASSWORD, oldPasswordExclusionCount), null,
                        HttpStatus.PRECONDITION_FAILED);
        }
        this.userService.updatePassword(password.getPassword(),null);
        return new ResponseEntity(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_RESET_PASSWORD), HttpStatus.OK);
    }

}
