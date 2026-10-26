package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface PanValidationRetriggerBO {

    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO);
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    public ResponseDataDTO validate(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    public ResponseDataDTO validatePanSchedulerRequest();
    public ResponseDataDTO validatePanSchedulerResponse(PanValidationRetriggerResponseDTO response);

}
