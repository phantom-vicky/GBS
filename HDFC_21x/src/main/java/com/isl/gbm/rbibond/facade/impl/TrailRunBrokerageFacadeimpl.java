package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.TrailRunBrokerageDTO;
import com.isl.gbm.rbibond.facade.BondBrokeragePaymentFacade;
import com.isl.gbm.rbibond.facade.TrailRunBrokerageFacade;
import com.isl.gbm.rbibond.service.TrailRunBrokerageService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class TrailRunBrokerageFacadeimpl implements TrailRunBrokerageFacade {


    @Autowired
    private TrailRunBrokerageService trailRunBrokerageService;
    @Autowired
    TransactionValidator transactionValidator;

    @Override
    @RequiresPermissions("")
    public ResponseDataDTO getBrokerDetails(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.getBrokerDetails(trailRunBrokerageDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD")
    public ResponseDataDTO add(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.add(trailRunBrokerageDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD")
    public ResponseDataDTO getTrailRun(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.getTrailRun(trailRunBrokerageDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY")
    public ResponseDataDTO getTrailRunVerDetail(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.getTrailRunVerDetail(trailRunBrokerageDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD")
    public ResponseDataDTO getAgencyCodeAddTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.getAgencyCodeAddTor(trailRunBrokerageDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY")
    public ResponseDataDTO getAgencyCodeVerTor(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.getAgencyCodeVerTor(trailRunBrokerageDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY")
    public ResponseDataDTO verifySelect(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.verifySelect(trailRunBrokerageDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY")
    public ResponseDataDTO verify(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.verify(trailRunBrokerageDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:VERIFY")
    public ResponseDataDTO delete(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.delete(trailRunBrokerageDTO);
    }

    @Override
    public ResponseDataDTO tranValidation(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        List<GbmTransactionMasterDto> listadd = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        GbmTransactionMasterDto dtoCredit = new GbmTransactionMasterDto();
        GbmTransactionMasterDto dtoDebit = new GbmTransactionMasterDto();
        TranDetailsDTO dtoChanged = null;
        List<TranDetailsDTO> tranDetailsDTOList = new ArrayList<>();
        dtoDebit.setAcctNum(trailRunBrokerageDTO.getDebitAccNo());
        dtoDebit.setTranLeg(CommonConstants.TRAN_LEG_DEBIT);
//        dtoCredit.setAmount(Double.valueOf(rbiBondAccTransferOutDTO.getTotTransferAmount()));
        listadd.add(dtoDebit);
        String finacleValidationReqString = "";

        finacleValidationReqString = "COL" + "^" + trailRunBrokerageDTO.getDebitAccNo() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + trailRunBrokerageDTO.getDebitAccNumFlgOut() + (CommonConstants.NO_STATUS.equalsIgnoreCase(trailRunBrokerageDTO.getDebitAccNumFlgOut()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));


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

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_TRAIL_RUN_BROKERAGE:ADD")
    public ResponseDataDTO download(TrailRunBrokerageDTO trailRunBrokerageDTO) {
        return trailRunBrokerageService.download(trailRunBrokerageDTO);
    }
}
