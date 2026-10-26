package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.AgeBseLkIPdDetlPmteEncasmetDTO;

public interface AgeBseLkIPdDetlPmteEncasmetService {



    public ResponseDataDTO addValidation(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    public ResponseDataDTO add(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO verifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO verify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO modifySelect(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO modify(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO inquiry(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);

    ResponseDataDTO delete(AgeBseLkIPdDetlPmteEncasmetDTO ageBseLkIPdDetlPmteEncasmetDTO);



}
