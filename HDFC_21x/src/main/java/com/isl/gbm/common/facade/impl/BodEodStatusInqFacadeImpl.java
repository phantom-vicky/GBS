package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.facade.BodEodStatusInqFacade;
import com.isl.gbm.common.service.BodEodStatusInqService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BodEodStatusInqFacadeImpl implements BodEodStatusInqFacade {

    @Autowired
    private BodEodStatusInqService bodEodStatusInqService;

    @Override
    public ResponseDataDTO getStatusInquiry() {
        return bodEodStatusInqService.getStatusInquiry();
    }

    @Override
    public ResponseDataDTO getModuleName() {
        return bodEodStatusInqService.getModuleName();
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_GU_FUNCTIONALITY:PPF_EOD_BOD_LC:ENQUIRY","GBM_PPF_DC:BOD_EOD_STATUS_INQUIRY_DC:ENQUIRY", "GBM_KVP_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LC:ENQUIRY", "GBM_KVP_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_CB:ENQUIRY", "GBM_DC_KVP:BOD_EOD_STATUS_INQUIRY:ENQUIRY", "GBM_DC_APY:BOD_EOD_STATUS_INQUIRY_DC:ENQUIRY", "GBM_APY_POP_OPERATION:BOD_EOD_STATUS_INQUIRY_POP:ENQUIRY", "GBM_APY_POP_SP_OPERATION:BOD_EOD_STATUS_INQUIRY_POP_SP:ENQUIRY", "GBM_SSA_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LINK_CELL:ENQUIRY", "GBM_SSA_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_COLLECTING_BRANCH:ENQUIRY", "GBM_DC_SSA:BOD_EOD_STATUS_INQUIRY:REPORT", "GBM_DC_SCSS:BOD_EOD_STATUS_INQUIRY:ENQUIRY", "GBM_SCSS_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_LINK_CELL:ENQUIRY", "GBM_SCSS_GU_FUNCTIONALITY:BOD_EOD_STATUS_INQUIRY_COLLECTING_BRANCH:ENQUIRY"})
    public ResponseDataDTO getInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqService.getInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_GU_FUNCTIONALITY:PPF_EOD_BOD_LC:ENQUIRY","GBM_DC_ST:ST_BOD_EOD_STATUS_INQUIRY_DC:ENQUIRY", "GBM_ST_GU_FUNCTIONALITY:ST_BOD_EOD_STATUS_INQUIRY_LC:ENQUIRY", "GBM_ST_GU_FUNCTIONALITY:ST_BOD_EOD_STATUS_INQUIRY_CB:ENQUIRY"})
    public ResponseDataDTO getStateTaxInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return bodEodStatusInqService.getStateTaxInquiryDetails(bodEodStatusInqDTO);
    }
}
