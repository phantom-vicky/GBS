package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.SubscriptionWorkflowBo;
import com.isl.gbm.rbibond.dao.SubscriptionWorkflowDao;
import com.isl.gbm.rbibond.dto.SubcriptionWorkFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SubscriptionWorkflowBoImpl implements SubscriptionWorkflowBo {


    @Autowired
    SubscriptionWorkflowDao subscriptionWorkflowDao;

    @Override
    public ResponseDataDTO getAddDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.getAddDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO add(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.add(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO addValidation(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.addValidation(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO inquiry(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.inquiry(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.verifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO verify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.verify(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO verifyFinal(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.verifyFinal(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO delete(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.delete(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.modifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO modify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.modify(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getApplicationDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.getApplicationDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getBrokerDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.getBrokerDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getOtherBankDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowDao.getOtherBankDetails(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO validateMonthlyPensionOfficeAccountDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return null;
    }

//    @Override
//    public ResponseDataDTO validateMonthlyPensionOfficeAccountDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
//        return subscriptionWorkflowDao.validateMonthlyPensionOfficeAccountDetails(subcriptionWorkFlowDTO);
//    }
}
