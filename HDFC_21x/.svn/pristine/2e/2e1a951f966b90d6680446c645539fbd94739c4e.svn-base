package com.isl.gbm.user.management.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.RolePermissionMappingFacade;
import com.isl.gbm.user.management.dto.RolePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/admin/rolemapping")
public class RolePermissionMappingController {

    @Autowired
    private RolePermissionMappingFacade rolePermissionMappingFacade;

    @PostMapping("/savedetails")
    public ResponseEntity<ResponseDataDTO> getRoleMappingDetailsForSave(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.getRoleMappingDetailsForSave(rolePermissionDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDataDTO> save(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.save(rolePermissionDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.verify(rolePermissionDTO);
    }




    @PostMapping("/delete")
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.delete(rolePermissionDTO);
    }

    @PostMapping("inquiry")
    public ResponseEntity<ResponseDataDTO> getInquiryDetails(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.getInquiryDetails(rolePermissionDTO);
    }

    @PostMapping("/verifydetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.getVerifyDetails(rolePermissionDTO);
    }

    @PostMapping("/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.modify(rolePermissionDTO);
    }

    @PostMapping("/modifydetails")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody RolePermissionDTO rolePermissionDTO){
        return rolePermissionMappingFacade.getModifyDetails(rolePermissionDTO);
    }

}
