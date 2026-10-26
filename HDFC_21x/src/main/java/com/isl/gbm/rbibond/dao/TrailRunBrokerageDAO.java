package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;

public interface TrailRunBrokerageDAO {

    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO);

    public ResponseDataDTO add(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO bondBrokeragePaymentDTO);
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO verifyResponse(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO verify(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO delete(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

    public ResponseDataDTO download(TrailRunBrokerageDTO bondBrokeragePaymentDTO);

}
