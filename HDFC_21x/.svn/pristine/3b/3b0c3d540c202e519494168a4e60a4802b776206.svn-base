package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSharingMaintenanceDTO;
import com.isl.gbm.common.facade.TocSharingMaintenanceFacade;
import com.isl.gbm.common.service.TocSharingMaintenanceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TocSharingMaintenanceFacadeImpl implements TocSharingMaintenanceFacade {

    @Autowired
    private TocSharingMaintenanceService tocSharingMaintenanceService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:ADD"})
    public ResponseDataDTO add(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.add(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:ADD", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:ADD"})
    public ResponseDataDTO addValidation(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.addValidation(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:MODIFY"})
    public ResponseDataDTO modifySelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.modifySelect(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:MODIFY", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:MODIFY"})
    public ResponseDataDTO modify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.modify(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:DELETE"})
    public ResponseDataDTO verifyDeleteSelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.verifyDeleteSelect(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:VERIFY", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:VERIFY"})
    public ResponseDataDTO verify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.verify(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:DELETE", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:DELETE"})
    public ResponseDataDTO delete(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.delete(tocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_DC_CBDT:CBDT_TOC_SHARING_MAINTENANCE:ENQUIRY", "GBM_DC_GST:GST_TOC_SHARING_MAINTENANCE:ENQUIRY", "GBM_DC_KVP:KVP_TOC_SHARING_MAINTENANCE:ENQUIRY", "GBM_DC_SSA:SSA_TOC_SHARING_MAINTENANCE:ENQUIRY"})
    public ResponseDataDTO inquiry(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceService.inquiry(tocSharingMaintenanceDTO);
    }
}
