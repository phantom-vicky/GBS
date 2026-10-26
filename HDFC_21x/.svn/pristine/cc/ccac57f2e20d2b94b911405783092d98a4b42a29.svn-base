package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface RbiBondCommonServiceDAO {

    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);
    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);

    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto efreightMetaDataRequestDto);

    public ResponseDataDTO getMetaData(String referenceId);
    public ResponseDataDTO getDocumentDetails();
    public ResponseDataDTO getRemarksList(String referenceId);

    ResponseDataDTO getMetaDatList(String listType);

    ResponseDataDTO genericSelect(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableInsert(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableModify(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableVerifySelect(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableVerify(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableDelete(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericSelectUnverified(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO genericTableInquirySelect(RbiBondCommonGenericTableDto efreightCommonGenericTableDto);

    ResponseDataDTO getGbmSolDate();

    ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO);
}
