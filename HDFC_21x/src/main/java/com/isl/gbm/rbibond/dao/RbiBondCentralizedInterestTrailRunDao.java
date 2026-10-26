package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;

import java.io.IOException;
import java.util.List;

public interface RbiBondCentralizedInterestTrailRunDao {
    ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto) throws IOException;

    ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

//    ResponseDataDTO validateOfficeAccountDetails(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto) throws Exception;

    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);


//    PKG_GBM_SCSS_PAYMENT_PROCESS_SP_GBM_SCSS_PAYMENT_TRAN_REQ("{ call PKG_GBM_SCSS_PAYMENT_PROCESS.SP_GBM_SCSS_PAYMENT_TRAN_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),




    RbiBondCentralizedInterestTrailRunDtoRequest interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest input) throws Exception;


    public ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);


    public ResponseDataDTO trailrun(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    public ResponseDataDTO bulkPaymentGetDet(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    public ResponseDataDTO cbsAccValidation(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    List<String> getValidationBatchList(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws Exception;

//tran validation req
   RbiBondCentralizedInterestTrailRunDtoRequest requestBatchForRbiBondIntValidation(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String validationBatchId) throws Exception;
//tran validation res update
  void responseBatchForRbiBondIntValidation(RbiBondCentralizedInterestTrailRunDtoRequest responseDto) throws Exception;
    // transaction batch list
   List<String> getTransactionBatchList(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String interestForHalfYear, String year, String moduleId) throws Exception;
//   tran batch req
    RbiBondCentralizedInterestTrailRunDtoRequest requestBatchForRbiBondIntRun(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String tranBatchId, String processDet);
    //   tran batch upd
    void responseBatchForRbiBondIntRun(RbiBondCentralizedInterestTrailRunDtoRequest responseDto);


    void updateCompletionOfBatchExecutionRbiBulkInterestPayment(String processId, String status, String statusRemarks);


    ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO downloadMerged(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

//    List<RbiBondCentralizedInterestTrailRunDownloadDto> fetchData(RbiBondCentralizedInterestTrailRunDtoRequest downloadDto, int offset, int limit);

    ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
}
