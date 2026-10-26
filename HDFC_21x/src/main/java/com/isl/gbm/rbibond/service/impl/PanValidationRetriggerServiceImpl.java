package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.PanValidationRetriggerBO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.service.PanValidationRetriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanValidationRetriggerServiceImpl implements PanValidationRetriggerService {
    @Autowired
    PanValidationRetriggerBO panValidationRetriggerBO;

    @Override
    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO) {
        return panValidationRetriggerBO.details(panValidationRetriggerDTO);
    }
   @Override
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        return panValidationRetriggerBO.verify(panValidationRetriggerResponseDTO);
    }
    @Override
    public ResponseDataDTO validate(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        return panValidationRetriggerBO.validate(panValidationRetriggerResponseDTO);
    }

}
