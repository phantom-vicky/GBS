package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSchemeMasterBO;
import com.isl.gbm.rbibond.dao.RbiBondSchemeMasterDAO;
import com.isl.gbm.rbibond.dto.SchemeMasterRequestResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSchemeMasterBOImpl implements RbiBondSchemeMasterBO{

    @Autowired
    RbiBondSchemeMasterDAO rbiBondSchemeMasterDAO;

    @Override
    public ResponseDataDTO modifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.modifySelect(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.verifySelect(schemeMasterRequestResponseDTO);
    }


    @Override
    public ResponseDataDTO add(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.add(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO inquiry(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.inquiry(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO verify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.verify(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO modify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.modify(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO delete(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterDAO.delete(schemeMasterRequestResponseDTO);
    }
}
