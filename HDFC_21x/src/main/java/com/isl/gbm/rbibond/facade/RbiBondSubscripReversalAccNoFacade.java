package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;

public interface RbiBondSubscripReversalAccNoFacade {

  public  ResponseDataDTO add(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);

   public ResponseDataDTO addValidation(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);

   public ResponseDataDTO verify(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);

   public ResponseDataDTO delete(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);

   public ResponseDataDTO inquiry(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);

   public ResponseDataDTO verifySelect(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO);
}
