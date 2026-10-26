package com.isl.gbm.admin.dao;

import com.isl.gbm.admin.model.SackUserMaster;

import java.util.List;

public interface SackUserDao {


    /**
     * Gets the sack user list.
     *
     * @param userId the user id
     * @param solId  the sol id
     * @return the sack user list
     */
    List<SackUserMaster> getSackUserList(final String userId, final String solId);

    /**
     * Gets the sol id.
     *
     * @param solId
     *            the sol id
     * @return the sol id
     */


    /**
     * Gets the user id.
     *
     * @param userId the user id
     * @return the user id
     */
    SackUserMaster getUserId(final String userId);

    /**
     * Update sack user list.
     *
     * @param userId the user id
     * @param solId  the sol id
     * @param master the master
     */
    public void updateSackUserList(final String userId, final String solId, List<SackUserMaster> master);
}
