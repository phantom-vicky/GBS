package com.isl.gbm.user.isac.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.isl.gbm.user.isac.dao.IsacDAO;
import com.isl.gbm.user.isac.dto.*;
import com.isl.gbm.user.isac.util.ProcNamesEnum;
import oracle.jdbc.internal.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

@Repository
public class IsacDAOImpl implements IsacDAO {
    private static final Logger log = LoggerFactory.getLogger(IsacDAOImpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;
    @Override
    public ResponseDataDTO userCreation(UserRequestDTO userRequestDTO, IsacResponseDTO isacResponseDTO) {
        log.info("DAO UserCreation Request: {}", userRequestDTO);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        String errorCode = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_CREATION.getProcName());
                stmt.setString(index++, CommonConstant.USER_CREATION);
                stmt.setString(index++, userRequestDTO.getiSACTicketNo());
                stmt.setString(index++, userRequestDTO.toString());
                stmt.setInt(index++, isacResponseDTO.getErrorCode() !=null ? isacResponseDTO.getErrorCode(): 0);
                stmt.setString(index++, isacResponseDTO.getErrorMessage() != null ? isacResponseDTO.getErrorMessage():"");
                stmt.setString(index++, userRequestDTO.getUserID() !=null ? userRequestDTO.getUserID() : "");
                stmt.setString(index++, userRequestDTO.getEmployeeCode() != null ? userRequestDTO.getEmployeeCode(): "");
                stmt.setString(index++, userRequestDTO.getEmployeeName() != null ? userRequestDTO.getEmployeeName():"");
                stmt.setString(index++, userRequestDTO.getBranchName() != null ? userRequestDTO.getBranchName():"");
                stmt.setString(index++, userRequestDTO.getBranchCode() != null ? userRequestDTO.getBranchCode():"");
                stmt.setString(index++, userRequestDTO.getDepartmentCode() != null ? userRequestDTO.getDepartmentCode():"");
                stmt.setString(index++, userRequestDTO.getDepartmentName() != null ? userRequestDTO.getDepartmentName():"");
                stmt.setString(index++, userRequestDTO.getEmail() != null ? userRequestDTO.getEmail():"");
                stmt.setString(index++, userRequestDTO.getMobileNo() != null ? userRequestDTO.getMobileNo(): "");
                stmt.setString(index++, userRequestDTO.getAddress() != null ? userRequestDTO.getAddress():"");
                stmt.setString(index++, userRequestDTO.getSupervisorCode() !=null ? userRequestDTO.getSupervisorCode():"");
                stmt.setString(index++, userRequestDTO.getRoleName() != null ? userRequestDTO.getRoleName():"");
                stmt.setString(index++, userRequestDTO.getInitiatedBy() != null ? userRequestDTO.getInitiatedBy():"");
                stmt.setString(index++, userRequestDTO.getApprovedBy() != null ? userRequestDTO.getApprovedBy():"");
                stmt.setString(index++, "$2a$12$38lBOxRzAnizAazQzKmtiucrDRWmxlpewnzsxb5wTSnXDTeOTmNIG");
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if( stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.FAILURE_MGS) || stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.SUCCESS_MSG)){
                    errorCode = stmt.getString(index - 3);
                }


            } else {
                resultMsg = ErrorMessages.TECHNICAL_ERROR;
                errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
            errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setStatusCode(errorCode);
        return responseDataDTO;

    }
    @Override
    public ResponseDataDTO unlockUser(UnlockUserRequestDTO unlockUserRequestDTO, IsacResponseDTO isacResponseDTO) {
        log.info("DAO unlockUser Request: {}", unlockUserRequestDTO);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        String errorCode = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_UNLOCK_USER.getProcName());
                stmt.setString(index++, CommonConstant.UNLOCK_USER);
                stmt.setString(index++, unlockUserRequestDTO.getiSACTicketNo());
                stmt.setString(index++, unlockUserRequestDTO.toString());
                stmt.setInt(index++, isacResponseDTO.getErrorCode() !=null ? isacResponseDTO.getErrorCode(): 0);
                stmt.setString(index++, isacResponseDTO.getErrorMessage() != null ? isacResponseDTO.getErrorMessage():"");
                stmt.setString(index++, unlockUserRequestDTO.getUserID() !=null ? unlockUserRequestDTO.getUserID() : "");
                stmt.setString(index++, unlockUserRequestDTO.getInitiatedBy() != null ? unlockUserRequestDTO.getInitiatedBy():"");
                stmt.setString(index++, unlockUserRequestDTO.getApprovedBy() != null ? unlockUserRequestDTO.getApprovedBy():"");
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if( stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.FAILURE_MGS) || stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.SUCCESS_MSG)){
                    errorCode = stmt.getString(index - 3);
                }
            } else {
                resultMsg = ErrorMessages.TECHNICAL_ERROR;
                errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
            errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setStatusCode(errorCode);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO sackUser(SackUserRequestDTO sackUserRequestDTO, IsacResponseDTO isacResponseDTO) {
        log.info("DAO sackUser Request: {}", sackUserRequestDTO);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        String errorCode = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_SACK_USER.getProcName());
                stmt.setString(index++, CommonConstant.SACK_USER);
                stmt.setString(index++, sackUserRequestDTO.getiSACTicketNo());
                stmt.setString(index++, sackUserRequestDTO.toString());
                stmt.setInt(index++, isacResponseDTO.getErrorCode() !=null ? isacResponseDTO.getErrorCode(): 0);
                stmt.setString(index++, isacResponseDTO.getErrorMessage() != null ? isacResponseDTO.getErrorMessage():"");
                stmt.setString(index++, sackUserRequestDTO.getUserID() !=null ? sackUserRequestDTO.getUserID() : "");
                stmt.setString(index++, sackUserRequestDTO.getInitiatedBy() != null ? sackUserRequestDTO.getInitiatedBy():"");
                stmt.setString(index++, sackUserRequestDTO.getApprovedBy() != null ? sackUserRequestDTO.getApprovedBy():"");
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if( stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.FAILURE_MGS) || stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.SUCCESS_MSG)){
                    errorCode = stmt.getString(index - 3);
                }
            } else {
                resultMsg = ErrorMessages.TECHNICAL_ERROR;
                errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
            errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setStatusCode(errorCode);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO enableDisableRevokeUser(UserEnableDisableRevokeRequestDTO userEnableDisableRevokeRequestDTO, IsacResponseDTO isacResponseDTO) {
        log.info("DAO enableDisableRevokeUser Request: {}", userEnableDisableRevokeRequestDTO);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        String errorCode = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                if("A01".equalsIgnoreCase(userEnableDisableRevokeRequestDTO.getStatus())) {
                    stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_ENABLE.getProcName());
                    stmt.setString(index++, CommonConstant.ENABLE_USER);
                }else if("D01".equalsIgnoreCase(userEnableDisableRevokeRequestDTO.getStatus())){
                    stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_DISABLE.getProcName());
                    stmt.setString(index++, CommonConstant.DISABLE_USER);
                }else if("T01".equalsIgnoreCase(userEnableDisableRevokeRequestDTO.getStatus())){
                    stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_TERMINATION.getProcName());
                    stmt.setString(index++, CommonConstant.TERMINATE_USER);
                }else{
                    stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_REVOKE.getProcName());
                    stmt.setString(index++, CommonConstant.REVOKE_USER);
                }
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.getStatus());
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.getiSACTicketNo());
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.toString());
                stmt.setInt(index++, isacResponseDTO.getErrorCode() !=null ? isacResponseDTO.getErrorCode(): 0);
                stmt.setString(index++, isacResponseDTO.getErrorMessage() != null ? isacResponseDTO.getErrorMessage():"");
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.getUserID() !=null ? userEnableDisableRevokeRequestDTO.getUserID() : "");
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.getInitiatedBy() != null ? userEnableDisableRevokeRequestDTO.getInitiatedBy():"");
                stmt.setString(index++, userEnableDisableRevokeRequestDTO.getApprovedBy() != null ? userEnableDisableRevokeRequestDTO.getApprovedBy():"");
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if( stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.FAILURE_MGS) || stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.SUCCESS_MSG)){
                    errorCode = stmt.getString(index - 3);
                }
            } else {
                resultMsg = ErrorMessages.TECHNICAL_ERROR;
                errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
            errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setStatusCode(errorCode);
        return responseDataDTO;

    }

    @Override
    public ResponseDataDTO userModify(IsacUserRequestModifyDTO userRequestDTO,IsacUserRequestModifyDTO oldValue, IsacResponseDTO isacResponseDTO) {
        log.info("DAO userModify Request: {}, Old Value: {}", userRequestDTO, oldValue);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        String errorCode = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_MODIFY.getProcName());
                stmt.setString(index++, CommonConstant.USER_MODIFY);
                stmt.setString(index++, userRequestDTO.getiSACTicketNo());
                stmt.setString(index++, userRequestDTO.toString());
                stmt.setInt(index++, isacResponseDTO.getErrorCode() !=null ? isacResponseDTO.getErrorCode(): 0);
                stmt.setString(index++, isacResponseDTO.getErrorMessage() != null ? isacResponseDTO.getErrorMessage():"");
                stmt.setString(index++, userRequestDTO.getUserID() !=null ? userRequestDTO.getUserID() : oldValue.getUserID());
