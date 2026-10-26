package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.user.management.dto.FeatureDto;
import com.isl.gbm.user.management.dto.ModuleDto;
import com.isl.gbm.user.management.manager.UserManagementUtilManager;
import com.isl.gbm.user.management.service.UserManagementUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class UserManagementUtilServiceImpl.
 */
@Service
@Transactional
public class UserManagementUtilServiceImpl implements UserManagementUtilService {

    /**
     * The user mgmt util manager.
     */
    @Autowired
    private UserManagementUtilManager userMgmtUtilManager;


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.service.UserPermissionService#getModules()
     */
    @Override
    public List<ModuleDto> getModules() {
        return this.userMgmtUtilManager.getModules();
    }


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.UserManagementUtilService#getFeatures(java.lang.String)
     */
    @Override
    public List<FeatureDto> getFeatures(String moduleId) {
        return this.userMgmtUtilManager.getFeatures(moduleId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.user.management.service.UserManagementUtilService#
     * isValidFeatureId(int)
     */
    @Override
    public Boolean isValidFeatureId(int featureId) {
        return this.userMgmtUtilManager.isValidFeatureId(featureId);
    }
}
