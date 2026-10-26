package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.dto.FinacleStatusValidationDTO;
import com.isl.gbm.cbs.integration.dto.McpStatusViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.cbs.integration.manager.McpStatusIntegrationManager;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.bo.RbiBondBodEodBo;
import com.isl.gbm.rbibond.dao.RbiBondBodEodDao;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.dto.BodEodDto;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.manager.UserAdministrationManager;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Component
public class RbiBondBodEodBoImpl implements RbiBondBodEodBo {

    private static final Logger log = LoggerFactory.getLogger(RbiBondBodEodBoImpl.class);

    @Autowired
    private RbiBondBodEodDao rbiBondBodEodDao;
    @Autowired
    private UserAdministrationManager userAdministrationManager;
    @Autowired
    private McpStatusIntegrationManager mcpStatusIntegrationManager;
    @Autowired
    private TransactionValidator transactionValidator;
    @Value("${bod.eod.fin.upd.flag}")
    private String isFinUpd;

    @Value("${eod.start.time}")
    private Integer startTime;

    @Value("${eod.end.time}")
    private Integer endTime;


    @Override
    public ResponseDataDTO startBodProcess(BodEodDto requestDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            List<ActiveUserDto> activeUserDtoList = userAdministrationManager.getActiveOtherUsersInCurrentSolId();
            if (CollectionUtils.isEmpty(activeUserDtoList)) {
                requestDto.setProcTypeIn(CommonConstants.PROC_TYPE_IN_REQUEST);
                responseDataDTO = rbiBondBodEodDao.startBodProcess(requestDto);
                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
                    BodEodDto responseDto = (BodEodDto) responseDataDTO.getResult();
                    if (CommonConstants.YES_STATUS.equalsIgnoreCase(responseDto.getCbsEntryFlag())) {
                        if(isFinUpd.equalsIgnoreCase(CommonConstants.YES_STATUS)){
                            McpStatusViewDto mcpStatusViewDto = new McpStatusViewDto();
                            mcpStatusViewDto.setSolId(UserAccountUtils.getCurrentSolId());
                            mcpStatusViewDto.setSolDate(requestDto.getSolDate());
                            mcpStatusViewDto.setSchCode(CommonConstants.RBI_BOND_MODULE_ID);
                            mcpStatusViewDto.setLevelCode(requestDto.getLevelCode());
                            mcpStatusViewDto.setMcpStatus(CommonConstants.NO);
                            mcpStatusViewDto.setCreatedBy(UserAccountUtils.getCurrentUsername());
                            FinacleStatusValidationDTO finResponse = mcpStatusIntegrationManager.uploadMcpStatus(mcpStatusViewDto);
                            if (CommonConstants.SUCCESS.equalsIgnoreCase(finResponse.getStatus())) {
                                requestDto.setProcTypeIn(CommonConstants.PROC_TYPE_IN_RESPONSE);
                                responseDataDTO = rbiBondBodEodDao.startBodProcess(requestDto);
                            } else {
                                throw new GenericBusinessException(finResponse.getReason());
                            }
                        }
                        else{
                            requestDto.setProcTypeIn(CommonConstants.PROC_TYPE_IN_RESPONSE);
                            responseDataDTO = rbiBondBodEodDao.startBodProcess(requestDto);
                        }

                    }
                }
            } else {
                responseDataDTO.setMessage("Other Users Already Logged In Current Sol Id");
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("activeUserList", activeUserDtoList);
                responseDataDTO.setResult(resultMap);
            }
        } catch (Exception e) {
            responseDataDTO.setMessage(e.getMessage());
        }
        return responseDataDTO;    }


    @Override
    public ResponseDataDTO startLinkCellEodProcess(BodEodDto bodEodDto) {

        ResponseDataDTO resultDto = new ResponseDataDTO();

        try {
            bodEodDto.setProcTypeIn(null);
            bodEodDto.setLevelCode("1");

            resultDto = rbiBondBodEodDao.startEodProcess(bodEodDto);

            if (resultDto.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                if(isFinUpd.equalsIgnoreCase(CommonConstants.YES_STATUS)){
                    updateCbsTables(bodEodDto);
                }
                else{
                    bodEodDto.setCashDepCheckFlg(null);
                    bodEodDto.setProcTypeIn("4");
                }

               if(bodEodDto.getProcTypeIn().equalsIgnoreCase("4")){
                   resultDto = rbiBondBodEodDao.startEodProcess(bodEodDto);
               }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resultDto;
    }

    @Override
    public ResponseDataDTO startPayBranchEodProcess(BodEodDto bodEodDto) {
        bodEodDto.setLevelCode("2");
        ResponseDataDTO resultDto = new ResponseDataDTO();
        BodEodDto responseDto = null;
        try {
            List<ActiveUserDto> activeUserDtoList = userAdministrationManager.getActiveOtherUsersInCurrentSolId();
            if (CollectionUtils.isEmpty(activeUserDtoList)) {
                
                    resultDto = rbiBondBodEodDao.startEodProcess(bodEodDto);
                    responseDto = (BodEodDto) resultDto.getResult();

                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(resultDto.getMessage())) {
                        if(responseDto.getEodFailedReason() != null){

                            return resultDto;
                        }
                        responseDto.setSolDate(bodEodDto.getSolDate());
                        responseDto.setLevelCode(bodEodDto.getLevelCode());
                        resultDto = processTransaction(responseDto);
                        if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(resultDto.getMessage())) {
                            responseDto = (BodEodDto) resultDto.getResult();
                            responseDto.setSolDate(bodEodDto.getSolDate());
                            responseDto.setLevelCode(bodEodDto.getLevelCode());
                            if (CommonConstants.SUCCESS.equalsIgnoreCase(responseDto.getEodStatus()))  // After  Successful EOD
                                if(isFinUpd.equalsIgnoreCase(CommonConstants.YES_STATUS)){
                                    updateCbsTables(bodEodDto);
                                }
                                else{
                                    bodEodDto.setCashDepCheckFlg(null);
                                    bodEodDto.setProcTypeIn("4");
                                }
                               if(bodEodDto.getProcTypeIn().equalsIgnoreCase("4")){
                                resultDto = rbiBondBodEodDao.startEodProcess(bodEodDto);
                               }
                             }

                    } else {
                        return resultDto;
                    }

                
            } else {
                resultDto.setMessage("Other Users Already Logged In Current Sol Id");
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("activeUserList", activeUserDtoList);
                resultDto.setResult(resultMap);
            }
        } catch (Exception e) {
            log.error("Exception : ", e);
            resultDto.setMessage(e.getMessage());
        }
        return resultDto;

    }

    private void updateCbsTables(BodEodDto requestDto) throws Exception {
        McpStatusViewDto mcpStatusViewDto = new McpStatusViewDto();
        mcpStatusViewDto.setSchCode(CommonConstants.RBI_BOND_MODULE_ID);
        mcpStatusViewDto.setSolId(UserAccountUtils.getCurrentSolId());
        mcpStatusViewDto.setSolDate(requestDto.getSolDate());
        mcpStatusViewDto.setLevelCode(requestDto.getLevelCode());
        mcpStatusViewDto.setMcpStatus(CommonConstants.COMPLETED);
        mcpStatusViewDto.setCreatedBy(UserAccountUtils.getCurrentUsername());
        FinacleStatusValidationDTO finResponse = mcpStatusIntegrationManager.updateMcpStatus(mcpStatusViewDto);

        if (CommonConstants.SUCCESS.equalsIgnoreCase(finResponse.getStatus())) {
            requestDto.setCashDepCheckFlg(null);
            requestDto.setProcTypeIn("4");
        } else {
            throw new GenericBusinessException(finResponse.getReason());
        }
    }

    private ResponseDataDTO processTransaction(BodEodDto requestDto) {
        ResponseDataDTO resultDto = new ResponseDataDTO();
        BodEodDto responseDto = null;
        BodEodDto resResponseDto = null;

        try {

            requestDto.setCashDepCheckFlg("DAV");
            requestDto.setProcTypeIn(null);
            resultDto = rbiBondBodEodDao.startEodProcess(requestDto);


            requestDto.setCashDepCheckFlg("DAV");
            requestDto.setProcTypeIn("0");
            resultDto = rbiBondBodEodDao.startEodProcess(requestDto);
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(resultDto.getMessage())) {
                responseDto = (BodEodDto) resultDto.getResult();

                validateCbsAccountDetails(responseDto);         // Validate Office Account Detail
                responseDto.setProcTypeIn("1");
                responseDto.setLevelCode(requestDto.getLevelCode());
                responseDto.setSolDate(requestDto.getSolDate());
                resultDto = rbiBondBodEodDao.startEodProcess(responseDto);
                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(resultDto.getMessage())) {
                    resResponseDto = (BodEodDto) resultDto.getResult();


                    List<GbmTransactionMasterDto> accDetailsList = resResponseDto.getTranList();

                        resResponseDto.setTranList(accDetailsList);
                        postTransactionToCbs(resResponseDto);

                    }

                resResponseDto.setLevelCode(requestDto.getLevelCode());
                resResponseDto.setSolDate(requestDto.getSolDate());
                resResponseDto.setProcTypeIn("2");
                return rbiBondBodEodDao.startEodProcess(resResponseDto);

            } else {
                return resultDto;
            }

        }

        catch (Exception e) {
            resultDto.setMessage(e.getMessage());
        }

        return resultDto;
    }

    private void validateCbsAccountDetails(BodEodDto requesDto) throws Exception {

        List<AccountValidationDto> colDet =  new ArrayList<>();
        List<AccountValidationDto> conDet =  new ArrayList<>();
        List<AccountValidationDto> payDet =  new ArrayList<>();
        List<AccountValidationDto> recDet =  new ArrayList<>();
        String finacleValidationReqString = "";
        AccountValidationDto accValDet = new AccountValidationDto();

        //iterating for schemes
        for(BodEodDto bodEodDto : requesDto.getAccValidationList()){


                finacleValidationReqString += "!" + bodEodDto.getSchemeCode() + "COL"+UserAccountUtils.getCurrentSolId() + "^" + bodEodDto.getBondColAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + bodEodDto.getBondCashCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bodEodDto.getBondCashCenFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
                finacleValidationReqString += "!" + bodEodDto.getSchemeCode() + "CON"+UserAccountUtils.getCurrentSolId() + "^" + bodEodDto.getBondConAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + bodEodDto.getBondConAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bodEodDto.getBondConAccCenFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
//                finacleValidationReqString += "!" + bodEodDto.getSchemeCode() + "PAY"+UserAccountUtils.getCurrentSolId() + "^" + bodEodDto.getBondPayAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + bodEodDto.getBondPayAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bodEodDto.getBondPayAccCenFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));
//                finacleValidationReqString += "!" + bodEodDto.getSchemeCode() + "REC"+UserAccountUtils.getCurrentSolId() + "^" + bodEodDto.getBondRecAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + bodEodDto.getBondRecAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(bodEodDto.getBondRecAccCenFlag()) ? ("~" + UserAccountUtils.getCurrentSolId()) : (""));

        }

        finacleValidationReqString = finacleValidationReqString.substring(1);


//        String finacleValidationReqString = "COL-"+requesDto.getCentalizedEodBodDTOList().get(0).getBranchId() + "^" + requesDto.getAccValidationList().get(0).getBondColAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + requesDto.getAccValidationList().get(0).getBondColAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(requesDto.getAccValidationList().get(0).getBondColAccCenFlag()) ? ("~" + requesDto.getCentalizedEodBodDTOList().get(0).getBranchId()) : (""));
//        finacleValidationReqString += "!" + "CON-"+requesDto.getCentalizedEodBodDTOList().get(0).getBranchId() + "^" + requesDto.getAccValidationList().get(0).getBondConAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + requesDto.getAccValidationList().get(0).getBondConAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(requesDto.getAccValidationList().get(0).getBondConAccCenFlag()) ? ("~" + requesDto.getCentalizedEodBodDTOList().get(0).getBranchId()) : (""));
//        finacleValidationReqString += "!" + "PAY-"+requesDto.getCentalizedEodBodDTOList().get(0).getBranchId() + "^" + requesDto.getAccValidationList().get(0).getBondPayAcc() + "~" + CommonConstants.TRAN_LEG_DEBIT + "~" + requesDto.getAccValidationList().get(0).getBondPayAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(requesDto.getAccValidationList().get(0).getBondPayAccCenFlag()) ? ("~" + requesDto.getCentalizedEodBodDTOList().get(0).getBranchId()) : (""));
//        finacleValidationReqString += "!" + "REC-"+requesDto.getCentalizedEodBodDTOList().get(0).getBranchId() + "^" + requesDto.getAccValidationList().get(0).getBondRecAcc() + "~" + CommonConstants.TRAN_LEG_CREDIT + "~" + requesDto.getAccValidationList().get(0).getBondRecAccCenFlag() + (CommonConstants.NO_STATUS.equalsIgnoreCase(requesDto.getAccValidationList().get(0).getBondRecAccCenFlag()) ? ("~" + requesDto.getCentalizedEodBodDTOList().get(0).getBranchId()) : (""));



        ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateBacidForAcidDetails(finacleValidationReqString);
        if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
            List<TranDetailsDTO> transactionList = validatorWrapperDTO.getTranDetailsDTOList();
            for (TranDetailsDTO dto : transactionList) {
                if (CommonConstants.SUCCESS.equalsIgnoreCase(dto.getValidityStatus())) {

                    accValDet = new AccountValidationDto();
                    String[] accNo = dto.getAccountNumber().split("\\^");
                    if (accNo[0].contains("COL")) {
                        accValDet.setAccNo(accNo[1]);
                        accValDet.setCbsAccType(dto.getIsSysAcct());
                        accValDet.setBranchId(UserAccountUtils.getCurrentSolId());
                        accValDet.setCbsBalance(dto.getCbsBal());
                        accValDet.setSchemeCode(accNo[0].substring(0,4));
                        colDet.add(accValDet);
                        requesDto.setCollectionAccountDet(colDet);

                    } else if (accNo[0].contains("CON")) {
                        accValDet.setAccNo(accNo[1]);
                        accValDet.setCbsAccType(dto.getIsSysAcct());
                        accValDet.setBranchId(UserAccountUtils.getCurrentSolId());
                        accValDet.setCbsBalance(dto.getCbsBal());
                        accValDet.setSchemeCode(accNo[0].substring(0,4));
                        conDet.add(accValDet);
                        requesDto.setConsolidatedAccountDet(conDet);

                    } else if (accNo[0].contains("PAY")) {
                        accValDet.setAccNo(accNo[1]);
                        accValDet.setCbsAccType(dto.getIsSysAcct());
                        accValDet.setBranchId(UserAccountUtils.getCurrentSolId());
                        accValDet.setCbsBalance(dto.getCbsBal());
                        accValDet.setSchemeCode(accNo[0].substring(0,4));
                        payDet.add(accValDet);
                        requesDto.setPayableAccountDet(payDet);

                    } else if (accNo[0].contains("REC")) {
                        accValDet.setAccNo(accNo[1]);
                        accValDet.setCbsAccType(dto.getIsSysAcct());
                        accValDet.setBranchId(UserAccountUtils.getCurrentSolId());
                        accValDet.setCbsBalance(dto.getCbsBal());
                        accValDet.setSchemeCode(accNo[0].substring(0,4));
                        recDet.add(accValDet);
                        requesDto.setReceivableAccountDet(recDet);

                    } else {
                        throw new GenericBusinessException("INVALID ACCOUNT NAME");
                    }
                } else {
                    throw new GenericBusinessException(dto.getReason());
                }
            }
        } else {
            throw new GenericBusinessException(validatorWrapperDTO.getReason());
        }

        // amount validation in collection accounts
        for(AccountValidationDto acc : requesDto.getCollectionAccountDet()){

            for(int i=0; i<requesDto.getAmtValidationList().size(); i++){

                if(acc.getSchemeCode().equalsIgnoreCase(requesDto.getAmtValidationList().get(i).getSchemeCode())
                        && acc.getBranchId().equalsIgnoreCase(requesDto.getAmtValidationList().get(i).getBranchId())
                ){
                    if(Double.parseDouble(acc.getCbsBalance()) < Double.parseDouble(requesDto.getAmtValidationList().get(i).getAmount())){

                        throw new GenericBusinessException("INSUFFICIENT FUNDS IN COLLECTION ACCOUNTS");

                    }

                }

            }


        }


    }

    private void postTransactionToCbs(BodEodDto requestDto) {
/*
        TransactionReponseDto transactionReponseDto = new TransactionReponseDto();
        List<GbmTransactionMasterDto> accDetailsList = requestDto.getTranList();

        if(!accDetailsList.isEmpty() && accDetailsList != null){
            CbsTransactionResponseDetails cbsResponse = transactionManger.postTransactionToCbs(accDetailsList, accDetailsList.get(0).getTransactionChannel());
            transactionReponseDto.setGbmTranId(accDetailsList.get(0).getTranId());
            transactionReponseDto.setCbsTranId(cbsResponse.getCbsTransactionId());
            transactionReponseDto.setCbsTranStatus(String.valueOf(cbsResponse.getTranStatus()));
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus())) {
                transactionReponseDto.setCbsTranStatusDesc(CommonConstants.TRAN_SUCCESS_MSG);
            } else {
                transactionReponseDto.setCbsTranStatus(CommonConstants.NO_STATUS);
                transactionReponseDto.setCbsTranStatusDesc(cbsResponse.getErrorDescription());
            }
            requestDto.setValueFlag("TRAN");
        }
        else{
            requestDto.setCashDepCheckFlg("NT");
        }
        requestDto.setTransactionReponseDto(transactionReponseDto);*/
    }

    @Override
    public ResponseDataDTO getTimeValidation() {
        Date dt = new Date();
        int hours = dt.getHours();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
        if(hours>=startTime && hours<=endTime-1){
            responseDataDTO.setMessage("EOD cannot be initiated between " + startTime.toString() +" to "+ endTime.toString());
        }
        return responseDataDTO;
    }
}
