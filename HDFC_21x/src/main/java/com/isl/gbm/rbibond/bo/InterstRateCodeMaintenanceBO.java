package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;

public interface InterstRateCodeMaintenanceBO {


    ResponseDataDTO add(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);

    ResponseDataDTO modifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);
    //
    ResponseDataDTO modify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);
    //
    ResponseDataDTO verify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);
    //
    ResponseDataDTO unVerifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);
    //
    ResponseDataDTO delete(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);
    //
    ResponseDataDTO inquiry(RateCodeMaintenanceDTO rateCodeMaintenanceDTO);



}
