package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
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
public class RbiBondInterestPaymentBatchJobManager {
    @Qualifier("applicationTaskExecutor")
    @Autowired
    private SimpleAsyncTaskExecutor customTaskExecutor;


    @Bean(name = "RbiBondIntPayJob")
    public Job rbiBondIntPayJob(JobRepository jobRepository,
                                @Qualifier("rbiBondInterestPaymentValidationStep") Step validationStep,
                                @Qualifier("rbiBondInterestPaymentTransactionBatchFetchStep") Step tranBatchFetchStep,
                                @Qualifier("rbiBondInterestPaymentTransactionStep") Step tranStep,
                                RbiBondInterestPaymentBatchJobListener rbiBondInterestPaymentBatchJobListener) {
        return new org.springframework.batch.core.job.builder.JobBuilder("RbiBond Interest Payment Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(rbiBondInterestPaymentBatchJobListener)
                .validator(rbiBondIntPaymentJobParametersValidator())
                .start(validationStep)
                .next(tranBatchFetchStep)
                .next(tranStep)
                .build();
    }


    @Bean(name = "rbiBondInterestPaymentValidationStep")
    public Step rbiBondInterestPaymentValidationStep(JobRepository jobRepository,
                                                     PlatformTransactionManager transactionManager,
                                                     @Qualifier("RbiBondInterestPaymentValidationReader") ItemReader rbiBondInterestPaymentBatchReader,
                                                     @Qualifier("RbiBondInterestPaymentValidationProcessor") ItemProcessor rbiBondInterestPaymentBatchProcessor,
                                                     @Qualifier("RbiBondInterestPaymentValidationWriter") ItemWriter rbiBondInterestPaymentBatchWriter) {
        return new org.springframework.batch.core.step.builder.StepBuilder("RbiBond Interest Payment Job: Validation Step", jobRepository)
                .<RbiBondCentralizedInterestTrailRunDtoRequest, RbiBondCentralizedInterestTrailRunDtoRequest>chunk(customTaskExecutor.getConcurrencyLimit(), transactionManager)
                .reader(rbiBondInterestPaymentBatchReader)
                .processor(rbiBondInterestPaymentBatchProcessor)
                .writer(rbiBondInterestPaymentBatchWriter)
//                .faultTolerant().skip(Throwable.class)  //.exceptionHandler()
                .taskExecutor(customTaskExecutor)
                .throttleLimit(customTaskExecutor.getConcurrencyLimit())
                .build();
    }

    @Bean(name = "rbiBondInterestPaymentTransactionBatchFetchStep")
    public Step tranBatchListFetchStep(JobRepository jobRepository,
                                       PlatformTransactionManager transactionManager,
                                       @Qualifier("RbiBondInterestPaymentTransactionBatchListReader") Tasklet tranBatchListReaderTasklet) {
        return new org.springframework.batch.core.step.builder.StepBuilder("RbiBond Interest Payment Job : Transaction Batch List Fetch Step", jobRepository)
                .tasklet(tranBatchListReaderTasklet, transactionManager)
                .build();
    }

    @Bean(name = "rbiBondInterestPaymentTransactionStep")
    public Step rbiBondInterestPaymentTransactionStep(JobRepository jobRepository,
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
//         CommonConstants.BRANCH_ID_STRING,"bondSubsId", "blaNo",
        String[] requiredKeys = {CommonConstants.PROCESS_ID_STRING, CommonConstants.CENTRALIZED_FLAG_STRING,CommonConstants.AVAILABLE_VAL_BATCH_STRING};
        jobParametersValidator.setRequiredKeys(requiredKeys);
        return jobParametersValidator;
    }

}
