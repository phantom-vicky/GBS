package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondReportBOLatest;
import com.isl.gbm.rbibond.dto.reports.InetPortfolioRequestDTO;
import com.isl.gbm.rbibond.dto.reports.PortfolioDTO;
import com.isl.gbm.rbibond.dto.reports.TransferReportDTO;
import com.isl.gbm.rbibond.service.RbiBondReportServiceLatest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class RbiBondReportServiceImplLatest implements RbiBondReportServiceLatest {
    @Autowired
    public RbiBondReportBOLatest rbiBondReportBOLatest;

    @Override
    public ResponseDataDTO getPortfolio(PortfolioDTO portfoliodto) {
        return rbiBondReportBOLatest.getPortfolio(portfoliodto);
    }

    public ResponseDataDTO getInetPortfolioDetails(InetPortfolioRequestDTO inetPortfolioDto) {
        return rbiBondReportBOLatest.getInetPortfolioDetails(inetPortfolioDto);
    }

    @Override
    public ResponseDataDTO getTransferReport(TransferReportDTO transferReportDTO) {
        return rbiBondReportBOLatest.getTransferReport(transferReportDTO);

    }
}
