package com.isl.gbm.rbibond.service.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dao.FreezeUnfreezeDAO;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;
import com.isl.gbm.rbibond.service.FreezeUnfreezeService;
import com.isl.gbm.rbibond.util.FreezeUnfreezeValidationHelper;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.stereotype.Service;

@Service
public class FreezeUnfreezeServiceImpl implements FreezeUnfreezeService {

    private static final String PAN = "pan";
    private static final String SUB = "sub";
    private static final String SCHEME = "scheme";
    private static final String BLA = "bla";
    private static final String ADD = "add";
    private static final String MODIFY = "modify";
    private static final String DELETE = "delete";
    private static final String VERIFY = "verify";
    private static final String INVALID_OPTIONS = "Invalid Options";

    private static final String PAN_REGEX =
            "^[A-Z]{5}[0-9]{4}[A-Z]$";

    private final FreezeUnfreezeDAO freezeUnfreezeDAO;

    public FreezeUnfreezeServiceImpl(FreezeUnfreezeDAO freezeUnfreezeDAO) {
        this.freezeUnfreezeDAO = freezeUnfreezeDAO;
    }

    public ResponseDataDTO getAddDetails(FreezUnfreezDTO freezUnfreezDTO) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
try {
    //pattern validation
    if (freezUnfreezDTO != null &&  freezUnfreezDTO.getPageType().equalsIgnoreCase("add") && freezUnfreezDTO.getMakerRemarks() != null) {
        ResponseDataDTO validationError =
                FreezeUnfreezeValidationHelper.validateMakerReason(freezUnfreezDTO.getMakerRemarks());
        if (validationError != null) {
            return validationError;
        }
    }
    if (freezUnfreezDTO.getPageType().equalsIgnoreCase("SCHEME") && freezUnfreezDTO.getSchemeCode() != null){
        String schemeCode = freezUnfreezDTO.getSchemeCode();
        if(schemeCode ==null || schemeCode.trim().isEmpty()){
            responseDataDTO.setMessage("Scheme code is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String pan = freezUnfreezDTO.getPanNoName();
        if (pan != null
                && !pan.trim().isEmpty()
                && !pan.replaceAll("\\s+", "").toUpperCase().matches(PAN_REGEX)) {
            responseDataDTO.setMessage("PAN must be valid (e.g., ABCDE1234F)");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String bla = freezUnfreezDTO.getBlaNo();
        if(bla ==null || bla.trim().isEmpty()){
            responseDataDTO.setMessage("BLA Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String subId= freezUnfreezDTO.getSubId();
        if(subId ==null || subId.trim().isEmpty()){
            responseDataDTO.setMessage("Subscription ID is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
    }
    if (freezUnfreezDTO.getPageType().equalsIgnoreCase("add") || freezUnfreezDTO.getPageType().equalsIgnoreCase("modify")) {
        if (freezUnfreezDTO.getSchemeCode() == null) {
            responseDataDTO.setMessage("SchmeCode Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getName() == null) {
            responseDataDTO.setMessage("name Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getBlaNo() == null) {
            responseDataDTO.setMessage("BLA Number Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getSubId() == null && freezUnfreezDTO.getPageType().equalsIgnoreCase("add")) {
            responseDataDTO.setMessage("Sub Id  Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getAmountFreeze() == null) {
            responseDataDTO.setMessage("Amount Freeze Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getInterestFreeze() == null) {
            responseDataDTO.setMessage("Interest Freeze Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getTransferAnotherBank() == null) {
            responseDataDTO.setMessage("transfer Another Bank Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getMakerRemarks() == null) {
            responseDataDTO.setMessage(" maker Remarks  Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getPanNoName() != null) {
            if (null != freezUnfreezDTO && null != freezUnfreezDTO.getPan()) {
                RbiBondServerSideRegexUtility.validatePAN(freezUnfreezDTO.getPan());
                responseDataDTO.setMessage("invalid Pan Number");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
    }
    if (freezUnfreezDTO.getPageType().equalsIgnoreCase("verify")) {
        if (freezUnfreezDTO.getSchemeCode() == null) {
            responseDataDTO.setMessage("SchmeCode Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getName() == null) {
            responseDataDTO.setMessage("name Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getBlaNo() == null) {
            responseDataDTO.setMessage("BLA Number Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getCheckerRemarks() == null) {
            responseDataDTO.setMessage("Checker Remarks Should Not Be Blank");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (freezUnfreezDTO.getPanNoName() != null) {
            if (null != freezUnfreezDTO && null != freezUnfreezDTO.getPan()) {
                RbiBondServerSideRegexUtility.validatePAN(freezUnfreezDTO.getPan());
                responseDataDTO.setMessage("invalid Pan Number");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
    }
    switch (freezUnfreezDTO.getPageType().toLowerCase()) {
        case SCHEME:
            return freezeUnfreezeDAO.getAddDetails(freezUnfreezDTO);
        case PAN:
            return freezeUnfreezeDAO.getAddPANDetails(freezUnfreezDTO);
        case SUB:
        case BLA:
            return freezeUnfreezeDAO.getAddBLADetails(freezUnfreezDTO);
        case ADD:
            return freezeUnfreezeDAO.addSubmit(freezUnfreezDTO);
        default:
            throw new IllegalArgumentException(INVALID_OPTIONS);
    }
}catch(Exception ex) {
    responseDataDTO.setMessage(ex.getMessage());
    responseDataDTO.setStatusCode("0");
    return responseDataDTO;}
    }

    @Override
    public ResponseDataDTO inquiry(FreezUnfreezDTO freezUnfreezDTO) {
        switch (freezUnfreezDTO.getPageType().toLowerCase()) {
            case SCHEME:
                return freezeUnfreezeDAO.getInquiryDetails(freezUnfreezDTO);
            case PAN:
                return freezeUnfreezeDAO.getInquiryPANDetails(freezUnfreezDTO);
            case SUB:
            case BLA:
                return freezeUnfreezeDAO.getInquiryBLADetails(freezUnfreezDTO);
            default:
                throw new IllegalArgumentException(INVALID_OPTIONS);
        }
    }

    @Override
    public ResponseDataDTO verify(FreezUnfreezDTO freezUnfreezDTO) {
        switch (freezUnfreezDTO.getPageType().toLowerCase()) {
            case SCHEME:
                return freezeUnfreezeDAO.verifySelect(freezUnfreezDTO);
            case PAN:
                return freezeUnfreezeDAO.verifyPANDetails(freezUnfreezDTO);
            case SUB:
            case BLA:
                return freezeUnfreezeDAO.verifyBLADetails(freezUnfreezDTO);
            case VERIFY:
                return freezeUnfreezeDAO.verify(freezUnfreezDTO);
            case DELETE:
                return freezeUnfreezeDAO.delete(freezUnfreezDTO);
            default:
                throw new IllegalArgumentException(INVALID_OPTIONS);
        }

    }


    @Override
    public ResponseDataDTO modifySelect(FreezUnfreezDTO freezUnfreezDTO) {
        switch (freezUnfreezDTO.getPageType().toLowerCase()) {
            case SCHEME:
                return freezeUnfreezeDAO.modifySelect(freezUnfreezDTO);
            case PAN:
                return freezeUnfreezeDAO.modifyPANDetails(freezUnfreezDTO);
            case SUB:
            case BLA:
                return freezeUnfreezeDAO.modifyBLADetails(freezUnfreezDTO);
            case MODIFY:
                return freezeUnfreezeDAO.modifySubmit(freezUnfreezDTO);
            default:
                throw new IllegalArgumentException(INVALID_OPTIONS);
        }
    }
}
