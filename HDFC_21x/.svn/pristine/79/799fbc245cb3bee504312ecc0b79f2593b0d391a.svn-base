package com.isl.gbm.admin.controller;


import com.isl.gbm.admin.dto.SackUserDto;
import com.isl.gbm.admin.service.SackUserService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sackuser")
public class SackUserController {


    @Autowired
    private SackUserService sackUserService;


    /* @Autowired
     */


    /**
     * Builds the response.
     *
     * @param message the message
     * @return the response data DTO
     */
    private ResponseDataDTO buildResponse(final String message) {
        final ResponseDataDTO dataDto = new ResponseDataDTO();
        dataDto.setMessage(message);
        return dataDto;
    }

    /**
     * Gets the sack user list.
     *
     * @return the sack user list
     */
    @GetMapping(value = "/getSackUserList")
    public ResponseEntity<List<SackUserDto>> getSackUserList() {

        final List<SackUserDto> sackUserDtoList = sackUserService.getSackUserList(CommonConstants.USER_ID,
                sackUserService.getSolId(CommonConstants.USER_ID).getSolId());

        return new ResponseEntity<>(sackUserDtoList, HttpStatus.OK);

    }

    /**
     * Gets the sol id.
     *
     * @param userId
     *            the user id
     * @return the sol id
     */

    /**
     * Update sack user list.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDataDTO> updateSackUserList(@RequestBody final List<SackUserDto> dto) {

        sackUserService.updateSackUserList(CommonConstants.USER_ID,
                sackUserService.getSolId(CommonConstants.USER_ID).getSolId(), dto);
        final ResponseDataDTO dataDto = buildResponse("User profile sacked successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

}
