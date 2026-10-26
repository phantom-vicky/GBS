package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "RbiBondInterestPaymentBatchProcessor")
public class RbiBondInterestPaymentBatchProcessor implements ItemProcessor<RbiBondCentralizedInterestTrailRunDtoRequest, RbiBondCentralizedInterestTrailRunDtoRequest> {
    private static final Logger log = LoggerFactory.getLogger(RbiBondInterestPaymentBatchProcessor.class);

   /* @Autowired
    private TransactionManger transactionManger;*/

    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest process(RbiBondCentralizedInterestTrailRunDtoRequest requestDto) {
        try {
            List<GbmTransactionMasterDto> tranList = requestDto.getAcctDetailsList();
            /*if (!CollectionUtils.isEmpty(tranList)) {
                CbsTransactionResponseDetails cbsResponse = transactionManger.postTransactionToCbs(tranList, tranList.get(0).getTransactionChannel());

                requestDto.setGbmTranIdOut(tranList.get(0).getTranId());
                requestDto.setCbsTranIdOut(cbsResponse.getCbsTransactionId());
                requestDto.setCbsTranStatusOut(String.valueOf(cbsResponse.getTranStatus()));
//                log.info("Completed Batch Det - GbmTranId : " + requestDto.getGbmTranIdOut() + " \tCBS TranId : " + requestDto.getCbsTranIdOut());
                if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(cbsResponse.getFinacleResponseStatus())) {
                    requestDto.setCbsTranStatusDesc(CommonConstants.TRAN_SUCCESS_MSG);
                } else {
                    requestDto.setCbsTranStatusOut(CommonConstants.NO_STATUS);
                    requestDto.setCbsTranStatusDesc(cbsResponse.getErrorDescription());
                }
            }*/
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        }
        return requestDto;

    }
}
