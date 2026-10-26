package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.manager.RoleAdministrationManager;
import com.isl.gbm.user.management.service.RoleAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * The Class RoleAdministrationServiceImpl.
 */
@Service
@Transactional
public class RoleAdministrationServiceImpl implements RoleAdministrationService {

    /**
     * The role administration manager.
     */
    @Autowired
    private RoleAdministrationManager roleAdministrationManager;


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.service.RoleAdministrationService#createRole(com.
     * isl.gbm.user.management.dto.RoleDto)
     */
    @Override
    public void createRole(RoleDto roleDto) {
        this.roleAdministrationManager.createRole(roleDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.service.RoleAdministrationService#isRoleExists(
     * java.lang.String)
     */
    @Override
    public Boolean isRoleExists(Integer roleId) {
        return this.roleAdministrationManager.getRoleById(roleId) != null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#
     * isRoleNameExists(java.lang.String)
     */
    @Override
    public Boolean isRoleNameExists(String name) {
        return this.roleAdministrationManager.getRoleByName(name) != null;
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#createRolePermissionMapping(java.lang.Integer, java.util.Set)
     */
    @Override
    public void createRolePermissionMapping(Integer roleId, Set<Integer> permissionIds) {
        this.roleAdministrationManager.saveRolePermissionMapping(roleId, permissionIds);

    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#getRoles()
     */
    @Override
    public List<RoleDto> getRoles() {
        return this.roleAdministrationManager.getRoles();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#getRolePermissions(java.lang.Integer)
     */
    @Override
    public List<PermissionDto> getRolePermissions(Integer roleId) {
        return this.roleAdministrationManager.getRolePermissions(roleId);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#updateRole(com.isl.gbm.user.management.dto.RoleDto)
     */
    @Override
    public void updateRole(RoleDto roleDto) {
        this.roleAdministrationManager.updateRole(roleDto);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#getRole(java.lang.Integer)
     */
    @Override
    public RoleDto getRole(Integer id) {
        return this.roleAdministrationManager.getRoleById(id);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#deleteRole(java.lang.Integer)
     */
    @Override
    public void deleteRole(Integer id) {
        this.roleAdministrationManager.deleteRole(id);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.RoleAdministrationService#isRoleAssigned(java.lang.Integer)
     */
    @Override
    public Boolean isRoleAssigned(Integer id) {
        return this.roleAdministrationManager.isRoleAssigned(id);
    }


    @Override
    public ResponseDataDTO saveRole(RoleDto roleDto){
        return roleAdministrationManager.saveRole(roleDto);
    }

    @Override
    public ResponseDataDTO modifyRole(RoleDetailsDto roleDto){
        return roleAdministrationManager.modifyRole(roleDto);
    }
    @Override
    public ResponseDataDTO deleteRole(RoleDetailsDto roleDto){
        return roleAdministrationManager.deleteRole(roleDto);
    }
    @Override
    public ResponseDataDTO verifyRole(RoleDetailsDto roleDto){
        return roleAdministrationManager.verifyRole(roleDto);
    }
    @Override
    public ResponseDataDTO inquiryRoleDetails(String roleId){
        return roleAdministrationManager.inquiryRoleDetails(roleId);
    }
    @Override
    public ResponseDataDTO modifyRoleDetails(String roleId){
        return roleAdministrationManager.modifyRoleDetails(roleId);
    }
    @Override
    public ResponseDataDTO verifyRoleDetails(String roleId){
        return roleAdministrationManager.verifyRoleDetails(roleId);
    }
}
