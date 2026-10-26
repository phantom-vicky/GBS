package com.isl.gbm.user.management.dao;


import com.isl.gbm.user.management.model.UserParam;

/**
 * The Interface UserParamDao.
 */
public interface UserParamDao {


    /**
     * Save.
     *
     * @param userParam the user param
     */
    void save(UserParam userParam);

    /**
     * Gets the user param.
     *
     * @return the user param
     */
    UserParam getUserParam();
}
