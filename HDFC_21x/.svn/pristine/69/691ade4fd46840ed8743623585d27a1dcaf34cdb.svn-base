package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondHUFDeathMarkingBo;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;
import com.isl.gbm.rbibond.service.RbiBondHUFDeathMarkingService;
import com.isl.gbm.rbibond.util.FreezeUnfreezeValidationHelper;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondHUFDeathMarkingServiceImpl implements RbiBondHUFDeathMarkingService {
    @Autowired
    RbiBondHUFDeathMarkingBo rbiBondHUFDeathMarkingBo;

    @Override
    public ResponseDataDTO getAddPanNoDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.getAddPanNoDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getCoParcenerDtls(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.getCoParcenerDtls(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO addCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {

            //pattern validation
            if (rbiBondHUFDeathMarkingDTO != null &&  rbiBondHUFDeathMarkingDTO.getRemarks() != null) {
                ResponseDataDTO validationError =
                        FreezeUnfreezeValidationHelper.validateMakerReason(rbiBondHUFDeathMarkingDTO.getRemarks());
                if (validationError != null) {
                    return validationError;
                }
            }

            // Validate all fields
            if (null != rbiBondHUFDeathMarkingDTO && null != rbiBondHUFDeathMarkingDTO.getPanNo() && !rbiBondHUFDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondHUFDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondHUFDeathMarkingBo.addCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO getVerDelPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.getVerDelPanDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            if (null != rbiBondHUFDeathMarkingDTO && null != rbiBondHUFDeathMarkingDTO.getPanNo() && !rbiBondHUFDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondHUFDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondHUFDeathMarkingBo.getVerifyDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.verifyCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }
    @Override
    public ResponseDataDTO deleteCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.deleteCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingBo.getInquiryPanDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO getInquiryDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondHUFDeathMarkingDTO && null != rbiBondHUFDeathMarkingDTO.getPanNo() && !rbiBondHUFDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondHUFDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondHUFDeathMarkingBo.getInquiryDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondHUFDeathMarkingDTO && null != rbiBondHUFDeathMarkingDTO.getPanNo() && !rbiBondHUFDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondHUFDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondHUFDeathMarkingBo.getAddDetails(rbiBondHUFDeathMarkingDTO);
    }
}
