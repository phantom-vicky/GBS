package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TransactionReponseDto;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;

public interface RbiBondClosureBeforeMaturDeathDao {



    ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);

    ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO);


    ResponseDataDTO verifyTranResponse(TransactionReponseDto transactionReponseDto);
}
