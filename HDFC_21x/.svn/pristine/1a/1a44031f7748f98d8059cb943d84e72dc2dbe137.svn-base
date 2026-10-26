package com.isl.gbm.common.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSlabDetailsDto;
import com.isl.gbm.common.facade.TocSlabDetailsFacade;
import com.isl.gbm.common.service.TocSlabDetailsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TocSlabDetailsFacadeImpl implements TocSlabDetailsFacade {

    @Autowired
    private TocSlabDetailsService tocSlabDetailsService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:MODIFY", "DC:SCSS_TOC_SHARING:MODIFY", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:MODIFY", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:MODIFY","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:MODIFY"})
    public ResponseDataDTO selectModifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.selectModifySlab(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:VERIFY", "DC:SCSS_TOC_SHARING:VERIFY", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:VERIFY", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:VERIFY","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:VERIFY"})
    public ResponseDataDTO selectVerifySlab(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.selectVerifySlab(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:ADD", "DC:SCSS_TOC_SHARING:ADD", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:ADD", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:ADD","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:ADD"})
    public ResponseDataDTO getAddDetails(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.getAddDetails(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:ADD", "DC:SCSS_TOC_SHARING:ADD", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:ADD", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:ADD","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:ADD"})
    public ResponseDataDTO save(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.save(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:MODIFY", "DC:SCSS_TOC_SHARING:MODIFY", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:MODIFY", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:MODIFY","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:MODIFY"})
    public ResponseDataDTO modify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.modify(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:VERIFY", "DC:SCSS_TOC_SHARING:VERIFY", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:VERIFY", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:VERIFY","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:VERIFY"})
    public ResponseDataDTO verify(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.verify(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:DELETE", "DC:SCSS_TOC_SHARING:DELETE", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:DELETE", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:DELETE","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:DELETE"})
    public ResponseDataDTO delete(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.delete(tocSlabDetailsDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_PPF_DC:PPF_TOC_SLAB_MAINTENANCE:ENQUIRY", "DC:SCSS_TOC_SHARING:ENQUIRY", "GBM_DC_GST:GST_TOC_SLAB_MAINTENANCE:ENQUIRY", "GBM_DC_CBDT:CBDT_TOC_SLAB_MAINTENANCE:ENQUIRY","GBM_DC_ICEGATE:ICEGATE_TOC_SLAB_MAINTENANCE:ENQUIRY"})
    public ResponseDataDTO inquiry(TocSlabDetailsDto tocSlabDetailsDto) {
        return tocSlabDetailsService.inquiry(tocSlabDetailsDto);
    }

}
