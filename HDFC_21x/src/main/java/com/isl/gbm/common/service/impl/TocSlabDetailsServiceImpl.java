package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.bo.TocSlabDetailsBO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import com.isl.gbm.common.service.TocSlabDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TocSlabDetailsServiceImpl implements TocSlabDetailsService {


    @Autowired
    private TocSlabDetailsBO tocSlabDetailsBO;

    @Override
    public ResponseDataDTO selectModifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.selectModifySlab(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO selectVerifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.selectVerifySlab(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO getAddDetails(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.getAddDetails(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO save(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.save(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO modify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.modify(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO verify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.verify(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO delete(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.delete(tocSlabDetailsDto);
    }

    @Override
    public ResponseDataDTO inquiry(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsBO.inquiry(tocSlabDetailsDto);
    }
}
