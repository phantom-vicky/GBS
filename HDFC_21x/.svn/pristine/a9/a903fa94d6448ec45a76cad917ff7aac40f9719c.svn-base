/*
 *
 */
package com.isl.gbm.cbs.integration.service.impl;


import com.isl.gbm.cbs.integration.dao.SolDateDao;
import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.manager.SolDateManager;
import com.isl.gbm.cbs.integration.service.SolDateService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class SchemeCodesServiceImpl.
 */
@Service
@Transactional
public class SolDateServiceImpl implements SolDateService {

    /**
     * The soldate  manager.
     */
    @Autowired
    private SolDateManager solDateManager;
    @Autowired

    private SolDateDao solDateDao;


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SolDateService#create(com.isl.
     * gbm.cbs.integration.dto.SolDateDto)
     */
 /*   @Override
    public void saveDetailsSolDate(final SolDateDto dto) {
        this.solDateManager.saveDetailsSolDate(dto);
    }*/

    @Override
    public SolDateDto getSolDateRecord() {
        return this.solDateManager.getSolDateRecord();
    }

    /**
     * get sol date record.
     *
     * @return the get sol date
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SolDateService#list()
     */
    @Override
    public ResponseDataDTO getSolDateRecordInquiry() {
        return solDateDao.getSolDateRecordInquiry();
    }


    @Override
    public ResponseDataDTO saveDetailsSolDate(SolDateDto dto) {
        return solDateDao.saveDetailsSolDate(dto);
    }



}
