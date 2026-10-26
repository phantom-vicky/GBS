package com.isl.gbm.admin.service.impl;

import com.isl.gbm.admin.dto.SackUserDto;
import com.isl.gbm.admin.manager.SackUserManager;
import com.isl.gbm.admin.service.SackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SackUserServiceImpl implements SackUserService {

    /**
     * The manager.
     */
    @Autowired
    private SackUserManager manager;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.service.SackUserService#getSackUserList(java.lang.
     * String, java.lang.String)
     */
    @Override
    public List<SackUserDto> getSackUserList(final String userId, final String solId) {

        return this.manager.getSackUserList(userId, solId);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.service.SackUserService#getSolId(java.lang.String)
     */


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.service.SackUserService#getSolId(java.lang.String)
     */
    @Override
    public SackUserDto getSolId(final String userId) {

        return this.manager.getSolId();

    }

    /**
     * Sets the sack user manager.
     *
     * @param manager the new sack user manager
     */
    public void setSackUserManager(final SackUserManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.admin.service.SackUserService#updateSackUserList(java.lang.
     * String, java.lang.String, java.util.List)
     */
    @Override
    public void updateSackUserList(final String userId, final String solId, final List<SackUserDto> dto) {

        this.manager.updateSackUserList(userId, solId, dto);

    }


}
