package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.facade.BondActualInterestPaymentFacade;
import com.isl.gbm.rbibond.service.BondActualInterestPaymentService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BondActualInterestPaymentFacadeImpl implements BondActualInterestPaymentFacade {

    private final BondActualInterestPaymentService paymentService;

    public BondActualInterestPaymentFacadeImpl(final BondActualInterestPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ADD")
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.addDetails(paymentRequestDTO);
    }


    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:ENQUIRY")
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.inquiry(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY",
            "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:UPLOAD", "GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:DOWNLOAD"})
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.verifyDetails(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY")
    public ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.reject(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:VERIFY")
    public ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.verifySubmit(paymentRequestDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:DOWNLOAD")
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.downloadFileFromSFTP(paymentRequestDTO);
    }
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:DOWNLOAD")
    public ResponseDataDTO mergeUnMergeDownloadFile(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentService.mergeUnMergeDownloadFile(paymentRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:BOND_ACTUAL_INTEREST_PAYMENT:UPLOAD")
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String halfYear, String year) {
        return this.paymentService.uploadFile(textFile, schemeCode, halfYear, year);
    }
}