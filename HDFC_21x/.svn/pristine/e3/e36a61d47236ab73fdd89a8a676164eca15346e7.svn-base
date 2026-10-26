package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.PermissionDao;
import com.isl.gbm.user.management.dao.RoleDao;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.model.Permission;
import com.isl.gbm.user.management.model.Role;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class RoleAdministrationManager.
 */
@Component
public class RoleAdministrationManager {

    /**
     * The role dao.
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * The user dao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * The permission dao.
     */
    @Autowired
    private PermissionDao permissionDao;

    private static final String ENQUIRY = "ENQUIRY";

    /**
     * Sets the role dao.
     *
     * @param roleDao the new role dao
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * Sets the permission dao.
     *
     * @param permissionDao the new permission dao
     */
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Map role dto to model.
     *
     * @param roleDto the role dto
     * @return the role
     */
    private Role mapRoleDtoToModel(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());
        role.setDisableRole(roleDto.getDisableRole() ? 1 : 0);
        role.setCreatedDate(roleDto.getCreatedDate());
        role.setCreatedBy(roleDto.getCreatedByUsername());
        role.setLastModifiedBy(roleDto.getLastModifiedByUsername());
        role.setLastModifiedDate(roleDto.getLastModifiedDate());
        return role;
    }

    /**
     * Map role model to dto.
     *
     * @param role the role
     * @return the role dto
     */
    private RoleDto mapRoleModelToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        roleDto.setDisableRole(role.getDisableRole() == 1 ? true : false);
        roleDto.setCreatedByUsername(role.getCreatedBy());
        roleDto.setCreatedDate(role.getCreatedDate());
        roleDto.setLastModifiedByUsername(role.getLastModifiedBy());
        roleDto.setLastModifiedDate(role.getLastModifiedDate());
        return roleDto;
    }

    /**
     * Map permission model to dto.
     *
     * @param permission the permission
     * @return the permission dto
     */
    private PermissionDto mapPermissionModelToDto(Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getId());
        permissionDto.setFeatureId(permission.getFeatureId());
        permissionDto.setType(permission.getType());
        permissionDto.setDescription(permission.getDescription());
        permissionDto.setDisplayName(permission.getDisplayName());
        return permissionDto;

    }

    /**
     * Creates the role.
     *
     * @param roleDto the role dto
     */
    public void createRole(RoleDto roleDto) {
        roleDto.setDisableRole(false);
        roleDto.setCreatedByUsername(UserAccountUtils.getCurrentUsername());
        roleDto.setCreatedDate(new Date());
        roleDao.saveRole(this.mapRoleDtoToModel(roleDto));
    }

    /**
     * Gets the role by name.
     *
     * @param name the name
     * @return the role by name
     */
    public RoleDto getRoleByName(String name) {

        Role role = this.roleDao.getRoleByName(name);
        if (role == null) {
            return null;
        }
        return this.mapRoleModelToDto(role);
    }

    /**
     * Gets the role by id.
     *
     * @param id the id
     * @return the role by id
     */
    public RoleDto getRoleById(Integer id) {

        Role role = this.roleDao.getRole(id);
        if (role == null) {
            return null;
        }
        return this.mapRoleModelToDto(role);
    }

    /**
     * Save role permission mapping.
     *
     * @param roleId        the role id
     * @param permissionIds the permission ids
     */
    public void saveRolePermissionMapping(int roleId, Set<Integer> permissionIds) {
        Role role = this.roleDao.getRole(roleId);
        Set<Permission> permissionSet = new HashSet<>();
        for (Integer permissionId : permissionIds) {
            Permission permission = this.permissionDao.getPermission(permissionId);
            permissionSet.add(permission);
        }
        this.attachEnquiryPermission(permissionSet);
        role.setPermissions(permissionSet);
        roleDao.saveRole(role);
    }

    /**
     * Attach enquiry permission.
     *
     * @param permissionSet the permission set
     */
    private void attachEnquiryPermission(Set<Permission> permissionSet) {
        Set<Permission> enquiryPermissionsSet = new HashSet<>();
        for (Permission permission : permissionSet) {
            Boolean isEnquiryPermissionAbsent = permissionSet.stream().noneMatch(per -> per.getFeatureId().equals(permission.getFeatureId()) && per.getType().equals(ENQUIRY));
            if (!permission.getType().equals(ENQUIRY) && isEnquiryPermissionAbsent) {
                this.generateEnquiryPermissionSet(permission, enquiryPermissionsSet);
            }
        }
        permissionSet.addAll(enquiryPermissionsSet);
    }

    /**
     * Generate enquiry permission set.
     *
     * @param permission            the permission
     * @param enquiryPermissionsSet the enquiry permissions set
     */
    private void generateEnquiryPermissionSet(Permission permission, Set<Permission> enquiryPermissionsSet) {
        Permission enquiryPermission = this.permissionDao.getEnquiryPermissionByFeature(permission.getFeatureId());
        if (enquiryPermission != null && enquiryPermissionsSet.stream().noneMatch(per -> per.getDisplayName().equals(permission.getDisplayName()))) {
            enquiryPermissionsSet.add(enquiryPermission);
        }
    }

    /**
     * Gets the roles.
     *
     * @return the roles
     */
    public List<RoleDto> getRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : this.roleDao.getRoles()) {
            roleDtos.add(this.mapRoleModelToDto(role));
        }
        return roleDtos;
    }

    /**
     * Gets the role permissions.
     *
     * @param roleId the role id
     * @return the role permissions
     */
    public List<PermissionDto> getRolePermissions(Integer roleId) {
        List<PermissionDto> permissionDtos = new ArrayList<>();
        Role role = this.roleDao.getRole(roleId);
        Set<Permission> permissions = role.getPermissions();
        for (Permission permission : permissions) {
            permissionDtos.add(this.mapPermissionModelToDto(permission));
        }
        return permissionDtos;
    }

    /**
     * Update role.
     *
     * @param roleDto the role dto
     */
    public void updateRole(RoleDto roleDto) {
        Role role = this.roleDao.getRole(roleDto.getId());
        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());
        role.setDisableRole(roleDto.getDisableRole() ? 1 : 0);
        role.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
        role.setLastModifiedDate(new Date());
        this.roleDao.saveRole(role);
    }

    /**
     * Delete role.
     *
     * @param id the id
     */
    public void deleteRole(Integer id) {
        this.roleDao.deleteRole(id);
    }

    /**
     * Checks if is role assigned.
     *
     * @param id the id
     * @return the boolean
     */
    public Boolean isRoleAssigned(Integer id) {
        return !this.userDao.getUsersByRole(id).isEmpty();
    }


    public ResponseDataDTO saveRole(RoleDto roleDto){
        return roleDao.saveRole(roleDto);
    }

    public ResponseDataDTO modifyRole(RoleDetailsDto roleDto){
        return roleDao.modifyRole(roleDto);
    }

    public ResponseDataDTO deleteRole(RoleDetailsDto roleDto){
        return roleDao.deleteRole(roleDto);
    }

    public ResponseDataDTO verifyRole(RoleDetailsDto roleDto){
        return roleDao.verifyRole(roleDto);
    }

    public ResponseDataDTO inquiryRoleDetails(String roleId){
        return roleDao.inquiryRoleDetails(roleId);
    }

    public ResponseDataDTO modifyRoleDetails(String roleId){
        return roleDao.modifyRoleDetails(roleId);
    }

    public ResponseDataDTO verifyRoleDetails(String roleId){
        return roleDao.verifyRoleDetails(roleId);
    }

}
