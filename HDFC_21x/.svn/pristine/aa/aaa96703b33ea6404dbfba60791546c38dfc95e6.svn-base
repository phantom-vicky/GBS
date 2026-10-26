package com.isl.gbm.rbibond.batch.RbiBulkpaymentDirectTransaction;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.batch.RbiBulkPayment.RbiBondInterestPaymentBatchJobListener;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RbiBondInterestPaymentBatchJobDirectTransactionManager {

    @Qualifier("applicationTaskExecutor")
    @Autowired
    private SimpleAsyncTaskExecutor customTaskExecutor;


    @Bean(name = "RbibondIntPayJobForDirectTransaction")
    public Job rbiBondIntPayJob(JobRepository jobRepository,
                                @Qualifier("rbiBondInterestPaymentTransactionStepForDirectTran") Step tranStep,
                                RbiBondInterestPaymentBatchJobListener rbiBondInterestPaymentBatchJobListener) {
        return new org.springframework.batch.core.job.builder.JobBuilder("Rbi Bond Interest Payment Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(rbiBondInterestPaymentBatchJobListener)
                .validator(rbiBondIntPaymentJobParametersValidator())
                .start(tranStep)
                .build();
    }

    @Bean(name = "rbiBondInterestPaymentTransactionStepForDirectTran")
    public Step rbiBondInterestPaymentTransactionStepForDirectTran(JobRepository jobRepository,
                                                                   PlatformTransactionManager transactionManager,
                                                                   @Qualifier("RbiBondInterestPaymentBatchReader") ItemReader rbiBondInterestPaymentBatchReader,
                                                                   @Qualifier("RbiBondInterestPaymentBatchProcessor") ItemProcessor rbiBondInterestPaymentBatchProcessor,
                                                                   @Qualifier("RbiBondInterestPaymentBatchWriter") ItemWriter rbiBondInterestPaymentBatchWriter) {
        return new org.springframework.batch.core.step.builder.StepBuilder("Rbi Bond Interest Payment Job : Transaction Step", jobRepository)
                .<RbiBondCentralizedInterestTrailRunDtoRequest, RbiBondCentralizedInterestTrailRunDtoRequest>chunk(1, transactionManager)
                .reader(rbiBondInterestPaymentBatchReader)
                .processor(rbiBondInterestPaymentBatchProcessor)
                .writer(rbiBondInterestPaymentBatchWriter)
//                .taskExecutor(customTaskExecutor)
//                .throttleLimit(customTaskExecutor.getConcurrencyLimit())
                .build();
    }


    private JobParametersValidator rbiBondIntPaymentJobParametersValidator() {
        DefaultJobParametersValidator jobParametersValidator = new DefaultJobParametersValidator();
        String[] requiredKeys = {CommonConstants.PROCESS_ID_STRING, CommonConstants.CENTRALIZED_FLAG_STRING, CommonConstants.BRANCH_ID_STRING,"bondSubsId", "blaNo",CommonConstants.AVAILABLE_VAL_BATCH_STRING};
        jobParametersValidator.setRequiredKeys(requiredKeys);
        return jobParametersValidator;
    }
}
