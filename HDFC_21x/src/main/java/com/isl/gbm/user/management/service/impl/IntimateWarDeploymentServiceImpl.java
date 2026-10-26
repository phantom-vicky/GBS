package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.IntimateWarDeploymentDAO;
import com.isl.gbm.user.management.service.IntimateWarDeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntimateWarDeploymentServiceImpl implements IntimateWarDeploymentService {

    @Autowired
    private IntimateWarDeploymentDAO intimateWarDeploymentDAO;

    @Override
    public ResponseDataDTO getWarDeploymentDetails() {
        return intimateWarDeploymentDAO.getWarDeploymentDetails();
    }
}
