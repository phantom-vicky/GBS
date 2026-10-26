package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;

public interface PanValidationRetriggerFacade {

    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO);
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
    public ResponseDataDTO validate(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO);
}
