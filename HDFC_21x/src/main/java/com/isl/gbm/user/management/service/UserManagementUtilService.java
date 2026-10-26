package com.isl.gbm.user.management.service;

import com.isl.gbm.user.management.dto.FeatureDto;
import com.isl.gbm.user.management.dto.ModuleDto;

import java.util.List;

/**
 * @author anoop.kumar
 */
public interface UserManagementUtilService {

    /**
     * Gets the modules.
     *
     * @return the modules
     */
    List<ModuleDto> getModules();

    /**
     * Gets the features.
     *
     * @param moduleId
     * @return the features
     */
    List<FeatureDto> getFeatures(String moduleId);

    /**
     * Checks if is valid feature id.
     *
     * @param featureId the feature id
     * @return true, if is valid feature id
     */
    Boolean isValidFeatureId(int featureId);
}
