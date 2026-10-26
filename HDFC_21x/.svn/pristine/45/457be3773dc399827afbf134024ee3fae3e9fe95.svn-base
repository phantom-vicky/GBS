package com.isl.gbm.common.bo.impl;

import com.isl.gbm.common.bo.TocSharingMaintenanceBO;
import com.isl.gbm.common.dao.TocSharingMaintenanceDAO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSharingMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TocSharingMaintenanceBOImpl implements TocSharingMaintenanceBO {

    @Autowired
    private TocSharingMaintenanceDAO tocSharingMaintenanceDAO;

    @Override
    public ResponseDataDTO add(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.add(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addValidation(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.addValidation(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.modifySelect(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.modify(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.verifyDeleteSelect(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.verify(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.delete(tocSharingMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(TocSharingMaintenanceDTO tocSharingMaintenanceDTO) {
        return tocSharingMaintenanceDAO.inquiry(tocSharingMaintenanceDTO);
    }
}
