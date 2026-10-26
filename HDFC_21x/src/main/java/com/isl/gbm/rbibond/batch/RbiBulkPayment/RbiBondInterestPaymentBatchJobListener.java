package com.isl.gbm.rbibond.batch.RbiBulkPayment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.RbiBondCentralizedInterestTrailRunDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class RbiBondInterestPaymentBatchJobListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(RbiBondInterestPaymentBatchJobListener.class);

    @Autowired
    private RbiBondCentralizedInterestTrailRunDao rbiBondCentralizedInterestTrailRunDao;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(String.format("Batch Job : 'RBIBOND - Interest Payment'\tProcessId : %s\tStarted At : %s", jobExecution.getJobParameters().getString(CommonConstants.PROCESS_ID_STRING), new Date().toString()));

        try {
            RbiBondInterestPaymentBatchDto paymentBatchDto = null;
            JobParameters jobParameters = jobExecution.getJobParameters();
            Map<String, JobParameter<?>> jobparameterKeyValue = jobParameters.getParameters();
            if(jobparameterKeyValue.containsKey(CommonConstants.AVAILABLE_VAL_BATCH_STRING)){
                 paymentBatchDto = new RbiBondInterestPaymentBatchDto(jobParameters.getString(CommonConstants.PROCESS_ID_STRING),jobParameters.getString("interestForHalfYear") ,jobParameters.getString("year"),jobParameters.getString(CommonConstants.CENTRALIZED_FLAG_STRING), jobParameters.getString(CommonConstants.BRANCH_ID_STRING), jobParameters.getString("bondSubsId"), jobParameters.getString("blaNo"),jobParameters.getString("moduleId"), new ObjectMapper().readValue(jobParameters.getString(CommonConstants.AVAILABLE_VAL_BATCH_STRING),  new TypeReference<List<String>>() {
                }));
            }else {
                 paymentBatchDto = new RbiBondInterestPaymentBatchDto(jobParameters.getString(CommonConstants.PROCESS_ID_STRING),jobParameters.getString("interestForHalfYear") ,jobParameters.getString("year"), jobParameters.getString(CommonConstants.CENTRALIZED_FLAG_STRING), jobParameters.getString(CommonConstants.BRANCH_ID_STRING), jobParameters.getString("bondSubsId"), new ObjectMapper().readValue(jobParameters.getString(CommonConstants.AVAILABLE_TRAN_BATCH_STRING), new TypeReference<List<String>>() {
                 }), jobParameters.getString("blaNo"), jobParameters.getString("moduleId"));
            }

            jobExecution.getExecutionContext().put(paymentBatchDto.getProcessId(), paymentBatchDto);
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            jobExecution.setExitStatus(new ExitStatus(ExitStatus.FAILED.getExitCode(),e.getMessage()));
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
//        log.info("JOB Status and Desc : " + jobExecution.getExitStatus().getExitCode() + " " + jobExecution.getExitStatus().getExitDescription().substring(0, jobExecution.getExitStatus().getExitDescription().indexOf("at")));
//        ExitStatus.COMPLETED
        String jobStatus = ExitStatus.COMPLETED.getExitCode().equals(jobExecution.getExitStatus().getExitCode()) ? CommonConstants.SUCCESS : CommonConstants.FAILURE;
        String jobStatusRemarks = null;
        if (CommonConstants.SUCCESS.equals(jobStatus))
            jobStatusRemarks = CommonConstants.SUCCESS_CONST;
        else {
            int pos = jobExecution.getExitStatus().getExitDescription().indexOf("at");
            if (pos == -1)
                jobStatusRemarks = "FAILED";
            else
                jobStatusRemarks = jobExecution.getExitStatus().getExitDescription().substring(0, pos);
        }

        JobParameters jobParameters = jobExecution.getJobParameters();
        log.info(String.format("Batch Job : 'RBIBOND - Interest Payment'\tProcessId : %s\tCompleted At : %s", jobParameters.getString(CommonConstants.PROCESS_ID_STRING), new Date().toString()));


        rbiBondCentralizedInterestTrailRunDao.updateCompletionOfBatchExecutionRbiBulkInterestPayment(jobParameters.getString(CommonConstants.PROCESS_ID_STRING), jobStatus, jobStatusRemarks);

        jobExecution.getExecutionContext().remove(jobParameters.getString(CommonConstants.PROCESS_ID_STRING));
    }
}
