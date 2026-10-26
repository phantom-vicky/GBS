package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.dto.RbiBondSubAccValidationDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.RbiBondAccTransferOutDTO;
import com.isl.gbm.rbibond.facade.RbiBondAccTransferOutFacade;
import com.isl.gbm.rbibond.service.RbiBondAccTransferOutService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RbiBondAccTransferOutFacadeimpl implements RbiBondAccTransferOutFacade {

    @Autowired
    RbiBondAccTransferOutService rbiBondAccTransferOutService;

    @Autowired
    TransactionValidator transactionValidator;


    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY")
    public ResponseDataDTO getOtherBankDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.getOtherBankDetails(rbiBondAccTransferOutDTO);
    }



    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY")
    public ResponseDataDTO getNomineeDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.getNomineeDetails(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ADD")
    public ResponseDataDTO add(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.add(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ADD")
    public ResponseDataDTO addValidation(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.addValidation(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.inquiry(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR,value = {"GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY","GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:DELETE"})
    public ResponseDataDTO verifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.verifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.verify(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.delete(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.modifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACC_TRN_OUT_RBI_BOND:MODIFY")
    public ResponseDataDTO modify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutService.modify(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO tranValidation(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        List<GbmTransactionMasterDto> listadd = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        GbmTransactionMasterDto dtoCredit = new GbmTransactionMasterDto();
        GbmTransactionMasterDto dtoDebit = new GbmTransactionMasterDto();
        TranDetailsDTO dtoChanged = null;
        List<TranDetailsDTO> tranDetailsDTOList = new ArrayList<>();
        dtoCredit.setAcctNum(rbiBondAccTransferOutDTO.getCreditAccNo());
        dtoCredit.setTranLeg(CommonConstants.TRAN_LEG_CREDIT);
//        dtoCredit.setAmount(Double.valueOf(rbiBondAccTransferOutDTO.getTotTransferAmount()));
        listadd.add(dtoCredit);
        String finacleValidationReqString = "";

         finacleValidationReqString = "COL" + "^" + rbiBondAccTransferOutDTO.getCreditAccNo() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondAccTransferOutDTO.getDdPoAccFlg() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondAccTransferOutDTO.getDdPoAccFlg()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


                com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
                if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
                    List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
                    Map<String, TranDetailsDTO> cbsResponseMap = new HashMap<>();
                    for (TranDetailsDTO dto : transactionList) {
                        if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
                            String[] accNo = dto.getAccountNumber().split("\\^");
                            dto.setAccountNumber(accNo[1]);
                            cbsResponseMap.put(accNo[0], dto);

                        } else {
                            if (dto.getAccountNumber().startsWith(String.valueOf(CommonConstants.TRAN_LEG_DEBIT)))
                                throw new GenericBusinessException(dto.getReason());
                        }
                    }
                    validatorWrapperDTO.setTranDetailsDTOList(transactionList);
                    responseDataDTO.setMessage("SUCCESS");
                    responseDataDTO.setResult(validatorWrapperDTO);
                }

        else {
            responseDataDTO.setMessage("CBS Validation of Account Details Failed");
        }
        return responseDataDTO;
    }




}
