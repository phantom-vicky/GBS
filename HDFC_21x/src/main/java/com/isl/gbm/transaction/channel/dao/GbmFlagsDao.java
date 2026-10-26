/**
 *
 */
package com.isl.gbm.transaction.channel.dao;

import com.isl.gbm.transaction.channel.model.GbmFlags;

import java.util.List;

/**
 * The Interface GbmFlagsDao.
 *
 * @author Rahul.Kumar
 */
public interface GbmFlagsDao {

    /**
     * Gets the flag details.
     *
     * @return the flag details
     */
    public List<GbmFlags> getFlagDetails();

}
