package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.BondInwardOutwardDTO;
import com.isl.gbm.rbibond.facade.BondInwardOutwardFacade;
import com.isl.gbm.rbibond.service.BondInwardOutwardService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class BondInwardOutwardFacadeimpl implements BondInwardOutwardFacade {


    @Autowired
    private BondInwardOutwardService bondInwardOutwardService;
    @Autowired
    TransactionValidator transactionValidator;

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO getAddInRefNo(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.getAddInRefNo(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO addOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.addOutward(bondInwardOutwardDTO);
    }
    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO rejectOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.rejectOutward(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO addInward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.addInward(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO getPanNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.getPanNoTor(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO inrRefNum(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.inrRefNum(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO upload(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.upload(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO download(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.download(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO getBlaNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.getBlaNoTor(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO getBlaSubsIdTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.getBlaSubsIdTor(bondInwardOutwardDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_INWARD_OUTWARD:ADD")
    public ResponseDataDTO delete(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardService.delete(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO tranValidation(BondInwardOutwardDTO bondInwardOutwardDTO) {
        List<GbmTransactionMasterDto> listadd = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        GbmTransactionMasterDto dtoCredit = new GbmTransactionMasterDto();
        GbmTransactionMasterDto dtoDebit = new GbmTransactionMasterDto();
        TranDetailsDTO dtoChanged = null;
        List<TranDetailsDTO> tranDetailsDTOList = new ArrayList<>();
        dtoDebit.setAcctNum(bondInwardOutwardDTO.getDebitAccNo());
        dtoDebit.setTranLeg(CommonConstants.TRAN_LEG_DEBIT);
//        dtoCredit.setAmount(Double.valueOf(rbiBondAccTransferOutDTO.getTotTransferAmount()));
        listadd.add(dtoDebit);
        String finacleValidationReqString = "";

        finacleValidationReqString = "COL" + "^" + bondInwardOutwardDTO.getDebitAccNo() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + bondInwardOutwardDTO.getDebitAccNumFlgOut() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bondInwardOutwardDTO.getDebitAccNumFlgOut()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


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
