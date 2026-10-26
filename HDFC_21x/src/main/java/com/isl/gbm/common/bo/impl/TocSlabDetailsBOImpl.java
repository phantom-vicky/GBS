package com.isl.gbm.common.bo.impl;

import com.isl.gbm.common.bo.TocSlabDetailsBO;
import com.isl.gbm.common.dao.TocSlabDetailsDao;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TocSlabDetailsBOImpl implements TocSlabDetailsBO {


    @Autowired
    private TocSlabDetailsDao tocSlabDetailsDao;

    @Override
    public ResponseDataDTO selectModifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.selectModifySlab(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO selectVerifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.selectVerifySlab(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO getAddDetails(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.getAddDetails(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO save(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.save(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO modify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.modify(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO verify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.verify(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO delete(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.delete(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO inquiry(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsDao.inquiry(tocSlabDetailsDto);
    }
}
