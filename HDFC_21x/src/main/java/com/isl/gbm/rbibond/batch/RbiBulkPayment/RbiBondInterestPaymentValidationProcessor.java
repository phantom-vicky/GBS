package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.common.exception.BusinessException;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import com.isl.gbm.transaction.channel.GbmTransactionMasterDto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RbiBondInterestPaymentValidationProcessor")
public class RbiBondInterestPaymentValidationProcessor implements ItemProcessor<RbiBondCentralizedInterestTrailRunDtoRequest, RbiBondCentralizedInterestTrailRunDtoRequest> {

    private static final Logger log = LoggerFactory.getLogger(RbiBondInterestPaymentValidationProcessor.class);

    @Autowired
    private TransactionValidator transactionValidator;

    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest process(RbiBondCentralizedInterestTrailRunDtoRequest input) throws Exception {
        log.info("Validation Processing (Batch Id): " + input.getValidationBatchId());
        List<GbmTransactionMasterDto> accDetailsList = input.getAcctDetailsList();
        if (!CollectionUtils.isEmpty(accDetailsList)) {
            try {
                ValidatorWrapperDTO validatorWrapperDTO = transactionValidator.validateAccountDetailslist(accDetailsList);
                if (validatorWrapperDTO != null) {
                    if (CommonConstants.SUCCESS.equalsIgnoreCase(validatorWrapperDTO.getStatus())) {
                        if (!CollectionUtils.isEmpty(validatorWrapperDTO.getTranDetailsDTOList())) {
                            input.setAccDetailsIn(validatorWrapperDTO.getTranDetailsDTOList());
                            return input;
                        } else {
                            throw new BusinessException(input.getProcessId() + " - " + input.getValidationBatchId() + " : Empty Response From CBS");
                        }
                    } else {
                        throw new BusinessException(input.getProcessId() + " - " + input.getValidationBatchId() + " : CBS Exception : " + validatorWrapperDTO.getReason());
                    }
                } else {
                    throw new BusinessException(input.getProcessId() + " - " + input.getValidationBatchId() + " : Invalid Response From CBS");
                }
            } catch (Exception e) {
                log.error("Exception : ", e);
                throw e;
            }
        } else {
            throw new BusinessException("No Records Found");
        }
    }


}
