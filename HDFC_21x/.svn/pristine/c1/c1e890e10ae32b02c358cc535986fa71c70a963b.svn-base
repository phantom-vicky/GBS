package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;

import java.util.List;
import java.util.Set;

/**
 * The Interface RoleAdministrationService.
 */
public interface RoleAdministrationService {

    /**
     * Creates the role.
     *
     * @param roleDto the role dto
     */
    void createRole(RoleDto roleDto);

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    List<RoleDto> getRoles();

    /**
     * Checks if is role exists.
     *
     * @param roleId the name
     * @return the boolean
     */
    Boolean isRoleExists(Integer roleId);

    /**
     * Checks if is role name exists.
     *
     * @param name the name
     * @return the boolean
     */
    Boolean isRoleNameExists(String name);

    /**
     * Creates the role permission mapping.
     *
     * @param roleId        the role id
     * @param permissionIds the permission ids
     */
    void createRolePermissionMapping(Integer roleId, Set<Integer> permissionIds);

    /**
     * Gets the role permissions.
     *
     * @param roleId the role id
     * @return the role permissions
     */
    List<PermissionDto> getRolePermissions(Integer roleId);

    /**
     * Update role.
     *
     * @param roleDto the role dto
     */
    void updateRole(RoleDto roleDto);

    /**
     * Gets the role.
     *
     * @param id the role id
     * @return the role
     */
    RoleDto getRole(Integer id);

    /**
     * Delete role.
     *
     * @param id the role id
     */
    void deleteRole(Integer id);

    /**
     * Checks if is role assigned.
     *
     * @param id the id
     * @return the boolean
     */
    Boolean isRoleAssigned(Integer id);

    ResponseDataDTO saveRole(RoleDto roleDto);

    ResponseDataDTO modifyRole(RoleDetailsDto roleDto);

    ResponseDataDTO deleteRole(RoleDetailsDto roleDto);

    ResponseDataDTO verifyRole(RoleDetailsDto roleDto);

    ResponseDataDTO inquiryRoleDetails(String roleId);

    ResponseDataDTO modifyRoleDetails(String roleId);

    ResponseDataDTO verifyRoleDetails(String roleId);
}
