package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.UserRoleMappingFacade;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/admin/userrolemapping")
public class UserRoleMappingController {

    @Autowired
    private UserRoleMappingFacade userRoleMappingFacade;


    @PostMapping("/savedetails")
    public ResponseEntity<ResponseDataDTO> getUserRoleMappingDetailsForSave(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.getUserRoleMappingDetailsForSave(userRoleMappingDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDataDTO> save(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.save(userRoleMappingDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.verify(userRoleMappingDTO);
    }

    @PostMapping("inquiry")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.getInquiryDetails(userRoleMappingDTO);
    }

    @PostMapping("modifydetails")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.getModifyDetails(userRoleMappingDTO);
    }

    @PostMapping("modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.modify(userRoleMappingDTO);
    }

    @PostMapping("verifydetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody UserRoleMappingDTO userRoleMappingDTO){
        return userRoleMappingFacade.getVerifyDetails(userRoleMappingDTO);
    }


}
