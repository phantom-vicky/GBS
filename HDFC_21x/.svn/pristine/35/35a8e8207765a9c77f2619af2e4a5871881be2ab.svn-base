package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "RbiBondInterestPaymentBatchReader")
public class RbiBondInterestPaymentBatchReader implements ItemReader<RbiBondCentralizedInterestTrailRunDtoRequest> {
    @Autowired
    private RbiBondCentralizedInterestTrailRunDao rbiBondCentralizedInterestTrailRunDao;

    private ExecutionContext jobExecutionContext;
    private String processId;

    @BeforeStep
    public void bindParameters(StepExecution stepExecution) throws Exception{
        this.processId = stepExecution.getJobParameters().getString(CommonConstants.PROCESS_ID_STRING);
        this.jobExecutionContext = stepExecution.getJobExecution().getExecutionContext();

//        ScssInterestPaymentBatchDto processDet = (ScssInterestPaymentBatchDto) this.jobExecutionContext.get(this.processId);
//        System.out.println("Tran Batches : "+new ObjectMapper().writeValueAsString(processDet.getTransactionBatchList()));
    }


    @Override
    public RbiBondCentralizedInterestTrailRunDtoRequest read() { //throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        RbiBondInterestPaymentBatchDto processDet = (RbiBondInterestPaymentBatchDto) this.jobExecutionContext.get(this.processId);
        String transactionBatchId = processDet.fetchNextTransactionBatch();

        if (transactionBatchId == null)
            return null;
        else
            return rbiBondCentralizedInterestTrailRunDao.requestBatchForRbiBondIntRun(processId, processDet.getCentralizedFlag(), processDet.getBranchId(), processDet.getBondSubsId(), processDet.getBlaNo(), transactionBatchId, processDet.getModuleId());
    }
}
