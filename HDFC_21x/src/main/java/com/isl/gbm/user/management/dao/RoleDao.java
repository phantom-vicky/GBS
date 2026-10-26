package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.model.Role;

import java.util.List;

/**
 * The Interface RoleDao.
 */
public interface RoleDao {

    /**
     * Creates the role.
     *
     * @param role the role
     */
    void saveRole(Role role);

    /**
     * Gets the role by name.
     *
     * @param name the name
     * @return the role by name
     */
    Role getRoleByName(String name);

    /**
     * Gets the role.
     *
     * @param id the id
     * @return the role
     */
    Role getRole(int id);

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    List<Role> getRoles();

    /**
     * @param roleId
     */
    void deleteRole(Integer roleId);

    ResponseDataDTO saveRole(RoleDto roleDto);

    ResponseDataDTO modifyRole(RoleDetailsDto roleDto);

    ResponseDataDTO deleteRole(RoleDetailsDto roleDto);

    ResponseDataDTO verifyRole(RoleDetailsDto roleDto);

    ResponseDataDTO inquiryRoleDetails(String roleId);

    ResponseDataDTO modifyRoleDetails(String roleId);

    ResponseDataDTO verifyRoleDetails(String roleId);
}
