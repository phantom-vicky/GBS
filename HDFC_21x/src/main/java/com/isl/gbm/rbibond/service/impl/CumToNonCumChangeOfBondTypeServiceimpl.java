package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CumToNonCumChangeOfBondTypeBo;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.CumToNonCumChangeOfBondTypeDTO;
import com.isl.gbm.rbibond.service.CumToNonCumChangeOfBondTypeService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CumToNonCumChangeOfBondTypeServiceimpl implements CumToNonCumChangeOfBondTypeService {

    @Autowired
    private CumToNonCumChangeOfBondTypeBo cumToNonCumChangeOfBondTypeBo;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    ResponseDataDTO responseDataDTO=new ResponseDataDTO();

    @Override
    public ResponseDataDTO getsubscriptionIdInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getsubscriptionIdInq(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getPantorchInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getPantorchInq(cumToNonCumChangeOfBondTypeDTO);
    }


    @Override
    public ResponseDataDTO getAddDashBoard(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getAddDashBoard(cumToNonCumChangeOfBondTypeDTO);
    }


    @Override
    public ResponseDataDTO addSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != cumToNonCumChangeOfBondTypeDTO && null != cumToNonCumChangeOfBondTypeDTO.getPanNum() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(cumToNonCumChangeOfBondTypeDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cumToNonCumChangeOfBondTypeBo.addSubmit(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO blaNo(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.blaNo(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getblaNoInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getblaNoInq(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.blaTorch(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getPantorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getPantorch(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO modifyPanTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.modifyPanTorch(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getRbiBondModuleNameDetails(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetailsInq(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getRbiBondModuleNameDetailsInq(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getBrokerTorch(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getBrokerTorch(cumToNonCumChangeOfBondTypeDTO);
    }



    @Override
    public ResponseDataDTO add(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {



        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getBondHolderName() != null && !cumToNonCumChangeOfBondTypeDTO.getBondHolderName().trim().isEmpty() &&!cumToNonCumChangeOfBondTypeDTO.getBondHolderName().matches("[A-Za-z ]+")) {

            responseDataDTO.setMessage("Name must contain only characters");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getPanNum() != null && !cumToNonCumChangeOfBondTypeDTO.getPanNum().trim().isEmpty() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().replaceAll("\\s+", "").toUpperCase().matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
            responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }



        try {
            // Validate all fields
            if (null != cumToNonCumChangeOfBondTypeDTO && null != cumToNonCumChangeOfBondTypeDTO.getPanNum() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(cumToNonCumChangeOfBondTypeDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cumToNonCumChangeOfBondTypeBo.add(cumToNonCumChangeOfBondTypeDTO);
    }


    @Override
    public ResponseDataDTO getGuardianDetails(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getGuardianDetails(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO inquiry(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {



        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getBondHolderName() != null && !cumToNonCumChangeOfBondTypeDTO.getBondHolderName().trim().isEmpty() &&!cumToNonCumChangeOfBondTypeDTO.getBondHolderName().matches("[A-Za-z ]+")) {

            responseDataDTO.setMessage("Name must contain only characters");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getPanNum() != null && !cumToNonCumChangeOfBondTypeDTO.getPanNum().trim().isEmpty() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().replaceAll("\\s+", "").toUpperCase().matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
            responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != cumToNonCumChangeOfBondTypeDTO && null != cumToNonCumChangeOfBondTypeDTO.getPanNum() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(cumToNonCumChangeOfBondTypeDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cumToNonCumChangeOfBondTypeBo.inquiry(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionId(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getsubscriptionId(cumToNonCumChangeOfBondTypeDTO);
    }


    @Override
    public ResponseDataDTO modifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.modifySelect(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO modify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        else if (cumToNonCumChangeOfBondTypeDTO.getPanNum() != null && !cumToNonCumChangeOfBondTypeDTO.getPanNum().trim().isEmpty() && !cumToNonCumChangeOfBondTypeDTO.getPanNum().replaceAll("\\s+", "").toUpperCase().matches("^[A-Z]{5}[0-9]{4}[A-Z]$")) {
            responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cumToNonCumChangeOfBondTypeBo.modify(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }


        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return cumToNonCumChangeOfBondTypeBo.verifySelect(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO verify(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

        return cumToNonCumChangeOfBondTypeBo.verify(cumToNonCumChangeOfBondTypeDTO);
    }

    @Override
    public ResponseDataDTO submitVerifyForm(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }


        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return cumToNonCumChangeOfBondTypeBo.submitVerifyForm(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetailsVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getRbiBondModuleNameDetailsVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getPantorchVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getPantorchVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getsubscriptionIdVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getsubscriptionIdVer(cumToNonCumChangeOfBondTypeDTO);
    }    @Override
    public ResponseDataDTO getblaNoVer(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {
        return cumToNonCumChangeOfBondTypeBo.getblaNoVer(cumToNonCumChangeOfBondTypeDTO);
    }
    @Override
    public ResponseDataDTO deleteSubmit(CumToNonCumChangeOfBondTypeDTO cumToNonCumChangeOfBondTypeDTO) {

        if (cumToNonCumChangeOfBondTypeDTO.getSchemeCode() == null || cumToNonCumChangeOfBondTypeDTO.getSchemeCode().trim().isEmpty() || !cumToNonCumChangeOfBondTypeDTO.getSchemeCode().matches("\\d+")) {
            responseDataDTO.setMessage("Scheme Code should not be blank and must contain degits only");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }


        else if (cumToNonCumChangeOfBondTypeDTO.getBlaNo() == null || cumToNonCumChangeOfBondTypeDTO.getBlaNo().trim().isEmpty()) {
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        else if (cumToNonCumChangeOfBondTypeDTO.getSubscriptionId() == null || cumToNonCumChangeOfBondTypeDTO.getSubscriptionId().trim().isEmpty()) {
            responseDataDTO.setMessage("Bond Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        else if (cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks() == null || cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("Remarks is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(cumToNonCumChangeOfBondTypeDTO.getRejectionRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return cumToNonCumChangeOfBondTypeBo.deleteSubmit(cumToNonCumChangeOfBondTypeDTO);
    }

}
