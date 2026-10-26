package com.isl.gbm.user.management.validator;


import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.service.PermissionAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class UserPermissionValidator.
 */
@Component
public class PermissionAdministrationValidator {

    /**
     * The permission administration service.
     */
    @Autowired
    private PermissionAdministrationService permissionAdministrationService;

    /**
     * Sets the permission administration service.
     *
     * @param permissionAdministrationService the new permission administration service
     */

    /**
     * Checks if is permission exists.
     *
     * @param permissionName the permission name
     * @return the boolean
     */
    public Boolean isPermissionExists(String permissionName) {
        return this.permissionAdministrationService.isPermissionExists(permissionName);
    }

    /**
     * Checks if is parent permission.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean isParentPermission(Integer id) {
        PermissionDto permission = this.permissionAdministrationService.getPermission(id);
        return UserConstants.PARENT_PERMISISONS.contains(permission.getDisplayName());
    }

}
