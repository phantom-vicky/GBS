package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.PreMaturityClosureMarkingBO;
import com.isl.gbm.rbibond.dto.PreMaturityListDTO;
import com.isl.gbm.rbibond.dto.PreMaturityRequestDTO;
import com.isl.gbm.rbibond.service.PreMaturityClosureMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreMaturityClosureMarkingServiceImpl implements PreMaturityClosureMarkingService {

    @Autowired
    PreMaturityClosureMarkingBO preMaturityClosureMarkingBO;

    @Override
    public ResponseDataDTO addDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.addDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO addTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.addTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO modifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.modifyDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO modifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.modifyTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO verifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.verifyDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO verifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.verifyTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO inquiryDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.inquiryDetails(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO inquiryTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingBO.inquiryTorch(preMaturityRequestDTO);
    }
    @Override
    public ResponseDataDTO add(PreMaturityRequestDTO preMaturityRequestDTO) {
        if(preMaturityRequestDTO.getListOfString() != null &&!preMaturityRequestDTO.getListOfString().isEmpty())
            return preMaturityClosureMarkingBO.add(preMaturityRequestDTO);
        else
            return ResponseDataDTO.builder().message("PreMaturity List is empty").statusCode("0").build();
    }
    @Override
    public ResponseDataDTO modify(PreMaturityRequestDTO preMaturityRequestDTO) {
        String errormsg = "";
        if(preMaturityRequestDTO !=null) {
            if (preMaturityRequestDTO.getTotalAmt() != null) {
                try {
                    double amtVal = Double.parseDouble(preMaturityRequestDTO.getTotalAmt());
                    if (amtVal < 0) {
                        errormsg = "Amount must not be negative for holder " + preMaturityRequestDTO.getTotalAmt();
                    }
                } catch (NumberFormatException e) {
                    errormsg = "Amount must be numeric for holder " + preMaturityRequestDTO.getTotalAmt();
                }
            return ResponseDataDTO.builder().message(errormsg).statusCode("0").build();
            }
        }
        if (preMaturityRequestDTO.getPreMaturityList() != null) {
            for (PreMaturityListDTO item : preMaturityRequestDTO.getPreMaturityList()) {
                if (item.getAge() != null) {
                    try {
                        int ageVal = Integer.parseInt(item.getAge());
                        if (ageVal < 0) {
                            return ResponseDataDTO.builder()
                                    .message("Age must not be negative for holder " + item.getHolderName())
                                    .statusCode("0")
                                    .build();
                        }
                    } catch (NumberFormatException e) {
                        return ResponseDataDTO.builder()
                                .message("Age must be numeric for holder " + item.getHolderName())
                                .statusCode("0")
                                .build();
                    }
                }

                if (item.getSubsAmt() != null) {
                    try {
                        double amtVal = Double.parseDouble(item.getSubsAmt());
                        if (amtVal < 0) {
                            return ResponseDataDTO.builder()
                                    .message("Amount must not be negative for holder " + item.getHolderName())
                                    .statusCode("0")
                                    .build();
                        }
                    } catch (NumberFormatException e) {
                        return ResponseDataDTO.builder()
                                .message("Amount must be numeric for holder " + item.getHolderName())
                                .statusCode("0")
                                .build();
                    }
                }
            }
        }

        if(preMaturityRequestDTO.getListOfString() != null &&!preMaturityRequestDTO.getListOfString().isEmpty())
            return preMaturityClosureMarkingBO.modify(preMaturityRequestDTO);
        else
            return ResponseDataDTO.builder().message("PreMaturity List is empty").statusCode("0").build();
    }
    @Override
    public ResponseDataDTO verify(PreMaturityRequestDTO preMaturityRequestDTO) {
        if(preMaturityRequestDTO.getListOfString() != null &&!preMaturityRequestDTO.getListOfString().isEmpty())
            return preMaturityClosureMarkingBO.verify(preMaturityRequestDTO);
        else
            return ResponseDataDTO.builder().message("PreMaturity List is empty").statusCode("0").build();    }
    @Override
    public ResponseDataDTO delete(PreMaturityRequestDTO preMaturityRequestDTO) {
        if(preMaturityRequestDTO.getListOfString() != null &&!preMaturityRequestDTO.getListOfString().isEmpty())
            return preMaturityClosureMarkingBO.delete(preMaturityRequestDTO);
        else
            return ResponseDataDTO.builder().message("PreMaturity List is empty").statusCode("0").build();    }
}
