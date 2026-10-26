package com.isl.gbm.user.management.dao.impl;


import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.ActiveUserDao;
import com.isl.gbm.user.management.dto.ActiveUserDto;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActiveUserDaoImpl implements ActiveUserDao {

    private static final Logger log = LoggerFactory.getLogger(UserRoleMappingDaoImpl.class);

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Autowired
    ConnectionPoolFactory connectionPoolFactory;
        @Override
        public ResponseDataDTO getUsers() {
            Connection connection = null;
            CallableStatement stmt = null;
            String resultMsg = "";
            ResultSet resultSet = null;
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            try {
                connection = connectionPoolFactory.getConnection();
                if (connection != null) {
                    int index = 1;
                    stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_active_user_ModSel.getProcName());
                    stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                    stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                    stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index++, Types.VARCHAR);
                    stmt.registerOutParameter(index, Types.VARCHAR);
                    stmt.execute();

                    log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                    log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                    resultMsg = stmt.getString(index);



                    if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                        resultSet = (ResultSet) stmt.getObject(3);
                        List<ActiveUserDto> userList = new ArrayList<>();
                        ActiveUserDto activeUserDto = null;
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                activeUserDto = new ActiveUserDto();
                                activeUserDto.setId(resultSet.getString(1));
                                activeUserDto.setUsername(resultSet.getString(2));
                                activeUserDto.setEmployeeName(resultSet.getString(3));
                                activeUserDto.setBranchCode(resultSet.getString(4));
                                activeUserDto.setEmployeeCode(resultSet.getString(5));
                                activeUserDto.setDormantDateTime(resultSet.getString(6));
                                activeUserDto.setStatus(resultSet.getString(7));
                                activeUserDto.setCreatedBy(stmt.getString(4));
                                activeUserDto.setCreatedDate(stmt.getString(5));
                                activeUserDto.setModifiedBy(stmt.getString(6));
                                activeUserDto.setModifiedDate(stmt.getString(7));
                                activeUserDto.setVerifiedBy(stmt.getString(8));
                                activeUserDto.setVerifiedDate(stmt.getString(9));
                                activeUserDto.setVerifiedStatus(stmt.getString(10));
                                activeUserDto.setRemarks(stmt.getString(11));


                                userList.add(activeUserDto);
                            }
                        }
;
                        responseDataDTO.setResult(userList);
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
            return responseDataDTO;
        }

