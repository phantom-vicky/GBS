package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;

public interface CacAckNumMaintenanceService {
 public  ResponseDataDTO add(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO verifyDeleteSelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO inquiry(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO verify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO delete(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO modifySelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

   public ResponseDataDTO modify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);

  public  ResponseDataDTO addDet(CacAckNumMaintenanceDto cacAckNumMaintenanceDto);
}
