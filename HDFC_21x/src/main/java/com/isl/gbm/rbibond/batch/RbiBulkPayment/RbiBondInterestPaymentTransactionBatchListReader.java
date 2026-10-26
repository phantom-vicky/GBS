package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RbiBondInterestPaymentTransactionBatchListReader")
public class RbiBondInterestPaymentTransactionBatchListReader implements Tasklet {
    @Autowired
    private RbiBondCentralizedInterestTrailRunDao rbiBondCentralizedInterestTrailRunDao;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String processId = (String) chunkContext.getStepContext().getJobParameters().get(CommonConstants.PROCESS_ID_STRING);
        RbiBondInterestPaymentBatchDto processDet = (RbiBondInterestPaymentBatchDto) chunkContext.getStepContext().getJobExecutionContext().get(processId);
//        RbiBondCentralizedInterestTrailRunDtoRequest processDet = (RbiBondCentralizedInterestTrailRunDtoRequest) chunkContext.getStepContext().getJobExecutionContext().get(processId);

        List<String> availableTranBatchList = this.rbiBondCentralizedInterestTrailRunDao.getTransactionBatchList(processId, processDet.getCentralizedFlag(), processDet.getBranchId(), processDet.getBondSubsId(), processDet.getBlaNo(),processDet.getInterestForHalfYear(),processDet.getYear(), processDet.getModuleId());
        processDet.setTransactionBatchList(availableTranBatchList);

        return RepeatStatus.FINISHED;
    }
}
