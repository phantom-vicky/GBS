package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.manager.McpStatusIntegrationManager;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.bo.RbiBondCentalizedBodEodBo;
import com.isl.gbm.rbibond.dao.RbiBondCentalizedBodEodDAO;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;
import com.isl.gbm.user.management.manager.UserAdministrationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class RbiBondCentalizedBodEodBOImpl implements RbiBondCentalizedBodEodBo {

    @Autowired
   private RbiBondCentalizedBodEodDAO centalizedBODAO;
    @Value("${eod.start.time}")
    private Integer startTime;

    @Value("${eod.end.time}")
    private Integer endTime;

    @Value("${bod.eod.fin.upd.flag}")
    private String isFinUpd;

    @Autowired
    private UserAdministrationManager userAdministrationManager;
    @Autowired
    private McpStatusIntegrationManager mcpStatusIntegrationManager;
    @Autowired
    private TransactionValidator transactionValidator;


    @Override
    public ResponseDataDTO add(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.add(centalizedEodBodDTO);
    }


    @Override
    public ResponseDataDTO getDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.getDate(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.getLevelBranch(centalizedEodBodDTO);
    }

    @Override

    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.startEodProcess(centalizedEodBodDTO);
    }


    @Override
    public ResponseDataDTO getEodDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.getEodDate(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getEodLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODAO.getEodLevelBranch(centalizedEodBodDTO);
    }

    @Override
    public ResponseDataDTO getTimeValidation() {
        Date dt = new Date();
        int hours = dt.getHours();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        if(hours>=startTime && hours<=endTime-1){
            responseDataDTO.setMessage("EOD cannot be initiated between " + startTime.toString() +" to "+ endTime.toString());
        }
        return responseDataDTO;
    }

}
