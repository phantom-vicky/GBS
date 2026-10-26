package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.facade.BondSubAccClosureBeforeMaturityFacade;
import com.isl.gbm.rbibond.service.BondSubAccClosureBeforeMaturityService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BondSubAccClosureBeforeMaturityFacadeImpl implements BondSubAccClosureBeforeMaturityFacade {

    private final BondSubAccClosureBeforeMaturityService service;

    public BondSubAccClosureBeforeMaturityFacadeImpl(BondSubAccClosureBeforeMaturityService service) {
        this.service = service;
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ADD")
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.service.addDetails(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:ENQUIRY")
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.service.inquiry(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:DOWNLOAD")
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.service.downloadFileFromSFTP(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:UPLOAD")
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String paymentDate) {
        return this.service.uploadFile(textFile, schemeCode, paymentDate);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:VERIFY",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:UPLOAD",
            "GBM_GU_RBIBOND:BOND_SUB_ACCOUNT_CLOSURE_BEFORE_MATURITY:DOWNLOAD"})
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.service.verifyDetails(paymentRequestDTO);
    }
}
