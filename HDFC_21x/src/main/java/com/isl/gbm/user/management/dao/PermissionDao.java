package com.isl.gbm.user.management.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.model.Permission;
import com.isl.gbm.user.management.model.PermissionType;

import java.util.Collection;
import java.util.List;

/**
 * DAO Interface for user permissions
 *
 * @author anoop.kumar
 */
public interface PermissionDao {

    /**
     * DAO method to create user permission
     *
     * @param permission
     */
    void savePermission(Permission permission);

    /**
     * Gets the permission.
     *
     * @param id the id
     * @return the permission
     */
    Permission getPermission(Integer id);

    /**
     * Gets the permission by name.
     *
     * @param permissionName the permission name
     * @return the permission by name
     */
    Permission getPermissionByName(String permissionName);

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    List<Permission> getPermissions();


    /**
     * Gets the permission types.
     *
     * @return the permission types
     */
    List<PermissionType> getPermissionTypes();

    /**
     * Delete permission.
     *
     * @param id the id
     */
    void deletePermission(Integer id);

    /**
     * Gets the enquiry permission by feature.
     *
     * @param featureId the feature id
     * @return the enquiry permission by feature
     */
    Permission getEnquiryPermissionByFeature(Integer featureId);

    /**
     * Gets the parent permissions.
     *
     * @return the parent permissions
     */
    List<Permission> getParentPermissions();

    ResponseDataDTO getMenuLinks();

    Collection<? extends String> getPermissionName();
}
