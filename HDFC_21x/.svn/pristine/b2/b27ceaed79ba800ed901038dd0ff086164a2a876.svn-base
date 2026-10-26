package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.TrailRunBrokerageBO;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;
import com.isl.gbm.rbibond.service.TrailRunBrokerageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrailRunBrokerageServiceimpl implements TrailRunBrokerageService {

    @Autowired
    private TrailRunBrokerageBO trailRunBrokerageBO;

    @Override
    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.getBrokerDetails(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO add(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.add(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.getTrailRun(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.getTrailRunVerDetail(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.getAgencyCodeAddTor(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.getAgencyCodeVerTor(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.verifySelect(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO verify(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.verify(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO delete(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.delete(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO download(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageBO.download(trailRunBrokerageDTO);
    }


}
