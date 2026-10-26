package com.isl.gbm.rbibond.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;

public interface RbiBondSchemeParameterService {

    ResponseDataDTO isRecordExist(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO add(RbiBondSchemeParameterDTO efreightSchemeParameterDTO);

    ResponseDataDTO modifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO modify(RbiBondSchemeParameterDTO efreightSchemeParameterDTO);

    ResponseDataDTO verify(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO unVerifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO delete(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO inquiry(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

    ResponseDataDTO interestRtCdTorch(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO);

}
