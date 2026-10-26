package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BodEodDto;

public interface RbiBondBodEodFacade {
   public ResponseDataDTO startLinkCellBodProcess(BodEodDto bodEodDto);

   public ResponseDataDTO startPayBranchBodProcess(BodEodDto bodEodDto);

   public ResponseDataDTO startLinkCellEodProcess(BodEodDto bodEodDto);

   public ResponseDataDTO startPayBranchEodProcess(BodEodDto bodEodDto);

   public ResponseDataDTO getTimeValidation();

}
