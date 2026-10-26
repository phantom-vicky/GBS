package com.isl.gbm.rbibond.facade;


import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import com.isl.gbm.rbibond.dto.reports.BondLedgerAccDto;

public interface RbiBondCommonServiceFacade {

    ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto metaDataRequestDto);

    ResponseDataDTO getModule(RbiBondMetaDataRequestDto metaDataRequestDto);

    ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto metaDataRequestDto);

    ResponseDataDTO getMetaData(String referenceId);
    ResponseDataDTO getDocumentDetails();

    ResponseDataDTO getMetaDatList(String listType);

    ResponseDataDTO genericTableOperationsBySp(RbiBondCommonGenericTableDto commonGenericTableDto);

    ResponseDataDTO getGbmSolDate();

    ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO);

    ResponseDataDTO getRemarksList(String referenceId);
}
