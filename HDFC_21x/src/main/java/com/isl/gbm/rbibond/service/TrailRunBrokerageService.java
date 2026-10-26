package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;

public interface TrailRunBrokerageService {


    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO add(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO trailRunBrokerageDTO);
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO verify(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO delete(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO download(TrailRunBrokerageDTO trailRunBrokerageDTO);
}
