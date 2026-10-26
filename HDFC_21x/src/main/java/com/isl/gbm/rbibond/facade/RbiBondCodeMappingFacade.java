package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;

public interface RbiBondCodeMappingFacade {


    public ResponseDataDTO add(RbiBondCodeMappingDTO tdsParameterDTO);

    public ResponseDataDTO addValidation();

    public ResponseDataDTO inquiryDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO verifyDeleteDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO verify(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO delete(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO modifyDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO modifySubmit(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);


    ResponseDataDTO getBranchCode(RbiBondCodeMappingDTO rbiBondTdsParameterDTO);
}
