package com.isl.gbm.user.management.controller.facad;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.RoleDto;
import com.isl.gbm.user.management.dto.UserDetailsDto;
import com.isl.gbm.user.management.dto.UserDto;
import com.isl.gbm.user.management.dto.UserParamDto;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserAdministrationFacade {
    public ResponseEntity<ResponseDataDTO> createUser(UserDto user);

    public ResponseEntity<ResponseDataDTO> createUserRoleMapping(Integer userId, Set<Integer> roleIds);

    public ResponseEntity<ResponseDataDTO> deleteUser(final Integer userId);

    public ResponseEntity<ResponseDataDTO> disableUser(final Integer userId, final Date disabledTill);

    public ResponseEntity<List<UserDto>> getLockedUsers();

    public ResponseEntity<List<RoleDto>> getUserRoles(final Integer userId);

    public ResponseEntity<UserDto> getUser(final Integer userId);

    public ResponseEntity<ResponseDataDTO> getUserByUsername(final String username);

    public ResponseEntity<UserParamDto> getUserParam();

    public ResponseEntity<List<UserDto>> getUsers();

    public ResponseEntity<ResponseDataDTO> updatePassword(final Integer userId, final String password);

    public ResponseEntity<ResponseDataDTO> unlockUser(final Integer userId);

    public ResponseEntity<ResponseDataDTO> updateUser(final UserDto user);

    public ResponseEntity<ResponseDataDTO> updateUserParams(final UserParamDto userParamDto);

    public ResponseEntity<ResponseDataDTO> activeUser(Integer userId);

    ResponseEntity<ResponseDataDTO> modifyUser(UserDto user);

    ResponseEntity<ResponseDataDTO> modifyUser(UserDetailsDto user);


    ResponseEntity<ResponseDataDTO> modifyUserDetails(UserDetailsDto user);

    ResponseEntity<ResponseDataDTO> verifyUserDetails(UserDetailsDto user);

    ResponseEntity<ResponseDataDTO> verifyUser(UserDetailsDto user);

    ResponseEntity<ResponseDataDTO> deleteUser(UserDetailsDto user);

    ResponseEntity<ResponseDataDTO> inquiryUserDetails(UserDetailsDto user);
}
