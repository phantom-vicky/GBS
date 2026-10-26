package com.isl.gbm.user.mgmt.dao.impl;

import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.ConnectionPoolFactory;
import com.isl.gbm.common.util.DataBaseUtils;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.user.management.dao.HibernateDao;
import com.isl.gbm.user.management.util.ProcNameEnum;
import com.isl.gbm.user.management.util.UserAccountUtils;
import com.isl.gbm.user.mgmt.dao.UnlockUserDAO;
import com.isl.gbm.user.mgmt.dto.UserCreationRoleMappingDTO;
import oracle.jdbc.internal.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class UnlockUserDAOimpl extends HibernateDao implements UnlockUserDAO {


    private static final Logger log = LoggerFactory.getLogger(UnlockUserDAOimpl.class);

    @Autowired
    private ConnectionPoolFactory connectionPoolFactory;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;



    @Override
    public ResponseDataDTO modifySelect(UserCreationRoleMappingDTO dto) {
        Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        dto = new UserCreationRoleMappingDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_UNLOCK_USER_MOD_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setString(index++, "SUPERAADMIN1");
//                stmt.setString(index++, "101");
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);//created by 4
                stmt.registerOutParameter(index++, Types.VARCHAR);//created date 5

                stmt.registerOutParameter(index++, Types.VARCHAR);//modified by 6
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified date 7
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified by 8
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified date 9
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified status 10

                stmt.registerOutParameter(index++, Types.VARCHAR);//remarks 11
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(3);
                    List<UserCreationRoleMappingDTO> list = new ArrayList<>();

                    if(resultSet != null){

                        while(resultSet.next()) {
                            UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                            item.setUserId(resultSet.getString(1));
                            item.setUsername(resultSet.getString(2));
                            item.setEmployeeName(resultSet.getString(3));
                            item.setBranchCode(resultSet.getString(4));
                            item.setEmployeeCode(resultSet.getString(5));
                            item.setLockedTimeDate(resultSet.getString(6));
                            list.add(item);

                        }

                    }

                    dto.setUserList(list);
                    dto.setCreatedBy(stmt.getString(4));
                    dto.setCreatedDate(stmt.getString(5));
                    dto.setModifiedBy(stmt.getString(6));
                    dto.setModifiedDate(stmt.getString(7));
                    dto.setVerifiedBy(stmt.getString(8));
                    dto.setVerifiedDate(stmt.getString(9));
                    dto.setVerifiedStatus(stmt.getString(10));
                    dto.setRemarks(stmt.getString(11));

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
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_UNLOCK_USER_VER_SEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
                stmt.registerOutParameter(index++, OracleTypes.CURSOR);
                stmt.registerOutParameter(index++, Types.VARCHAR);//created by 4
                stmt.registerOutParameter(index++, Types.VARCHAR);//created date 5

                stmt.registerOutParameter(index++, Types.VARCHAR);//modified by 6
                stmt.registerOutParameter(index++, Types.VARCHAR);//modified date 7
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified by 8
                stmt.registerOutParameter(index++, Types.VARCHAR);//verified date 9
                stmt.registerOutParameter(index++, Types.VARCHAR);//previous oper type 10

                stmt.registerOutParameter(index++, Types.VARCHAR);//verified status 11
                stmt.registerOutParameter(index++, Types.VARCHAR);//remarks 12
                stmt.registerOutParameter(index++, Types.VARCHAR);
                stmt.registerOutParameter(index, Types.VARCHAR);
                stmt.execute();
                log.info(CommonConstants.EXCECUTED_SQL_QUERY_MESSAGE);
                log.info(CommonConstants.STATUS_MESSAGE , stmt.getString(index));
                resultMsg = stmt.getString(index);

                if (stmt.getString(index - 1) != null && stmt.getString(index - 1).equals(CommonConstants.SUCCESS)) {

                    ResultSet resultSet = (ResultSet) stmt.getObject(3);
                    List<UserCreationRoleMappingDTO> list = new ArrayList<>();
                    while(resultSet.next()) {
                        UserCreationRoleMappingDTO item = new UserCreationRoleMappingDTO();
                        item.setUserId(resultSet.getString(1));
                        item.setUsername(resultSet.getString(2));
                        item.setEmployeeName(resultSet.getString(3));
                        item.setBranchCode(resultSet.getString(4));
                        item.setEmployeeCode(resultSet.getString(5));
                        item.setLockedTimeDate(resultSet.getString(6));
                        list.add(item);
                    }

                    dto.setUserList(list);

                    dto.setCreatedBy(stmt.getString(4));
                    dto.setCreatedDate(stmt.getString(5));
                    dto.setModifiedBy(stmt.getString(6));
                    dto.setModifiedDate(stmt.getString(7));
                    dto.setVerifiedBy(stmt.getString(8));
                    dto.setVerifiedDate(stmt.getString(9));
                    dto.setPreviousOperationType(stmt.getString(10));
                    dto.setVerifiedStatus(stmt.getString(11));
                    dto.setRemarks(stmt.getString(12));

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
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_UNLOCK_USER_VER.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, dto.getUserListFiltered()));
                List<String> userList = dto.getUserList().stream().map(UserCreationRoleMappingDTO::getUserId).collect(Collectors.toList());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, userList));
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
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_UNLOCK_USER_DEL.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, dto.getUserListFiltered()));
                List<String> userList = dto.getUserList().stream().map(UserCreationRoleMappingDTO::getUserId).collect(Collectors.toList());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, userList));
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
     Connection connection = null;
        CallableStatement stmt = null;
        String resultMsg = "";
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            connection = connectionPoolFactory.getConnection();
            if (connection != null) {
                int index = 1;
                stmt = connection.prepareCall(ProcNameEnum.SP_GBM_UNLOCK_USER_MOD.getProcName());
                stmt.setString(index++, UserAccountUtils.getCurrentUsername());
                stmt.setString(index++, UserAccountUtils.getCurrentSolId());
//                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, dto.getUserListFiltered()));
                List<String> userList = dto.getUserList().stream().map(UserCreationRoleMappingDTO::getUserId).collect(Collectors.toList());
                stmt.setArray(index++, DataBaseUtils.generateTYP_GBM_PP_COL_SINGLE_ARRAY_OBJ(connection, userList));
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
