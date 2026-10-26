package com.isl.gbm.rbibond.dao;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;

public interface TdsParameterForRbiBondDao {

    public ResponseDataDTO add(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO addValidation(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO inquiryDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO verifyDeleteDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO verify(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO delete(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO modifyDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);

    public ResponseDataDTO modifySubmit(RbiBondTdsParameterDTO rbiBondTdsParameterDTO);
}