@Override
public ResponseDataDTO modifyUsers(ActiveUserDto activeUserDto) {
    Connection connection = null;
    CallableStatement stmt = null;
    ResponseDataDTO responseDataDTO = new ResponseDataDTO();
    String resultMsg = "";
    try {
        connection = connectionPoolFactory.getConnection();
        if (connection != null) {
            int index = 1;
            stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_active_user_Mod.getProcName());

            stmt.setString(index++, UserAccountUtils.getCurrentUsername());
            stmt.setString(index++, UserAccountUtils.getCurrentSolId());
            stmt.setArray(index++, generateTYP_SINGLE_COL_OBJ(connection, activeUserDto.getUserListone()));
            stmt.registerOutParameter(index++, Types.VARCHAR);
            stmt.registerOutParameter(index, Types.VARCHAR);
            stmt.execute();
            log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
            log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
            resultMsg = stmt.getString(index);
        } else {
            log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
            resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
        }

    }  catch (Exception e) {
        log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        resultMsg = e.getMessage();
    } finally {
        connectionPoolFactory.closeConnection(connection, stmt);
    }
    responseDataDTO.setMessage(resultMsg);
    return responseDataDTO;
}
public User validateUserStatus(String userName){
    Connection connection = null;
    PreparedStatement stmt = null;
    String resultMsg = "";
    ResultSet resultSet = null;
    User user=new User();
    try {
        connection = connectionPoolFactory.getConnection();
        int index = 1;
        if (connection != null) {
            //stmt = connection.prepareStatement("SELECT CASE WHEN status = 'D01' THEN 'D01' WHEN status IN ('A01') AND (TRUNC((sysdate-last_success_login))+1>(SELECT dormancy_period FROM gbm_user_mgmt_user_param WHERE verified_status ='Y' AND is_Active ='Y' AND is_deleted ='N')) THEN 'D02' ELSE status END AS status FROM gbm_user_mgmt_users WHERE username=? AND verified_status = 'Y'");
            stmt = connection.prepareStatement("SELECT CASE WHEN u.status = 'D01' THEN 'D01' WHEN u.status = 'T01' THEN 'T01' WHEN u.status = 'A01' AND (TRUNC(SYSDATE - u.last_success_login) + 1 > (SELECT dormancy_period FROM gbm_user_mgmt_user_param WHERE verified_status = 'Y' AND is_active = 'Y' AND is_deleted = 'N')) THEN 'D02' WHEN u.status = 'A01' AND r.disable_role = '1' THEN 'RD' WHEN u.status = 'A01' AND r.disable_role = '0' THEN 'A01' ELSE u.status END AS status FROM gbm_user_mgmt_users u LEFT JOIN gbm_user_mgmt_roles r ON u.role_name = r.id WHERE u.username = ? AND u.verified_status = 'Y'");
            stmt.setString(index, userName);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                user.setStatus(resultSet.getString("status"));
                resultMsg = CommonConstants.SUCCESS_CONST;
            } else {
                resultMsg = CommonConstants.NDF;
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
    return user;
}

    private static ARRAY generateTYP_SINGLE_COL_OBJ(Connection connection, List<String> userListone) throws Exception {
        StructDescriptor genericTableStruct = StructDescriptor.createDescriptor("TYP_SINGLE_COL_OBJ", connection);
        ArrayDescriptor genericTableDescriptor = ArrayDescriptor.createDescriptor("TYP_SINGLE_COL", connection);

        STRUCT[] genericTableInsertStructs = null;
        if (userListone == null) {
            genericTableInsertStructs = new STRUCT[1];
            Object[] paramArray = null;
            STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
            genericTableInsertStructs[0] = struct;
        } else {
            genericTableInsertStructs = new STRUCT[userListone.size()];
            for (int index = 0; index < userListone.size(); index++) {
                Object[] paramArray = new Object[1];
                paramArray[0] = userListone.get(index);

                STRUCT struct = new STRUCT(genericTableStruct, connection, paramArray);
                genericTableInsertStructs[index] = struct;
            }
        }
        return new ARRAY(genericTableDescriptor, connection, genericTableInsertStructs);
    }

    @Override
    public ResponseDataDTO getVerifyDetails() {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResultSet resultSet = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_active_user_sp_ver_sel.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();

                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);



                if (stmt.getString(index-1) != null && stmt.getString(index-1).equals(CommonConstants.SUCCESS)) {


                    resultSet = (ResultSet) stmt.getObject(3);
                    List<ActiveUserDto> userList = new ArrayList<>();
                    ActiveUserDto activeUserDto = null;
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            activeUserDto = new ActiveUserDto();
                            activeUserDto.setId(resultSet.getString(1));
                            activeUserDto.setUsername(resultSet.getString(2));
                            activeUserDto.setEmployeeName(resultSet.getString(3));
                            activeUserDto.setBranchCode(resultSet.getString(4));
                            activeUserDto.setEmployeeCode(resultSet.getString(5));
                            activeUserDto.setDormantDateTime(resultSet.getString(6));
                            activeUserDto.setStatus(resultSet.getString(7));
                            activeUserDto.setCreatedBy(stmt.getString(4));
                            activeUserDto.setCreatedDate(stmt.getString(5));
                            activeUserDto.setModifiedBy(stmt.getString(6));
                            activeUserDto.setModifiedDate(stmt.getString(7));
                            activeUserDto.setVerifiedBy(stmt.getString(8));
                            activeUserDto.setVerifiedDate(stmt.getString(9));
                            activeUserDto.setPrevOperType(stmt.getString(10));
                            activeUserDto.setVerifiedStatus(stmt.getString(11));
                            activeUserDto.setRemarks(stmt.getString(12));
                            userList.add(activeUserDto);
                        }
                    }

                    responseDataDTO.setResult(userList);
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
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO verifyUsers(ActiveUserDto activeUserDto) {
        Connection connection = null;
        CallableStatement stmt = null;
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "";
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_active_user_sp_ver.getProcName());

                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateTYP_SINGLE_COL_OBJ(connection, activeUserDto.getUserListone()));
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE + stmt.getString(index));
                resultMsg = stmt.getString(index);
            } else {
                log.error(CommonConstants.EXCEPTION_MESSAGE_CONNECTION_NOT_AVAILABLE);
                resultMsg = ErrorMessages.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            log.error(CommonConstants.EXCEPTION_MESSAGE, e);
        } finally {
            connectionPoolFactory.closeConnection(connection, stmt);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO deleteUsers(ActiveUserDto activeUserDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (activeUserDto.getRemarks() == null
                || activeUserDto.getRemarks().trim().isEmpty()) {
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(activeUserDto.getRemarks())) {
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
                stmt = connection.prepareCall(ProcNameEnum.pkg_gbm_active_user_sp_del.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.setArray(index++, generateTYP_SINGLE_COL_OBJ(connection, activeUserDto.getUserListone()));
                stmt.setString(index++, activeUserDto.getRemarks());
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();


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
