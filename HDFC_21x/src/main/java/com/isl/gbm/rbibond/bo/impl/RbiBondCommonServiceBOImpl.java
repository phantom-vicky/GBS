package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.CommonDataDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondCommonServiceBO;
import com.isl.gbm.rbibond.dao.RbiBondCommonServiceDAO;
import com.isl.gbm.rbibond.dto.RbiBondCommonGenericTableDto;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondCommonServiceBOImpl implements RbiBondCommonServiceBO {

    @Autowired
    private RbiBondCommonServiceDAO efreightCommonServiceDAO;

    @Override
    public ResponseDataDTO getModuleDetails(RbiBondMetaDataRequestDto efreightSchemeParameterDTO) {
        return efreightCommonServiceDAO.getModuleDetails(efreightSchemeParameterDTO);
    }
    @Override
    public ResponseDataDTO getModule(RbiBondMetaDataRequestDto efreightSchemeParameterDTO) {
        return efreightCommonServiceDAO.getModule(efreightSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO getFinancialYear(RbiBondMetaDataRequestDto efreightSchemeParameterDTO) {
        return efreightCommonServiceDAO.getFinancialYear(efreightSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO getMetaData(String referenceId) {
        return efreightCommonServiceDAO.getMetaData(referenceId);
    }
    @Override
    public ResponseDataDTO getDocumentDetails() {
        return efreightCommonServiceDAO.getDocumentDetails();
    }
    @Override
    public ResponseDataDTO getRemarksList(String referenceId) {
        return efreightCommonServiceDAO.getRemarksList(referenceId);
    }


    @Override
    public ResponseDataDTO getMetaDatList(String listType) {
        return efreightCommonServiceDAO.getMetaDatList(listType);
    }

    @Override
    public ResponseDataDTO genericTableOperationsBySp(RbiBondCommonGenericTableDto efreightCommonGenericTableDto) {
        switch (efreightCommonGenericTableDto.getSp()) {
            case CommonConstants.SP_SELECT_UNVERIFIED: {
                return efreightCommonServiceDAO.genericSelectUnverified(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_SELECT: {
                return efreightCommonServiceDAO.genericSelect(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_INSERT: {
                return efreightCommonServiceDAO.genericTableInsert(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_MODIFY: {
                return efreightCommonServiceDAO.genericTableModify(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_SELECT_VERIFY: {
                return efreightCommonServiceDAO.genericTableVerifySelect(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_VERIFY: {
                return efreightCommonServiceDAO.genericTableVerify(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_DELETE: {
                return efreightCommonServiceDAO.genericTableDelete(efreightCommonGenericTableDto);
            }
            case CommonConstants.SP_INQUIRY: {
                return efreightCommonServiceDAO.genericTableInquirySelect(efreightCommonGenericTableDto);
            }
            default: {
                System.err.println("Invalid SP");
            }
        }

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage("Invalid SP");
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getGbmSolDate() {
        return efreightCommonServiceDAO.getGbmSolDate();
    }

    @Override
    public ResponseDataDTO getCommonMetaData(CommonDataDTO requestDTO){
        return efreightCommonServiceDAO.getCommonMetaData(requestDTO);
    }
}