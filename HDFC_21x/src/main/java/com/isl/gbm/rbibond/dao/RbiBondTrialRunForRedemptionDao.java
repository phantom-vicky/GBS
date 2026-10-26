package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunForRedemptionDto;

import java.io.IOException;
import java.util.List;

public interface RbiBondTrialRunForRedemptionDao {
    ResponseDataDTO add(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto);

    ResponseDataDTO addTorch(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto);

    ResponseDataDTO getTrialRunProcess(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO getBlaNo(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO getTrialRunDetailsReport(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto) throws IOException;

    ResponseDataDTO interestPaymentAddTorch(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto);

//    ResponseDataDTO validateOfficeAccountDetails(RbiBondTrailRunForRedemptionDto centralizedScssInterestTrailRunDto) throws Exception;

    public ResponseDataDTO intPayUnverifiedRecords(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);


//    PKG_GBM_SCSS_PAYMENT_PROCESS_SP_GBM_SCSS_PAYMENT_TRAN_REQ("{ call PKG_GBM_SCSS_PAYMENT_PROCESS.SP_GBM_SCSS_PAYMENT_TRAN_REQ(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"),




    RbiBondTrailRunForRedemptionDto interestPaymentStatus(RbiBondTrailRunForRedemptionDto input) throws Exception;


    public ResponseDataDTO getPanTorchDetails(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);


    public ResponseDataDTO trailrun(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest);

    public ResponseDataDTO bulkPaymentGetDet(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest);

    public ResponseDataDTO cbsAccValidation(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest);

    List<String> getValidationBatchList(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest) throws Exception;

//tran validation req
   RbiBondTrailRunForRedemptionDto requestBatchForRbiBondIntValidation(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String validationBatchId) throws Exception;
//tran validation res update
  void responseBatchForRbiBondIntValidation(RbiBondTrailRunForRedemptionDto responseDto) throws Exception;
    // transaction batch list
   List<String> getTransactionBatchList(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String interestForHalfYear, String year, String moduleId) throws Exception;
//   tran batch req
    RbiBondTrailRunForRedemptionDto requestBatchForRbiBondIntRun(String processId, String centralizedFlag, String branchId, String bondSubsId, String blaNo, String tranBatchId, String processDet);
    //   tran batch upd
    void responseBatchForRbiBondIntRun(RbiBondTrailRunForRedemptionDto responseDto);


    void updateCompletionOfBatchExecutionRbiBulkInterestPayment(String processId, String status, String statusRemarks);


    ResponseDataDTO download(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);
    ResponseDataDTO downloadMerged(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);

//    List<RbiBondCentralizedInterestTrailRunDownloadDto> fetchData(RbiBondTrailRunForRedemptionDto downloadDto, int offset, int limit);

    ResponseDataDTO verifySelect(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO getInterestTrailRunVerify(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO verifySubmit(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO delete(RbiBondTrailRunForRedemptionDto rbiBondCentralizedInterestTrailRunDto);
}