//                stmt.setString(index++, userRequestDTO.getEmployeeCode() != null ? userRequestDTO.getEmployeeCode(): "");
                stmt.setString(index++, (!userRequestDTO.getEmployeeName().isEmpty()) ? userRequestDTO.getEmployeeName(): oldValue.getEmployeeName());
                stmt.setString(index++, (!userRequestDTO.getBranchName().isEmpty()) ? userRequestDTO.getBranchName():oldValue.getBranchName());
                stmt.setString(index++, (!userRequestDTO.getBranchCode().isEmpty()) ? userRequestDTO.getBranchCode():oldValue.getBranchCode());
                stmt.setString(index++, (!userRequestDTO.getDepartmentCode().isEmpty()) ? userRequestDTO.getDepartmentCode():oldValue.getDepartmentCode());
                stmt.setString(index++, (!userRequestDTO.getDepartmentName().isEmpty()) ? userRequestDTO.getDepartmentName():oldValue.getDepartmentName());
                stmt.setString(index++, (!userRequestDTO.getEmail().isEmpty()) ? userRequestDTO.getEmail():oldValue.getEmail());
                stmt.setString(index++, (!userRequestDTO.getMobileNo().isEmpty()) ? userRequestDTO.getMobileNo():oldValue.getMobileNo());
                stmt.setString(index++, (!userRequestDTO.getAddress().isEmpty()) ? userRequestDTO.getAddress():oldValue.getAddress());
                stmt.setString(index++, (!userRequestDTO.getSupervisorCode().isEmpty()) ? userRequestDTO.getSupervisorCode():oldValue.getSupervisorCode());
                stmt.setString(index++, (!userRequestDTO.getRoleName().isEmpty()) ? userRequestDTO.getRoleName().toUpperCase():oldValue.getRoleName().toUpperCase());
                stmt.setString(index++, (!userRequestDTO.getInitiatedBy().isEmpty()) ? userRequestDTO.getInitiatedBy():oldValue.getInitiatedBy());
                stmt.setString(index++, (!userRequestDTO.getApprovedBy().isEmpty()) ? userRequestDTO.getApprovedBy():oldValue.getApprovedBy());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
                if( stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.FAILURE_MGS) || stmt.getString(index - 1).equalsIgnoreCase(CommonConstant.SUCCESS_MSG)){
                    errorCode = stmt.getString(index - 3);
                }


            } else {
                resultMsg = ErrorMessages.TECHNICAL_ERROR;
                errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.TECHNICAL_ERROR;
            errorCode = ErrorMessages.ISAC_EXCEPTION_ERROR_CODE;

        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setStatusCode(errorCode);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO userModifySelect(String userId) {
        log.info("DAO userModifySelect Request: {}", userId);
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        IsacUserRequestModifyDTO output = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNamesEnum.PKG_GBM_USER_MGMT_ISAC_USER_MODIFY_SEL.getProcName());
                stmt.setString(index++, userId);
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                resultMsg = stmt.getString(index);
                log.info(CommonConstants.STATUS_MESSAGE + " {}", resultMsg);
                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(2);

                    if(resultSet != null){

                        output = new IsacUserRequestModifyDTO();

                        if(resultSet.next()) {

                            output.setUserID(userId);

                            output.setEmployeeName(resultSet.getString(1));
                            output.setBranchName(resultSet.getString(2));
                            output.setBranchCode(resultSet.getString(3));
                            output.setDepartmentCode(resultSet.getString(4));
                            output.setDepartmentName(resultSet.getString(5));
                            output.setEmail(resultSet.getString(6));
                            output.setMobileNo(resultSet.getString(7));
                            output.setAddress(resultSet.getString(8));
                            output.setSupervisorCode(resultSet.getString(9));
                            output.setRoleName(resultSet.getString(10));

                        }

                    }


                }
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(output);
        return responseDataDTO;
    }
}
