package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BodEodDto;

public interface RbiBondBodEodDao {
  public   ResponseDataDTO startBodProcess(BodEodDto requestDto);

  public ResponseDataDTO startEodProcess(BodEodDto bodEodDto);


}
