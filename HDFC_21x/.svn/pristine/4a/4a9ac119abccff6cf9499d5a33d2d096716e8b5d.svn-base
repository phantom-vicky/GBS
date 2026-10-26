package com.isl.gbm.user.management.util;

import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.dto.PermissionDto;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    /**
     * Instantiates a new permission utils.
     */
    private PermissionUtils() {

    }

    /**
     * Gets the permissions.
     *
     * @return the permissions
     */
    public static List<PermissionDto> getParentPermissions() {
        List<PermissionDto> permissionDtos = new ArrayList<>();

        PermissionDto addPermissionDto = new PermissionDto();
        addPermissionDto.setDisplayName(PermissionConstants.USER_MGMT_PERMISSION_ADD);
        addPermissionDto.setType("ADD");
        addPermissionDto.setFeatureName("PERMISSION");

        PermissionDto enquiryPermissionDto = new PermissionDto();
        enquiryPermissionDto.setDisplayName(PermissionConstants.USER_MGMT_PERMISSION_ENQUIRY);
        enquiryPermissionDto.setType("ENQUIRY");
        enquiryPermissionDto.setFeatureName("PERMISSION");

        permissionDtos.add(addPermissionDto);
        permissionDtos.add(enquiryPermissionDto);
        return permissionDtos;
    }
}
