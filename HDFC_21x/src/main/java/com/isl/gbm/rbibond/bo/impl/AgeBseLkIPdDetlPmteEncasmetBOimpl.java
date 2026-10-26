package com.isl.gbm.rbibond.bo.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.AgeBseLkIPdDetlPmteEncasmetBO;
import com.isl.gbm.rbibond.dao.AgeBseLkIPdDetlPmteEncasmetDAO;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgeBseLkIPdDetlPmteEncasmetBOimpl implements AgeBseLkIPdDetlPmteEncasmetBO {


    @Autowired
    private AgeBseLkIPdDetlPmteEncasmetDAO ageBseLkIPdDetlPmteEncasmetDAO;


    @Override
    public ResponseDataDTO addValidation(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.addValidation(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO add(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.add(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.verifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO verify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.verify(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.modifySelect(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO modify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.modify(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO inquiry(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.inquiry(ageBseLkIPdDetlPmteEncasmetDTO);
    }

    @Override
    public ResponseDataDTO delete(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO) {
        return ageBseLkIPdDetlPmteEncasmetDAO.delete(ageBseLkIPdDetlPmteEncasmetDTO);
    }


}
