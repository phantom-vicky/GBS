package com.isl.gbm.user.management.controller.facad;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.PermissionDto;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.RoleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface RoleAdministrationFacade {
    public ResponseEntity<ResponseDataDTO> createRole(RoleDto roleDto);

    public ResponseEntity<ResponseDataDTO> updateRole(RoleDto roleDto);

    public ResponseEntity<RoleDto> getRole(Integer roleId);

    public ResponseEntity<ResponseDataDTO> deleteRole(Integer id);

    public ResponseEntity<ResponseDataDTO> createRolePermissionMapping(Integer roleId, Set<Integer> permissionIds);

    public ResponseEntity<List<RoleDto>> getRoles();

    public ResponseEntity<List<PermissionDto>> getPermissions(Integer roleId);

    ResponseEntity<ResponseDataDTO> saveRole(RoleDto roleDto);

    ResponseEntity<ResponseDataDTO> modifyRole(RoleDetailsDto roleDto);

    ResponseEntity<ResponseDataDTO> deleteRole(RoleDetailsDto roleDto);

    ResponseEntity<ResponseDataDTO> verifyRole(RoleDetailsDto roleDto);

    ResponseEntity<ResponseDataDTO> inquiryRoleDetails(RoleDetailsDto roleDto);

    ResponseEntity<ResponseDataDTO> modifyRoleDetails(RoleDetailsDto roleDto);

    ResponseEntity<ResponseDataDTO> verifyRoleDetails(RoleDetailsDto roleDto);
}
