package com.isl.gbm.rbibond.bo;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PreMaturityRequestDTO;
public interface PreMaturityClosureMarkingBO{
ResponseDataDTO addDetails(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO addTorch(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO modifyDetails(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO modifyTorch(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO verifyDetails(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO verifyTorch(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO inquiryDetails(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO inquiryTorch(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO add(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO verify(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO modify(PreMaturityRequestDTO preMaturityRequestDTO);
ResponseDataDTO delete(PreMaturityRequestDTO preMaturityRequestDTO);
}
