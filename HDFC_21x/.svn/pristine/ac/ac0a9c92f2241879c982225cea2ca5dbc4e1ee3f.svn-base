package com.isl.gbm.user.isac.bo.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.isac.bo.IsacBO;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.isl.gbm.user.isac.dao.IsacDAO;
import com.isl.gbm.user.isac.dto.*;
import com.isl.gbm.user.management.constants.UserConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.Collection;

@Component
public class IsacBOImpl implements IsacBO {
    @Autowired
    IsacDAO isacDAO;
    private static final Logger log = LoggerFactory.getLogger(IsacBOImpl.class);

    @Override
    public IsacResponseDTO userCreation(UserRequestDTO userRequestDTO, BindingResult result) {

        ResponseDataDTO responseDataDTO;
        IsacResponseDTO isacResponseDTO = new IsacResponseDTO();
    try {
    if (result.hasErrors()) {
        StringBuilder errors = new StringBuilder();
        errors.append("INVALID FIELD DATA(");
        for (FieldError error : result.getFieldErrors()) {
            errors.append(error.getDefaultMessage()).append("; ");
        }
        errors.append(")");
        isacResponseDTO.setErrorMessage(errors.toString());
        isacResponseDTO.setErrorCode(CommonConstant.INVLAID_FIELD_DATA);
        isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo() != null ? userRequestDTO.getiSACTicketNo() : " ");
        if(isacResponseDTO.getTicketNumber() != null) {
            isacDAO.userCreation(userRequestDTO, isacResponseDTO);
        }

    } else {
        isacResponseDTO.setErrorMessage(CommonConstant.SUCCESS_DESC);
        isacResponseDTO.setErrorCode(CommonConstant.SUCCESS);
        isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo());
        responseDataDTO = isacDAO.userCreation(userRequestDTO, isacResponseDTO);
        if (responseDataDTO.getStatusCode() != null && responseDataDTO.getMessage() != null) {
            log.info("Getting Response from DAOImpl");
            isacResponseDTO.setErrorCode(Integer.parseInt(responseDataDTO.getStatusCode()));
            isacResponseDTO.setErrorMessage(responseDataDTO.getMessage());
        }
    }

    }
      catch (Exception e){
          log.error(CommonConstants.EXCEPTION_MESSAGE, e);
          isacResponseDTO.setErrorCode(Integer.parseInt(ErrorMessages.ISAC_EXCEPTION_ERROR_CODE));
          isacResponseDTO.setErrorMessage(ErrorMessages.TECHNICAL_ERROR);
          isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo());
      }
    return isacResponseDTO;
    }


    @Override
    public IsacResponseDTO unlockUser(UnlockUserRequestDTO unlockUserRequestDTO, BindingResult result) {
        IsacResponseDTO isacResponseDTO = new IsacResponseDTO();
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            if (result.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                errors.append("INVALID FIELD DATA(");
                for (FieldError error : result.getFieldErrors()) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
                errors.append(")");
                isacResponseDTO.setErrorMessage(errors.toString());
                isacResponseDTO.setErrorCode(CommonConstant.INVLAID_FIELD_DATA);
                isacResponseDTO.setTicketNumber(unlockUserRequestDTO.getiSACTicketNo() != null ? unlockUserRequestDTO.getiSACTicketNo() : " ");
                if (isacResponseDTO.getTicketNumber() != null) {
                    isacDAO.unlockUser(unlockUserRequestDTO, isacResponseDTO);
                }
            } else {
                isacResponseDTO.setErrorMessage(CommonConstant.SUCCESS_DESC);
                isacResponseDTO.setErrorCode(CommonConstant.SUCCESS);
                isacResponseDTO.setTicketNumber(unlockUserRequestDTO.getiSACTicketNo());
                responseDataDTO = isacDAO.unlockUser(unlockUserRequestDTO, isacResponseDTO);
                if (responseDataDTO.getStatusCode() != null && responseDataDTO.getMessage() != null) {
                    log.info("Getting Response from DAOImpl");
                    isacResponseDTO.setErrorCode(Integer.parseInt(responseDataDTO.getStatusCode()));
                    isacResponseDTO.setErrorMessage(responseDataDTO.getMessage());
                }
            }
        }catch (Exception e){
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            isacResponseDTO.setErrorCode(Integer.parseInt(ErrorMessages.ISAC_EXCEPTION_ERROR_CODE));
            isacResponseDTO.setErrorMessage(ErrorMessages.TECHNICAL_ERROR);
            isacResponseDTO.setTicketNumber(unlockUserRequestDTO.getiSACTicketNo());
        }
        return isacResponseDTO;
    }

    @Override
    public IsacResponseDTO enableDisableRevokeUser(UserEnableDisableRevokeRequestDTO userEnableDisableRevokeRequestDTO, BindingResult result) {
        IsacResponseDTO isacResponseDTO = new IsacResponseDTO();
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            if (result.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                errors.append("INVALID FIELD DATA(");
                for (FieldError error : result.getFieldErrors()) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
                errors.append(")");
                isacResponseDTO.setErrorMessage(errors.toString());
                isacResponseDTO.setErrorCode(CommonConstant.INVLAID_FIELD_DATA);
                isacResponseDTO.setTicketNumber(userEnableDisableRevokeRequestDTO.getiSACTicketNo() != null ? userEnableDisableRevokeRequestDTO.getiSACTicketNo() : " ");
                if (isacResponseDTO.getTicketNumber() != null) {
                    isacDAO.enableDisableRevokeUser(userEnableDisableRevokeRequestDTO, isacResponseDTO);
                }
            } else {
                isacResponseDTO.setErrorMessage(CommonConstant.SUCCESS_DESC);
                isacResponseDTO.setErrorCode(CommonConstant.SUCCESS);
                isacResponseDTO.setTicketNumber(userEnableDisableRevokeRequestDTO.getiSACTicketNo());
                responseDataDTO = isacDAO.enableDisableRevokeUser(userEnableDisableRevokeRequestDTO, isacResponseDTO);
                if (responseDataDTO.getStatusCode() != null && responseDataDTO.getMessage() != null) {
                    log.info("Getting Response from DAOImpl");
                    isacResponseDTO.setErrorCode(Integer.parseInt(responseDataDTO.getStatusCode()));
                    isacResponseDTO.setErrorMessage(responseDataDTO.getMessage());
                }
            }
        }catch (Exception e){
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            isacResponseDTO.setErrorCode(Integer.parseInt(ErrorMessages.ISAC_EXCEPTION_ERROR_CODE));
            isacResponseDTO.setErrorMessage(ErrorMessages.TECHNICAL_ERROR);
            isacResponseDTO.setTicketNumber(userEnableDisableRevokeRequestDTO.getiSACTicketNo());
        }
        return isacResponseDTO;
    }


    @Override
    public IsacResponseDTO sackUser(SackUserRequestDTO sackUserRequestDTO, BindingResult result) {
        IsacResponseDTO isacResponseDTO = new IsacResponseDTO();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            if (result.hasErrors()) {
                StringBuilder errors = new StringBuilder();
                errors.append("INVALID FIELD DATA(");
                for (FieldError error : result.getFieldErrors()) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
                errors.append(")");
                isacResponseDTO.setErrorMessage(errors.toString());
                isacResponseDTO.setErrorCode(CommonConstant.INVLAID_FIELD_DATA);
                isacResponseDTO.setTicketNumber(sackUserRequestDTO.getiSACTicketNo() != null ? sackUserRequestDTO.getiSACTicketNo() : " ");
                if (isacResponseDTO.getTicketNumber() != null) {
                    isacDAO.sackUser(sackUserRequestDTO, isacResponseDTO);
                }
            } else {
                isacResponseDTO.setErrorMessage(CommonConstant.SUCCESS_DESC);
                isacResponseDTO.setErrorCode(CommonConstant.SUCCESS);
                isacResponseDTO.setTicketNumber(sackUserRequestDTO.getiSACTicketNo());
                responseDataDTO = isacDAO.sackUser(sackUserRequestDTO, isacResponseDTO);
                this.userSacking(sackUserRequestDTO.getUserID());
                if (responseDataDTO.getStatusCode() != null && responseDataDTO.getMessage() != null) {
                    log.info("Getting Response from DAOImpl");
                    isacResponseDTO.setErrorCode(Integer.parseInt(responseDataDTO.getStatusCode()));
                    isacResponseDTO.setErrorMessage(responseDataDTO.getMessage());
                }
            }
        }catch (Exception e){
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            isacResponseDTO.setErrorCode(Integer.parseInt(ErrorMessages.ISAC_EXCEPTION_ERROR_CODE));
            isacResponseDTO.setErrorMessage(ErrorMessages.TECHNICAL_ERROR);
            isacResponseDTO.setTicketNumber(sackUserRequestDTO.getiSACTicketNo());
        }
        return isacResponseDTO;
    }





    public void userSacking(String username) {
        final DefaultSecurityManager securityManager = (DefaultSecurityManager) SecurityUtils.getSecurityManager();
        final DefaultSessionManager sessionManager = (DefaultSessionManager) securityManager.getSessionManager();
        final Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();

        for (Session session : activeSessions) {
            try{
                String sessionUsername = (String) session.getAttribute(UserConstants.USERNAME);
                if(sessionUsername.equalsIgnoreCase(username)){
                    log.info("Killing Username: " + username + " with session ID: " + session.getId());
                    sessionManager.getSessionDAO().delete(session);
                    session.stop();
                }

            }catch (Exception e){}
        }
    }



    @Override
    public IsacResponseDTO userModify(IsacUserRequestModifyDTO userRequestDTO, BindingResult result) {

        ResponseDataDTO responseDataDTO;
        IsacResponseDTO isacResponseDTO = new IsacResponseDTO();
        try {
            if (result.hasErrors()) {
                IsacUserRequestModifyDTO oldValue=new IsacUserRequestModifyDTO();
                StringBuilder errors = new StringBuilder();
                errors.append("INVALID FIELD DATA(");
                for (FieldError error : result.getFieldErrors()) {
                    errors.append(error.getDefaultMessage()).append("; ");
                }
                errors.append(")");
                isacResponseDTO.setErrorMessage(errors.toString());
                isacResponseDTO.setErrorCode(CommonConstant.INVLAID_FIELD_DATA);
                isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo() != null ? userRequestDTO.getiSACTicketNo() : " ");
                if(isacResponseDTO.getTicketNumber() != null) {
                    isacDAO.userModify(userRequestDTO, oldValue,isacResponseDTO);
                }

            } else {

                ResponseDataDTO modifySelectResponseDao = isacDAO.userModifySelect(userRequestDTO.getUserID());
                if(modifySelectResponseDao.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)){
                    IsacUserRequestModifyDTO oldValue = (IsacUserRequestModifyDTO) modifySelectResponseDao.getResult();
                    userRequestDTO = this.compareOldValueModify(oldValue, userRequestDTO);

                    isacResponseDTO.setErrorMessage(CommonConstant.SUCCESS_DESC);
                    isacResponseDTO.setErrorCode(CommonConstant.SUCCESS);
                    isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo());
                    responseDataDTO = isacDAO.userModify(userRequestDTO,oldValue, isacResponseDTO);
                    if (responseDataDTO.getStatusCode() != null && responseDataDTO.getMessage() != null) {
                        log.info("Getting Response from DAOImpl");
                        isacResponseDTO.setErrorCode(Integer.parseInt(responseDataDTO.getStatusCode()));
                        isacResponseDTO.setErrorMessage(responseDataDTO.getMessage());
                    }

                }else{
                    IsacUserRequestModifyDTO oldValue=new IsacUserRequestModifyDTO();
                    isacResponseDTO.setErrorMessage(modifySelectResponseDao.getMessage());
                    isacResponseDTO.setErrorCode(CommonConstant.INVALID_USER);
                    isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo() != null ? userRequestDTO.getiSACTicketNo() : " ");
                    ResponseDataDTO modify=isacDAO.userModify(userRequestDTO,oldValue, isacResponseDTO);
                    isacResponseDTO.setErrorCode(Integer.parseInt(modify.getStatusCode()));
                }


            }

        }
        catch (Exception e){
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            isacResponseDTO.setErrorCode(Integer.parseInt(ErrorMessages.ISAC_EXCEPTION_ERROR_CODE));
            isacResponseDTO.setErrorMessage(ErrorMessages.TECHNICAL_ERROR);
            isacResponseDTO.setTicketNumber(userRequestDTO.getiSACTicketNo());
        }
        return isacResponseDTO;
    }



    public IsacUserRequestModifyDTO compareOldValueModify(IsacUserRequestModifyDTO oldValue, IsacUserRequestModifyDTO newValue){


        //checking employeeName
        if(this.changeOccurredVal(oldValue.getEmployeeName(), newValue.getEmployeeName())){
            newValue.setEmployeeName(oldValue.getEmployeeName());
        }

        //checking branchName
        if(this.changeOccurredVal(oldValue.getBranchName(), newValue.getBranchName())){
            newValue.setBranchName(oldValue.getBranchName());
        }

        //checking branchCode
        if(this.changeOccurredVal(oldValue.getBranchCode(), newValue.getBranchCode())){
            newValue.setBranchCode(oldValue.getBranchCode());
        }

        //checking departmentCode
        if(this.changeOccurredVal(oldValue.getDepartmentCode(), newValue.getDepartmentCode())){
            newValue.setDepartmentCode(oldValue.getDepartmentCode());
        }

        //checking departmentName
        if(this.changeOccurredVal(oldValue.getDepartmentName(), newValue.getDepartmentName())){
            newValue.setDepartmentName(oldValue.getDepartmentName());
        }

        //checking roleName
        if(this.changeOccurredVal(oldValue.getRoleName(), newValue.getRoleName())){
            newValue.setRoleName(oldValue.getRoleName());
        }


        //checking email
        if(this.changeOccurredVal(oldValue.getEmail(), newValue.getEmail())){
            newValue.setEmail(oldValue.getEmail());
        }

        //checking mobileNo
        if(this.changeOccurredVal(oldValue.getMobileNo(), newValue.getMobileNo())){
            newValue.setMobileNo(oldValue.getMobileNo());
        }

        //checking address
        if(this.changeOccurredVal(oldValue.getAddress(), newValue.getAddress())){
            newValue.setAddress(oldValue.getAddress());
        }

        //checking supervisorCode
        if(this.changeOccurredVal(oldValue.getSupervisorCode(), newValue.getSupervisorCode())){
            newValue.setSupervisorCode(oldValue.getSupervisorCode());
        }
        return newValue;
    }

    public boolean changeOccurredVal(String oldValue, String newValue){
        //returning true here bind oldvalue to newvalue
        if(newValue == null){
            return true;
        }else{
            if(oldValue == null){
                return false;
            }else{
                if(newValue.equalsIgnoreCase(oldValue)){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

}
