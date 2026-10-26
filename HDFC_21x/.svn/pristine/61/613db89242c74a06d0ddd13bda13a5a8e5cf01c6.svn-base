package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.annotations.AdminLog;
import com.isl.gbm.user.management.constants.UserMgmtOperations;
import com.isl.gbm.user.management.controller.facad.RoleAdministrationFacade;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleAdminDTO;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.service.RoleAdministrationService;
import com.isl.gbm.user.management.validator.RoleAdministrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;


/**
 * The Class RoleAdministrationController.
 */
@RestController
@RequestMapping("/gbmultra/user/role")
public class RoleAdministrationController {

    /**
     * The role administration service.
     */
    @Autowired
    private RoleAdministrationService roleAdministrationService;

    /**
     * The role administration validator.
     */
    @Autowired
    private RoleAdministrationValidator roleAdministrationValidator;

    @Autowired
    private RoleAdministrationFacade roleAdministrationFacade;

    /**
     * Instantiates a new role administration controller.
     *
     * @param roleAdministrationService the role administration service
     * @param roleAdministrationValidator the role administration validator
     */


    /**
     * Creates the role.
     *
     * @param roleDto the role dto
     * @return the response entity
     */
    @PostMapping
    @AdminLog(logType = UserMgmtOperations.CREATE_ROLE)
    public ResponseEntity<ResponseDataDTO> createRole(@Valid @RequestBody final RoleDto roleDto) {
		/*if (BooleanUtils.isTrue(this.roleAdministrationValidator.isRoleNameExists(roleDto.getName()))) {
			return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_EXISTING_ROLE, null,
					HttpStatus.CONFLICT);
		}

		this.roleAdministrationService.createRole(roleDto);
		return new ResponseEntity<>(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ADD_ROLE),
				HttpStatus.OK)*/
        ;
        return roleAdministrationFacade.createRole(roleDto);
    }

    /**
     * Updates the role.
     *
     * @param roleDto the role dto
     * @return the response entity
     */
    @PutMapping

    @AdminLog(logType = UserMgmtOperations.UPDATE_ROLE)
    public ResponseEntity<ResponseDataDTO> updateRole(@Valid @RequestBody final RoleDto roleDto) {
		/*if (BooleanUtils.isFalse(this.roleAdministrationValidator.isRoleExists(roleDto.getId()))) {
			return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_ROLE_NOT_FOUND, null,
					HttpStatus.NOT_FOUND);
		}

		this.roleAdministrationService.updateRole(roleDto);
		return new ResponseEntity<>(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_UPDATE_ROLE),
				HttpStatus.OK);*/

        return roleAdministrationFacade.updateRole(roleDto);
    }

    @PostMapping("/getRole")
    public ResponseEntity<RoleDto> getRole(@RequestBody RoleAdminDTO roleAdminDTO) {
        return roleAdministrationFacade.getRole(roleAdminDTO.getRoleId());
    }

    /**
     * Delete role.
     *
     * @param id the role id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    @AdminLog(logType = UserMgmtOperations.DELETE_ROLE)
    public ResponseEntity<ResponseDataDTO> deleteRole(@PathVariable("id") Integer id) {

        return roleAdministrationFacade.deleteRole(id);
    }

    @PostMapping("/permission/createRolePermissionMapping")
    @AdminLog(logType = UserMgmtOperations.UPDATE_ROLE_PERMISSION_MAPPING)
    public ResponseEntity<ResponseDataDTO> createRolePermissionMapping(@RequestBody RoleAdminDTO roleAdminDTO) {
		/*if (BooleanUtils.isFalse(this.roleAdministrationValidator.isRoleExists(roleId))) {
			return APIResponseBuilder.buildErrorResponse(UserMgmtMessages.ERROR_ROLE_NOT_FOUND, null,
					HttpStatus.NOT_FOUND);
		}

		this.roleAdministrationService.createRolePermissionMapping(roleId, permissionIds);
		return new ResponseEntity<>(APIResponseBuilder.buildResponse(UserMgmtMessages.SUCCESS_ROLE_PERMISSION_MAPPING),
				HttpStatus.OK);*/
        return roleAdministrationFacade.createRolePermissionMapping(roleAdminDTO.getRoleId(), roleAdminDTO.getPermissionIds());
    }

    /**
     * Gets the roles.
     *
     * @return the roles
     */
/*    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> getRoles() {
        return roleAdministrationFacade.getRoles();
    }*/

    @PostMapping("/permission/getPermissions")
    public ResponseEntity<List<PermissionDto>> getPermissions(@RequestBody RoleAdminDTO roleAdminDTO) {
        //	final List<PermissionDto> permissions = this.roleAdministrationService.getRolePermissions(roleId);
        return roleAdministrationFacade.getPermissions(roleAdminDTO.getRoleId());
    }


     @PostMapping("/create")
    //@AdminLog(logType = UserMgmtOperations.ADD_USER)
    public ResponseEntity<ResponseDataDTO> saveRole(@RequestBody final RoleDto roleDto) {
        return this.roleAdministrationFacade.saveRole(roleDto);
    }

    @PostMapping("/modify")
    public ResponseEntity<ResponseDataDTO> modifyRole(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.modifyRole(roleDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDataDTO> deleteRole(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.deleteRole(roleDto);
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseDataDTO> verifyRole(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.verifyRole(roleDto);
    }
    @PostMapping("/verifydetails")
    public ResponseEntity<ResponseDataDTO> verifyRoleDetails(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.verifyRoleDetails(roleDto);
    }

    @PostMapping("/modifydetails")
    public ResponseEntity<ResponseDataDTO> modifyRoleDetails(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.modifyRoleDetails(roleDto);
    }

    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiryRoleDetails(@RequestBody final RoleDetailsDto roleDto) {
        return this.roleAdministrationFacade.inquiryRoleDetails(roleDto);
    }

}
