package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.TrailRunBrokerageBO;
import com.isl.gbm.rbibond.dao.TrailRunBrokerageDAO;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TrailRunBrokerageBOimpl implements TrailRunBrokerageBO {

    @Autowired
    private TrailRunBrokerageDAO trailRunBrokerageDAO;

    @Override
    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.getBrokerDetails(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO add(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.add(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.getTrailRun(trailRunBrokerageDTO);
    }
    @Override
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.getTrailRunVerDetail(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.getAgencyCodeAddTor(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.getAgencyCodeVerTor(trailRunBrokerageDTO);
    }
    @Override
    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.verifySelect(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO verify(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.verify(trailRunBrokerageDTO);

    }

    @Override
    public ResponseDataDTO delete(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.delete(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO download(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageDAO.download(trailRunBrokerageDTO);
    }



}
