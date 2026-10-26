package com.isl.gbm.user.management.manager;

import com.isl.gbm.user.management.dao.FeatureDao;
import com.isl.gbm.user.management.dao.ModuleDao;
import com.isl.gbm.user.management.dto.FeatureDto;
import com.isl.gbm.user.management.dto.ModuleDto;
import com.isl.gbm.user.management.model.Feature;
import com.isl.gbm.user.management.model.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManagementUtilManager {

    /**
     * The module dao.
     */
    @Autowired
    private ModuleDao moduleDao;

    /**
     * The feature dao.
     */
    @Autowired
    private FeatureDao featureDao;

    /**
     * Sets the module dao.
     *
     * @return the modules
     * @parammoduleDao the new module dao
     * <p>
     * <p>
     * /**
     * Gets the modules.
     */
    public List<ModuleDto> getModules() {
        List<ModuleDto> moduleDtos = new ArrayList<>();
        List<Module> modules = this.moduleDao.getModules();
        for (Module module : modules) {
            moduleDtos.add(this.mapModuleModelToDto(module));
        }
        return moduleDtos;
    }

    /**
     * Gets the features.
     *
     * @param moduleId the module id
     * @return the features
     */
    public List<FeatureDto> getFeatures(String moduleId) {
        List<FeatureDto> featureDtos = new ArrayList<>();
        List<Feature> features = this.featureDao.getFeatures(moduleId);
        for (Feature feature : features) {
            featureDtos.add(this.mapFeatureModelToDto(feature));
        }
        return featureDtos;
    }

    /**
     * Checks if is valid feature id.
     *
     * @param featureId the feature id
     * @return true, if is valid feature id
     */
    public Boolean isValidFeatureId(int featureId) {
        return this.featureDao.isValidFeatureId(featureId);
    }

    /**
     * Map feature model to dto.
     *
     * @param feature the feature
     * @return the feature dto
     */
    private FeatureDto mapFeatureModelToDto(Feature feature) {
        FeatureDto featureDto = new FeatureDto();
        featureDto.setDescription(feature.getDescription());
        featureDto.setDisplayName(feature.getDisplayName());
        featureDto.setId(feature.getId());
        featureDto.setModuleId(feature.getModuleId());
        featureDto.setName(feature.getName());
        return featureDto;
    }

    /**
     * Map module model to dto.
     *
     * @param module the module
     * @return the module dto
     */
    private ModuleDto mapModuleModelToDto(Module module) {
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setDescription(module.getDescription());
        moduleDto.setDisplayName(module.getDisplayName());
        moduleDto.setId(module.getId());
        moduleDto.setName(module.getName());
        return moduleDto;
    }
}
