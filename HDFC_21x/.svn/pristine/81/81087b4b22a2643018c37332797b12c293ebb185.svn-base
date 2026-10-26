package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDtoRequest;

import java.io.IOException;

public interface RbiBondCentralizedInterestTrailRunFacade {
    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO getTrialRunProcess(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO getReportTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO getTrialRunDetailsReport(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws IOException;

    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO intPayUnverifiedRecords(RbiBondCentralizedInterestTrailRunDto centralizedRbiBondInterestTrailRunDto);


    public ResponseDataDTO interestPaymentStatus(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

    public ResponseDataDTO getInterestPayBatchTranDetails(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);


    ResponseDataDTO download(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO verifySelect(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest);

    ResponseDataDTO getInterestTrailRunVerify(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException;
    ResponseDataDTO getInterestTrailRunVerifyDownload(RbiBondCentralizedInterestTrailRunDtoRequest rbiBondCentralizedInterestTrailRunDtoRequest) throws IOException;

    ResponseDataDTO verifySubmit(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);

    ResponseDataDTO delete(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
}
