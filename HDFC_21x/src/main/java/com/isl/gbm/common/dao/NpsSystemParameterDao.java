/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.dao;

import com.isl.gbm.common.model.NpsSystemParameter;
import com.isl.gbm.common.model.NpsSystemParameterDetails;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface NpsSystemParameterDao.
 */
public interface NpsSystemParameterDao {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the nps system parameter
     */
    public NpsSystemParameter findById(String id);

    /**
     * Gets the sys param details.
     *
     * @param parameterId the parameter id
     * @return the sys param details
     */
    public NpsSystemParameter getSysParamDetails(final String parameterId);

    /**
     * Gets the sys parm details list.
     *
     * @return the sys parm details list
     */
    public List<NpsSystemParameterDetails> getSysParamDetailsList();

    public List<NpsSystemParameterDetails> getCommonDetailsList();


    /**
     * Gets the sys parm list.
     *
     * @return the sys parm list
     */
    public List<NpsSystemParameter> getSysParamList();


    public List<NpsSystemParameter> getCommonList();


    /**
     * @param moduleId
     * @return
     */
    public List<NpsSystemParameter> getSysParamModuleList(String moduleId);

    /**
     * Update sys param.
     *
     * @param npsSysParamDtoList the nps sys param dto list
     */
    public void updateSysParam(List<NpsSystemParameter> npsSysParamDtoList);

    /**
     * Verify sys param.
     *
     * @param npsSysParamDtoList the nps sys param dto list
     */
    public void verifySysParam(List<NpsSystemParameter> npsSysParamDtoList);

}
