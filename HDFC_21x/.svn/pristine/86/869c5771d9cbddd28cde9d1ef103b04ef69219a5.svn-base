package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.SubscriptionAccClosingOnMaturityDTO;
import com.isl.gbm.rbibond.facade.SubscriptionAccClosingOnMaturityFacade;
import com.isl.gbm.rbibond.service.SubscriptionAccClosingOnMaturityService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SubscriptionAccClosingOnMaturityFacadeimpl implements SubscriptionAccClosingOnMaturityFacade {

    @Autowired
    private SubscriptionAccClosingOnMaturityService subscriptionAccClosingOnMaturityService;


    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ADD")
    public ResponseDataDTO add(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.add(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ADD")
    public ResponseDataDTO addValidation(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.addValidation(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.inquiry(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:VERIFY",
            "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:UPLOAD", "GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:DOWNLOAD"})
    public ResponseDataDTO verifySelect(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.verifySelect(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.verify(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:DELETE")
    public ResponseDataDTO delete(SubscriptionAccClosingOnMaturityDTO subscriptionAccClosingOnMaturityDTO) {
        return subscriptionAccClosingOnMaturityService.delete(subscriptionAccClosingOnMaturityDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:DOWNLOAD")
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO downloadFileFromSFTP) {
        return subscriptionAccClosingOnMaturityService.downloadFileFromSFTP(downloadFileFromSFTP);

    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_CLS_MTY_RBI_BOND:UPLOAD")
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String fromDate, String toDate) {
        return subscriptionAccClosingOnMaturityService.uploadFile(textFile, schemeCode, fromDate, toDate);
    }
}
