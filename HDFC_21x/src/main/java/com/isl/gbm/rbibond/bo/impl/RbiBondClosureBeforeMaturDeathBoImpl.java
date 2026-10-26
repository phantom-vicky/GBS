package com.isl.gbm.rbibond.bo.impl;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondClosureBeforeMaturDeathBo;
import com.isl.gbm.rbibond.dao.RbiBondClosureBeforeMaturDeathDao;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class RbiBondClosureBeforeMaturDeathBoImpl implements RbiBondClosureBeforeMaturDeathBo {


    @Autowired
    RbiBondClosureBeforeMaturDeathDao rbiBondClosureBeforeMaturDeathDao;



    @Override
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathDao.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathDao.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathDao.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathDao.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {

        ResponseDataDTO resultDto = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        Map<String, String> statusMap =  new LinkedHashMap<>();

        try {
            resultDto = rbiBondClosureBeforeMaturDeathDao.verify(rbiBondClosureBeforeMaturDTO);

               /* CbsTransactionResponseDetails cbsResponse;
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

                responseDataDTO = rbiBondClosureBeforeMaturDeathDao.verifyTranResponse(transactionReponseDto);

                    statusMap.put("tranStatus", transactionReponseDto.getCbsTranStatus());
                    statusMap.put("tranId", transactionReponseDto.getGbmTranId());

                    if(transactionReponseDto.getCbsTranStatus().equalsIgnoreCase(CommonConstants.YES_STATUS)){
                        responseDataDTO.setResult(CommonConstants.SUCCESS_CONST);
                    }
                    else{
                        responseDataDTO.setResult("CBS TRANSACTION FAILED");
                    }

                    responseDataDTO.setResult(statusMap);

*/
        } catch (Exception e) {
            resultDto.setMessage(e.getMessage());
        }

        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathDao.delete(rbiBondClosureBeforeMaturDTO);
    }
}
