package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BrokerMasterMaintainanceBo;
import com.isl.gbm.rbibond.dto.BrokerMasterMainDTO;
import com.isl.gbm.rbibond.service.BrokerMasterMaintainanceService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrokerMasterMaintainanceServiceimpl implements BrokerMasterMaintainanceService {

    @Autowired
    private BrokerMasterMaintainanceBo brokerMasterMaintainanceBo;


    @Override
    public ResponseDataDTO add(BrokerMasterMainDTO brokerMasterMainDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
//            if (null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getPan()) {
//                RbiBondServerSideRegexUtility.validatePAN(brokerMasterMainDTO.getPan());
//            }
//            if (null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getMobileNo()) {
//                RbiBondServerSideRegexUtility.validateMobileNumber(brokerMasterMainDTO.getMobileNo());
//            }
//            if (null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getEmail()) {
//                RbiBondServerSideRegexUtility.validateEmail(brokerMasterMainDTO.getEmail());
//            }
//            if (null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getInstIfscCode()) {
//                RbiBondServerSideRegexUtility.validateIFSC(brokerMasterMainDTO.getInstIfscCode());
//            }
//            if (null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getOperativeAccNo()) {
//                RbiBondServerSideRegexUtility.validateAccountNumber(brokerMasterMainDTO.getOperativeAccNo());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return brokerMasterMaintainanceBo.add(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.addValidation(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.inquiryDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.verifyDeleteDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO verify(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.verify(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO delete(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.delete(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.modifyDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(BrokerMasterMainDTO brokerMasterMainDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(null!=brokerMasterMainDTO&&null!=brokerMasterMainDTO.getColumnValues()) {
            for (ColumnValueDTO columnValueDTO : brokerMasterMainDTO.getColumnValues()) {
                String colName = columnValueDTO.getColName();
                String value = columnValueDTO.getValue();
                try {
//                    if (null != colName && null != value && "C014".equals(colName)) {
//                        RbiBondServerSideRegexUtility.validatePAN(value);
//                    }
//                    if (null != colName && null != value && "C017".equals(colName)) {
//                        RbiBondServerSideRegexUtility.validateMobileNumber(value);
//                    }
//                    if (null != colName && null != value && "C018".equals(colName)) {
//                        RbiBondServerSideRegexUtility.validateEmail(value);
//                    }
//                    if (null != colName && null != value && "C026".equals(colName)) {
//                        RbiBondServerSideRegexUtility.validateIFSC(value);
//                    }
//                    if (null != colName && null != value && "C008".equals(colName)) {
//                        RbiBondServerSideRegexUtility.validateAccountNumber(value);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    responseDataDTO.setMessage(e.getMessage());
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            }
        }
        return brokerMasterMaintainanceBo.modifySubmit(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO getBranchCode(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.getBranchCode(brokerMasterMainDTO);
    }
    @Override
    public ResponseDataDTO getNonHdfcTorch(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceBo.getNonHdfcTorch(brokerMasterMainDTO);
    }

}
