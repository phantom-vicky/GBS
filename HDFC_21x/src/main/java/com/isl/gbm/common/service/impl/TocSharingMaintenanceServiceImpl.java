package com.isl.gbm.common.service.impl;

import com.isl.gbm.common.bo.TocSharingMaintenanceBO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSharingMaintenanceDTO;
import com.isl.gbm.common.service.TocSharingMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TocSharingMaintenanceServiceImpl implements TocSharingMaintenanceService {

    @Autowired
    private TocSharingMaintenanceBO tocSharingMaintenanceBO;

    @Override
    public ResponseDataDTO add(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.add(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addValidation(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.addValidation(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.modifySelect(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.modify(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.verifyDeleteSelect(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.verify(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.delete(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceBO.inquiry(tocSharingMaintenanceDTO);
    }
}
