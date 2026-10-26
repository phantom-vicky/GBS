package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.rbibond.dto.reports.InetPortfolioRequestDTO;
import com.isl.gbm.rbibond.dto.reports.PANValidationReportDTO;
import com.isl.gbm.rbibond.dto.reports.PortfolioDTO;
import com.isl.gbm.rbibond.dto.reports.TransferReportDTO;
import com.isl.gbm.rbibond.facade.RbiBondReportFacadeLatest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gbmultra/rbibond/latestReports")
public class RbiBondReportControllerLatest {

    @Autowired
    RbiBondReportFacadeLatest rbiBondReportFacadeLatest;

    @PostMapping(path = "/getPortfolio")
    public ResponseEntity<ResponseDataDTO> getPortfolio(@RequestBody final PortfolioDTO portfoliodto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportFacadeLatest.getPortfolio(portfoliodto));
    }

    @PostMapping(path = "/inet/portfolio")
    public ResponseEntity<ResponseDataDTO> getInetPortfolioDetails(@RequestBody final InetPortfolioRequestDTO inetPortfolioDto) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportFacadeLatest.getInetPortfolioDetails(inetPortfolioDto));
    }
    @PostMapping(path = "/getTransferReport")
    public ResponseEntity<ResponseDataDTO> getTransferReport(@Valid @RequestBody final TransferReportDTO transferReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(rbiBondReportFacadeLatest.getTransferReport(transferReportDTO));
    }
}
