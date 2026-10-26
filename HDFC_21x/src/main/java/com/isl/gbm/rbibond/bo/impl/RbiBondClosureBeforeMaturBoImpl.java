package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondClosureBeforeMaturBo;
import com.isl.gbm.rbibond.dao.RbiBondClosureBeforeMaturDao;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class RbiBondClosureBeforeMaturBoImpl implements RbiBondClosureBeforeMaturBo {


    @Autowired
    RbiBondClosureBeforeMaturDao rbiBondClosureBeforeMaturDao;
/*
    @Autowired
    TransactionManger transactionManger;*/


    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDao.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDao.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDao.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDao.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO resultDto = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, String> statusMap =  new LinkedHashMap<>();

        try {
            resultDto = rbiBondClosureBeforeMaturDao.verify(rbiBondClosureBeforeMaturDTO);

                /*CbsTransactionResponseDetails cbsResponse;
                TransactionReponseDto transactionReponseDto = new TransactionReponseDto();

                List<GbmTransactionMasterDto> accDetailsList = (List<GbmTransactionMasterDto>) resultDto.getResult();
                cbsResponse = transactionManger.postTransactionToCbs(accDetailsList, accDetailsList.get(0).getTransactionChannel());
                transactionReponseDto.setGbmTranId(accDetailsList.get(0).getTranId());
                transactionReponseDto.setCbsTranId(cbsResponse.getCbsTransactionId());
                transactionReponseDto.setCbsTranStatus(String.valueOf(cbsResponse.getTranStatus()));

                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus())) {
                    transactionReponseDto.setCbsTranStatusDesc(CommonConstants.TRAN_SUCCESS_MSG);
                } else {
                    transactionReponseDto.setGbmTranId(accDetailsList.get(0).getTranId());
                    transactionReponseDto.setCbsTranStatus(CommonConstants.NO_STATUS);
                    transactionReponseDto.setCbsTranStatusDesc(cbsResponse.getErrorDescription());
                }

                responseDataDTO = rbiBondClosureBeforeMaturDao.verifyTranResponse(transactionReponseDto);

                if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {

                    statusMap.put("gbmTranId", transactionReponseDto.getGbmTranId());
                    statusMap.put("cbsTranId", transactionReponseDto.getCbsTranId());
                    statusMap.put("tranStatus", transactionReponseDto.getCbsTranStatus());

                    if(transactionReponseDto.getCbsTranStatus().equalsIgnoreCase(CommonConstants.YES_STATUS)){
                        responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    }
                    else{
                        responseDataDTO.setMessage("CBS TRANSACTION FAILED");
                    }
                    responseDataDTO.setResult(statusMap);
                }
                else{
                    responseDataDTO.setMessage("DATABASE BUSINESS EXCEPTION");
                }

*/
        } catch (Exception e) {
            resultDto.setMessage(e.getMessage());
        }

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDao.delete(rbiBondClosureBeforeMaturDTO);
    }
}
