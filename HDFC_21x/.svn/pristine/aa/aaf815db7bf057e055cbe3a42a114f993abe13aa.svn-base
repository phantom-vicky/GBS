package com.isl.gbm.user.management.controller;

import com.isl.gbm.user.management.dto.FeatureDto;
import com.isl.gbm.user.management.dto.ModuleDto;
import com.isl.gbm.user.management.service.UserManagementUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The Class UserManagementUtilController.
 */
@RestController
@RequestMapping("/gbmultra/user")
public class UserManagementUtilController {

    /**
     * The user management util manager.
     */
    @Autowired
    private UserManagementUtilService userManagementUtilService;

    /**
     * Instantiates a new user management util controller.
     *
     * @return the modules
     * @paramuserManagementUtilService the user management util service
     * <p>
     * /**
     * Gets the modules.
     */
    @PostMapping("/module/all")
    public ResponseEntity<List<ModuleDto>> getModules() {
        final List<ModuleDto> modules = this.userManagementUtilService.getModules();
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    /**
     * Gets the features.
     *
     * @param moduleId the module id
     * @return the features
     */
    @GetMapping("/module/{module_id}/feature/all")
    public ResponseEntity<List<FeatureDto>> getFeatures(@PathVariable("module_id") final String moduleId) {
        final List<FeatureDto> features = this.userManagementUtilService.getFeatures(moduleId);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }
}
