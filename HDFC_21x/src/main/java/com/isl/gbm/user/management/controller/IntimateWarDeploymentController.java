package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.controller.facad.IntimateWarDeploymentFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/user")
public class IntimateWarDeploymentController {

    @Autowired
    private IntimateWarDeploymentFacade intimateWarDeploymentFacade;


    @PostMapping(path = "/getWarDeploymentDetails")
    public ResponseEntity<ResponseDataDTO> getWarDeploymentDetails() {
        return APIResponseBuilder.buildResponseFromDto(intimateWarDeploymentFacade.getWarDeploymentDetails());
    }
}
