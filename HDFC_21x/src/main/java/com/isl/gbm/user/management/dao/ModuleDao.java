package com.isl.gbm.user.management.dao;

import com.isl.gbm.user.management.model.Module;

import java.util.List;

/**
 * The Interface ModuleDao.
 */
public interface ModuleDao {

    /**
     * DAO method to get all active modules.
     *
     * @return the modules
     */
    List<Module> getModules();
}
