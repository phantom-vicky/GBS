package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BondBrokeragePaymentBO;
import com.isl.gbm.rbibond.dao.BondBrokeragePaymentDAO;
import com.isl.gbm.rbibond.dto.BondBrokeragePaymentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BondBrokeragePaymentBOimpl implements BondBrokeragePaymentBO {


   /* @Autowired
    private TransactionManger transactionManger;*/

    private static final Logger log = LoggerFactory.getLogger(BondBrokeragePaymentBOimpl.class);

    @Autowired
    private BondBrokeragePaymentDAO bondBrokeragePaymentDAO;

    @Override
    public ResponseDataDTO getBrokerDetails(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.getBrokerDetails(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO add(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.add(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.addValidation(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.inquiry(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.verifySelect(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO verify(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        ResponseDataDTO responseDataDTO = bondBrokeragePaymentDAO.verify(bondBrokeragePaymentDTO);

        /*if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
            BondBrokeragePaymentDTO dbResponse = (BondBrokeragePaymentDTO) responseDataDTO.getResult();
            CbsTransactionResponseDetails cbsResponse = transactionManger.postTransactionToCbs(dbResponse.getGbmTransactionMasterDtoList() , dbResponse.getChannelType());

            log.info("Cbs Response In Service Layer: {}", cbsResponse);
            if (StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "SUCCESS")) {
                dbResponse.setCbsTranId(cbsResponse.getCbsTransactionId());
                dbResponse.setCbsTranStatus(cbsResponse.getTranStatus().toString());
                dbResponse.setCbsTranStatusDescription("Successful Transaction");
            } else if (StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "FAILURE")||StringUtils.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus(), "FAILED")) {
                dbResponse.setCbsTranStatus(CommonConstants.NO.toString());
                if (cbsResponse.getTranStatus() != null)
                    dbResponse.setCbsTranStatus(cbsResponse.getTranStatus().toString());
                else
                    dbResponse.setCbsTranStatus(CommonConstants.NO_STATUS);
                if(cbsResponse.getErrorDescription().contains("INSUFFICIENT AVAIL BAL")){
                    dbResponse.setCbsTranStatusDescription("Insufficient Balance");
                } else {
                    dbResponse.setCbsTranStatusDescription(cbsResponse.getErrorDescription());
                }
                responseDataDTO.setMessage(cbsResponse.getErrorDescription());
            } else {
                dbResponse.setCbsTranStatus(CommonConstants.NO_STATUS);
                dbResponse.setCbsTranStatusDescription("CBS Connection Failed");
                responseDataDTO.setMessage("CBS Connection Failed");
            }
            log.info("Cbs Response In Service Layer to update In DB: {}", dbResponse.getCbsTranStatusDescription());
            return bondBrokeragePaymentDAO.verifyResponse(dbResponse);
        }
        else{*/
            return responseDataDTO;

    }

    @Override
    public ResponseDataDTO delete(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentDAO.delete(bondBrokeragePaymentDTO);
    }



}
