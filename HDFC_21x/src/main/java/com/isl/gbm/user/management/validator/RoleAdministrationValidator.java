package com.isl.gbm.user.management.validator;


import com.isl.gbm.user.management.service.RoleAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Class RoleAdministrationValidator.
 */
@Component
public class RoleAdministrationValidator {

    /**
     * The role administration service.
     */
    @Autowired
    private RoleAdministrationService roleAdministrationService;


    /**
     * Checks if is role exists.
     *
     * @param roleId the role dto
     * @return the boolean
     */
    public Boolean isRoleExists(Integer roleId) {
        return this.roleAdministrationService.isRoleExists(roleId);
    }

    /**
     * Checks if is role name exists.
     *
     * @param name the name
     * @return the boolean
     */
    public Boolean isRoleNameExists(String name) {
        return this.roleAdministrationService.isRoleNameExists(name);
    }


    /**
     * Checks if is role assigned.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean isRoleAssigned(Integer id) {
        return this.roleAdministrationService.isRoleAssigned(id);
    }

}
