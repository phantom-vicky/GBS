package com.isl.gbm.rbibond.bo;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunReportDto;
import java.io.IOException;
import java.util.List;

public interface RbiBondCentralizedInterestTrailRunBo {
//    public ResponseDataDTO add(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
//
//    public ResponseDataDTO addTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
//
//    public ResponseDataDTO interestPaymentAddTorch(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);
//
////    public ResponseDataDTO validateOperativeAccountNumbers(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto) throws Exception;
//
//    public ResponseDataDTO interestPaymentStatus(RbiBondCentralizedInterestTrailRunDto rbiBondCentralizedInterestTrailRunDto);


    byte[] excelFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> rbiBondCentralizedInterestTrailRunDtoList, RbiBondCentralizedInterestTrailRunDto bondCentralizedInterestTrailRunDto) throws IOException;

    byte[] pdfFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> rbiBondCentralizedInterestTrailRunDtoList, RbiBondCentralizedInterestTrailRunDto bondCentralizedInterestTrailRunDto) throws IOException;
}
