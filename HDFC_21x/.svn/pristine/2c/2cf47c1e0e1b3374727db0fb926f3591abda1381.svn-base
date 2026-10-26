package com.isl.gbm.user.mgmt.dao.impl;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dao.impl.BondBrokeragePaymentDAOimpl;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.dto.RoleDetailsDto;
import com.isl.gbm.user.management.dto.UserDetailsDto;
import com.isl.gbm.user.management.dto.UserRoleMappingDTO;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import com.isl.gbm.user.mgmt.dao.UserCreationRoleMappingDAO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import oracle.jdbc.internal.OracleTypes;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Repository
public class UserCreationRoleMappingDAOimpl extends HibernateDao implements UserCreationRoleMappingDAO {


    private static final Logger log = LoggerFactory.getLogger(UserCreationRoleMappingDAOimpl.class);
    private static final String EMAIL_REGEX =
            "^(?!.*\\s)[a-zA-Z0-9._%+-]+@(hdfcbank\\.com|hdfcbank\\.co\\.in)$";

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO getRoleDetails() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<UserCreationRoleMappingDTO> result = null;
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_GET_RLE_DTS.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, oracle.jdbc.OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet)stmt.getObject(3);
                    result  = new ArrayList<>();
                    while(resultSet.next()){
                        UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                        item.setRoleId(resultSet.getString(1));
                        item.setRoleName(resultSet.getString(2));
                        item.setRoleDescription(resultSet.getString(3));
                        result.add(item);
                    }
                }
            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(result);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getBranchDetails() {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        List<Map<String, String>> result = null;

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_GET_BRH_DTS.getProcName());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet rs = (ResultSet) stmt.getObject(1);
                    if (rs != null) {
                        result = new ArrayList<>();
                        while (rs.next()) {
                            Map<String, String> item = new HashMap<>();
                            item.put("branchCode", rs.getString(1));
                            item.put("branchName", rs.getString(2));
                            result.add(item);
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(result);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO validateUsername(String username) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            final Session session = getSession();
            Query<BigDecimal> countQuery = session.createNativeQuery("SELECT COUNT(1) FROM gbm_user_mgmt_users WHERE username = :username");
            countQuery.setParameter("username", username.toUpperCase());
            BigDecimal totalCount = countQuery.getSingleResult();
            if(totalCount.compareTo(BigDecimal.ZERO) > 0){
                resultMsg = "User Already Available";
            }else{
                resultMsg = CommonConstants.SUCCESS_CONST;
            }
        } catch (Exception e) {
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO validateEmail(String email) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            final Session session = getSession();
            Query<BigDecimal> countQuery = session.createNativeQuery("SELECT COUNT(1) FROM gbm_user_mgmt_users WHERE EMAIL = :email");
            countQuery.setParameter("email", email);
            BigDecimal totalCount = countQuery.getSingleResult();
            if(totalCount.compareTo(BigDecimal.ZERO) > 0){
                resultMsg = "Email Id  Already Available";
            }else{
                resultMsg = CommonConstants.SUCCESS_CONST;
            }
        } catch (Exception e) {
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO validateEmployeeCode(String employeeCode) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            final Session session = getSession();
            Query<BigDecimal> countQuery = session.createNativeQuery("SELECT COUNT(1) FROM gbm_user_mgmt_users WHERE EMPLOYEE_CODE = :employeeCode");
            countQuery.setParameter("employeeCode", employeeCode);
            BigDecimal totalCount = countQuery.getSingleResult();
            if(totalCount.compareTo(BigDecimal.ZERO) > 0){
                resultMsg = "Employee Code  Already Available";
            }else{
                resultMsg = CommonConstants.SUCCESS_CONST;
            }
        } catch (Exception e) {
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO add(UserCreationRoleMappingDTO dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if(dto.getUsername() == null || dto.getUsername().trim().isEmpty() || !dto.getUsername().matches("^[A-Za-z][A-Za-z0-9]*$")){
            responseDataDTO.setMessage("Username mandatory must start with a letter and contain only letters and digits");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(dto.getEmployeeName() == null || dto.getEmployeeName().trim().isEmpty() || !dto.getEmployeeName().matches(".*[a-zA-Z].*")){
            responseDataDTO.setMessage("Employee Name mandatory must start with a letter and contain only letters");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty() && !dto.getEmail().matches(EMAIL_REGEX)) {
            responseDataDTO.setMessage("Invalid email. Allows only hdfcbank.com or hdfcbank.co.in emails are allowed");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_ADD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUsername());
                stmt.setString(index++, dto.getEmployeeCode());
                stmt.setString(index++, dto.getEmployeeName());
                stmt.setString(index++, dto.getBranchCode());
                stmt.setString(index++, dto.getBranchName());
                stmt.setString(index++, dto.getDepartmentCode());
                stmt.setString(index++, dto.getDepartmentName());
                stmt.setString(index++, dto.getEmail());
                stmt.setString(index++, dto.getMobileNo());
                stmt.setString(index++, dto.getAddress());
                stmt.setString(index++, dto.getSupervisorCode());
                stmt.setString(index++, dto.getRoleId());
                stmt.setString(index++, UserAccountUtils.encryptPassword(dto.getPassword()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(UserCreationRoleMappingDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_INQ.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//user details
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//role details

                stmt.registerOutParameter(index++, Types.VARCHAR);//created by 6
                stmt.registerOutParameter(index++, Types.VARCHAR);//created date 7
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified by 8
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified date 9
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified by 10
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified date 11
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified status 12
                stmt.registerOutParameter(index++, Types.VARCHAR);//p_prev_opr_type_out 13
                stmt.registerOutParameter(index++, Types.VARCHAR);//remarks 14

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(dto.getUserId() == null){
                        //all users
                        ResultSet resultSetOne = (ResultSet) stmt.getObject(4);
                        if (resultSetOne != null) {
                            List<UserCreationRoleMappingDTO> result = new ArrayList<>();
                            while (resultSetOne.next()) {
                                UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                                item.setUserId(resultSetOne.getString(1));
                                item.setEmployeeName(resultSetOne.getString(2));
                                item.setUsername(resultSetOne.getString(3));
                                item.setBranchCode(resultSetOne.getString(4));
                                item.setEmployeeCode(resultSetOne.getString(5));
                                item.setRoleName(resultSetOne.getString(6));
                                item.setUserStatus(resultSetOne.getString(7));
                                item.setAuthStatus(resultSetOne.getString(8));
                                result.add(item);
                            }
                            dto.setUserList(result);
                        }

                    }
                    else{
                        //all users single user
                        ResultSet resultSet = (ResultSet) stmt.getObject(4);
                        if (resultSet.next()) {
                            UserCreationRoleMappingDTO oldValue = new UserCreationRoleMappingDTO();
                            oldValue.setUserId(resultSet.getString(1));
                            oldValue.setUsername(resultSet.getString(2));
                            oldValue.setEmployeeCode(resultSet.getString(3));
                            oldValue.setEmployeeName(resultSet.getString(4));
                            oldValue.setBranchCode(resultSet.getString(5));
                            oldValue.setBranchName(resultSet.getString(6));
                            oldValue.setDepartmentCode(resultSet.getString(7));
                            oldValue.setDepartmentName(resultSet.getString(8));
                            oldValue.setEmail(resultSet.getString(9));
                            oldValue.setMobileNo(resultSet.getString(10));
                            oldValue.setAddress(resultSet.getString(11));
                            oldValue.setSupervisorCode(resultSet.getString(12));
                            oldValue.setUserStatus(resultSet.getString(14));
                            dto.setOldValue(oldValue);


                            UserCreationRoleMappingDTO newValue = new UserCreationRoleMappingDTO();
                            newValue.setUserId(resultSet.getString(1));
                            newValue.setUsername(resultSet.getString(19));
                            newValue.setEmployeeCode(resultSet.getString(16));
                            newValue.setEmployeeName(resultSet.getString(26));
                            newValue.setBranchCode(resultSet.getString(15));
                            newValue.setBranchName(resultSet.getString(21));
                            newValue.setDepartmentCode(resultSet.getString(17));
                            newValue.setDepartmentName(resultSet.getString(22));
                            newValue.setEmail(resultSet.getString(23));
                            newValue.setMobileNo(resultSet.getString(24));
                            newValue.setAddress(resultSet.getString(25));
                            newValue.setSupervisorCode(resultSet.getString(18));
                            newValue.setUserStatus(resultSet.getString(27));

                            dto.setNewValue(newValue);


                            dto.setCreatedBy(stmt.getString(6));
                            dto.setCreatedDate(stmt.getString(7));
                            dto.setModifiedBy(stmt.getString(8));
                            dto.setModifiedDate(stmt.getString(9));
                            dto.setVerifiedBy(stmt.getString(10));
                            dto.setVerifiedDate(stmt.getString(11));
                            dto.setVerifiedStatus(stmt.getString(12));
                            dto.setPreviousOperationType(stmt.getString(13));
                            dto.setRemarks(stmt.getString(14));
                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(5);
                        if (resultSetTwo.next()) {
                            dto.getOldValue().setRoleName(resultSetTwo.getString(1));
                            dto.getNewValue().setRoleName(resultSetTwo.getString(2));
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(dto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//user details
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//role details

                stmt.registerOutParameter(index++, Types.VARCHAR);//created by 6
                stmt.registerOutParameter(index++, Types.VARCHAR);//created date 7
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified by 8
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified date 9
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified by 10
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified date 11
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified status 12
                stmt.registerOutParameter(index++, Types.VARCHAR);//remarks 13

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(dto.getUserId() == null){
                        //all users
                        ResultSet resultSetOne = (ResultSet) stmt.getObject(4);
                        if (resultSetOne != null) {
                            List<UserCreationRoleMappingDTO> result = new ArrayList<>();
                            while (resultSetOne.next()) {
                                UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                                item.setUserId(resultSetOne.getString(1));
                                item.setEmployeeName(resultSetOne.getString(2));
                                item.setUsername(resultSetOne.getString(3));
                                item.setBranchCode(resultSetOne.getString(4));
                                item.setEmployeeCode(resultSetOne.getString(5));
                                item.setRoleName(resultSetOne.getString(6));
                                item.setUserStatus(resultSetOne.getString(7));
                                result.add(item);
                            }
                            dto.setUserList(result);
                        }

                    }
                    else{
                        //all users single user
                        ResultSet resultSet = (ResultSet) stmt.getObject(4);
                        if (resultSet.next()) {

                            dto.setUserId(resultSet.getString(1));
                            dto.setUsername(resultSet.getString(2));
                            dto.setEmployeeCode(resultSet.getString(3));
                            dto.setEmployeeName(resultSet.getString(4));
                            dto.setBranchCode(resultSet.getString(5));
                            dto.setBranchName(resultSet.getString(6));
                            dto.setDepartmentCode(resultSet.getString(7));
                            dto.setDepartmentName(resultSet.getString(8));
                            dto.setEmail(resultSet.getString(9));
                            dto.setMobileNo(resultSet.getString(10));
                            dto.setAddress(resultSet.getString(11));
                            dto.setSupervisorCode(resultSet.getString(12));
                            dto.setUserStatus(resultSet.getString(14));



                            dto.setCreatedBy(stmt.getString(6));
                            dto.setCreatedDate(stmt.getString(7));
                            dto.setModifiedBy(stmt.getString(8));
                            dto.setModifiedDate(stmt.getString(9));
                            dto.setVerifiedBy(stmt.getString(10));
                            dto.setVerifiedDate(stmt.getString(11));
                            dto.setVerifiedStatus(stmt.getString(12));
                            dto.setRemarks(stmt.getString(13));

                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(5);
                        if (resultSetTwo.next()) {
                            dto.setRoleId(resultSetTwo.getString(1));
                            dto.setRoleName(resultSetTwo.getString(2));
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(dto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifySelect(UserCreationRoleMappingDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//user details
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);//role details

                stmt.registerOutParameter(index++, Types.VARCHAR);//created by 6
                stmt.registerOutParameter(index++, Types.VARCHAR);//created date 7
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified by 8
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified date 9
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified by 10
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified date 11
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified status 12
                stmt.registerOutParameter(index++, Types.VARCHAR);//p_prev_opr_type_out 13
                stmt.registerOutParameter(index++, Types.VARCHAR);//remarks 14

                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    if(dto.getUserId() == null){
                        //all users
                        ResultSet resultSetOne = (ResultSet) stmt.getObject(4);
                        if (resultSetOne != null) {
                            List<UserCreationRoleMappingDTO> result = new ArrayList<>();
                            while (resultSetOne.next()) {
                                UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                                item.setUserId(resultSetOne.getString(1));
                                item.setEmployeeName(resultSetOne.getString(2));
                                item.setUsername(resultSetOne.getString(3));
                                item.setBranchCode(resultSetOne.getString(4));
                                item.setEmployeeCode(resultSetOne.getString(5));
                                item.setRoleName(resultSetOne.getString(6));
                                item.setUserStatus(resultSetOne.getString(7));
                                result.add(item);
                            }
                            dto.setUserList(result);
                        }

                    }
                    else{
                        //all users single user
                        ResultSet resultSet = (ResultSet) stmt.getObject(4);
                        if (resultSet.next()) {
                            UserCreationRoleMappingDTO oldValue = new UserCreationRoleMappingDTO();
                            oldValue.setUserId(resultSet.getString(1));
                            oldValue.setUsername(resultSet.getString(2));
                            oldValue.setEmployeeCode(resultSet.getString(3));
                            oldValue.setEmployeeName(resultSet.getString(4));
                            oldValue.setBranchCode(resultSet.getString(5));
                            oldValue.setBranchName(resultSet.getString(6));
                            oldValue.setDepartmentCode(resultSet.getString(7));
                            oldValue.setDepartmentName(resultSet.getString(8));
                            oldValue.setEmail(resultSet.getString(9));
                            oldValue.setMobileNo(resultSet.getString(10));
                            oldValue.setAddress(resultSet.getString(11));
                            oldValue.setSupervisorCode(resultSet.getString(12));
                            oldValue.setUserStatus(resultSet.getString(14));
                            dto.setOldValue(oldValue);
//                            dto.setUserStatusTR(resultSet.getString(14));



                            UserCreationRoleMappingDTO newValue = new UserCreationRoleMappingDTO();
                            newValue.setUserId(resultSet.getString(1));
                            newValue.setUsername(resultSet.getString(19));
                            newValue.setEmployeeCode(resultSet.getString(16));
                            newValue.setEmployeeName(resultSet.getString(26));
                            newValue.setBranchCode(resultSet.getString(15));
                            newValue.setBranchName(resultSet.getString(21));
                            newValue.setDepartmentCode(resultSet.getString(17));
                            newValue.setDepartmentName(resultSet.getString(22));
                            newValue.setEmail(resultSet.getString(23));
                            newValue.setMobileNo(resultSet.getString(24));
                            newValue.setAddress(resultSet.getString(25));
                            newValue.setSupervisorCode(resultSet.getString(18));
                            newValue.setUserStatus(resultSet.getString(27));
//                            newValue.setUserStatusTR(resultSet.getString(27));
                            dto.setNewValue(newValue);


                            dto.setCreatedBy(stmt.getString(6));
                            dto.setCreatedDate(stmt.getString(7));
                            dto.setModifiedBy(stmt.getString(8));
                            dto.setModifiedDate(stmt.getString(9));
                            dto.setVerifiedBy(stmt.getString(10));
                            dto.setVerifiedDate(stmt.getString(11));
                            dto.setVerifiedStatus(stmt.getString(12));
                            dto.setPreviousOperationType(stmt.getString(13));
                            dto.setRemarks(stmt.getString(14));

                        }

                        ResultSet resultSetTwo = (ResultSet) stmt.getObject(5);
                        if (resultSetTwo.next()) {
                            dto.getOldValue().setRoleName(resultSetTwo.getString(1));
                            dto.getNewValue().setRoleName(resultSetTwo.getString(2));
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
        responseDataDTO.setMessage(resultMsg);
        responseDataDTO.setResult(dto);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verify(UserCreationRoleMappingDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO delete(UserCreationRoleMappingDTO dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (dto.getRemarks() == null
                || dto.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }


        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(dto.getRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.setString(index++, dto.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO modify(UserCreationRoleMappingDTO dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty() && !dto.getEmail().matches(EMAIL_REGEX)) {
            responseDataDTO.setMessage("Invalid email. Allows only hdfcbank.com or hdfcbank.co.in emails are allowed");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";

        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_USER_MGMT_USR_CRT_RLE_MPG_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setString(index++, dto.getUserId());
                stmt.setString(index++, dto.getEmployeeName());
                stmt.setString(index++, dto.getBranchCode());

                stmt.setString(index++, dto.getBranchName());
                stmt.setString(index++, dto.getDepartmentCode());
                stmt.setString(index++, dto.getDepartmentName());
                stmt.setString(index++, dto.getEmail());
                stmt.setString(index++, dto.getMobileNo());

                stmt.setString(index++, dto.getAddress());
                stmt.setString(index++, dto.getSupervisorCode());
                stmt.setString(index++, dto.getUserStatus());
                stmt.setString(index++, dto.getRoleId());
                stmt.registerOutParameter(index++, Types.VARCHAR);

                stmt.registerOutParameter(index, Types.VARCHAR);
                
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);

            } else {
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


}
