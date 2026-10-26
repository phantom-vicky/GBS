package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.manager.PermissionAdministrationManager;
import com.isl.gbm.user.management.service.PermissionAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class UserPermissionServiceImpl.
 */
@Service
@Transactional
public class PermissionAdministrationServiceImpl implements PermissionAdministrationService {

    /**
     * The user permission manager.
     */
    @Autowired
    private PermissionAdministrationManager permissionAdministrationManager;


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.service.UserPermissionService#createPermission(
     * com.isl.gbm.user.management.dto.PermissionDto)
     */
    @Override
    public void createPermission(PermissionDto permissionDto) {
        this.permissionAdministrationManager.createPermission(permissionDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.service.UserPermissionService#isPermissionExists(
     * java.lang.String)
     */
    @Override
    public Boolean isPermissionExists(String permissionName) {
        return this.permissionAdministrationManager.getPermissionByName(permissionName) != null;
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.PermissionAdministrationService#getPermissions()
     */
    @Override
    public List<PermissionDto> getPermissions() {
        return this.permissionAdministrationManager.getPermissions();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.PermissionAdministrationService#getPermissionTypes()
     */
    @Override
    public List<String> getPermissionTypes() {
        return this.permissionAdministrationManager.getPermissionTypes();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.PermissionAdministrationService#deletePermission(java.lang.Integer)
     */
    @Override
    public void deletePermission(Integer id) {
        this.permissionAdministrationManager.deletePermission(id);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.PermissionAdministrationService#getPermission(java.lang.Integer)
     */
    @Override
    public PermissionDto getPermission(Integer id) {
        return this.permissionAdministrationManager.getPermission(id);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.PermissionAdministrationService#updatePermission(com.isl.gbm.user.management.dto.PermissionDto)
     */
    @Override
    public void updatePermission(PermissionDto permissionDto) {
        this.permissionAdministrationManager.updatePermission(permissionDto);
    }

    @Override
    public ResponseDataDTO getMenuLinks() {
        return permissionAdministrationManager.getMenuLinks();
    }

}
