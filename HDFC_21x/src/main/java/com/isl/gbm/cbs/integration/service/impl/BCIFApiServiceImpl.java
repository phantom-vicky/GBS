package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.wrapper.BCIFRequestDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.BCIFResponseDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.cbs.integration.manager.BCIFApiManager;
import com.isl.gbm.cbs.integration.service.BCIFApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BCIFApiServiceImpl implements BCIFApiService {

    @Autowired
    BCIFApiManager manager;
    @Override
    public BCIFResponseDTO getBcifDetails(BCIFRequestDTO bcifRequestDTO) {
        return this.manager.getBcifDetails(bcifRequestDTO.getId());
    }
}
