package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;

public interface RbiBondCommonServiceService {

    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);

    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);

    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);

    public ResponseDataDTO getMetaData(String referenceId);
    public ResponseDataDTO getDocumentDetails();
    public ResponseDataDTO getRemarksList(String referenceId);

    ResponseDataDTO getMetaDatList(String listType);

    ResponseDataDTO genericTableOperationsBySp(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO getGbmSolDate();

    ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO);

}
