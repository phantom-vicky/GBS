package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.rbibond.facade.RbiBondReportFacadeLatest;
import com.isl.gbm.rbibond.service.RbiBondReportServiceLatest;
import com.isl.gbm.rbibond.service.RbiBondReportsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondReportFacadeImplLatest implements RbiBondReportFacadeLatest {
    @Autowired
    private RbiBondReportServiceLatest rbiBondReportServiceLatest;


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:PORTFOLIO_REPORT:REPORT")
    public ResponseDataDTO getPortfolio(PortfolioDTO portfoliodto) {
        return rbiBondReportServiceLatest.getPortfolio(portfoliodto);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:INET_PORTFOLIO_REPORT:REPORT")
    public ResponseDataDTO  getInetPortfolioDetails(InetPortfolioRequestDTO inetPortfolioDto){
        return rbiBondReportServiceLatest.getInetPortfolioDetails(inetPortfolioDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:TRANSFER_REPORT:REPORT")
    public ResponseDataDTO getTransferReport(TransferReportDTO transferReportDTO) {
        return rbiBondReportServiceLatest.getTransferReport(transferReportDTO);
    }
}
