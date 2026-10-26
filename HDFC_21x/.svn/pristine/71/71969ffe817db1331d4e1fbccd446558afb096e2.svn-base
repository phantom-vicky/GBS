package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;
import com.isl.gbm.rbibond.facade.AgeBseLkIPdDetlPmteEncasmetFacade;
import com.isl.gbm.rbibond.service.AgeBseLkIPdDetlPmteEncasmetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AgeBseLkIPdDetlPmteEncasmetFacadeImpl implements AgeBseLkIPdDetlPmteEncasmetFacade {

    @Autowired
    private AgeBseLkIPdDetlPmteEncasmetService ageBseLkIPdDetlPmteEncasmetService;


    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ADD")
    public ResponseDataDTO addValidation(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.addValidation(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ADD")
    public ResponseDataDTO add(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.add(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY")
    public ResponseDataDTO verifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.verifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY")
    public ResponseDataDTO verify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.verify(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:MODIFY")
    public ResponseDataDTO modifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.modifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:MODIFY")
    public ResponseDataDTO modify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.modify(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:ENQUIRY")
    public ResponseDataDTO inquiry(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.inquiry(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_AGE_BASED_LOCK_IN_PERIOD:VERIFY")
    public ResponseDataDTO delete(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetService.delete(ageBseLkIPdDetlPmteEncasmetDTO);
    }


}
