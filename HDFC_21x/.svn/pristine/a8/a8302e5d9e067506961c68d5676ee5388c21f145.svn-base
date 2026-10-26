package com.isl.gbm.user.management.dao;

import com.isl.gbm.user.management.model.Feature;

import java.util.List;


/**
 * The Interface FeatureDao.
 */
public interface FeatureDao {

    /**
     * DAO method to check whether feature id is valid.
     *
     * @param featureId the feature id
     * @return the boolean
     */
    Boolean isValidFeatureId(int featureId);

    /**
     * DAO method to get all active features.
     *
     * @param moduleId the module id
     * @return the features
     */
    List<Feature> getFeatures(String moduleId);

    /**
     * Gets the feature by name.
     *
     * @param featureName the feature name
     * @return the feature by name
     */
    Feature getFeatureByName(String featureName);

}
