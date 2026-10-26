package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;

import java.io.IOException;

public interface RbiBondCentralizedInterestTrailRunService {
    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException;

    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto centralizedScssInterestTrailRunDto);

    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);


    public ResponseDataDTO interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest input);

    public ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException;
    ResponseDataDTO getInterestTrailRunVerifyDownload(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException;

    ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
}
