package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.common.dto.RbiBondSubAccValidationDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.SubcriptionWorkFlowDTO;
import com.isl.gbm.rbibond.facade.SubscriptionWorkflowFacade;
import com.isl.gbm.rbibond.service.SubscriptionWorkflowService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class SubscriptionWorkflowFacadeImpl implements SubscriptionWorkflowFacade {

    @Autowired
    SubscriptionWorkflowService subscriptionWorkflowService;

    @Autowired
    TransactionValidator transactionValidator;


    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ADD")
    public ResponseDataDTO getAddDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.getAddDetails(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ADD")
    public ResponseDataDTO add(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.add(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO addValidation(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.addValidation(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.inquiry(subcriptionWorkFlowDTO);
    }


//    private void validateMonthlyPensionOfficeAccountDetails(ResponseDataDTO responseDataDTO) throws Exception {
//
////            PensionCommutationGratuityTdsRefundDto pensionCommutationGratuityTdsRefundResponseDto = new PensionCommutationGratuityTdsRefundDto();
//
//            Map<String, Object> resultMap = (Map<String, Object>) responseDataDTO.getResult();
//            Object accList = resultMap.get("accDetList");
//            Object torchList = resultMap.get("torchList");
//            List<AccountValidationDto> accountValidationDtoList = new ObjectMapper().convertValue(accList, new TypeReference<List<AccountValidationDto>>() {});
//
//
//            if (CommonConstants.SUCCESS_CONST.equals(responseDataDTO.getMessage())) {
//                if (!CollectionUtils.isEmpty(accountValidationDtoList)) {
//                    String finacleValidationReqString = accountValidationDtoList.get(0).getAccountName() + "^" + accountValidationDtoList.get(0).getAccNo() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + accountValidationDtoList.get(0).getIsCentralized() + (CommonConstants.NO_STATUS.equalsIgnoreCase(accountValidationDtoList.get(0).getIsCentralized()) ? ("~" + accountValidationDtoList.get(0).getBranchId()) : (""));
//                    AccountValidationDto tempDto;
//                    for (int i = 1; i < accountValidationDtoList.size(); i++) {
//                        tempDto = accountValidationDtoList.get(i);
//                        finacleValidationReqString += "!" + tempDto.getAccountName() + "^" + tempDto.getAccNo() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + tempDto.getIsCentralized() + (CommonConstants.NO_STATUS.equalsIgnoreCase(tempDto.getIsCentralized()) ? ("~" + tempDto.getBranchId()) : (""));
//                    }
//
//                    com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
//                    if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
//                        List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
//                        Map<String, TranDetailsDTO> cbsResponseMap = new HashMap<>();
//                        for (TranDetailsDTO dto : transactionList) {
//                            if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {
//                                String[] accNo = dto.getAccountNumber().split("\\^");
//                                dto.setAccountNumber(accNo[1]);
//                                cbsResponseMap.put(accNo[0], dto);
//                            } else {
//                                if (dto.getAccountNumber().startsWith(String.valueOf(CommonConstants.TRAN_LEG_DEBIT)))
//                                    throw new GenericBusinessException(dto.getReason());
//                            }
//                        }
//                    } else {
//                        throw new GenericBusinessException(validatorWrapperDTO.getReason());
//                    }
//                }
//            } else {
//                throw new GenericBusinessException(responseDataDTO.getMessage());
//            }

//            pensionCommutationGratuityTdsRefundResponseDto.setTorchList((List<Map<String, Object>>) torchList);
//            responseDataDTO.setResult(pensionCommutationGratuityTdsRefundResponseDto);


//    }

    @Override
    public ResponseDataDTO getTranValidation(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        List<GbmTransactionMasterDto> listadd = new ArrayList<>();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        GbmTransactionMasterDto dtoCredit = new GbmTransactionMasterDto();
        GbmTransactionMasterDto dtoDebit = new GbmTransactionMasterDto();
        TranDetailsDTO dtoChanged = null;

        String finacleValidationReqString = "";


        List<RbiBondSubAccValidationDTO> rbiBondSubAccValidationDTOList = new ArrayList<>();


        if(!subcriptionWorkFlowDTO.getBondCashAccount().isEmpty() && subcriptionWorkFlowDTO.getTranMode().equalsIgnoreCase("C")){
            RbiBondSubAccValidationDTO listData = new RbiBondSubAccValidationDTO();
            listData.setAccNum(subcriptionWorkFlowDTO.getBondCashAccount());
            listData.setAccType(subcriptionWorkFlowDTO.getBondCashAccFlgOut());
            rbiBondSubAccValidationDTOList.add(listData);
        }
        if(!subcriptionWorkFlowDTO.getBondCollectionAccount().isEmpty()){
            RbiBondSubAccValidationDTO listData = new RbiBondSubAccValidationDTO();
            listData.setAccNum(subcriptionWorkFlowDTO.getBondCollectionAccount());
            listData.setAccType(subcriptionWorkFlowDTO.getBondCollectionAccFlgOut());
            rbiBondSubAccValidationDTOList.add(listData);
        }else if(!subcriptionWorkFlowDTO.getBondClearingAccount().isEmpty() && subcriptionWorkFlowDTO.getTranMode().equalsIgnoreCase("CL")){
            RbiBondSubAccValidationDTO listData = new RbiBondSubAccValidationDTO();
            listData.setAccNum(subcriptionWorkFlowDTO.getBondClearingAccount());
            listData.setAccType(subcriptionWorkFlowDTO.getBondClearingAccFlgOut());
            rbiBondSubAccValidationDTOList.add(listData);
        }else if(!subcriptionWorkFlowDTO.getDdPoAccountNo().isEmpty() && subcriptionWorkFlowDTO.getTranMode().equalsIgnoreCase("DD/PO")){
            RbiBondSubAccValidationDTO listData = new RbiBondSubAccValidationDTO();
            listData.setAccNum(subcriptionWorkFlowDTO.getDdPoAccNo());
            listData.setAccType(subcriptionWorkFlowDTO.getDdPoAccFlgOut());
            rbiBondSubAccValidationDTOList.add(listData);
        }else if (!subcriptionWorkFlowDTO.getNeftPlaceHolderAccount().isEmpty()&& subcriptionWorkFlowDTO.getTranMode().equalsIgnoreCase("NEFT")){
            RbiBondSubAccValidationDTO listData = new RbiBondSubAccValidationDTO();
            listData.setAccNum(subcriptionWorkFlowDTO.getNeftPlaceHolderAccount());
            listData.setAccType(subcriptionWorkFlowDTO.getNeftPlaceholderFlgOut());
            rbiBondSubAccValidationDTOList.add(listData);
        }

        if (!CommonConstants.SUCCESS_CONST.equals(responseDataDTO.getMessage())) {
            if (!CollectionUtils.isEmpty(rbiBondSubAccValidationDTOList)) {
                RbiBondSubAccValidationDTO rbiBondSubAccValidationDTO = new RbiBondSubAccValidationDTO();

//                if (!CollectionUtils.isEmpty(accountValidationDtoList)) {
                finacleValidationReqString = rbiBondSubAccValidationDTOList.get(0).getAccName() + "^" + rbiBondSubAccValidationDTOList.get(0).getAccNum() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondSubAccValidationDTO.getAccType() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondSubAccValidationDTO.getAccType()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
//                    AccountValidationDto tempDto;


                for (int i = 1; i < rbiBondSubAccValidationDTOList.size(); i++) {
                    rbiBondSubAccValidationDTO = rbiBondSubAccValidationDTOList.get(i);
                    finacleValidationReqString += "!" + rbiBondSubAccValidationDTO.getAccName() + "^" + rbiBondSubAccValidationDTO.getAccNum() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + rbiBondSubAccValidationDTO.getAccType() + (CommonConstants.NO_STATUS.equalsIgnoreCase(rbiBondSubAccValidationDTO.getAccType()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
                }

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
                    throw new GenericBusinessException(validatorWrapperDTO.getReason());
                }
            }
        } else {
            throw new GenericBusinessException(responseDataDTO.getMessage());
        }
//        ValidatorWrapperDTO validatorResult = transactionValidator.validateAccountDetailsWithoutBalanceCheck(listadd);
//        if (validatorResult.getTranDetailsDTOList() != null) {
//            for (TranDetailsDTO dtos : validatorResult.getTranDetailsDTOList()) {
//                dtoChanged = new TranDetailsDTO();
//                dtoChanged.setAccountNumber(dtos.getAccountNumber().trim());
//                dtoChanged.setIsSysAcct(dtos.getIsSysAcct().trim());
//                tranDetailsDTOList.add(dtoChanged);
//
//            }



        return responseDataDTO;
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:VERIFY", "GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:DELETE"})
    public ResponseDataDTO verifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.verifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.verify(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:DELETE")
    public ResponseDataDTO delete(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.delete(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySelect(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.modifySelect(subcriptionWorkFlowDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUBS_WRK_FLOW_RBI_BOND:MODIFY")
    public ResponseDataDTO modify(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.modify(subcriptionWorkFlowDTO);
    }

    @Override
    public ResponseDataDTO getApplicationDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.getApplicationDetails(subcriptionWorkFlowDTO);

    }

    @Override
    public ResponseDataDTO getBrokerDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.getBrokerDetails(subcriptionWorkFlowDTO);

    }

    @Override
    public ResponseDataDTO getOtherBankDetails(SubcriptionWorkFlowDTO subcriptionWorkFlowDTO) {
        return subscriptionWorkflowService.getOtherBankDetails(subcriptionWorkFlowDTO);
    }
}
