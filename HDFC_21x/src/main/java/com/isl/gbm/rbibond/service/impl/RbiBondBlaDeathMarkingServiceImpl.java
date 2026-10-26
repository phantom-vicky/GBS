package com.isl.gbm.rbibond.service.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondBlaDeathMarkingBo;
import com.isl.gbm.rbibond.dto.RbiBondBlaDeathMarkingDTO;
import com.isl.gbm.rbibond.service.RbiBondBlaDeathMarkingService;
import com.isl.gbm.rbibond.util.FreezeUnfreezeValidationHelper;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondBlaDeathMarkingServiceImpl implements RbiBondBlaDeathMarkingService {



    @Autowired
    RbiBondBlaDeathMarkingBo rbiBondBlaDeathMarkingBo;
    @Override
    public ResponseDataDTO getAddPanNoDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.getAddPanNoDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getAddDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondBlaDeathMarkingDTO && null != rbiBondBlaDeathMarkingDTO.getPanNo() && !rbiBondBlaDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondBlaDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondBlaDeathMarkingBo.getAddDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO addCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {

            //pattern validation
            if (rbiBondBlaDeathMarkingDTO != null &&  rbiBondBlaDeathMarkingDTO.getRemarks() != null) {
                ResponseDataDTO validationError =
                        FreezeUnfreezeValidationHelper.validateMakerReason(rbiBondBlaDeathMarkingDTO.getRemarks());
                if (validationError != null) {
                    return validationError;
                }
            }

            // Validate all fields
            if (null != rbiBondBlaDeathMarkingDTO && null != rbiBondBlaDeathMarkingDTO.getPanNo() && !rbiBondBlaDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondBlaDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondBlaDeathMarkingBo.addCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getVerDelPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.getVerDelPanDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondBlaDeathMarkingDTO && null != rbiBondBlaDeathMarkingDTO.getPanNo() && !rbiBondBlaDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondBlaDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondBlaDeathMarkingBo.getVerifyDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.verifyCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getDeleteDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.getDeleteDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO deleteCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.deleteCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingBo.getInquiryPanDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondBlaDeathMarkingDTO && null != rbiBondBlaDeathMarkingDTO.getPanNo() && !rbiBondBlaDeathMarkingDTO.getPanNo().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondBlaDeathMarkingDTO.getPanNo());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondBlaDeathMarkingBo.getInquiryDetails(rbiBondBlaDeathMarkingDTO);
    }


}
