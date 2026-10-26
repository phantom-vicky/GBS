package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.management.controller.facad.PermissionAdministrationFacade;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.service.PermissionAdministrationService;
import com.isl.gbm.user.management.validator.PermissionAdministrationValidator;
import com.isl.gbm.user.management.validator.UserManagementCommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;


/**
 * The Class UserManagementController.
 */
@RestController
@RequestMapping("/gbmultra/user/permission")
public class PermissionAdministrationController {

    /**
     * The user permission service.
     */
    @Autowired
    private PermissionAdministrationService permissionAdministrationService;

    /**
     * The user permission validator.
     */
    @Autowired
    private PermissionAdministrationValidator permissionAdministrationServiceValidator;

    /**
     * The user management common validator.
     */
    @Autowired
    private UserManagementCommonValidator userManagementCommonValidator;
    @Autowired
    PermissionAdministrationFacade permissionAdministrationFacade;

    /**
     * Instantiates a new permission administration controller.
     *
     * @param permissionAdministrationService
     *            the permission administration service
     * @param permissionAdministrationServiceValidator
     *            the permission administration service validator
     * @param userManagementCommonValidator
     *            the user management common validator
     */
/*	public PermissionAdministrationController(PermissionAdministrationService permissionAdministrationService,
			PermissionAdministrationValidator permissionAdministrationServiceValidator,
			UserManagementCommonValidator userManagementCommonValidator) {
		this.permissionAdministrationService = permissionAdministrationService;
		this.permissionAdministrationServiceValidator = permissionAdministrationServiceValidator;
		this.userManagementCommonValidator = userManagementCommonValidator;
	}*/

    /**
     * Creates the permission.
     *
     * @param permissionDto the permission dto
     * @return the response entity
     */
    @PostMapping
    @AdminLog(logType = UserMgmtOperations.CREATE_PERMISSION)
    public ResponseEntity<ResponseDataDTO> createPermission(@Valid @RequestBody final PermissionDto permissionDto) {

        return permissionAdministrationFacade.createPermission(permissionDto);
    }

    @PutMapping
    @AdminLog(logType = UserMgmtOperations.UPDATE_PERMISSION)
    public ResponseEntity<ResponseDataDTO> updatePermission(@Valid @RequestBody final PermissionDto permissionDto) {
        return permissionAdministrationFacade.updatePermission(permissionDto);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    @AdminLog(logType = UserMgmtOperations.DELETE_PERMISSION)
    public ResponseEntity<ResponseDataDTO> deletePermission(@PathVariable("id") Integer id) {
        return permissionAdministrationFacade.deletePermission(id);
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    @GetMapping("/all")
    public ResponseEntity<List<PermissionDto>> getPermissions() {
        return permissionAdministrationFacade.getPermissions();
    }

    /**
     * Gets the permission.
     *
     * @param id the id
     * @return the permission
     */
    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> getPermission(@PathVariable("id") Integer id) {
        final PermissionDto permission = this.permissionAdministrationService.getPermission(id);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    /**
     * Gets the permission types.
     *
     * @return the permission types
     */
    @GetMapping("/types")
    public ResponseEntity<List<String>> getPermissionTypes() {
        return new ResponseEntity<>(this.permissionAdministrationService.getPermissionTypes(), HttpStatus.OK);
    }

    @PostMapping("/getMenuLinks")
    public ResponseEntity<com.isl.gbm.common.dto.ResponseDataDTO> getMenuLinks() {
        return APIResponseBuilder.buildResponseFromDto(permissionAdministrationFacade.getMenuLinks());
    }
}
