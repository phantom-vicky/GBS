package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondCommonServiceBO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import com.isl.gbm.rbibond.service.RbiBondCommonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondCommonServiceImpl implements RbiBondCommonServiceService {

    @Autowired
    private RbiBondCommonServiceBO efreightCommonServiceBO;

    @Override
    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceBO.getModuleDetails(efreightMetaDataRequestDto);
    }
    @Override
    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceBO.getModule(efreightMetaDataRequestDto);
    }

    @Override
    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceBO.getFinancialYear(efreightMetaDataRequestDto);
    }

    @Override
    public ResponseDataDTO getMetaData(String referenceId) {
        return efreightCommonServiceBO.getMetaData(referenceId);
    }

    @Override
    public ResponseDataDTO getDocumentDetails() {
        return efreightCommonServiceBO.getDocumentDetails();
    }

    @Override
    public ResponseDataDTO getRemarksList(String referenceId) {
        return efreightCommonServiceBO.getRemarksList(referenceId);
    }


    @Override
    public ResponseDataDTO getMetaDatList(String listType) {
        return efreightCommonServiceBO.getMetaDatList(listType);
    }

    @Override
    public ResponseDataDTO genericTableOperationsBySp(RbiBondCommonGenericTableDto efreightCommonGenericTableDto) {
        return efreightCommonServiceBO.genericTableOperationsBySp(efreightCommonGenericTableDto);
    }

    @Override
    public ResponseDataDTO getGbmSolDate() {
        return efreightCommonServiceBO.getGbmSolDate();
    }

    @Override
    public ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO){
        return efreightCommonServiceBO.getCommonMetaData(requestDTO);
    }
}
