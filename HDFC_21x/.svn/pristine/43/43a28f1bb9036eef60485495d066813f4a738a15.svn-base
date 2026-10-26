package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.BondBrokeragePaymentDTO;
import com.isl.gbm.rbibond.facade.BondBrokeragePaymentFacade;
import com.isl.gbm.rbibond.service.BondBrokeragePaymentService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class BondBrokeragePaymentFacadeimpl implements BondBrokeragePaymentFacade {


    @Autowired
    private BondBrokeragePaymentService bondBrokeragePaymentService;
    @Autowired
    TransactionValidator transactionValidator;

    @Override
    @RequiresPermissions("")
    public ResponseDataDTO getBrokerDetails(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.getBrokerDetails(bondBrokeragePaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ADD")
    public ResponseDataDTO add(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.add(bondBrokeragePaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ADD")
    public ResponseDataDTO addValidation(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.addValidation(bondBrokeragePaymentDTO);
    }

    @Override
//    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.inquiry(bondBrokeragePaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:VERIFY")
    public ResponseDataDTO verifySelect(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.verifySelect(bondBrokeragePaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.verify(bondBrokeragePaymentDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_BROK_PAYMNT_RBI_BOND:DELETE")
    public ResponseDataDTO delete(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        return bondBrokeragePaymentService.delete(bondBrokeragePaymentDTO);
    }

    @Override
    public ResponseDataDTO tranValidation(BondBrokeragePaymentDTO bondBrokeragePaymentDTO) {
        List<GbmTransactionMasterDto> listadd = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        GbmTransactionMasterDto dtoCredit = new GbmTransactionMasterDto();
        GbmTransactionMasterDto dtoDebit = new GbmTransactionMasterDto();
        TranDetailsDTO dtoChanged = null;
        List<TranDetailsDTO> tranDetailsDTOList = new ArrayList<>();
        dtoDebit.setAcctNum(bondBrokeragePaymentDTO.getDebitAccNo());
        dtoDebit.setTranLeg(CommonConstants.TRAN_LEG_DEBIT);
//        dtoCredit.setAmount(Double.valueOf(rbiBondAccTransferOutDTO.getTotTransferAmount()));
        listadd.add(dtoDebit);
        String finacleValidationReqString = "";

        finacleValidationReqString = "COL" + "^" + bondBrokeragePaymentDTO.getDebitAccNo() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + bondBrokeragePaymentDTO.getDebitAccNumFlgOut() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bondBrokeragePaymentDTO.getDebitAccNumFlgOut()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


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
        return responseDataDTO;    }
}
