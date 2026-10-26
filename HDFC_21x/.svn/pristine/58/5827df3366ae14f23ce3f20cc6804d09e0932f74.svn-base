package com.isl.gbm.user.management.manager;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.constants.UserConstants;
import com.isl.gbm.user.management.constants.Users;
import com.isl.gbm.user.management.dao.FeatureDao;
import com.isl.gbm.user.management.dao.PermissionDao;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.model.Feature;
import com.isl.gbm.user.management.model.Permission;
import com.isl.gbm.user.management.model.PermissionType;
import com.isl.gbm.user.management.util.PermissionUtils;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class UserPermissionManager.
 */
@Component
public class PermissionAdministrationManager {

    /**
     * The permission dao.
     */
    @Autowired
    private PermissionDao permissionDao;

    /**
     * The feature dao.
     */
    @Autowired
    private FeatureDao featureDao;


    /**
     * Map permission dto to model.
     *
     * @param permissionDto the permission dto
     * @return the permission
     */
    private Permission mapPermissionDtoToModel(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setId(permissionDto.getId());
        permission.setFeatureId(permissionDto.getFeatureId());
        permission.setType(permissionDto.getType());
        permission.setDescription(permissionDto.getDescription());
        permission.setDisplayName(permissionDto.getDisplayName());
        permission.setCreatedBy(permissionDto.getCreatedByUsername());
        permission.setCreatedDate(permissionDto.getCreatedDate());
        permission.setLastModifiedBy(permissionDto.getLastModifiedByUsername());
        permission.setLastModifiedDate(permissionDto.getLastModifiedDate());
        return permission;
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
        permissionDto.setCreatedByUsername(permission.getCreatedBy());
        permissionDto.setCreatedDate(permission.getCreatedDate());
        permissionDto.setLastModifiedByUsername(permission.getLastModifiedBy());
        permissionDto.setLastModifiedDate(permission.getLastModifiedDate());
        return permissionDto;

    }

    /**
     * Creates the permission.
     *
     * @param permissionDto the permission dto
     */
    public void createPermission(PermissionDto permissionDto) {
        permissionDto.setCreatedByUsername(UserAccountUtils.getCurrentUsername());
        permissionDto.setCreatedDate(new Date());
        this.permissionDao.savePermission(this.mapPermissionDtoToModel(permissionDto));
    }

    /**
     * Gets the permission by name.
     *
     * @param permissionName the permission name
     * @return the permission by name
     */
    public PermissionDto getPermissionByName(String permissionName) {
        Permission permission = this.permissionDao.getPermissionByName(permissionName);
        if (permission == null) {
            return null;
        }
        return this.mapPermissionModelToDto(permission);
    }

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public List<PermissionDto> getPermissions() {
        List<PermissionDto> permissionDtos = new ArrayList<>();
        List<Permission> permissions = this.permissionDao.getPermissions();
        for (Permission permission : permissions) {
            permissionDtos.add(this.mapPermissionModelToDto(permission));
        }
        return permissionDtos;
    }

    /**
     * Gets the permission types.
     *
     * @return the permission types
     */
    public List<String> getPermissionTypes() {
        List<String> permissionTypes = new ArrayList<>();
        for (PermissionType permissionType : this.permissionDao.getPermissionTypes()) {
            permissionTypes.add(permissionType.getType());
        }
        return permissionTypes;
    }

    /**
     * Delete permission.
     *
     * @param id the id
     */
    public void deletePermission(Integer id) {
        this.permissionDao.deletePermission(id);
    }

    /**
     * Gets the permission.
     *
     * @param id the id
     * @return the permission
     */
    public PermissionDto getPermission(Integer id) {
        return this.mapPermissionModelToDto(this.permissionDao.getPermission(id));
    }

    /**
     * Update permission.
     *
     * @param permissionDto the permission dto
     */
    public void updatePermission(PermissionDto permissionDto) {
        Permission permission = this.permissionDao.getPermission(permissionDto.getId());
        permission.setLastModifiedBy(UserAccountUtils.getCurrentUsername());
        permission.setLastModifiedDate(new Date());
        permission.setDescription(permissionDto.getDescription());
        this.permissionDao.savePermission(permission);
    }

    /**
     * Checks if is parent permissions added.
     *
     * @return the boolean
     */
    public Boolean isParentPermissionsAdded() {
        return this.permissionDao.getParentPermissions().size() == UserConstants.PARENT_PERMISSIONS_COUNT;
    }

    /**
     * Inits the method.
     *
     * @throws Exception the exception
     */
    public void initMethod() {
        if (!this.isParentPermissionsAdded()) {
            this.addParentPermissions();
        }
    }

    /**
     * Adds the parent permissions.
     */
    private void addParentPermissions() {
        List<PermissionDto> permissionDtos = PermissionUtils.getParentPermissions();
        for (PermissionDto permissionDto : permissionDtos) {
            if (this.permissionDao.getPermissionByName(permissionDto.getDisplayName()) != null) {
                continue;
            }
            Feature feature = this.featureDao.getFeatureByName(permissionDto.getFeatureName());
            if (feature == null) {
                continue;
            }
            Permission permission = new Permission();
            permission.setFeatureId(feature.getId());
            permission.setCreatedBy(Users.SUPER_ADMIN);
            permission.setCreatedDate(new Date());
            permission.setDescription(permissionDto.getDescription());
            permission.setType(permissionDto.getType());
            permission.setDisplayName(permissionDto.getDisplayName());
            this.permissionDao.savePermission(permission);
        }

    }

    public ResponseDataDTO getMenuLinks() {
        return permissionDao.getMenuLinks();
    }
}
