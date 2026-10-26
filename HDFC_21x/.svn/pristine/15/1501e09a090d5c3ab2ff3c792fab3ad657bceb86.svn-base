package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import com.isl.gbm.rbibond.dto.reports.BondLedgerAccDto;
import com.isl.gbm.rbibond.facade.RbiBondCommonServiceFacade;
import com.isl.gbm.rbibond.service.RbiBondCommonServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondCommonServiceFacadeImpl implements RbiBondCommonServiceFacade {

    @Autowired
    private RbiBondCommonServiceService efreightCommonServiceService;


    @Override
    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceService.getModuleDetails(efreightMetaDataRequestDto);
    }
  @Override
    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceService.getModule(efreightMetaDataRequestDto);
    }


    @Override
    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto efreightMetaDataRequestDto) {
        return efreightCommonServiceService.getFinancialYear(efreightMetaDataRequestDto);
    }
    @Override
    public ResponseDataDTO getMetaData(String referenceId) {
        return efreightCommonServiceService.getMetaData(referenceId);
    }
    @Override
    public ResponseDataDTO getDocumentDetails() {
        return efreightCommonServiceService.getDocumentDetails();
    }
    @Override
    public ResponseDataDTO getMetaDatList(String listType) {
        return efreightCommonServiceService.getMetaDatList(listType);
    }

    @Override
    public ResponseDataDTO genericTableOperationsBySp(RbiBondCommonGenericTableDto efreightCommonGenericTableDto) {
        return efreightCommonServiceService.genericTableOperationsBySp(efreightCommonGenericTableDto);
    }

    @Override
    public ResponseDataDTO getGbmSolDate() {
        return efreightCommonServiceService.getGbmSolDate();
    }

    @Override
    public ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO){
        return efreightCommonServiceService.getCommonMetaData(requestDTO);
    }

    @Override
    public ResponseDataDTO getRemarksList(String referenceId) {
        return efreightCommonServiceService.getRemarksList(referenceId);

    }

}
