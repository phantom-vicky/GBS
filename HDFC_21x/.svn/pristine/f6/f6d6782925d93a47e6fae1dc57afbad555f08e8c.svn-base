package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.PermissionDto;

import java.util.List;

/**
 * The Interface UserPermissionService.
 */
public interface PermissionAdministrationService {

    /**
     * Creates the permission.
     *
     * @param permissionDto the permission dto
     */
    void createPermission(PermissionDto permissionDto);


    /**
     * Checks if is permission exists.
     *
     * @param permissionName the permission name
     * @return the boolean
     */
    Boolean isPermissionExists(String permissionName);


    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    List<PermissionDto> getPermissions();

    /**
     * Gets the permission types.
     *
     * @return the permission types
     */
    List<String> getPermissionTypes();


    /**
     * Delete permission.
     *
     * @param id the id
     */
    void deletePermission(Integer id);


    /**
     * Gets the permission.
     *
     * @param id the id
     * @return the permission
     */
    PermissionDto getPermission(Integer id);


    /**
     * Update permission.
     *
     * @param permissionDto the permission dto
     */
    void updatePermission(PermissionDto permissionDto);

    ResponseDataDTO getMenuLinks();
}
