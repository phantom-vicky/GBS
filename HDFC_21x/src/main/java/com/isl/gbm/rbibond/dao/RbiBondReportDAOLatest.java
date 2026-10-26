package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.reports.InetPortfolioRequestDTO;
import com.isl.gbm.rbibond.dto.reports.PortfolioDTO;
import com.isl.gbm.rbibond.dto.reports.TransferReportDTO;

public interface RbiBondReportDAOLatest {
    ResponseDataDTO getPortfolio(PortfolioDTO portfoliodto);

    public ResponseDataDTO getInetPortfolioDetails(InetPortfolioRequestDTO inetPortfolioDto);

    public ResponseDataDTO getTransferReport(TransferReportDTO transferReportDTO);
}
