package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;

public interface RbiBondCentalizedBodEodBo {
   public ResponseDataDTO add(RbiBondEodBodDTO centalizedEodBodDTO);

   public ResponseDataDTO getDate(RbiBondEodBodDTO centalizedEodBodDTO);

   public ResponseDataDTO getLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO);

    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO);

    public  ResponseDataDTO getEodDate(RbiBondEodBodDTO centalizedEodBodDTO);

    public ResponseDataDTO getEodLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO);

   public ResponseDataDTO getTimeValidation();
}
