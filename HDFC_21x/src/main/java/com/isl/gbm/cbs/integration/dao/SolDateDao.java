/*
 *
 */
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.model.GbmSolDate;
import com.isl.gbm.common.dto.ResponseDataDTO;

/**
 * The Interface SolDateDao.
 */
public interface SolDateDao {

    /**
     * getSolDateRecord
     *
     * @return the SolDate
     */
    public ResponseDataDTO getSolDateRecordInquiry();

    public GbmSolDate getSolDateRecord();



    /**
     * Save or update.
     *
     * @paramsol date the sol date
     */
   // public void saveOrUpdate(GbmSolDate solDate);

    ResponseDataDTO saveDetailsSolDate(SolDateDto dto);

}
