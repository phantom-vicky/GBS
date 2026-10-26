package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.SubscriptionWorkflowBo;
import com.isl.gbm.rbibond.dto.SubcriptionWorkFlowDTO;
import com.isl.gbm.rbibond.service.SubscriptionWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionWorkflowServiceImpl implements SubscriptionWorkflowService {

    @Autowired
    SubscriptionWorkflowBo subscriptionWorkflowBo;


    private static final Logger log = LoggerFactory.getLogger(SubscriptionWorkflowServiceImpl.class);



    @Override
    public ResponseDataDTO getAddDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.getAddDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO add(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.add(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO addValidation(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.addValidation(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO inquiry(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.inquiry(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.verifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO verify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {

        ResponseDataDTO responseDataDTO = subscriptionWorkflowBo.verify(subcriptionWorkFlowDTO);

        /*if(subcriptionWorkFlowDTO.getLastVerifiedBy() == null){
            //verify and transaction
            if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
                SubcriptionWorkFlowDTO dbResponse = (SubcriptionWorkFlowDTO) responseDataDTO.getResult();
                CbsTransactionResponseDetails cbsResponse = transactionManger.postTransactionToCbs(dbResponse.getGbmTransactionMasterDtoList() , dbResponse.getChannelType());

                log.info("Cbs Response In Service Layer : {}" , cbsResponse);
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
                        dbResponse.setCbsTranDescription(cbsResponse.getErrorDescription());
                    }
                    responseDataDTO.setMessage(cbsResponse.getErrorDescription());
                } else {
                    dbResponse.setCbsTranStatus(CommonConstants.NO_STATUS);
                    dbResponse.setCbsTranStatusDescription("CBS Connection Failed");
                    responseDataDTO.setMessage("CBS Connection Failed");
                }
                log.info("Cbs Response In Service Layer to update In DB: {}" , dbResponse.getCbsTranDescription());
                return subscriptionWorkflowBo.verifyFinal(dbResponse);
            }else{
                return responseDataDTO;
            }
        }else{*/
            //normal verify
            return responseDataDTO;






    }


    @Override
    public ResponseDataDTO delete(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.delete(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.modifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO modify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.modify(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getApplicationDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.getApplicationDetails(subcriptionWorkFlowDTO);

    }

    @Override
    public ResponseDataDTO getBrokerDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.getBrokerDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getOtherBankDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowBo.getOtherBankDetails(subcriptionWorkFlowDTO);
    }

//    @Override
//    public ResponseDataDTO validateMonthlyPensionOfficeAccountDetails(SubcriptionWorkFlowDTO pensionPaymentDto) {
//        return subscriptionWorkflowBo.validateSupplementaryDaArrearOfficaAccountDetails(pensionPaymentDto);
//    }

}

